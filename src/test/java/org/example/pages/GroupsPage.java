package org.example.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class GroupsPage {

    public GroupsPage open() {
        $("a[href='/admin']").shouldBe(visible).click();
        $("a[href='/admin/groups']").shouldBe(visible).click();
        return this;
    }

    public void createGroup(String name, String slug) {
        $(byText("Добавить группу")).shouldBe(visible).click();
        $("#name").setValue(name);
        $("#slug").setValue(slug);
        $("#curatorId-label").parent().$("[aria-haspopup='menu']").shouldBe(visible).click();
        $("[role='menuitem']").shouldBe(visible).click();
        $("#description").setValue("test");
        $("[type='submit']").click();
    }

    public SelenideElement successMessage() {
        return $(byText("Группа успешно создана"));
    }

    public SelenideElement deleteMessage() {
        return $(byText("Группа удалена"));
    }

    public SelenideElement group(String name) {
        return $(byText(name));
    }

    public SelenideElement row(String name) {
        return $$("table tbody tr").findBy(text(name));
    }

    public void deleteGroup(String name) {
        row(name).shouldBe(visible).$("button[aria-label='Удалить']").click();
        SelenideElement deleteModal = $("dialog");
        deleteModal.shouldBe(visible).$(byText("Удалить")).click();
    }
}
