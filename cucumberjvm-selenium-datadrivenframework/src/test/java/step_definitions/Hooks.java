package step_definitions;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import helpers.DataHelper;

public class Hooks {
	public static WebDriver driver;
	public List<HashMap<String, String>> datamap1;

	public Hooks() {

		datamap1 = DataHelper.data(System.getProperty("user.dir") + "//src//test//resources//testData/Browser.xlsx",
				"Sheet1");
		System.out.println("Datamap values are -->" + datamap1);
	}

	@Before
	/**
	 * Delete all cookies at the start of each scenario to avoid shared state
	 * between tests
	 */
	public void openBrowser() throws MalformedURLException {

		for (int index = 0; index < datamap1.size(); index++) {

			System.out.println("Printing current data set...");
			String Browsername = datamap1.get(index).get("Browsername");

			if (Browsername.equals("chrome")) {

				System.out.println("Called openBrowser");
				System.setProperty("webdriver.chrome.driver", "driver//chromedriver.exe");
				driver = new ChromeDriver();
				driver.manage().deleteAllCookies();
				driver.manage().window().maximize();
				driver.get(datamap1.get(index).get("Url"));
			} else if (Browsername.equals("mozilla")) {
				System.setProperty("webdriver.gecko.driver", "driver//geckodriver.exe");
				driver = new FirefoxDriver();
				driver.manage().deleteAllCookies();
				driver.manage().window().maximize();
				driver.get(datamap1.get(index).get("Url"));
			}
		}

	}

	/**
	 * @param scenario
	 */
	@After
	/**
	 * Embed a screenshot in test report if test is marked as failed
	 */
	public void embedScreenshot(Scenario scenario) {

		if (scenario.isFailed()) {
			try {
				scenario.write("Current Page URL is " + driver.getCurrentUrl());
				// byte[] screenshot = getScreenshotAs(OutputType.BYTES);
				byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenshot, "image/png");
			} catch (WebDriverException somePlatformsDontSupportScreenshots) {
				System.err.println(somePlatformsDontSupportScreenshots.getMessage());
			}

		}
		driver.quit();

	}

}