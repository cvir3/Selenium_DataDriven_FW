package utils.ExtendReport;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ExtentTestManager {

    private static final Map<Long, ExtentTest> extentTestMap = new ConcurrentHashMap<>();
    private static final ExtentReports extent = ExtentManager.getReporter();

    private ExtentTestManager() {
        // Private constructor to prevent instantiation
    }

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get(Thread.currentThread().getId());
    }

    public static synchronized void endTest() {
        ExtentTest test = extentTestMap.get(Thread.currentThread().getId());
        if (test != null) {
            extent.endTest(test);
            extentTestMap.remove(Thread.currentThread().getId()); // Clean up after test ends
        }
    }

    public static synchronized ExtentTest startTest(String testName) {
        return startTest(testName, "");
    }

    public static synchronized ExtentTest startTest(String testName, String description) {
        ExtentTest test = extent.startTest(testName, description);
        extentTestMap.put(Thread.currentThread().getId(), test);
        return test;
    }

    public static void logPass(String message) {
        ExtentTest test = getTest();
        if (test != null) {
            test.log(LogStatus.PASS, message);
        }
    }

    public static void logFail(String message) {
        ExtentTest test = getTest();
        if (test != null) {
            test.log(LogStatus.FAIL, message);
        }
    }

    public static void logInfo(String message) {
        ExtentTest test = getTest();
        if (test != null) {
            test.log(LogStatus.INFO, message);
        }
    }
}
