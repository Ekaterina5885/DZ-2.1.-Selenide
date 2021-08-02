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

        $("[data-test-id='name'] [type=text]").setValue("Ирина Пирогова");
        $("[data-test-id=phone] [class='input__box'] [class='input__control']").setValue("+79883335522");
        $("[data-test-id=agreement]").click();
        $("[role=button] [class='button__content'] [class='button__text']").click();
        $("[data-test-id=order-success]")
                .shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    // 2. Отправка заявки с указанием фамилии через дефис;
    @Test
    public void shouldReturnSuccessfullyIfSurnameWithHyphen() {

        $("[data-test-id='name'] [type=text]").setValue("Ирина Пирогова-Смирнова");
        $("[data-test-id=phone] [class='input__box'] [class='input__control']").setValue("+79883335522");
        $("[data-test-id=agreement]").click();
        $("[role=button] [class='button__content'] [class='button__text']").click();
        $("[data-test-id=order-success]")
                .shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
}