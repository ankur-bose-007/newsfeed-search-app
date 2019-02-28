import { element, by, browser, protractor } from "protractor";


export class admin {
    login() {
        element(by.id('InputEmail')).clear();
        element(by.id('InputEmail')).sendKeys('bose.ankur007@gmail.com');

        element(by.id('InputPassword')).clear();
        element(by.id('InputPassword')).sendKeys('Password@10');

        element(by.id('submit')).click();
        browser.wait(protractor.ExpectedConditions.alertIsPresent(), 4000);
        browser.switchTo().alert().accept();
    }
    blacklist() {
        let div = element(by.css('table'));
        let blockelement = div.all(by.css('button')).first();
        if (blockelement.isPresent())
            return blockelement.click();
    }
    search(username: string) {
        element(by.id('searchText')).clear();
        element(by.id('searchText')).sendKeys(username);

        element(by.id('searchSubmit')).click();
    }
    logout() {
        return element(by.id('logout'));
    }
}