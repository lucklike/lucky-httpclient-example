package io.github.lucklike.luckyclient.api.mainfun;

import java.io.File;
import java.util.Objects;
import java.util.stream.Stream;

public class FileListMain {

    public static void main(String[] args) {
        String p1 = "C:\\Users\\18143\\Desktop\\21\\cpe-products-component-backend\\BOOT-INF\\lib";
        String p2 = "C:\\Users\\18143\\Desktop\\10\\cpe-products-component-backend\\BOOT-INF\\lib";

        printFileList(p2);
    }


    private static void printFileList(String path) {
        Stream.of(Objects.requireNonNull(new File(path).listFiles())).map(File::getName).forEach(System.out::println);
    }

}
