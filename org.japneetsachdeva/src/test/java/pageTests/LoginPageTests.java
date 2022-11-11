package pageTests;


import base.AndroidActions;
import io.appium.java_client.android.Activity;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import pageObjects.FormPage;
import test_configuration.BaseTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Listeners(utils.TestNGListeners_main.class)

public class LoginPageTests extends BaseTest
{
    //TODO: PageObjects
    FormPage formPage;
    AndroidActions androidActions;


    @BeforeMethod (alwaysRun = true)
    public void setupActivity()
    {
        //TODO: to re-load the activty once your actions are completed
        driver.startActivity(new Activity("com.androidsample.generalstore","com.androidsample.generalstore.MainActivity"));

        formPage = new FormPage(driver);
        androidActions = new AndroidActions(driver);
    }

    @Test(dataProvider = "getDataPositiveLogin", description = "Testing positive login scenario", priority = 1, groups = {"Regression"},invocationCount = 1)
    public void loginPositiveTest(HashMap<String,String> input)
    {
        staticWait(5000);
        formPage.setUserName(input.get("name"));
        staticWait(2000);
        driver.hideKeyboard();
        //formPage.setGender();
        formPage.clickDropDownBtn();
        androidActions.scroller(input.get("country_name"));
        formPage.selectCountry(input.get("country_name"));
        formPage.clickLoginBtn();
        staticWait(5000);
    }

    @Test(dataProvider = "getDataNegativeLogin", description = "Testing negative login scenario", priority = 2,groups = {"Regression"},invocationCount = 1)
    public void loginNegativeTest(HashMap<String,String> input)
    {
        staticWait(5000);
        //formPage.setUserName(input.get("name"));
        staticWait(2000);
        //driver.hideKeyboard();
        //formPage.setGender();
        formPage.clickDropDownBtn();
        androidActions.scroller(input.get("country_name"));
        formPage.selectCountry(input.get("country_name"));
        formPage.clickLoginBtn();
         String text =  driver.findElement(By.xpath("//android.widget.Toast")).getAttribute("name");
         System.out.println(text);
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

    @DataProvider
    public Object[][] getDataNegativeLogin() throws IOException
    {
        List<HashMap<String, String>> data = getjsonData(System.getProperty("user.dir") + "/src/test/java/resources/ecom_login_data.json");

        return new Object[][]
                {
                        {data.get(1)}
                };
    }
}
