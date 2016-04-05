package ro.utcn.pt.assign2.presentation.model;

public class Warehouse {

	private Product product;
	private int quantity;
	public Warehouse(Product product, int quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + quantity;
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
		Warehouse other = (Warehouse) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}
	
	
}
