package stanalone.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import stanalone.abstractcomponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
//this invokes this.driver
	//this invokes pagefactory
	@FindBy(id="userEmail")
	WebElement UserMail;
	@FindBy(id="userPassword")
	WebElement UserPassword;
	@FindBy(id="login")
	WebElement Login;
	@FindBy(className="toast-message")
	WebElement element;
	
	public SelectItemPageObjects Loginpage(String mail, String password) {
		UserMail.sendKeys(mail);
		UserPassword.sendKeys(password);
		Login.click();
		SelectItemPageObjects items1=new SelectItemPageObjects(driver);
		return items1;
	}
	public void got() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	//class="ng-tns-c4-10 toast-message ng-star-inserted"
	public String errorMessage() {
		waitForWebElementToAppear(element);
		return element.getText();
		
	}
	
	
}
