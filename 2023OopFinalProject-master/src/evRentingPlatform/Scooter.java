package evRentingPlatform;

import java.util.Random;
/**
 * the representation of a scooter, which contains series number, position, battery, and status
 * @author linchia-ho
 * 
 */
public class Scooter extends ObjetWithPositon{

	/**
	 * the status of a scooter
	 * @author linchia-ho
	 *
	 */
	public enum ScooterStatus{
		/**
		 * ready for service
		 */
		IDLE,
		/**
		 * under use
		 */
		Occupied,
		/**
		 * cannot be used, should be fix by repairman
		 */
		Malfunction,
		/**
		 * under repairing
		 */
		Repairing,
	}
	/**
	 * the series number of the scooter, read from given .json file
	 */
	private String no;
	/**
	 * the status of the scooter
	 */
	private ScooterStatus status = setRandomInitialStatus();
	/**
	 * the remaining battery
	 */
	private int power;
	/**
	 * empty constructor
	 */
	public Scooter() {}
	/**
	 * constructor with latitude and longitude
	 * @param no serial ID
	 * @param lat latitude
	 * @param lng longitude
	 * @param power battery power
	 */
	public Scooter(String no, double lat, double lng, int power) {
		super(lat, lng);
		this.no = no;
		this.power = power;
	}
	/**
	 * constructor with position
	 * @param no serial ID
	 * @param position position
	 * @param power battery power
	 */
	public Scooter(String no, Position position, int power) {
		super(position);
		this.no = no;
		this.power = power;
	}
	/**
	 * determine the initial status of a scooter.
	 * A scooter has 5% of chance being malfunction
	 */
	private static ScooterStatus setRandomInitialStatus() {
		 Random random = new Random();
		 if(random.nextDouble() < 0.5) {
			 return ScooterStatus.IDLE;
		 }else {
	         return ScooterStatus.Malfunction;
		 } 
	}
	/**
	 * @return the no(series number)
	 */
	public String getNo() {
		return no;
	}
	/**
	 * @param no the no(series number) to set
	 */
	public void setNo(String no) {
		this.no = no;
	}
	/**
	 * @return the status of scooter
	 */
	public ScooterStatus getStatus() {
		return status;
	}
	/**
	 * @param status the status of scooter to set
	 */
	public void setStatus(ScooterStatus status) {
		this.status = status;
	}
	/**
	 * @return the battery power
	 */
	public int getPower() {
		return power;
	}
	/**
	 * @param power the battery power to set
	 */
	public void setPower(int power) {
		this.power = power;
	}
	/**
	 * consume battery by 1% per kilometer
	 * @param distance in kilometer
	 */
	public void consumePower(double distance) {
		try {
			this.power -= (int)Math.ceil(distance);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public String toString() {
		return "Scooter No." + this.no + 
				"\n<Status: " + this.status + ">" + 
				"\n<Battery: " + this.power + ">" + 
				"\n<" + this.getPosition().toString() + ">" ;
	}
}