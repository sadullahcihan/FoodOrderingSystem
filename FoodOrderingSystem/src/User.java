
public class User implements Customer {

	private int id;
	private String name;
	private String email;
	private String password;
	private String phone;
	private String address;
	private String birthday;
	private String type = "N";

	// first user creation
	public User(String name, String email, String password, String phone, String address, String birthday) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.birthday = birthday;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void display() {
		System.out.println(id + " " + name + " " + email + " " + password + " " + phone + " " + address + " " + birthday
				+ " " + type);
	}

	public int getDiscountPercent() {
		return 0;
	}

	public void setDiscountPercent(int discountPercent) {
	}
}