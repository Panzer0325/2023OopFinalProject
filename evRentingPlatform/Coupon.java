package evRentingPlatform;
/**
 * Coupons are given after users charge the scooter. A coupon cannot be instantly used at the ride which it is created.
 * @author linchia-ho
 * Different coupons may have same rent history ID, which means they are both generated during a single rent event
 */
public class Coupon {

	/**
	 * the rent history which generates it
	 */
	private String rentHistoryID;
	/**
	 * the flag represent it is used or not
	 */
	private boolean usedFlag = false;
	/**
	 * constructor requires the affiliated rent event in order to generate the ID
	 * @param affiliation the rent event which generates this coupon
	 */
	public Coupon(RentHistory affiliation) {
		this.rentHistoryID = affiliation.getHistoryID();
	}
	/**
	 * get the readable ID of the parent rent history
	 * @return the ID of that rent history, 
	 */
	public String getHistoryID() {
		return this.rentHistoryID;
	}
	/**
	 * switch the flag of used to true
	 * @return {@code true} if this coupon is successfully used; otherwise {@code false}
	 */
	public boolean useCoupon() {
		if(!this.usedFlag) {
			this.usedFlag = true;
			return true;
		}else {
			return false;
		}
	}
	/**
	 * get the flag of used
	 * @return {@code true} if this coupon is used; otherwise {@code false}
	 */
	public boolean getUsedFlag() {
		return this.usedFlag;
	}
	@Override
	public String toString() {
		return "Coupon from" + rentHistoryID + 
				"\n<Used:" + this.usedFlag + ">";
	}
}
