package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentReportHelper
{
    public static ExtentReports reports;
    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yyyy\\hh_mm_ss");

    public static ExtentReports getReportObject() throws IOException {
        //String reportPath = "./reports/index.html";
       // String reportPath = "./reports/";
        File file = new File(System.getProperty("user.dir")+"/reports/"+dtf.format(LocalDateTime.now()));
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(file);

        sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setReportName("Automation Results");
        sparkReporter.config().setDocumentTitle("Test Results");
        sparkReporter.config().setJs("document.getElementsByClassName('col-sm-12 col-md-4')[0].style.setProperty('min-inline-size','-webkit-fill-available');");
        sparkReporter.config().setCss(".badge.pass-bg.log.float-right{background-color:#38CC77}");
        sparkReporter.config().setCss(".text-pass{font-size:18px;font-weight:600;}");
        sparkReporter.config().setCss(".badge-primary{background-color:#E07C24;}");
        sparkReporter.config().setCss(".pass-bg{background-color:#1FAA59;}");
        //sparkReporter.config().setCss(".test-content.scrollable.ps-container.ps-theme-default.open{background-color:#0D0D0D;}");

        reports = new ExtentReports();
        reports.attachReporter(sparkReporter);

        reports.setSystemInfo("Tester is: ", "Japneet Sachdeva");
        //Desktop.getDesktop().browse(file.toURI());
        return  reports;
    }

}
