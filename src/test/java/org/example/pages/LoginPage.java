package org.example.pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {
    public LoginPage openPage() {
        open("/auth");
        return this;
    }

    public LoginPage loginAs(String loginValue, String passValue) {
        $("#login").setValue(loginValue);
        $("#password").setValue(passValue);
        $("button[type='submit']").click();

        return this;
    }
}
