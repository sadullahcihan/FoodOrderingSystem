import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TestFoodOrderingSystem {
	static ArrayList<Cart> basket = new ArrayList<>();

	@SuppressWarnings("resource")
	public static Restaurant createRestaurant(int id, String name, int cityId, String address, String phone)
			throws IOException {
		ArrayList<Food> menu = new ArrayList<Food>();
		int foodID;
		String foodName;
		String kitchenType = null;
		int unitPrice;
		int unitInStock;
		// txt food database reading
		File fileDir = new File(name + ".txt");
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"));
		String str;
		in = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"));
		int i = 0;
		String[] s;
		while ((str = in.readLine()) != null) {
			if (i == 0) {
				kitchenType = str; // first row represents kitchen type
				i++;
				continue;
			}
			s = str.split(",");
			foodID = Integer.parseInt(s[1]);
			foodName = s[2];
			unitPrice = Integer.parseInt(s[3]);
			unitInStock = Integer.parseInt(s[4]);
			menu.add(new Food(foodID, foodName, kitchenType, unitPrice, unitInStock)); // menu fill
		}
		in.close();
		return new Restaurant(id, name, cityId, address, phone, menu,kitchenType);
	}

	public static void main(String[] args) throws IOException {
		Restaurant r0 = createRestaurant(1, "Battalbey Çiğköfte", 35, "Bucakoop Mah. no:45 İzmir", "+2322541102");
		String name = "Sadullah";
		String email = "ozz@gmail.com";
		String password = "foodSepeti5";
		String phone = "+905426040270";
		String address = "Denizli Merkez";
		String birthday = "07.05.2000";
		boolean isPremium = false; // swing ten gelen
		Customer u0;
		if (isPremium)
			u0 = new PremiumUser(name, email, password, phone, address, birthday);
		else
			u0 = new User(name, email, password, phone, address, birthday);
		// u0.display();
		Authorization checking = new Authorization();
		Customer u1 = checking.login("+905426040270", "foodSepeti5");
		Boolean passed = u1 != null ;
		if (passed) {
			u0.display();
			// resto seç
			// resto bilgileri butonu
			r0.displayRestoInfo();
			// resto yemek listesi buton
			System.out.println("\nMenu\n");
			for (Food f : r0.getMenu())
				System.out.println(f.getName() + " " + f.getUnitPrice());
			Boolean buttonAddToCart = true;
			if (buttonAddToCart) { // r0 parametresi gelir fonksiyon olursa
				int xTh = 0; // ex:3. yemek//swing ten gelecek
				addToCart(new Cart(basket.size() + 1, r0.getMenu().get(xTh).getFoodID(), 1));
				addToCart(new Cart(basket.size() + 1, r0.getMenu().get(1).getFoodID(), 1));
				addToCart(new Cart(basket.size() + 1, r0.getMenu().get(2).getFoodID(), 1));
				int basketSize = basket.size();
				ArrayList<Food> menu = r0.getMenu();
				int menuSize = menu.size();
				for (int i = 0; i < basketSize; i++) {
					// menüdeki food id ile cart taki eşit olursa sepete ekle ve ekrana bas
					for (int j = 0; j < menuSize; j++) {
						if (basket.get(i) != null && basket.get(i).getFoodID() == menu.get(j).getFoodID()) {
							System.out.println(
									menu.get(j).getKitchenType() + menu.get(j).getName() + menu.get(j).getUnitPrice());
						}
					}
				}
			}
			if (confirmTheBasket()) {
				int paymentTypeID = 0;
				Payment p;
				int cc = 5555555;
				p = new Payment(0);
				ArrayList<Food> menu = r0.getMenu();
				int foodCost = calculateFoodCost(menu);
				if (p.payment(paymentTypeID, cc, foodCost, u0, "reduce1")) {
					String orderNote = "kat 1 girişte soldaki adama dikkat edin";
					Calendar date = Calendar.getInstance();
					Order o = new Order(1, basket, date, foodCost, orderNote);
					o.display(menu);
					changeTheAmountOfProduct(menu);
					dischargeCart();
				}
			}
		}
	}

	static public void addToCart(Cart cart) {
		basket.add(cart);
	}

	static public void deleteFromCart(int cartID) {
		basket.remove(cartID);
	}

	static public void dischargeCart() {
		// int basketSize = basket.size();
		basket.removeAll(basket);
	}

	static public void setQuantity(int cartID, boolean isIncreased) {
		int quantity = basket.get(cartID).getQuantity();
		if (isIncreased) {
			basket.get(cartID).setQuantity(++quantity); // if user presses the + button
			return;
		}
		basket.get(cartID).setQuantity(--quantity); // if user presses the - button
	}

	static public boolean confirmTheBasket() {
		return true;
	}

	static public int calculateFoodCost(ArrayList<Food> menu) {
		int basketSize = basket.size();
		int menuSize = menu.size();
		int cost = 0;
		for (int i = 0; i < basketSize; i++) {
			// menüdeki food id ile cart taki eşit olursa cost değiştir
			for (int j = 0; j < menuSize; j++) {
				if (basket.get(i) != null && basket.get(i).getFoodID() == menu.get(j).getFoodID())
					cost += menu.get(j).getUnitPrice() * basket.get(i).getQuantity();
			}
		}
		return cost;
	}

	static public void changeTheAmountOfProduct(ArrayList<Food> menu) {
		int basketSize = basket.size();
		int menuSize = menu.size();
		for (int i = 0; i < basketSize; i++) {
			// menüdeki food id ile cart taki eşit olursa unit stock update
			for (int j = 0; j < menuSize; j++) {
				if (basket.get(i) != null && basket.get(i).getFoodID() == menu.get(j).getFoodID()) {
					menu.get(j).setUnitInStock(menu.get(j).getUnitInStock() - basket.get(i).getQuantity());
					// System.out.println(menu.get(j).getName()+" " +menu.get(j).getUnitInStock());
				}
			}
		}
	}

}