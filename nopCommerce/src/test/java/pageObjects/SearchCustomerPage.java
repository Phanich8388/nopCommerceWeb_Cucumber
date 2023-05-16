package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitHelper;

public class SearchCustomerPage {
	
	public WebDriver ldriver;
	public WaitHelper waithelper;
	
	public  SearchCustomerPage(WebDriver rdriver) {
		this.ldriver=rdriver;
		PageFactory.initElements(ldriver, this);
		waithelper = new WaitHelper(ldriver);		
	}
	
	@FindBy(how = How.ID, using="SearchEmail")
	WebElement txtEmail;
	
	@FindBy(how = How.ID, using="SearchFirstName")
	WebElement txtFirstName;
	
	@FindBy(how = How.ID, using="SearchLastName")
	WebElement txtLastName;
	
	@FindBy(how = How.ID, using ="search-customers")
	WebElement btnSearch;
	
	@FindBy(how =How.XPATH, using="//table[@id='customers-grid']" )
	WebElement table;
	
	@FindBy(how =How.XPATH, using="//table[@id='customers-grid']//tbody/tr" )
	List<WebElement> tableRows;
	
	@FindBy(how =How.XPATH, using="//table[@id='customers-grid']//tbody/tr/td" )
	List<WebElement> tableColumns;
	
	public void setEmail(String email) {
		waithelper.WaitForElement(txtEmail, 30);
		txtEmail.clear();
		txtEmail.sendKeys(email);
	}
	
	
	public void setFirstName(String firstname) {
		waithelper.WaitForElement(txtFirstName, 30);
		txtFirstName.clear();
		txtFirstName.sendKeys(firstname);
	}
	
	public void setLastName(String lastname) {
		waithelper.WaitForElement(txtLastName, 30);
		txtLastName.clear();
		txtLastName.sendKeys(lastname);
	}
	
	public void clickSearchbtn() {
		btnSearch.click();
		waithelper.WaitForElement(btnSearch, 30);
	}
	
	public int getNoofRows() {
		return tableRows.size();
	}
	
	public int getNoofColumns() {
		return tableColumns.size();
	}
	
	public boolean searchCustomerByEmail(String email) {
		boolean flag=false;
		for(int i=1; i<=getNoofRows();i++) {
			String emailid=table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr/td[2]")).getText();
			System.out.println(emailid);
			if(emailid.equals(email)) {
				flag=true;
			}
		}		
		return flag;  			
	}
	
	public boolean searchCustomerByName(String name) {
		boolean flag=false;
		for(int i=1; i<=getNoofRows();i++) {
			String actname=table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr/td[3]")).getText();
			System.out.println(name);
			if(actname.equals(name)) {
				flag=true;
			}
		}		
		return flag;  			
	}
	
}
