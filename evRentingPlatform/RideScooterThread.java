package evRentingPlatform;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class RideScooterThread extends Thread {
	private User user;
	private volatile boolean stopRiding = false;
	private CountDownLatch latch;
	
	
	public RideScooterThread(User user, CountDownLatch latch) {
		this.user = user;
		this.latch = latch;
	}
	
	public void stopUpdating() {
		stopRiding = true;
	}
	
	public static Position generate(Double lat, Double lng) throws IOException{
		Double nextLat, nextLng;
		do
		{
			nextLat = lat + (Math.random() - 0.5) * 0.001;
			nextLng = lng + (Math.random() - 0.5) * 0.001;
		}while(nextLat < 25.026708 || nextLat > 25.068277 || nextLng < 121.511162 || nextLng > 121.567045);
		return new Position(nextLat, nextLng);
	}
	@Override
	public void run(){
		while(!stopRiding) {
            try {
                Thread.sleep(10000); // Sleep for 10 seconds
                Position nextPosition = generate(user.getLat(),user.getLng());
                // update position
                user.setLat(nextPosition.lat);
                user.setLng(nextPosition.lng);
                user.getScooter().consumePower(Position.calculateDistance(user.getPosition(), nextPosition));
                user.getRentEvent().updatePositionHistory(new Position(nextPosition)); 
                System.out.println("Update position: " + user.getLat() + ", " + user.getLng());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
            	e.printStackTrace();
            } catch (Exception e ) {
            	e.printStackTrace();
            }
		}
		latch.countDown();
	}
}
