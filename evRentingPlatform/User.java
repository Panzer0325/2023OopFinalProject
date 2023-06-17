package evRentingPlatform;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

/**
 * the customer of the scooter renting service
 * @author linchia-ho
 *
 */
public class User extends Person{
	private String cellphone = null;
	private String email = null;
	private String userName = null;
	private String creditCard = null;
	private ArrayList<RentHistory> rentHistory = new ArrayList<RentHistory>(5);
	private RentHistory rentEvent;
	private Scooter scooter = null;
	private ArrayList<Coupon> couponList = new ArrayList<Coupon>(5);	
	/**
	 * empty constructor
	 */
	public User() {};
	/**
	 * 
	 * @param account the account
	 * @param password the password
	 */
	public User(String account, String password) {
		super(account, password);
	}
	/**
	 * generates random initial position, are called when user login
	 */
	public void setInitialPosition() {
		double[] latRange = {25.026708,25.068277};
	    double[] lngRange = {121.511162, 121.567045};
	    Random random = new Random();
	    double randomLat = latRange[0] + random.nextDouble() * (latRange[1] - latRange[0]);
	    double randomLng= lngRange[0] + random.nextDouble() * (lngRange[1] - lngRange[0]);
		this.setPosition(randomLat, randomLng);
	}
	/**
	 * @return the rentHistory
	 */
	public ArrayList<RentHistory> getRentHistory() {
		return rentHistory;
	}
	/**
	 * generate and add a new rent history to user's list of rent history
	 */
	public void newRentEvent() {
		try {
			if(this.rentEvent == null) {
				this.rentEvent = new RentHistory(this.getPosition(),this.scooter.getNo());
			}else {
				throw new Exception("Last rent event is unclosed");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * get the instance of rent event
	 * @return
	 */
	public RentHistory getRentEvent() {
		return this.rentEvent;
	}
	/**
	 * clear the feild of rent event, should be executed after it is add to the rent history
	 */
	public void clearRentEvent() {
		this.rentEvent = null;
	}
	/**
	 * @return the scooter 
	 */
	public Scooter getScooter() {
		return scooter;
	}
	/**
	 * @param scooter the scooter to set
	 */
	public void setScooter(Scooter scooter) {
		this.scooter = scooter;
	}
	/**
	 * @return the couponList
	 */
	public ArrayList<Coupon> getCouponList() {
		return couponList;
	}
	/**
	 * @param couponList the couponList to set
	 */
	public void setCouponList(ArrayList<Coupon> couponList) {
		this.couponList = couponList;
	}

	/**
	 * @return the cellphone
	 */
	public String getCellphone() {
		return cellphone;
	}

	/**
	 * @param cellphone the cellphone to set
	 */
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the creditCard
	 */
	public String getCreditCard() {
		return creditCard;
	}
	/**
	 * @param creditCard the creditCard to set
	 */
	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}
	
}
