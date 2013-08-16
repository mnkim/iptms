package com.naonworks.iptms.mnkim.login.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.naonworks.iptms.mnkim.login.dao.LoginDAO;
import com.naonworks.iptms.mnkim.login.model.LoginVO;
import com.naonworks.iptms.mnkim.login.service.LoginService;

@Service("LoginService")
public class LoginServiceImpl implements LoginService {
	@Resource(name = "LoginDAO")
	private LoginDAO loginDAO ;

	@Override
	public LoginVO selectLogin(LoginVO vo) throws Exception {
		// TODO Auto-generated method stub
		return loginDAO.selectLoginUser(vo);
	}

}
