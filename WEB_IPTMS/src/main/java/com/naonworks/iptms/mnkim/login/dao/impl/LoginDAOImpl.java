package com.naonworks.iptms.mnkim.login.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Service;

import com.naonworks.iptms.mnkim.login.dao.LoginDAO;
import com.naonworks.iptms.mnkim.login.model.LoginVO;

@Service("LoginDAO")
public class LoginDAOImpl extends SqlSessionDaoSupport implements LoginDAO {


	@Override
	public LoginVO selectLoginUser(LoginVO vo) {
		// TODO Auto-generated method stub
		return (LoginVO) getSqlSession().selectOne("loginDao.selectUser",vo);
	}
	
	
}
