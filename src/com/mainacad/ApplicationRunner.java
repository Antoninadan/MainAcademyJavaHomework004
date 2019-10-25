package com.mainacad;

import com.mainacad.model.UserConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ApplicationRunner {
    public static final Logger LOGGER = Logger.getLogger(ApplicationRunner.class.getName());
    public static void main(String[] args) {

//        String str = "dfhf bvthf dfhfh";
//        FileService.writeTextToFile(str, "file1.txt");
//        String readedString = FileService.readTextFromFile("file1.txt");
//        LOGGER.info(readedString);

        List<UserConnection> userConnections = new ArrayList<>();
        for (int i = 0; i <=5 ; i++) {
            userConnections.add(new UserConnection());
        }

        System.out.println("userConnections.toString() = " + userConnections.toString());
        
    }
}
