package com.sql.course.database;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.sql.course.model.Course;

@Service
public class SQLiteDataHandler {

	//injecting a DataSource object managed by Spring
	@Autowired
	private DataSource dataSource;

	//method to get a connection to the database
	private Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	//method to create the database table if it does not exist
	public void createDatabase() {
		try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {

			//SQL statement to create the courses table
			String sql = "CREATE TABLE IF NOT EXISTS courses (\n" + " semester text NOT NULL,\n"
					+ " course text NOT NULL,\n" + " instructor text NOT NULL,\n" + " location text NOT NULL\n" + ");";
			
			//executing the SQL statement
			stmt.execute(sql);
			System.out.println("A new database has been created.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	//method to read courses from a JSON file
	public List<Course> readCoursesFromJson(String jsonFilePath) {
		try (InputStream inputStream = getClass().getResourceAsStream("/courses.json"); 
				JsonReader jsonReader = new JsonReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
			
			//using Gson to parse the JSON file into a list of Course objects
			return new Gson().fromJson(jsonReader, new TypeToken<List<Course>>() {
			}.getType());
		} catch (IOException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	//method to save a list of courses to the database 
	public void saveCoursesToDatabase(List<Course> courses) {
		//SQL statement to insert a course into the database
	    String sqlInsert = "INSERT INTO courses(semester, course, instructor, location) VALUES(?,?,?,?)";
	    //SQL statement to check if a course already exists in the database
	    String sqlCheck = "SELECT COUNT(*) FROM courses WHERE semester = ? AND course = ? AND instructor = ? AND location = ?";

	    try (Connection conn = getConnection();
	         PreparedStatement pstmtCheck = conn.prepareStatement(sqlCheck);
	         PreparedStatement pstmtInsert = conn.prepareStatement(sqlInsert)) {
	         
	        for (Course course : courses) {
	            // preparing and executing the SQL statement to check for existing course
	            pstmtCheck.setString(1, course.getSemester());
	            pstmtCheck.setString(2, course.getCourse());
	            pstmtCheck.setString(3, course.getInstructor());
	            pstmtCheck.setString(4, course.getLocation());
	            ResultSet rs = pstmtCheck.executeQuery();
	            
	            if (rs.next() && rs.getInt(1) == 0) { 
	            	//inserting it if the course does not exist
	                pstmtInsert.setString(1, course.getSemester());
	                pstmtInsert.setString(2, course.getCourse());
	                pstmtInsert.setString(3, course.getInstructor());
	                pstmtInsert.setString(4, course.getLocation());
	                pstmtInsert.executeUpdate();
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	}

	//method to retrieve all courses from the database
	public List<Course> getAllCourses() {
		List<Course> courses = new ArrayList<>();
		//SQL statement to select all courses
		String sql = "SELECT * FROM courses";
		try (Connection conn = getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				//creating a new Course object for each row in the result set and add it to the list
				Course course = new Course();
				course.setSemester(rs.getString("semester"));
			    course.setCourse(rs.getString("course"));
			    course.setInstructor(rs.getString("instructor"));
			    course.setLocation(rs.getString("location"));
				courses.add(course);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(courses);
		return courses;
	}

}
