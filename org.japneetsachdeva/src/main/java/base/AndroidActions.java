package base;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Commons;

import java.time.Duration;

public class AndroidActions extends Commons
{
    AndroidDriver driver;

    public AndroidActions(AndroidDriver androidDriver)
    {
        driver = androidDriver;
    }

    public void scroller(String scrollTill)
    {
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector())." +
                "scrollIntoView(text(\""+scrollTill+"\"));"));
    }

    public void attributeContainsWait()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title"))
                ,"text", "Cart"));
    }
}
