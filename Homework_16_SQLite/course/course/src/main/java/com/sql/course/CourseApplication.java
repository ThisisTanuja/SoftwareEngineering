package com.sql.course;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.beans.factory.annotation.Autowired;

import com.sql.course.database.SQLiteDataHandler;
import com.sql.course.htmltablegenerator.HTMLTableGenerator;
import com.sql.course.model.Course;
import java.util.List;

@SpringBootApplication
//specifies the packages to scan for Spring components
@ComponentScan(basePackages = {"com.sql.course", "other.package.to.scan"})
public class CourseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseApplication.class, args);
    }

    //injecting SQLiteDataHandler to interact with the SQLite database
    @Autowired
    private SQLiteDataHandler handler;

    //injecting HTMLTablGenerator for generating HTML tables
    @Autowired
    private HTMLTableGenerator generator;

    //defines a Spring bean - code to be executed when the application starts
    @Bean
    public CommandLineRunner run() {
        return args -> {
            //reading courses data from a JSON file
            List<Course> courses = handler.readCoursesFromJson("/courses.json");
            
            //saving the courses data to the SQLite database
            handler.saveCoursesToDatabase(courses);
            
            //retrieving the list of all courses from the database
            List<Course> retrievedCourses = handler.getAllCourses();
            
            //generating an HTML table file with the courses data
            String htmlFilePath = generator.generateHtmlTable(retrievedCourses);

            //logging the completion of the process and the path to the generated HTML file
            System.out.println("Process completed: JSON loaded, saved to DB, retrieved, and HTML generated.");
            System.out.println("HTML file generated at: " + htmlFilePath);
        };
    }
}
