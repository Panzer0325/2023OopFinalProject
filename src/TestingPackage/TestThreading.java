package TestingPackage;

import java.util.Scanner;

public class TestThreading {
    public static void main(String[] args) {
        scooter myScooter = new scooter();
        boolean rideInProgress = false;

        Scanner scanner = new Scanner(System.in);

        System.out.print("Start riding scooter? (Y/N): ");
        String startInput = scanner.nextLine();

        if (startInput.equalsIgnoreCase("Y")) {
            rideInProgress = true;
            System.out.println("Ride started!");

            DistanceUpdater distanceUpdater = new DistanceUpdater(myScooter);
            Thread distanceUpdaterThread = new Thread(distanceUpdater);
            distanceUpdaterThread.start();

            while (rideInProgress) {
                System.out.print("End riding the scooter? (Y/N): ");
                String endInput = scanner.nextLine();

                if (endInput.equalsIgnoreCase("Y")) {
                    rideInProgress = false;
                    System.out.println("Ride ends, thank you.");
                    distanceUpdater.stopUpdating(); // Stop the distance update thread
                    System.out.println("Final distance: " + myScooter.distance);
                } else if (!endInput.equalsIgnoreCase("N")) {
                    System.out.println("Invalid input. Continuing the ride.");
                }
            }
        } else if (startInput.equalsIgnoreCase("N")) {
            System.out.println("Ride ends, thank you.");
            return;
        } else {
            System.out.println("Invalid input. Ride ends.");
            return;
        }
    }
}

class scooter {
    public double distance = 0;
}

class DistanceUpdater implements Runnable {
    private scooter myScooter;
    private volatile boolean stopUpdating;

    public DistanceUpdater(scooter scooter) {
        this.myScooter = scooter;
        this.stopUpdating = false;
    }

    public void stopUpdating() {
        stopUpdating = true;
    }

    @Override
    public void run() {
        while (!stopUpdating) {
            try {
                Thread.sleep(10000); // Sleep for 10 seconds
                myScooter.distance += 10;
                System.out.println("Distance: " + myScooter.distance);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
