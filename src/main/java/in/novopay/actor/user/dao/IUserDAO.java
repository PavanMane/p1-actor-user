package in.novopay.actor.user.dao;

import java.util.List;

public interface IUserDAO {

	User save(User user);
	
	User update(User user);
	
	void delete(String email);
	
	User getUser(String email);
	
	List<User> getAll();
	
	List<User> searchUsers(String searchString);
	
	
}
