package com.nithin.abstarctcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nithin.base.pageobject.CartPage;
import com.nithin.base.pageobject.OrderPage;

public class AbstarctComponents {
	WebDriver driver;

	public AbstarctComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "[routerlink*='cart']")
	WebElement cartBtn;

	@FindBy(xpath = "(//button[@class='btn btn-custom'])[2]")
	WebElement orderBtn;

	public void waitForElementToAppear(By ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(ele));

	}

	public void waitForWebElementToAppear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(ele));

	}

	public void waitForElementToDisapear(WebElement ele) throws InterruptedException {
		Thread.sleep(2000);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.invisibilityOf(ele));

	}

	public CartPage goToCartPage() {

		cartBtn.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;

	}
	
	public OrderPage goToOrderPage() {

		orderBtn.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;

	}

}
