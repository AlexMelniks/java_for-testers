package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;

public class ApplicationManager {
    protected WebDriver driver;
    private LoginHelper session;

    private GroupHelper groups;
    private ContactHelper adds;
    private Properties properties;

    public void init(String browser, Properties properties ) {
        this.properties = properties;
        if (driver == null) {
            if ("chrome".equals(browser)) {
                driver = new ChromeDriver();
            } else if ("firefox".equals(browser)) {
                driver = new FirefoxDriver();
            } else {
                throw new IllegalArgumentException(String.format("Unknown browser %s", browser));
            }
        }
        Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
        driver.get(properties.getProperty("web.baseUrl"));
        driver.manage().window().setSize(new Dimension(960, 759));
        session().Login(properties);
    }

    public ContactHelper adds() {
        if (adds == null) {
            adds = new ContactHelper(this);
        }
        return adds;
    }

    public LoginHelper session() {
        if (session == null) {
            session = new LoginHelper(this);
        }
        return session;
    }

    public GroupHelper groups() {
        if (groups == null) {
            groups = new GroupHelper(this);
        }
        return groups;
    }

    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return false;
        } catch (NoSuchElementException exception) {
            return true;
        }
    }

}
