package org.example.tests;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.example.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.exist;


public class GroupsTests extends BaseTest {
    LoginPage loginPage = new LoginPage();

    @BeforeEach
    void login() {
        loginPage.openPage();
        loginPage.loginAs("admin", "123456");
        $("a[href=\"/admin\"]").click();
        $("a[href=\"/admin/groups\"]").click();
    }

    @Test
    void groupCrudTest() {
        String groupName = "E2E-группа-" + System.currentTimeMillis();
        $(byText("Добавить группу")).click();

        $("#name").setValue(groupName);
        $("#slug").setValue("test-" + System.currentTimeMillis());
        //$("#gradeLevel").setValue("\"10\"");   BUG
        $("#curatorId-label").parent().$("[aria-haspopup='menu']").shouldBe(visible).click();
        $("[role='menuitem']").shouldBe(visible).click();
        $("#description").setValue("test");
        $("[type=\"submit\"]").click();

        $(byText("Группа успешно создана"))
                .shouldBe(visible);

        $(byText(groupName)).shouldBe(visible);
        refresh();
        $(byText(groupName)).shouldBe(visible);

        ElementsCollection rows = $$("table tbody tr");

        SelenideElement groupRow = rows.findBy(text(groupName));

        groupRow.shouldBe(visible);
        groupRow.$("button[aria-label='Удалить']").click();

        $(byText("Удалить")).click();

        $(byText("Группа удалена"))
                .shouldBe(visible);

        $$("table tbody tr")
                .findBy(text(groupName))
                .shouldNot(exist);

    }
}
