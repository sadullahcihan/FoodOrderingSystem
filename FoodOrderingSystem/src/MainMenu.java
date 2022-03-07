import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class MainMenu {
	private JFrame frame;
	private JTable table;
	private JTable table2;
	private DefaultTableModel dtm;
	private DefaultTableModel dtm2;
	private JTextField textField;
	private JButton btnAddToCart;
	private JButton btnDischarge;
	private JButton btnBuy;
	public static int foodID = -1;
	public static int RestaurantID = -1;
	public ArrayList<Restaurant> restoList;
	public static int oldRestoID = 0;

	public MainMenu() throws IOException {
		restoList = new ArrayList<Restaurant>();
		frame = new JFrame("Main Menu ");
		frame.setBounds(100, 100, 1250, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		JLabel lblResto = new JLabel("Restaurant");
		lblResto.setBounds(1, 11, 81, 14);
		frame.getContentPane().add(lblResto);
		JLabel lblFoodOrdered = new JLabel("CART");
		lblFoodOrdered.setBounds(850, 11, 81, 14);
		frame.getContentPane().add(lblFoodOrdered);
		// resto table
		table = new JTable();
		// backButton = new JButton();
		// orderButton = new JButton();
		dtm = new DefaultTableModel(0, 0);
		final String header1[] = new String[] { "Restaurant ID", "Restaurant Name", "Food ID", "Food Name",
				"Kitchen Type", "Price" };
		dtm.setColumnIdentifiers(header1);
		dtm.addRow(header1);
		table = new JTable();
		table.setModel(dtm);
		table.setBounds(1, 31, 1, 1); // int x, int y, int width, int height
		table.setSize(500, 600); // width,height
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(5);
		table.getColumnModel().getColumn(3).setPreferredWidth(40);
		table.getColumnModel().getColumn(4).setPreferredWidth(30);
		table.getColumnModel().getColumn(5).setPreferredWidth(30);
		table.setShowGrid(true); // remove cell boarder
		frame.getContentPane().add(table);

		// test
		Restaurant r0 = TestFoodOrderingSystem.createRestaurant(1, "Battalbey Çiğköfte", 35,
				"Bucakoop Mah. no:45 İzmir", "+2322541102");
		Restaurant r1 = TestFoodOrderingSystem.createRestaurant(2, "Kervan Kebap", 35, "Bucakoop Mah. no:45 İzmir",
				"+2322541102");
		restoList.add(r0);
		restoList.add(r1);
		int restoID = 0;
		int restoSize = restoList.size();
		String restoName = null;
		int foodId = 0;
		String foodName = null;
		String kitchenType = null;
		int price = 0;
		int menuSize = 0;
		for (int i = 0; i < restoSize; i++) {
			restoID = restoList.get(i).getId();
			restoName = restoList.get(i).getName();
			kitchenType = restoList.get(i).getKitchenType();
			menuSize = restoList.get(i).getMenu().size();
			for (int j = 0; j < menuSize; j++) {
				foodId = restoList.get(i).getMenu().get(j).getFoodID();
				foodName = restoList.get(i).getMenu().get(j).getName();
				price = restoList.get(i).getMenu().get(j).getUnitPrice();
				dtm.addRow(new Object[] { restoID, restoName, foodId, foodName, kitchenType, price });
			}
		}

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int columnIndex = table.getSelectedColumn();
				System.out.println("Double click on jable");
				if (columnIndex == 0 || columnIndex == 1 || columnIndex == 2 || columnIndex == 3 || columnIndex == 4) {
					JOptionPane.showMessageDialog(frame, "Editing this Field may cause error in the data.",
							"Error Edit Not Permitted For This Field", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		// to take food id from restaurant table
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int row = table.convertRowIndexToModel(table.getSelectedRow());
				int col = table.getColumnModel().getColumnIndex("Food ID");
				MainMenu.setFoodID((Integer) table.getModel().getValueAt(row, col));
				JOptionPane.showMessageDialog(frame, "Food ID: " + MainMenu.foodID);
			}
		});
		// to take restaurant id from restaurant table
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int row = table.convertRowIndexToModel(table.getSelectedRow());
				int col = table.getColumnModel().getColumnIndex("Restaurant ID");
				MainMenu.setRestaurantID((Integer) table.getModel().getValueAt(row, col));
				//JOptionPane.showMessageDialog(frame, "Restaurant ID: " + MainMenu.RestaurantID);
			}
		});
		// add button
		btnAddToCart = new JButton("ADD");
		btnAddToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (MainMenu.oldRestoID != MainMenu.RestaurantID) {
					TestFoodOrderingSystem.dischargeCart();
					textField.setText("0");
					MainMenu.oldRestoID = MainMenu.RestaurantID;
				}
				String oldTotal = textField.getText();
				TestFoodOrderingSystem
						.addToCart(new Cart(TestFoodOrderingSystem.basket.size() + 1, MainMenu.foodID, 1));
				dtm2 = new DefaultTableModel(0, 0);
				final String header2[] = new String[] { "Cart ID", "Food Name", "Quantity", "Price" };
				dtm2.setColumnIdentifiers(header2);
				dtm2.addRow(header2);
				table2 = new JTable();
				table2.setModel(dtm2);
				table2.setBounds(799, 31, 1, 1); // int x, int y, int width, int height
				table2.setSize(300, 600); // width,height
				table2.getColumnModel().getColumn(0).setPreferredWidth(30);
				table2.getColumnModel().getColumn(1).setPreferredWidth(80);
				table2.getColumnModel().getColumn(2).setPreferredWidth(30);
				table2.getColumnModel().getColumn(3).setPreferredWidth(30);
				table2.setShowGrid(true); // remove cell boarder
				frame.getContentPane().add(table2);
				JLabel lblTotal2 = new JLabel("Total  : ");
				lblTotal2.setBounds(799, 635, 46, 14);
				frame.getContentPane().add(lblTotal2);
				textField = new JTextField();
				textField.setBounds(850, 635, 86, 20);
				textField.setText(oldTotal);
				frame.getContentPane().add(textField);
				textField.setColumns(10);
				// test
				int basketSize = TestFoodOrderingSystem.basket.size();
				String foodNameCart = null;
				int quantity = 1;
				int priceCart = 0;
				int menuSizeCart;
				if (MainMenu.RestaurantID != -1) {
					menuSizeCart = restoList.get(MainMenu.RestaurantID).getMenu().size();
					int foodIDCart = 0;
					int cartID = 0;
					System.out.println(textField.getText());
					for (int i = 0; i < basketSize; i++) {
						foodIDCart = TestFoodOrderingSystem.basket.get(i).getFoodID();
						cartID = TestFoodOrderingSystem.basket.get(i).getId();
						for (int j = 0; j < menuSizeCart; j++) {
							if (foodIDCart == restoList.get(MainMenu.RestaurantID).getMenu().get(j).getFoodID()) {
								foodNameCart = restoList.get(MainMenu.RestaurantID).getMenu().get(j).getName();
								priceCart = restoList.get(MainMenu.RestaurantID).getMenu().get(j).getUnitPrice();
								dtm2.addRow(new Object[] { cartID, foodNameCart, quantity, priceCart });
								textField.setText(Integer.toString(Integer.parseInt(oldTotal) + priceCart));
								frame.getContentPane().add(textField);
								break;
							}
						}
					}
				}
			}
		});
		btnAddToCart.setBounds(799, 670, 89, 23);
		frame.getContentPane().add(btnAddToCart);

		// cart table
		// backButton = new JButton();
		// orderButton = new JButton();
		dtm2 = new DefaultTableModel(0, 0);
		final String header2[] = new String[] { "Cart ID", "Food Name", "Quantity", "Price" };
		dtm2.setColumnIdentifiers(header2);
		dtm2.addRow(header2);
		table2 = new JTable();
		table2.setModel(dtm2);
		table2.setBounds(799, 31, 1, 1); // int x, int y, int width, int height
		table2.setSize(300, 600); // width,height
		table2.getColumnModel().getColumn(0).setPreferredWidth(30);
		table2.getColumnModel().getColumn(1).setPreferredWidth(80);
		table2.getColumnModel().getColumn(2).setPreferredWidth(30);
		table2.getColumnModel().getColumn(3).setPreferredWidth(30);
		table2.setShowGrid(true);
		frame.getContentPane().add(table2);
		JLabel lblTotal2 = new JLabel("Total  : ");
		lblTotal2.setBounds(799, 635, 46, 14);
		frame.getContentPane().add(lblTotal2);
		textField = new JTextField();
		textField.setBounds(850, 635, 86, 20);
		textField.setText("0");
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		// discarge button
		btnDischarge = new JButton("Discharge");
		btnDischarge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TestFoodOrderingSystem.dischargeCart();
				dtm2 = new DefaultTableModel(0, 0);
				final String header2[] = new String[] { "Cart ID", "Food Name", "Quantity", "Price" };
				dtm2.setColumnIdentifiers(header2);
				dtm2.addRow(header2);
				table2 = new JTable();
				table2.setModel(dtm2);
				table2.setBounds(799, 31, 1, 1); // int x, int y, int width, int height
				table2.setSize(300, 600); // width,height
				table2.getColumnModel().getColumn(0).setPreferredWidth(30);
				table2.getColumnModel().getColumn(1).setPreferredWidth(80);
				table2.getColumnModel().getColumn(2).setPreferredWidth(30);
				table2.getColumnModel().getColumn(3).setPreferredWidth(30);
				table2.setShowGrid(false); // remove cell boarder
				frame.getContentPane().add(table2);
				JLabel lblTotal2 = new JLabel("Total  : ");
				lblTotal2.setBounds(799, 635, 46, 14);
				frame.getContentPane().add(lblTotal2);
				textField = new JTextField();
				textField.setBounds(850, 635, 86, 20);
				textField.setText("0");
				frame.getContentPane().add(textField);
				textField.setColumns(10);
				//empty card
				dtm2.addRow(new Object[] { "","","","" });
				
			}
		});
		btnDischarge.setBounds(935, 670, 150, 23);
		frame.getContentPane().add(btnDischarge);
		// buy button
		btnBuy = new JButton("BUY");
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (TestFoodOrderingSystem.basket.size() != 0) {
					PaymentFrame p = new PaymentFrame();
					p.getFrame().setVisible(true);
					frame.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(frame, "Basket is empty", "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnBuy.setBounds(945, 635, 150, 23);
		frame.getContentPane().add(btnBuy);
	}

	public static void setRestaurantID(int restoID) {
		MainMenu.RestaurantID = restoID - 1;
	}


	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public static void setFoodID(int foodID) {
		MainMenu.foodID = foodID;
	}
}
