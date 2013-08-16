package com.naonworks.iptms.mnkim.config.userAndAccMngm.userMngm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.naonworks.iptms.mnkim.config.userAndAccMngm.userMngm.dao.UserInfoDAO;
import com.naonworks.iptms.mnkim.config.userAndAccMngm.userMngm.model.UserInfoVO;
import com.naonworks.iptms.mnkim.config.userAndAccMngm.userMngm.service.UserInfoService;

@Service("UserInfoService")
public class UserInfoServiceImpl implements UserInfoService {
	@Resource(name = "UserInfoDAO")
	private UserInfoDAO userInfoDAO ;

	@Override
	public int insertUserInfo(Map<String, String> paramVO) throws Exception {
		return userInfoDAO.insertUserInfo(paramVO);
	}

	@Override
	public List<UserInfoVO> selectUserInfoList(Map<String, String> paramVO)
			throws Exception {
		// TODO Auto-generated method stub
		return userInfoDAO.selectUserInfoList(paramVO);
	}

	@Override
	public int deleteUserInfo(Map<String, String> paramVO) throws Exception {
		// TODO Auto-generated method stub
		return userInfoDAO.deleteUserInfo(paramVO);
	}

	@Override
	public int updateUserInfo(Map<String, String> paramVO) throws Exception {
		// TODO Auto-generated method stub
		return userInfoDAO.updateUserInfo(paramVO);
	}

	@Override
	public int selectUserInfoListCnt(Map<String, String> paramVO)
			throws Exception {
		// TODO Auto-generated method stub
		return userInfoDAO.selectUserInfoListCnt(paramVO);
	}

}
