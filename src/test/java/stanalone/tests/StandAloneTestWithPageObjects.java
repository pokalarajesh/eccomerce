package stanalone.tests;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import stanalone.pageobjects.CartPageObjects;
import stanalone.pageobjects.OrderPage;
import stanalone.pageobjects.PaymentPage;
import stanalone.pageobjects.SelectItemPageObjects;
import stanalone.testcomponent.BaseTest;

public class StandAloneTestWithPageObjects extends BaseTest{

	
	@Test(dataProvider="getData",groups= {"purchase"})
	public void productCheckout(HashMap<String,String> map) throws IOException {
		List<String> WantedItems1=Arrays.asList("ZARA COAT 3", "IPHONE 13  PRO");
		
		SelectItemPageObjects items1=page.Loginpage(map.get("email"),map.get("pass"));
		
		List<WebElement> WantedItems=items1.getProductList();
		items1.selectProduct("ZARA COAT 3", "IPHONE 13  PRO");
		CartPageObjects cartPage=items1.goToCart();
		
		Boolean v=cartPage.VerifyItems("ZARA COAT 3", "IPHONE 13  PRO");
		System.out.println(v);
		cartPage.PrintItems("ZARA COAT 3", "IPHONE 13  PRO");
		String text=cartPage.ItemText(300);
		Assert.assertEquals(WantedItems1.get(0), text);
		PaymentPage payment=cartPage.CheckOut();
		
		payment.CountryPage("ind",300);
		OrderPage orderPage=payment.goToOderPage();
		
		orderPage.getOrderId();
		String Note="Thankyou for the order.";
		String ThankNotes=orderPage.AssertNote();
		Assert.assertTrue(ThankNotes.equalsIgnoreCase(Note));
		
	}
	@DataProvider
	public Object[][] getData() throws IOException{
		
		List<HashMap<String,String>> data=getJsonDataToHashMap(System.getProperty("user.dir")+"\\src\\test\\java\\stanalone\\data\\purchaseOrde.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	
	
	
								//step222222
	//	@DataProvider
//	public Object[][] getData(){
//	HashMap<String,String> map=new 	HashMap<String,String>();
//	map.put("email", "rajeshpok@gmail.com");
//	map.put("pass", "Abcd1234@");
//	
//	HashMap<String,String> map1=new 	HashMap<String,String>();
//	map1.put("email", "raj@gmail.com");
//	map1.put("pass", "Abcd1234@");
//		
//		return new Object[][] {{map},{map1}};
//	}
							//step333333333
//	@DataProvider
//	public Object[][] getData(){
//		Object[][] varia=new Object[][] {{"rajeshpok@gmail.com", "Abcd1234@"},{"raj@gmail.com", "Abcd1234@"}};
//		return varia;
//	}
   
}
