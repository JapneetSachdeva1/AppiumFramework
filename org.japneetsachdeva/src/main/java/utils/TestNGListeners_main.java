package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import lombok.SneakyThrows;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static utils.Commons.takeScreenshot;
import static utils.ExtentReportHelper.getReportObject;

public class TestNGListeners_main extends Commons implements ITestListener
{
    //int id =0;
    static ExtentReports extentReports;
    static {
        try {
            extentReports = getReportObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ExtentTest logger;
    public AndroidDriver driver = null;
    //ThreadLocal to generate the report for parallel execution
    public ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();


    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");


    public void onTestStart(ITestResult result)
    {
        logger = extentReports.createTest(result.getMethod().getMethodName());
        logger.assignAuthor("<b> <i> Japneet Sachdeva </i> </b>");
        extentTestThreadLocal.set(logger);
        extentTestThreadLocal.get().log(Status.INFO, "<b> Automation start Time: </b>"+ dtf.format(LocalDateTime.now()));

        try{
            driver = (AndroidDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        extentTestThreadLocal.get().log(Status.INFO, "Driver is: "+driver);
        extentTestThreadLocal.get().log(Status.INFO, "Test description: "+result.getMethod().getDescription());
    }


    @SneakyThrows
    public void onTestSuccess(ITestResult result)
    {
        extentTestThreadLocal.get().log(Status.PASS, MarkupHelper.createLabel("Test completed successfully!", ExtentColor.GREEN));
    }


    @SneakyThrows
    public void onTestFailure(ITestResult result)
    {
        //TODO: adds a comment to the report that test got failed
        extentTestThreadLocal.get().log(Status.FAIL, MarkupHelper.createLabel("Test Failed! ", ExtentColor.RED));

        //TODO: Adds screenshot in base65 format
        try{
            driver = (AndroidDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
            }
        catch (Exception e)
        {
            e.printStackTrace();
        }

         String path = takeScreenshot(driver,result.getMethod().getMethodName());
        //TODO: Adds a comment in the report with the reason of the test failure
        extentTestThreadLocal.get().fail(MediaEntityBuilder.createScreenCaptureFromBase64String("data:img/png;base64," +path).build());
        extentTestThreadLocal.get().log(Status.FAIL, result.getThrowable());
    }


    public void onTestSkipped(ITestResult result) {
    }


    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // not implemented
    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    public void onTestFailedWithTimeout(ITestResult result) {

    }

    public void onStart(ITestContext context, ITestResult result) {
        // not implemented
    }


    public void onFinish(ITestContext context)
    {
        extentTestThreadLocal.get().log(Status.INFO, "<b> Automation end time: </b>"+ dtf.format(LocalDateTime.now()));

        extentReports.flush();
    }
}
