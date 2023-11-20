package tests.contact;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tests.base.TestBase;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {
    @ParameterizedTest
    @MethodSource("singleRandomContactProvider")
    public void canCreateMultipleAdds(ContactData contact) {
        var oldContacts = app.hbm().getContactList();
        app.contacts().createContactNew(contact);
        var newContacts = app.hbm().getContactList();
        Comparator<ContactData> compareById = Comparator.comparingInt(o -> Integer.parseInt(o.id()));
        newContacts.sort(compareById);
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newContacts.get(newContacts.size() - 1).id()).withPhoto(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);

    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void canNotCreateAdd(ContactData add) {
        var oldAdds = app.contacts().getListContact();
        app.contacts().createContactNew(add);
        var newAdds = app.contacts().getListContact();
        Assertions.assertEquals(newAdds, oldAdds);

    }

     /*@ParameterizedTest
    @MethodSource("contactProvider")

    public void contactCreationInGroupTests(ContactData contact) {
        var oldContacts = app.contacts().getListContact();
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var group = app.hbm().getGroupList().get(0);
        var oldRelated = app.hbm().getContactsInGroup(group);
        //app.contacts().createContactNew(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());

        var newContacts = app.contacts().getListContact();

        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);

        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newContacts.get(newContacts.size() - 1).id()));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    } */

    public static List<ContactData> singleRandomContactProvider() throws IOException {
        return List.of(new ContactData()
                .withFirstName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10))
                .withPhoto(CommonFunctions.randomFile("src/test/resources/images")));

    }

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
        for (var firstName : List.of("","Test")) {
            for (var lastName : List.of("","Test")) {
                result.add(new ContactData()
                        .withFirstName(firstName)
                        .withLastName(lastName)
                        .withPhoto(CommonFunctions.randomFile("src/test/resources/images")));
            }

        }
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(new File("contacts.json"), new TypeReference<List<ContactData>>() {
        });
        result.addAll(value);
        return result;
    }

    public static List<ContactData> negativeContactProvider() {
        return new ArrayList<>(List.of(new ContactData("", "Test'", "",(CommonFunctions.randomFile("src/test/resources/images")))));
    }

}
