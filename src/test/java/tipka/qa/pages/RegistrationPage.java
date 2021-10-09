package tipka.qa.pages;

import com.codeborne.selenide.SelenideElement;
import tipka.qa.pages.component.CalendarComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage {

    private SelenideElement formTitle = $(".practice-form-wrapper"),
            nameInput = $("#firstName"),
            surnameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            phoneInput = $("#userNumber"),
            subjectSet = $("#subjectsInput"),
            genderSet = $("#genterWrapper"),
            hobbySet = $("#hobbiesWrapper"),
            pictureUpload = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            stateClick =  $("#state"),
            cityClick = $("#city"),
            citySet = $("#stateCity-wrapper"),
            stateSet = $("#stateCity-wrapper");

    private final String FORM_TITLE = "Student Registration Form";

    public void setCity (String city) {
        cityClick.click();
        citySet.$(byText(city)).click();
    }

    public void setState(String state) {
        stateClick.click();
        stateSet.$(byText(state)).click();
    }

    public CalendarComponent calendar = new CalendarComponent();

    public void openPage() {
        open("/automation-practice-form");
        formTitle.shouldHave(text(FORM_TITLE));
    }

    public void typeName (String name) {
        nameInput.setValue(name);
    }

    public void typeSurname (String surname) {
        surnameInput.setValue(surname);
    }

    public void typeEmail (String email) {
        emailInput.setValue(email);
    }

    public void typePhone (String phone) {
        phoneInput.setValue(phone);
    }

    public void setSubject(String subject) {
        subjectSet.setValue(subject).pressEnter();
    }

    public void setGender (String gender) {
        genderSet.$(byText(gender)).click();
    }

    public void setHobby (String hobby) {
        hobbySet.$(byText(hobby)).click();
    }

    public void uploadPicture (String fileName) {
        pictureUpload.uploadFromClasspath(fileName);
    }

    public void typeAddress (String address) {
        addressInput.setValue(address).pressEnter();
    }

    public RegistrationPage checkResultValue(String field, String value) {
        $(".table-responsive").$(byText(field)).parent()
                .shouldHave(text(value));
        return this;
    }

}
