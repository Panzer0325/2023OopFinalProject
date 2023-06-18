package evRentingPlatformGUI;

import java.util.ArrayList;
import java.util.Scanner;

import evRentingPlatform.RentScooterService;


public class TestRentScooterService2 {
	public static void main(String[] args) {
		
		// Start Demo:
		// Start the renting service
		RentScooterService service = new RentScooterService();
		LoginGUI app=new LoginGUI(service);
		app.setVisible(true);
	}
}
