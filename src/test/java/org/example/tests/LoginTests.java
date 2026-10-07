package org.example.tests;

import org.example.pages.LoginPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class LoginTests extends BaseTest {
    LoginPage loginPage = new LoginPage();

    @Test
    void successAdminAuth() {
        loginPage.openPage();
        loginPage.loginAs("admin", "123456");

        webdriver().shouldHave(url("http://localhost:5173/"));

        $(".chakra-heading").shouldHave(text("Добро пожаловать"));
    }

    @Test
    void unSuccessAdminAuth() {
        loginPage.openPage();
        loginPage.loginAs("admin666", "123456");

        webdriver().shouldHave(url("http://localhost:5173/auth"));

        $(".chakra-alert").shouldHave(text("Ошибка входа"));

        $(".chakra-heading").shouldNotHave(text("Добро пожаловать"));
    }
}
