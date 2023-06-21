package evRentingPlatformGUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.fasterxml.jackson.databind.ObjectMapper;

import evRentingPlatform.RentScooterService;
import evRentingPlatform.Repairman;
import evRentingPlatform.Scooter;
import evRentingPlatform.Scooter.ScooterStatus;

public class StaffGUI extends JFrame {

    private JTable scooterTable;
    private DefaultTableModel tableModel= new DefaultTableModel();
    private int selectedRow;
    private ArrayList<Scooter> scooterList;
    private Scooter selectedscooter;
    private RentScooterService service;

    public StaffGUI(RentScooterService service){
    	//default information of the frame
        setTitle("Scooter List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        // Create the table model and set column names
        tableModel.setColumnIdentifiers(new Object[]{"Number", "Latitude", "Longitude", "Power", "Status"});
        
        // Load scooter data from file
        scooterList = service.searchMalfunctionScooter(service.getrpOperator());
        
        // Populate the table with scooter data
        for (Scooter scooter : scooterList) {
            Object[] rowData = {scooter.getNo(), scooter.getLat(), scooter.getLng(), scooter.getPower(),scooter.getStatus().toString()};
            tableModel.addRow(rowData);
        }
        
        // Create the table and set the model
        scooterTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(scooterTable);

        JButton changeStatusButton = new JButton("Change Status");
        JButton changePowerButton = new JButton("Change Power");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(changeStatusButton);
        buttonPanel.add(changePowerButton);

        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(scrollPane, BorderLayout.CENTER);
        container.add(buttonPanel, BorderLayout.SOUTH);
        
        JButton logoutbottum = new JButton("Log Out");
        logoutbottum.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(service.repairmanLogOut()) {
        			LoginGUI login=new LoginGUI(service);
        			login.setVisible(true);
				StaffGUI.this.dispose();
        		}
        	}
        });
        buttonPanel.add(logoutbottum);
        
        // Attach event listeners to fix broken scooters
        changeStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedRow = scooterTable.getSelectedRow();
                if (selectedRow >= 0) {

                	selectedscooter=new Scooter((String) tableModel.getValueAt(selectedRow, 0),(double) tableModel.getValueAt(selectedRow, 1),(double) tableModel.getValueAt(selectedRow, 2),(int) tableModel.getValueAt(selectedRow, 3));
                	tableModel.setValueAt(Scooter.ScooterStatus.Repairing, selectedRow, 4);
                		if(service.fixScooter(service.getrpOperator(),selectedscooter)==true){             		              
                    		JOptionPane.showMessageDialog(StaffGUI.this, "修理完成");
                    		tableModel.setValueAt(Scooter.ScooterStatus.IDLE, selectedRow, 4);
                    	};
            	     }
                else {
                    JOptionPane.showMessageDialog(StaffGUI.this, "Please select a row.");
                }
            }
       });
       
        
        //
        changePowerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = scooterTable.getSelectedRow();
                if (selectedRow >= 0) {
                	selectedscooter=new Scooter((String) tableModel.getValueAt(selectedRow, 0),(double) tableModel.getValueAt(selectedRow, 1),(double) tableModel.getValueAt(selectedRow, 2),(int) tableModel.getValueAt(selectedRow, 3));                	
                    if (selectedscooter.getPower()<20) {
                    	if(service.chargeLowBatteryScooter(service.getrpOperator(),selectedscooter)) {
                    		tableModel.setValueAt(selectedscooter.getPosition().lat, selectedRow, 1);
                    		tableModel.setValueAt(selectedscooter.getPosition().lng, selectedRow, 2);
                    		JOptionPane.showMessageDialog(StaffGUI.this, "充電完成");
                    		tableModel.setValueAt(100, selectedRow, 3);
                    } 
                }
                    else {
                        JOptionPane.showMessageDialog(StaffGUI.this, "電量已高於20");
                    }              
                }
                else {
                    JOptionPane.showMessageDialog(StaffGUI.this, "Please select a row.");                
                }
            }
        });
    }

    /**
	 * methods for testing
	 */
   
  //update data to .json file(for testing)
    private void updateScooterDataToJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("resources/scooter_detail.json"), scooterList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        StaffGUI gui = new StaffGUI(null);
        gui.setVisible(true);
    }
}
