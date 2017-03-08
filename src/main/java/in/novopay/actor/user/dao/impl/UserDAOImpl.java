package in.novopay.actor.user.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import in.novopay.actor.user.dao.IUserDAO;
import in.novopay.actor.user.dao.User;

@Repository
public class UserDAOImpl implements IUserDAO {
	
	@Autowired
	private UserRepository userRepository;
	
//	@Autowired
//	private MongoTemplate mongoTemplate;

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User update(User user) {
		User _userInDB = userRepository.findOneByEmailIgnoreCase(user.getEmail());
		user.setId(_userInDB.getId());
		return userRepository.save(user);
	}

	@Override
	public void delete(String email) {
		userRepository.deleteByEmailIgnoreCase(email);
	}

	@Override
	public User getUser(String email) {
		return userRepository.findOneByEmailIgnoreCase(email);
	}

	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}

	@Override
	public List<User> searchUsers(String searchString) {
//		Query query = new Query();
//		for (String token: searchString.split(" ")) {
//			query.addCriteria(Criteria.where("first_name").regex(token).orOperator(Criteria.where("last_name").regex(token)).orOperator(Criteria.where("last_name").regex(token)));
//		}
//		
//		mongoTemplate.remove(query, User.class);
		return null;
	}
	
	

}
