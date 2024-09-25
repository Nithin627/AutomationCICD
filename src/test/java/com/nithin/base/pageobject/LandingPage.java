package com.nithin.base.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nithin.abstarctcomponents.AbstarctComponents;

public class LandingPage extends AbstarctComponents {
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

		PageFactory.initElements(driver, this);

	}

//	driver.findElement(By.xpath("//div[@aria-label='Incorrect email or password.']"))

//	@FindBy(xpath = "//div[@aria-label='Incorrect email or password.']")
//	WebElement errorMsg;

	@FindBy(css = "div[aria-label='Incorrect email or password.']")
	WebElement errorMsg;

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement userPassword;

	@FindBy(id = "login")
	WebElement loginBtn;

	public ProductCatalog enterUserDetails(String uname, String pass) {
		userEmail.sendKeys(uname);
		userPassword.sendKeys(pass);
		loginBtn.click();
		ProductCatalog productCatalogue = new ProductCatalog(driver);
		return productCatalogue;
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}

	public String getErrorMsg() {
		waitForWebElementToAppear(errorMsg);
		return errorMsg.getTagName();

	}

}
