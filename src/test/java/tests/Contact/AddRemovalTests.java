package tests.Contact;

import model.AddData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.ArrayList;
import java.util.Random;

public class AddRemovalTests extends TestBase {

    @Test
    public void canRemoveAdd() {
        if (app.adds().getCountAdd() == 0) {
            app.adds().createAddNew(new AddData());
        }
        var oldAdds = app.adds().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldAdds.size());
        app.adds().removeAdd(oldAdds.get(index));
        var newAdds = app.adds().getList();
        var expectedList = new ArrayList<>(oldAdds);
        expectedList.remove(index);
        Assertions.assertEquals(newAdds, expectedList);
    }

    @Test
    public void canRemoveAllAddAtOnce() {
        if (app.adds().getCountAdd() == 0) {
            app.adds().createAddNew(new AddData().withFirstName("Тест"));
        }
        app.adds().removeAllAdd();
        Assertions.assertEquals(0, app.adds().getCountAdd());
    }

}
