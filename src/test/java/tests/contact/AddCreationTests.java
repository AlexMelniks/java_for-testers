package tests.contact;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import model.AddData;
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
        expectedList.add(add.withId(newAdds.get(newAdds.size() - 1).id()).withPhoto(""));
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


    public static List<AddData> addProvider() throws IOException {
        var result = new ArrayList<AddData>();
        for (var firstName : List.of("", "Test")) {
                for (var lastName : List.of("", "Test")) {
                    result.add(new AddData()
                            .withFirstName(firstName)
                            .withLastName(lastName)
                            .withPhoto(randomFile("src/test/resources/images")));
                }

        }
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(new File("contacts.json"), new TypeReference<List<AddData>>(){});
        result.addAll(value);
        return result;
    }

    public static List<AddData> negativeAddProvider() {
        return new ArrayList<>(List.of(new AddData("", "Test'", "", (randomFile("src/test/resources/images")))));
    }

}
