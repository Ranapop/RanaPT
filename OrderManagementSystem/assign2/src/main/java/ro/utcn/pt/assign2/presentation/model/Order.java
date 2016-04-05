package ro.utcn.pt.assign2.presentation.model;

public class Order {

	private int id;
	private User user;
	private Product product;
	private int quantity;
	public Order(int id,User user, Product product, int quantity) {

		this(user,product,quantity);
		this.id = id;
	}
	public Order(User user, Product product, int quantity) {
		this.user = user;
		this.product = product;
		this.quantity = quantity;
	}
	public Product getProduct() {
		return product;
	}
	public int getQuantity() {
		return quantity;
	}
	public User getUser(){
		return user;
	}
	
	public int getId(){
		return id;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", user=" + user + ", product=" + product + ", quantity=" + quantity + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + quantity;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id != other.id)
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (quantity != other.quantity)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
	
}
