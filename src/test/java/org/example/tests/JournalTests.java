package org.example.tests;

import com.codeborne.selenide.ElementsCollection;
import org.example.pages.JournalPage;
import org.example.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

@DisplayName("Журнал")
public class JournalTests extends BaseTest {
    LoginPage loginPage = new LoginPage();
    JournalPage journalPage = new JournalPage();

    @BeforeEach
    void login() {
        loginPage.openPage();
        loginPage.loginAs("admin", "123456");
        journalPage.open();
    }

    @Test
    @DisplayName("Таблица журнала содержит данные")
    void journalTableTest() {
        journalPage.table().shouldBe(visible);
        journalPage.rows().shouldHave(sizeGreaterThan(0));

        ElementsCollection firstRowCells = journalPage.rows().first().$$("td");
        firstRowCells.shouldHave(sizeGreaterThan(0));
    }

    @Test
    @DisplayName("Фильтрация журнала по группе и сброс фильтров")
    void groupFilterTest() {
        String groupName = journalPage.selectSecondGroup();

        journalPage.groupFilter().shouldHave(text(groupName));
        journalPage.table().shouldBe(visible);
        journalPage.rows().shouldHave(sizeGreaterThan(0));

        journalPage.resetFilters();

        journalPage.table().shouldNot(exist);
        journalPage.emptyMessage().shouldBe(visible);
    }
}
