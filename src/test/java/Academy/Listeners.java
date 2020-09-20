package Academy;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.ExtentReporterNG;
import resources.base;



public class Listeners extends base implements ITestListener{
	ExtentTest test;			// creating global variable for extent report
	ExtentReports extent = ExtentReporterNG.getReportObject();

	// 1.1) adding threadlocal to handle to capture logs when multiple test are running in parallel
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();	
	
	@Override
	public void onTestStart(ITestResult result) {		
		test = extent.createTest(result.getMethod().getMethodName());   // this will get test method name as test name
		extentTest.set(test);   	// 1.2)  loading set into extentText
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.PASS, "Test Passed");	//	1.3) adding Threadlocal (to handle multiple test execution in parallel)
		}

	@Override
	public void onTestFailure(ITestResult result) {
		// This will capture screenshot on each test failure
		
		//	Extent report log recording
		extentTest.get().fail(result.getThrowable());			// This will attach logs for failed scripts
		
		//Screenshot capture 
		WebDriver driver = null;
		
		try {
			// Following will give access to the field in test case where it got failed
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (Exception e) {
			
		}
		
		// this will provide test method name.  We will pass this variable in takeScreenshot method in base.class
		String testMethodName = result.getMethod().getMethodName();  
		try {
			// Following is without attaching screenshot in Extent Report
//			TakeSreenshots(testMethodName, driver);		//passing testMethodName into TakeScreenshots methods in base.class
			
			/* 	Following will attach failed script screenshot into ExtentReport
			 * 	extentTest.get().addScreenCaptureFromPath   : add screenshot in ExtentReport for failed scripts
			 * 	TakeSreenshots(testMethodName, driver) : will take screenshot, method created in base class
			 * 	result.getMethod().getMethodName() : screenshot title
			 */
			extentTest.get().addScreenCaptureFromPath(TakeSreenshots(testMethodName, driver), result.getMethod().getMethodName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();		// This will flush (end) Extent Report logging
	}



}
