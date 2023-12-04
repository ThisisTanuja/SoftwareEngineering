package com.sql.course;

import com.sql.course.database.SQLiteDataHandler;
import com.sql.course.model.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest 
//extending the test with SpringExtension which integrates the Spring TestContext Framework into JUnit 5
@ExtendWith(SpringExtension.class)
public class SQLiteDataHandlerTest {

	//injecting the SQLiteDataHandler bean into this test class
    @Autowired
    private SQLiteDataHandler dataHandler; 

    //Test method to verify that courses are correctly read from a JSON file
    @Test
    public void testReadCoursesFromJson() {
        //calling the readCoursesFromJson method and passes the name of the test JSON file
        List<Course> courses = dataHandler.readCoursesFromJson("test_courses.json");
        
        //asserting that the returned courses list is not null
        assertNotNull(courses, "The courses list should not be null");
        assertFalse(courses.isEmpty(), "The courses list should not be empty");
    }

  
    //Test method to verify that courses can be saved to and retrieved from the database
    @Test
    public void testSaveAndRetrieveCourses() {
    	//reading courses from the JSON file, asserting that the courses list is not null and saving the courses to the database
        List<Course> courses = dataHandler.readCoursesFromJson("test_courses.json");
        assertNotNull(courses, "The courses list should not be null");
        dataHandler.saveCoursesToDatabase(courses);

        //retrieves all courses from the database, asserting that the retrieved courses list is not null and that the size of the retrieved courses list 
        //matches the size of the originally read list
        List<Course> retrievedCourses = dataHandler.getAllCourses();
        assertNotNull(retrievedCourses, "The retrieved courses list should not be null");
        assertEquals(courses.size(), retrievedCourses.size(), "The number of retrieved courses should match the number of saved courses");

    }
}
