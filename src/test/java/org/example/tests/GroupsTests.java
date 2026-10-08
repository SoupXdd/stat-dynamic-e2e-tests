package org.example.tests;

import org.example.pages.GroupsPage;
import org.example.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.refresh;


public class GroupsTests extends BaseTest {
    LoginPage loginPage = new LoginPage();
    GroupsPage groupsPage = new GroupsPage();

    @BeforeEach
    void login() {
        loginPage.openPage();
        loginPage.loginAs("admin", "123456");
        groupsPage.open();
    }

    @Test
    void groupCrudTest() {
        long timestamp = System.currentTimeMillis();
        String groupName = "E2E-группа-" + timestamp;
        groupsPage.createGroup(groupName, "test-" + timestamp);

        groupsPage.successMessage().shouldBe(visible);
        groupsPage.group(groupName).shouldBe(visible);
        refresh();
        groupsPage.group(groupName).shouldBe(visible);

        groupsPage.deleteGroup(groupName);
        groupsPage.deleteMessage().shouldBe(visible);
        groupsPage.row(groupName).shouldNot(exist);

    }
}
