package in.novopay.actor.user.service;

import java.util.List;

public interface IUserService {

	void save(UserDTO user);
	
	void update(UserDTO user);
	
	void delete(String email);
	
	UserDTO getUser(String email);
	
	List<UserDTO> getAll();
}
