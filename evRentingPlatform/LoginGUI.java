package evRentingPlatform;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class LoginGUI extends JFrame {

	private JPanel contentPane;
	private JTextField account;
	private JPasswordField password;
	private JTextField staffaccount;
	private JPasswordField staffpassword;
	//
	private CardLayout cardlayout;
	JLabel errormessage = new JLabel("");
	private JTextField newaccount;
	private JPasswordField newpassword;
	//
	private ArrayList<Repairman> repairmanlist;
	//
	  private static ArrayList<Repairman> loadRepairmanDataFromJson(String filePath) {
	        ObjectMapper objectMapper = new ObjectMapper();
	        try {
	            File file = new File(filePath);
	            Repairman[]repairmanrArray = objectMapper.readValue(file, Repairman[].class);
	            return new ArrayList<>(Arrays.asList(repairmanrArray));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return new ArrayList<>();
	    }
	 //
	  public boolean checkArraysEquality(String[] array1, String[] array2) {
		    // Check if array lengths are equal
		    if (array1.length != array2.length) {
		        return false;
		    }

		    // Iterate over each element of the arrays
		    for (int i = 0; i < array1.length; i++) {
		        // Compare elements using equals() method
		        if (!array1[i].equals(array2[i])) {
		            return false;
		        }
		    }

		    // All elements are equal
		    return true;
		}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		setTitle("EV Renting Platform");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to EV Renting Platform");
		lblNewLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 45));
		lblNewLabel.setLabelFor(lblNewLabel);
		lblNewLabel.setBounds(571, 127, 733, 66);
		contentPane.add(lblNewLabel);
		
		cardlayout=new CardLayout();
		
		JPanel mainpane = new JPanel();
		mainpane.setBounds(540, 346, 782, 434);
		contentPane.add(mainpane);
		mainpane.setLayout(cardlayout);

		JPanel customerpane = new JPanel();
		customerpane.setBounds(540, 346, 782, 434);
		mainpane.add(customerpane,"customerpane");
		customerpane.setLayout(null);
		
		JLabel Account = new JLabel("Account:");
		Account.setFont(new Font("標楷體", Font.PLAIN, 30));
		Account.setBounds(62, 124, 146, 62);
		customerpane.add(Account);
		
		JLabel Password = new JLabel("Password:");
		Password.setFont(new Font("標楷體", Font.PLAIN, 30));
		Password.setBounds(49, 214, 146, 56);
		customerpane.add(Password);
		
		account = new JTextField();
		account.setFont(new Font("標楷體", Font.PLAIN, 20));
		account.setBounds(205, 137, 442, 40);
		customerpane.add(account);
		account.setColumns(10);
		
		password = new JPasswordField();
		password.setFont(new Font("\u6A19\u6977\u9AD4", password.getFont().getStyle(), 20));
		password.setBounds(205, 223, 442, 42);
		customerpane.add(password);
		
		JButton login = new JButton("登入");
		UserLogin userlogin= new UserLogin();
		login.addActionListener(userlogin);
		login.setFont(new Font("標楷體", Font.PLAIN, 25));
		login.setBounds(518, 292, 129, 62);
		customerpane.add(login);
		
		errormessage.setFont(new Font("標楷體", Font.PLAIN, 15));
		errormessage.setForeground(new Color(255, 0, 0));
		errormessage.setBounds(252, 275, 263, 22);
		customerpane.add(errormessage);
		
		JButton enrollbottom = new JButton("立刻註冊");
		enrollbottom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(mainpane,"enrollpane");
			}
		});
		enrollbottom.setFont(new Font("標楷體", Font.PLAIN, 18));
		enrollbottom.setBounds(96, 280, 146, 40);
		customerpane.add(enrollbottom);
		
		JPanel staffpane = new JPanel();
		staffpane.setLayout(null);
		mainpane.add(staffpane,"staffpane");
		
		JLabel Staffaccount = new JLabel("Account:");
		Staffaccount.setFont(new Font("標楷體", Font.PLAIN, 30));
		Staffaccount.setBounds(62, 124, 146, 62);
		staffpane.add(Staffaccount);
		
		JLabel Staffpassword = new JLabel("Password:");
		Staffpassword.setFont(new Font("標楷體", Font.PLAIN, 30));
		Staffpassword.setBounds(49, 214, 146, 56);
		staffpane.add(Staffpassword);
		
		staffaccount = new JTextField();
		staffaccount.setFont(new Font("標楷體", Font.PLAIN, 20));
		staffaccount.setColumns(10);
		staffaccount.setBounds(205, 137, 442, 40);
		staffpane.add(staffaccount);
		
		staffpassword = new JPasswordField();
		staffpassword.setFont(new Font("標楷體", Font.PLAIN, 20));
		staffpassword.setBounds(205, 223, 442, 42);
		staffpane.add(staffpassword);
		
		JButton stafflogin = new JButton("登入");
		StaffLogin stafflog= new StaffLogin();
		stafflogin.addActionListener(stafflog);
		stafflogin.setFont(new Font("標楷體", Font.PLAIN, 25));
		stafflogin.setBounds(518, 292, 129, 62);
		staffpane.add(stafflogin);
		
		JLabel errormessage_1 = new JLabel("");
		errormessage_1.setForeground(Color.RED);
		errormessage_1.setFont(new Font("標楷體", Font.PLAIN, 15));
		errormessage_1.setBounds(245, 289, 263, 29);
		staffpane.add(errormessage_1);
		
		JPanel enrollpane = new JPanel();
		enrollpane.setLayout(null);
		mainpane.add(enrollpane, "enrollpane");
		
		JLabel Newaccount = new JLabel("Account:");
		Newaccount.setFont(new Font("標楷體", Font.PLAIN, 30));
		Newaccount.setBounds(62, 124, 146, 62);
		enrollpane.add(Newaccount);
		
		JLabel Newpassword = new JLabel("Password:");
		Newpassword.setFont(new Font("標楷體", Font.PLAIN, 30));
		Newpassword.setBounds(49, 214, 146, 56);
		enrollpane.add(Newpassword);
		
		newaccount = new JTextField();
		newaccount.setFont(new Font("標楷體", Font.PLAIN, 20));
		newaccount.setColumns(10);
		newaccount.setBounds(205, 137, 442, 40);
		enrollpane.add(newaccount);
		
		newpassword = new JPasswordField();
		newpassword.setToolTipText("");
		newpassword.setFont(new Font("標楷體", Font.PLAIN, 20));
		newpassword.setBounds(205, 223, 442, 42);
		enrollpane.add(newpassword);
		
		JButton enroll = new JButton("註冊");
		enroll.setFont(new Font("標楷體", Font.PLAIN, 25));
		enroll.setBounds(518, 292, 129, 62);
		enrollpane.add(enroll);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 18));
		menuBar.setBounds(0, 0, 167, 73);
		contentPane.add(menuBar);
		
		JMenu menu = new JMenu("切換維修人員/顧客");
		menuBar.add(menu);
		menu.setBackground(new Color(128, 255, 128));
		menu.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 18));
		
		JMenuItem customer = new JMenuItem("顧客");
		customer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(mainpane,"customerpane");
			}
		});
		customer.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		menu.add(customer);
		
		JMenuItem staff = new JMenuItem("維修人員");
		staff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(mainpane,"staffpane");
			}
		});
		staff.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		menu.add(staff);
	}
	
	public class UserLogin extends JFrame implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			 // 
            String acc=staffaccount.getText();
            String pw=new String(staffpassword.getPassword());
            RentScooterService login=new RentScooterService();
            if(login.userLogIn(acc,pw)) {
            	UserGUI user=new UserGUI();
    			user.setVisible(true);
    			LoginGUI.this.dispose();
        }
			errormessage.setText("InValid Account/Password");
		}
	}
	public class StaffLogin extends JFrame implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// Load Repairman data from JSON file
	        repairmanlist = loadRepairmanDataFromJson("resources/repairman.json");
	        // 
	            String acc=staffaccount.getText();
	            String pw=new String(staffpassword.getPassword());
	            RentScooterService login=new RentScooterService();
	            if(login.repairmanLogIn(acc,pw)) {
	        		StaffGUI worker=new StaffGUI();
	    			worker.setVisible(true);
	    			LoginGUI.this.dispose();
	        }
			errormessage.setText("InValid Account/Password");
			errormessage.setVisible(true);
		}
	}
}
