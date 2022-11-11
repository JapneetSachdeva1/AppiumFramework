package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
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

public class TestNGListeners_main implements ITestListener
{
    //int id =0;
    private final ExtentReports extentReports = getReportObject();

   // private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public AndroidDriver driver = null;
    //ThreadLocal to generate the report for parallel execution
    private ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();


    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

    public TestNGListeners_main() throws IOException {
    }


    public void onTestStart(ITestResult result)
    {
        final ExtentTest logger = extentReports.createTest(result.getMethod().getMethodName(), "<b> <i> Description: " + result.getMethod().getDescription() + "</i> </b>");

        logger.assignCategory("Regression");
        extentTestThreadLocal.set(logger);
        extentTestThreadLocal.get().log(Status.INFO, "<b> Automation start Time: </b>"+ dtf.format(LocalDateTime.now()));

        //extentTestThreadLocal.get().log(Status.INFO, "Test description: "+result.getMethod().getDescription());
        //removeDuplicateTest();
    }

    public void removeDuplicateTest()
    {
         }


    @SneakyThrows
    public void onTestSuccess(ITestResult result)
    {
        extentTestThreadLocal.get().log(Status.PASS, MarkupHelper.createLabel("Test completed successfully!", ExtentColor.GREEN));
        //removeDuplicateTest();
    }


    @SneakyThrows
    public void onTestFailure(ITestResult result)
    {
        //TODO: adds a comment to the report that test got failed
        extentTestThreadLocal.get().log(Status.FAIL, MarkupHelper.createLabel("Test Failed! ", ExtentColor.RED));

        //TODO: Adds screenshot in base64 format
        try{
            driver = (AndroidDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());

            extentTestThreadLocal.get().log(Status.INFO, "Driver: "+ driver);
            }
        catch (Exception e)
        {
            e.printStackTrace();
        }

         String path = takeScreenshot(driver,result.getMethod().getMethodName());
        //TODO: Adds a comment in the report with the reason of the test failure
        extentTestThreadLocal.get().fail(MediaEntityBuilder.createScreenCaptureFromBase64String("base64" +path).build());
        extentTestThreadLocal.get().log(Status.FAIL, result.getThrowable());
        //removeDuplicateTest();
    }


    public void onTestSkipped(ITestResult result) {
    }


    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // not implemented
    }

    @Override
    public void onStart(ITestContext iTestContext)
    {
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
