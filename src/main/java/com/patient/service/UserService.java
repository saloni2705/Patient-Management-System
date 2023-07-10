package com.patient.service;

import com.patient.entity.UserDtls;

public interface UserService {

    UserDtls createUser(UserDtls user);

    boolean checkEmail(String email);

	void updateUser(UserDtls user);

	void updateUserPasswordByEmail(String email, String newPassword);
	
	UserDtls getUserByEmail(String email);

}
