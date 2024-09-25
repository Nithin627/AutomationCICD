package com.nithin.base.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nithin.abstarctcomponents.AbstarctComponents;

public class ConfirmationPage extends AbstarctComponents {

	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

//	String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();

	@FindBy(css = ".hero-primary")
	WebElement confirmMessage;

	public String getConfimMSG() {
		String text = confirmMessage.getText();
		return text;
	}
}
