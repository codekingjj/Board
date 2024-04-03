package console_board;

public class User {
	String nickName, id, pw;

	public User() {

	}

	public User(String id, String pw, String nickName) {
		this.id = id;
		this.pw = pw;
		this.nickName = nickName;
	}

	public User userClone() {
		return new User(this.id, this.pw, this.nickName);
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

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

}
