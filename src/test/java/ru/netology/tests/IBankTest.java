package ru.netology.tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataGenerator.Registration.getRegisteredUser;
import static ru.netology.data.DataGenerator.getAnyLogin;
import static ru.netology.data.DataGenerator.getAnyPassword;


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
        $("button.button").click();
        $("h2").shouldHave(Condition.exactText("Личный кабинет")).shouldBe((Condition.visible));

    }

    @Test
    @DisplayName("Should unsuccessfully login with blocked registered user")
    void shouldUnsuccessfulLoginWithRegisteredButBlockedUser() {
        var registeredUser = getRegisteredUser("blocked");
        $("[data-test-id='login'] input").val(registeredUser.getLogin());
        $("[data-test-id='password'] input").val(registeredUser.getPassword());
        $("button.button").click();
        $("[class=\"notification__content\"]").shouldHave(Condition.exactText("Ошибка! Пользователь заблокирован")).shouldBe((Condition.visible));

    }

    @Test
    @DisplayName("Should unsuccessfully login with wrong login")
    void shouldUnsuccessfulLoginWithWrongLogin() {
        var registeredUser = getRegisteredUser("active");
        var wrongLogin = getAnyLogin();
        $("[data-test-id='login'] input").val(wrongLogin);
        $("[data-test-id='password'] input").val(registeredUser.getPassword());
        $("button.button").click();
        $("[class=\"notification__content\"]").shouldHave(Condition.exactText("Ошибка! Неверно указан логин или пароль")).shouldBe((Condition.visible));

    }

    @Test
    @DisplayName("Should unsuccessfully login with wrong password")
    void shouldUnsuccessfulLoginWithWrongPassword() {
        var registeredUser = getRegisteredUser("active");
        var wrongPassword = getAnyPassword();
        $("[data-test-id='login'] input").val(wrongPassword);
        $("[data-test-id='password'] input").val(registeredUser.getPassword());
        $("button.button").click();
        $("[class=\"notification__content\"]").shouldHave(Condition.exactText("Ошибка! Неверно указан логин или пароль")).shouldBe((Condition.visible));

    }

    @Test
    @DisplayName("Should unsuccessfully login with wrong password")
    void shouldUnsuccessfulLoginWithEmptyLogin() {
        var registeredUser = getRegisteredUser("active");
        var emptyLogin = ' ';
        $("[data-test-id='login'] input").val(String.valueOf(emptyLogin));
        $("[data-test-id='password'] input").val(registeredUser.getPassword());
        $("button.button").click();
        $("[class=\"input__sub\"]").shouldHave(Condition.exactText("Поле обязательно для заполнения")).shouldBe((Condition.visible));

    }
}


