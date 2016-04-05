package ro.utcn.pt.assign2.bll;

import java.util.ArrayList;
import java.util.List;

import ro.utcn.pt.assign2.dao.TableDAO;
import ro.utcn.pt.assign2.dao.dbentities.OrderT;
import ro.utcn.pt.assign2.dao.dbentities.ProductT;
import ro.utcn.pt.assign2.dao.dbentities.UserT;
import ro.utcn.pt.assign2.presentation.model.Order;
import ro.utcn.pt.assign2.presentation.model.Product;
import ro.utcn.pt.assign2.presentation.model.User;

public class OrderBLL {

	public static String insert(Order order){//returns an error or null if success
		
		//1.get the user id
		int userId = UserBLL.checkUser(order.getUser());
		if(userId==-1){
			return "Unexisting user";
		}
		//2. get id of product to be inserted
		ProductT productT = getProduct(order.getProduct().getName());//can be replaced
		if(productT==null){
			return "Unexisting product";
		}
		//3. test for under stock
		if(productT.getStock()<order.getQuantity()){
			return "Understock";
		}
		//4.update product table
		productT.setStock(productT.getStock()-order.getQuantity());
		if(!TableDAO.update(productT)){
			return "Error updating the database";
		}
		OrderT orderT = new OrderT(userId,productT.getIdProduct(),order.getQuantity());
		if(!TableDAO.insert(orderT)){
			return "Error inserting the order";
		}
		return null;
	}
	
	public static boolean update(Order oldOrder,Order newOrder) {
		OrderT orderTable = (OrderT)TableDAO.read(OrderT.class, "idorder", String.valueOf(oldOrder.getId()));
		if(orderTable==null){
			return false;
		}
		ProductT newProductT = getProduct(newOrder.getProduct().getName());
		if(newProductT==null){
			return false;
		}
		orderTable.setProductId(newProductT.getIdProduct());
		System.out.println("product id set to "+newProductT.getIdProduct());
		int idUser ;
		if((idUser = UserBLL.checkUser(newOrder.getUser()))!=-1){
			orderTable.setUserId(idUser);
		}else{
			return false;
		}
		System.out.println("user id set to "+idUser);
		orderTable.setQuantity(newOrder.getQuantity());
		return TableDAO.update(orderTable);
	}
	
	public static boolean delete(Order order) {
		OrderT orderTable = (OrderT)TableDAO.read(OrderT.class, "idorder", String.valueOf(order.getId()));
		if(orderTable==null){
			return false;
		}
		return TableDAO.delete(orderTable);
	}
	
	public static List<Object> read(){
		ArrayList<Object> orders = new ArrayList<>();
		ArrayList<Object> objects = TableDAO.readAll(OrderT.class);
		for(Object object :objects){
			OrderT orderT = (OrderT) object;
			int userId = orderT.getUserId();
			UserT userTable = (UserT)TableDAO.read(UserT.class, "iduser", String.valueOf(userId));
			User user = new User(userTable.getUsername(),userTable.getPassword());
			int productId = orderT.getProductId();
			ProductT productTable = (ProductT)TableDAO.read(ProductT.class, "idproduct", String.valueOf(productId));
			Product product = new Product(productTable.getName(), productTable.getPrice());
			Order order = new Order(orderT.getIdOrder(),user,product,orderT.getQuantity());
			orders.add(order);
		}
		return orders;
	}
	
	private static ProductT getProduct(String productName){
		ProductT productT = null;
		ArrayList<Object> products = TableDAO.readAll(ProductT.class);
		for(Object obj: products){
			ProductT product = (ProductT)obj;
			if(product.getName().equals(productName)){
				productT = product;
			}
		}
		return productT;
	}
}
