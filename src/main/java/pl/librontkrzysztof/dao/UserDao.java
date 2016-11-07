package pl.librontkrzysztof.dao;

import java.util.List;

import pl.librontkrzysztof.model.User;


public interface UserDao {

	User findById(int id);
	
	User findBySSO(String sso);
	
	void save(User user);
	
	void deleteBySSO(String sso);
	
	List<User> findAllUsers();

}

