package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.base.TestBase;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    void testPhones (){
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData());
        }
        var contacts = app.hbm().getContactList();
        var phones = app.contacts().getPhones();
        for (var contact : contacts){
            var expected = Stream.of(contact.home(), contact.mobile(), contact.work(), contact.phone2())
                    .filter(s->s != null && !"".equals(s))
                    .collect(Collectors.joining("\n"));
            Assertions.assertEquals(expected, phones.get(contact.id()));

        }

    }
}

