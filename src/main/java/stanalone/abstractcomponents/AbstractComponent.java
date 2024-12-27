package stanalone.abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import stanalone.pageobjects.CartPageObjects;
import stanalone.pageobjects.OrderPageObjects2;

public class AbstractComponent {
	WebDriver driver;
public AbstractComponent(WebDriver driver) {
	this.driver=driver;
	PageFactory.initElements(driver, this);
}
@FindBy(css="[routerlink*='cart']")
WebElement cartButton;
@FindBy(css="[routerlink*='myorders']")
WebElement orderButton;
public void waitForElementToAppear(By find) {
	Wait wait=new WebDriverWait(driver, Duration.ofSeconds(4));
	wait.until(ExpectedConditions.visibilityOfElementLocated(find));
	
}
public void waitForElementToDisAppear(By find) {
	Wait wait=new WebDriverWait(driver, Duration.ofSeconds(3));
	wait.until(ExpectedConditions.invisibilityOfElementLocated(find));
	
}
public void waitForWebElementToAppear(WebElement element) {
	Wait wait=new WebDriverWait(driver, Duration.ofSeconds(3));
	wait.until(ExpectedConditions.visibilityOf(element));
	
}
public CartPageObjects goToCart() {
	cartButton.click();
	CartPageObjects cartPage=new CartPageObjects(driver);
	return cartPage;
}
public OrderPageObjects2 goToOrder() {
	orderButton.click();
	OrderPageObjects2 orderPage=new OrderPageObjects2(driver);
	return orderPage;
}
public void scrolling(int x) {
	JavascriptExecutor scroll=(JavascriptExecutor)driver;
	scroll.executeScript("window.scrollBy(0,"+x+")");
}
}
