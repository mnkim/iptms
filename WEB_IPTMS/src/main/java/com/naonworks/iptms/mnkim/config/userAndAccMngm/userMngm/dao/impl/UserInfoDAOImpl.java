package com.naonworks.iptms.mnkim.config.userAndAccMngm.userMngm.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Service;

import com.naonworks.iptms.mnkim.config.userAndAccMngm.userMngm.dao.UserInfoDAO;
import com.naonworks.iptms.mnkim.config.userAndAccMngm.userMngm.model.UserInfoVO;

@Service("UserInfoDAO")
public class UserInfoDAOImpl extends SqlSessionDaoSupport implements UserInfoDAO {
	
	@Override
	public int insertUserInfo(Map<String, String> map) {
		return (Integer) getSqlSession().insert("userInfoDao.insertUserInfo", map);
	}

	@Override
	public List<UserInfoVO> selectUserInfoList(Map<String, String> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("userInfoDao.selectUserInfo", map);
	}

	@Override
	public int deleteUserInfo(Map<String, String> map) {
		// TODO Auto-generated method stub
		return (Integer) getSqlSession().delete("userInfoDao.deleteUserInfo", map);
	}

	@Override
	public int updateUserInfo(Map<String, String> map) {
		// TODO Auto-generated method stub
		return (Integer) getSqlSession().update("userInfoDao.updateUserInfo", map);
	}

	@Override
	public int selectUserInfoListCnt(Map<String, String> map) {
		// TODO Auto-generated method stub
		return (Integer) getSqlSession().selectOne("userInfoDao.selectUserInfoCnt", map);
	}
	
	
}
