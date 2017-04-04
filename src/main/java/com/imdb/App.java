package com.imdb;

import com.imdb.AppiumServer;
import com.imdb.Data;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.ExecuteException;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

      
import io.appium.java_client.android.AndroidDriver;

public class App {
	
	 protected AppiumServer appiumServer;
	 AndroidDriver driver;
	    
	    protected AppiumServer getAppiServerInstance() {
	        if (appiumServer == null) {
	            appiumServer = new AppiumServer();
	        }
	        return appiumServer;
	    }
	    AppiumServer appium = new AppiumServer();
	
	 @BeforeSuite
	public void launch() throws InterruptedException, Exception {
		
		 appium.startServer();
		 Thread.sleep(5000);
		 File appDir = new File("src/main/java");
		 File app = new File(appDir,"Imdb.apk");
		 
		 DesiredCapabilities capabilities = new DesiredCapabilities();
	     capabilities.setCapability("deviceName", "MoTo E");
	     capabilities.setCapability("app", app.getAbsolutePath());
	     capabilities.setCapability("appPackage", "com.imdb.mobile");
	    capabilities.setCapability("appActivity","com.imdb.mobile.HomeActivity");
	     capabilities.setCapability("platformVersion", "4.4.4");
	     capabilities.setCapability("platformName", "Android");
	     driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	     driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);

	}
	
	 @AfterSuite
		public void exit()throws ExecuteException, Exception {
			driver.quit();
			appium.stopServer();
	}
	 
	 @Test
	 public void launchImdbApp(){
		 Data page = new Data(driver);
		 
			page.clickOnNotNow();
			try {
				page.clickOnDeviceLocation();
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		 try {
			 page.clickOnCancel();
			 System.out.println("Kindly check your interent");
			driver.navigate().back();
		} catch (Exception e) {
			// TODO: handle exception
		}
		 
	 }
	 
	 @Test
	 public void imdTest1() throws InterruptedException, SQLException{
		 Data page = new Data(driver);
//		 page.clickOnDrawer();
		 page.selectMovieOption();
		 Thread.sleep(5000);
		 page.selectTopRatedMovies();
		 page.DBConnectio();
		 page.listOfMovies();
		 driver.navigate().back();
		driver.closeApp();
	 }

}
 