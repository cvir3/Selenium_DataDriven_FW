package WebFunctions;

import WebElements.we_Dahsboard;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BaseClass;
import utils.ExcelUtils;
import utils.ExtendReport.ExtentTestManager;

import java.util.List;

public class wf_Register extends BaseClass {
    we_Dahsboard d = new we_Dahsboard();
    ExcelUtils excel = new ExcelUtils("Register");
    String fullname = excel.getValue(1, 0);
    String username = excel.getValue(1, 1);
    String email = excel.getValue(1, 2);
    String password = excel.getValue(1, 3);
    String confirmPassword = excel.getValue(1, 4);

    public wf_Register(WebDriver remoteDriver) {
        this.webDriver = remoteDriver;
    }

    public void registerForm() throws Exception {
        try {
            webDriver.findElement(d.linkRegister).click();
            ExtentTestManager.logInfo("Clicked on register link.");
            webDriver.findElement(d.inputFullName).sendKeys(fullname);
            ExtentTestManager.logPass("Full name entered successfully: " + fullname);
            webDriver.findElement(d.inputUsername).sendKeys(username);
            ExtentTestManager.logPass("Username entered successfully: " + username);
            webDriver.findElement(d.inputEmail).sendKeys(email);
            ExtentTestManager.logPass("Email entered successfully: " + email);
            webDriver.findElement(d.inputPassword).sendKeys(password);
            ExtentTestManager.logPass("Password entered successfully.");
            webDriver.findElement(d.inputConfirmPassword).sendKeys(confirmPassword);
            ExtentTestManager.logPass("Confirm password entered successfully.");
            webDriver.findElement(d.inputLocation).sendKeys("Mumbai");
            Thread.sleep(5000);
            List<WebElement> countryList = webDriver.findElements(d.selectDropdownItem);
            for (WebElement e : countryList) {
                System.out.println(e.getText());
                if (e.getText().equalsIgnoreCase("Mumbai")) {
                    e.click();
                    break;
                }
            }
            webDriver.findElement(d.selectTerms).click();
            ExtentTestManager.logPass("Clicked on terms and conditions.");
            webDriver.findElement(d.btnRegister).click();
            ExtentTestManager.logPass("Clicked on register button.");
        } catch (Exception e) {
            ExtentTestManager.logFail("Error occurred during registration: " + e.getMessage());
            throw e;
        }
    }

}
