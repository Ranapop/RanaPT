package ro.utcn.pt.assign2.dao.dbentities;

public class UserT {

	private int idUser;
	private String username;
	private String password;
	
	public UserT(String username, String password){
		
		this.username = username;
		this.password = password;
	}
	
	public UserT(){
		
	}

	public UserT(int userId, String username, String password) {
		this.idUser = userId;
		this.username = username;
		this.password = password;
	}

	public int getIdUser() {
		return idUser;
	}
	
	public String getUsername(){
		return username;
	}

	public String getPassword() {
		return password;
	}
}
