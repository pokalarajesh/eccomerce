package stanalone.pageobjects;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import stanalone.abstractcomponents.AbstractComponent;

public class SelectItemPageObjects extends AbstractComponent{
	WebDriver driver;
	public SelectItemPageObjects(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
//this invokes this.driver
	//this invokes pagefactory
	@FindBy(css="div.card-body h5 b")
	List<WebElement> WantedItems;
	By products=By.cssSelector("div.card-body h5 b");
	By message=By.cssSelector("#toast-container");
	By animate=By.cssSelector(".ng-animating");
	public List<WebElement> getProductList() {
	
		waitForElementToAppear(products);
		return WantedItems;
	}
	public void selectProduct(String name1, String name2) {
		
		List<String> WantedItems1=Arrays.asList(name1, name2);
		getProductList().stream().filter(item->WantedItems1.contains(item.getText()))
		.forEach(s->s.findElement(By.xpath("//parent::h5/following-sibling::button[2]")).click());
		//waitForElementToAppear(message);
		System.out.println(driver.findElement(message).getText());
		waitForElementToDisAppear(animate);
		
	}
	
	
	
}
