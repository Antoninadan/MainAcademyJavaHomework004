package com.mainacad.service;

import com.mainacad.helper.UserConnectionHelper;
import com.mainacad.model.UserConnection;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class FileServiceTest {

    private static final String PATH = FileService.FILES_DIR;
    private static final String SEP = FileService.SEP;
    private static final String TEXT_FILE_NAME = "test_text_file.txt";
    private static final String BYTES_FILE_NAME = "test_bytes_file.obj";
    private static final String OBJECT_FILE_NAME = "test_bytes_file.obj";

    @org.junit.jupiter.api.BeforeAll
    void setUp() {
        FileService.writeTextToFile("", PATH, TEXT_FILE_NAME, false);
        FileService.writeBytesToFile(new byte[0], PATH, BYTES_FILE_NAME);
        FileService.writeBytesToFile(new byte[0], PATH, OBJECT_FILE_NAME);
//        FileService.writeObjectToFile(testUserConnection, PATH, OBJECT_FILE_NAME);
    }

    @org.junit.jupiter.api.AfterAll
    void tearDown() {
        File testTextFile = new File(PATH + SEP + TEXT_FILE_NAME);
        testTextFile.delete();

        File testBytesFile = new File(PATH + SEP + BYTES_FILE_NAME);
        testBytesFile.delete();

        File testObjectFile = new File(PATH + SEP + OBJECT_FILE_NAME);
        testObjectFile.delete();
    }

    @org.junit.jupiter.api.Test
    void testReadObjectFromFile() {
        UserConnection testUserConnection = UserConnectionHelper.getRandomUserConnection();
        FileService.writeTextToFile(testUserConnection.toString(), PATH, TEXT_FILE_NAME, false);

        UserConnection readObject = (UserConnection) (FileService.readObjectFromFile(PATH, OBJECT_FILE_NAME));
        assertEquals(testUserConnection.getConnectionTime(), readObject.getConnectionTime());
        assertEquals(testUserConnection.getSessionId(), readObject.getSessionId());
        assertEquals(testUserConnection.getUserIP(), readObject.getUserIP());
    }
}