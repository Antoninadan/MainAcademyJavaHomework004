package com.mainacad;

import com.mainacad.helper.UserConnectionHelper;
import com.mainacad.model.UserConnection;
import com.mainacad.multithreads.MultithreadWrapper;
import com.mainacad.service.FileService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.logging.Logger;

public class ApplicationRunner {
    public static final Logger LOGGER = Logger.getLogger(ApplicationRunner.class.getName());

    public static void main(String[] args) {
        List<UserConnection> userConnections = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            UserConnection userConnection = UserConnectionHelper.getRandomUserConnection();
            userConnections.add(userConnection);
        }
        FileService.writeCollectionToFile(userConnections, FileService.FILES_DIR, "file1.txt", false);
        FileService.writeObjectToFile(userConnections.get(0), FileService.FILES_DIR, "file_obj.txt");
//        LOGGER.info(((UserConnection)(FileService.readObjectFromFile(FileService.FILES_DIR, "file_obj.txt"))).toString());

        // test multithread
        List<Thread> threads = new ArrayList<>();
        Semaphore semaphore = new Semaphore(10);
        for (int i = 1; i <= 100; i++) {
            MultithreadWrapper multithreadWrapper = new MultithreadWrapper("thread " + i, userConnections.get(0), semaphore);
            threads.add(multithreadWrapper);
            multithreadWrapper.start();
        }
        semaphore.release();
    }
}
