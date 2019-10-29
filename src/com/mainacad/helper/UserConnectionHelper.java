package com.mainacad.helper;

import com.mainacad.model.UserConnection;

import java.util.Date;
import java.util.Random;

public class UserConnectionHelper {
    public static UserConnection getRandomUserConnection(){
        UserConnection userConnection = new UserConnection();
        userConnection.setConnectionTime(new Date().getTime() - getRandomNumber(0, 299));
        userConnection.setSessionId(getRandomNumber(10000, 99999));
        userConnection.setUserIP(
                getRandomNumber(100, 255) + "."+
                        getRandomNumber(100, 255) + "."+
                        getRandomNumber(100, 255) + "."+
                        getRandomNumber(100, 255) + "."+
                        getRandomNumber(100, 255));
        return userConnection;
    }

    private static int getRandomNumber(int from, int to) {
        return from + new Random().nextInt(to - from);
    }
}
