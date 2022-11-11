package pageObjects;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class ProductListPage
{
    AndroidDriver driver;
    //TODO: Locators
    private final By productCartBtn = By.id("com.androidsample.generalstore:id/productAddCart");
    private final By cartBtn = By.id("com.androidsample.generalstore:id/appbar_btn_cart");

    public ProductListPage(AndroidDriver androidDriver)
    {
        driver = androidDriver;
    }

    public void addProductToCart(int index)
    {
        driver.findElements(productCartBtn).get(index).click();
    }

    public CartPage goToCart()
    {
        driver.findElement(cartBtn).click();
        return new CartPage(driver);
    }

    public void setCartBtn()
    {
        driver.findElement(cartBtn).click();
    }
}
