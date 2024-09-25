package com.nithin.base.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nithin.abstarctcomponents.AbstarctComponents;

public class CheckOutPage extends AbstarctComponents {
	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;

	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement selecCountry;

	@FindBy(css = ".action__submit")
	WebElement submitBtn;

	By countryBy = By.cssSelector(".ta-results");

	public void enterCountryDetails(String countryName) {
		Actions action = new Actions(driver);
		action.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(countryBy);
		selecCountry.click();

	}

	public ConfirmationPage submitOrder() {
		submitBtn.click();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
	}

}
