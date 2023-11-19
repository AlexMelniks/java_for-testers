package manager;

import model.ContactData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {
    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createContactNew(ContactData contact) {
        openContactNewPage();
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }

    public void removeAdd(ContactData contact) {
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
        modifyContactForm(modifiedAdd);
        submitContactModification();
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

    public void openContactNewPage() {
        if (manager.isElementPresent(By.name("new"))) {
            click(By.linkText("add new"));
        }
    }
    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstName());
        type(By.name("lastname"), contact.lastName());
        attach(By.name("photo"), contact.photo());
    }
    private void modifyContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstName());
        type(By.name("lastname"), contact.lastName());
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }


    public void openHomePage() {
            click(By.linkText("home"));
    }
    private void selectContact(ContactData add) {
        click(By.cssSelector(String.format("input[value='%s']", add.id())));
    }

    private void removeSelectedContact() {
        click(By.cssSelector(".left:nth-child(8) > input"));
        manager.driver.switchTo().alert().accept();
    }

    public int getCountContact() {
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
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

}



