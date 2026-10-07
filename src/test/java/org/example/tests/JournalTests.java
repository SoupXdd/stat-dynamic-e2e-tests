package org.example.tests;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.example.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class JournalTests extends BaseTest {
    LoginPage loginPage = new LoginPage();

    SelenideElement table = $("table");
    ElementsCollection rows = table.$$("tbody tr");

    @BeforeEach
    void login() {
        loginPage.openPage();
        loginPage.loginAs("admin", "123456");
        $("[href=\"/journal\"]").click();
    }

    @Test
    void journalTableTest() {
        table.shouldBe(visible);
        rows.shouldHave(sizeGreaterThan(0));

        ElementsCollection firstRowCells = rows.first().$$("td");
        firstRowCells.shouldHave(sizeGreaterThan(0));
    }

    @Test
    void groupFilterTest() {
        $("[aria-label='Курс']").click();
        $$("[role='menuitem']").get(1).click();

        $("[aria-label='Группа']").click();
        SelenideElement group = $$("[role='menuitem']").get(1);
        String groupName = group.getText();
        group.click();

        $("[aria-label='Группа']").shouldHave(text(groupName));
        table.shouldBe(visible);
        rows.shouldHave(sizeGreaterThan(0));

        $(byText("Сбросить фильтры")).click();

        table.shouldNot(exist);
        $(byText("Выберите курс и группу для отображения журнала")).shouldBe(visible);
    }
}
