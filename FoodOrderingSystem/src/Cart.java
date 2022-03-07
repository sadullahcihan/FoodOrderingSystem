
public class Cart {
	private int id;
	private int foodID;
	private int quantity;
	
	public Cart(int id, int foodID, int quantity) {
		this.id = id;
		this.foodID = foodID;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFoodID() {
		return foodID;
	}

	public void setFoodID(int foodID) {
		this.foodID = foodID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
