package evRentingPlatform;
/**
 * the instance of charging station, comprise of series number, latitude and longitude
 * @author linchia-ho
 *
 */
public class ChargingStation extends ObjetWithPositon{

	/**
	 * the series number of the charging station; read from given .json file
	 */
	private int no;
	/**
	 * empty constructor which position is set to {-1000.0, -1000.0}, no is set to 0
	 */
	public ChargingStation() {}	
	/**
	 * constructor with no, latitude and longitude
	 * @param no the series number
	 * @param lat the latitude of the position
	 * @param lng the longitude of the postition
	 */
	public ChargingStation(int no, double lat, double lng) {
		super(lat, lng);
		this.no = no;
	}
	/**
	 * constructor with no and {@code Position} type
	 * @param no
	 * @param position
	 */
	public ChargingStation(int no, Position position) {
		super(position.lat, position.lng);
		this.no = no;
	}
	/**
	 * @return the no
	 */
	public int getNo() {
		return no;
	}
	/**
	 * @param no the no to set
	 */
	public void setNo(int no) {
		this.no = no;
	}
	
	@Override
	public String toString() {
		return "Charging Station No." + this.no + 
				"\n<" + this.getPosition().toString() + ">";
	}
	
}
