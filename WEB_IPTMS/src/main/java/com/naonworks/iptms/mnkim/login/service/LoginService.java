package com.naonworks.iptms.mnkim.login.service;

import com.naonworks.iptms.mnkim.login.model.LoginVO;


public interface LoginService {
	
	public LoginVO selectLogin(LoginVO vo) throws Exception;
}
