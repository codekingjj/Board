package console_board;

import java.util.ArrayList;
import java.util.Arrays;

public class UserManager {
	int userCnt;
	ArrayList<User> list = new ArrayList<>();

	public User createUser(String id, String pw, String nickName) {
		if (isDuplId(id)) {
			User user = new User(id, pw, nickName);
			list.add(user);
			userCnt++;
			return user;
		}
		return new User();
	}

	public boolean deleteUser(User user) {
		String nickName = user.getNickName();
		User target = findUserByNickName(nickName);
		return list.remove(target);
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

	public User findUserByNickName(String nickName) {
		for (User user : list) {
			if (user.getNickName().equals(nickName)) {
				return user;
			}
		}
		return new User();
	}

}
