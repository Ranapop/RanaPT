package ro.utcn.pt.assign2.dao.dbentities;

public class ProductT {

	private int idproduct;
	private String name;
	private int price;
	private int stock;
	
	public ProductT(String name, int price, int stock){
		this.name = name;
		this.price = price;
		this.stock = stock;
	}
	
	public ProductT(){
	}
	
	public String toString(){
		return idproduct+" "+name+" "+price+" "+stock;
	}

	public int getIdProduct(){
		return idproduct;
	}
	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock){
		this.stock = stock;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public void setName(String name){
		this.name = name;
	}
}
