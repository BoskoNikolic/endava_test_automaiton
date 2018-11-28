package com.endava.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Vladimir Krekic
 */

public class AboutPage extends BasePage {

	public static final String ABOUT_URL = ENDAVA_URL_EN + "/About";
	public static final String ABOUT_TITLE = "About";
	private static Logger log = Logger.getLogger(AboutPage.class);
	private WebElement rightArrow = driver.findElement(By.xpath("//*[@id=\"all-locations\"]/div[2]/div[2]/div[2]"));
	private By cities = By.xpath("//*[@id=\"all-locations\"]/div[1]/div/div[*]/div/h3");
	private By addresses = By.xpath("//*[@id=\"all-locations\"]/div[1]/div/div[*]/div/p");
	private List<WebElement> listOfCities = driver.findElements(cities);
	private List<WebElement> listOfAddresses = driver.findElements(addresses);
	private Set<String> allLocations = setAllLocations();
	private static final List<String> ADDRESSES_OF_ENDAVA_LOCATIONS = Arrays.asList("BELGRADE", "BOGOTÁ", "ATLANTA", "BUCHAREST", "AMSTERDAM",
			"Calle 96 No. 10-38, Edificio BOX, 7th & 8th Floor, Bogota D.C.", "9đ, Milutina Milankovića St.", "One Glenlake Pkwy, Suite 784", 
			"4G Vasile Milea Blvd., 9th floor, AFI 3 Business Park", "Laapersveld 43, Hilversum", "COPENHAGEN", "CLUJ-NAPOCA", "BUENOS AIRES", "CHISINAU", "2420 17th St.", 
			"51 Al. Vaida Voevod St.", "San Martin 439", "15 Sfatul Tarii St.", 
			"UNIVATE, Njalsgade 76", "FRANKFURT", "LONDON", "MONTEVIDEO", "MEDELLÍN", "DENVER", "IASI", "3E Palat St. Palas, United Business Center 1 - 5th Floor", 
			"Cra 48 A # 15 sur – 84",
			"Rio Negro 1338/3", "125 Old Broad Street", "Eschersheimer Landstraße 14", "ROSARIO", "NEW YORK", "SEATTLE", "NEW JERSEY", "SKOPJE", 
			"757 3rd Avenue Suite 1901, NY, 10017 USA", 
			"Urquiza 2284", "12900 NE 180th Street, #240", "5 Hilltop Road, Mendham", "Kale Building, UI. 11 Mart Br. 2", 
			"SOFIA", "89b Vitosha Blvd., Millenium Business Center, 9th & 10th Floor");
	

	public AboutPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * @return Set of cities and addresses grabbed from web site
	 * @author Vladimir Krekic
	 */
	public Set<String> setAllLocations() {
		Set<String> setOfAllLocations = new LinkedHashSet<>();
		for (int counter = 0; counter < 6; counter++) {
			addLocations(setOfAllLocations, listOfCities);
			addLocations(setOfAllLocations, listOfAddresses);
			selectElement(rightArrow);
		}
		log.debug("Set of addresses grabbed");
		return setOfAllLocations;
	}

	/**
	 * @param set Set of cities and addresses in which elements will be added
	 * @param list partial List of cities or addresses to be added into a Set
	 * @return partial Set of cities and addresses grabbed from web site
	 * @author Vladimir Krekic
	 */
	private Set<String> addLocations(Set<String> set, List<WebElement> list) {
		set.addAll(
				list.stream().map(w -> w.getText()).filter(w -> !w.equalsIgnoreCase("")).collect(Collectors.toSet()));
		return set;
	}

	/**
	 * @param addresses Set of addresses grabbed from web page
	 * @return boolean - true if all elements are matching list of addresses and prints list of elements not matching if
	 *         false
	 * @author Vladimir Krekic
	 */
	public boolean checkAddresses(Set<String> addresses) {
		if (addresses.size() == ADDRESSES_OF_ENDAVA_LOCATIONS.size()) {
			return addresses.stream().filter(address -> !ADDRESSES_OF_ENDAVA_LOCATIONS.contains(address))
					.peek(log::debug) // prints list of elements not matching
					.count() == 0;
		}
		else{
		String[] stringAddress = addresses.toArray(new String[addresses.size()]);
		for (int i = 0; i <  ADDRESSES_OF_ENDAVA_LOCATIONS.size() ; i++)
		{
			boolean logic = false;
			for (int j = 0; j <  addresses.size(); j++){
				if ( ADDRESSES_OF_ENDAVA_LOCATIONS.get(i).toString().equals(stringAddress[j].toString())){
					logic = true;
				}
				if (j == addresses.size()-1 && logic == false)	{
					log.debug("Not found: " + ADDRESSES_OF_ENDAVA_LOCATIONS.get(i));
				}
			}
		}
		log.debug("Number of grabbed addresses not matching. Size of set should be "
				+ ADDRESSES_OF_ENDAVA_LOCATIONS.size() + " found: " + addresses.size());
		return false;
		}
	}

	public Set<String> getAllLocations() {
		return allLocations;
	}
}
