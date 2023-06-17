package evRentingPlatform;

import java.awt.EventQueue;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Canvas;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.awt.event.ActionEvent;
import java.awt.*;

import javax.swing.*;

public class UserGUI extends JFrame {

	private JPanel contentPane;
	private JTextField account;
	private JPasswordField password;
	private final static String rentstr="rentcar";
	private final static String profilestr="profile";
	private final static String ridestr="ride";
	
	//Textfields
	private JTextField phonenum;
	private JTextField coupon;
	private JTextField name;
	private JTextField email;
	private JTextField payment;
	private JTextField range;
	private JTextField location;
	private JTextField totalrange;
	private JTextField couponnum;
	private JTextField fee;
	
	//for other classes usage
	JLabel num = new JLabel("");
	public static String currentcard;
	
	//panels for UserGUI
	CardLayout cardlayout = new CardLayout();
	JPanel mainpane = new JPanel(cardlayout);
	JPanel accountpane = new JPanel();
	JPanel rentpane = new JPanel();
	JPanel checkoutpane = new JPanel();
	JPanel ridepane = new JPanel();
	
	private ScooterSelectionGUI carSelectionFrame; // Add a reference to the CarSelectionGUI frame
	private ChargeStationGUI ChargeStationFrame;// Add a reference to the ChargeStationGUI frame
	
	//getters for cardlayout
	public CardLayout getCardLayout() {
		return cardlayout;
	}
	public JPanel getCardPanel() {
		return mainpane;
	}
	
	/**
	 * Launch the application.
	 */
	
	public static void creategui() {
		UserGUI frame = new UserGUI();
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
	public UserGUI() {
        
		setTitle("EV Renting Platform");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Title = new JLabel("Welcome");
		Title.setFont(new Font("微軟正黑體", Font.PLAIN, 45));
		Title.setLabelFor(Title);
		Title.setBounds(757, 10, 218, 66);
		contentPane.add(Title);
		
		mainpane.setBounds(0, 70, 1906, 933);
		contentPane.add(mainpane);

		accountpane.setBounds(540, 346, 782, 434);
		accountpane.setLayout(null);
		mainpane.add(accountpane,"profile");
		
		JLabel Account = new JLabel("Account:");
		Account.setFont(new Font("標楷體", Font.PLAIN, 30));
		Account.setBounds(542, 186, 146, 62);
		accountpane.add(Account);
		
		JLabel Password = new JLabel("Password:");
		Password.setFont(new Font("標楷體", Font.PLAIN, 30));
		Password.setBounds(526, 276, 146, 56);
		accountpane.add(Password);
		
		account = new JTextField();
		account.setFont(new Font("標楷體", Font.PLAIN, 20));
		account.setBounds(776, 199, 442, 40);
		accountpane.add(account);
		account.setColumns(10);
		
		password = new JPasswordField();
		password.setFont(new Font("\u6A19\u6977\u9AD4", password.getFont().getStyle(), 20));
		password.setBounds(776, 285, 442, 42);
		accountpane.add(password);
		
		JButton renew = new JButton("更新");
		renew.setFont(new Font("微軟正黑體", Font.PLAIN, 25));
		renew.setBounds(1273, 199, 129, 40);
		accountpane.add(renew);
		
		phonenum = new JTextField();
		phonenum.setFont(new Font("標楷體", Font.PLAIN, 20));
		phonenum.setColumns(10);
		phonenum.setBounds(776, 372, 442, 40);
		accountpane.add(phonenum);
		
		JLabel Phone = new JLabel("手機號碼:");
		Phone.setFont(new Font("標楷體", Font.PLAIN, 30));
		Phone.setBounds(526, 372, 146, 56);
		accountpane.add(Phone);
		
		JLabel TItle_1 = new JLabel("個人資料檢視/修改");
		TItle_1.setFont(new Font("標楷體", Font.PLAIN, 40));
		TItle_1.setBounds(729, 74, 393, 62);
		accountpane.add(TItle_1);
		
		JLabel Pay = new JLabel("付款資料:");
		Pay.setFont(new Font("標楷體", Font.PLAIN, 30));
		Pay.setBounds(526, 638, 146, 56);
		accountpane.add(Pay);
		
		JLabel Coupon = new JLabel("我的優惠券:");
		Coupon.setFont(new Font("標楷體", Font.PLAIN, 30));
		Coupon.setBounds(497, 780, 175, 62);
		accountpane.add(Coupon);
		
		coupon = new JTextField();
		coupon.setFont(new Font("標楷體", Font.PLAIN, 20));
		coupon.setColumns(10);
		coupon.setBounds(776, 793, 100, 40);
		coupon.setEditable(false);
		coupon.setText("1");
		accountpane.add(coupon);
		
		JLabel text = new JLabel("張");
		text.setFont(new Font("標楷體", Font.PLAIN, 30));
		text.setBounds(929, 780, 38, 62);
		accountpane.add(text);
		
		name = new JTextField();
		name.setFont(new Font("標楷體", Font.PLAIN, 20));
		name.setColumns(10);
		name.setBounds(776, 553, 442, 40);
		accountpane.add(name);
		
		email = new JTextField();
		email.setFont(new Font("標楷體", Font.PLAIN, 20));
		email.setColumns(10);
		email.setBounds(776, 462, 442, 40);
		accountpane.add(email);
		
		JLabel Email = new JLabel("email:");
		Email.setFont(new Font("標楷體", Font.PLAIN, 30));
		Email.setBounds(572, 452, 100, 56);
		accountpane.add(Email);
		
		payment = new JTextField();
		payment.setFont(new Font("標楷體", Font.PLAIN, 20));
		payment.setColumns(10);
		payment.setBounds(776, 648, 442, 40);
		accountpane.add(payment);
		
		JLabel Name = new JLabel("姓名:");
		Name.setFont(new Font("標楷體", Font.PLAIN, 30));
		Name.setBounds(584, 543, 92, 56);
		accountpane.add(Name);
		
		JButton renew_1 = new JButton("更新");
		renew_1.setFont(new Font("微軟正黑體", Font.PLAIN, 25));
		renew_1.setBounds(1273, 292, 129, 40);
		accountpane.add(renew_1);
		
		JButton renew_2 = new JButton("更新");
		renew_2.setFont(new Font("微軟正黑體", Font.PLAIN, 25));
		renew_2.setBounds(1273, 372, 129, 40);
		accountpane.add(renew_2);
		
		JButton renew_3 = new JButton("更新");
		renew_3.setFont(new Font("微軟正黑體", Font.PLAIN, 25));
		renew_3.setBounds(1273, 462, 129, 40);
		accountpane.add(renew_3);
		
		JButton renew_4 = new JButton("更新");
		renew_4.setFont(new Font("微軟正黑體", Font.PLAIN, 25));
		renew_4.setBounds(1273, 553, 129, 40);
		accountpane.add(renew_4);
		
		JButton renew_5 = new JButton("更新");
		renew_5.setFont(new Font("微軟正黑體", Font.PLAIN, 25));
		renew_5.setBounds(1273, 648, 129, 40);
		accountpane.add(renew_5);
		
		mainpane.add(rentpane,"rentcar");
		rentpane.setLayout(null);
		
		JLabel Range = new JLabel("範圍(km):");
		Range.setBounds(542, 29, 146, 62);
		Range.setFont(new Font("標楷體", Font.PLAIN, 30));
		rentpane.add(Range);
		
		JButton search = new JButton("查詢");
		search.setBounds(845, 121, 129, 62);
		search.setFont(new Font("標楷體", Font.PLAIN, 25));
		rentpane.add(search);
		 // Add ActionListener to the search button
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Perform the search action
            	carSelectionFrame = new ScooterSelectionGUI(new ArrayList<>(),UserGUI.this);
                carSelectionFrame.setVisible(true);
            }
        });
		
		range = new JTextField();
		range.setFont(new Font("標楷體", Font.PLAIN, 20));
		range.setColumns(10);
		range.setBounds(719, 40, 442, 40);
		rentpane.add(range);
		
		ridepane.setLayout(null);
		mainpane.add(ridepane, "ride");
		
		JButton Charge = new JButton("尋找充電站");
		Charge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 ChargeStationFrame=new ChargeStationGUI(UserGUI.this);
				 ChargeStationFrame.setVisible(true);
			}
		});
		Charge.setFont(new Font("標楷體", Font.PLAIN, 25));
		Charge.setBounds(558, 501, 212, 62);
		ridepane.add(Charge);
		
		JLabel Scooter = new JLabel("租借車輛:");
		Scooter.setFont(new Font("標楷體", Font.PLAIN, 30));
		Scooter.setBounds(598, 123, 146, 62);
		ridepane.add(Scooter);
		
		JButton back = new JButton("歸還車輛");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			cardlayout.show(mainpane, "checkout");
			currentcard="checkout";
			}
		});
		back.setFont(new Font("標楷體", Font.PLAIN, 25));
		back.setBounds(1091, 501, 212, 62);
		ridepane.add(back);
		
		num.setFont(new Font("標楷體", Font.PLAIN, 30));
		num.setBounds(788, 123, 335, 62);
		ridepane.add(num);
		
		JButton stop = new JButton("暫停騎車");
		stop.setFont(new Font("標楷體", Font.PLAIN, 25));
		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		stop.setBounds(1091, 309, 212, 62);
		ridepane.add(stop);
		
		JButton resume = new JButton("開始騎車");
		resume.setFont(new Font("標楷體", Font.PLAIN, 25));
		resume.setBounds(558, 308, 212, 64);
		ridepane.add(resume);
		
		checkoutpane.setLayout(null);
		mainpane.add(checkoutpane, "checkout");
		
		JLabel Location = new JLabel("目前位置:");
		Location.setFont(new Font("標楷體", Font.PLAIN, 30));
		Location.setBounds(526, 186, 146, 62);
		checkoutpane.add(Location);
		
		location = new JTextField();
		location.setFont(new Font("標楷體", Font.PLAIN, 20));
		location.setColumns(10);
		location.setBounds(778, 199, 442, 40);
		checkoutpane.add(location);
		
		JButton pay = new JButton("確認並付款");
		pay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(ridepane, "付款完成，感謝您的使用!");
				cardlayout.show(mainpane, "profile");
				currentcard=profilestr;
			}
		});
		pay.setFont(new Font("微軟正黑體", Font.PLAIN, 25));
		pay.setBounds(833, 722, 188, 62);
		checkoutpane.add(pay);
		
		totalrange = new JTextField();
		totalrange.setFont(new Font("標楷體", Font.PLAIN, 20));
		totalrange.setColumns(10);
		totalrange.setBounds(778, 311, 442, 40);
		checkoutpane.add(totalrange);
		
		JLabel Totalrange = new JLabel("總駕駛里程:");
		Totalrange.setFont(new Font("標楷體", Font.PLAIN, 30));
		Totalrange.setBounds(497, 301, 175, 56);
		checkoutpane.add(Totalrange);
		
		JLabel Toltal = new JLabel("結算/計價");
		Toltal.setFont(new Font("標楷體", Font.PLAIN, 40));
		Toltal.setBounds(833, 70, 217, 62);
		checkoutpane.add(Toltal);
		
		JLabel Fee = new JLabel("費用:");
		Fee.setFont(new Font("標楷體", Font.PLAIN, 30));
		Fee.setBounds(587, 453, 85, 56);
		checkoutpane.add(Fee);
		
		JLabel Coupon_1 = new JLabel("可用優惠券:");
		Coupon_1.setFont(new Font("標楷體", Font.PLAIN, 30));
		Coupon_1.setBounds(497, 572, 175, 62);
		checkoutpane.add(Coupon_1);
		
		couponnum = new JTextField();
		couponnum.setText("1");
		couponnum.setFont(new Font("標楷體", Font.PLAIN, 20));
		couponnum.setEditable(false);
		couponnum.setColumns(10);
		couponnum.setBounds(778, 585, 100, 40);
		checkoutpane.add(couponnum);
		
		JLabel Coupon_1_1 = new JLabel("張");
		Coupon_1_1.setFont(new Font("標楷體", Font.PLAIN, 30));
		Coupon_1_1.setBounds(972, 572, 38, 62);
		checkoutpane.add(Coupon_1_1);
		
		fee = new JTextField();
		fee.setFont(new Font("標楷體", Font.PLAIN, 20));
		fee.setColumns(10);
		fee.setBounds(778, 463, 442, 40);
		checkoutpane.add(fee);
		
		JButton usecoupon = new JButton("使用優惠券");
		usecoupon.setFont(new Font("微軟正黑體", Font.PLAIN, 25));
		usecoupon.setBounds(1120, 572, 188, 62);
		checkoutpane.add(usecoupon);
		
	    DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Number", "latitude","longitude", "Battery"}, 0);
		
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
				cardlayout.show(mainpane,profilestr);
				currentcard=profilestr;
			}
		});
		profile.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		userdata.add(profile);
		
		JMenuItem renthistory = new JMenuItem("租借紀錄");
		renthistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RentHistoryGUI frame2 = new RentHistoryGUI();
				frame2.setVisible(true);
			}
		});
		renthistory.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 15));
		userdata.add(renthistory);
		
		JMenuItem staff = new JMenuItem("租借車輛");
		staff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardlayout.show(mainpane,rentstr);
				currentcard=rentstr;
			}
		});
		staff.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 20));
		menu.add(staff);
		
	
	}
}
