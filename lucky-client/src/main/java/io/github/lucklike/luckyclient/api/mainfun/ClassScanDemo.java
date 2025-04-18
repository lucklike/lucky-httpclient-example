package io.github.lucklike.luckyclient.api.mainfun;

import com.baomidou.mybatisplus.annotation.TableName;
import com.luckyframework.common.ScanUtils;
import com.luckyframework.common.Table;
import com.luckyframework.reflect.AnnotationUtils;

import java.io.File;
import java.lang.annotation.AnnotationFormatError;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ClassScanDemo {

    static String prefix = "\\build\\classes\\java\\main\\";

    public static void main(String[] args) throws Exception {
        // D:\CaiRenHuiProjects\temp\cpe-esb-basedata
        // D:\CaiRenHuiProjects\cpe-esb-archive\
//        File file = new File("D:\\CaiRenHuiProjects\\cpe-esb-component\\");
        File file = new File("D:\\CaiRenHuiProjects\\cpe-esb-archive\\");

        List<File> classesFiles = getClassesFiles(file);

        Table table = new Table();
        table.styleThree();
        table.addHeader("TableName", "ClassName");

        Set<String> tableNames = new LinkedHashSet<>();
        for (File classesFile : classesFiles) {
            List<Class<?>> classes = loadClass(classesFile);
            for (Class<?> clazz : classes) {
                try {
                    TableName tableNameAnn = AnnotationUtils.findMergedAnnotation(clazz, TableName.class);
                    if (tableNameAnn != null) {
                        String tableName = tableNameAnn.value();
                        tableNames.add(tableName.toLowerCase());
                        table.addDataRow(tableName.toLowerCase(), clazz.getName());
                    }
                } catch (Throwable err) {
                    // ignore
                }
            }
        }


        tableNames.forEach(System.out::println);
        System.out.println("共发现表："+tableNames.size());

        System.out.println(table.format());
    }


    private static List<File> getClassesFiles(File dir) {
        List<File> classFiles = new ArrayList<>();
        if (dir.isDirectory()) {
            if (dir.getAbsolutePath().endsWith(prefix.substring(0, prefix.length() - 1))) {
                classFiles.add(dir);
            } else {
                for (File file : dir.listFiles()) {
                    classFiles.addAll(getClassesFiles(file));
                }
            }
            return classFiles;
        } else {
            return classFiles;
        }
    }


    private static List<Class<?>> loadClass(File file) throws Exception {
        List<Class<?>> classes = new ArrayList<>();
        @SuppressWarnings("resource") ClassLoader classLoader = new URLClassLoader(new URL[]{file.toURI().toURL()});
        for (String className : getClassNames(file)) {
            try {
                Class<?> clazz = classLoader.loadClass(className);
                if (clazz != null) {
                    classes.add(clazz);
                }
            } catch (NoClassDefFoundError error) {
                // ignore
            }
        }
        return classes;
    }

    private static List<String> getClassNames(File file) throws Exception {
        List<String> classNameList = new ArrayList<>();
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                classNameList.addAll(getClassNames(f));
            }
        } else if (file.isFile()) {
            if (file.getName().endsWith(".class")) {
                classNameList.add(getClassName(file));
            }
        }
        return classNameList;
    }

    private static String getClassName(File file) throws Exception {

        String className = file.getAbsolutePath();
        className = className.replace(File.separatorChar, '.');
        className = className.substring(0, className.length() - 6);

        return className.substring(className.indexOf(prefix.replace(File.separator, ".")) + prefix.length());
    }
}
