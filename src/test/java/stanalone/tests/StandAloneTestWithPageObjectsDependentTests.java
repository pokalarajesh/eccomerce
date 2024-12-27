package stanalone.tests;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import stanalone.pageobjects.CartPageObjects;
import stanalone.pageobjects.OrderPage;
import stanalone.pageobjects.OrderPageObjects2;
import stanalone.pageobjects.PaymentPage;
import stanalone.pageobjects.SelectItemPageObjects;
import stanalone.testcomponent.BaseTest;

public class StandAloneTestWithPageObjectsDependentTests extends BaseTest{

	
	@Test
	public void itemValidation1() throws IOException {
		List<String> WantedItems1=Arrays.asList("ZARA COAT 3", "IPHONE 13  PRO");
		
		SelectItemPageObjects items1=page.Loginpage("rajeshpok@gmail.com", "Abcd1234@");
		
		List<WebElement> WantedItems=items1.getProductList();
		items1.selectProduct("ZARA COAT 3", "IPHONE 13  PRO");
		CartPageObjects cartPage=items1.goToCart();
		
		Boolean v=cartPage.VerifyItems("ZARA COAT 3", "IPHONE 13  PRO");
		System.out.println(v);
		cartPage.PrintItems("ZARA COAT 3", "IPHONE 13  PRO");
		String text=cartPage.ItemText(300);
		Assert.assertEquals(WantedItems1.get(0), text);
		
	}
	
	@Test(dependsOnMethods= {"itemValidation1"})
	public void itemValidation2() throws IOException {
				
		SelectItemPageObjects items1=page.Loginpage("rajeshpok@gmail.com", "Abcd1234@");
		OrderPageObjects2 orderPage=items1.goToOrder();
		System.out.println(orderPage.VerifyItems("ZARA COAT 3", "IPHONE 13  PRO"));
		
	}
}
