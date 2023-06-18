package evRentingPlatform;

import java.util.ArrayList;
import java.util.Random;

/**
 * the customer of the scooter renting service
 * @author linchia-ho
 *
 */
public class User extends Person{
	/**
	 * the cell phone number
	 */
	private String cellphone = null;
	/**
	 * the email address
	 */
	private String email = null;
	/**
	 * the name of the user
	 */
	private String userName = null;
	/**
	 * the credit card digit
	 */
	private String creditCard = null;
	/**
	 * the history of renting record
	 */
	private ArrayList<RentHistory> rentHistory = new ArrayList<RentHistory>(5);
	/**
	 * the current renting event, exist only during renting
	 */
	private RentHistory rentEvent;
	/**
	 * the rent scooter, exist only during renting
	 */
	private Scooter scooter = null;
	/**
	 * the coupon gain by chargine scooters
	 */
	private ArrayList<Coupon> couponList = new ArrayList<Coupon>(5);	
	
	/**
	 * empty constructor
	 */
	public User() {};
	/**
	 * constructor with account and password
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
	 * receive a coupon 
	 * @param coupon the new coupon
	 * @return {@code true} if successfully executed; otherwise {@code false}
	 */
	public boolean addToCouponList(Coupon coupon) {
		try {
			this.couponList.add(coupon);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
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
	/**
	 * a simplified version for display essential information
	 * @return who and where the use is
	 */
	public String simpleToString() {
		return "User name: " + this.userName +
				"\n<" + this.getPosition().toString() + ">";
	}
	@Override
	public String toString() {
		return "User name: " + this.userName + 
				"\n<Account: "	+ this.getAccount()+
				"\n<Password: " + this.getPassword()+ ">" +
				"\n<Cell: " + this.cellphone + ">" +
				"\n<Email: " + this.email + ">" +
				"\n<Credit Card: " + this.creditCard + ">" +
				"\n<" + this.getPosition().toString() + ">";
	}
	
}
