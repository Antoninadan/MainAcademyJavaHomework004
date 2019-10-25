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
    public static void writeTextToFile(String text, String fileName, boolean append) {
        checkTargetDir();
        try (FileWriter fileWriter = new FileWriter(FILES_DIR + SEP + fileName, append)) {
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

    private static void checkTargetDir() {
        File file = new File(FILES_DIR);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    public static String readTextFromFile(String fileName) {
        String out = "";
        try (FileReader fileReader = new FileReader(FILES_DIR + SEP + fileName);
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
    public static void writeBytesToFile(byte[] bytes, String fileName) {
        checkTargetDir();
        try (FileOutputStream fileOutputStream = new FileOutputStream(FILES_DIR + SEP + fileName)) {
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] getBytesFromFile(String fileName) {
        File file = new File(FILES_DIR + SEP + fileName);
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public static void copyFile(String sourceFileName, String targetFileName) {
        byte[] bytes = getBytesFromFile(sourceFileName);
        writeBytesToFile(bytes, targetFileName);
    }

//    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//    public static void moveFile(String sourceFileName, String sourcePath, String targetPath) {
//        byte[] bytes = getBytesFromFile(sourceFileName);
//        writeBytesToFile(bytes, targetFileName);
//    }

    public static <T> void writeListToFile(Collection<T> collection, String fileName, boolean append) {
        if (!append) {
            writeTextToFile("", fileName, false);
        }
        for (T each : collection) {
            FileService.writeTextToFile(each.toString() + "\n", fileName, true);
        }
    }

}
