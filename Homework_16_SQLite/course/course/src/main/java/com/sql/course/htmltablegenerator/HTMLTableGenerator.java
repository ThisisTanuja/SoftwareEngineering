package com.sql.course.htmltablegenerator;

import com.sql.course.model.Course;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class HTMLTableGenerator {

	//a constant string defining the HTML table headers
    private static final String TABLE_HEADERS = "<tr>\n"
            + "<th>Semester</th>\n"
            + "<th>Course</th>\n"
            + "<th>Instructor</th>\n"
            + "<th>Location</th>\n"
            + "</tr>\n";

    //method to generate HTML table from a list of Course objects
    public String generateHtmlTable(List<Course> courses) throws IOException {
        StringBuilder htmlBuilder = new StringBuilder();

        //starting the HTML document and add the defined headers
        htmlBuilder.append(startHtmlDocument());
        htmlBuilder.append(TABLE_HEADERS);

        //iterating over each Course object and formatting it as an HTML row
        for (Course course : courses) {
            htmlBuilder.append(formatCourseRow(course));
        }

        //closing the HTML document by appending the closing tags
        htmlBuilder.append(endHtmlDocument());

        //writing the HTML content to a file and return the file path
        return writeHtmlToFile(htmlBuilder.toString());
    }

    //helper method to define the start of an HTML document
    private String startHtmlDocument() {
        return "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<title>Courses Table</title>\n"
                + "<style>\n"
                + "table {width: 100%; border-collapse: collapse;}\n"
                + "th, td {border: 1px solid black; padding: 8px; text-align: left;}\n"
                + "th {background-color: #f2f2f2;}\n"
                + "</style>\n"
                + "</head>\n"
                + "<body>\n"
                + "<h2>Courses List</h2>\n"
                + "<table>\n";
    }

    //helper method to format a Course object as an HTML table row
    private String formatCourseRow(Course course) {
        return "<tr>\n"
                + "<td>" + course.getSemester() + "</td>\n"
                + "<td>" + course.getCourse() + "</td>\n"
                + "<td>" + course.getInstructor() + "</td>\n"
                + "<td>" + course.getLocation() + "</td>\n"
                + "</tr>\n";
    }

    //helper method to define the end of an HTML document
    private String endHtmlDocument() {
        return "</table>\n</body>\n</html>";
    }

    //helper method to write the HTML content a file
    private String writeHtmlToFile(String htmlContent) throws IOException {
    	//defining the path for the HTML file
        Path path = Paths.get("courses.html");
        //writing HTML content to the file
        Files.write(path, htmlContent.getBytes());
        //returning the absolute path of the path
        return path.toAbsolutePath().toString();
    }
}
