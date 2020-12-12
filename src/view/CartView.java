package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
//import javax.xml.bind.ParseConversionEvent;

import controller.CartController;
import controller.FoodController;
import controller.OrderController;
import controller.UserController;
import core.view.View;
import model.CartModel;
import model.FoodModel;
import model.OrderDetailModel;
import model.OrderModel;
import model.UserModel;

public class CartView extends View{

	JPanel mainPanel,northPanel,centerPanel,southPanel,modifyCartPanel;
	JLabel cartFoodIdLbl,cartFoodQtyLbl,cartTitleLbl;
	JTextField cartFoodIdTf,cartQtyTf;
	JButton removeFromCartBtn,orderBtn;
	JTable cartTableData;
	DefaultTableModel cartDtm;
	//private Integer userId = 0;
	// nanti constructor terima userId(customer)
	public CartView() {
		super("Cart");
		this.width=600;
		this.height=600; 
		
		super.showForm();
	}

	@Override
	public void init() {
		mainPanel= new JPanel(new BorderLayout());
		northPanel = new JPanel(); 
		centerPanel = new JPanel(new GridLayout(2,1));
		southPanel = new JPanel(); 
		modifyCartPanel = new JPanel(new GridLayout(1,2));
		
		cartTitleLbl = new JLabel("Cart");
		cartTableData = new JTable();
		
		cartFoodIdLbl=new JLabel("Food Id");
		cartFoodIdTf=new JTextField();
		cartFoodIdTf.setBackground(Color.white);
		cartFoodIdTf.setEditable(false); 
	
		removeFromCartBtn = new JButton("Remove");
		orderBtn = new JButton("Order");
	}

	@Override
	public void addComponent() {
		northPanel.add(cartTitleLbl);
		
		viewManageCartForm();
		centerPanel.add(new JScrollPane(cartTableData));
		centerPanel.add(modifyCartPanel);
		
		modifyCartPanel.add(cartFoodIdLbl);
		modifyCartPanel.add(cartFoodIdTf);
		
		southPanel.add(removeFromCartBtn);
		southPanel.add(orderBtn);
		
		mainPanel.add(northPanel,BorderLayout.NORTH);
		mainPanel.add(centerPanel,BorderLayout.CENTER);
		mainPanel.add(southPanel,BorderLayout.SOUTH);
		
		add(mainPanel);
	}

	private void viewManageCartForm() {
		Vector<String> header = new Vector<>();
		header.add("Food Id");
		header.add("Name");
		header.add("Quantity");
		header.add("Sub Price");
		cartDtm = new DefaultTableModel(header,0);
		
		Integer userId = 2;// nanti parameter diganti userId(customer) 
		Vector<CartModel> carts = CartController.getInstance().viewAll(userId);
		
		for (CartModel cart : carts) {
			Vector<Object> row = new Vector<>();
			row.add(cart.getFoodId());
			FoodModel food = FoodController.getInstance().getFood(cart.getFoodId());
			row.add(food.getName());
			row.add(cart.getQty());
			row.add(cart.getQty()*food.getPrice());
			cartDtm.addRow(row);
		}
		
		cartTableData.setModel(cartDtm);
	}
	
	@Override
	public void addListener() {
		
		cartTableData.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				cartFoodIdTf.setText(cartTableData.getValueAt(cartTableData.getSelectedRow(),0).toString());
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		removeFromCartBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer userId=2; // nanti disesuaikan sama userId(cusotmer);
				
				Integer foodId = Integer.parseInt(cartFoodIdTf.getText());
				int deleteConfirmation = JOptionPane.showConfirmDialog(CartView.this, "Are you sure to delete these food from your cart?","Confirmation", JOptionPane.WARNING_MESSAGE);
				
				if(deleteConfirmation == JOptionPane.YES_OPTION) {
					boolean removeFromCart = CartController.getInstance().removeFromCart(userId, foodId);
					if(removeFromCart) {
						JOptionPane.showMessageDialog(CartView.this, "Successfully Remove Food From Cart","Success", JOptionPane.PLAIN_MESSAGE);
						viewManageCartForm();
					}else {
						JOptionPane.showMessageDialog(CartView.this, CartController.getInstance().getErrorMsg(),"Error", JOptionPane.WARNING_MESSAGE);
					}
				}		
			} 
		});
		
		orderBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer userId=1; // nanti disesuaikan sama userId(cusotmer);
				UserModel user = UserController.getInstance().getOne(userId);
				Date date = java.sql.Date.valueOf(java.time.LocalDate.now());
				
				Vector<CartModel> carts = CartController.getInstance().viewAll(userId);
			
				int orderConfirmation = JOptionPane.showConfirmDialog(CartView.this, "Proceed Order? ","Confirmation", JOptionPane.WARNING_MESSAGE);
				
				if(orderConfirmation == JOptionPane.YES_OPTION) {
					boolean orderStatus = OrderController.getInstance().addOrder(user, date);
					if(orderStatus) {
						OrderModel order = OrderController.getInstance().getNewInsertedOrder(userId);    
						
						for (CartModel cart : carts) {
							OrderDetailModel orderDetail = new OrderDetailModel();
							orderDetail.addOrderDetail(order.getOrderId(), cart.getFoodId(), cart.getQty());
						}
						JOptionPane.showMessageDialog(CartView.this, "Order Successful","Success", JOptionPane.PLAIN_MESSAGE);
						CartController.getInstance().removeAll(user.getUserId());
						viewManageCartForm();
					}else {
						JOptionPane.showMessageDialog(CartView.this, OrderController.getInstance().getErrorMsg(),"Error", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		
	}

}
