package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class Commons
{
    public Properties properties;
    public AppiumDriverLocalService serviceBuilder;

    public Double getFormattedPrice(String amount,int index)
    {
        Double price = Double.parseDouble(amount.substring(index));
        return price;
    }

    public List<HashMap<String,String>> getjsonData(String jsonFilePath) throws IOException
    {
        //TODO: Convert json file content to JSON string
        String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {

        });
        return data;
    }

    public AppiumDriverLocalService startAppiumServer(String ipAddress, int portNumber)
    {
        serviceBuilder = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\Japneet Sachdeva\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress(ipAddress).usingPort(portNumber).build();
        serviceBuilder.start();
        return serviceBuilder;
    }

    public Properties loadProperty() throws IOException
    {
        properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Japneet Sachdeva\\Downloads\\org.japneetsachdeva-20221102T053351Z-001\\org.japneetsachdeva\\src\\main\\java\\resources\\config.properties");
        properties.load(fileInputStream);
        //properties.getProperty(propertyName);
        return properties;
    }

        public static String takeScreenshot(AppiumDriver driver, String testCaseName) throws IOException
    {

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String path = System.getProperty("user.dir")+"/screenshots/"+testCaseName+".png";

        FileUtils.copyFile(srcFile, new File(path));
        return path;

    }

    public void staticWait(int sleepTime)
    {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
