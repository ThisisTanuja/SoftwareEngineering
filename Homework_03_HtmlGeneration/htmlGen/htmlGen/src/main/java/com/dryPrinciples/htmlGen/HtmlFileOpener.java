package com.dryPrinciples.htmlGen;

import java.io.File;
import java.io.IOException;

public class HtmlFileOpener {
	
	//This method is used to open an HTML file in a web browser
	public static void openHTMLFile(String fileName) {
		try {
			
			//Creating a file object for the specified HTML file
			File htmlFile = new File(fileName);
			
			//Checking if the HTML file exists
			if(htmlFile.exists()) {
				
				//getting the operating system name
				String osName = System.getProperty("os.name").toLowerCase();
				
				//Checking if the operating system is Windows
				
				//Using a ProcessBuilder to execute a command that opens the file
				//in the default web browser, if yes
				if(osName.contains("win")) {
					ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "start", fileName);
					
					//Setting the working directory to the current user's directory
					processBuilder.directory(new File(System.getProperty("user.dir")));
					
					//Starting the process to open the HTML file
					processBuilder.start();
				}
				else {
					
					//Printing an error message if the operating system is not Windows
					System.err.println("Opening in a web broser is not supported on this platform!");
				}
			}
			else {
				
				//Printing an error message if the HTML file doesn't exist
				System.err.println("HTML file '" + fileName + "' not found!");
			}
		}
		catch(IOException e) {
			
			//Printing an error message if an IOException occurs
			System.err.println("Error opening the HTML file: " + e.getMessage());
		}
	}

}
