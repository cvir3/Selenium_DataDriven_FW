package WebElements;

import org.openqa.selenium.By;

public class we_Login_LogOut {

    public By loginLInk = By.xpath("//a[@href='/login']");
    public By inputEmail = By.xpath("//input[@type='email']");
    public By inputPassword = By.xpath("//input[@type='password']");
    public By btnSignIn = By.xpath("//button[@type='submit']");
    public By clickOnUserName = By.xpath("//button[@class='dropbtn border-right-0']");
    public By clickOnLogOut = By.xpath("//a[@class='nav-link']");
}
