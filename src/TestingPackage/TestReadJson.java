package TestingPackage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TestReadJson {
	 public static void main(String[] args) {
	        ObjectMapper objectMapper = new ObjectMapper();

	        try {
	            // Read the JSON file
	            File file = new File("resources/data.json");
	            
	            // Map the JSON array to a list of Location objects
	            List<Car> cars = objectMapper.readValue(file, new TypeReference<List<Car>>() {});

	            // Print each location object
	            for (Car car : cars) {
	                System.out.println("No: " + car.getNo());
	                System.out.println("Latitude: " + car.getPosition().lat);
	                System.out.println("Longitude: " + car.getPosition().lng);
	                System.out.println();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	 

}