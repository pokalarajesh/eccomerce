package stanalone.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentRep {
	public static ExtentReports reports() {
		String path=System.getProperty("user.dir")+"//reports//index.html";
		ExtentSparkReporter report=new ExtentSparkReporter(path);
		report.config().setReportName("raj");
		report.config().setDocumentTitle("rajtitle");
		
		ExtentReports extent= new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("test", "raji");
		return extent;
	}



}
