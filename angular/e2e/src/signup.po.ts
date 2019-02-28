import { element, by } from "protractor";

export class signup {
    navigateToLogin() {
        element(by.id("navigate_login")).click();
    }
    setEmail(emailId: string) {
        element(by.id('InputEmail')).clear();
        element(by.id('InputEmail')).sendKeys(emailId);
    }
    setPassword(password: string) {
        element(by.id('InputPassword')).clear();
        element(by.id('InputPassword')).sendKeys(password);
    }
    setUsername(username: string) {
        element(by.id('InputUsername')).clear();
        element(by.id('InputUsername')).sendKeys(username);
    }
    signup() {
        element(by.id('submit_signup')).click();
    }
}