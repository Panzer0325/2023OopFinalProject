package evRentingPlatform;

public class Position {
	/**
	 * latitude
	 */
	public double lat;
	/**
	 * longitude
	 */
	public double lng;
	
	/**
	 * regular constructor for creating a position data structure with given latitude and longitude
	 * @param lat latitude
	 * @param lng longitude
	 */
	public Position(double lat, double lng) {
		this.lat = lat;
		this.lng = lng;
	}
	/**
	 * Copy constructor
	 * @param copyPosition
	 */
	public Position(Position copyPosition) {
		this.lat = copyPosition.lat;
		this.lng = copyPosition.lng;
	}
	/**
	 * empty constructor sets both latitude and longitude at -1000.0 to distinguish from normal value
	 */
	public Position() {
		this.lat = -1000.0;
		this.lng = -1000.0;
	}
	/**
	 * calculate the distance of 2 position in Kilometer
	 * @param position1 the 1st position
	 * @param position2 the 2nd position
	 * @return the distance in kilometer
	 */
	public static double calculateDistance(Position position1, Position position2) {
//		double distance = Math.sqrt(
//				Math.pow((position1.lat - position2.lat), 2) + Math.pow((position1.lng - position2.lng),2)
//				);
//		distance = distance * 100;
		double lat1 = Math.toRadians(position1.lat);
        double lon1 = Math.toRadians(position1.lng);
        double lat2 = Math.toRadians(position2.lat);
        double lon2 = Math.toRadians(position2.lng);

        double deltaLat = lat2 - lat1;
        double deltaLon = lon2 - lon1;

        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
                + Math.cos(lat1) * Math.cos(lat2) * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = 6371 * c; // Earth's radius in kilometers

        return distance;
	}
}
