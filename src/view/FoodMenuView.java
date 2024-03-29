package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

import controller.CartController;
import controller.FoodController;
import core.view.View;
import model.FoodModel;
/*
 * Pada frame ini ditampilkan menu makanan yang ada pada restoran, selain itu frame ini juga berfungsi sebagai menu untuk "add to cart".
 * Pada frame ini juga, User dapat memilih makanan untuk ditambahkan ke dalam cart.
 */
public class FoodMenuView extends View{

	JPanel mainPanel,northPanel,centerPanel,southPanel,addFoodDataToCartPanel;
	JPanel nameLblPan,nameTfPan,priceLblPan,priceTfPan,quantityLblPan,quantitySpnPan;
	JLabel foodMenuLbl,nameLbl,priceLbl,quantityLbl;
	JTextField nameTf,priceTf;
	JSpinner quantitySpn;
	JButton addToCartBtn;
	JTable foodTableData;
	DefaultTableModel foodDtm;
	JScrollPane foodTableScrollPane;
	private Integer foodId;
	
	public FoodMenuView() {
		super("Food Menu");
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
		addFoodDataToCartPanel = new JPanel(new GridLayout(3,2));
		
		foodMenuLbl = new JLabel("Food Menu");
		
		nameLblPan = new JPanel();
		nameTfPan= new JPanel();
		priceLblPan= new JPanel();
		priceTfPan= new JPanel();
		quantityLblPan= new JPanel();
		quantitySpnPan= new JPanel();
		
		nameLbl = new JLabel("Food Name");
		nameLbl.setPreferredSize(new Dimension(70,30));	
		nameTf = new JTextField("");	
		nameTf.setPreferredSize(new Dimension(100,30));
		nameTf.setBackground(Color.white);
		priceLbl = new JLabel("Price");
		priceLbl.setPreferredSize(new Dimension(70,30));
		priceTf = new JTextField("");
		priceTf.setPreferredSize(new Dimension(100,30));
		priceTf.setBackground(Color.white);
		nameTf.setEditable(false); 
		priceTf.setEditable(false);
		quantityLbl = new JLabel("Quantity");
		quantityLbl.setPreferredSize(new Dimension(70,30));
		quantitySpn = new JSpinner();
		quantitySpn.setPreferredSize(new Dimension(100,30));
		
		addToCartBtn = new JButton("Add To Cart");
	}

	@Override
	public void addComponent() {
		northPanel.add(foodMenuLbl);
		
		viewMenu();
		centerPanel.add(foodTableScrollPane);
		centerPanel.add(addFoodDataToCartPanel);
		
		nameLblPan.add(nameLbl);
		nameTfPan.add(nameTf);
		priceLblPan.add(priceLbl);
		priceTfPan.add(priceTf);
		quantityLblPan.add(quantityLbl);
		quantitySpnPan.add(quantitySpn);
		
		addFoodDataToCartPanel.add(nameLblPan);
		addFoodDataToCartPanel.add(nameTfPan);
		addFoodDataToCartPanel.add(priceLblPan);
		addFoodDataToCartPanel.add(priceTfPan);
		addFoodDataToCartPanel.add(quantityLblPan);
		addFoodDataToCartPanel.add(quantitySpnPan);
		
		southPanel.add(addToCartBtn);
		
		mainPanel.add(northPanel,BorderLayout.NORTH);
		mainPanel.add(centerPanel,BorderLayout.CENTER);
		mainPanel.add(southPanel,BorderLayout.SOUTH);
		
		add(mainPanel);
	}

	private void viewMenu() {
		Vector<String> header = new Vector<>();
		header.add("Food Id");
		header.add("Name");
		header.add("Price");
		header.add("Description");
		header.add("Status");
		foodDtm = new DefaultTableModel(header,0) {	
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}	
		};
		
		Vector<FoodModel> foods = FoodController.getInstance().viewAll();
		
		for (FoodModel food : foods) {
			Vector<Object> row = new Vector<>();
			row.add(food.getFoodId());
			row.add(food.getName());
			row.add(food.getPrice());
			row.add(food.getDescription());
			row.add(food.getStatus());
			foodDtm.addRow(row);
		}
		
		foodTableData = new JTable(foodDtm);
		foodTableScrollPane = new JScrollPane(foodTableData);
	}
	
	@Override
	public void addListener() {
	foodTableData.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				foodId=Integer.parseInt(foodTableData.getValueAt(foodTableData.getSelectedRow(),0).toString());
				nameTf.setText(foodTableData.getValueAt(foodTableData.getSelectedRow(),1).toString());
				priceTf.setText(foodTableData.getValueAt(foodTableData.getSelectedRow(),2).toString());
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
		
		addToCartBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer userId=MainFormView.userID; 
				Integer qty = (Integer) quantitySpn.getValue();
				if(nameTf.getText().equals("") || priceTf.getText().equals("")|| qty==0) {
					JOptionPane.showMessageDialog(FoodMenuView.this, "Please choose the food and determine the food quantity","Error Message", JOptionPane.ERROR_MESSAGE);
				}else {
					boolean addToCart = CartController.getInstance().addToCart(userId,foodId,qty);
					if(addToCart) {
						JOptionPane.showMessageDialog(FoodMenuView.this, "Successfully Add Food to Cart","Success", JOptionPane.PLAIN_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(FoodMenuView.this, CartController.getInstance().getErrorMsg(),"Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}

}
