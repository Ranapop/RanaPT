package ro.utcn.pt.assign2.bll;

import java.util.ArrayList;

import ro.utcn.pt.assign2.dao.TableDAO;
import ro.utcn.pt.assign2.dao.dbentities.ProductT;
import ro.utcn.pt.assign2.presentation.model.Product;
import ro.utcn.pt.assign2.presentation.model.Warehouse;

public class WarehouseBLL {

	public static boolean insert(Warehouse warehouse) {

		ProductT productTable = new ProductT(warehouse.getProduct().getName(), warehouse.getProduct().getPrice(),warehouse.getQuantity());
		return TableDAO.insert(productTable);
	}

	public static boolean update(Warehouse oldWarehouse,Warehouse newWarehouse) {
		ProductT productTable = (ProductT)TableDAO.read(ProductT.class, "name", "'"+oldWarehouse.getProduct().getName()+"'");
		productTable.setPrice(newWarehouse.getProduct().getPrice());
		productTable.setName(newWarehouse.getProduct().getName());
		productTable.setStock(newWarehouse.getQuantity());
		return TableDAO.update(productTable);
	}
	
	public static boolean delete(Warehouse warehouse){
		ProductT productTable = (ProductT)TableDAO.read(ProductT.class, "name", "'"+warehouse.getProduct().getName()+"'");
		if(productTable!=null){
			return TableDAO.delete(productTable);
		}
		return false;
	}
	
	public static ArrayList<Object> read(){
		ArrayList<Object> warehouses = new ArrayList<>();
		ArrayList<Object> objects = TableDAO.readAll(ProductT.class);
		for(Object object :objects){
			ProductT productT = (ProductT) object;
			Warehouse warehouse = new Warehouse(new Product(productT.getName(),productT.getPrice()),productT.getStock());
			warehouses.add(warehouse);
		}
		return warehouses;
	}
	
}
