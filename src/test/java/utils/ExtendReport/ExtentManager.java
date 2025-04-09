package utils.ExtendReport;

import com.relevantcodes.extentreports.ExtentReports;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ExtentManager {

    private static ExtentReports extent;

    public synchronized static ExtentReports getReporter(String environment) {
        if (extent == null) {
            initializeReporter(environment);
        }
        return extent;
    }

    public synchronized static ExtentReports getReporter() {
        if (extent == null) {
            initializeReporter("Default Environment");
        }
        return extent;
    }

    private static void initializeReporter(String enviroment) {
        DateFormat df = new SimpleDateFormat("dd_MMM_yyyy-HH_mm_ss");
        String workingDir = System.getProperty("user.dir");
        String reportPath = workingDir + File.separator + "Reports";
        File reportDir = new File(reportPath);
        if (!reportDir.exists()) {
            reportDir.mkdirs();
        }
        String reportFilePath = reportPath + File.separator + "Results_" + df.format(System.currentTimeMillis()) + ".html";
        extent = new ExtentReports(reportFilePath);
        extent.addSystemInfo("Environment", enviroment);
        extent.loadConfig(new File("utils/ExtendReport/extent-config.xml"));
    }


}
