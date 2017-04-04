package com.imdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;

public class Data {
    AndroidDriver driver;
    
    By notNow       = By.id("com.imdb.mobile:id/splash_not_now");
    By cancel       = By.name("Cancel");
    By drawer 		= By.name("Open Drawer");
	By movies 		= By.name("Movies");
	By rated 		= By.name("Top Rated Movies");
	By recyclerView = By.id("com.imdb.mobile:id/list");
	By movieName	= By.id("com.imdb.mobile:id/label");
	By seeAll       = By.name("See all");
	By gpsAccess    = By.name("Allow");
	

	static java.sql.Statement stmt;
	static Connection con;

	public Data(AndroidDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}
	
	public void clickOnNotNow(){
		driver.findElement(notNow).click();
	}
	
	public void clickOnDeviceLocation(){
		driver.findElement(gpsAccess).click();
	}
	
	public void clickOnCancel(){
		driver.findElement(cancel).click();
	}

	public void clickOnDrawer(){
    	driver.findElement(drawer).click();
    	
    }
    
    public void selectMovieOption(){
    	driver.findElement(movies).click();
    }
    
    public void selectTopRatedMovies(){
//    	driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+rated+"\").instance(0))");
    	org.openqa.selenium.Dimension dimension;
    	dimension = driver.manage().window().getSize();
 	  
// 			  System.out.println("Before swipe");
 			  int startx 	= (dimension.width/2);
 			  int endy 		= (int) (dimension.height *0.2);
 			  int starty 	= (int) (dimension.height * 0.9); 
 			
 			  for(int i = 0; i<=3; i++ ){
    	driver.swipe(startx, starty, startx, endy, 3000);
 			  }
    	driver.findElement(rated).getText();
    	driver.findElement(seeAll).click();
    	
    	WebElement size = driver.findElementByClassName("android.widget.LinearLayout");
    	System.out.println(size);
    }
    
    public static void DBConnectio() throws SQLException{
    	 con = DriverManager.getConnection("jdbc:sqlite:C://Users//admin//Desktop//sqllight//SQLiteStudio//ListOFMovieDataBase.db");
		 stmt = con.createStatement();
		
		stmt = con.createStatement();
	      
		  String dropQuery = "DROP TABLE IF EXISTS 'MOVIES'";
		  stmt.executeUpdate(dropQuery);
		
	      String sql = "CREATE TABLE MOVIES" +
	                   " (Number VARCHAR(255), " + 
	                   " MovieName VARCHAR(255), " +
	                   " Year INTEGER, " +
	                   " Rating VARCHAR(255))";
//	                   " PRIMARY KEY ( id ))"; 
	    stmt.executeUpdate(sql);
    }
    
    public static void connect(String number, String movieName, String year, String Rating) throws SQLException {
 	   
        //Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Movie name is" + movieName + "Rating is" + Rating);
		/*Connection con = DriverManager.getConnection("jdbc:sqlite:C://Users//admin//Desktop//sqllight//SQLiteStudio//ListOFMovieDataBase.db");
		java.sql.Statement stmt = con.createStatement();*/
		
		stmt = con.createStatement();
       
		String  sql2 = "INSERT INTO MOVIES VALUES ('" +number+"','" +movieName+"',' "+year+"',' "+Rating+"')";
 //      String  sql2 = "INSERT INTO MOVIES VALUES (1,'TumBin','9.4')";
        stmt.executeUpdate(sql2);
        System.out.println("Done");
       }
    
    public void listOfMovies() throws InterruptedException, SQLException{
    	String a = "250. Pirates of the Caribbean: The Curse of the Black Pearl (2003)"; /// last element in the list
    	Boolean found = false;
    	while (!found){

            List<WebElement> ele = driver.findElements(By.id("com.imdb.mobile:id/label"));
            int size=0;
             size = size+ele.size();
             //System.out.println(size);
            for (int i = 0; i < size; i++) {
                String movieName;
                String rating = "";
                String number;
                String year;
                
               movieName = ((WebElement) driver.findElementsById("com.imdb.mobile:id/label").get(i)).getText();
                
               number = movieName.split("\\.")[0];
               
               String movieNameArray = movieName.split("\\.")[1];
       		   String actualMovieName = movieNameArray.split("\\(")[0];
//               number = number.substring(0, number.length() - 4);
               year = movieName.substring(movieName.length() - 6);
               year = year.replaceAll("\\D+", "");
//               connect(number,movieName.replaceAll("[^a-zA-Z]+", ""),year, rating);
               
               try {
            	   rating = ((WebElement) driver.findElementsById("com.imdb.mobile:id/imdb_rating").get(i)).getText();
            	   connect(number,actualMovieName.replace("'", ""),year, rating);
            	   
			} catch (Exception e) {
				// TODO: handle exception
//				System.out.println(e);
				rating = null;
			}
                
               if(rating != null){
         
                //System.out.print(movieName);
                //System.out.println("------" + rating);
               }else{
            	   continue;
               }
                
                if (movieName.equals(a)) {
                    
                    found =true;
                    System.out.println(size);
                    String query = "DELETE FROM MOVIES WHERE rowid IN ( SELECT min(rowid) FROM MOVIES GROUP BY MovieName HAVING COUNT(MovieName)>1 )";
                    stmt.executeUpdate(query);
                    break;
                }

            }
            if(!found){
            	org.openqa.selenium.Dimension dimension;
            	dimension = driver.manage().window().getSize();
         	  
//         			  System.out.println("Before swipe");
         			  int startx 	= (dimension.width/2);
         			  int endy 		= (int) (dimension.height *0.1);
         			  int starty 	= (int) (dimension.height * 0.9);
//         			  int endy		= dimension.height;	
//         			  System.out.println("Before swipe Starts");
         			  Thread.sleep(5000);
//         			  driver.swipe(125, generaty(driver.scrollTo(Submit).getLocation()), 465,generaty(driver.scrollTo(Submit).getLocation()) , 2000);
//         			 System.out.println("startx = " + startx + " ,endy = " + endy + " , starty = " + starty);
         			  Thread.sleep(5000);
         			  
         		    driver.swipe(startx, starty, startx, endy, 3000);
//         		 
    
            }
    	}
    }
}
 