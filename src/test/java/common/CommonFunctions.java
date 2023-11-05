package common;

import java.util.Random;

public class CommonFunctions {
    public static String randomString(int n) {
        var rnd = new Random();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            result.append((char) ('a' + rnd.nextInt(26)));
        }
        return result.toString();
    }
}

