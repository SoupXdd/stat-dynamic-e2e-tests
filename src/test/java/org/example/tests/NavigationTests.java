package org.example.tests;

import org.example.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class NavigationTests extends BaseTest {
    LoginPage loginPage = new LoginPage();

    @BeforeEach
    void login() {
        loginPage.openPage();
        loginPage.loginAs("admin", "123456");
    }

    @ParameterizedTest
    @MethodSource("pages")
    void navigationTest(String link, String expectedUrl, String expectedTitle) {

        $(link).click();

        webdriver().shouldHave(url("http://localhost:5173" + expectedUrl));
        $("h1").shouldHave(text(expectedTitle));
    }

    static Stream<Arguments> pages() {
        return Stream.of(
                Arguments.of("a[href=\"/schedule\"]", "/schedule", "Расписание"),
                Arguments.of("a[href=\"/journal\"]", "/journal", "Журнал предметника"),
                Arguments.of("a[href=\"/statistic\"]", "/statistic", "Статистика")

        );
    }
}
