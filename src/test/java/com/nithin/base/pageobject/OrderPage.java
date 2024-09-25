package com.nithin.base.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nithin.abstarctcomponents.AbstarctComponents;

public class OrderPage extends AbstarctComponents {
	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> productsNames;

	public boolean verifyOrederDisplay(String productName) {
		boolean match = productsNames.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;
	}

}
