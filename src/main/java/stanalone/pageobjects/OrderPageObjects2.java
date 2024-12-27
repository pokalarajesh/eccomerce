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

public class OrderPageObjects2 extends AbstractComponent{

	WebDriver driver;
	public OrderPageObjects2(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> Selected;
	
	public Boolean VerifyItems(String name1, String name2) {
		List<String> WantedItems1=Arrays.asList(name1,name2);
		Boolean v=Selected.stream().anyMatch(product->WantedItems1.
				contains(Selected.get(0).getText()));
		return v;
		
	}
		
	

}
