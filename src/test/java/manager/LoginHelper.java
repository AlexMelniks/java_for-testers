package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class LoginHelper extends HelperBase {

    public LoginHelper(ApplicationManager manager) {
        super(manager);
    }

    void Login() {
        type(By.name("user"), "admin");
        type(By.name("pass"), "secret");
        click(By.cssSelector("input:nth-child(7)"));
    }

    public void openAddNew() {
        if (manager.isElementPresent(By.name("new"))) {
            click(By.linkText("add new"));
        }
    }
}
