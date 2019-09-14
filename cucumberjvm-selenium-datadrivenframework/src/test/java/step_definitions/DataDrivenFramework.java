package step_definitions;
import cucumber.api.PendingException;
import helpers.DataHelper;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Page_Object.User_Login_Object;
import Utility.VideoRecorder_utlity;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
public class DataDrivenFramework {
    public WebDriver driver;
    public List<HashMap<String,String>> datamap;
    public DataDrivenFramework()
    {
    	driver = Hooks.driver;
     	datamap = DataHelper.data(System.getProperty("user.dir")+"//src//test//resources//testData/default.xlsx","Sheet1");
    }
    @When("^I open automationpractice website$")
    public void i_open_automationpractice_website() throws Throwable {
    	
        //driver.get("http://sorterlog-tst.psi.psigroupinc.com");
    }
    @When("^click contact us$")
    public void click_contact_us() throws Throwable {

    }
    @Then("^I contact the customer service with excel row \"(.*?)\" dataset$")
    public void i_contact_the_customer_service_with_excel_row_dataset(String arg1) throws Throwable {
        int index = Integer.parseInt(arg1)-1;
        System.out.println("Printing current data set...");
        /*for(HashMap h:datamap)
        {
            System.out.println(h.keySet());
            System.out.println(h.values());
        }
        */
        
        
		PageFactory.initElements(driver, User_Login_Object.class);  
		VideoRecorder_utlity.startRecord("Login_Validation_Recording");
        
        Thread.sleep(4000);
		WebDriverWait wait1 = new WebDriverWait(driver, 50);
		WebElement element1 = wait1.until(ExpectedConditions.visibilityOf(User_Login_Object.Username));
		Thread.sleep(1000);
		element1.sendKeys(datamap.get(index).get("Username"));
		
		byte[] decoded = Base64.getDecoder().decode(datamap.get(index).get("Password"));
		String Decoded_Password=new String(decoded, StandardCharsets.UTF_8);
		
		WebDriverWait wait2 = new WebDriverWait(driver, 50);
		WebElement element2 = wait2.until(ExpectedConditions.visibilityOf(User_Login_Object.Password));
		Thread.sleep(1000);
		element2.sendKeys(Decoded_Password);
		
		WebDriverWait wait3 = new WebDriverWait(driver, 50);
		WebElement element3 = wait3.until(ExpectedConditions.visibilityOf(User_Login_Object.Login_Button));
		Thread.sleep(1000);
		element3.click();
		
		VideoRecorder_utlity.stopRecord();
		
	    }
}