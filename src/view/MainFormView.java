package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import controller.EmployeeController;
import controller.FoodController;
import controller.OrderController;
import controller.UserController;
import core.view.MainView;

public class MainFormView extends MainView{

	JMenuBar mainMenuBar;

	JMenu userMenu,transactionMenu,shopMenu, chefMenu, managerMenu, driverMenu;
	JSeparator menuSeparator;
    JMenuItem loginMI, registerMI, logoutMI,exitMI,orderMI,historyMI,foodMI,cartMI, chefAddFoodMI, chefFoodListMI, chefOrderListMI, manageEmployeeMI, financialMI, historyOrderMI, takenOrderMi,availableOrderMI,userinfoMI;

	JDesktopPane desktop = new JDesktopPane();

	RegistrationView registrationFrame;
	ManageEmployeeView manageEmployeeFrame;
	FoodMenuView foodMenuIF;
	FinancialSummaryView financialFrame;
	CartView cartIF;
	public static boolean loginState = false;
//	public static boolean logoutState = true;
	
	ChefAddFoodView chefAddFoodFrame;
	ChefFoodListView chefFoodListFrame;
	ChefOrderListView orderListChefFrame;
	HistoryOrderView historyFrame;
	TakenOrderView takenOrderFrame;
	AvailableOrderView availableOrderFrame;
	UserInformationView userInfoFrame;
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
		

		chefMenu = new JMenu("Chef Menu");
		chefAddFoodMI = new JMenuItem("Add Food");
		chefFoodListMI = new JMenuItem("Food List");
		chefOrderListMI = new JMenuItem("Order List");

		managerMenu = new JMenu("Manager");
		manageEmployeeMI = new JMenuItem("Manage Employee");
		financialMI = new JMenuItem("Financial Summary");
		
		driverMenu = new JMenu("Order For Driver");
		historyOrderMI = new JMenuItem("History Order");
		takenOrderMi = new JMenuItem("Taken Order List");
		availableOrderMI = new JMenuItem("Available Order");
		userinfoMI = new JMenuItem("User Info");
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
		

		mainMenuBar.add(chefMenu);
		chefMenu.add(chefAddFoodMI);
		chefMenu.add(chefFoodListMI);
		chefMenu.add(chefOrderListMI);
		
	
		mainMenuBar.add(managerMenu);
		managerMenu.add(manageEmployeeMI);
		managerMenu.add(financialMI);
		
		mainMenuBar.add(driverMenu);
		driverMenu.add(historyOrderMI);
		driverMenu.add(takenOrderMi);
		driverMenu.add(availableOrderMI);
		driverMenu.add(userinfoMI);
		
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
				foodMenuIF = FoodController.getInstance().viewMenu();
				desktop.removeAll();
				desktop.add(foodMenuIF);
			}
		});
		
		cartMI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				add(desktop,BorderLayout.CENTER);
				cartIF = new CartView();
				desktop.removeAll();
				desktop.add(cartIF);
			}
		});
		
		manageEmployeeMI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				add(desktop,BorderLayout.CENTER);
				EmployeeController.getInstance().viewManageEmployeeForm();
				desktop.removeAll();
				desktop.add(new ManageEmployeeView());
			}
		});
		
		chefAddFoodMI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				add(desktop,BorderLayout.CENTER);
				chefAddFoodFrame = new ChefAddFoodView();
				FoodController.getInstance().viewManageFoodForm(chefAddFoodFrame);
				desktop.removeAll();
				desktop.add(chefAddFoodFrame);
			}
		});
		
		chefFoodListMI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				add(desktop,BorderLayout.CENTER);
				chefFoodListFrame = new ChefFoodListView();
				FoodController.getInstance().viewManageFoodForm(chefFoodListFrame);
				desktop.removeAll();
				desktop.add(chefFoodListFrame);
			}
		});
		
		chefOrderListMI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				add(desktop,BorderLayout.CENTER);
				orderListChefFrame = new ChefOrderListView();
				OrderController.getInstance().viewOrderQueue(orderListChefFrame);
				desktop.removeAll();
				desktop.add(orderListChefFrame);
			}
		});
		
		historyOrderMI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				add(desktop,BorderLayout.CENTER);
				historyFrame = new HistoryOrderView(desktop,2,null);
				desktop.removeAll();
				desktop.add(historyFrame);
			}
		});
		
		takenOrderMi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				add(desktop,BorderLayout.CENTER);
				takenOrderFrame = new TakenOrderView(desktop, 5);
				desktop.removeAll();
				desktop.add(takenOrderFrame);
			}
		});
		
		availableOrderMI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				add(desktop,BorderLayout.CENTER);
				availableOrderFrame = new AvailableOrderView(desktop);
				desktop.removeAll();
				desktop.add(availableOrderFrame);
			}
		});
		
		userinfoMI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				add(desktop,BorderLayout.CENTER);
				userInfoFrame = new UserInformationView(desktop,1);
				desktop.removeAll();
				desktop.add(userInfoFrame);
			}
		});
		
		financialMI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				add(desktop,BorderLayout.CENTER);
				desktop.removeAll();
				desktop.add(OrderController.getInstance().viewProfit());
			}
		});
	}

}
