package ro.utcn.pt.assign2.dao.dbentities;

public class OrderT {
	private int idOrder;
	private int userId;
	private int productId;
	private int quantity;
	
	public OrderT(int userId, int productId, int quantity) {
		this.userId = userId;
		this.productId = productId;
		this.quantity = quantity;
	}
	
	public OrderT(){
		
	}

	public int getIdOrder(){
		return idOrder;
	}
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
