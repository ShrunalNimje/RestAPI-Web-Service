package my.mood.restAPI.RestAPI.Web.Service.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	public static List<User> users = new ArrayList<>();
	
	public static int countUser = 0;
	
	static {
		users.add(new User(++countUser, "Adam", LocalDate.now().minusYears(45)));
		users.add(new User(++countUser, "Shakira", LocalDate.now().minusYears(34)));
		users.add(new User(++countUser, "Shrunal", LocalDate.now().minusYears(21)));
	}
	
	// return all the users
	public List<User> findAll(){
		return users;
	}
	
	// return one specific user
	public User findOne(int id) {
		Predicate<? super User> predicate = User -> User.getId() == id;
		return users.stream().filter(predicate).findFirst().get();
	}
	
	// create & return user
	public User createUser(User user) {
		user.setId(++countUser);
		users.add(user);
		return user;
	}
}
