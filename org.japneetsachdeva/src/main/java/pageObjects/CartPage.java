package pageObjects;

import base.AndroidActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class CartPage extends AndroidActions
{
    AndroidDriver driver;
    //TODO: Locators
    private By priceTag = By.id("com.androidsample.generalstore:id/productPrice");
    private By totalPrice = By.id("com.androidsample.generalstore:id/totalAmountLbl");
    private By closeBtnForPopUp = By.id("android:id/button1");
    private AppiumBy checkBox = (AppiumBy) AppiumBy.className("android.widget.CheckBox");
    private By proccedBtn = By.id("com.androidsample.generalstore:id/btnProceed");

    public CartPage(AndroidDriver androidDriver)
    {
        super(androidDriver);
        driver = androidDriver;
    }

    public String getProductPrice(int index)
    {
        return driver.findElements(priceTag).get(index).getText();
    }

    public String totalDisplayedPrice()
    {
        return driver.findElement(totalPrice).getText();
    }

    public void closePopUp()
    {
        driver.findElement(closeBtnForPopUp).click();
    }

    public void selectAgreeTermsBtn()
    {
        driver.findElement(checkBox).click();
    }

    public WebViewPage clickProceedBtnToOpenWebView()
    {
        driver.findElement(proccedBtn).click();
        return new WebViewPage(driver);
    }


}
