package evRentingPlatform;

import java.util.Random;
/**
 * the data structure of a scooter, which contains series number, position, battery, and status
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
	 * 
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
	 * @return the no
	 */
	public String getNo() {
		return no;
	}
	/**
	 * @param no the no to set
	 */
	public void setNo(String no) {
		this.no = no;
	}
	/**
	 * @return the status
	 */
	public ScooterStatus getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(ScooterStatus status) {
		this.status = status;
	}
	/**
	 * @return the power
	 */
	public int getPower() {
		return power;
	}
	/**
	 * @param power the power to set
	 */
	public void setPower(int power) {
		this.power = power;
	}
	/**
	 * consume battery by 1%
	 * @param distance in kilometer
	 */
	public void consumePower(double distance) {
		try {
			this.power -= (int)Math.round(distance);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
