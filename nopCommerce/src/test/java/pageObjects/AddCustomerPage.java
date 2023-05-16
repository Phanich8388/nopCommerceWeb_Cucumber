package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustomerPage {

	public WebDriver ldriver;

	public AddCustomerPage(WebDriver driver) {
		this.ldriver = driver;
		PageFactory.initElements(ldriver, this);
	}

	By linkCustomers_menu = By.xpath("//a[@href='#']//p[contains(text(),'Customers')]");
	By linkCustomers_menuitem = By.xpath("/html/body/div[3]/aside/div/div[4]/div/div/nav/ul/li[4]/ul/li[1]/a/p");

	By btnAddnew = By.xpath("//*[@class='fas fa-plus-square']");

	By txtEmail = By.xpath("//input[@id='Email']");
	By txtPassword = By.xpath("//input[@id='Password']");

	By txtcustomerRoles = By.xpath("//div[@class='k-multiselect-wrap k-floatwrap']");

	By lstitemAdministrators = By.xpath("//li[contains(text(),'Administrators')]");
	By lstitemRegistered = By.xpath("//li[contains(text(),'Registered')]");
	By lstitemGuests = By.xpath("//li[@contains(text(),'Guests')]");
	By lstitemVendors = By.xpath("//li[@contains(text(),'Vendors')]");

	By drgmgrOfVendor = By.xpath("//*[@id='VendorId']");
	By rdMaleGender = By.id("Gender_Male");
	By rdFemaleGender = By.xpath("Gender_Female");

	By txtFirstName = By.xpath("//input[@id='FirstName']");
	By txtLastName = By.xpath("//input[@id='LastName']");

	By txtDob = By.xpath("//input[@id='DateOfBirth']");
	By txtCompanyName = By.xpath("//input[@id='Company']");
	By txtAdminContent = By.xpath("//textarea[@id='AdminComment']");
	By btnSave = By.xpath("//button[@name='save']");
	
	public String getTitle() {
		return ldriver.getTitle();
	}

	public void clickOnCustomersMenu() {
		ldriver.findElement(linkCustomers_menu).click();
	}

	public void clickOnCustomersMenuItem() {
		ldriver.findElement(linkCustomers_menuitem).click();
	}

	public void clickOnAddnew() {
		ldriver.findElement(btnAddnew).click();
	}

	public void setEmail(String email) {
		ldriver.findElement(txtEmail).sendKeys(email);
	}

	public void setPassword(String password) {
		ldriver.findElement(txtPassword).sendKeys(password);
	}

	public void setCustomerRoles(String role) throws InterruptedException {
		if (!role.equals("Vendors")) {
			ldriver.findElement(By.xpath(""));
		}
		ldriver.findElement(txtcustomerRoles).click();

		WebElement listitem;

		Thread.sleep(3000);
		if (role.equals("Adminstrators")) {
			listitem = ldriver.findElement(lstitemAdministrators);
		} else if (role.equals("Guests")) {
			listitem = ldriver.findElement(lstitemGuests);
		} else if (role.equals("Registered")) {
			listitem = ldriver.findElement(lstitemRegistered);
		} else if (role.equals("Vendors")) {
			listitem = ldriver.findElement(lstitemVendors);
		} else {
			listitem = ldriver.findElement(lstitemGuests);
		}
		listitem.click();

		JavascriptExecutor js = (JavascriptExecutor) ldriver;
		js.executeScript("arguments[0].lick();", listitem);
	}
	
	public void setManagerOfVendor(String value) {
		Select drp=new Select(ldriver.findElement(drgmgrOfVendor));
		drp.selectByVisibleText(value);
	}
	
	public void setGender(String gender) {
		if(gender.equals("Male")) {
			ldriver.findElement(rdMaleGender).click();
		}
		else if(gender.equals("Female")) {
			ldriver.findElement(rdFemaleGender).click();
		}
		else
		{
			ldriver.findElement(rdMaleGender).click();
		}
	}
	
	public void setFirstName(String fname) {
		ldriver.findElement(txtFirstName).sendKeys(fname);		 
	}
	
	public void setLastName(String lname) {
		ldriver.findElement(txtLastName).sendKeys(lname);
	}
	
	public void setDob(String dob) {
		ldriver.findElement(txtDob).sendKeys(dob);
	}
	
	public void setCompanyName(String comname) {
		ldriver.findElement(txtCompanyName).sendKeys(comname);
	}
	
	public void setAdminContent(String content) {
		ldriver.findElement(txtAdminContent).sendKeys(content);
	}
	
	public void clickOnSave() {
		ldriver.findElement(btnSave).click();
	}
	
	public String getConfirmationMessage() {
		String actualMsg = ldriver.findElement(By.tagName("body")).getText();
		return actualMsg;		
	}

}
