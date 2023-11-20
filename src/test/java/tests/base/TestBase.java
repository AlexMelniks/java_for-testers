package tests.base;

import manager.ApplicationManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Random;

public class TestBase {
    public static ApplicationManager app;



    @BeforeEach
    public void setUp() throws IOException {
        var propeties = new Properties();
        propeties.load(new FileReader(System.getProperty("target", "local.properties")));
        if (app == null) {
            app = new ApplicationManager();
            app.init(System.getProperty("browser", "chrome"), propeties);
        }

    }


}
