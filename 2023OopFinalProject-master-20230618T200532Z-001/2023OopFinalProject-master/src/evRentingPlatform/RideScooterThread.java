package evRentingPlatform;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import evRentingPlatformGUI.UserGUI;
/**
 * A riding simulation machine which interacts with other object with independent thread
 * @author linchia-ho
 *
 */
public class RideScooterThread extends Thread {
	/**
	 * the user who is executing riding
	 */
	private User user;
	/**
	 * a flag represents the staus of riding
	 */
	private volatile boolean riding = true;
	/**
	 * a synchronization aid
	 */
	private CountDownLatch latch;
	
	/**
	 * constructor of the riding simulation machine
	 * @param user the user
	 * @param latch the synchronization aid
	 */
	public RideScooterThread(User user, CountDownLatch latch) {
		this.user = user;
		this.latch = latch;
	}
	/**
	 * a sign for terminating the riding behavior
	 * Riding will not end immediately in order to simulate the parking behavior
	 */
	public void stopUpdating() {
		riding = false;
	}
	/**
	 * a randon position generator
	 * @param lat latitude
	 * @param lng longitude
	 * @return the randomly generated position
	 * @throws IOException
	 * @author allem40306
	 */
	public static Position generate(Double lat, Double lng) throws IOException{
		Double nextLat, nextLng;
		do
		{
			nextLat = lat + (Math.random() - 0.5) * 0.001;
			nextLng = lng + (Math.random() - 0.5) * 0.001;
		}while(nextLat < 25.026708 || nextLat > 25.068277 || nextLng < 121.511162 || nextLng > 121.567045);
		return new Position(nextLat, nextLng);
	}
	/**
	 * execution of riding simulation, details are as follow:
	 * (1)Update the position of user every 10 seconds
	 * (2)Update the battery consumption
	 */
	@Override
	public void run(){
		while(riding) {
            try {
                Thread.sleep(10000); // Sleep for 10 seconds
                if(user.getScooter().getPower() <= 1) {
                	throw new LowBatteryException("Less than 1% during riding");
                }
                Position nextPosition = generate(user.getLat(),user.getLng());
                // update position
                user.getScooter().consumePower(Position.calculateDistance(user.getPosition(), nextPosition));
                user.setPosition(nextPosition);
                user.getScooter().setPosition(nextPosition);   
                user.getRentEvent().updatePositionHistory(new Position(nextPosition)); 
                System.out.println("Update position: " + user.getLat() + ", " + user.getLng());
                UserGUI.DisplayPowerRemain(user.getScooter().getPower());
                UserGUI.DisplayCurrentPosition(user.getPosition());
                user.getRentEvent().CalculateDistance();
                UserGUI.DisplayAccumulativeDistance(user.getRentEvent().getDistance());
                UserGUI.loadMap(user.getPosition().lat,user.getPosition().lng);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
            	e.printStackTrace();
            } catch (LowBatteryException e ) {
            	e.printStackTrace();
            	this.stopUpdating();
            } catch (Exception e ) {
            	e.printStackTrace();
            }
		}
		latch.countDown();
	}
}