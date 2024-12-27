package stanalone.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import stanalone.abstractcomponents.AbstractComponent;

public class OrderPage extends AbstractComponent{

	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css=".em-spacer-1 .ng-star-inserted")
	WebElement OrderNo;
	@FindBy(css=".hero-primary")
	WebElement ThankNote;
	
	public void getOrderId() {
		System.out.println(OrderNo.getText());
	}
	public String AssertNote() {
		return ThankNote.getText();
		
	}

	

}
