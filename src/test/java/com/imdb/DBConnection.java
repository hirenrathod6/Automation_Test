package com.imdb;

import io.appium.java_client.android.AndroidDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	public AndroidDriver driver;

	public DBConnection(AndroidDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	public static void connect(String movieName, String Rating) throws SQLException {
    	   
        //Class.forName("com.mysql.jdbc.Driver");
		System.out.println("MOvie Name Is" + movieName + "Rating is" + Rating);
		Connection con = DriverManager.getConnection("jdbc:sqlite:C://Users//admin//Desktop//sqllight//SQLiteStudio//ListOFMovieDataBase.db");
		java.sql.Statement stmt = con.createStatement();
		
		stmt = con.createStatement();
	      
		  String dropQuery = "DROP TABLE IF EXISTS 'MOVIES'";
		  stmt.executeUpdate(dropQuery);
		
	      String sql = "CREATE TABLE MOVIES" +
	    		       " PRIMARY KEY ( id ), " +
	                   " MovieName VARCHAR(255), " + 
	                   " Rating VARCHAR(255))";
	                   

	      stmt.executeUpdate(sql);
		
		String  sql1 = "SELECT * FROM MOVIES";
		
        stmt.executeUpdate(sql1);
        
        String  sql2 = "INSERT INTO MOVIES VALUES ('" +movieName+"',' "+Rating+"')";
        stmt.executeUpdate(sql2);
        System.out.println("Done");
    	
	}

}
