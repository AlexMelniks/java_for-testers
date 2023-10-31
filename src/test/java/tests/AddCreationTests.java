package tests;

import model.AddData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AddCreationTests extends TestBase {

    @ParameterizedTest
    @MethodSource("addProvider")
    public void canCreateMultipleAdds(AddData add) {
        var oldAdds = app.adds().getList();
        app.adds().createAddNew(add);
        var newAdds = app.adds().getList();
        Comparator<AddData> compareById = Comparator.comparingInt(o -> Integer.parseInt(o.id()));
        newAdds.sort(compareById);
        var expectedList = new ArrayList<>(oldAdds);
        expectedList.add(add.withId(newAdds.get(newAdds.size() - 1).id()).withFirstName("").withMiddleName("").withLastName(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newAdds, expectedList);

    }

    @ParameterizedTest
    @MethodSource("negativeAddProvider")
    public void canNotCreateAdd(AddData add) {
        var oldAdds = app.adds().getList();
        app.adds().createAddNew(add);
        var newAdds = app.adds().getList();
        Assertions.assertEquals(newAdds, oldAdds);

    }

    public static List<AddData> addProvider() {
        var result = new ArrayList<AddData>();
        for (var firstName : List.of("", "Test")) {
            for (var middleName : List.of("", "Test")) {
                for (var lastName : List.of("", "Test")) {
                    result.add(new AddData().withFirstName(firstName).withMiddleName(middleName).withLastName(lastName));
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            result.add(new AddData().withFirstName(randomString(i * 10)).withMiddleName(randomString(i * 10)).withLastName(randomString(i * 10)));
        }
        return result;
    }

    public static List<AddData> negativeAddProvider() {
        return new ArrayList<AddData>(List.of(new AddData("", "Test'", "", "")));
    }
}
