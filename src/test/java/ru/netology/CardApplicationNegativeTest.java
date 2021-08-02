package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardApplicationNegativeTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    // 1. Отправка заявки с пустым полем "Фамилия и Имя";
    @Test
    public void shouldReturnErrorMessageIfSurnameAndNameIsEmpty() {

        $("[data-test-id='name'] [type=text]").setValue("");
        $("[data-test-id=phone] [class='input__box'] [class='input__control']").setValue("+79883335522");
        $("[data-test-id=agreement]").click();
        $("[role=button] [class='button__content'] [class='button__text']").click();
        $("[data-test-id='name'].input_invalid .input__sub")
                .shouldHave(exactText("Поле обязательно для заполнения"));
    }

    // 2. Отправка заявки с полем "Фамилия и Имя" на английском языке;
    @Test
    public void shouldReturnErrorMessageIfInvalidSurnameAndName() {

        $("[data-test-id=name] [type=text]").setValue("Irina Pirogova");
        $("[data-test-id=phone] [class='input__box'] [class='input__control']").setValue("+79883335522");
        $("[data-test-id=agreement]").click();
        $("[role=button] [class='button__content'] [class='button__text']").click();
        $("[data-test-id='name'].input_invalid .input__sub")
                .shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    // 3. Отправка заявки с полем "Фамилия и Имя", состоящий из цифр;
    @Test
    public void shouldReturnErrorMessageIfSurnameAndNameConsistsOfNumbers() {

        $("[data-test-id=name] [type=text]").setValue("555");
        $("[data-test-id=phone] [class='input__box'] [class='input__control']").setValue("+79883335522");
        $("[data-test-id=agreement]").click();
        $("[role=button] [class='button__content'] [class='button__text']").click();
        $("[data-test-id='name'].input_invalid .input__sub")
                .shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    // 4. Отправка заявки с полем "Фамилия и Имя", состоящий из спецсимволов;
    @Test
    public void shouldReturnErrorMessageIfSurnameAndNameConsistsOfSpecialCharacters() {

        $("[data-test-id=name] [type=text]").setValue("%&%&%&");
        $("[data-test-id=phone] [class='input__box'] [class='input__control']").setValue("+79883335522");
        $("[data-test-id=agreement]").click();
        $("[role=button] [class='button__content'] [class='button__text']").click();
        $("[data-test-id='name'].input_invalid .input__sub")
                .shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    // 5. Отправка заявки с пустым полем "Мобильный телефон";
    @Test
    public void shouldReturnErrorMessageIfPhoneEmpty() {

        $("[data-test-id=name] [type=text]").setValue("Ирина Пирогова");
        $("[data-test-id=phone] [class='input__box'] [class='input__control']").setValue("");
        $("[data-test-id=agreement]").click();
        $("[role=button] [class='button__content'] [class='button__text']").click();
        $("[data-test-id='phone'].input_invalid .input__sub")
                .shouldHave(exactText("Поле обязательно для заполнения"));
    }

    // 6. Отправка заявки с введенным в поле "Мобильный телефон" букв;
    @Test
    public void shouldReturnErrorMessageIfPhoneWithLetters() {

        $("[data-test-id=name] [type=text]").setValue("Ирина Пирогова");
        $("[data-test-id=phone] [class='input__box'] [class='input__control']").setValue("АБВ");
        $("[data-test-id=agreement]").click();
        $("[role=button] [class='button__content'] [class='button__text']").click();
        $("[data-test-id='phone'].input_invalid .input__sub")
                .shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    // 7. Отправка заявки с введенным в поле "Мобильный телефон" спецсимволов;
    @Test
    public void shouldReturnErrorMessageIfPhoneHasSpecialCharacters() {

        $("[data-test-id=name] [type=text]").setValue("Ирина Пирогова");
        $("[data-test-id=phone] [class='input__box'] [class='input__control']").setValue("%&%&%&");
        $("[data-test-id=agreement]").click();
        $("[role=button] [class='button__content'] [class='button__text']").click();
        $("[data-test-id='phone'].input_invalid .input__sub")
                .shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    // 8. Отправка заявки с неверно заполненным полем "Мобильный телефон", без "+";
    @Test
    public void shouldReturnErrorMessageIfPhoneIsWrong() {
        $("[data-test-id=name] [type=text]").setValue("Ирина Пирогова");
        $("[data-test-id=phone] [class='input__box'] [class='input__control']").setValue("79883335522");
        $("[data-test-id=agreement]").click();
        $("[role=button] [class='button__content'] [class='button__text']").click();
        $("[data-test-id='phone'].input_invalid .input__sub")
                .shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    // 9. Отправка заявки с введенным в поле "Мобильный телефон" неверного количества цифр;
    @Test
    public void shouldReturnErrorMessageIfPhoneWrong() {
        $("[data-test-id=name] [type=text]").setValue("Ирина Пирогова");
        $("[data-test-id=phone] [class='input__box'] [class='input__control']").setValue("+7983335522");
        $("[data-test-id=agreement]").click();
        $("[role=button] [class='button__content'] [class='button__text']").click();
        $("[data-test-id='phone'].input_invalid .input__sub")
                .shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    // 10. Отправка заявки без согласия с условиями обработки персональных данных;
    @Test
    public void shouldReturnErrorMessageIfDoNotTick() {

        $("[data-test-id=name] [type=text]").setValue("Ирина Пирогова");
        $("[data-test-id=phone] [class='input__box'] [class='input__control']").setValue("+79883335522");
        $("[role=button] [class='button__content'] [class='button__text']").click();
        $("[data-test-id='agreement'].input_invalid .checkbox__text")
                .shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }
}