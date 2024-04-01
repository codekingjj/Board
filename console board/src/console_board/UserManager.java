package console_board;

import java.util.ArrayList;

public class UserManager {
	int userCnt;
	ArrayList<User> list = new ArrayList<>();

	public User createUser(String id, String pw, String nickName) {
		if (isDuplId(id)) {
			User user = new User(id, pw);
			user.nickName = nickName;
			userCnt++;
			return user;
		} else {
			return new User();
		}
	}

	public boolean isDuplId(String id) {
		User user = findUserById(id);
		if (id.equals(user.getId())) {
			return false;
		} else {
			return true;
		}
	}

	public User findUserById(String id) {
		for (User user : list) {
			if (user.getId().equals(id)) {
				return user.userClone();
			}
		}
		return new User();
	}

}
