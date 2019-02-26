import { browser, protractor,element, by } from "protractor";
import { User } from "./user.po";


describe('user tests',()=>{
    let page:User;
    beforeEach(()=>{
        browser.get('/');
        page=new User();
        page.login();
    });

    it('inside user page',()=>{
        expect(browser.getCurrentUrl()).toEqual('http://localhost:4200/navbar/Pageuser');
    });

    it('navigate to search history',()=>{
        page.navigateToSearchHistory();
        expect(browser.getCurrentUrl()).toEqual('http://localhost:4200/navbar/searchHistory');
    });

    it('navigate to news',()=>{
        page.navigateToSearchHistory();
        page.navigateToNews();
        expect(browser.getCurrentUrl()).toEqual('http://localhost:4200/navbar/Pageuser');
    });

    it('news search',()=>{
        element(by.id('searchTab')).clear();
        element(by.id('searchTab')).sendKeys('dasdasda');

        element(by.id('searchBut')).click();
        browser.wait(protractor.ExpectedConditions.alertIsPresent(),4000);
        expect(browser.switchTo().alert().getText()).toEqual('search added');
        browser.switchTo().alert().accept();
    });


    it('search history present',()=>{
        page.navigateToSearchHistory();
        expect(page.searchTable().isPresent()).toBeTruthy();
    });

    it('delete search',()=>{
        page.navigateToSearchHistory();
        page.deleteSearch().click();
        browser.wait(protractor.ExpectedConditions.alertIsPresent(),4000);
        expect(browser.switchTo().alert().getText()).toEqual('record deleted');
        browser.switchTo().alert().accept();
    });

    it('logout',()=>{
        page.logout().click();
        expect(browser.getCurrentUrl()).toEqual('http://localhost:4200/');
    })
});