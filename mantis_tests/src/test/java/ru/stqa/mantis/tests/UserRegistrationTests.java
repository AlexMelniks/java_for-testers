//package ru.stqa.mantis.tests;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.MethodSource;
//import ru.stqa.mantis.common.CommonFunctions;
//import io.swagger.client.model.UserAddResponse;
//import ru.stqa.mantis.model.UserData;
//
//import java.time.Duration;
//import java.util.function.Supplier;
//import java.util.stream.Stream;
//
//public class UserRegistrationTests extends TestBase {
//
//    public static Stream<String> randomUserProvider() {
//        Supplier<String> randomUser = () -> CommonFunctions.randomString(10);
//        return Stream.generate(randomUser).limit(1);
//    }
//
//    @ParameterizedTest
//    @MethodSource("randomUserProvider")
//    void canRegisterUser(String username) {
//        var email = String.format("%s@localhost", username);
//        var password = "password";
//        app.jamesCli().addUser(email, password);
//        app.registration().initRegistration(username, email);
//        var message = app.mail().receive(email, password, Duration.ofSeconds(10)).get(0);
//        var url = app.mail().extractUrl(message);
//        app.mail().drain(email, password);
//        app.registration().completeRegistration(url, username, password);
//        app.http().login(username, password);
//        Assertions.assertTrue(app.http().isLoggedIn());
//    }
//    @ParameterizedTest
//    @MethodSource("randomUserProvider")
//    void canRegisterUserViaApi(String username) {
//        var email = String.format("%s@localhost", username);
//        var password = "password";
//        app.jamesApi().addUser(email, password);
//        UserAddResponse addResponse = app.restApi()
//                .createUser(new UserData().withUserName(username).withEmail(email));
//        var message = app.mail().receive(email, password, Duration.ofSeconds(10)).get(0);
//        var url = app.mail().extractUrl(message);
//        app.jamesApi().drainInbox(email);
//        app.registration().completeRegistration(url, username, password);
//        app.http().login(username, password);
//        Assertions.assertTrue(app.http().isLoggedIn());
//
//        app.restApi().deleteUser(addResponse.getUser().getId());
//        app.jamesApi().deleteUser(email);
//    }
//}