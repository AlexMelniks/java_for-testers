package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static ru.stqa.mantis.tests.TestBase.app;

public class LoginTests extends TestBase{
    @Test
    void canLogin(){
        app.session().login("administrator", "root");
        Assertions.assertTrue(app.session().isLoggedIn());
    }
}
