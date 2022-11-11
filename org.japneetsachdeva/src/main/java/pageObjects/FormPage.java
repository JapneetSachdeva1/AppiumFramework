package pageObjects;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import java.text.Normalizer;

public class FormPage
{
    private AndroidDriver driver;

    //TODO: Locators
    private By usernameField = By.id("com.androidsample.generalstore:id/nameField");
    private By dropDownBtn = By.id("android:id/text1");
    private By radioFemaleBtn = By.id("com.androidsample.generalstore:id/radioFemale");
    private By country = By.xpath("//android.widget.TextView[@text='Argentina']");
    private By loginBtn = By.id("com.androidsample.generalstore:id/btnLetsShop");

    public FormPage(AndroidDriver androidDriver)
    {
        driver = androidDriver;
    }
    public void setUserName(String userName)
    {
        driver.findElement(usernameField).sendKeys(userName);
    }

    public void setGender()
    {
        driver.findElement(radioFemaleBtn).click();
    }

    public void clickDropDownBtn()
    {
        driver.findElement(dropDownBtn).click();
    }

    public void selectCountry(String countryName)
    {
        driver.findElement(By.xpath("//android.widget.TextView[@text='"+countryName+"']")).click();
    }

    public ProductListPage clickLoginBtn()
    {
        driver.findElement(loginBtn).click();
        return new ProductListPage(driver);
    }

}
