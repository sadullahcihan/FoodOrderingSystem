
public class Food {
	private int foodID;
	private String foodName;
	private String kitchenType;
	private int unitPrice;
	private int unitInStock;

	public Food(int foodID, String name, String kitchenType, int unitPrice, int unitInStock) {
		this.foodName = name;
		this.foodID = foodID;
		this.kitchenType = kitchenType;
		this.unitPrice = unitPrice;
		this.unitInStock = unitInStock;
	}

	public String getName() {
		return foodName;
	}

	public void setName(String name) {
		this.foodName = name;
	}

	public int getFoodID() {
		return foodID;
	}

	public void setFoodID(int foodID) {
		this.foodID = foodID;
	}

	public String getKitchenType() {
		return kitchenType;
	}

	public void setKitchenType(String kitchenType) {
		this.kitchenType = kitchenType;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getUnitInStock() {
		return unitInStock;
	}

	public void setUnitInStock(int unitInStock) {
		this.unitInStock = unitInStock;
	}
}