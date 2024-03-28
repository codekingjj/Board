package console_board;

public class User {
	String nickName, id, pw;
	
	public User() {
		
	}
	
	public User(String nickName, String id, String pw) {
		this.nickName = nickName;
		this.id = id;
		this.pw = pw;
	}
	
	public String getNickName() {
		return this.nickName;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getPw() {
		return this.pw;
	}
	
	public void setPw(String pw) {
		this.pw = pw;
	}
	
}
