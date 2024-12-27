package stanalone.testcomponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import stanalone.pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage page;
	public WebDriver initilizeDriver() throws IOException {
		Properties prop=new Properties();
		FileInputStream fs=new FileInputStream("C:\\Users\\prajesh\\eclipse-workspace\\Eccommerce\\src\\main\\java\\stanalone\\resources\\global.properties");
		prop.load(fs);
		String browserName=System.getProperty("browser")==null?prop.getProperty("browser"):System.getProperty("browser");
		//String browserName=prop.getProperty("browser");
		if(browserName.contains("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options=new ChromeOptions();
			if(browserName.contains("maximize")) {
				options.addArguments("headless");
				
			}
			driver=new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900));
		}else if(browserName.equalsIgnoreCase("firefox")) {
			
		}else if(browserName.equalsIgnoreCase("edge")) {
			
		}else {
			
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}
	
	public List<HashMap<String, String>> getJsonDataToHashMap(String filepath) throws IOException {
		//reading json file
		String jsonContent=FileUtils.readFileToString(new File(filepath),
				StandardCharsets.UTF_8);
		//converting using jackson databind
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;

	}
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		driver=initilizeDriver();
		page=new LandingPage(driver);
		page.got();
		return page;
	}
	@AfterMethod(alwaysRun=true)
	public void closeBrowser() {
		driver.quit();
	}
}
