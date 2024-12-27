package stanalone.pageobjects;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import stanalone.abstractcomponents.AbstractComponent;

public class CartPageObjects extends AbstractComponent{

	WebDriver driver;
	public CartPageObjects(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="div[class='cartSection'] h3")
	List<WebElement> Selected;
	@FindBy(css="div[class='cartSection'] h3")
	WebElement Select;
	@FindBy(xpath="//div/ul/li[3]/button")
	WebElement CheckoutButton;
	
	public Boolean VerifyItems(String name1, String name2) {
		List<String> WantedItems1=Arrays.asList(name1,name2);
		Boolean v=Selected.stream().anyMatch(product->WantedItems1.
				contains(Selected.get(0).getText()));
		return v;
		
	}
	
	public void PrintItems(String name1, String name2) {
		List<String> WantedItems1=Arrays.asList(name1,name2);
		Selected.stream().filter(product->WantedItems1.
				contains(Selected.get(0).getText())).forEach(s->System.out.println(s.getText()));	
	}
	public String ItemText(int x) {
		String text=Select.getText();
		scrolling(x);
		return text;
	}
	
	public PaymentPage CheckOut() {
		CheckoutButton.click();
		PaymentPage payment=new PaymentPage(driver);
		return payment;
	}
	
	

}
