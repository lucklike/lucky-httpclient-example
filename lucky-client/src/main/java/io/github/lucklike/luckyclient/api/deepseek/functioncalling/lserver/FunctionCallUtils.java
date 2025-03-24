package io.github.lucklike.luckyclient.api.deepseek.functioncalling.lserver;

import com.luckyframework.common.ConfigurationMap;
import com.luckyframework.common.ContainerUtils;
import com.luckyframework.common.StringUtils;
import com.luckyframework.httpclient.generalapi.describe.Describe;
import com.luckyframework.httpclient.proxy.CommonFunctions;
import com.luckyframework.reflect.AnnotationUtils;
import com.luckyframework.reflect.ClassUtils;
import com.luckyframework.reflect.MethodUtils;
import io.github.lucklike.common.api.BookApi;
import io.github.lucklike.common.api.annotation.FunctionCallDesc;
import io.github.lucklike.common.api.annotation.Required;
import io.github.lucklike.httpclient.annotation.HttpReference;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
public class FunctionCallUtils {

    private final Map<String, Function<String, String>> TOOL_CALL_MAP = new LinkedHashMap<>();
    private final List<ConfigurationMap> TOOL_CALL_CONFIGS = new ArrayList<>();

    @HttpReference
    private BookApi bookApi;


    @PostConstruct
    public void init() {
        Method[] allMethod = ClassUtils.getAllMethod(BookApi.class);
        for (Method method : allMethod) {
            Describe describeAnn = AnnotationUtils.findMergedAnnotation(method, Describe.class);
            Required requiredAnn = AnnotationUtils.findMergedAnnotation(method, Required.class);

            ConfigurationMap toolCallConfig = createToolCallConfig(
                    describeAnn.name(),
                    describeAnn.desc(),
                    requiredAnn == null ? null : requiredAnn.value()
            );

            Function<String, String> toolCall = null;
            int parameterCount = method.getParameterCount();
            if (parameterCount == 1) {
                Class<?> type = method.getParameters()[0].getType();
                Field[] fields = ClassUtils.getAllFields(type);
                for (Field field : fields) {
                    FunctionCallDesc functionCallDescAnn = AnnotationUtils.findMergedAnnotation(field, FunctionCallDesc.class);
                    if (functionCallDescAnn != null) {
                        String paramName = field.getName();
                        String paramDesc = functionCallDescAnn.desc();
                        String paramType = StringUtils.hasText(functionCallDescAnn.type())
                                ? functionCallDescAnn.type()
                                : field.getType().getSimpleName().toLowerCase();
                        addParameter(toolCallConfig, paramName, paramType, paramDesc);
                    }
                }

                toolCall = args -> {
                    try {
                        Object paramObj = CommonFunctions._json(args, ResolvableType.forMethodParameter(method, 0));
                        return CommonFunctions.json(MethodUtils.invoke(bookApi, method, paramObj));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                };
            } else if (parameterCount == 0) {
                toolCall = args -> {
                    try {
                        return CommonFunctions.json(MethodUtils.invoke(bookApi, method));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                };
            }


            TOOL_CALL_CONFIGS.add(toolCallConfig);
            if (toolCall != null) {
                TOOL_CALL_MAP.put(describeAnn.name(), toolCall);
            }

        }
    }

    public List<ConfigurationMap> getToolCallConfigs() {
        return TOOL_CALL_CONFIGS;
    }

    public String callTool(String toolName, String param) {
        return TOOL_CALL_MAP.get(toolName).apply(param);
    }

    private ConfigurationMap createToolCallConfig(String funcName, String funcDesc, String[] required) {
        ConfigurationMap tool = new ConfigurationMap();
        tool.addProperty("type", "function");
        tool.addProperty("function.name", funcName);
        tool.addProperty("function.description", funcDesc);
        if (ContainerUtils.isNotEmptyArray(required)) {
            tool.addProperty("function.required", required);
        }
        return tool;
    }

    private void addParameter(ConfigurationMap toolCallConfig, String parameterName, String parameterType, String parameterDesc) {
        toolCallConfig.addProperty("function.parameters.type", "object");

        String paramTypeTemp = "function.parameters.properties.%s.type";
        String paramDescTemp = "function.parameters.properties.%s.description";


        toolCallConfig.addProperty(String.format(paramTypeTemp, parameterName), parameterType);
        toolCallConfig.addProperty(String.format(paramDescTemp, parameterName), parameterDesc);
    }

}
