package tipka.qa;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tipka.qa.helpers.TestBase;
import tipka.qa.pages.RegistrationPage;

import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;
import static java.lang.Integer.parseInt;
import static tipka.qa.pages.TestData.*;

@Tag("properties")
public class RegistrationFormTest extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    Faker faker = new Faker();
    String name, surname,email, day,month,year,address;

    @Test
    void shouldPass() {
        Assertions.assertTrue(true);
    }

    @Test
    void shouldPassToo() {
        Assertions.assertTrue(true);
    }

    @Test
    void shouldNotPass() {
        Assertions.assertTrue(false);
    }

    @Test
    void fillFormTest() {

        step("Open students registration form: ", () -> {
            registrationPage.openPage();
        });

        step("Fill student credentials: ", () -> {
            name = faker.name().firstName();
            registrationPage.typeName(name);
            surname = faker.name().lastName();
            registrationPage.typeSurname(surname);
            email = faker.internet().emailAddress();
            registrationPage.typeEmail(email);
            registrationPage.setGender(gender);
            registrationPage.typePhone(phone);
        });

        step("Set birth date: ", () -> {
            String  birthDate = someBloodyMagicWithDate();
            day = birthDate.substring(0,2);
            month = andSomeBloodyMagicWithMonth(birthDate.substring(3,5));
            year = birthDate.substring(6);
            registrationPage.calendar.setBirthDate(day, month, year);
        });

        step("Set subjects: ", () -> {
            registrationPage.setSubject(subjectOne);
            registrationPage.setSubject(subjectTwo);
        });

        step("Set hobbies: ", () -> {
            registrationPage.setHobby(hobbySport);
            registrationPage.setHobby(hobbyReading);
        });

        step("Upload student photo: ", () -> {
            registrationPage.uploadPicture(filePath + fileName);
        });

        step("Set address: ", () -> {
            address = faker.shakespeare().asYouLikeItQuote();
            registrationPage.typeAddress(address);
            registrationPage.setState(state);
            registrationPage.setCity(city);
        });

        step("Submit: ", () -> {
            $("#submit").click();
        });

        step("Verify successful form submit", () -> {
            $(".modal-title").shouldHave(text(header));
            registrationPage.checkResultValue("Student Name", (name + " " + surname))
                    .checkResultValue("Student Email", (email))
                    .checkResultValue("Gender", (gender))
                    .checkResultValue("Mobile", (phone))
                    .checkResultValue("Date of Birth", (day + " " + month +  "," + year))
                    .checkResultValue("Subjects", (subjectOne + ", " + subjectTwo))
                    .checkResultValue("Hobbies", (hobbySport + ", " + hobbyReading))
                    .checkResultValue("Picture", (fileName))
                    .checkResultValue("Address", (address))
                    .checkResultValue("State and City", (state + " " + city));
        });
    }

    private String someBloodyMagicWithDate() {
        return  (new SimpleDateFormat("dd.MM.yyyy"))
                .format(faker
                        .date()
                        .birthday(15,35));
    }

    private String andSomeBloodyMagicWithMonth(String monthAsNumber) {
        String monthAsWord = Month
                .of(parseInt(monthAsNumber))
                .toString();
        return monthAsWord.charAt(0)
                + monthAsWord.substring(1).toLowerCase(Locale.ROOT);
    }


}
