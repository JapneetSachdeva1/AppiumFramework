package test_configuration;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import utils.Commons;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class BaseTest extends Commons {
    public AndroidDriver driver;
    public AppiumDriverLocalService serviceBuilder;
    public Properties properties;


    @BeforeClass(alwaysRun = true)
    public void setUpAppium() throws IOException
    {
        properties = loadProperty();
        String ipAddress = System.getProperty("ipAddress") != null ? System.getProperty("idAddress") : properties.getProperty("ipAddress");
        String portNumber = System.getProperty("portNumber") != null ? System.getProperty("portNumber") : properties.getProperty("portNumber");
        serviceBuilder = startAppiumServer(ipAddress, Integer.parseInt(portNumber));

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(properties.getProperty("deviceName"));
        options.setSystemPort(8200);
        options.setChromedriverExecutable("C:\\Users\\Japneet Sachdeva\\Downloads\\appium_chrome_driver_2\\chromedriver.exe");
        options.setApp(System.getProperty("user.dir") + "\\src\\test\\java\\resources\\General-Store.apk");
        driver = new AndroidDriver(serviceBuilder.getUrl(), options);

    }


    public void longPressGesture(WebElement element)
    {
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(),
                        "duration", 2000));
    }



    @AfterClass(alwaysRun = true)
    public void tearDown() {

        driver.quit();
        serviceBuilder.stop(); //will stop the server on port 4723
    }
}
