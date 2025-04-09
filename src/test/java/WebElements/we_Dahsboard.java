package WebElements;


import org.openqa.selenium.By;

public class we_Dahsboard {

    public By linkRegister = By.xpath("//a[normalize-space()='Register']");
    public By inputFullName = By.xpath("//input[@placeholder='Full Name']");
    public By inputUsername = By.xpath("//input[@placeholder='username09']");
    public By inputEmail = By.xpath("//input[@type='email']");
    public By inputPassword = By.xpath("(//input[@type='password'])[1]");
    public By inputConfirmPassword = By.xpath("(//input[@type='password'])[2]");
    public By inputLocation = By.xpath("//input[@placeholder='Enter a location']");
    public By selectDropdownItem = By.xpath("(//span[contains(text(),'Mu')])[1]");
    public By selectTerms = By.xpath("//div[@class='form-group checkbox']");
    public By btnRegister = By.xpath("//button[@type='submit']");
}
