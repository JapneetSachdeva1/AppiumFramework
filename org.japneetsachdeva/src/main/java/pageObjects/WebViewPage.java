package pageObjects;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class WebViewPage
{
    AndroidDriver driver;

    //TODO: Locators
    private By googleSearch = By.name("q");

    public WebViewPage(AndroidDriver androidDriver)
    {
        driver = androidDriver;
    }

    public void setGoogleSearch()
    {
        driver.findElement(googleSearch).sendKeys("Japneet Sachdeva LinkedIn", Keys.ENTER);
    }
}
