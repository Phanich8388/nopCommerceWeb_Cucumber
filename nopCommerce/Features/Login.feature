Feature: Login

@Sanity
Scenario: Successful login with valid Credentails
	Given User Launch Chromre browser
	When User opens URL "https://admin-demo.nopcommerce.com/login"
	And User enters Email as "admin@yourstore.com" and Password as "admin"
	And Click on Login
	Then Page Title should be "Dashboard / nopCommerce administration"
	When User click on Log out link
	Then Page Title should be "Your store. Login"
	And close browser

@Sanity @Regression	
Scenario Outline: Successful login with Data Driven credentails
	Given User Launch Chromre browser
	When User opens URL "https://admin-demo.nopcommerce.com/login"
	And User enters Email as "<email>" and Password as "<password>"
	And Click on Login
	Then Page Title should be "Dashboard / nopCommerce administration"
	When User click on Log out link
	Then Page Title should be "Your store. Login"
	And close browser
	
	Examples:
	|email|password|
	|admin@yourstore.com|admin|
	|admin@yourstore.com|adim123|