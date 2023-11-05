package tests.Group;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.ArrayList;
import java.util.Random;

public class GroupRemovalTests extends TestBase {

    @Test
    public void canRemoveGroup() {
        if (app.groups().getCountGroup() == 0) {
            app.groups().createGroup(new GroupData());
        }
        var oldGroups = app.groups().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        app.groups().removeGroup(oldGroups.get(index));
        var newGroups = app.groups().getList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.remove(index);
        Assertions.assertEquals(newGroups, expectedList);
    }

    @Test
    public void canRemoveAllGroupsAtOnce() {
        if (app.groups().getCountGroup() == 0) {
            app.groups().createGroup(new GroupData("", "newGroup", "newHeader", "newFooter"));
        }
        app.groups().removeAllGroups();
        Assertions.assertEquals(0, app.groups().getCountGroup());
    }
}
