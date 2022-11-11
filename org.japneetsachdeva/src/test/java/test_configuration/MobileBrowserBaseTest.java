package test_configuration;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class MobileBrowserBaseTest
{
    public AndroidDriver driver;
    public AppiumDriverLocalService serviceBuilder;

    @BeforeMethod
    public void setUpAppium() throws MalformedURLException
    {
        serviceBuilder = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\Japneet\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress("127.0.0.1").usingPort(4723).build();
        serviceBuilder.start();

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("emulator");
        options.setChromedriverExecutable("C:\\Users\\Japneet\\Desktop\\chromeDriver_appium\\chromedriver.exe");
        options.setCapability("browserName", "Chrome");
         driver = new AndroidDriver(new URL("http://127.0.0.1:4723"),options);
    }

    @AfterMethod
    public void tearDown()
    {

        driver.quit();
        serviceBuilder.stop(); //will stop the server on port 4723
    }
}
