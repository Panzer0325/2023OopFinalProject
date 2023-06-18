package TestingPackage;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

import evRentingPlatform.*;

public class TestRideScooterThread {

	public static void main(String[] args) {
		User user = new User();
		user.setPosition(25.04, 121.511162);
		boolean rideInProgress = false;
		
        Scanner scanner = new Scanner(System.in);
        CountDownLatch latch = new CountDownLatch(1);

        System.out.print("Start riding scooter? (Y/N): ");
        String startInput = scanner.nextLine();
        
        if (startInput.equalsIgnoreCase("Y")) {
            rideInProgress = true;
            System.out.println("Ride started!");

            RideScooterThread rideScooterThread = new RideScooterThread(user, latch);
            rideScooterThread.start();

            while (rideInProgress) {
                System.out.print("End riding the scooter? (Y/N): ");
                String endInput = scanner.nextLine();

                if (endInput.equalsIgnoreCase("Y")) {
                    rideInProgress = false;
                    System.out.println("Ride ends, thank you.");
                    rideScooterThread.stopUpdating(); // Stop the distance update thread
                    try {
                        latch.await(); // Wait until the distance update is complete
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Final position: " + user.getLat() + user.getLng());
                } else if (!endInput.equalsIgnoreCase("N")) {
                    System.out.println("Invalid input. Continuing the ride.");
                }
            }
        } else if (startInput.equalsIgnoreCase("N")) {
            System.out.println("Cancel riding, thank you.");
            return;
        } else {
            System.out.println("Invalid input. Ride ends.");
            return;
        }
	}
}
