package ro.utcn.pt.assign2.presentation.controller;
import ro.utcn.pt.assign2.dao.DBConnector;
import ro.utcn.pt.assign2.presentation.view.HomeFrame;

public class Main {

	public static void main(String[] args) {
		try {
			DBConnector dbConnector = new DBConnector();
		} catch (Exception e) {
			System.out.println("Please assure your mysql seerver is running!");
			e.printStackTrace();
		} 
		HomeFrame frame = new HomeFrame("Home");
		frame.addUserActionlistener(new UserButtonActionListener(frame));
        frame.addProductActionlistener(new WarehouseButtonActionListener(frame));
        frame.addOrderActionlistener(new OrderButtonActionListener(frame));
        frame.addInsertButtonActionListener(new InsertButtonActionListener(frame));
        frame.addUpdateButtonActionListener(new UpdateButtonActionListener(frame));
        frame.addDeleteButtonActionListener(new DeleteButtonActionListener(frame));
		frame.addPrintButtonActionListener(new PrintButtonActionListener(frame));
	}
}
