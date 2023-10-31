package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupModificationTests extends TestBase{
    @Test
    void canModifyGroup(){
        if (app.groups().getCountGroup() == 0) {
            app.groups().createGroup(new GroupData("", "newGroup", "newHeader", "newFooter"));
        }
        app.groups().modifyGroup(new GroupData().withName("modify group"));
    }
}
