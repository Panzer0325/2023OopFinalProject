package evRentingPlatformGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import evRentingPlatform.Position;
import evRentingPlatform.RentHistory;
import evRentingPlatform.RentScooterService;
import evRentingPlatform.Scooter;

import java.awt.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RentHistoryGUI extends JFrame implements ActionListener {

    private JTable historyTable;
    private DefaultTableModel tableModel;
    /**
	 * Record of position, should be updated whenever the position is updated
	 */
	private ArrayList<RentHistory> historylist = new ArrayList<RentHistory> (10);
	private ArrayList<Position> position = new ArrayList<Position> (10);
    
    public RentHistoryGUI(RentScooterService service) {
    	//setup the frame
    	setTitle("Car Renting History");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Create the table model and set column names
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new Object[]{"No.","ScooterNo.","Date","Rent Fee", "Start Position", "End Position", "Distance","Start Time","End Time","Total Time","Charge Times","With Coupon"});
        
        //get rent history
        historylist=service.getUserOperator().getRentHistory();
        
        // Populate the table with history data
        for (RentHistory history : historylist) {
        	position=history.getPositionHistory();
        	Position StartPosition = position.get(0);
        	Position EndPosition = position.get(position.size()-1);
            Object[] rowData = {history.getHistoryID(),history.getScooterNo(),history.getDate(), history.getRentFee(),StartPosition,EndPosition, history.getDistance(),history.getRentStartTime(),history.getRentEndTime(),history.getTotalTime(),history.getChargeTimes(),history.isWithCoupon()};
            tableModel.addRow(rowData);
           }
        // Create the table and set the model	
        historyTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(historyTable);
        
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(scrollPane, BorderLayout.CENTER);
        
        JButton ok = new JButton("ok");
        ok.setFont(new Font("標楷體", Font.PLAIN, 20));
        ok.addActionListener(this);
        getContentPane().add(ok, BorderLayout.SOUTH);
        
    }
    //add data to history record (for testing)
    public void addRentingEntry(LocalDate date, double rentFee, String startPosition, String endPosition,
                               double distance, LocalTime rentStartTime, LocalTime rentEndTime, Duration totalTime,
                               int chargeTimes, boolean withCoupon) {
        tableModel.addRow(new Object[]{
                date,
                rentFee,
                startPosition,
                endPosition,
                distance,
                rentStartTime,
                rentEndTime,
                totalTime,
                chargeTimes,
                withCoupon
        });
    }
    //for testing
    public static void main(String[] args) {
  
        RentHistoryGUI gui = new RentHistoryGUI(null);
        
        gui.setVisible(true);

        gui.addRentingEntry(LocalDate.now(), 100.0, "A", "B", 10.0,
                LocalTime.of(8, 0), LocalTime.of(9, 0), Duration.ofHours(1),
                1, true);
        gui.addRentingEntry(LocalDate.now(), 150.0, "B", "C", 15.0,
                LocalTime.of(10, 0), LocalTime.of(11, 0), Duration.ofHours(1),
                1, false);
    }

	@Override
	
	public void actionPerformed(ActionEvent e) {
		this.dispose();
	}
}
