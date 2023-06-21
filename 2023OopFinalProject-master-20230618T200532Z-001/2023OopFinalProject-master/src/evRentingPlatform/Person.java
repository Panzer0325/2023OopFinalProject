package evRentingPlatform;
/**
 * the parent class of both {@code User} and {@code Repairman}(human characters) in the system
 * @author linchia-ho
 *
 */
public abstract class Person extends ObjetWithPositon{
	/**
	 * the account
	 */
	private String account;
	/**
	 * the password
	 */
	private String password;
	
	public Person() {};
	
	public Person(String account, String password) {
		this.account = account;
		this.password = password;
	}
	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}
	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
