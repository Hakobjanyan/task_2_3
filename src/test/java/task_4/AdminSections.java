package task_4;

import TestBase.TestBase;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class AdminSections extends TestBase {

    @Test
    public void testAssertSection() {
        login();
        ArrayList<String> sectionList = sectionList();
        for (String str : sectionList) {
            getSection(str).click();
            Assert.assertTrue(isElementPresent(By.xpath("//h1")));

            ArrayList<String> underSectionName = underSectionList(getSection(str));
            if (underSectionName.size() > 0) {
                for (String el : underSectionName) {
                    getUnderSection(getSection(str), el).click();
                    Assert.assertTrue(isElementPresent(By.xpath("//h1")));
                }
            }
        }
    }

    public void login() {
        driver.get("http://localhost/litecart/admin/login.php");
        driver.findElement(By.cssSelector("[name=\"username\"]")).sendKeys("admin");
        driver.findElement(By.cssSelector("[name=\"password\"]")).sendKeys("admin");
        driver.findElement(By.cssSelector("[name=\"login\"]")).click();
        Assert.assertTrue("Что-то пошло не так, авторизация прошла не успешна",
                driver.findElement(By.cssSelector("[title=\"Logout\"]")).isDisplayed());
    }

    private WebElement getSection(String sectionName) {
        return driver.findElement(By.xpath("//li[@id='app-']//a[./*[text()='" + sectionName + "']]"));
    }

    private WebElement getUnderSection(WebElement section, String underSectionName) {
        return section.findElement(By.xpath("..//li[.//*[text()='" + underSectionName + "']]"));
    }

    private ArrayList<String> sectionList() {
        List<WebElement> section = driver.findElements(By.xpath("//li[@id='app-']//span[@class='name']"));
        ArrayList<String> sectionName = new ArrayList<>();
        for (WebElement el : section)
            sectionName.add(el.getText());
        return sectionName;
    }

    private ArrayList<String> underSectionList(WebElement webElement) {
            List<WebElement> underSection = webElement.findElements(By.xpath("..//li"));
            ArrayList<String> underSectionName = new ArrayList<>();
            for (WebElement el : underSection)
                underSectionName.add(el.getText());
            return underSectionName;
    }
}
