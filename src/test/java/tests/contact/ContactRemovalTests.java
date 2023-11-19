package tests.contact;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.base.TestBase;

import java.util.ArrayList;
import java.util.Random;

public class ContactRemovalTests extends TestBase {

    @Test
    public void canRemoveAdd() {
        if (app.contacts().getCountContact() == 0) {
            app.contacts().createContactNew(new ContactData());
        }
        var oldAdds = app.contacts().getListContact();
        var rnd = new Random();
        var index = rnd.nextInt(oldAdds.size());
        app.contacts().removeAdd(oldAdds.get(index));
        var newAdds = app.contacts().getListContact();
        var expectedList = new ArrayList<>(oldAdds);
        expectedList.remove(index);
        Assertions.assertEquals(newAdds, expectedList);
    }

    @Test
    public void canRemoveAllAddAtOnce() {
        if (app.contacts().getCountContact() == 0) {
            app.contacts().createContactNew(new ContactData().withFirstName("Test"));
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0, app.contacts().getCountContact());
    }

}
