package console_board;

import java.util.Scanner;

public class RunBoard {
	public RunBoard() {

	}

	Scanner scan = new Scanner(System.in);

	final int EXIT = 0;
	final int SIGN_IN = 1;
	final int SIGN_OUT = 2;
	final int LOG_IN = 3;
	final int LOG_OUT = 4;

	int log;
	String nickName;

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

		printCompleteMessage(user);

	}

	private void signOut() {
		String pw = inputString("비밀번호");

		User user = userManager.findUserByNickName(nickName);

		boolean result = false;
		if (user.getPw().equals(pw)) {
			result = userManager.deleteUser(user);
			System.out.println("1");
		}
		String message = result ? "회원 탈퇴 완료" : "회원 탈퇴 실패";
		if (result) {
			nickName = "";
			log = 0;
		}
		System.out.println(message);
	}

	private void logIn() {
		String id = inputString("아이디");
		String pw = inputString("비밀번호");

		User user = userManager.findUserById(id);

		if (user.getPw().equals(pw)) {
			log = 1;
			nickName = user.getNickName();
			System.out.printf("%s님, 로그인 되었습니다\n.", nickName);
		} else {
			System.out.println("아이디 혹은 비밀번호가 일치하지 않습니다.");
		}
	}

	private void logOut() {
		log = 0;
		nickName = "";
		System.out.println("로그아웃 되었습니다.");
	}

	private void runMenu(int select) {
		switch (select) {
		case SIGN_IN:
			signIn();
			break;
		case SIGN_OUT:
			signOut();
			break;

		case LOG_IN:
			logIn();
			break;

		case LOG_OUT:
			logOut();
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

	public void run() {
		while (isRun) {
			showMenu();
			System.out.println(nickName);		//검수용
			System.out.println(userManager.list);//검수용
			int select = inputNumber("메뉴 선택");
			runMenu(select);
		}
	}
}
