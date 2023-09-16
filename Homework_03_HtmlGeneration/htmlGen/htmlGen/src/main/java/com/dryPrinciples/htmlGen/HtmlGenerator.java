package com.dryPrinciples.htmlGen;

import java.util.Collections;
import java.util.List;

import j2html.tags.ContainerTag;
import static j2html.TagCreator.h1;
import static j2html.TagCreator.html;
import static j2html.TagCreator.head;
import static j2html.TagCreator.title;
import static j2html.TagCreator.style;
import static j2html.TagCreator.body;
import static j2html.TagCreator.div;
import static j2html.TagCreator.table;
import static j2html.TagCreator.tr;
import static j2html.TagCreator.th;
import static j2html.TagCreator.td;


public class HtmlGenerator {
	//This method generates an HTML structure for displaying course history
	public static ContainerTag generateHTML(List<String> semesters, List<String> courseNames) {
	    return html(
	        head(
	        		
	        	//Setting the title of the HTML page
	            title("Courses taken"),
	            
	            //Defining inline CSS styles for various elements
	            style(
	            	"table { width: 90%; margin: 20px auto; font-size: 35px; }" + 
	            	"table, th, td { border: 2px solid; }" + 
	            	"th, td { border: 2px double black; padding: 12px; text-align: left; }" + 
	            	"th {font-weight: bold; }" + 
	            	"h1{ text-align: center; }"
	            )
	        ),
	        body(
	        		
	        	//Adding level 1 heading 
	            h1("My Course History"),
	            table()
	            
	            		//Adding table headers
                        .with(headerRow())
                        
                        //Adding course data rows
                        .with(courseRows(semesters, courseNames))
                ));
	}
	
	//This method generates the header row of the table
	private static ContainerTag headerRow() {
		return tr(
				
				//Table header for Semester
				th("Semester"), 
				
				//Table header for Course
				th("Course"));
	}
	
	//This method generates the data rows for the courses
	private static ContainerTag courseRows(List<String> semesters, List<String> courseNames) {
		
		//Initializing a new ContainerTag and assigns it a 'div' element
		ContainerTag rows = div();
		
		for(int i = 0; i < semesters.size(); i++) {
			String semester = semesters.get(i);
			List<String> selectedCourses;
			
			//Determining which courses belong to the current semester
			if (semester.equals("Spring 2023")) {
		            //Selecting the first three courses for Spring
		            selectedCourses = courseNames.subList(0, 3);
		    } else if (semester.equals("Fall 2023")) {
		            //Selecting the last three courses for Spring
		            selectedCourses = courseNames.subList(3, 6);
		    } else {
		            //Handling other semesters if needed (no courses selected)
		            selectedCourses = Collections.emptyList();
		    }
			
			//Creating a table row for each selected course
			for(String courseName : selectedCourses) {
				rows.with(createTableRow(semester, courseName));
			}
		}
		return rows;
	}

	
	//This method created a table row with semester and course name
	private static ContainerTag createTableRow(String semester, String courseName) {
	    return tr(
	    	
	    	//Semester data cell
	        td(semester),
	        
	        //Course name data cell
	        td(courseName)
	    );
	}


	
}
