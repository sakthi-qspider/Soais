package Autonomiq1;



	

	import java.util.regex.Pattern;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
	import java.io.File;
	import java.io.FileInputStream;
	import java.util.ArrayList;
	import java.util.HashMap;
	import org.apache.commons.io.FileUtils;
	import org.apache.poi.hssf.usermodel.HSSFSheet;
	import org.apache.poi.hssf.usermodel.HSSFWorkbook;
	import org.apache.poi.ss.usermodel.Cell;
	import org.apache.poi.ss.usermodel.DataFormatter;
	import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.SystemOutLogger;

import java.util.concurrent.TimeUnit;
	import org.testng.annotations.AfterClass;
	import org.testng.annotations.Test;
	import static org.testng.Assert.*;
	import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.remote.CapabilityType;
	import org.openqa.selenium.remote.DesiredCapabilities;
	import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
	import org.testng.Reporter;
	public class Testing1 {
		private WebDriver driver = null;
		DesiredCapabilities dc;
		private StringBuffer verificationErrors = new StringBuffer();
		String frameID = "ptModframe_";
		int index = 0;
	

		@AfterClass
		public void tearDown() throws Exception {
			//driver.quit();
			String verificationErrorString = verificationErrors.toString();
			if (!"".equals(verificationErrorString)) {
				fail(verificationErrorString);
			}
		}

		public HashMap<String, ArrayList<String>> readTestDataFile (String filename) {
			FileInputStream fis = null;
			BufferedReader br = null;
			String line = "";
			String cvsSplitBy = ",";
			HashMap<String, ArrayList<String>> values = new HashMap<String, ArrayList<String>>();
			try {
				fis = new FileInputStream(new File(filename));
				HSSFWorkbook workbook = new HSSFWorkbook(fis);
				HSSFSheet sheet = workbook.getSheetAt(0);
				DataFormatter formatter = new DataFormatter();
				int totanumberofcolumns = 0;
				Row rowkey = sheet.getRow(0);
				totanumberofcolumns = rowkey.getLastCellNum();
				for (int rowindex = 1; rowindex <= sheet.getLastRowNum(); rowindex++) {
					Row rowvalue = sheet.getRow(rowindex);
					String key = null;
					for (int colindex = 0; colindex < totanumberofcolumns; colindex++) {
						Cell cellkey = rowkey.getCell(colindex);
						key = formatter.formatCellValue(cellkey);
						String value = null;
						Cell cellvalue = rowvalue.getCell(colindex);
						value = formatter.formatCellValue(cellvalue);
						if (!values.containsKey(key)) {
							ArrayList<String> valuearray = new ArrayList<String>();
							valuearray.add(value);
							values.put(key, valuearray);
							}
						else {
							values.get(key).add(value);
						}
					}
				}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
				}
			return values;
		}

		public void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{
			TakesScreenshot scrShot =((TakesScreenshot)webdriver);
			File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
			File DestFile=new File(fileWithPath);
			FileUtils.copyFile(SrcFile, DestFile);
		}
		@Test
		public void test_case_2() throws Exception {
			/*try{
			switch (System.getProperty("browser")) {
				case "chrome":
					System.out.println("Using chrome for execution...");
					dc = DesiredCapabilities.chrome();
					break;
				case "firefox":
					System.out.println("Using firefox for execution...");
					dc = DesiredCapabilities.firefox();
					break;
				default:
					dc = DesiredCapabilities.chrome();
				break;
			}


			switch (System.getProperty("platform")) {
				case "windows":
					dc.setCapability(CapabilityType.PLATFORM, Platform.WINDOWS);
					break;
					case "linux":
					dc.setCapability(CapabilityType.PLATFORM, Platform.LINUX);
					break;
				default:
					break;
			}
*/

/*
			try {
				driver = new RemoteWebDriver(new java.net.URL("http://18.216.57.194:4444/wd/hub"), dc);
				Reporter.getCurrentTestResult().setAttribute("hubUrl","http://18.216.57.194:4444/wd/hub");
				Reporter.getCurrentTestResult().setAttribute("sessionId", ((RemoteWebDriver)driver).getSessionId());
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			} catch (MalformedURLException e1){
				e1.printStackTrace();
			}
*/			
			System.setProperty("webdriver.gecko.driver","./Driver/geckodriver.exe");
			driver=new FirefoxDriver();
			//System.setProperty("webdriver.chrome.driver","./driver/chromedriver.exe");
			//driver=new ChromeDriver();
			
		//	System.setProperty("webdriver.ie.driver","./Driver/IEDriverServer.exe");
			//driver= new InternetExplorerDriver();
			
			
			
			driver.get("http://soais-d71:8085/psp/HCM92DMO/EMPLOYEE/HRMS?&cmd=login&errorCode=106&languageCd=ENG");
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
			HashMap<String, ArrayList<String>> hash = readTestDataFile ("data/testdata_TC_AddDepartment.xls");
			driver.findElement(By.xpath("//label[@for='userid']/ancestor::div[@class='ps_signinentry']//input[@name='userid']")).clear();
			driver.findElement(By.xpath("//label[@for='userid']/ancestor::div[@class='ps_signinentry']//input[@name='userid']")).sendKeys(hash.get("User ID").get(0));
			driver.findElement(By.xpath("//label[@for='pwd']/ancestor::div[@class='ps_signinentry']//input[@name='pwd']")).clear();
			driver.findElement(By.xpath("//label[@for='pwd']/ancestor::div[@class='ps_signinentry']//input[@name='pwd']")).sendKeys(hash.get("Password").get(0));
			driver.findElement(By.xpath("//input[@name='Submit']")).click();
			Thread.sleep(3000);
		
			driver.findElement(By.xpath("//ul[@class='pthnav']/li[3]/a")).click();
			
			//Autonomiq Xpath.
			
			//driver.findElement(By.xpath("//div[@class='pthnavscroll']/ul/li[26]/a")).clear();
			
			driver.findElement(By.xpath("//div[@class='pthnavscroll']/ul/li[26]/a")).click();
			
			//Autonomiq Xpath.
			
			//driver.findElement(By.xpath("//div[@class='pthnavscroll']/ul/li[26]/a")).sendKeys(hash.get("Set ID").get(0));
		
			driver.findElement(By.xpath("//div[@aria-labelledby='pthnavbca_PORTAL_ROOT_OBJECT']/div[1]/ul/li/div[3]/div[2]/div[2]/ul/li[4]/a")).click();
			driver.findElement(By.xpath("//div[@aria-labelledby='pthnavbca_PORTAL_ROOT_OBJECT']/div[1]/ul/li/div[3]/div[2]/div[2]/ul/li[4]/div[3]/div[2]/div[2]/ul/li[2]/a")).click();
			driver.findElement(By.xpath("//div[@aria-labelledby='pthnavbca_PORTAL_ROOT_OBJECT']/div[1]/ul/li/div[3]/div[2]/div[2]/ul/li[4]/div[3]/div[2]/div[2]/ul/li[2]/div[3]/div[2]/div[2]/ul/li[16]/a")).click();
			
			
		//Switch to frame.
		
		     driver.switchTo().frame("ptifrmtgtframe");
		    Thread.sleep(1000);
		    
		    //Click on Add a New Value.
		    driver.findElement(By.xpath(".//*[@id='ICTAB_1']/span")).click();
		
		     Thread.sleep(2000);
		     
		     // Enter the Department Name
		     driver.findElement(By.xpath(".//div[@id='win0divDEPT_TBL_DEPTID']/input[@id='DEPT_TBL_DEPTID']")).sendKeys("Dept67");
		 	//Autonomiq Xpath.
			
			//driver.findElement(By.xpath("//label[@for='DEPT_TBL_SETID']/parent::td/parent::tr//input[@name='DEPT_TBL_SETID']")).clear();
			
	        Thread.sleep(2000);
	        
	        // Click on Add button.
			driver.findElement(By.xpath(".//*[@id='#ICSearch']")).click();
			
			// Enter the Descriptions.
			
			driver.findElement(By.xpath(".//*[@id='DEPT_TBL_DESCR$0']")).sendKeys("SOA IT Solutions Pvt limited");
			
			
			System.out.println("Descriptions is  clicked");
			
			//Autonomiq Xpath.
			//driver.findElement(By.xpath("//label[@for='DEPT_TBL_SETID']/parent::td/parent::tr//input[@name='DEPT_TBL_SETID']")).click();
			
			
		
			//Click on Location set ID Search image
		  driver.findElement(By.xpath(".//*[@id='DEPT_TBL_SETID_LOCATION$prompt$0']/img")).click();
		
		
           driver.switchTo().defaultContent();
           Thread.sleep(3000);
           
           driver.switchTo().frame("ptModFrame_0");
           
           driver.findElement(By.xpath(".//*[@id='RESULT0$64']")).click();
	
	      Reporter.log("control is pass",true);
	      
	    //Autonomiq Xpath.
	
		//	driver.findElement(By.xpath("//label[@for='DEPT_TBL_SETID']/parent::td/parent::tr//input[@name='DEPT_TBL_SETID']")).sendKeys(hash.get("Set ID").get(0));
	
	      driver.switchTo().defaultContent();
	    System.out.println("Control is ptifrmtgtframe");
	    
	      Thread.sleep(2000);
	    driver.switchTo().frame("ptifrmtgtframe");
	    
	    driver.findElement(By.id("DEPT_TBL_LOCATION$prompt$0")).click();
	    
	    System.out.println(" PopUp frame is clicked");
	    
	   
	    
	    driver.switchTo().defaultContent();
	    System.out.println("Control comes into defaultframe");
	    Thread.sleep(1000);
	    
	    driver.switchTo().frame("ptModFrame_1");
	    System.out.println("Control goes to ptModFrame_1 ");
	    
	    
	    driver.findElement(By.xpath(".//*[@id='RESULT1$5']")).click();
	    
	    System.out.println("Traget Element is Clicked");
	    

	      driver.switchTo().defaultContent();
	    System.out.println("Control is ptifrmtgtframe");
	    
	      Thread.sleep(2000);
	    driver.switchTo().frame("ptifrmtgtframe");
	    driver.findElement(By.id("DEPT_TBL_COMPANY$prompt$0")).click();
	    System.out.println(" PopUp frame is clicked");
	    
 
	    
	    driver.switchTo().defaultContent();
	    System.out.println("Control comes into defaultframe");
	    Thread.sleep(1000);
	    
	    driver.switchTo().frame("ptModFrame_2");
	    System.out.println("Control goes to ptModFrame_2 ");
	    
	    
	    driver.findElement(By.xpath(".//*[@id='RESULT0$296']")).click();
	    
	    System.out.println("Traget Element is Clicked");
	    
	    Thread.sleep(1000);
	    
	    driver.switchTo().defaultContent();
	    System.out.println("Controls returns to Main window");
	    
	    driver.switchTo().frame("ptifrmtgtframe");
	   
	   
	    driver.findElement(By.id("#ICSave")).click();
	    
	    System.out.println("Department is created");
	    
	      
	      
		/*System.exit(0);
		
			driver.findElement(By.xpath("//label[@for='DEPT_TBL_SETID']/parent::td/parent::tr//input[@name='DEPT_TBL_SETID']")).clear();
			driver.findElement(By.xpath("//label[@for='DEPT_TBL_SETID']/parent::td/parent::tr//input[@name='DEPT_TBL_SETID']")).sendKeys(hash.get("Set ID").get(0));
			
		System.exit(0);
			driver.findElement(By.xpath("//input[@name='#ICSearch']")).clear();
			driver.findElement(By.xpath("//input[@name='#ICSearch']")).sendKeys(hash.get("Set ID").get(0));
			driver.findElement(By.xpath("//label[@for='DEPT_TBL_SETID']/parent::td/parent::tr//input[@name='DEPT_TBL_SETID']")).clear();
			driver.findElement(By.xpath("//label[@for='DEPT_TBL_SETID']/parent::td/parent::tr//input[@name='DEPT_TBL_SETID']")).sendKeys(hash.get("Set ID").get(0));
			driver.findElement(By.xpath("//label[@for='DEPT_TBL_DEPTID']/parent::td/parent::tr//input[@name='DEPT_TBL_DEPTID']")).clear();
			driver.findElement(By.xpath("//label[@for='DEPT_TBL_DEPTID']/parent::td/parent::tr//input[@name='DEPT_TBL_DEPTID']")).sendKeys(hash.get("Department").get(0));
			driver.findElement(By.xpath("//input[@name='#ICSearch']")).clear();
			driver.findElement(By.xpath("//input[@name='#ICSearch']")).sendKeys(hash.get("Password").get(0));
			driver.findElement(By.xpath("//a[@name='RESULT1$5']")).click();
			driver.findElement(By.xpath("//a[@name='RESULT3$259']")).click();
		*/	}//finally{
				//tearDown();
			//}
		}
	



