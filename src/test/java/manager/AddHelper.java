package manager;

import model.AddData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class AddHelper extends HelperBase {
    public AddHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createAddNew(AddData add) {
        openAddNewPage();
        fillAddForm(add);
        submitAddCreation();
    }

    public void removeAdd(AddData add) {
        openHomePage();
        selectAdd(add);
        removeSelectedAdd();
    }

    public void removeAllAdd() {
        openHomePage();
        selectAllAdd();
        removeSelectedAdd();
    }
    public void openAddNewPage() {
        if (manager.isElementPresent(By.name("new"))) {
            click(By.linkText("add new"));
        }
    }
    private void fillAddForm(AddData add) {
        type(By.name("firstname"), add.firstName());
        type(By.name("lastname"), add.lastName());
    }

    private void submitAddCreation() {
        click(By.name("submit"));
    }


    public void openHomePage() {
        if (manager.isElementPresent(By.name("new"))) {
            click(By.linkText("home"));
        }
    }
    private void selectAdd(AddData add) {
        click(By.cssSelector(String.format("input[value='%s']", add.id())));
    }

    private void removeSelectedAdd() {
        click(By.cssSelector(".left:nth-child(8) > input"));
        manager.driver.switchTo().alert().accept();
    }

    public int getCountAdd() {
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    private void selectAllAdd() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }
    public List<AddData> getList() {
        openHomePage();
        var adds = new ArrayList<AddData>();
        var trs = manager.driver.findElements(By.name("entry"));
        for (var tr : trs) {
            var checkbox = tr.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("id");
            var last = tr.findElement(By.cssSelector("tr>td:nth-of-type(2)"));
            var lastName = last.getText();
            var first = tr.findElement(By.cssSelector("tr>td:nth-of-type(3)"));
            var firstName = first.getText();
            adds.add(new AddData().withId(id).withLastName(String.valueOf(lastName)).withFirstName(String.valueOf(firstName)));
        }
        return adds;
    }
}



