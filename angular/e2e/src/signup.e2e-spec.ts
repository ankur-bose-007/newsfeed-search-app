import { signup } from "./signup.po";
import { browser, protractor } from "protractor";

describe('tests for signup', () => {
    let page: signup;
    beforeEach(() => {
        page = new signup();
        browser.get('/signup');
    });
    it('navigate to login', () => {
        page.navigateToLogin();
        expect(browser.getCurrentUrl()).toEqual('http://localhost:4200/');
    });
    it('successfully registered', () => {
        page.setEmail('test' + Math.floor(Math.random() * 1000) + '@gmail.com');
        page.setPassword('Password@10');
        page.setUsername('test test');

        page.signup();
        browser.wait(protractor.ExpectedConditions.alertIsPresent(), 4000);
        expect(browser.switchTo().alert().getText()).toEqual('User registered Successfully');
        browser.switchTo().alert().accept();

        expect(browser.getCurrentUrl()).toEqual('http://localhost:4200/');
    });
    it('not registered', () => {
        page.setEmail('bose.ankur007@gmail.com');
        page.setPassword('Password@10');
        page.setUsername('test test');

        page.signup();
        browser.wait(protractor.ExpectedConditions.alertIsPresent(), 4000);
        expect(browser.switchTo().alert().getText()).toEqual('User already exists. Please Login using credentials.');
        browser.switchTo().alert().accept();

        expect(browser.getCurrentUrl()).toEqual('http://localhost:4200/signup');
    });
});