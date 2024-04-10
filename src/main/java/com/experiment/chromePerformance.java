package com.experiment;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

public class chromePerformance {
	 public static void main(String[] args) {
	        ChromeOptions options = new ChromeOptions();
	        options.addArguments("--enable-logging"); // Enable logging
	        options.addArguments("");
	      // Required for non-W3C compliant browsers like Chrome
          int i=10;
          int b=20;
          int c=i+b;
          System.out.println("the value of c is"+ c);
	        WebDriver driver = new ChromeDriver(options);
	        driver.get("https://google.com/");
	        // Enable performance logging
	        LogEntries logs = driver.manage().logs().get(LogType.PERFORMANCE);

	        // Print performance log entries
	        for (LogEntry entry : logs) {
	            System.out.println(entry.getMessage());
	        }
          
	        driver.quit();
	    }
}
