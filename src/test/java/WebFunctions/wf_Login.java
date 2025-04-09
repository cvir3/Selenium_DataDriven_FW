package WebFunctions;

import WebElements.we_Login_LogOut;
import org.openqa.selenium.WebDriver;
import utils.BaseClass;
import utils.ExcelUtils;
import utils.ExtendReport.ExtentTestManager;

public class wf_Login extends BaseClass {

    we_Login_LogOut l = new we_Login_LogOut();
    ExcelUtils excel = new ExcelUtils("Register");
    String email = excel.getValue(1, 2);
    String password = excel.getValue(1, 3);

    public wf_Login(WebDriver remoteDriver) {
        this.webDriver = remoteDriver;
    }

    public void loginPage() throws Exception {
        try {
            webDriver.findElement(l.loginLInk).click();
            ExtentTestManager.logInfo("Clicked on login link.");
            webDriver.findElement(l.inputEmail).sendKeys(email);
            ExtentTestManager.logPass("Email successfully entered: " + email);
            webDriver.findElement(l.inputPassword).sendKeys(password);
            ExtentTestManager.logPass("Password entered successfully.");
            webDriver.findElement(l.btnSignIn).click();
            ExtentTestManager.logPass("Clicked on Sign in button.");
            Thread.sleep(1000);
        } catch (Exception e) {
            ExtentTestManager.logFail("Error occurred during login: " + e.getMessage());
            throw e;
        }
    }

    public void logOut() throws Exception {
        try {
            webDriver.findElement(l.clickOnUserName).click();
            ExtentTestManager.logInfo("Clicked on Username.");
            Thread.sleep(1000);
            webDriver.findElement(l.clickOnLogOut).click();
            ExtentTestManager.logPass("Clicked on logout button.");
            Thread.sleep(1000);
        } catch (Exception e) {
            ExtentTestManager.logFail("Error occurred during logout: " + e.getMessage());
            throw e;
        }
    }
}
