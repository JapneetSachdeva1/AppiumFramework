package pageTests;

import base.AndroidActions;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.CartPage;
import pageObjects.FormPage;
import pageObjects.ProductListPage;
import pageObjects.WebViewPage;
import test_configuration.BaseTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Listeners(utils.TestNGListeners_main.class)

public class E2ETest extends BaseTest
{
    FormPage formPage;
    AndroidActions androidActions;
    ProductListPage productListPage;
    WebViewPage webViewPage;
    CartPage cartPage;

//    @AfterMethod
//    public void setupActivity()
//    {
//        //TODO: to re-load the activty once your actions are completed
//        driver.startActivity(new Activity("com.androidsample.generalstore","com.androidsample.generalstore.MainActivity"));
//    }


    @Test(dataProvider = "getData", groups = {"Smoke"})
    public void testTotalPriceOfItemsInCart(HashMap<String,String> input) throws InterruptedException {

     formPage = new FormPage(driver);
     androidActions = new AndroidActions(driver);
    // productListPage = new ProductListPage(appiumDriver);

        Thread.sleep(5000);
       formPage.setUserName(input.get("name"));
        Thread.sleep(2000);
        driver.hideKeyboard();
        formPage.setGender();
        formPage.clickDropDownBtn();
        androidActions.scroller(input.get("country_name"));
        formPage.selectCountry(input.get("country_name"));
        productListPage = formPage.clickLoginBtn();
        // String text =  appiumDriver.findElement(By.xpath("//android.widget.Toast")).getAttribute("name");
        // System.out.println(text);
        Thread.sleep(5000);

        //TODO: Now Routed to Products List Page

        androidActions.scroller("Jordan 6 Rings");
        productListPage.addProductToCart(0);
        productListPage.addProductToCart(1);
        Thread.sleep(4000);
        cartPage =  productListPage.goToCart();
        //TODO: Routed to cart page

        Thread.sleep(2000);
        androidActions.attributeContainsWait();

        //double price1 = androidActions.getFormattedPrice(cartPage.totalDisplayedPrice(), 1);
        double price1 = cartPage.getFormattedPrice(cartPage.getProductPrice(0), 1);
        //double price2 = Double.parseDouble(cartPage.getProductPrice(1).substring(1));
        double price2 = cartPage.getFormattedPrice(cartPage.getProductPrice(1),1 );


        double sum = price1+price2;

        System.out.println(sum);

        double totalPrice =  Double.parseDouble(cartPage.totalDisplayedPrice().substring(1));

        Assert.assertEquals(totalPrice,sum);

        longPressGesture(driver.findElement(By.id("com.androidsample.generalstore:id/termsButton")));

        Thread.sleep(1000);

        cartPage.closePopUp();

        Thread.sleep(1000);

        cartPage.selectAgreeTermsBtn();

        Thread.sleep(1000);

        webViewPage = cartPage.clickProceedBtnToOpenWebView();
        Thread.sleep(9000);

        //TODO: Helps to find Context name for WebView
//        Set<String> contexts = appiumDriver.getContextHandles();
//        Iterator<String> iterator = contexts.iterator();
//
//        while(iterator.hasNext())
//        {
//            System.out.println(contexts);
//            iterator.next();
//        }


        driver.context("WEBVIEW_com.androidsample.generalstore");
        Thread.sleep(15000);

        webViewPage.setGoogleSearch();
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.context("NATIVE_APP");

        Thread.sleep(4000);
    }

//    @Test
//    public void testLogin() throws InterruptedException {
//        formPage = new FormPage(driver);
//        androidActions = new AndroidActions(driver);
//        // productListPage = new ProductListPage(appiumDriver);
//
//        Thread.sleep(5000);
//        formPage.setUserName("Singh");
//        Thread.sleep(2000);
//        driver.hideKeyboard();
//        formPage.setGender();
//        formPage.clickDropDownBtn();
//        productListPage = formPage.clickLoginBtn();
//    }


    @DataProvider
    public Object[][] getData() throws IOException {

        List<HashMap<String, String>> data = getjsonData(System.getProperty("user.dir") + "/src/test/java/resources/ecom_login_data.json");


//         return new Object[][]
//                {
//                        {
//                            "Japneet Sachdeva",
//                                "Argentina"
//                        },
//                        {
//                            "Rahul Shetty",
//                            "Macau"
//                        }
//                };
        //}
            return new Object[][]
                    {
                    {data.get(0)}, {data.get(1)}
            };
    }

}
