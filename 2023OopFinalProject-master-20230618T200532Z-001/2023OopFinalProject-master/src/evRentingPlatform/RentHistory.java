package evRentingPlatform;

import java.time.*;
import java.util.ArrayList;
/**
 * the data structure of the renting history
 * @author linchia-ho
 *
 */
public class RentHistory {
	
	/**
	 * the date, can only be set by constructor
	 */
	private LocalDate date;
	/**
	 * the rent fee
	 */
	private double rentFee;
	/**
	 * Record of position, should be updated whenever the position is updated
	 */
	private ArrayList<Position> positionHistory = new ArrayList<Position>(10);
	/**
	 * the accumulate distance 
	 */
	private double distance;
	/**
	 * the time when renting starts, can only be set by constructor
	 */
	private LocalTime rentStartTime;
	/**
	 * the time when renting ends
	 */
	private LocalTime rentEndTime;
	/**
	 * the total renting time
	 */
	private Duration totalTime;
	/**
	 * amount of time that user charges the scooter at charging station
	 */
	private int chargeTimes = 0;
	/**
	 * whether this transaction receive discount from valid coupon
	 */
	private boolean withCoupon = false;
	/**
	 * the serial number of the scooter
	 */
	private String scooterNo;
	/**
	 * the identifier of this rent history event, combines of the (1)date, (2)start time and (3) scooter ID
	 */
	private String historyID;
	/**
	 * the constructor which initiate the event with given position and scooter ID, the time stamp will automatically be generated
	 * @param startPosition
	 * @param scooterNo
	 */
	public RentHistory(Position startPosition, String scooterNo) {
		this.date = LocalDate.now();
		this.positionHistory.add(new Position(startPosition));
		this.rentStartTime = LocalTime.now();
		this.scooterNo = scooterNo;
		this.historyID = this.date.toString() + this.rentStartTime.toString() + this.scooterNo;
	}

	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}
	/**
	 * automatically calculate the rent fee and returns the fee
	 * @return the rent fee
	 */
	public double getRentFee() {
		this.calculateRentFee();
		return rentFee;
	}
	/**
	 * calculate the rent fee by 2.5NTD per kilometer, get 10% off with coupon
	 */
	public void calculateRentFee() {
		try {
			this.rentFee = distance * 2.5;
			if(withCoupon) {
				this.rentFee *= 0.9;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * to get the position history
	 * @return a copy of position history
	 */
	public ArrayList<Position> getPositionHistory(){
		ArrayList<Position> copyPositionHistory = new ArrayList<Position>(this.positionHistory.size());
		for(Position copyPosition: this.positionHistory) {
			copyPositionHistory.add(copyPosition);
		}
		return copyPositionHistory ;
	}
	/**
	 * add a new position to the position history
	 * @param newPosition the updated new position
	 */
	public void updatePositionHistory(Position newPosition) {
		this.positionHistory.add(newPosition);
	}
	/**
	 * @return the distance
	 */
	public double getDistance() {
		return distance;
	}
	/**
	 * calculate the accumulative distance from the position history
	 */
	public void CalculateDistance() {
		try {
			double accumulativeDistance = 0;
			Position previousPosition = null;
			for(Position recordedPosition: positionHistory) {
				if(null == previousPosition) {
					previousPosition = recordedPosition;
				}
				accumulativeDistance += Position.calculateDistance(previousPosition, recordedPosition);
			}
			this.distance = accumulativeDistance;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @return the rentStartTime
	 */
	public LocalTime getRentStartTime() {
		return rentStartTime;
	}

	/**
	 * @return the rentEndTime
	 */
	public LocalTime getRentEndTime() {
		return rentEndTime;
	}
	/**
	 * @param rentEndTime the rentEndTime to set
	 */
	public void setRentEndTime(LocalTime rentEndTime) {
		this.rentEndTime = rentEndTime;
	}
	/**
	 * @return the totalTime
	 */
	public Duration getTotalTime() {
		return totalTime;
	}
	/**
	 * calculate the Total time from the start time and end time
	 */
	public void calculateTotalTime() {
		try {
			this.totalTime = Duration.between(rentStartTime, rentEndTime);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @return the chargeTimes
	 */
	public int getChargeTimes() {
		return chargeTimes;
	}
	/**
	 * increase the charge record by one
	 */
	public void addChargeTimes() {
		this.chargeTimes += 1;
	}
	/**
	 * @return the withCoupon
	 */
	public boolean isWithCoupon() {
		return withCoupon;
	}
	/**
	 * @param withCoupon the withCoupon to set
	 */
	public void setWithCoupon(boolean withCoupon) {
		this.withCoupon = withCoupon;
	}
	/**
	 * @return the scooterNo
	 */
	public String getScooterNo() {
		return scooterNo;
	}
	/**
	 * 
	 * @return the history Identifier
	 */
	public String getHistoryID() {
		return historyID;
	}
	@Override
	public String toString() {
		return "Rent History: " + this.historyID + //"\n<" + + ">" + 
				"\n<Date: " + this.date + ">" + 
				"\n<Scooter: " + this.scooterNo + ">" + 
				"\n<Start: " + this.rentStartTime+ ">" +
				"\n<End: " + this.rentEndTime+ ">" + 
				"\n<Duration: " + this.totalTime+ ">" + 
				"\n<Start Position; " + this.positionHistory.get(0)+ ">" + 
				"\n<End Position: " + this.positionHistory.get(this.positionHistory.size()-1) + ">" + 
				"\n<Charge Times: " + this.chargeTimes + ">" +
				"\n<Fee: " + this.rentFee + ">" + 
				"\n<With coupon: " + this.withCoupon + ">";
	}
	
}
