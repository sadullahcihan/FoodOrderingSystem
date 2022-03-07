import java.util.ArrayList;

public class Restaurant {
	private int id;
	private String name;
	private int cityId;
	private String address;
	private String phone;
	private ArrayList<Food> menu;
	private String kitchenType;

	public Restaurant(int id, String name, int cityId, String address, String phone, ArrayList<Food> menu,String kitchenType) {
		this.id = id;
		this.name = name;
		this.cityId = cityId;
		this.address = address;
		this.phone = phone;
		this.menu = menu;
		this.kitchenType=kitchenType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public ArrayList<Food> getMenu() {
		return menu;
	}

	public void setMenu(ArrayList<Food> menu) {
		this.menu = menu;
	}
	
	public String getKitchenType() {
		return kitchenType;
	}

	public void setKitchenType(String kitchenType) {
		this.kitchenType = kitchenType;
	}

	public void displayRestoInfo() {
		System.out.println("\n"+cityId + "\n" + name + "\n" + address + "\n" + phone);
	}

}