package stanalone.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import stanalone.abstractcomponents.AbstractComponent;

public class PaymentPage extends AbstractComponent{

	WebDriver driver;
	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css=".form-group .text-validated")
	WebElement Country;
	@FindBy(css=".ta-item:nth-child(3)")
	WebElement CountryName;
	@FindBy(css=".actions .btnn")
	WebElement OrderPageButton;
	
	public void CountryPage(String name,int x) {
	Country.sendKeys(name);	
	CountryName.click();
	scrolling(x);
	}
	public OrderPage goToOderPage() {
		OrderPageButton.click();
		OrderPage orderPage=new OrderPage(driver);
		return orderPage;
	}
}
