package in.novopay.actor.user.dao.impl;

import org.springframework.data.mongodb.repository.MongoRepository;

import in.novopay.actor.user.dao.User;

public interface UserRepository extends MongoRepository<User, String>{

	User findOneByEmailIgnoreCase(String email);
	
	void deleteByEmailIgnoreCase(String email);
	
}
