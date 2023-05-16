Feature: Customers

 Background: Below are steps for open url
	Given User Launch Chromre browser
	When User opens URL "https://admin-demo.nopcommerce.com/login"
	And User enters Email as "admin@yourstore.com" and Password as "admin"
	And Click on Login
	Then User can view Dashboard

@Sanity
Scenario: Add a new Customer
	When User click on customers Menu
	And click on customers Menu Item
	And click on Add new button
	Then User can view Add new customer page
	When User enter customer info
	And click on Save button
	Then User can view confirmation message "The new customer has been added successfully."
	And close browser

@Sanity
Scenario: Search customer by EmailID
	When User click on customers Menu
	And click on customers Menu Item
	And Enter customer email
	When Click on Search button
	Then User should found Email in the search table
	And close browser

@Regression
Scenario: Search customer by Name
	When User click on customers Menu
	And click on customers Menu Item
	And Enter customer Firstname
	And Enter customer Lastname
	When Click on Search button
	Then User should found Name in the search table
	And close browser

	