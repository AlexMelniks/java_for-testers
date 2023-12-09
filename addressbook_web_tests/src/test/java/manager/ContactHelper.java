package manager;

import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {
    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createContactNew(ContactData contact) {
        openAddNewPage();
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }

    public void createContactInGroup(ContactData contact, GroupData group) {
        openAddNewPage();
        fillContactForm(contact);
        selectGroup(group);
        submitContactCreation();
        returnToHomePage();
    }

    public void addContactInGroup(ContactData contact, GroupData group) {
        openHomePage();
        selectContact(contact);
        addInGroup(group);
        openHomePage();
    }

    public void removeContact(ContactData contact) {
        openHomePage();
        selectContact(contact);
        removeSelectedContact();
    }

    public void removeAllContacts() {
        openHomePage();
        selectAllContact();
        removeSelectedContact();
    }
    public void modifyContact(ContactData contact, ContactData modifiedAdd) {
        openHomePage();
        selectContact(contact);
        initContactModification(contact);
        fillContactForm(modifiedAdd);
        submitContactModification();
    }
    public void removeContactFromGroup(ContactData contact, GroupData group) {
        openHomePage();
        selectGroupToDisplayСontacts(group);
        selectContact(contact);
        removeFromGroup();
    }
    public void addContactToGroup(ContactData contact, GroupData group) {
        openHomePage();
        noneGroup();
        selectContact(contact);
        selectAddToGroup(group);
    }
    private void selectAddToGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());
        click(By.name("add"));
    }

    private void removeFromGroup() {
        click(By.name("remove"));
        click(By.linkText("group page \"Test\""));
    }

    private void selectGroupToDisplayСontacts(GroupData group) {
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());
    }
    private void addInGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());
        click(By.name("add"));
    }

    private void returnToHomePage() {
        click(By.linkText("home page"));
    }

    private void submitContactModification() {
        click(By.name("update"));
    }

    private void initContactModification(ContactData contact) {
        click(By.cssSelector(String.format("a[href='edit.php?id=%s']", contact.id())));

    }

    public void openAddNewPage() {
        if (manager.isElementPresent(By.name("new"))) {
            click(By.linkText("add new"));
        }
    }
    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstName());
        type(By.name("lastname"), contact.lastName());
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }


    public void openHomePage() {
            click(By.linkText("home"));
    }
    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
    }
    public void refreshPage() {
        manager.driver.navigate().refresh();
    }
    private void removeSelectedContact() {
        click(By.cssSelector(".left:nth-child(8) > input"));
        manager.driver.switchTo().alert().accept();
    }

    public int getCountContact() {
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public int getCountContactInGroup() {
        openHomePage();
        noneGroup();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    private void noneGroup() {
        click(By.name("group"));
        {
            WebElement dropdown = manager.driver.findElement(By.name("group"));
            dropdown.findElement(By.xpath("//option[. = '[none]']")).click();
        }
    }


    private void selectAllContact() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }

    public List<ContactData> getListContact() {
        openHomePage();
        var contacts = new ArrayList<ContactData>();
        var rows = manager.driver.findElements(By.xpath("//tr"));
        rows.remove(0);
        for (var row : rows) {
            var id = row.findElement(By.name("selected[]")).getAttribute("value");
            var last = row.findElement(By.cssSelector("tr>td:nth-of-type(2)"));
            var lastName = last.getText();
            var first = row.findElement(By.cssSelector("tr>td:nth-of-type(3)"));
            var firstName = first.getText();
            contacts.add(new ContactData().withId(id).withLastName(lastName).withFirstName(firstName));
        }
        return contacts;
    }
    public List<ContactData> getListContactInGroup() {
        openHomePage();
        noneGroup();
        var contacts = new ArrayList<ContactData>();
        var rows = manager.driver.findElements(By.xpath("//tr"));
        rows.remove(0);
        for (var row : rows) {
            var id = row.findElement(By.name("selected[]")).getAttribute("value");
            var last = row.findElement(By.cssSelector("tr>td:nth-of-type(2)"));
            var lastName = last.getText();
            var first = row.findElement(By.cssSelector("tr>td:nth-of-type(3)"));
            var firstName = first.getText();
            contacts.add(new ContactData().withId(id).withLastName(lastName).withFirstName(firstName));
        }
        return contacts;
    }

    public ArrayList<String> getContactInfo(ContactData contact) {
        var result = new ArrayList<String>();
         WebElement row = manager.driver.findElement(By.xpath(String.format("//input[@id='%s']/../..", contact.id())));
            result.add(row.findElements(By.tagName("td")).get(3).getText());
            result.add(row.findElements(By.tagName("td")).get(4).getText());
            result.add(row.findElements(By.tagName("td")).get(5).getText());

        return result;
    }

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

}



