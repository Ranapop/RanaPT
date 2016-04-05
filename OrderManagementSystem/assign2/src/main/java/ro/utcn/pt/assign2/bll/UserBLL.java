package ro.utcn.pt.assign2.bll;

import java.util.ArrayList;

import ro.utcn.pt.assign2.dao.TableDAO;
import ro.utcn.pt.assign2.dao.dbentities.UserT;
import ro.utcn.pt.assign2.presentation.model.User;

public class UserBLL {

	public static boolean insert(User user) {/// should I pass warehouse? :? or
												/// just product + quantity

		UserT userTable = new UserT(user.getUsername(), user.getPassword());
		return TableDAO.insert(userTable);
	}

	public static boolean update(User oldUser, User newUser) {
		int userId = checkUser(oldUser);
		if(userId == -1)
			return false;
		UserT userT = new UserT(userId,newUser.getUsername(),newUser.getPassword());
		return TableDAO.update(userT);
	}
	
	public static boolean delete(User user) {
		UserT userTable = (UserT)TableDAO.read(UserT.class, "username", "'"+user.getUsername()+"'");
		if(userTable!=null){
			return TableDAO.delete(userTable);
		}
		return false;
	}
	
	public static ArrayList<Object> read(){
		ArrayList<Object> users = new ArrayList<>();
		ArrayList<Object> objects = TableDAO.readAll(UserT.class);
		for(Object object :objects){
			UserT userT = (UserT) object;
			User user = new User(userT.getUsername(),userT.getPassword());
			users.add(user);
		}
		return users;
	}
	public static int checkUser(User user) {// method will return -1 on error
		

		UserT userTable = (UserT)TableDAO.read(UserT.class, "username", "'"+user.getUsername()+"'");
		if(userTable==null){
			return -1;
		}
		User userFromDb = new User(userTable.getUsername(), userTable.getPassword());
		if (userFromDb.equals(user)) {
			return userTable.getIdUser();
		}
		return -1;
	}

}
