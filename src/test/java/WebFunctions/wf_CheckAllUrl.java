package WebFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BaseClass;
import utils.ExtendReport.ExtentTestManager;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class wf_CheckAllUrl extends BaseClass {

    private int workingCount = 0;
    private int brokenCount = 0;
    private int invalidCount = 0;
    private int nullOrEmptyCount = 0;

    public wf_CheckAllUrl(WebDriver remoteDriver) {
        this.webDriver = remoteDriver;
    }

    public static void checkLink(String linkUrl) {
        try {
            URL url = new URL(linkUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode >= 200 && responseCode < 400) {
                System.out.println(linkUrl + " --> Working (Status: " + responseCode + ")");
                ExtentTestManager.logPass(linkUrl + " --> Working (Status: " + responseCode + ")");
            } else {
                System.out.println(linkUrl + " --> Broken (Status: " + responseCode + ")");
                ExtentTestManager.logFail(linkUrl + " --> Broken (Status: " + responseCode + ")");
            }
        } catch (Exception e) {
            System.out.println(linkUrl + " -->  Invalid URL or Connection Failed. " + e.getMessage());
            ExtentTestManager.logWarning(linkUrl + " -->  Invalid URL or Connection Failed. " + e.getMessage());

        }
    }

    public void checkAllUrl() throws InterruptedException {
        List<WebElement> links = webDriver.findElements(By.tagName("a"));
        System.out.println("Total number of links: " + links.size());
        ExtentTestManager.logInfo("Total number of <a> links found on page: " + links.size());

        for (WebElement link : links) {
            String url = link.getDomAttribute("href");
            if (url != null && !url.isEmpty()) {
                checkLink(url);
            } else {
                nullOrEmptyCount++;
                System.out.println("Empty or null URL found");
                ExtentTestManager.logWarning("Empty or null URL found");
            }
        }
    }
}
