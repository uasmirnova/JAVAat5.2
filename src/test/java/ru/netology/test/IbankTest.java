package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.testinfo.Info;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.testinfo.DataGenerator.*;

public class IbankTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    public void shouldValidActiveUser() {
        Info generateUser = activeUser();
        $("[data-test-id=login] input").setValue(generateUser.getLogin());
        $("[data-test-id=password] input").setValue(generateUser.getPassword());
        $("[data-test-id=action-login]").click();
        $(".App_appContainer__3jRx1 h2").shouldBe(visible).shouldHave(text("Личный кабинет"));
    }

    @Test
    public void shouldBlockedUser() {
        Info generateUser = blockedUser();
        $("[data-test-id=login] input").setValue(generateUser.getLogin());
        $("[data-test-id=password] input").setValue(generateUser.getPassword());
        $("[data-test-id=action-login]").click();
        $("[data-test-id=error-notification]").shouldBe(visible).shouldHave(text("Пользователь заблокирован"));
    }

    @Test
    public void shouldNotRegisteredUser() {
        Info generateUser = notRegisteredUser();
        $("[data-test-id=login] input").setValue(generateUser.getLogin());
        $("[data-test-id=password] input").setValue(generateUser.getPassword());
        $("[data-test-id=action-login]").click();
        $("[data-test-id=error-notification]").shouldBe(visible).shouldHave(text("Неверно указан логин или пароль"));
    }

    @Test
    public void shouldInvalidLoginUser() {
        Info generateUser = invalidLoginUser();
        $("[data-test-id=login] input").setValue(generateUser.getLogin());
        $("[data-test-id=password] input").setValue(generateUser.getPassword());
        $("[data-test-id=action-login]").click();
        $("[data-test-id=error-notification]").shouldBe(visible).shouldHave(text("Неверно указан логин или пароль"));
    }

    @Test
    public void shouldInvalidPasswordUser() {
        Info generateUser = invalidPasswordUser();
        $("[data-test-id=login] input").setValue(generateUser.getLogin());
        $("[data-test-id=password] input").setValue(generateUser.getPassword());
        $("[data-test-id=action-login]").click();
        $("[data-test-id=error-notification]").shouldBe(visible).shouldHave(text("Неверно указан логин или пароль"));
    }
}
