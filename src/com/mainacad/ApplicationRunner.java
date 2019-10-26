package com.mainacad;

import com.mainacad.model.UserConnection;
import com.mainacad.service.FileService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class ApplicationRunner {
    public static final Logger LOGGER = Logger.getLogger(ApplicationRunner.class.getName());

    public static void main(String[] args) {
        List<UserConnection> userConnections = createTestListConnection();
        FileService.writeCollectionToFile(userConnections, FileService.FILES_DIR, "file1.txt", false);
        FileService.moveFile(FileService.FILES_DIR, "file1.txt", FileService.FILES_DIR + FileService.SEP + "movefile");
    }

    public static List<UserConnection> createTestListConnection() {
        List<UserConnection> userConnections = new ArrayList<>();
        Date date = new Date();
        for (int i = 1; i <= 5; i++) {
            userConnections.add(new UserConnection(getRandomIP(), getRandomSessionId(), date.getTime()));
        }
        return userConnections;
    }

    public static String getRandomIP() {
        String result = "";
        int rangeFrom = 100;
        int rangeTo = 155;
        for (int i = 1; i <= 4; i++) {
            result += Integer.toString(rangeFrom + (int) (Math.random() * rangeTo));
            if (i != 4) {
                result += ".";
            }
        }
        return result;
    }

    public static String getRandomSessionId() {
        int rangeFrom = 10000;
        int rangeTo = 89999;
        return Integer.toString(rangeFrom + (int) (Math.random() * rangeTo));
    }


}
