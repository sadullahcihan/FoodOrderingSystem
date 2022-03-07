
public class Payment {
	private int paymentTypeID;

	public Payment(int paymentTypeID) {
		this.paymentTypeID = paymentTypeID;
	}

	public int getPaymentTypeID() {
		return paymentTypeID;
	}

	public void setPaymentTypeID(int paymentTypeID) {
		this.paymentTypeID = paymentTypeID;
	}

	public boolean payment(int paymentTypeID, int cc, int foodCost, Customer c, String discountCode) {
		if (c.getType().equals("P")) {
			foodCost = foodCost * (1 - (c.getDiscountPercent() / 100)); // 10% discount
			System.out.println("\nYou got "+c.getDiscountPercent()+"% discount!");
		}
			
		if (discountCode.equals("reduce1")) {
			foodCost -= 1;
			System.out.println("\nYou got 1 TL discount!");
		}
			
		else if (discountCode.equals("reduce5")) {
			foodCost -= 5;
			System.out.println("\nYou got 5 TL discount!");
		}
		switch (paymentTypeID) {
		case 0: {
			// credit cart
			if (confirmCreditCard(cc)) {
				// cc.setMoney(cc.getMoney-foodCost) //(if we had cc system)
				System.out.println("Total cost: " + foodCost + " TL");
				System.out.println("Your order has been completed");
				return true;
			}
			System.out.println("Your credit card has failed");
			return false;
		}
		case 1: {
			// paying on the door
			System.out.println("Total cost: " + foodCost + " TL");
			System.out.println("Your order has confirmed. You will pay on the door.");
			return true;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + paymentTypeID);
		}
	}

	public boolean confirmCreditCard(int cc) {
		return true;
	}
}