package TestingPackage;

import evRentingPlatform.*;

public class TestToString {

	public static void main(String[] args) {
		ChargingStation cs = new ChargingStation(10, 25.131234234125, 125.34513453452);
		System.out.println(cs.toString());
		Repairman rp = new Repairman("Hoffman"," aernjk24gfb");
		rp.setPosition(25.131234234125, 125.34513453452);
		System.out.println(rp.toString());
	}

}
