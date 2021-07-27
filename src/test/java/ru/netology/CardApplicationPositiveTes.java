package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardApplicationPositiveTes {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    // 1. Отправка заявки с верно заполненными полями;
    @Test
    public void shouldReturnSuccessIfFieldsAreFilledInCorrectly() {

        $("[data-test-id=name] input").setValue("Ирина Пирогова");
        $("[data-test-id=phone] input").setValue("+79883335522");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    // 2. Отправка заявки с указание только имени;
    @Test
    public void shouldReturnSuccessfullyIfIfOnlyNameIsSpecified() {

        $("[data-test-id=name] input").setValue("Ирина");
        $("[data-test-id=phone] input").setValue("+79883335522");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    // 3. Отправка заявки с указание только фамилии;
    @Test
    public void shouldReturnSuccessfullyIfIfOnlySurnameIsSpecified() {

        $("[data-test-id=name] input").setValue("Пирогова");
        $("[data-test-id=phone] input").setValue("+79883335522");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    // 4. Отправка заявки с указание фамилии через дефис;
    @Test
    public void shouldReturnSuccessfullyIfSurnameWithHyphen() {

        $("[data-test-id=name] input").setValue("Ирина Пирогова-Смирнова");
        $("[data-test-id=phone] input").setValue("+79883335522");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
}