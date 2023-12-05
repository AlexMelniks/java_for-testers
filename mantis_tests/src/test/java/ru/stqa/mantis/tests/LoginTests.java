package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static ru.stqa.mantis.tests.TestBase.app;

public class LoginTests extends TestBase{
    @Test
    void canLogin(){
        app.http().login("administrator", "root");
        Assertions.assertTrue(app.http().isLoggedIn());
    }
}
