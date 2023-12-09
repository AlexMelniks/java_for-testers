package tests.contact;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.base.TestBase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactRemovalTests extends TestBase {

    @Test
    public void canRemoveAdd() throws InterruptedException {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData());
        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        Thread.sleep(1000);
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(expectedList, newContacts);
    }

    @Test
    public void canRemoveAllAddAtOnce() {
        if (app.contacts().getCountContact() == 0) {
            app.contacts().createContactNew(new ContactData().withFirstName("Test"));
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0, app.contacts().getCountContact());
    }

    @Test
    void canRemoveContactFromGroup() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData());
        }
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData());
        }
        var group = app.hbm().getGroupList().get(0);
        var contact = app.hbm().getContactList().get(0);
        if (!app.hbm().getCountContactInGroup(group, contact)) {
            app.contacts().addContactToGroup(contact, group);
        }
        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().removeContactFromGroup(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);

        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        var expectedList = new ArrayList<>(oldRelated);
        expectedList.remove(0);
        expectedList.sort(compareById);
        if (!newRelated.isEmpty()) {
            newRelated.sort(compareById);
            Assertions.assertEquals(expectedList, newRelated);
        }
    }
}
