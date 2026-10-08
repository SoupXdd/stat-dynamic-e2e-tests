package org.example.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {

    @Step("Открыть страницу авторизации")
    public LoginPage openPage() {
        open("/auth");
        return this;
    }

    @Step("Войти как пользователь {loginValue}")
    public LoginPage loginAs(String loginValue, String passValue) {
        $("#login").setValue(loginValue);
        $("#password").setValue(passValue);
        $("button[type='submit']").click();

        return this;
    }
}
