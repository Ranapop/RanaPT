package ro.utcn.pt.assign2.utilities;

public class Constants {

	public static final String DEFAULT_TABLE_VALUE = "NULL";
	public static final String SUCCESS = "Success";
	public static final class ViewType{

		public static final String USER_VIEW = "UserView";
		public static final String WAREHOUSE_VIEW = "WarehouseView";
		public static final String ORDER_VIEW = "OrderView";
	}
	
	public static final class ERROR{

		public static final String INSERT_ERROR = "Error while inserting the data";
		public static final String UPDATE_ERROR = "Error while upadating the data";
		public static final String DELETE_ERROR = "Error while deleting the data";
		public static final String PRINT_ERROR = "Error printing the order!";
		public static final String UNSELECTED_ORDER = "Error! Please select an order to be printed!";
	}
	
}
