package task_2;

import TestBase.TestBase;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class AdminLoginTest extends TestBase {
    @Test
    public void loginTest() {
        driver.get("http://localhost/litecart/admin/login.php");
        driver.findElement(By.cssSelector("[name=\"username\"]")).sendKeys("admin");
        driver.findElement(By.cssSelector("[name=\"password\"]")).sendKeys("admin");
        driver.findElement(By.cssSelector("[name=\"login\"]")).click();
        Assert.assertTrue("Что-то пошло не так, авторизация прошла не успешна",
                driver.findElement(By.cssSelector("[title=\"Logout\"]")).isDisplayed());
    }
}
