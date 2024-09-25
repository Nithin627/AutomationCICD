package com.nithin.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.nithin.base.pageobject.CartPage;
import com.nithin.base.pageobject.ProductCatalog;
import com.nithin.testComponenets.BaseTest;
import com.nithin.testComponenets.Retry;

public class ErrorValidationTest extends BaseTest {
	@Test(groups = { "ErrorHandling" })//, retryAnalyzer = Retry.class
	public void LoginErrorValidation() throws InterruptedException, IOException {
		String productName = "ZARA COAT 3";
		String country = "India";

		ProductCatalog productCatalogue = landingpage.enterUserDetails("batman2@gmail.com", "Batman123@");
		String errorMsg = landingpage.getErrorMsg();
		Assert.assertEquals("Incorrect emaile or password.", errorMsg);

	}

	@Test(groups = "productValiadtion")
	public void productErrorValidation() throws InterruptedException, IOException {
		String productName = "ZARA COAT 3";
		String country = "India";

		ProductCatalog productCatalogue = landingpage.enterUserDetails("batman@gmail.com", "Batman123@");

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();

		boolean verifyProductDisplay = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(verifyProductDisplay);

	}

}
