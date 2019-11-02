package com.mainacad.multithreads;

import com.mainacad.model.UserConnection;
import com.mainacad.service.FileService;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.logging.Logger;

public class MultithreadWrapper extends Thread{
    private static final Logger LOGGER = Logger.getLogger(MultithreadWrapper.class.getName());
    private static final String FILE_NAME_MULTI = "file_multi.txt";
    private final UserConnection userConnection;
    private final String threadName;
    private Semaphore semaphore;


    public MultithreadWrapper(String threadName, UserConnection userConnection, Semaphore semaphore) {
        this.threadName = threadName;
        this.userConnection = userConnection;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            LOGGER.info(threadName + " was started");
            FileService.writeTextToFile(userConnection.toString(), FileService.FILES_DIR, FILE_NAME_MULTI, true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
