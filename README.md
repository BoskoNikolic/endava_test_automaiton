Endava test automation

This test framework contains tests written for Endava website (https://www.endava.com/) and includes:

	 AboutPage validation
	 
	 	- testAllLocations:
	 		Opens Endava site and validates new URL, finds "WE DELIVER GLOBAL TRANSFORMATION"
	 		section and validates all of 23 Endava locations (both names of the cities and addresses), 
	 		e.g. Belgrade 9đ, Milutina Milankovica St.
	 
	 AgilePage validation
	 
	 	- testAgileItemActiveInDAAMenu:
	 		Test validates that AGILE menu item is active in DIGITAL - AGILE - AUTOMATION menu.
	 	
	 	- testAutofillWithLinkedin:
	 		Test checks autofill with LinkedIn button, validates that new window pops up 
	 		and validates error messages.
	 
	 AutomationPage validation
	 
	 	- testAutomationMenuIsActive:
	 		Test validates that automation link on Automation page is active 
	 		and checks if URL has changed.
	 
	 ContactPage validation	
	 
	 	- testContactPage:
	 		After clicking on Contact page in Menu, validates its URL, validates that both radio buttons
	 		("Interested in our services?" and "Want to join our team?") are not selected, validates that message 
	 		"Please visit the Careers section on our website to apply for job openings. Please use the 
	 		form below if you have another question or important message." is visible.
	  	
    HomePage validation
    
    	- testHomePageIsOpened:
    		Test validates Home Page URL and title.
    		
    	- testOpenMenu:
    		Test validates that SOLUTION menus are visible after clicking on down arrow.
    	
    	- testLanguageMenu:
    		Test verifies that language is changed after choosing Deutsch in language options on Home Page.
    		Verifies that URL corresponds to German version of the website.
    		Verifies that copyright message is the following text in German: "Alle Rechte vorbehalten" (instead of 'All rights reserved'). 
    		Changes language back to 'English' and verifies that language is changed: URL switched back to
    		the English version of the website. 
    		Verifies that copyright message is the following text in English: "All rights reserved".
    		
    	- testPhoneIconLink:
    		Test validates that click on the phone icon is a link to the Contact page.
    		
    	- testSocialMediaIconsLinks:
    		Test validates that links of social media icons are correct.
    	
    	- testCookiePolicy:
    		Validates text in cookies policy message. 
    		Clicks on "Learn More" and validates that user is taken to Cookie Policy page.
    
    InvestorsPage validation
    
		- testAboutUsVisibility:
			Test validates that the string "ABOUT US" is visible on the "INVESTORS" page in the menu.
		
		- testInvestorsSearch:
			Test validates URL on Investors page.
			Clicks on the Search, validates that search result is "No results found." if "blahblah" is typed 
			into search box and searched for.    	    	
    
    MenuPage validation   
    
		- testIsEndavaLogoLinkToHomePageInAllMenuPages:
			Test validates that Endava logo is a link to the home page in all Menu pages, except Investors page.
     
    SuccessStoriesPage validation
		
		- testFirstStoryHeading:
			Test validates that heading text atop of the page contains string "SUCCESS STORIES".
    
All tests take screen shots on failure.

Pre-requisites

    Java 8 or higher
    Web browser (Firefox, Chrome, Internet Explorer)
    Maven
    Selenium Webdriver
    TestNG

Running Tests

The following steps should get you set up for running Selenium tests locally on your machine:

    1. Clone this repository to your local machine.
    2. Declare this project as Maven
    3. Run as TestNG

Supported browsers

    Firefox
    Internet Explorer
    Chrome

Choose browser by setting it as a parameter in testng.xml file.