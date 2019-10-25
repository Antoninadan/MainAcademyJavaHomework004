package com.mainacad.service;

import java.io.*;
import java.nio.file.Files;
import java.util.Collection;
import java.util.List;

public class FileService {
    public static final String MAIN_DIR = System.getProperty("user.dir");
    public static final String SEP = System.getProperty("file.separator");
    public static final String FILES_DIR = MAIN_DIR + SEP + "files";

    // work with text
    public static void writeTextToFile(String text, String path, String fileName, boolean append) {
        checkTargetDir(path);
        try (FileWriter fileWriter = new FileWriter(path + SEP + fileName, append)) {
            if (append) {
                BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
                bufferWriter.write(text);
                bufferWriter.close();
            } else {
                fileWriter.write(text);
                fileWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void checkTargetDir(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    public static String readTextFromFile(String path, String fileName) {
        String out = "";
        try (FileReader fileReader = new FileReader(path + SEP + fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                out += line + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }

    // work with bytes
    public static void writeBytesToFile(byte[] bytes, String path, String fileName) {
        checkTargetDir(path);
        try (FileOutputStream fileOutputStream = new FileOutputStream(path + SEP + fileName)) {
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] getBytesFromFile(String path, String fileName) {
        File file = new File(path + SEP + fileName);
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public static void copyFile(String sourcePath, String sourceFileName, String targetPath, String targetFileName) {
        byte[] bytes = getBytesFromFile(sourcePath, sourceFileName);
        writeBytesToFile(bytes, targetPath, targetFileName);
    }

//    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public static void moveFile(String sourcePath, String sourceFileName, String targetPath) {
        byte[] bytes = getBytesFromFile(sourcePath, sourceFileName);
        deleteFile(path, fileName);
        writeBytesToFile(bytes, targetFileName);
    }

    public static void deleteFile(String path, String fileName){

    }

    public static <T> void writeListToFile(Collection<T> collection, String fileName, boolean append) {
        if (!append) {
            writeTextToFile("", fileName, false);
        }
        for (T each : collection) {
            FileService.writeTextToFile(each.toString() + "\n", fileName, true);
        }
    }

}
