package com.doongie.instagram.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doongie.instagram.common.EncryptService;
import com.doongie.instagram.user.dao.UserDAO;

@Service
public class UserBO {
	
	@Autowired
	private UserDAO userDAO;
	
	public int addUser(String loginId, String password, String name, String email) {
		
		
		String encryptPassword = EncryptService.md5(password);
		
		return userDAO.insertUser(loginId, encryptPassword, name, email);
	}
	
	public boolean isDuplicateId(String loginId) {
		
		int count = userDAO.selectCountByLoginId(loginId);
		
		if(count == 0) {
			return false;
		} else {
			return true;
		}
	}

}
