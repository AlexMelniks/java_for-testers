package manager;

import org.openqa.selenium.By;

import java.util.Properties;

public class LoginHelper extends HelperBase {



    public LoginHelper(ApplicationManager manager) {
        super(manager);
    }

    void Login(Properties properties) {
        type(By.name("user"), properties.getProperty("web.username"));
        type(By.name("pass"), properties.getProperty("web.password"));
        click(By.cssSelector("input:nth-child(7)"));
    }

    public void openAddNew() {
        if (manager.isElementPresent(By.name("new"))) {
            click(By.linkText("add new"));
        }
    }
}
