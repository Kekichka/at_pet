package at.task11;

import at.task11.SignUpPage;

public class SignUpBO {

    private final SignUpPage signUpPage = new SignUpPage();

    public String registerNewUser(String username, String password) {

        signUpPage.openSignUpModal();
        signUpPage.enterUsername(username);
        signUpPage.enterPassword(password);
        signUpPage.clickSignUpButton();

        return signUpPage.getAlertMessage(); // success or failure message
    }
}
