package com.nithin.base.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.nithin.abstarctcomponents.AbstarctComponents;

public class ProductCatalog extends AbstarctComponents {
	WebDriver driver;

	public ProductCatalog(WebDriver driver) {
		super(driver);

		this.driver = driver;

		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;

	By productsBy = By.cssSelector(".mb-3");

	By addToCart = By.cssSelector(".card-body button:last-of-type");

	By toastMsg = By.cssSelector("#toast-container");

	@FindBy(css = ".ng-animating")
	WebElement toastDisappear;

	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}

	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return prod;
	}

	public void addProductToCart(String productName) throws InterruptedException {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMsg);
		waitForElementToDisapear(toastDisappear);

	}

}
