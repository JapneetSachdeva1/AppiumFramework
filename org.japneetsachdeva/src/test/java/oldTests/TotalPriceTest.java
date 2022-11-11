package oldTests;

import base.AndroidActions;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.FormPage;
import test_configuration.BaseTest;

import java.time.Duration;

public class TotalPriceTest extends BaseTest
{
    FormPage formPage = new FormPage(driver);
    AndroidActions androidActions;
    @Test
    public void testTotalPriceOfItemsInCart() throws InterruptedException {
        Thread.sleep(5000);
       formPage.setUserName("Japneet Sachdeva");
        Thread.sleep(2000);
        driver.hideKeyboard();
        formPage.setGender();
        formPage.clickLoginBtn();


        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector())." +
                "scrollIntoView(text(\"Argentina\"));"));

        driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        // String text =  driver.findElement(By.xpath("//android.widget.Toast")).getAttribute("name");
        // System.out.println(text);
        Thread.sleep(5000);

        //TODO: Now Routed to Products List Page

        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector())." +
                "scrollIntoView(text(\"Jordan 6 Rings\"));"));

        driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(0).click();
        driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(1).click();

        Thread.sleep(4000);
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

        //TODO: Routed to cart page

        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title"))
                ,"text", "Cart"));

        String price1 = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(0).getText();
        String price2 = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(1).getText();

        String [] price1_split = price1.split("\\$");
        String[] price2_split = price2.split("\\$");

        double finalPrice1 = Double.parseDouble(price1_split[1]);
        double finalPrice2 = Double.parseDouble(price2_split[1]);

        double sum = finalPrice1+finalPrice2;

        System.out.println(sum);

       String totalPrice =  driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
        System.out.println(totalPrice);

        String[] totalPrice_split = totalPrice.split("\\$");
        double totalPriceFinal = Double.parseDouble(totalPrice_split[1]);

        System.out.println(totalPriceFinal);

        Assert.assertEquals(totalPriceFinal,sum);

        longPressGesture(driver.findElement(By.id("com.androidsample.generalstore:id/termsButton")));

        Thread.sleep(1000);

        driver.findElement(By.id("android:id/button1")).click();

        Thread.sleep(1000);

        driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();

        Thread.sleep(1000);

        driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
        Thread.sleep(9000);

        //TODO: Helps to find Context name for WebView
//        Set<String> contexts = driver.getContextHandles();
//        Iterator<String> iterator = contexts.iterator();
//
//        while(iterator.hasNext())
//        {
//            System.out.println(contexts);
//            iterator.next();
//        }


        driver.context("WEBVIEW_com.androidsample.generalstore");
        driver.findElement(By.name("q")).sendKeys("Japneet Sachdeva LinkedIn", Keys.ENTER);

        Thread.sleep(15000);

        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.context("NATIVE_APP");

        Thread.sleep(4000);



    }
}
