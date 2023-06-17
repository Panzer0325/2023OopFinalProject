package evRentingPlatform;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScooterSelectionGUI extends JFrame {
	
    private JTable carTable;
    private JButton selectButton;
    private UserGUI frame1;
    private String selectedScooterNumber;
    private ArrayList<Scooter> scooterList;
    private Scooter selectedscooter;
    /**
	 * 
	 */
    public ScooterSelectionGUI(RentScooterService service,ArrayList<Scooter> scooterList,UserGUI frame1,double range) {
    	this.scooterList=scooterList;
    	this.frame1=frame1;
        // Set up the frame
        setTitle("選擇車輛");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create the table model and set column names
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new Object[]{"Number", "Latitude", "Longitude", "Power"});
        
        // Load scooter data from JSON file
        scooterList = service.searchScooter(service.getUserOperator(),range);

        // Populate the table with scooter data
        for (Scooter scooter : scooterList) {
            Object[] rowData = {scooter.getNo(), scooter.getLat(), scooter.getLng(), scooter.getPower()};
            tableModel.addRow(rowData);
        }

        // Create the table and set the model
        carTable = new JTable(tableModel);
        carTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Create the select button
        selectButton = new JButton("確定");
        selectButton.setFont(new Font("標楷體", Font.PLAIN, 20));
        selectButton.addActionListener(e -> {
            // Get the selected row
            int selectedRow = carTable.getSelectedRow();
            if (selectedRow != -1) {
                selectedScooterNumber = (String) carTable.getValueAt(selectedRow, 0);
                JOptionPane.showMessageDialog(this, "租借車輛為: " + selectedScooterNumber);
                selectedscooter=new Scooter(selectedScooterNumber,(double) carTable.getValueAt(selectedRow, 1),(double) carTable.getValueAt(selectedRow, 2),(int) carTable.getValueAt(selectedRow, 3));
                service.rentScooter(service.getUserOperator(),selectedscooter);
                frame1.num.setText(getselectedScooterNumber());
                frame1.getCardLayout().show(frame1.getCardPanel(), "ride");
                UserGUI.currentcard="ride";
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "請選擇車輛");
            }
        });

        // Create a scroll pane and add the table
        JScrollPane scrollPane = new JScrollPane(carTable);

        // Set the layout of the frame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(selectButton, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
        
       
    }
    
    //getter for selectedCarnumber
    public String getselectedScooterNumber() {
    	return selectedScooterNumber;
    }
    
    //method for testing
    public static void main(String[] args) {
    	SwingUtilities.invokeLater(() -> {
    	    UserGUI frame1 = new UserGUI(null);
    	    ScooterSelectionGUI scooterSelectionGUI = new ScooterSelectionGUI(null,null, null,10);
    	    scooterSelectionGUI.setVisible(true);
    	});
    }
    
}
