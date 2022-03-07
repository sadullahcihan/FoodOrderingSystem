
public class PremiumUser extends User implements Customer {

	private int discountPercent = 10; // 10% discount

	public PremiumUser(String name, String email, String password, String phone, String address, String birthday) {
		super(name, email, password, phone, address, birthday);
		setType("P");
	}

	@Override
	public int getDiscountPercent() {
		return discountPercent;
	}

	@Override
	public void setDiscountPercent(int discountPercent) {
		this.discountPercent = discountPercent;
	}
}