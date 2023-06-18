package evRentingPlatform;

import java.util.ArrayList;
import java.util.Scanner;


public class TestRentScooterService {

	public static void main(String[] args) {
		
		// Start Demo:
		// Start the renting service
		RentScooterService service = new RentScooterService();
		System.out.println("<<Scooter Renting Servie Start>>");
		System.out.println();
		
		// Login with a pre-created user account
		System.out.println("<<User inputs its account and password>>");
		System.out.println("User logs in:" + service.userLogIn("WeirdCoffeePerson", "HarioV60"));
		System.out.println();
		
		// Return user current position
		System.out.println("<<User requires his current position>>");
		Position userPosition = service.showUserPosition(service.getUserOperator());
		System.out.println("User's position: {latitude: " + userPosition.lat + " User longitude: " + userPosition.lng + "}");
		System.out.println();
		
		// Search scooters within given range
		System.out.println("<<User searches scooter within 0.25km>>");
		ArrayList<Scooter> scooterWithinRange = service.searchScooter(service.getUserOperator(), 0.25);
		System.out.println("Scooters Within Range: " + scooterWithinRange.size());
		for(Scooter scooter: scooterWithinRange) {
			System.out.println("No: " + scooter.getNo());
            System.out.println("Power: " + scooter.getPower());	                
            System.out.println("Latitude: " + scooter.getLat());
            System.out.println("Longitude: " + scooter.getLng());
            System.out.println("Status: " + scooter.getStatus());
            System.out.println();
		}
		
		// Imitate selecting a certain scooter in GUI
		Scooter selectedScooter = scooterWithinRange.get(scooterWithinRange.size()-1);
		System.out.println("Selected scooter:");
		System.out.println("No: " + selectedScooter.getNo());
        System.out.println("Power: " + selectedScooter.getPower());	                
        System.out.println("Latitude: " + selectedScooter.getLat());
        System.out.println("Longitude: " + selectedScooter.getLng());
        System.out.println();
        
        // Execute renting a scooter
        service.rentScooter(service.getUserOperator(), selectedScooter);
        userPosition = service.showUserPosition(service.getUserOperator());
        System.out.println("User's position after renting:");
        System.out.println("User latitude: " + userPosition.lat + " longitude: " + userPosition.lng);
        System.out.println("Scooter's status after renting: "+ selectedScooter.getStatus());
        System.out.println();
        
        // Start riding scooter
        System.out.println("<<Start physically riding scooter for 15 sec>>");
        service.rideScooter(service.getUserOperator());
        try {
            Thread.sleep(15000); // Sleep for 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // End riding
        service.endRidingScooter(service.getUserOperator()); // Stop the riding thread
        System.out.println("<<End physically riding scooter>>");
       
        // Display result
        System.out.println("Rent End Time:" + service.getUserOperator().getRentEvent().getRentEndTime());
        System.out.println("Total Time:" + service.getUserOperator().getRentEvent().getTotalTime());
        System.out.println("Final distance: " + service.getUserOperator().getRentEvent().getDistance());
        
        // Pay and return
        System.out.println("The fee of this ride: " + service.displayFee(service.getUserOperator()));
        System.out.println("Return the scooter and pays the fee of: " + service.payFeeAndReturnScooter(service.getUserOperator()));
        
        
	}

}
//        boolean rideInProgress = false;
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Start riding scooter? (Y/N): ");
//        String startInput = scanner.nextLine();
//        if (startInput.equalsIgnoreCase("Y")) {
//        	rideInProgress = service.rideScooter(service.getUserOperator());
//            System.out.println("Ride started!");
//            while (rideInProgress) {
//                System.out.print("End riding the scooter? (Y/N): \n");
//                String endInput = scanner.nextLine();
//
//                if (endInput.equalsIgnoreCase("Y")) {
//                    rideInProgress = false;
//                    service.endRidingScooter(service.getUserOperator()); // Stop the riding thread
//                    System.out.println("Ride ends, thank you.");
//                    System.out.println("Rent End Time:" + service.getUserOperator().getRentEvent().getRentEndTime());
//                    System.out.println("Total Time:" + service.getUserOperator().getRentEvent().getTotalTime());
//                    System.out.println("Final distance: " + service.getUserOperator().getRentEvent().getDistance());
//                } else if (!endInput.equalsIgnoreCase("N")) {
//                    System.out.println("Invalid input. Continuing the ride.");
//                }
//            }
//        } else if (startInput.equalsIgnoreCase("N")) {
//            System.out.println("Ride Terminates");
//        } else {
//            System.out.println("Invalid input. Ride ends.");
//        }