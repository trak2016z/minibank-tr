package pl.librontkrzysztof.dao;

import java.util.List;

import pl.librontkrzysztof.model.UserProfile;


public interface UserProfileDao {

	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(int id);
}
