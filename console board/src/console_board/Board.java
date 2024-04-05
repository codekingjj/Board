package console_board;

public class Board {
	String title, contents, nickName;
	
	public Board() {
		
	}
	
	public Board(String title, String contents, String nickName) {
		this.title = title;
		this.contents = contents;
		this.nickName = nickName;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("제목 : %s / 내용 : %s / 작성자 : %s", title, contents, nickName);
	}
}
