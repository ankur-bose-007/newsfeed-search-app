import { browser, protractor } from "protractor";
import { login } from "./login.po";

describe('login tests',()=>{
    let page:login;
    beforeEach(()=>{
        page=new login();
        browser.get('/');
    });
    it('inside login page',()=>{
        expect(browser.getCurrentUrl()).toEqual('http://localhost:4200/');
    });
    it('navigate to signup',()=>{
        page.navigateToSignup();
        expect(browser.getCurrentUrl()).toEqual('http://localhost:4200/signup');
    });
    it('successful login for user',()=>{
        page.setEmail('jeet.ankur007@gmail.com');
        page.setPassword('Password@10');

        page.login();

        browser.wait(protractor.ExpectedConditions.alertIsPresent(),4000);
        expect(browser.switchTo().alert().getText()).toEqual('Successfully logged in');
        browser.switchTo().alert().accept();

        expect(browser.getCurrentUrl()).toEqual('http://localhost:4200/navbar/Pageuser');
    });
    it('successful login for admin',()=>{
        page.setEmail('bose.ankur007@gmail.com');
        page.setPassword('Password@10');

        page.login();

        browser.wait(protractor.ExpectedConditions.alertIsPresent(),4000);
        expect(browser.switchTo().alert().getText()).toEqual('Successfully logged in');
        browser.switchTo().alert().accept();

        expect(browser.getCurrentUrl()).toEqual('http://localhost:4200/Pageadmin');
    });
    it('user doesn\'t exist login',()=>{
        page.setEmail('sourav.ghosh10@mail.com ');
        page.setPassword('Password@10');

        page.login();

        browser.wait(protractor.ExpectedConditions.alertIsPresent(),4000);
        expect(browser.switchTo().alert().getText()).toEqual('User doesn\'t exist');
        browser.switchTo().alert().accept();

        expect(browser.getCurrentUrl()).toEqual('http://localhost:4200/');
    });
    it('wrong credentials',()=>{
        page.setEmail('bose.ankur007@gmail.com');
        page.setPassword('Password@11');

        page.login();

        browser.wait(protractor.ExpectedConditions.alertIsPresent(),4000);
        expect(browser.switchTo().alert().getText()).toEqual('Wrong credentials');
        browser.switchTo().alert().accept();

        expect(browser.getCurrentUrl()).toEqual('http://localhost:4200/');
    });
});