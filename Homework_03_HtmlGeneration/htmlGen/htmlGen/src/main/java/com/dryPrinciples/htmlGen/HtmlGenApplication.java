package com.dryPrinciples.htmlGen;


import java.util.Arrays;
import java.util.List;


import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HtmlGenApplication {

	public static void main(String[] args) {
		
		//Defining the name of the HTML file to be generated
		String fileName = "hw3.html";
		
		//Creating lists to represent semesters and course names
        List<String> semesters = Arrays.asList("Spring 2023", "Fall 2023");
        List<String> courseNames = Arrays.asList("Web Development", "Natural Language Process",
                "Database Systems", "Software Engineering", "Datastructure and Algorithms", "Database Design");

        //Generating HTML using the HtmlGenerator class and pass the semester and course name lists
        var html = HtmlGenerator.generateHTML(semesters, courseNames);

        //Writing the generated HTML content to the specified HTML file
        HtmlFileWriter.writeHTMLToFile(html, fileName);

        //Opening the generated HTML file in a web browser
        HtmlFileOpener.openHTMLFile(fileName);
	}

}
