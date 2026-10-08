package org.example.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class AdminPage {

    public AdminPage open() {
        $("a[href='/admin']").shouldBe(visible).click();
        return this;
    }

    public AdminPage openSection(String path) {
        $("a[href='" + path + "']").shouldBe(visible).click();
        return this;
    }

    public SelenideElement title() {
        return $$("p.chakra-text").first();
    }

    public ElementsCollection rows() {
        return $(".chakra-table").$$("tbody tr");
    }

    public SelenideElement content() {
        return $(".chakra-stack");
    }
}
