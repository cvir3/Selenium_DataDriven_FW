package utils;

import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.ExtendReport.ExtentManager;
import utils.ExtendReport.ExtentTestManager;

import java.io.File;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.time.Duration;
import java.util.Date;


public class BaseClass {

    public WebDriver webDriver;

    public static void takeSnapShot(WebDriver driver, String fileWithPath) throws Exception {
        TakesScreenshot scrShot = (TakesScreenshot) driver;
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File(fileWithPath);
        FileUtils.copyFile(SrcFile, DestFile);
        System.out.println("Screenshot taken successfully..");
    }

    @BeforeClass(alwaysRun = true)
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        webDriver = new ChromeDriver(chromeOptions);
        webDriver.get("https://www.embark.org/?ref=madewithvuejs.com");
        System.out.println("Page title is : -" + webDriver.getTitle());
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        ExtentTestManager.startTest("Method Name: " + method.getName());
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("TEST STARTED # " + method.getAnnotation(Test.class).description());
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    @AfterMethod
    public void afterMethod(ITestResult result, Method method) throws Exception {
        String fileName;
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        String testSRes;
        Date endDateTime;
        String testDescription;
        String dateEndInString;
        if (result.getStatus() == 2) {
            endDateTime = new Date();
            DateFormat shortDf = DateFormat.getDateTimeInstance(3, 3);
            testDescription = shortDf.format(endDateTime).replace("/", "_");
            testDescription = testDescription.replace(" ", "_");
            testDescription = testDescription.replace(":", "_");
            dateEndInString = "SC_error__" + testDescription;
            fileName = System.getProperty("user.dir") + "/Reports/Failure_Screenshots/" + dateEndInString + ".png";
            takeSnapShot(webDriver, fileName);
            ExtentTestManager.getTest().log(LogStatus.FAIL, "Error Screenshot" + ExtentTestManager.getTest().addScreenCapture("failure_screenshots\\" + dateEndInString + ".png"));
            ExtentTestManager.getTest().log(LogStatus.FAIL, result.getThrowable().getMessage());
            ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Failed");
            testSRes = "FAIL";
        } else if (result.getStatus() == 3) {
            ExtentTestManager.getTest().log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
            testSRes = "SKIP";
        } else {
            ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
            testSRes = "PASS";
        }

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("TEST COMPLETED # [ " + testSRes + " ] " + method.getAnnotation(Test.class).description());
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        ExtentManager.getReporter().endTest(ExtentTestManager.getTest());
        ExtentManager.getReporter().flush();
        ExtentTestManager.getTest().log(LogStatus.INFO, "Closed");
        Thread.sleep(2000);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
