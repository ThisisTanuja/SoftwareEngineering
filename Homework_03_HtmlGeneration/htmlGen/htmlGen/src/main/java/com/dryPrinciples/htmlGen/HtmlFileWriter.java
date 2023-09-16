package com.dryPrinciples.htmlGen;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import j2html.tags.ContainerTag;

public class HtmlFileWriter {
	
	//This method writes HTML content to a file
	public static void writeHTMLToFile(ContainerTag html, String fileName) {
		try {
			
			//Creating a fileWriter to write to the specified file
			FileWriter fileWriter = new FileWriter(fileName);
			
			//Creating a PrintWriter to write formatted text to the FileWriter
			PrintWriter printWriter = new PrintWriter(fileWriter);
			
			//Writing the HTML content to the file using the renderedFormatted() method of the containerTag 
			printWriter.println(html.renderFormatted());
			
			//Closing the PrintWriter and FileWriter to flush and release resources
			printWriter.close();
			fileWriter.close();
			
			//Printing a success message indicating that the HTML file has been generated
			System.out.println("HTML file '" + fileName + "' has been generated successfully!");
		}
		catch(IOException e) {
			
			//Printing an error message with the exception message if an IOException occurs during file writing
			System.err.println("Error writing to the file: " + e.getMessage());
		}
		
	}

}
