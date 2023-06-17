package evRentingPlatform;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;


public class ChargeStationGUI extends JFrame implements ActionListener{

    private JTable stationTable;
    private DefaultTableModel tableModel;
    private UserGUI frame2;
    private ChargingStation chargingstation;
    RentScooterService service;
    public ChargeStationGUI(RentScooterService service,UserGUI frame2) {
    	this.service=service;
    	this.frame2=frame2;
        setTitle("Charge Station List");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Create table with columns
        tableModel = new DefaultTableModel();
        tableModel.addColumn("No.");
        tableModel.addColumn("Latitude");
        tableModel.addColumn("Longitude");
        

        stationTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(stationTable);

        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(scrollPane, BorderLayout.CENTER);
        
        JButton SelectBottom = new JButton("確定");
        SelectBottom.addActionListener(this);
        SelectBottom.setFont(new Font("標楷體", Font.PLAIN, 20));
        getContentPane().add(SelectBottom, BorderLayout.SOUTH);
        
        // Load charge station data from JSON file
        List<ChargingStation> chargeStationList =service.searchChargingStation(service.getUserOperator()) ;

        // Add charge station data to the GUI
        for (ChargingStation chargeStation : chargeStationList) {
            addChargeStation(chargeStation.getLat(), chargeStation.getLng(), chargeStation.getNo());
        }
        
    }

    public void addChargeStation(double latitude, double longitude, int no) {
        tableModel.addRow(new Object[]{
        		no,
                latitude,
                longitude
        });
    }
    //test sample
    public static void main(String[] args) {
        UserGUI frame2 = new UserGUI(null);
        ChargeStationGUI gui = new ChargeStationGUI(null,frame2);

        // Load charge station data from JSON file
        List<ChargingStation> chargeStationList = gui.loadChargeStationDataFromJson("src/main/resources/battery.json");

        // Add charge station data to the GUI
        for (ChargingStation chargeStation : chargeStationList) {
            gui.addChargeStation(chargeStation.getLat(), chargeStation.getLng(), chargeStation.getNo());
        }

        gui.setVisible(true);
    }


	@Override
	public void actionPerformed(ActionEvent e) {
		// Get the selected row
        int selectedRow = stationTable.getSelectedRow();
        if (selectedRow != -1) 
        {
        	chargingstation=new ChargingStation((int) stationTable.getValueAt(selectedRow,0),(double) stationTable.getValueAt(selectedRow,1),(double) stationTable.getValueAt(selectedRow,2));
        	if(service.moveToChargingStation(service.getUserOperator(),chargingstation)) {
        		service.chargeScooter(service.getUserOperator());
        		JOptionPane.showMessageDialog(this, "充電完成!" );
        		this.dispose();
        	}
        	else {
        		 JOptionPane.showMessageDialog(this, "抱歉，剩餘電量不足以到達此站");
        	}
        } 
        else {
            JOptionPane.showMessageDialog(this, "請選擇充電站");
        }
		
	}
	//
	private static List<ChargingStation> loadChargeStationDataFromJson(String filePath) {
	    ObjectMapper objectMapper = new ObjectMapper();
	    try {
	        File file = new File(filePath);
	        ChargingStation[] chargeStationArray = objectMapper.readValue(file, ChargingStation[].class);
	        return new ArrayList<>(Arrays.asList(chargeStationArray));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return new ArrayList<>();
	}

}
