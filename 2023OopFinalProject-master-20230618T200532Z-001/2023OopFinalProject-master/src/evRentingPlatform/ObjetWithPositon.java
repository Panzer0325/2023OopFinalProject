package evRentingPlatform;
/**
 * an object with the field of position;
 * it is possible to manipulate the position directly through a {@code Position} object or respectively through latitude and longitude
 * @author linchia-ho
 *
 */
public abstract class ObjetWithPositon{
	/**
	 * the position of the object
	 */
	private Position position = new Position();
	/**
	 * empty constructor which position is set to {-1000.0, -1000.0}
	 */
	public ObjetWithPositon() {};
	/**
	 * constructor with {@code Position} type
	 * @param position
	 */
	public ObjetWithPositon(Position position) {
		this.position = new Position(position);
	}
	/**
	 * constructor with latitude and longitude
	 * @param lat latitude
	 * @param lng longitude
	 */
	public ObjetWithPositon(double lat, double lng) {
		this.position = new Position(lat,lng);
	}
	/**
	 * @return the latitude
	 */
	public double getLat() {
		return position.lat;
	}
	/**
	 * @param lat the latitude to set
	 */
	public void setLat(double lat) {
		this.position.lat = lat;
	}
	/**
	 * @return the longitude
	 */
	public double getLng() {
		return position.lng;
	}
	/**
	 * @param lng the longitude to set
	 */
	public void setLng(double lng) {
		this.position.lng = lng;
	}
	/**
	 * return a copy of it's position
	 * @return position
	 */
	public Position getPosition() {
		return new Position(this.position);
	}
	/**
	 * set position with given position object
	 * @param position position to be set
	 */
	public void setPosition(Position position) {
		this.setPosition(position.lat,position.lng);
	}
	/**
	 * set position with given latitude and longitude
	 * @param lat latitude to be set
	 * @param lng longitude to be set
	 */
	public void setPosition(double lat, double lng) {
		this.setLat(lat);
		this.setLng(lng);
	}
}
