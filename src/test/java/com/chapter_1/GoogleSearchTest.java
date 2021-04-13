package com.chapter_1;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.jupiter.api.Assertions.*;


public class GoogleSearchTest {
    private WebDriver driver;
    private String url_google="https://www.google.com/";

    @BeforeEach
    public void setUP(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url_google);
    }

    @Test
    public void TestGoogleSearch(){
        WebElement element;
        element=driver.findElement(By.name("q"));
        element.clear();
        element.sendKeys("Selenium testing cookbook");
        element.submit();

        new WebDriverWait(driver,10).until(
                // 使用Boolean自动装箱而不是boolean是因为这里使用了泛型，必须传递对象
                (ExpectedCondition<Boolean>) wd -> wd.getTitle().toLowerCase().startsWith("selenium testing cookbook"));

        assertEquals("Selenium testing cookbook - Sök på Google", driver.getTitle());
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }
}
