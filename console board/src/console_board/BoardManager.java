package console_board;

import java.util.ArrayList;
import java.util.Arrays;

public class BoardManager {
	ArrayList<Board> boardList = new ArrayList<>();
	Board board = new Board();

	public ArrayList<Board> getboard() {
		return this.boardList;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("제목 : %s / 내용 : %s / 작성자 : %s", board.title, board.contents, board.nickName);
	}
}
