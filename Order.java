public class Order {
	private String Order_Name;
	private int Quantity;
	private String Special_Need;
	private double Money;

	public Order(String ordername) {
		this.Order_Name = ordername;
		this.Quantity = 1;
		this.Special_Need = "";
	}

	public Order(String ordername, int quantity) {
		this.Order_Name = ordername;
		this.Quantity = quantity;
		this.Special_Need = "";
	}

	public double getMoney() {
		return Money;
	}

	public void setMoney(double money) {
		Money = money;
	}

	public Order(String ordername, int quantity, String special_need, double Money) {
		this.Order_Name = ordername;
		this.Quantity = quantity;
		this.Special_Need = special_need;
		this.Money = Money;
	}

	public String getOrder_Name() {
		return Order_Name;
	}

	public void setOrder_Name(String order_Name) {
		Order_Name = order_Name;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

	public String getSpecial_Need() {
		return Special_Need;
	}

	public void setSpecial_Need(String special_Need) {
		Special_Need = special_Need;
	}

}
