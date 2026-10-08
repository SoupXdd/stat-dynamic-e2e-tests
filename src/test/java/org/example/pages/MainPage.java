package org.example.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    public MainPage openSection(String path) {
        $("a[href='" + path + "']").shouldBe(visible).click();
        return this;
    }
}
