package ru.netology.tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataGenerator.Registration.getRegisteredUser;


public class IBankTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successfully login with active registered user")
    void shouldSuccessfulLoginWithRegisteredAndActiveUser() {
        var registeredUser = getRegisteredUser("active");
        $("[data-test-id='login'] input").val(registeredUser.getLogin());
        $("[data-test-id='password'] input").val(registeredUser.getPassword());
        $("[data-test-id='action-login']").click();
        $("h2").shouldHave(text("Личный кабинет")).shouldBe((Condition.visible));

    }
}


