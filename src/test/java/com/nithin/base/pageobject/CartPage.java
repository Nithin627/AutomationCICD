package com.nithin.base.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}


	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;

	@FindBy(css = ".totalRow button")
	WebElement checkoutBtn;

	public boolean verifyProductDisplay(String productName) {
		boolean match = cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;

	}

	public CheckOutPage goTocheckOut() {
		checkoutBtn.click();
		CheckOutPage checkOut=new CheckOutPage(driver);
		return checkOut;
		
	}

}
