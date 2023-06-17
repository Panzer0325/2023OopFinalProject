package evRentingPlatform;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
    
    public RentHistoryGUI() {

    	setTitle("Car Renting History");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Create table with columns
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Date");
        tableModel.addColumn("Rent Fee");
        tableModel.addColumn("Start Position");
        tableModel.addColumn("End Position");
        tableModel.addColumn("Distance");
        tableModel.addColumn("Rent Start Time");
        tableModel.addColumn("Rent End Time");
        tableModel.addColumn("Total Time");
        tableModel.addColumn("Charge Times");
        tableModel.addColumn("With Coupon");
        
        historyTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(historyTable);

        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(scrollPane, BorderLayout.CENTER);
        
        JButton ok = new JButton("ok");
        ok.setFont(new Font("標楷體", Font.PLAIN, 20));
        ok.addActionListener(this);
        getContentPane().add(ok, BorderLayout.SOUTH);
        
        //for testing
        addRentingEntry(LocalDate.now(), 100.0, "A", "B", 10.0,
                LocalTime.of(8, 0), LocalTime.of(9, 0), Duration.ofHours(1),
                1, true);
        addRentingEntry(LocalDate.now(), 150.0, "B", "C", 15.0,
                LocalTime.of(10, 0), LocalTime.of(11, 0), Duration.ofHours(1),
                1, false);
    }
    //add data to history record
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
  
        RentHistoryGUI gui = new RentHistoryGUI();
        
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
