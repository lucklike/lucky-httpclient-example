package io.github.lucklike.luckyclient.api.mainfun;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileListMain {

    public static void main(String[] args) {
        String p1 = "D:\\cairh\\jar\\old\\cpe-esb-component-server(1)\\BOOT-INF\\lib";
        String p2 = "D:\\cairh\\jar\\new\\cpe-products-basedata-task\\BOOT-INF\\lib";

        compare(fileList(p1), fileList(p2));
    }

    private static List<String> fileList(String path) {
        return Stream.of(Objects.requireNonNull(new File(path).listFiles())).map(File::getName).sorted(String::compareTo).collect(Collectors.toList());
    }
    
    private static void compare(List<String> oldJarFiles, List<String> newJarFiles) {
        Map<String, String> oldMap = listToMap(oldJarFiles);
        Map<String, String> newMap = listToMap(newJarFiles);


        Set<String> oldKeys = new HashSet<>(oldMap.keySet());
        Set<String> newKeys = new HashSet<>(newMap.keySet());

        oldKeys.retainAll(newKeys);

        List<String> oldList = new ArrayList<>();
        List<String> newList = new ArrayList<>();
        for (String key : oldKeys) {
            oldList.add(oldMap.get(key));
            newList.add(newMap.get(key));
        }

        oldList.forEach(System.out::println);

        System.out.println("-----------------------------------");

        newList.forEach(System.out::println);


    }
    
    private static Map<String, String> listToMap(List<String> jarFiles) {
        Map<String, String> map = new HashMap<>();
        for (String jarFile : jarFiles) {
            int i = jarFile.lastIndexOf("-");
            if (i != -1) {
                map.put(jarFile.substring(0, i), jarFile);
            } else {
                map.put(jarFile, jarFile);
            }
        }
        return map;
    }


    private static void printFileList(String path) {
        Stream.of(Objects.requireNonNull(new File(path).listFiles())).map(File::getName).sorted(String::compareTo).forEach(System.out::println);
    }

}
