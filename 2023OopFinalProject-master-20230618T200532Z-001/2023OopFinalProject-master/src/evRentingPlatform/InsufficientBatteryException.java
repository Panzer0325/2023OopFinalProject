package evRentingPlatform;
/**
 * Exception thrown when insufficient battery to reach a certain destination
 * @author linchia-ho
 *
 */
public class InsufficientBatteryException extends Exception {
	
	/**
	 * constructor with default message.
	 */
	public InsufficientBatteryException() {
		super("Insufficient remaining battery to reach destination.");

	}
	/**
	 * constructor with customized message
	 * @param message the message to be sent
	 */
	public InsufficientBatteryException(String message) {
		super(message);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
