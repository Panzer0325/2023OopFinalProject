package evRentingPlatform;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.util.ArrayList;

public class TestInitialization {

	 public static void main(String[] args) {
		 //initalizeChargingStation();
		 //initalizeScooter();
	 
	 }
	 public static void initalizeScooter(){
		 ObjectMapper objectMapper = new ObjectMapper();
		 try {
	            // Read the JSON file
	            File file = new File("resources/scooter_detail.json");
	            
	            // Map the JSON array to a list of Location objects
	            ArrayList<Scooter> scooters = objectMapper.readValue(file, new TypeReference<ArrayList<Scooter>>() {});

	            // Print each location object
	            for (Scooter scooter : scooters) {
	                System.out.println("No: " + scooter.getNo());
	   	            System.out.println("Power: " + scooter.getPower());	                
	                System.out.println("Latitude: " + scooter.getLat());
	                System.out.println("Longitude: " + scooter.getLng());
	                System.out.println();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	 }
	 
	 public static void initalizeChargingStation(){
		 ObjectMapper objectMapper = new ObjectMapper();

	        try {
	            // Read the JSON file
	            File file = new File("resources/battery.json");
	            
	            // Map the JSON array to a list of Location objects
	            ArrayList<ChargingStation> chargingStations = objectMapper.readValue(file, new TypeReference<ArrayList<ChargingStation>>() {});

	            // Print each location object
	            for (ChargingStation station : chargingStations) {
	                System.out.println("No: " + station.getNo());
	                System.out.println("Latitude: " + station.getLat());
	                System.out.println("Longitude: " + station.getLng());
	                System.out.println();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	 }

}
