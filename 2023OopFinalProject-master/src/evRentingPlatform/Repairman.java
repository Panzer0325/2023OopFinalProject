package evRentingPlatform;

/**
 * the person who can fix and recharge scooters
 * @author linchia-ho
 *
 */
public class Repairman extends Person {
	@Override
	public String toString() {
		return "Repairman account: " + getAccount() + 
				"\n<Password: " + getPassword() + ">";
	}
	public Repairman() {};
	/**
	 * constructor with account and password
	 * @param account the account
	 * @param password the password
	 */
	public Repairman(String account, String password) {
		super(account, password);
	}

}
