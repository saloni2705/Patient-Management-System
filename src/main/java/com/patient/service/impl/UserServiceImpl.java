package com.patient.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.patient.entity.UserDtls;
import com.patient.repository.UserRepository;
import com.patient.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDtls createUser(UserDtls user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        return userRepo.save(user);
    }

    @Override
    public boolean checkEmail(String email) {
        return userRepo.existsUserDtlsByEmail(email);
    }

	@Override
	public void updateUser(UserDtls user) {
		 user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepo.save(user);// TODO Auto-generated method stub
		
	}
	@Override
	public void updateUserPasswordByEmail(String email, String newPassword) {
	    UserDtls user = userRepo.findByEmail(email);
	    if (user != null) {
	        user.setPassword(passwordEncoder.encode(newPassword));
	        userRepo.save(user);
	    }
	}
	
	@Override
	public UserDtls getUserByEmail(String email) {
	    return userRepo.findByEmail(email);
	}



}
