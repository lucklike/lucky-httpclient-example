package io.github.lucklike.common.api.util;

import com.luckyframework.common.ConfigurationMap;
import com.luckyframework.common.ContainerUtils;
import com.luckyframework.common.StringUtils;
import com.luckyframework.httpclient.proxy.function.CommonFunctions;
import com.luckyframework.httpclient.proxy.function.SerializationFunctions;
import com.luckyframework.reflect.AnnotationUtils;
import com.luckyframework.reflect.ClassUtils;
import com.luckyframework.reflect.MethodUtils;
import io.github.lucklike.common.api.annotation.ToolParam;
import io.github.lucklike.common.api.annotation.Tool;
import org.springframework.core.ResolvableType;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class FunctionCallMange {

    private final Map<String, Function<String, String>> TOOL_CALL_MAP = new LinkedHashMap<>();
    private final List<ConfigurationMap> TOOL_CALL_CONFIGS = new ArrayList<>();

    private FunctionCallMange() {

    }

    public static FunctionCallMange create(@NonNull Object... tools) {
        FunctionCallMange mange = new FunctionCallMange();
        mange.addTools(tools);
        return mange;
    }

    public void addTools(@NonNull Object... tools) {
        for (Object tool : tools) {
            addTool(tool);
        }
    }

    public void addTool(@NonNull Object tool) {
        Assert.notNull(tool, "Tool cannot be null");
        Method[] allMethod = ClassUtils.getAllMethod(tool.getClass());
        for (Method method : allMethod) {
            Tool toolAnn = AnnotationUtils.findMergedAnnotation(method, Tool.class);
            if (toolAnn == null) {
                continue;
            }

            String toolName = StringUtils.hasText(toolAnn.name()) ? toolAnn.name() : method.getName();

            if (TOOL_CALL_MAP.containsKey(toolName)) {
                continue;
            }

            ConfigurationMap toolCallConfig = createToolCallConfig(
                    toolName,
                    toolAnn.desc(),
                    toolAnn.required()
            );

            Function<String, String> toolCall = null;
            int parameterCount = method.getParameterCount();
            if (parameterCount == 1) {
                Class<?> type = method.getParameters()[0].getType();
                Field[] fields = ClassUtils.getAllFields(type);
                for (Field field : fields) {
                    ToolParam toolParamAnn = AnnotationUtils.findMergedAnnotation(field, ToolParam.class);
                    if (toolParamAnn != null) {
                        String paramName = field.getName();
                        String paramDesc = toolParamAnn.desc();
                        String paramType = StringUtils.hasText(toolParamAnn.type())
                                ? toolParamAnn.type()
                                : field.getType().getSimpleName().toLowerCase();
                        addParameter(toolCallConfig, paramName, paramType, paramDesc);
                    }
                }

                toolCall = args -> {
                    try {
                        Object paramObj = SerializationFunctions._json(args, ResolvableType.forMethodParameter(method, 0));
                        return SerializationFunctions.json(MethodUtils.invoke(tool, method, paramObj));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                };
            } else if (parameterCount == 0) {
                toolCall = args -> {
                    try {
                        return SerializationFunctions.json(MethodUtils.invoke(tool, method));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                };
            }

            if (toolCall != null) {
                TOOL_CALL_CONFIGS.add(toolCallConfig);
                TOOL_CALL_MAP.put(toolName, toolCall);
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
