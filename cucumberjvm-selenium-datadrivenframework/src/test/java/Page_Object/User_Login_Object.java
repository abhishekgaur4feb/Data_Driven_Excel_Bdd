package Page_Object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class User_Login_Object extends BaseClass{

	public static WebDriver driver;


	public User_Login_Object(WebDriver driver){
		super(driver);
	}
	
	@FindBy(how=How.XPATH, using="//input[@id='MainContent_LoginUser_UserName']")
	public static WebElement Username;
	
	@FindBy(how=How.XPATH, using="//input[@id='MainContent_LoginUser_Password']")
	public static WebElement Password;
	
	@FindBy(how=How.XPATH, using="//input[@id='MainContent_LoginUser_LoginButton']")
	public static WebElement Login_Button;
	

}
