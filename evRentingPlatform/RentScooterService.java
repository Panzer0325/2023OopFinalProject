package evRentingPlatform;

import evRentingPlatform.Scooter.ScooterStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * the instance of the scooter renting service and contains interactive actions
 * @author linchia-ho
 *
 */
public class RentScooterService {
	private ArrayList<User> userList;
	private ArrayList<Scooter> scooterList;
	private ArrayList<Repairman> repairmanList;
	private ArrayList<ChargingStation> chargingStationList;
	private Repairman rpOperator = null;
	private User userOperator = null;//make sure always decouple after used, whether normally or abnormally ends
	private RideScooterThread rideScooterThread;
	private CountDownLatch latch;
	/**
	 * @return the userOperator
	 */
	public User getUserOperator() {
		return userOperator;
	}
	/**
	 * @param userOperator the userOperator to set
	 */
	public void setUserOperator(User userOperator) {
		this.userOperator = userOperator;
	}
	
	/**
	 * Automatically loads in users, scooters, repairmen and charging stations 
	 */
	public RentScooterService() {
		// Share use a singleton ObjectMapper to increase efficiency
		ObjectMapper objectMapper = new ObjectMapper();
		this.initiateUser(objectMapper);
		this.initiateScooter(objectMapper);
		this.initiateChargingStation(objectMapper);
		this.initiateRepairman(objectMapper);
		//System.out.println(scooterList.size());
	}
	/**
	 * Initiate exist users: read external file and create {@code User} class respectively
	 * @param SingletonObjectMapper an reusable ObjectMapper instance
	 * @return {@code true} if successfully executed; otherwise {@code false}.
	 */
	private boolean initiateUser(ObjectMapper SingletonObjectMapper) {
		ObjectMapper objectMapper = SingletonObjectMapper;
		try {
            // Read the JSON file
            File file = new File("resources/user.json");
            
            // Map the JSON array to a list of Location objects
            this.userList = objectMapper.readValue(file, new TypeReference<ArrayList<User>>() {});
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}
	/**
	 * Initiate exist scooters read external file and create {@code Scooter} class respectively.
	 * @param SingletonObjectMapper an reusable ObjectMapper instance
	 * @return {@code true} if successfully executed; otherwise {@code false}.
	 */
	private boolean  initiateScooter(ObjectMapper SingletonObjectMapper) {
		ObjectMapper objectMapper = SingletonObjectMapper;
		try {
            // Read the JSON file
			File file = new File("resources/scooter_detail.json");
            
            // Map the JSON array to a list of Location objects
            this.scooterList = objectMapper.readValue(file, new TypeReference<ArrayList<Scooter>>() {});
//            for (Scooter scooter : scooterList) {
//                System.out.println("No: " + scooter.getNo());
//   	          System.out.println("Power: " + scooter.getPower());	                
//                System.out.println("Latitude: " + scooter.getLat());
//                System.out.println("Longitude: " + scooter.getLng());
//                System.out.println();
//            }        
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}
	/**
	 * Initiate repairmans
	 * @param SingletonObjectMapper an reusable ObjectMapper instance	
	 * @return {@code true} if successfully executed; otherwise {@code false}.
	 */
	private boolean  initiateRepairman(ObjectMapper SingletonObjectMapper) {
		ObjectMapper objectMapper = SingletonObjectMapper;
		try {
            // Read the JSON file
            File file = new File("resources/repairman.json");
            
            // Map the JSON array to a list of Location objects
            this.repairmanList = objectMapper.readValue(file, new TypeReference<ArrayList<Repairman>>() {});
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}
	/**
	 * Initiate ChargingStations, read external file and create {@code ChargingStation} class respectively
	 * @param SingletonObjectMapper an reusable ObjectMapper instance
	 * @return 
	 */
	private boolean  initiateChargingStation(ObjectMapper SingletonObjectMapper) {
		ObjectMapper objectMapper = SingletonObjectMapper;
		try {
            // Read the JSON file
            File file = new File("resources/battery.json");
            
            // Map the JSON array to a list of Location objects
            this.chargingStationList = objectMapper.readValue(file, new TypeReference<ArrayList<ChargingStation>>() {});
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}
	/**
	 * input user's account and password, if valid, the user will be stored as operator
	 * @param account the account
	 * @param password the password
	 * @return {@code true} if successfully executed; otherwise {@code false}.
	 */
	public boolean userLogIn(String account, String password) {
		try {
			for(User user: userList){
				if(account.equals(user.getAccount()) && password.equals(user.getPassword())) {
					this.userOperator = user;
					this.userOperator.setInitialPosition();
					//System.out.println("Welcome User: " + userOperator.getAccount());
					return true;
				}
			}
			return false;
		}catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}
	/**
	 * Log out current user
	 * @return {@code true} if successfully executed; otherwise {@code false}.
	 */
	public boolean userLogOut() {
		try {
			this.userOperator = null;
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * input repairman's account and password, if valid, the repairman will be stored as UserOperator
	 * @param account the account
	 * @param password the password
	 * @return {@code true} if successfully executed; otherwise {@code false}.
	 */
	public boolean repairmanLogIn(String account, String password) {
		try {
			for(Repairman repairman: repairmanList){
				if(account.equals(repairman.getAccount()) && password.equals(repairman.getPassword())) {
					this.rpOperator = repairman;
					//System.out.println("Welcome Repairman: " + rpOperator.getAccount());
					return true;
				}
			}
			return false;
		}catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}
	/**
	 * Log out current repairman
	 * @return {@code true} if successfully executed; otherwise {@code false}.
	 */
	public boolean repairmanLogOut() {
		try {
			this.rpOperator = null;
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * Return user's position for GUI display
	 * use {@code Position.lat} and {@code Position.lng} to get latitude and longitude respectively
	 * @param user user who requires it's geography position
	 * @return user's position
	 */
	public Position showUserPosition(User user) {
		return user.getPosition();
	}
	/**
	 * serach available scooter in given range from user's current position
	 * @param user who executes searching
	 * @param diameter diameter of searching circle in Kilometer
	 * @return the list of scooters which are available in given range; if no suitable scooter is found then null
	 */
	public ArrayList<Scooter> searchScooter(User user, double diameter){
		try {
			double radius = diameter * 0.005;
			Double[] latLimit = {(user.getPosition().lat - radius),(user.getPosition().lat + radius)};
			Double[] lngLimit = {(user.getPosition().lng - radius),(user.getPosition().lng + radius)};
			ArrayList<Scooter> scooterWithinRange = new ArrayList<Scooter>(10);
			for(Scooter scooter: scooterList) {
				if(scooter.getLat() < latLimit[1] && scooter.getLat() > latLimit[0] &&
				   scooter.getLng() < lngLimit[1] && scooter.getLng() > lngLimit[0]) {
					if(scooter.getStatus() == Scooter.ScooterStatus.IDLE)
						scooterWithinRange.add(scooter);
				}
			}
			return scooterWithinRange;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * start renting the scooter, will trigger following actions:
	 * (1) store {@code Scooter} inside {@code User} to represent the action of renting a scooter, 
	 * (2) update user's position to scooter's position
	 * (3) Switch scooter's status to occupied
	 * (4) new a {@code RentHistory} to user
	 * @param user user who rents the scooter
	 * @param scooter the scooter been rent
	 * @return
	 */
	public boolean rentScooter(User user, Scooter scooter) {
		try {
			user.setScooter(scooter);
			user.setPosition(scooter.getPosition());
			scooter.setStatus(ScooterStatus.Occupied);
			user.newRentEvent();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * start riding scooter, the position will be updated and recorded to the rentHistory
	 * @param user target user
	 * @return {@code true} if successfully executed; otherwise {@code false}
	 */
	public boolean rideScooter(User user) {
        try {
        	this.latch = new CountDownLatch(1);
    		rideScooterThread = new RideScooterThread(user, latch);
    		rideScooterThread.start();
    		return true;
        }catch(Exception e) {
        	e.printStackTrace();
        	return false;
        }
	}
	/**
	 * temporarily stop updating position and recording rentHistory 
	 * @param user target user
	 * @return {@code true} if successfully executed; otherwise {@code false}
	 */
	public boolean stopRidingScooter(User user) {
		try {
			this.rideScooterThread.stopUpdating(); // Stop the distance update thread
			try {
	            this.latch.await(); // Wait until the distance update is complete
	            return true;
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	            return false;
	        }
        }catch(Exception e) {
        	e.printStackTrace();
        	return false;
        }
	}
	/**
	 * search charging stations within default distance from the user's current position
	 * @param position current user position
	 * @return the list of ChargingStation within default distance; if no suitable charging station is found then null
	 */
	public ArrayList<ChargingStation> searchChargingStation(User user){
		try {
			double radius = 0.025;// default 5 Km diameter
			Double[] latLimit = {(user.getPosition().lat - radius),(user.getPosition().lat + radius)};
			Double[] lngLimit = {(user.getPosition().lng - radius),(user.getPosition().lng + radius)};
			ArrayList<ChargingStation> stationWithinRange = new ArrayList<ChargingStation>(10);
			for(ChargingStation station: chargingStationList) {
				if(station.getLat() < latLimit[1] && station.getLat() > latLimit[0] &&
				   station.getLng() < lngLimit[1] && station.getLng() > lngLimit[0]) {
					stationWithinRange.add(station);
				}
			}
			return stationWithinRange;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * move to the selected charging station, if the remain battery is not enough, this action will not be executed
	 * The transfer efficiency is set at 1Km-to-1% of battery
	 * @param user target user
	 * @param chargingStation target charging station
	 * @return {@code true} if successfully executed; otherwise {@code false}
	 */
	public boolean moveToChargingStation(User user, ChargingStation chargingStation) {
		try {
			double distance = Position.calculateDistance(user.getPosition(), chargingStation.getPosition());
			System.out.println("Distance from Scooter rider to Charging station is: " + distance);
			System.out.println("Remaining battery is: " + user.getScooter().getPower() + "%");
			if(user.getScooter().getPower() > distance) {
				user.setPosition(chargingStation.getPosition());
				//user.getScooter().setPosition(chargingStation.getPosition());
				user.getRentEvent().updatePositionHistory(chargingStation.getPosition());
				return true;
			}
			return false;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * rewrite scooter's battery to 100
	 * @param user user who execute charging
	 * @return {@code true} if successfully executed; otherwise {@code false}
	 */
	public boolean chargeScooter(User user){
		try {
			user.getScooter().setPower(100);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * terminate a ride, the position will stop updating and rentHistory will no longer be record
	 * @param user target user
	 * @return {@code true} if successfully executed; otherwise {@code false}
	 */
	public boolean endRidingScooter(User user) {
		try {
			this.rideScooterThread.stopUpdating(); // Stop the distance update thread
			try {
	            this.latch.await(); // Wait until the distance update is complete
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	            return false;
	        }
			user.getRentEvent().setRentEndTime(LocalTime.now());
			user.getRentEvent().calculateTotalTime();
			user.getRentEvent().CalculateDistance();
			return true;
        }catch(Exception e) {
        	e.printStackTrace();
        	return false;
        }
	}
	/**
	 * return display Position and distance for GUI display
	 * @param user target user
	 * @return [latitude, longitude, distance]; if fail, then return {@code null}
	 */
	public double[] displayPositionAndDistance(User user) {
		try {
			double[] result = {user.getLat(), user.getLng(), user.getRentEvent().getDistance()};
			return result;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * return the fee of the current ride for GUI display
	 * @param user target
	 * @return the value of fee; otherwise returns -1.0
	 */
	public double displayFee(User user) {
		try {
			//user.getRentEvent().calculateRentFee();
			return user.getRentEvent().getRentFee();
		}catch(Exception e) {
			e.printStackTrace();
			return -1.0;
		}
	}
	/**
	 * execute payment and decouple the scooter from the user
	 * @param user target user
	 * @param withCoupon {@code true} if use coupon for this ride; otherwise {@code false}
	 * @return {@code true} if successfully executed; otherwise {@code false}
	 */
	public boolean payFeeAndReturnScooter(User user, boolean withCoupon) {
		try {
			user.getRentHistory().add(user.getRentEvent());
			user.clearRentEvent();
			user.setScooter(null);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * search for malfunction scooters
	 * @param repairman repairman who execute searching
	 * @return the list of all malfunction scooters; otherwise {@code null}
	 */
	public ArrayList<Scooter> searchMalfunctionScooter(Repairman repairman){
		try {
			ArrayList<Scooter> brokenScooter = new ArrayList<Scooter>(10);
			for(Scooter scooter: scooterList) {
				if(ScooterStatus.Malfunction == scooter.getStatus()) {
					brokenScooter.add(scooter);
				}
			}
			return brokenScooter;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * fixes a scooter, will switch the status of a scooter from {@code Malfunction} to {@code IDLE}.
	 * @param repairman repairman who execute the fixing
	 * @param scooter the scooter to be fixed
	 * @return {@code true} if successfully executed; otherwise {@code false}
	 */
	public boolean fixScooter(Repairman repairman, Scooter scooter) {
		try {
			scooter.setStatus(ScooterStatus.IDLE);
			return true;	
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * charge a scooter. Then relocate the scooter to a different position
	 * @param repairman
	 * @return
	 */
	public boolean chargeLowBatteryScooter(Repairman repairman, Scooter scooter) {
		try {
			scooter.setPower(100);
			double[] latRange = {25.026708,25.068277};
		    double[] lngRange = {121.511162, 121.567045};
		    Random random = new Random();
		    double randomLat = latRange[0] + random.nextDouble() * (latRange[1] - latRange[0]);
		    double randomLng= lngRange[0] + random.nextDouble() * (lngRange[1] - lngRange[0]);
			scooter.setPosition(randomLat, randomLng);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	}
