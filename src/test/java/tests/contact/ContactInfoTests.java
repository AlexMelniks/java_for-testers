package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.base.TestBase;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    void testPhones() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData());
        }
        var contacts = app.hbm().getContactList();
        var phones = app.contacts().getPhones();
        for (var contact : contacts) {
            var expected = Stream.of(contact.home(), contact.mobile(), contact.work(), contact.phone2())
                    .filter(s -> s != null && !"".equals(s))
                    .collect(Collectors.joining("\n"));
            Assertions.assertEquals(expected, phones.get(contact.id()));
        }
    }

    @Test
    void testEmail() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData());
        }
        var contacts = app.hbm().getContactList();
        var email = app.contacts().getEmail();
        for (var contact : contacts) {
            var expected = Stream.of(contact.email(), contact.email2(), contact.email3())
                    .filter(s -> s != null && !"".equals(s))
                    .collect(Collectors.joining("\n"));
            Assertions.assertEquals(expected, email.get(contact.id()));
        }
    }

    @Test
    void testAddress() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData());
        }
        var contacts = app.hbm().getContactList();
        var address = app.contacts().getAddress();
        for (var contact : contacts) {
            var expected = Stream.of(contact.address())
                    .filter(s -> s != null && !"".equals(s))
                    .collect(Collectors.joining("\n"));
            Assertions.assertEquals(expected, address.get(contact.id()));
        }
    }
}

