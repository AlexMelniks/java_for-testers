package tests;

import manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;

import java.util.Random;

public class TestBase {
    protected static ApplicationManager app;


    @BeforeEach
    public void setUp() {
        if (app == null) {
            app = new ApplicationManager();
            app.init(System.getProperty("browser", "chrome"));
        }

    }
    public static String randomString(int n) {
        var rnd = new Random();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++){
            result.append((char) ('a' + rnd.nextInt(26)));
        }
        return result.toString();
    }
}
