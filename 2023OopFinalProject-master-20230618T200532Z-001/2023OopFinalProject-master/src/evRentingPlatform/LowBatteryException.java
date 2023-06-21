package evRentingPlatform;
/**
 * Exception thrown when the absolute remaining battery is lower then 1%
 * @author linchia-ho
 *
 */
public class LowBatteryException extends Exception {


	/**
	 * constructor with default message
	 */
	public LowBatteryException() {
		super("Lower then 1% battery, please immediately pull over");
		// TODO Auto-generated constructor stub
	}
	/**
	 * constructor with customized message
	 * @param message the message to be sent
	 */
	public LowBatteryException(String message) {
		super(message);
	}
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}