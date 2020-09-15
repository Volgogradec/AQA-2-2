package ru.netology;

import org.junit.jupiter.api.Test;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardDeliveryTest {
//    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

    @Test
    void testPositiveAllInput() {
        open("http://localhost:9999/");
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id='city'] input").setValue("Волгоград");
        form.$("[data-test-id='date'] input").setValue("18.09.2020");
        form.$("[data-test-id='name'] input").setValue("Дмитрий Евдокимов");
        form.$("[data-test-id='phone'] input").setValue("+79642682654");
        form.$("[data-test-id='agreement']").click();
        form.$(".button__content").click();
        $("[data-test-id='notification']").waitUntil(visible, 14000);
    }

    @Test
    void testPositiveDateEmpty(){
        open("http://localhost:9999/");
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id='city'] input").setValue("Волгоград");
        form.$("[data-test-id='name'] input").setValue("Дмитрий Евдокимов");
        form.$("[data-test-id='phone'] input").setValue("+79642682654");
        form.$("[data-test-id='agreement']").click();
        form.$(".button__content").click();
        $("[data-test-id='notification']").waitUntil(visible, 14000);
    }

    @Test
    void testNegativeCityEmpty(){
        open("http://localhost:9999/");
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id='date'] input").setValue("18.09.2020");
        form.$("[data-test-id='name'] input").setValue("Дмитрий Евдокимов");
        form.$("[data-test-id='phone'] input").setValue("+79642682654");
        form.$("[data-test-id='agreement']").click();
        form.$(".button__content").click();
        String text = form.$("[data-test-id='city'] .input__sub").getText();
        assertEquals("Поле обязательно для заполнения", text.trim());
    }

    @Test
    void testNegativeNameEmpty(){
        open("http://localhost:9999/");
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id='city'] input").setValue("Волгоград");
        form.$("[data-test-id='date'] input").setValue("18.09.2020");
        form.$("[data-test-id='phone'] input").setValue("+79642682654");
        form.$("[data-test-id='agreement']").click();
        form.$(".button__content").click();
        String text = form.$("[data-test-id='name'] .input__sub").getText();
        assertEquals("Поле обязательно для заполнения", text.trim());
    }

    @Test
    void testNegativePhoneEmpty(){
        open("http://localhost:9999/");
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id='city'] input").setValue("Волгоград");
        form.$("[data-test-id='date'] input").setValue("18.09.2020");
        form.$("[data-test-id='name'] input").setValue("Дмитрий Евдокимов");
        form.$("[data-test-id='agreement']").click();
        form.$(".button__content").click();
        String text = form.$("[data-test-id='phone'] .input__sub").getText();
        assertEquals("Поле обязательно для заполнения", text.trim());
    }

    @Test
    void testNegativeAgreementEmpty(){
        open("http://localhost:9999/");
        SelenideElement form = $("[action='/']");
        form.$("[data-test-id='city'] input").setValue("Волгоград");
        form.$("[data-test-id='date'] input").setValue("18.09.2020");
        form.$("[data-test-id='name'] input").setValue("Дмитрий Евдокимов");
        form.$("[data-test-id='phone'] input").setValue("+79642682654");
        form.$(".button__content").click();
        String text = form.$("[data-test-id='agreement'].input_invalid").getText();
        assertEquals("Я соглашаюсь с условиями обработки и использования моих персональных данных", text.trim());
    }
}
