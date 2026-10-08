package org.example.tests;

import org.example.pages.LoginPage;
import org.example.pages.MainPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

@DisplayName("Основная навигация")
public class NavigationTests extends BaseTest {
    LoginPage loginPage = new LoginPage();
    MainPage mainPage = new MainPage();

    @BeforeEach
    void login() {
        loginPage.openPage();
        loginPage.loginAs("admin", "123456");
    }

    @DisplayName("Переходы по основным страницам")
    @ParameterizedTest(name = "{index} -> {1}")
    @MethodSource("pages")
    void navigationTest(String path, String expectedTitle) {

        mainPage.openSection(path);

        webdriver().shouldHave(url("http://localhost:5173" + path));
        $("h1").shouldHave(text(expectedTitle));
    }

    static Stream<Arguments> pages() {
        return Stream.of(
                Arguments.of("/schedule", "Расписание"),
                Arguments.of("/journal", "Журнал предметника"),
                Arguments.of("/statistic", "Статистика")

        );
    }
}
