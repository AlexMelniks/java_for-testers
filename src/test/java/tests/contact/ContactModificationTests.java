package tests.contact;

import model.ContactData;
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
        var oldAdds = app.contacts().getListContact();
        var rnd = new Random();
        var index = rnd.nextInt(oldAdds.size());
        var testData = new ContactData().withFirstName("modify name");
        app.contacts().modifyContact(oldAdds.get(index), testData);
        var newAdds = app.contacts().getListContact();
        var expectedList = new ArrayList<>(oldAdds);
        expectedList.set(index, testData.withId(oldAdds.get(index).id()));
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newAdds.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newAdds, expectedList);
    }


}
