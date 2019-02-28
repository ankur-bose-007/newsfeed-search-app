import { admin } from "./admin.po";
import { browser, protractor, element, by } from "protractor";

describe('admin test',()=>{
    let page:admin;
    beforeEach(()=>{
        browser.get('/');
        page=new admin();
        page.login();
    });
    it('inside admin page',()=>{
        expect(browser.getCurrentUrl()).toEqual('http://localhost:4200/Pageadmin');
    });
    it('blacklist user',()=>{
        page.blacklist();
        browser.wait(protractor.ExpectedConditions.alertIsPresent(),4000);
        expect(browser.switchTo().alert().getText()).toEqual('User Blacklisted');
        browser.switchTo().alert().accept();
        expect(browser.getCurrentUrl()).toEqual('http://localhost:4200/Pageadmin');
    });
    it('search user',()=>{
        page.search('jeet');
        expect(element(by.css('table')).all(by.css('button')).first().isPresent()).toBeTruthy();
    })
    it('logout',()=>{
        page.logout().click();
        expect(browser.getCurrentUrl()).toEqual('http://localhost:4200/');
    })
});