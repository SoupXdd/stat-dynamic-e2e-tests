package org.example.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public abstract class BaseTest {

    @BeforeAll
    static void configure() {
        Configuration.baseUrl = "http://localhost:5173";
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }
}
