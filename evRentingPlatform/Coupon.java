package evRentingPlatform;
/**
 * Coupons are given after users charge the scooter. A coupon cannot be instantly used at the ride which it is created.
 * @author linchia-ho
 *
 */
public class Coupon {
	/**
	 * whether this coupon can be used for the current ride
	 */
	private boolean usable = false;

	/**
	 * @return the usable
	 */
	public boolean isUsable() {
		return usable;
	}

	/**
	 * @param usable the usable to set
	 */
	public void setUsable(boolean usable) {
		this.usable = usable;
	}
	
}
