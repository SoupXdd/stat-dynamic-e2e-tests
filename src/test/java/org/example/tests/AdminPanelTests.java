package org.example.tests;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.example.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class AdminPanelTests extends BaseTest {
    LoginPage loginPage = new LoginPage();

    @BeforeEach
    void login() {
        loginPage.openPage();
        loginPage.loginAs("admin", "123456");
        $("a[href=\"/admin\"]").click();
    }

    @ParameterizedTest
    @MethodSource("pages")
    void navigationTest(String link, String expectedUrl, String expectedTitle) {

        $(link).click();

        webdriver().shouldHave(url("http://localhost:5173/admin" + expectedUrl));
        $$("p.chakra-text").get(0).shouldHave(text(expectedTitle));
        $(".chakra-table").$$("tbody tr").shouldHave(sizeGreaterThan(0));
        $(".chakra-stack").shouldNotHave(text("Internal Server Error"));
        $(".chakra-stack").shouldNotHave(text("Failed to fetch"));
    }

    static Stream<Arguments> pages() {
        return Stream.of(
                Arguments.of("a[href=\"/admin/users\"]", "/users", "Управление пользователями"),
                Arguments.of("a[href=\"/admin/groups\"]", "/groups", "Управление группами"),
                Arguments.of("a[href=\"/admin/disciplines\"]", "/disciplines", "Управление дисциплинами"),
                Arguments.of("a[href=\"/admin/courses\"]", "/courses", "Управление курсами"),
                Arguments.of("a[href=\"/admin/themes\"]", "/themes", "Управление темами"),
                Arguments.of("a[href=\"/admin/grades\"]", "/grades", "Управление оценками"),
                Arguments.of("a[href=\"/admin/meetings\"]", "/meetings", "Управление встречами"),
                Arguments.of("a[href=\"/admin/homework\"]", "/homework", "Управление домашними заданиями"),
                Arguments.of("a[href=\"/admin/docs\"]", "/docs", "Управление библиотекой"),
                Arguments.of("a[href=\"/admin/academic-years\"]", "/academic-years", "Управление учебными годами")

        );
    }

}
