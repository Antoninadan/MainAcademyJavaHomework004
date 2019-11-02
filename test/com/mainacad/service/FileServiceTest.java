package com.mainacad.service;

import com.mainacad.helper.UserConnectionHelper;
import com.mainacad.model.UserConnection;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class FileServiceTest {
    private static final String PATH = FileService.FILES_DIR;
    private static final String SEP = FileService.SEP;
    private static final String TEXT_FILE_NAME = "test_text_file.txt";
    private static final String BYTES_FILE_NAME = "test_bytes_file.obj";
    private static final String OBJECT_FILE_NAME = "test_object_file.obj";
    private static UserConnection userConnection;

    @org.junit.jupiter.api.BeforeAll
    void setUpBeforeAll() {
        byte[] testBytes = FileService.getBytesFromFile(PATH, "cat.jpg");
        FileService.writeBytesToFile(testBytes, PATH, BYTES_FILE_NAME);

        userConnection = UserConnectionHelper.getRandomUserConnection();
        FileService.writeObjectToFile(userConnection, PATH, OBJECT_FILE_NAME);
    }

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        FileService.writeTextToFile("", PATH, TEXT_FILE_NAME, false);
        UserConnection testUserConnection = UserConnectionHelper.getRandomUserConnection();
        FileService.writeTextToFile(testUserConnection.toString(), PATH, TEXT_FILE_NAME, false);
        FileService.writeObjectToFile(testUserConnection, PATH, OBJECT_FILE_NAME);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        File testTextFile = new File(PATH + SEP + TEXT_FILE_NAME);
        testTextFile.delete();
    }

    @org.junit.jupiter.api.AfterAll
    void tearDownAfterAll() {
        File testBytesFile = new File(PATH + SEP + BYTES_FILE_NAME);
        testBytesFile.delete();
        File testObjectFile = new File(PATH + SEP + OBJECT_FILE_NAME);
        testObjectFile.delete();
    }

    @Test
    void testReadTextFromFile() {
        String testText = FileService.readTextFromFile(PATH, TEXT_FILE_NAME);
        assertNotNull(testText);

        assertTrue(testText.contains(" "));
        assertTrue(testText.length() > 22);
    }

    @Test
    void getBytesFromFile() {
        byte[] testBytes = FileService.getBytesFromFile(PATH, BYTES_FILE_NAME);
        assertNotNull(testBytes);
        assertTrue(testBytes.length > 0);
    }

    @org.junit.jupiter.api.Test
    void testReadUserConnectionFromFile() {
        List<UserConnection> readObject = FileService.readUserConnectionsFromFile(PATH, OBJECT_FILE_NAME);
        assertNotNull(readObject);
        assertNotNull(readObject.get(0).getConnectionTime());
        assertNotNull(readObject.get(0).getUserIP());
        assertNotNull(readObject.get(0).getSessionId());
        assertTrue(readObject.get(0).getSessionId() > 10000 &&
                readObject.get(0).getSessionId() < 99999);
    }

    @org.junit.jupiter.api.Test
    void testReadObjectFromFile() {
        UserConnection testUserConnection = (UserConnection) (FileService.readObjectFromFile(PATH, OBJECT_FILE_NAME));
        assertNotNull(testUserConnection);
        assertEquals(userConnection.getConnectionTime(), testUserConnection.getConnectionTime());
    }
}