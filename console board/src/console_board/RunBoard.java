package console_board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class RunBoard {
	public RunBoard() {

	}

	Scanner scan = new Scanner(System.in);

	final int EXIT = 0;
	final int SIGN_IN = 1;
	final int SIGN_OUT = 6;
	final int LOG_IN = 2;
	final int LOG_OUT = 5;

	int log;
	String nickName;

	private boolean isRun = true;

	UserManager userManager = new UserManager();
	BoardManager boardManager = new BoardManager();

	private void showMenu() {
		System.out.println("===게시판===");
		System.out.println("1) 회원가입");
		System.out.println("2) 로그인");
		System.out.println("0) 종료");
	}

	private void signIn() {
		checkLog();
		if (checkLog()) {
			System.out.println("로그아웃 상태에서 이용가능한 서비스 입니다.");
			return;
		}
		String id = inputString("아이디");
		String pw = inputString("비밀번호");
		String nickName = inputString("닉네임");

		User user = userManager.createUser(id, pw, nickName);

		printCompleteMessage(user);

	}

	private void signOut() {
		checkLog();
		if (!checkLog()) {
			System.out.println("로그아웃 상태에서 이용가능한 서비스 입니다.");
			return;
		}
		String pw = inputString("비밀번호");

		User user = userManager.findUserByNickName(nickName);

		boolean result = false;
		if (user.getPw().equals(pw)) {
			result = userManager.deleteUser(user);
		}
		String message = result ? "회원 탈퇴 완료" : "회원 탈퇴 실패";
		if (result) {
			nickName = "";
			log = 0;
		}
		System.out.println(message);
	}

	private void logIn() {
		checkLog();
		if (checkLog()) {
			System.out.println("로그아웃 상태에서 이용가능한 서비스 입니다.");
			return;
		}
		String id = inputString("아이디");
		String pw = inputString("비밀번호");

		User user = userManager.findUserById(id);

		if (user.getPw().equals(pw)) {
			log = 1;
			nickName = user.getNickName();
			System.out.printf("%s님, 로그인 되었습니다.\n", nickName);
		} else {
			System.out.println("아이디 혹은 비밀번호가 일치하지 않습니다.");
		}
	}

	private void logOut() {
		checkLog();
		if (!checkLog()) {
			System.out.println("로그아웃 상태에서 이용가능한 서비스 입니다.");
			return;
		}
		log = 0;
		nickName = "";
		System.out.println("로그아웃 되었습니다.");
	}

	private void showBoard() {

		for (Board board : boardManager.boardList) {
			System.out.println(board);
		}

		System.out.println("1) 게시글 작성");
		System.out.println("2) 게시글 조회");
		System.out.println("3) 게시글 삭제");
		System.out.println("4) 게시글 수정");
		System.out.println("5) 로그아웃");
		System.out.println("6) 회원탈퇴");

		int select = scan.nextInt();

		runboard(select);
	}

	private void runboard(int select) {
		switch (select) {
		case 1:
			writeContents();
			break;
		case 2:
//			searchContents();
			break;
		case 3:
//			deleteContents();
			break;
		case 4:
//			fixContents();
			break;
		case LOG_OUT:
			logOut();
			break;
		case SIGN_OUT:
			signOut();
			break;
		}
	}

	private void writeContents() {
		String title = inputString("제목");
		String contents = inputString("내용");
		String writer = nickName;

		Board board = new Board(title, contents, writer);
		boardManager.boardList.add(board);
	}

	private void runMenu(int select) {
		switch (select) {
		case SIGN_IN:
			signIn();
			break;
		case LOG_IN:
			logIn();
			break;
		case EXIT:
			isRun = false;
			break;
		}
	}

	// 입력 기능(문자열, 정수)
	private String inputString(String message) {
		System.out.println(message + " : ");
		String str = scan.next();
		return str;
	}

	private int inputNumber(String message) {
		int number = -1;
		String str = "";
		System.out.println(message + " : ");
		try {
			str = scan.next();
			number = Integer.parseInt(str);
		} catch (Exception e) {
			System.err.println("해당 값은 존재하지 않습니다.");
		}
		return number;
	}

	private void printCompleteMessage(User user) {
		String message = user.getId() != null ? String.format("%s(%s) 회원님 환영합니다.\n", user.nickName, user.getId())
				: "회원가입 실패\n";
		System.out.printf(message);
	}

	private boolean checkLog() {
		if (log == 1) {
			return true;
		} else {
			return false;
		}
	}

	public void run() {
		while (isRun) {
			if (log == 1) {
				showBoard();
			} else {
				showMenu();
//			System.out.println(nickName); // 검수용
//			System.out.println(userManager.list);// 검수용
				int select = inputNumber("메뉴 선택");
				runMenu(select);
			}
		}
	}
}
