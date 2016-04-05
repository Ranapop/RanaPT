package ro.utcn.pt.assign2.utilities;

import ro.utcn.pt.assign2.presentation.model.Order;
import ro.utcn.pt.assign2.presentation.model.Product;
import ro.utcn.pt.assign2.presentation.model.User;
import ro.utcn.pt.assign2.presentation.model.Warehouse;

public class ModelFactory {

	public static User createUser(String username, String password) {
		// returns null on error
		if (username.equals(Constants.DEFAULT_TABLE_VALUE)) {
			return null;
		}
		if (password.equals(Constants.DEFAULT_TABLE_VALUE)) {
			return null;
		}
		return new User(username, password);
	}
	
	public static  Warehouse createWarehouse(String name, String price,String quantity) {
		// returns null on error
		Integer priceAsInt;
		Integer quantityAsInt;
		try{
			priceAsInt = Integer.valueOf(price);
			quantityAsInt = Integer.valueOf(quantity);
			
		}catch(NumberFormatException e){
			return null;
		}
		if (name.equals(Constants.DEFAULT_TABLE_VALUE)) {
			return null;
		}
		return new Warehouse(new Product(name,priceAsInt),quantityAsInt);
	}
	
	public static  Order createOrder(String username,String password,String name, String price,String quantity) {
		// returns null on error
		Integer priceAsInt;
		Integer quantityAsInt;
		try{
			priceAsInt = Integer.valueOf(price);
			quantityAsInt = Integer.valueOf(quantity);
		}catch(NumberFormatException e){
			return null;
		}
		if (username.equals(Constants.DEFAULT_TABLE_VALUE)) {
			return null;
		}
		if (password.equals(Constants.DEFAULT_TABLE_VALUE)) {
			return null;
		}
		if (name.equals(Constants.DEFAULT_TABLE_VALUE)) {
			return null;
		}
		User user = new User(username,password);
		Product product = new Product(name, priceAsInt);
		return new Order(user,product,quantityAsInt);
	}
}
