package evRentingPlatformGUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import evRentingPlatform.Coupon;
import evRentingPlatform.Position;
import evRentingPlatform.RentScooterService;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class UserGUI extends JFrame {

	//User Informations
	private JTextField account;
	private JTextField password;
	private JTextField phone;
	private JTextField name;
	private JTextField email;
	private JTextField payment;
	private JTextField range;
	private JTextField location;
	private JTextField totalrange;
	private JTextField fee;
	private JTextField couponnum;
	
	//panels for UserGUI
	private JPanel contentPane;
	CardLayout cardlayout = new CardLayout();
	JPanel mainpane = new JPanel(cardlayout);
	JPanel accountpane = new JPanel();
	JPanel rentpane = new JPanel();
	JPanel checkoutpane = new JPanel();
	JPanel ridepane = new JPanel();
	/**
	 * @currentcard record the current card 
	 */
	public static String currentcard;
	
	private ScooterSelectionGUI scooterselectionFrame; // Add a reference to the CarSelectionGUI frame
	private ChargeStationGUI ChargeStationFrame;// Add a reference to the ChargeStationGUI frame
	private RentScooterService service;//scooterservice
	
	//record user's final position
	public double[] userfinalposition;
	/**
	 * @param coupon user's available coupon number
	 * @param hascoupon Label that inform user has available coupon 
	 * @checkbox withcoupon whether user want to use coupon 
	 */
	private Coupon coupon;
	private JLabel hasCoupon;
	private JCheckBox withcoupon;
	/**
	 * @image riding image of riding
	 * @image pauseriding image of stop riding
	 * 
	 */
	private JLabel riding;
	private JLabel pauseriding;
	private static JLabel CurrentPosition;
	private static JLabel PowerRemain;
	private static JLabel DriveRange;
	JLabel num = new JLabel("");
	
	//for bult-in google map
	private static WebView webView;
	public static JFXPanel Mappanel;
	
	//getters for cardlayout
	public CardLayout getCardLayout() {
		return cardlayout;
	}
	public JPanel getCardPanel() {
		return mainpane;
	}
	
	//display remaining power
	public static void DisplayPowerRemain(int power) {
	    SwingUtilities.invokeLater(() -> {
	        PowerRemain.setText("剩餘電量: "+power);
	     });
	}
	//display current position
	public static void DisplayCurrentPosition(Position currentposition) {
        SwingUtilities.invokeLater(() -> {
            CurrentPosition.setText("目前位置: " + currentposition.lat + " , " + currentposition.lng);
        });
	}
	//display total distance driven
	public static void DisplayAccumulativeDistance(double distance){
		SwingUtilities.invokeLater(() -> {
			DriveRange.setText("總行駛里程: "+distance);
	    });
	}
	
	//Initialize GoogleMap
	public static void initializeJavaFX() {
        Platform.runLater(new Runnable() {
        	public void run() {
            webView = new WebView();
            WebEngine webEngine = webView.getEngine();
            webEngine.load("https://www.google.com/maps");
            Scene scene = new Scene(webView);
            UserGUI.Mappanel.setScene(scene);
        	}
        });
    }
	public static void loadMap(double latitude, double longitude) {
        Platform.runLater(new Runnable(){
        	public void run() {
            WebView webView = new WebView();
            WebEngine webEngine = webView.getEngine();
            webEngine.load("https://www.google.com/maps?q=" + latitude + "," + longitude);
            Scene scene = new Scene(webView);
             UserGUI.Mappanel.setScene(scene);
        	}
        });
    }
	/**
	 * Launch the application.
	 */
	public static void creategui() {
		UserGUI frame = new UserGUI(new RentScooterService());
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					creategui();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public UserGUI(RentScooterService service) {
		this.service=service;
		setTitle("EV Renting Platform");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Title = new JLabel();
		Title.setFont(new Font("微軟正黑體", Font.PLAIN, 40));
		Title.setLabelFor(Title);
		Title.setBounds(604, 0, 695, 66);
		Title.setText("Welcome, "+service.getUserOperator().getAccount());
		contentPane.add(Title);
		
		mainpane.setBounds(0, 57, 1540, 788);
		contentPane.add(mainpane);
		
		accountpane.setBounds(540, 346, 782, 434);
		accountpane.setLayout(null);
		mainpane.add(accountpane,"profile");
		
		JLabel Account = new JLabel("Account:");
		Account.setFont(new Font("標楷體", Font.PLAIN, 30));
		Account.setBounds(457, 186, 146, 62);
		accountpane.add(Account);
		
		JLabel Password = new JLabel("Password:");
		Password.setFont(new Font("標楷體", Font.PLAIN, 30));
		Password.setBounds(445, 276, 146, 56);
		accountpane.add(Password);
		
		account = new JTextField();
		account.setFont(new Font("標楷體", Font.PLAIN, 20));
		account.setBounds(677, 199, 442, 40);
		accountpane.add(account);
		account.setColumns(10);
		account.setText(service.getUserOperator().getAccount());
		
		password = new JTextField();
		password.setFont(new Font("\u6A19\u6977\u9AD4", password.getFont().getStyle(), 20));
		password.setBounds(677, 285, 442, 42);
		accountpane.add(password);
		password.setText(service.getUserOperator().getPassword());
		
		JButton renew = new JButton("更新");
		renew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				service.getUserOperator().setAccount(account.getText());
			}
		});
		renew.setFont(new Font("微軟正黑體", Font.PLAIN, 25));
		renew.setBounds(1169, 199, 129, 40);
		accountpane.add(renew);
		
		phone = new JTextField();
		phone.setFont(new Font("標楷體", Font.PLAIN, 20));
		phone.setColumns(10);
		phone.setBounds(677, 382, 442, 40);
		accountpane.add(phone);
		phone.setText(service.getUserOperator().getCellphone());
		
		JLabel Phone = new JLabel("手機號碼:");
		Phone.setFont(new Font("標楷體", Font.PLAIN, 30));
		Phone.setBounds(445, 372, 146, 56);
		accountpane.add(Phone);
		
		JLabel TItle_1 = new JLabel("個人資料檢視/修改");
		TItle_1.setFont(new Font("標楷體", Font.PLAIN, 40));
		TItle_1.setBounds(629, 72, 393, 62);
		accountpane.add(TItle_1);
		
		JLabel Pay = new JLabel("付款資料:");
		Pay.setFont(new Font("標楷體", Font.PLAIN, 30));
		Pay.setBounds(445, 628, 146, 56);
		accountpane.add(Pay);
		
		name = new JTextField();
		name.setFont(new Font("標楷體", Font.PLAIN, 20));
		name.setColumns(10);
		name.setBounds(677, 553, 442, 40);
		accountpane.add(name);
		name.setText(service.getUserOperator().getUserName());
		
		email = new JTextField();
		email.setFont(new Font("標楷體", Font.PLAIN, 20));
		email.setColumns(10);
		email.setBounds(677, 462, 442, 40);
		accountpane.add(email);
		email.setText(service.getUserOperator().getEmail());
		
		JLabel Email = new JLabel("email:");
		Email.setFont(new Font("標楷體", Font.PLAIN, 30));
		Email.setBounds(491, 452, 100, 56);
		accountpane.add(Email);
		
		payment = new JTextField();
		payment.setFont(new Font("標楷體", Font.PLAIN, 20));
		payment.setColumns(10);
		payment.setBounds(677, 638, 442, 40);
		accountpane.add(payment);
		payment.setText(service.getUserOperator().getCreditCard());
		
		JLabel Name = new JLabel("姓名:");
		Name.setFont(new Font("標楷體", Font.PLAIN, 30));
		Name.setBounds(504, 543, 92, 56);
		accountpane.add(Name);
		
		JButton renew_1 = new JButton("更新");
		renew_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				service.getUserOperator().setPassword(password.getText());
			}
		});
		renew_1.setFont(new Font("微軟正黑體", Font.PLAIN, 25));
		renew_1.setBounds(1169, 285, 129, 40);
		accountpane.add(renew_1);
		
		JButton renew_2 = new JButton("更新");
		renew_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				service.getUserOperator().setCellphone(phone.getText());
			}
		});
		renew_2.setFont(new Font("微軟正黑體", Font.PLAIN, 25));
		renew_2.setBounds(1169, 382, 129, 40);
		accountpane.add(renew_2);
		
		JButton renew_3 = new JButton("更新");
		renew_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				service.getUserOperator().setEmail(email.getText());
			}
		});
		renew_3.setFont(new Font("微軟正黑體", Font.PLAIN, 25));
		renew_3.setBounds(1169, 462, 129, 40);
		accountpane.add(renew_3);
		
		JButton renew_4 = new JButton("更新");
		renew_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				service.getUserOperator().setUserName(name.getText());
			}
		});
		renew_4.setFont(new Font("微軟正黑體", Font.PLAIN, 25));
		renew_4.setBounds(1169, 553, 129, 40);
		accountpane.add(renew_4);
		
		JButton renew_5 = new JButton("更新");
		renew_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				service.getUserOperator().setCreditCard(payment.getText());
			}
		});
		renew_5.setFont(new Font("微軟正黑體", Font.PLAIN, 25));
		renew_5.setBounds(1169, 638, 129, 40);
		accountpane.add(renew_5);
		
		JLabel Coupon = new JLabel("優惠券:");
		Coupon.setFont(new Font("標楷體", Font.PLAIN, 30));
		Coupon.setBounds(475, 704, 146, 56);
		accountpane.add(Coupon);
		
		couponnum = new JTextField();
		couponnum.setText((String) null);
		couponnum.setFont(new Font("標楷體", Font.PLAIN, 20));
		couponnum.setColumns(10);
		couponnum.setBounds(677, 714, 442, 40);
		couponnum.setEditable(false);
		couponnum.setText(service.getUserOperator().getCoupon()+" 張");
		accountpane.add(couponnum);
		
		mainpane.add(rentpane,"rentcar");
		rentpane.setLayout(null);
		
		JLabel Range = new JLabel("範圍(km):");
		Range.setBounds(474, 305, 146, 62);
		Range.setFont(new Font("標楷體", Font.PLAIN, 30));
		rentpane.add(Range);
		
		JButton search = new JButton("查詢");
		search.setBounds(703, 456, 129, 62);
		search.setFont(new Font("標楷體", Font.PLAIN, 25));
		rentpane.add(search);
		 // Add ActionListener to the search button
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Perform the search action
            	scooterselectionFrame = new ScooterSelectionGUI(service,new ArrayList<>(),UserGUI.this,Double.parseDouble(range.getText()));
            	scooterselectionFrame.setVisible(true);
            }
        });
		
		range = new JTextField();
		range.setFont(new Font("標楷體", Font.PLAIN, 20));
		range.setColumns(10);
		range.setBounds(703, 318, 442, 40);
		rentpane.add(range);
		
		JLabel currentposition = new JLabel("目前位置:");
		currentposition.setFont(new Font("標楷體", Font.PLAIN, 30));
		currentposition.setBounds(474, 186, 146, 62);
		rentpane.add(currentposition);
		
		JLabel currentPosition = new JLabel();
		currentPosition.setFont(new Font("微軟正黑體", Font.PLAIN, 22));
		currentPosition.setBounds(703, 194, 442, 40);
		rentpane.add(currentPosition);
		Position current=service.showUserPosition(service.getUserOperator());
		currentPosition.setText(Double.toString(current.lat)+" , "+Double.toString(current.lng));
		
		ridepane.setLayout(null);
		mainpane.add(ridepane, "ride");
		
		JButton Charge = new JButton("尋找充電站");
		Charge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 ChargeStationFrame=new ChargeStationGUI(service,UserGUI.this);
				 ChargeStationFrame.setVisible(true);
			}
		});
		Charge.setFont(new Font("標楷體", Font.PLAIN, 25));
		Charge.setBounds(518, 602, 212, 62);
		ridepane.add(Charge);
		
		JLabel Scooter = new JLabel("租借車輛:");
		Scooter.setFont(new Font("標楷體", Font.PLAIN, 30));
		Scooter.setBounds(493, 11, 146, 62);
		ridepane.add(Scooter);
		
		JButton back = new JButton("歸還車輛");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(service.endRidingScooter(service.getUserOperator())) {
				JOptionPane.showMessageDialog(ridepane, "歸還成功");
				userfinalposition=service.displayPositionAndDistance(service.getUserOperator());
				location.setText(Double.toString(userfinalposition[0])+" , "+Double.toString(userfinalposition[1]));
				totalrange.setText(Double.toString(userfinalposition[2]));
				fee.setText(Double.toString(service.displayFee(service.getUserOperator())));
				cardlayout.show(mainpane, "checkout");
				currentcard="checkout";
				//check whether user has usaable coupon
				coupon=service.findQualifiedCoupon(service.getUserOperator());
				if(coupon==null) {
					hasCoupon.setVisible(false);
					withcoupon.setVisible(false);
				}
			}
			}
		});
		back.setFont(new Font("標楷體", Font.PLAIN, 25));
		back.setBounds(822, 602, 212, 62);
		ridepane.add(back);
		
		num.setFont(new Font("標楷體", Font.PLAIN, 30));
		num.setBounds(666, 11, 335, 52);
		ridepane.add(num);
		
		JButton stop = new JButton("暫停騎車");
		stop.setFont(new Font("標楷體", Font.PLAIN, 25));
		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				service.stopRidingScooter(service.getUserOperator());
				riding.setVisible(false);
				pauseriding.setVisible(true);
			}
		});
		stop.setBounds(124, 559, 212, 62);
		ridepane.add(stop);
		
		JButton resume = new JButton("開始騎車");
		resume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				service.rideScooter(service.getUserOperator());
				riding.setVisible(true);
				pauseriding.setVisible(false);
			}
		});
		resume.setFont(new Font("標楷體", Font.PLAIN, 25));
		resume.setBounds(124, 451, 212, 64);
		ridepane.add(resume);
		
		riding = new JLabel("New label");
		riding.setIcon(new ImageIcon(UserGUI.class.getResource("/evRentingPlatformGUI/ride.gif")));
		riding.setBounds(4, 84, 436, 284);
		riding.setVisible(false);
		ridepane.add(riding);
		
		pauseriding = new JLabel("New label");
		pauseriding.setIcon(new ImageIcon(UserGUI.class.getResource("/evRentingPlatformGUI/pause.jpg")));
		pauseriding.setBounds(4, 84, 436, 284);
		ridepane.add(pauseriding);
		
		CurrentPosition = new JLabel("");
		CurrentPosition.setFont(new Font("標楷體", Font.PLAIN, 20));
		CurrentPosition.setBounds(1097, 108, 410, 44);
		ridepane.add(CurrentPosition);
		
		PowerRemain = new JLabel("");
		PowerRemain.setFont(new Font("標楷體", Font.PLAIN, 25));
		PowerRemain.setBounds(1157, 244, 305, 62);
		ridepane.add(PowerRemain);
		
		DriveRange = new JLabel("");
		DriveRange.setFont(new Font("標楷體", Font.PLAIN, 25));
		DriveRange.setBounds(1157, 365, 303, 52);
		ridepane.add(DriveRange);
		
		checkoutpane.setLayout(null);
		mainpane.add(checkoutpane, "checkout");
		
		JLabel Location = new JLabel("目前位置:");
		Location.setFont(new Font("標楷體", Font.PLAIN, 30));
		Location.setBounds(492, 186, 146, 62);
		checkoutpane.add(Location);
		
		location = new JTextField();
		location.setFont(new Font("標楷體", Font.PLAIN, 20));
		location.setColumns(10);
		location.setBounds(712, 199, 442, 40);
		location.setEditable(false);
		checkoutpane.add(location);
		
		JButton pay = new JButton("確認並付款");
		//pay and return to login gui
		pay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(withcoupon.isSelected()==true) {
					service.payFeeAndReturnScooter(service.getUserOperator(),coupon);
					JOptionPane.showMessageDialog(ridepane, "付款完成，感謝您的使用!");
					LoginGUI login=new LoginGUI(service);
					login.setVisible(true);
					UserGUI.this.dispose();
				}
				else {
					service.payFeeAndReturnScooter(service.getUserOperator());
					JOptionPane.showMessageDialog(ridepane, "付款完成，感謝您的使用!");
					LoginGUI login=new LoginGUI(service);
					login.setVisible(true);
					UserGUI.this.dispose();
			}
			}
		});
		pay.setFont(new Font("微軟正黑體", Font.PLAIN, 25));
		pay.setBounds(672, 630, 188, 62);
		checkoutpane.add(pay);
		
		totalrange = new JTextField();
		totalrange.setFont(new Font("標楷體", Font.PLAIN, 20));
		totalrange.setColumns(10);
		totalrange.setBounds(712, 311, 442, 40);
		totalrange.setEditable(false);
		checkoutpane.add(totalrange);
		
		JLabel Totalrange = new JLabel("總駕駛里程:");
		Totalrange.setFont(new Font("標楷體", Font.PLAIN, 30));
		Totalrange.setBounds(463, 301, 175, 56);
		checkoutpane.add(Totalrange);
		
		JLabel Toltal = new JLabel("結算/計價");
		Toltal.setFont(new Font("標楷體", Font.PLAIN, 40));
		Toltal.setBounds(672, 68, 217, 62);
		checkoutpane.add(Toltal);
		
		JLabel Fee = new JLabel("費用:");
		Fee.setFont(new Font("標楷體", Font.PLAIN, 30));
		Fee.setBounds(553, 453, 85, 56);
		checkoutpane.add(Fee);
		
		hasCoupon = new JLabel("有九折優惠券!");
		hasCoupon.setForeground(new Color(255, 0, 0));
		hasCoupon.setFont(new Font("標楷體", Font.PLAIN, 25));
		hasCoupon.setBounds(583, 542, 175, 62);
		hasCoupon.setVisible(true);
		checkoutpane.add(hasCoupon);
		
		fee = new JTextField();
		fee.setFont(new Font("標楷體", Font.PLAIN, 20));
		fee.setColumns(10);
		fee.setBounds(712, 463, 442, 40);
		fee.setEditable(false);
		checkoutpane.add(fee);
		
		withcoupon = new JCheckBox("使用優惠券");
		withcoupon.setVisible(true);
		withcoupon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		withcoupon.setFont(new Font("微軟正黑體", Font.PLAIN, 25));
		withcoupon.setBounds(904, 540, 157, 56);
		checkoutpane.add(withcoupon);
		
	    JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 18));
		menuBar.setBounds(0, 0, 145, 73);
		contentPane.add(menuBar);
		
		JMenu menu = new JMenu("選單");
		menuBar.add(menu);
		menu.setBackground(new Color(128, 255, 128));
		menu.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 30));
		
		JMenu userdata = new JMenu("會員專區");
		userdata.setFont(new Font("微軟正黑體", Font.PLAIN, 20));
		menu.add(userdata);
		
		JMenuItem profile = new JMenuItem("個人資訊");
		profile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(mainpane,"profile");
				currentcard="profile";
			}
		});
		profile.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		userdata.add(profile);
		
		JMenuItem renthistory = new JMenuItem("租借紀錄");
		renthistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RentHistoryGUI frame2 = new RentHistoryGUI(service);
				frame2.setVisible(true);
			}
		});
		renthistory.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		userdata.add(renthistory);
		
		JMenuItem logout = new JMenuItem("登出");
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(currentcard.equals("ride")) {
					JOptionPane.showMessageDialog(ridepane, "請專心騎車");
				}
				else if(currentcard.equals("checkout")) {
					JOptionPane.showMessageDialog(ridepane, "請先完成結帳");;
				}
				else {
					if(service.userLogOut()) {
						LoginGUI login=new LoginGUI(service);
	        			login.setVisible(true);
	        			UserGUI.this.dispose();
					}
				}
			}
		});
		logout.setHorizontalAlignment(SwingConstants.CENTER);
		logout.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		userdata.add(logout);
		
		JMenuItem staff = new JMenuItem("租借車輛");
		staff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(mainpane,"rentcar");
				currentcard="rentcar";
			}
		});
		staff.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		menu.add(staff);
	}
}
