package evRentingPlatform;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.fasterxml.jackson.databind.ObjectMapper;

public class StaffGUI extends JFrame {

    private JTable scooterTable;
    private DefaultTableModel tableModel;
    private List<Scooter> scooterList;

    public StaffGUI() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Scooter List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Create table with columns
        tableModel = new DefaultTableModel();
        tableModel.addColumn("No.");
        tableModel.addColumn("Latitude");
        tableModel.addColumn("Longitude");
        tableModel.addColumn("Power");
        tableModel.addColumn("Status");

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
        
        // Load JSON file
        loadScooterDataFromJson("resources/scooter_detail.json");
        
        // Attach event listeners to buttons
        changeStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = scooterTable.getSelectedRow();
                if (selectedRow >= 0) {
                	Scooter.ScooterStatus currentStatus = (Scooter.ScooterStatus) tableModel.getValueAt(selectedRow, 4);
                    if (currentStatus==Scooter.ScooterStatus.Malfunction) {
                        tableModel.setValueAt(Scooter.ScooterStatus.Repairing, selectedRow, 4);
                        
                    } else if (currentStatus==Scooter.ScooterStatus.Repairing) {
                        tableModel.setValueAt(Scooter.ScooterStatus.Repairing.IDLE, selectedRow, 4);
                    }
                    else {
                    	JOptionPane.showMessageDialog(StaffGUI.this, "機車狀態正常");
                    }
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
                    int currentPower = (int) tableModel.getValueAt(selectedRow, 3);
                    if (currentPower < 20) {
                        double newLatitude = Math.random() * (25.068277 - 25.026708) + 25.026708;
                        double newLongitude = Math.random() * (121.567045 - 121.511162) + 121.511162;

                        tableModel.setValueAt(100, selectedRow, 3);
                        tableModel.setValueAt(newLatitude, selectedRow, 1);
                        tableModel.setValueAt(newLongitude, selectedRow, 2);
                        JOptionPane.showMessageDialog(StaffGUI.this, "充電完成");
                    } else {
                        JOptionPane.showMessageDialog(StaffGUI.this, "電量已高於20");
                    }
                } else {
                    JOptionPane.showMessageDialog(StaffGUI.this, "Please select a row.");
                }
            }
        });
    }

    //get data from .json file
    public void loadScooterDataFromJson(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            scooterList = objectMapper.readValue(new File(filePath), objectMapper.getTypeFactory().constructCollectionType(List.class, Scooter.class));
            for (Scooter scooter : scooterList) {
                tableModel.addRow(new Object[]{
                        scooter.getNo(),
                        scooter.getLat(),
                        scooter.getLng(),
                        scooter.getPower(),
                        scooter.getStatus()
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  //update data to .json file
    private void updateScooterDataToJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/main/resources/scooter_detail.json"), scooterList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        StaffGUI gui = new StaffGUI();
        gui.setVisible(true);

        // Load scooter data from JSON file
        gui.loadScooterDataFromJson("src/main/resources/scooter_detail.json");
    }
}
