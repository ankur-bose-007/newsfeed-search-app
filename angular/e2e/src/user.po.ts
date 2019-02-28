import { by, element, protractor, browser } from "protractor";

export class User {
    login() {
        element(by.id('InputEmail')).clear();
        element(by.id('InputEmail')).sendKeys('jeet123@gmail.com');

        element(by.id('InputPassword')).clear();
        element(by.id('InputPassword')).sendKeys('Password@10');

        element(by.id('submit')).click();

        browser.wait(protractor.ExpectedConditions.alertIsPresent(), 4000);
        expect(browser.switchTo().alert().getText()).toEqual('Successfully logged in');
        browser.switchTo().alert().accept();
    }

    navigateToSearchHistory() {
        element(by.id('history')).click();
    }

    navigateToNews() {
        element(by.id('news')).click();
    }

    searchTable() {
        return element(by.id('searchTable'));
    }

    deleteSearch() {
        return element(by.id('searchTable')).all(by.id('del')).first();
    }

    logout() {
        return element(by.id('logout'));
    }
}