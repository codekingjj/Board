package console_board;

import java.util.Scanner;

public class RunBoard {
	public RunBoard() {
		showMenu();
	}

	Scanner scan = new Scanner(System.in);

	final int EXIT = 0;
	final int SIGN_IN = 1;
	final int SIGN_OUT = 2;
	final int LOG_IN = 3;
	final int LOG_OUT = 4;

	private boolean isRun = true;

	UserManager userManager = new UserManager();
	BoardManager boardManager = new BoardManager();

	private void showMenu() {
		System.out.println("===게시판===");
		System.out.println("1) 회원가입");
		System.out.println("2) 회원탈퇴");
		System.out.println("3) 로그인");
		System.out.println("4) 로그아웃");
		System.out.println("0) 종료");
	}

	private void signIn() {
		String id = inputString("아이디");
		String pw = inputString("비밀번호");
		String nickName = inputString("닉네임");

		User user = userManager.createUser(id, pw, nickName);

		userManager.list.add(user);

		printCompleteMessage(user);

	}

	private void runMenu(int select) {
		switch (select) {
		case SIGN_IN:
			signIn();
			break;
		case SIGN_OUT:
//			signOut();
			break;

		case LOG_IN:
//			logIn();
			break;

		case LOG_OUT:
//			logOut();
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
		String message = user.getId() != null ? String.format("%s(%s) 회원님 환영합니다.", user.nickName, user.getId())
				: "회원가입 실패";
		System.out.printf(message);
	}

	public void run() {
		while (isRun) {
			int select = inputNumber("메뉴 선택");
			runMenu(select);
		}
	}
}
