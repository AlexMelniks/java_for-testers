package tests.contact;

import model.AddData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.base.TestBase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class AddModificationTests extends TestBase {
    @Test
    void canModifyAdd(){
        if (app.adds().getCountAdd() == 0) {
            app.adds().createAddNew(new AddData());
        }
        var oldAdds = app.adds().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldAdds.size());
        var testData = new AddData().withFirstName("modify name");
        app.adds().modifyAdd(oldAdds.get(index), testData);
        var newAdds = app.adds().getList();
        var expectedList = new ArrayList<>(oldAdds);
        expectedList.set(index, testData.withId(oldAdds.get(index).id()));
        Comparator<AddData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newAdds.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newAdds, expectedList);
    }


}
