package stanalone.tests;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().window().fullscreen();
		driver.findElement(By.id("userEmail")).sendKeys("rajeshpok@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Abcd1234@");
		driver.findElement(By.id("login")).click();
		Wait wait=new WebDriverWait(driver, Duration.ofSeconds(4));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		List<String> WantedItems=Arrays.asList("ZARA COAT 3", "IPHONE 13  PRO");
		List<WebElement> items=driver.findElements(By.cssSelector("div.card-body h5 b"));
		
		//option1
//		for(int i = 0;i<items.size();i++) {
//			String itemName=items.get(i).getText();
//			if(WantedItems.contains(itemName)){
//				items.get(i).findElement(By.xpath("//div[\"card-body\"]/h5/b/parent::h5/following-sibling::button[2]")).click();
//			}
//			System.out.println(i);
//		}
		//option2
//		WebElement pro=items.stream().filter(item->item.getText().equalsIgnoreCase("ZARA COAT 3"))
//				.findFirst().orElse(null);
//		pro.findElement(By.xpath("//parent::h5/following-sibling::button[2]")).click();
		//option3
		items.stream().filter(item->WantedItems.contains(item.getText()))
		.forEach(s->s.findElement(By.xpath("//parent::h5/following-sibling::button[2]")).click());
		
		
		System.out.println(driver.findElement(By.cssSelector("#toast-container")).getText());
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> Selected=driver.findElements(By.cssSelector("div[class='cartSection'] h3"));
		Boolean v=Selected.stream().anyMatch(product->WantedItems.contains(Selected.get(0).getText()));
		System.out.println(v);
		Selected.stream().filter(product->WantedItems.contains(Selected.get(0).getText())).forEach(s->System.out.println(s.getText()));
		Assert.assertEquals(WantedItems.get(0), driver.findElement(By.cssSelector("div[class='cartSection'] h3")).getText());
		driver.findElement(By.xpath("//div/ul/li[3]/button")).click();
		
		driver.findElement(By.cssSelector(".form-group .text-validated")).sendKeys("ind");
//		List<WebElement> countries=driver.findElements(By.cssSelector(".ng-star-inserted"));
//		for(int i=0;i<countries.size();i++) {
//			String name=countries.get(i).getText();
//			if(name.equalsIgnoreCase("india")) {
//				countries.get(i).click();
//			}
//		}
//	    countries.stream().filter(name->name.getText().equalsIgnoreCase("india")).forEach(name->name.click());
		driver.findElement(By.cssSelector(".ta-item:nth-child(3)")).click();
		driver.findElement(By.cssSelector(".actions .btnn")).click();
		System.out.println(driver.findElement(By.cssSelector(".em-spacer-1 .ng-star-inserted")).getText());
		String cong=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(cong.equalsIgnoreCase("Thankyou for the order."));
		driver.quit();
	}
   
}
