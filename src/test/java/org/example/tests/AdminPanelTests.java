package org.example.tests;

import org.example.pages.AdminPage;
import org.example.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

@DisplayName("Административная панель")
public class AdminPanelTests extends BaseTest {
    LoginPage loginPage = new LoginPage();
    AdminPage adminPage = new AdminPage();

    @BeforeEach
    void login() {
        loginPage.openPage();
        loginPage.loginAs("admin", "123456");
        adminPage.open();
    }

    @DisplayName("Проверка навигации по админ-панели")
    @ParameterizedTest(name = "{index} -> {1}")
    @MethodSource("pages")
    void navigationTest(String path, String expectedTitle) {

        adminPage.openSection(path);

        webdriver().shouldHave(url("http://localhost:5173" + path));
        adminPage.title().shouldHave(text(expectedTitle));
        adminPage.rows().shouldHave(sizeGreaterThan(0));
        adminPage.content().shouldNotHave(text("Internal Server Error"));
        adminPage.content().shouldNotHave(text("Failed to fetch"));
    }

    static Stream<Arguments> pages() {
        return Stream.of(
                Arguments.of("/admin/users", "Управление пользователями"),
                Arguments.of("/admin/groups", "Управление группами"),
                Arguments.of("/admin/disciplines", "Управление дисциплинами"),
                Arguments.of("/admin/courses", "Управление курсами"),
                Arguments.of("/admin/themes", "Управление темами"),
                Arguments.of("/admin/grades", "Управление оценками"),
                Arguments.of("/admin/meetings", "Управление встречами"),
                Arguments.of("/admin/homework", "Управление домашними заданиями"),
                Arguments.of("/admin/docs", "Управление библиотекой"),
                Arguments.of("/admin/academic-years", "Управление учебными годами")

        );
    }

}
