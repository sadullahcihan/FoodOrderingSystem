import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Order {
	private int id;
	private ArrayList<Cart> basket;
	private Calendar orderTime;
	private String orderNote;
	private int foodCost;

	public Order(int id, ArrayList<Cart> basket, Calendar orderTime, int foodCost, String orderNote) {
		this.id = id;
		this.basket = basket;
		this.orderTime = orderTime;
		this.foodCost = foodCost;
		this.orderNote = orderNote;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Cart> getBasket() {
		return basket;
	}

	public void setBasket(ArrayList<Cart> basket) {
		this.basket = basket;
	}

	public int getFoodCost() {
		return foodCost;
	}

	public void setFoodCost(int foodCost) {
		this.foodCost = foodCost;
	}

	public Calendar getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Calendar orderTime) {
		this.orderTime = orderTime;
	}

	public String getOrderNote() {
		return orderNote;
	}

	public void setOrderNote(String orderNote) {
		this.orderNote = orderNote;
	}

	public int couponDiscount(String couponCode) {
		return 0;
	}

	public void display(ArrayList<Food> menu) {
		System.out.println("\nYour order:\nOrder id :" + id);
		System.out.println("Order Time: " + orderTime.getTime());
		long timeInSecs = orderTime.getTimeInMillis();
		Date afterAdding30Mins = new Date(timeInSecs + (30 * 60 * 1000));
		System.out.println("Your order will be delivered around: " + afterAdding30Mins);
		System.out.println("Food Cost: " + foodCost);
		System.out.println("Order Note: " + orderNote);
		System.out.println("Basket:\n");
		int basketSize = basket.size();
		int menuSize = menu.size();
		int cost = 0;
		for (int i = 0; i < basketSize; i++) {
			// menüdeki food id ile cart taki eşit olursa cost değiştir
			for (int j = 0; j < menuSize; j++) {
				if (basket.get(i) != null && basket.get(i).getFoodID() == menu.get(j).getFoodID())
					System.out.println("Food name: " + menu.get(j).getName() + " Price: " + menu.get(j).getUnitPrice()
							+ " Quantity: " + basket.get(i).getQuantity());
			}
		}
	}
}