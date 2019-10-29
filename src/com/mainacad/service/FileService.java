package com.mainacad.service;

import com.mainacad.model.UserConnection;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
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

    public static void writeObjectToFile(Object object, String path, String fileName) {
        checkTargetDir(path);
        try (FileOutputStream fileOutputStream = new FileOutputStream(path + SEP + fileName);
             ObjectOutputStream objectInputStream = new ObjectOutputStream(fileOutputStream)) {
            objectInputStream.writeObject(object);
            objectInputStream.flush();
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

    public static void moveFile(String sourcePath, String sourceFileName, String targetPath) {
        byte[] bytes = getBytesFromFile(sourcePath, sourceFileName);
        writeBytesToFile(bytes, targetPath, sourceFileName);
        deleteFile(sourcePath, sourceFileName);
    }

    public static void deleteFile(String path, String fileName) {
        File file = new File(path + SEP + fileName);
        file.delete();
    }

    public static <T> void writeCollectionToFile(Collection<T> collection, String path, String fileName, boolean append) {
        if (!append) {
            writeTextToFile("", path, fileName, false);
        }
        for (T each : collection) {
            FileService.writeTextToFile(each.toString() + "\n", path, fileName, true);
        }
    }

    public static List<UserConnection> readUserConnectionsFromFile(String path, String fileName) {
        List<UserConnection> userConnections = new ArrayList<>();
        try (FileReader fileReader = new FileReader(path + SEP + fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                UserConnection userConnection = new UserConnection();
                String words[] = line.split(" ");
                userConnection.setSessionId(Integer.valueOf(words[0]));
                userConnection.setConnectionTime(Long.valueOf(words[1]));
                userConnection.setUserIP(words[2]);
                userConnections.add(userConnection);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userConnections;
    }

    public static Object readObjectFromFile(String path, String fileName) {
        Object object = new Object();
        try (FileInputStream fileInputStream = new FileInputStream(path + SEP + fileName);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            object = objectInputStream.readObject();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        return object;
    }

}
