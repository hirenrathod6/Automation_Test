package com.imdb;

import io.appium.java_client.android.AndroidDriver;

import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;

public class AppiumServer {
	

	
		public void startServer() throws ExecuteException, IOException{
	
//	CommandLine command = new CommandLine("cmd");
	CommandLine command = new CommandLine("C://Program Files (x86)//Appium//node.exe");
//	command.addArgument("/c");
//	command.addArgument("C://Program Files (x86)//Appium//node.exe");
	command.addArgument("C://Program Files (x86)//Appium//node_modules//appium//bin//appium.js");
	command.addArgument("--address");
	command.addArgument("127.0.0.1");
	command.addArgument("--port");
	command.addArgument("4723");
	command.addArgument("--no-reset");
	command.addArgument("--log");
	command.addArgument("C://Users//admin//Desktop//logs//appiumLogs2.txt");
	DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
	DefaultExecutor executor = new DefaultExecutor();
	executor.setExitValue(1);
	executor.execute(command, resultHandler);
}
	  public  void stopServer() throws IOException {  
	        CommandLine command = new CommandLine("cmd");  
	        command.addArgument("/c");  
	        command.addArgument("Taskkill /F /IM node.exe");  
	      
	        DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();  
	        DefaultExecutor executor = new DefaultExecutor();  
	        executor.setExitValue(1);  
	        executor.execute(command, resultHandler);  
	        
	    }
//	  public void main 
	
}