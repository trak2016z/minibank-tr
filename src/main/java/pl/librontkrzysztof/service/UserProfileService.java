package pl.librontkrzysztof.service;

import java.util.List;

import pl.librontkrzysztof.model.UserProfile;


public interface UserProfileService {

	UserProfile findById(int id);

	UserProfile findByType(String type);
	
	List<UserProfile> findAll();
	
}
