package com.nithin.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.nithin.base.pageobject.CartPage;
import com.nithin.base.pageobject.CheckOutPage;
import com.nithin.base.pageobject.ConfirmationPage;
import com.nithin.base.pageobject.OrderPage;
import com.nithin.base.pageobject.ProductCatalog;
import com.nithin.testComponenets.BaseTest;

public class SubmitOrderTest extends BaseTest {

//	String productName = "ZARA COAT 3";

	@Test(dataProvider = "testdata3", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> map) throws InterruptedException, IOException {
//		String productName = "ZARA COAT 3";
		String country = "India";

//		WebDriverManager.chromedriver().setup();
//		driver = new ChromeDriver();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		driver.manage().window().maximize();

//		LandingPage landingpage = new LandingPage(driver);

//		landingpage.goTo();

//		LandingPage landingpage = launchApplication();
		ProductCatalog productCatalogue = landingpage.enterUserDetails(map.get("email"), map.get("password"));

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(map.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();

		boolean verifyProductDisplay = cartPage.verifyProductDisplay(map.get("product"));
		Assert.assertTrue(verifyProductDisplay);
		CheckOutPage checkOutPage = cartPage.goTocheckOut();
		checkOutPage.enterCountryDetails(country);
		ConfirmationPage confirMationPage = checkOutPage.submitOrder();

		String confirmMessage = confirMationPage.getConfimMSG();

		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "submitOrder" }, dataProvider = "testData")
	public void orderHistoryTest(String userName, String pass, String productName) {
		ProductCatalog productCatalogue = landingpage.enterUserDetails(userName, pass);
		OrderPage orderpage = productCatalogue.goToOrderPage();

		Assert.assertTrue(orderpage.verifyOrederDisplay(productName));

	}

//	driving data form Json

	@DataProvider(name = "testData")
	public Object[][] getData() {

		return new Object[][] { { "batman@gmail.com", "Batman123@", "ZARA COAT 3" },
				{ "batman@gmail.com", "Batman123@", "ADIDAS ORIGINAL" } };

	}

	@DataProvider(name = "testData2")
	public Object[][] getDataHashMap() {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "batman@gmail.com");
		map.put("password", "Batman123@");
		map.put("product", "ZARA COAT 3");

		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("email", "batman@gmail.com");
		map2.put("password", "Batman123@");
		map2.put("product", "ADIDAS ORIGINAL");

		return new Object[][] { { map }, { map2 } };

	}

	@DataProvider(name = "testdata3")
	public Object[][] readFromJson() {

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\com\\nithin\\data\\purcahse.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

}
