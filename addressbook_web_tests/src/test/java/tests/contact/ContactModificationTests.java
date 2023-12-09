package tests.contact;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.base.TestBase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationTests extends TestBase {
    @Test
    void canModifyAdd(){
        if (app.contacts().getCountContact() == 0) {
            app.contacts().createContactNew(new ContactData());
        }
        var oldAdds = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldAdds.size());
        var testData = new ContactData().withFirstName("modify name");
        app.contacts().modifyContact(oldAdds.get(index), testData);
        var newAdds = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldAdds);
        expectedList.set(index, testData.withId(oldAdds.get(index).id()));
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newAdds.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newAdds, expectedList);
    }
    @Test
    void canAddContactToGroup() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData());
        }
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData());
        }
        var group = app.hbm().getGroupList().get(0);
        var contact = app.hbm().getContactList().get(0);
        if (app.hbm().getCountContactInGroup(group, contact)) {
            app.contacts().removeContactFromGroup(contact, group);
        }
        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().addContactToGroup(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newRelated.sort(compareById);
        var expectedList = new ArrayList<>(oldRelated);
        expectedList.add(contact);
        expectedList.sort(compareById);
        Assertions.assertEquals(expectedList, newRelated);
    }
}



