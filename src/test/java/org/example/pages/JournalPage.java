package org.example.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class JournalPage {

    public JournalPage open() {
        $("a[href='/journal']").shouldBe(visible).click();
        return this;
    }

    public SelenideElement table() {
        return $("table");
    }

    public ElementsCollection rows() {
        return table().$$("tbody tr");
    }

    public String selectSecondGroup() {
        $("[aria-label='Курс']").click();
        $$("[role='menuitem']").get(1).shouldBe(visible).click();

        $("[aria-label='Группа']").click();
        SelenideElement group = $$("[role='menuitem']").get(1).shouldBe(visible);
        String groupName = group.getText();
        group.click();
        return groupName;
    }

    public SelenideElement groupFilter() {
        return $("[aria-label='Группа']");
    }

    public void resetFilters() {
        $(byText("Сбросить фильтры")).shouldBe(visible).click();
    }

    public SelenideElement emptyMessage() {
        return $(byText("Выберите курс и группу для отображения журнала"));
    }
}
