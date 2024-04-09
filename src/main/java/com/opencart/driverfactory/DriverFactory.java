package com.opencart.driverfactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.opencart.frameworkException.CustomException;



public class DriverFactory {
	
	public WebDriver driver;
	Properties prop;
	 private static ThreadLocal<WebDriver> tldriver=new ThreadLocal<WebDriver>();
	public WebDriver initDriver(Properties prop) {
		
		System.out.println("The browser from config: "+prop.getProperty("browser"));
		//String browserNameProp=prop.getProperty("browser").toLowerCase().trim();
		String browserName=System.getProperty("browser");
		System.out.println("The browser is from env: "+ browserName);
		if(browserName==null) {
			browserName=prop.getProperty("browser");
		}
		switch(browserName.toLowerCase().trim()) {
		case "chrome":
			String driverPath="C:\\Tools\\TestingTools\\drivers\\chrome\\chromedriver.exe";
			System.out.println(driverPath);
			System.setProperty("webdriver.chrome.driver", driverPath);
			    driver=new ChromeDriver();
			    tldriver.set(driver);
			    break;
		case "firefox":
			    driver=new FirefoxDriver();
			    tldriver.set(driver);
			    break;
		case "safari":
		    driver=new SafariDriver();
		    tldriver.set(driver);
		    break;
	    case "edge":
		    driver=new EdgeDriver();
		    tldriver.set(driver);
		    break;
		 default:
			 System.out.println("Please enter correct browser");
			     
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
		String env=prop.getProperty("env");
		switch(env){
		case "qa":
			driver.get(prop.getProperty("qa.URL"));
			break;
		case "prod":
			driver.get(prop.getProperty("prod.URL"));
			break;
		default:
			break;
		}
		//driver.get(prop.getProperty("URL"));
		return driver;
		
	}
	
	
	
	public Properties initProperty() {
	
//		Properties prop=new Properties();
//		FileInputStream fis=null;
//		String envName=System.getProperty("env");
//		System.out.println("Running test cases on environment  "+ envName);
//		try {
//		if(envName==null) {
//			System.out.println("No env given so runnning on QA environment");
//			System.out.println(" Properties file path"+".\\src\\main\\resources\\config\\qa.config.properties");
//				fis=new FileInputStream(".\\src\\main\\resources\\config\\qa.config.properties");
//		} else {
//			
//		       switch(envName.toLowerCase().trim()) {
//		       case "qa":
//		    	   fis=new FileInputStream(".\\src\\main\\resources\\config\\qa.config.properties");
//		    	   break;
//		       case "dev":
//		    	   fis=new FileInputStream(".\\src\\main\\resources\\config\\dev.config.properties");
//		    	   break;
//		       case "stage":
//		    	   fis=new FileInputStream(".\\src\\main\\resources\\config\\stage.config.properties");
//		    	   break;
//		       case "prod":
//		    	   fis=new FileInputStream(".\\src\\main\\resources\\config\\prod.config.properties");
//		    	   break;
//		    	   default:
//		    		   System.out.println("Please send a proper envoirnment information");
//		    		   throw new CustomException("InValid environment");
//		       }
//		
//				
//		}
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		     try {
//				prop.load(fis);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
		/**
		 *  With 
		 */
//		Properties prop=new Properties();
//	FileInputStream fis=null;
//	try {
//		fis=new FileInputStream(".\\src\\main\\resources\\config\\config.properties");
//		
//			prop.load(fis);
//			String envName=prop.getProperty("env");
//			System.out.println("Running test cases on environment  "+ envName);
//			switch(envName.toLowerCase().trim()) {
//		       case "qa":
//		    	   fis=new FileInputStream(".\\src\\main\\resources\\config\\qa.config.properties");
//		    	   break;
//		       case "dev":
//		    	   fis=new FileInputStream(".\\src\\main\\resources\\config\\dev.config.properties");
//		    	   break;
//		       case "stage":
//		    	   fis=new FileInputStream(".\\src\\main\\resources\\config\\stage.config.properties");
//		    	   break;
//		       case "prod":
//		    	   fis=new FileInputStream(".\\src\\main\\resources\\config\\prod.config.properties");
//		    	   break;
//		    	   default:
//		    		   System.out.println("Please send a proper envoirnment information");
//		    		   throw new CustomException("InValid environment");
//			
//			}
//			}catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	prop=new Properties();
		FileInputStream fis=null;
		
		try {
			fis=new FileInputStream(".\\src\\main\\resources\\config\\config.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return prop;
		
	}


	 public String getProperty(String key) {
	        return prop.getProperty(key);
	    }

	    public String getUsername(String environment) {
	      
				return prop.getProperty(environment + ".username");
			
	    }

	    public String getPassword(String environment) {
	      
				return prop.getProperty(environment + ".password");
			
	    }
	    
		public synchronized static WebDriver getDriver() {
			return tldriver.get();
		}

		/********Take screenshot
		 * @throws WebDriverException *****************/
		
		public static String getScreenshot() {
			File srcFile;

			String path = null;
			
			
					srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
				
				
			
			 path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
			File destination = new File(path);

			
				try {
					FileUtils.copyFile(srcFile, destination);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			

			return path;
		}
		


}
