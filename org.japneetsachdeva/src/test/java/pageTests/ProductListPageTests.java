package pageTests;

import base.AndroidActions;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageObjects.FormPage;
import pageObjects.ProductListPage;
import test_configuration.BaseTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Listeners(utils.TestNGListeners_main.class)

public class ProductListPageTests extends BaseTest
{
    //TODO: PageObjects
    FormPage formPage;
    AndroidActions androidActions;
    ProductListPage productListPage;
    //TODO: Assertions
    String cartToast = "Please add some product at first";

    @Test(dataProvider = "getDataPositiveLogin",description = "Validate the toast message for empty cart", priority = 1,groups = {"Regression"},invocationCount = 1)
    public void testEmptyCartToastMessage(HashMap<String,String> input)
    {
        formPage = new FormPage(driver);
        androidActions = new AndroidActions(driver);
        productListPage = new ProductListPage(driver);

        staticWait(5000);
        formPage.setUserName(input.get("name"));
        staticWait(2000);
        driver.hideKeyboard();
        formPage.setGender();
        formPage.clickDropDownBtn();
        androidActions.scroller(input.get("country_name"));
        formPage.selectCountry(input.get("country_name"));
        productListPage = formPage.clickLoginBtn();
        staticWait(5000);
        productListPage.setCartBtn();
        staticWait(1000);
        String text =  driver.findElement(By.xpath("//android.widget.Toast")).getAttribute("name");
        System.out.println(text);
        Assert.assertEquals(text,cartToast);
        staticWait(5000);
    }

    @DataProvider
    public Object[][] getDataPositiveLogin() throws IOException {

        List<HashMap<String, String>> data = getjsonData(System.getProperty("user.dir") + "/src/test/java/resources/ecom_login_data.json");

        return new Object[][]
                {
                        {data.get(0)}
                };
    }

}
