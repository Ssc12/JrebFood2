package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import controller.FoodController;
import controller.UserController;
import core.view.MainView;

public class MainFormView extends MainView{

	JMenuBar mainMenuBar;
	JMenu userMenu,transactionMenu,shopMenu, managerMenu;
	JSeparator menuSeparator;
	JMenuItem registerMI, loginMI,logoutMI,exitMI,orderMI,historyMI,foodMI,cartMI, manageEmployeeMI, financialMI;
	JDesktopPane desktop = new JDesktopPane();

	RegistrationView registrationFrame;
	ManageEmployeeView manageEmployeeFrame;
	FoodMenuView foodMenu;
	public static boolean loginState = false;
//	public static boolean logoutState = true;
	
	public MainFormView() {
		super();
	}

	@Override
	public void initialize() {
		mainMenuBar = new JMenuBar();
		userMenu = new JMenu("User");
		registerMI = new JMenuItem("Create Account");
		loginMI = new JMenuItem("Login");
		logoutMI = new JMenuItem("Logout");
		exitMI = new JMenuItem("Exit");
		menuSeparator = new JSeparator();
		
		transactionMenu = new JMenu("Transaction");
		orderMI = new JMenuItem("Order");
		historyMI = new JMenuItem("History");
		
		shopMenu = new JMenu("Shop");
		foodMI = new JMenuItem("Food Menu");
		cartMI = new JMenuItem("Cart");
		
		managerMenu = new JMenu("Manager");
		manageEmployeeMI = new JMenuItem("Manage Employee");
		financialMI = new JMenuItem("Financial Summary");
	}

	@Override
	public void addComponent() {
		mainMenuBar.add(userMenu);
		userMenu.add(registerMI);
		userMenu.add(loginMI);
		userMenu.add(logoutMI);
		userMenu.add(menuSeparator);
		userMenu.add(exitMI);
		
		mainMenuBar.add(transactionMenu);
		transactionMenu.add(orderMI);
		transactionMenu.add(historyMI);
		
		mainMenuBar.add(shopMenu);
		shopMenu.add(foodMI);
		shopMenu.add(cartMI);
		
		mainMenuBar.add(managerMenu);
		managerMenu.add(manageEmployeeMI);
		managerMenu.add(financialMI);
		
		add(mainMenuBar,BorderLayout.NORTH);
	}

	@Override
	public void addListener() {
		// TODO Auto-generated method stub
//		loginMI.addActionListener();
//		logoutMI.addActionListener();
//		cartMI.addActionListener();
		
		registerMI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				add(desktop, BorderLayout.CENTER);
				registrationFrame = new RegistrationView();
				UserController.getInstance().view(registrationFrame);
				desktop.removeAll();
				desktop.add(registrationFrame);
			}
		});
		
		exitMI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		foodMI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				add(desktop,BorderLayout.CENTER);
				foodMenu = FoodController.getInstance().showUserFoodMenu();
				desktop.add(foodMenu);
			}
		});
		
		manageEmployeeMI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				add(desktop,BorderLayout.CENTER);
				manageEmployeeFrame = new ManageEmployeeView(desktop);
				UserController.getInstance().view(manageEmployeeFrame);
				desktop.removeAll();
				desktop.add(manageEmployeeFrame);
			}
		});
	}

}
