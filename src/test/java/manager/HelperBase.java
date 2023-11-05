package manager;

import org.openqa.selenium.By;

import java.nio.file.Paths;

public class HelperBase {
    protected ApplicationManager manager;

    public HelperBase(ApplicationManager manager) {
        this.manager = manager;
    }

    protected void click(By Locator) {
        manager.driver.findElement(Locator).click();
    }

    protected void type(By Locator, String text) {
        click(Locator);
        manager.driver.findElement(Locator).clear();
        manager.driver.findElement(Locator).sendKeys(text);
    }
    protected void attach(By Locator, String file) {
        manager.driver.findElement(Locator).sendKeys(Paths.get(file).toAbsolutePath().toString());
    }
}
