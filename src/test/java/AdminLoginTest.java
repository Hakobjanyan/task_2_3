import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AdminLoginTest {

    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void loginTest() {
        driver.get("http://localhost/litecart/admin/login.php");
        driver.findElement(By.cssSelector("[name=\"username\"]")).sendKeys("admin");
        driver.findElement(By.cssSelector("[name=\"password\"]")).sendKeys("admin");
        driver.findElement(By.cssSelector("[name=\"login\"]")).click();
        Assert.assertTrue("Что-то пошло не так, авторизация прошла не успешна",
                driver.findElement(By.cssSelector("[title=\"Logout\"]")).isDisplayed());
    }

    @After
    public void stop() {
        if(this.driver!=null)
            driver.quit();
    }
}
