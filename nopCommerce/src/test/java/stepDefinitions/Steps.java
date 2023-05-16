package stepDefinitions;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class Steps extends BaseClass{
	
	@Before
	public void setup() throws IOException {
		logger = Logger.getLogger("nopCommerce");
		PropertyConfigurator.configure("Log4j.properties");
		logger.info("***** Launching Browser *******");
		
		// Reading properties from config.properties file
		configProp = new Properties();
		FileInputStream configPropFile = new FileInputStream("config.properties");
		configProp.load(configPropFile);
				
		if(configProp.getProperty("browser").equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromepath"));
			driver = new ChromeDriver();
		}
		else if(configProp.getProperty("browser").equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", configProp.getProperty("firefoxpath"));
			driver = new ChromeDriver();
		}
		logger.info("** Successfully "+ configProp.getProperty("browser") +" Browser is Launched ***");		
		driver.manage().window().maximize();	
	}

	
	// ********* Login Page  ********//	
	@Given("User Launch Chromre browser")
	public void user_launch_chromre_browser() {
		lp = new LoginPage(driver);
	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
		logger.info("***** Opening URL *******");
	    driver.get(url);
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password) {
		logger.info("***** Entering email and password *******");
	    lp.setPassword(email);
	    lp.setPassword(password);
	}

	@When("Click on Login")
	public void click_on_login() {
	    lp.clickLogin();
	}

	@Then("Page Title should be {string}")
	public void page_title_should_be(String epxTitle) {
	   if(driver.getPageSource().contains("Login was unsuccessful")) {
		   driver.close();
		   Assert.assertTrue(false);
	   }{
		   String actualTitle= driver.getTitle();
		   Assert.assertEquals(epxTitle, actualTitle);
	   }		
	}

	@When("User click on Log out link")
	public void user_click_on_log_out_link() throws InterruptedException {
	    lp.clickLogout();
	    Thread.sleep(5000);
	}

	@Then("close browser")
	public void close_browser() {
	    driver.close();
	}
	
	// ****** Customer Page *********//
	
	@Then("User can view Dashboard")
	public void user_can_view_dashboard() throws InterruptedException {		
		addCustomerPage=new AddCustomerPage(driver);
		Assert.assertEquals("Dashboard / nopCommerce administration" ,addCustomerPage.getTitle());
	}

	@When("User click on customers Menu")
	public void user_click_on_customers_menu() throws InterruptedException {
		Thread.sleep(3000);
		addCustomerPage.clickOnCustomersMenu();
	}

	@When("click on customers Menu Item")
	public void click_on_customers_menu_item() throws InterruptedException {
		Thread.sleep(3000);
		addCustomerPage.clickOnCustomersMenuItem();
	}

	@When("click on Add new button")
	public void click_on_add_new_button() throws InterruptedException {
		addCustomerPage.clickOnAddnew();
		Thread.sleep(3000);
	}

	@Then("User can view Add new customer page")
	public void user_can_view_add_new_customer_page() {
		Assert.assertEquals("Add a new customer / nopCommerce administration", addCustomerPage.getTitle());
	}

	@When("User enter customer info")
	public void user_enter_customer_info() throws InterruptedException {
		String email = randomString()+"@gmail.com";
		addCustomerPage.setEmail(email);
		addCustomerPage.setPassword("abc123");
		addCustomerPage.setFirstName("testing");
		addCustomerPage.setLastName("test");
		addCustomerPage.setGender("Male");
		addCustomerPage.setDob("5/15/1988");
		addCustomerPage.setCompanyName("Alpha");
		//addCustomerPage.setCustomerRoles("Guests");
		addCustomerPage.setAdminContent("Tesitng application");
		addCustomerPage.setManagerOfVendor("Vendor 2");
	}

	@When("click on Save button")
	public void click_on_save_button() throws InterruptedException {
		addCustomerPage.clickOnSave();
		Thread.sleep(3000);
	}

	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String expMsg) {
		Assert.assertTrue(addCustomerPage.getConfirmationMessage().contains(expMsg));
	}

	
	// ****** Search Customer Page *****//
	@When("Enter customer email")
	public void enter_customer_email() {
		searchCustomerPage = new SearchCustomerPage(driver);
		searchCustomerPage.setEmail("victoria_victoria@nopCommerce.com");
	}
	
	@When("Click on Search button")
	public void click_on_search_button() throws InterruptedException {
		searchCustomerPage.clickSearchbtn();
		Thread.sleep(3000);
	}

	@Then("User should found Email in the search table")
	public void user_should_found_email_in_the_search_table() {
		boolean status = searchCustomerPage.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
		Assert.assertEquals(true, status);		
	}
	
	@When("Enter customer Firstname")
	public void enter_customer_firstname() {
		searchCustomerPage = new SearchCustomerPage(driver);
		searchCustomerPage.setFirstName("Victoria");
	}
	
	@When("Enter customer Lastname")
	public void enter_customer_lastname() {	
		searchCustomerPage.setLastName("Terces");
	}
	
	@Then("User should found Name in the search table")
	public void user_should_found_name_in_the_search_table() {
		boolean status = searchCustomerPage.searchCustomerByName("Victoria Terces");
		Assert.assertEquals(true, status);	
	}
	


}
