import { by, element } from "protractor";

export class login {
    setEmail(emailId: string) {
        element(by.id('InputEmail')).clear();
        element(by.id('InputEmail')).sendKeys(emailId);
    }
    setPassword(password: string) {
        element(by.id('InputPassword')).clear();
        element(by.id('InputPassword')).sendKeys(password);
    }
    login() {
        element(by.id('submit')).click();
    }
    navigateToSignup() {
        element(by.id('navigate_signup')).click();
    }
}