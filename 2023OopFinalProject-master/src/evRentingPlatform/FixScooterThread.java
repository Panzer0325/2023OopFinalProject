package evRentingPlatform;

import evRentingPlatformGUI.StaffGUI;

/**
 * 
 * @author linchia-ho
 *
 */
public class FixScooterThread extends Thread {
	
	/**
	 * the scooter being examed
	 */
	private Scooter scooter;
	/**
	 * constructor which receives the target scooter 
	 * @param scooter
	 */
	public FixScooterThread(Scooter scooter) {
		this.scooter = scooter;
	}
	@Override
	public void run() {
		try {
			switch(this.scooter.getStatus()) {
			case IDLE:
				break;
			case Malfunction:
				scooter.setStatus(Scooter.ScooterStatus.Repairing);
				Thread.sleep(60000);             // Sleep for 60 seconds
				scooter.setStatus(Scooter.ScooterStatus.IDLE);
				break;
			case Repairing:
				break;
			case Occupied:
				break;
			default:
				break;	
			}
            System.out.println("");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e ) {
        	e.printStackTrace();
        }
	}
}
