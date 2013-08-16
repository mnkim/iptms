package com.naonworks.iptms.mnkim.config.userAndAccMngm.userMngm.service;

import java.util.List;
import java.util.Map;

import com.naonworks.iptms.mnkim.config.userAndAccMngm.userMngm.model.UserInfoVO;


public interface UserInfoService {
	
	public int insertUserInfo(Map<String, String> paramVO) throws Exception;
	
	public List<UserInfoVO> selectUserInfoList(Map<String, String> paramVO) throws Exception;
	
	public int deleteUserInfo(Map<String, String> paramVO) throws Exception;
	
	public int updateUserInfo(Map<String, String> paramVO) throws Exception;	
	
	public int selectUserInfoListCnt(Map<String, String> paramVO) throws Exception;	
}
