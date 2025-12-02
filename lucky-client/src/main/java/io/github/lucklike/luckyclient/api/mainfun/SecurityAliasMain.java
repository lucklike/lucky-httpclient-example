package io.github.lucklike.luckyclient.api.mainfun;

import com.luckyframework.httpclient.proxy.function.CommonFunctions;
import com.luckyframework.httpclient.proxy.function.SerializationFunctions;
import com.luckyframework.serializable.SerializationTypeToken;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class SecurityAliasMain {

    public static void main(String[] args) throws Exception {
        String temp = FileCopyUtils.copyToString(new InputStreamReader(Files.newInputStream(Paths.get("D:\\test\\SecurityAlias.txt")), StandardCharsets.UTF_8));

        List<Map<String, String>> list = SerializationFunctions._json(new File("D:\\test\\SecurityAlias.json"), new SerializationTypeToken<List<Map<String, String>>>() {
        });

        StringBuilder ssb = new StringBuilder();
        for (Map<String, String> map : list) {
            String label = map.get("label");
            String value = map.get("value");

            String content = String.format(temp, label, StringUtils.capitalize(value), value.toUpperCase());
            ssb.append(content);
        }

        System.out.println(ssb);
    }
}
