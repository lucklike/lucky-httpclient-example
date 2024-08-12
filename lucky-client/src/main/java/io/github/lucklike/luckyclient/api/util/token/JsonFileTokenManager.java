package io.github.lucklike.luckyclient.api.util.token;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.InputStreamReader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static com.luckyframework.httpclient.core.serialization.SerializationConstant.JSON_SCHEME;

@Slf4j
public abstract class JsonFileTokenManager<T> extends TokenManager<T> {

    /**
     * 获取存储Token数据的JSON文件路径
     *
     * @return 存储Token数据的JSON文件路径
     */
    protected abstract String getJsonFilePath();

    /**
     * 初始化Token
     *
     * @return Token数据
     */
    protected abstract T initToken();

    @Override
    @SuppressWarnings("unchecked")
    protected final T loadToken() {
        try {
            File file = ResourceUtils.getFile(getJsonFilePath());
            if (file.exists()) {
                T token = (T) JSON_SCHEME.deserialization(new InputStreamReader(Files.newInputStream(file.toPath()), StandardCharsets.UTF_8), getTokenClass());
                if (!isExpires(token)) {
                    return token;
                }
            }
            T token = initToken();
            saveToken(token);
            return token;
        } catch (Exception e) {
            log.error("Failed to load token");
            return null;
        }
    }

    @Override
    protected final void saveToken(T token) {
        try {
            File file = ResourceUtils.getFile(getJsonFilePath());
            Writer writer = Files.newBufferedWriter(file.toPath(), StandardCharsets.UTF_8);
            FileCopyUtils.copy(JSON_SCHEME.serialization(token), writer);
        } catch (Exception e) {
            log.error("Failed to save token");
        }
    }

}
