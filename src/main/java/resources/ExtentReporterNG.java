package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	static ExtentReports extent;
	
	public static ExtentReports getReportObject() {
//		public static void main (String[] args) {
	
		String path = System.getProperty("user.dir")+"\\reports\\index.html";
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Tesst");
		reporter.config().setDocumentTitle("Automatin results");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Pradeep B");
//		System.out.println(path);
		return extent;
	}
}
