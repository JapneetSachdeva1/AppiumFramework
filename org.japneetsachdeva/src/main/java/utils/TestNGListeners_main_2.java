package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.appium.java_client.android.AndroidDriver;
import lombok.SneakyThrows;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static utils.Commons.takeScreenshot;
import static utils.ExtentReportHelper.getReportObject;

public class TestNGListeners_main_2 implements ITestListener
{
    //int id =0;
    private static ExtentReports extentReports;
    static {
        try {
            extentReports = getReportObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   // private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public AndroidDriver driver = null;
    //ThreadLocal to generate the report for parallel execution
    private final ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();
    ExtentTest logger;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");


    public void onTestStart(ITestResult result)
    {
        logger = extentReports.createTest(result.getMethod().getMethodName(), "<b> <i> Description: " + result.getMethod().getDescription() + "</i> </b>");

        logger.assignCategory("Regression");
        //extentTestThreadLocal.set(logger);
        logger.log(Status.INFO, "<b> Automation start Time: </b>"+ dtf.format(LocalDateTime.now()));

        //extentTestThreadLocal.get().log(Status.INFO, "Test description: "+result.getMethod().getDescription());
        //removeDuplicateTest();
    }

    public void removeDuplicateTest()
    {
         }


    @SneakyThrows
    public void onTestSuccess(ITestResult result)
    {
        logger.log(Status.PASS, MarkupHelper.createLabel("Test completed successfully!", ExtentColor.GREEN));
        //removeDuplicateTest();
    }


    @SneakyThrows
    public void onTestFailure(ITestResult result)
    {
        //TODO: adds a comment to the report that test got failed
        logger.log(Status.FAIL, MarkupHelper.createLabel("Test Failed! ", ExtentColor.RED));

        //TODO: Adds screenshot in base64 format
        try{
            driver = (AndroidDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());

            logger.log(Status.INFO, "Driver: "+ driver);
            }
        catch (Exception e)
        {
            e.printStackTrace();
        }

         String path = takeScreenshot(driver,result.getMethod().getMethodName());
        //TODO: Adds a comment in the report with the reason of the test failure
        logger.fail(MediaEntityBuilder.createScreenCaptureFromBase64String("base64" +path).build());
        logger.log(Status.FAIL, result.getThrowable());
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
        logger.log(Status.INFO, "<b> Automation end time: </b>"+ dtf.format(LocalDateTime.now()));

        extentReports.flush();
    }
}
