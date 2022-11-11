package oldTests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import test_configuration.MobileBrowserBaseTest;

public class mobileBrowserTest1 extends MobileBrowserBaseTest
{

    @Test
    public void testBrowser() throws InterruptedException
    {
        driver.get("https://rahulshettyacademy.com/angularAppdemo");
        driver.findElement(By.xpath("//span[@class='navbar-toggler-icon']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[@routerlink='/products']")).click();
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)","");
        Thread.sleep(2000);
        String courseName = driver.findElement(By.xpath("//a[text()='Devops']")).getText();
        Thread.sleep(2000);
        Assert.assertEquals(courseName, "Devops");
        Thread.sleep(5000);
    }
}
