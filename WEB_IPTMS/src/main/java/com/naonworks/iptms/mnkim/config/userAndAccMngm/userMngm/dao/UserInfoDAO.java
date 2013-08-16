package com.naonworks.iptms.mnkim.config.userAndAccMngm.userMngm.dao;

import java.util.List;
import java.util.Map;

import com.naonworks.iptms.mnkim.config.userAndAccMngm.userMngm.model.UserInfoVO;

public interface UserInfoDAO {
	public int insertUserInfo(Map<String, String> map);
	public List<UserInfoVO> selectUserInfoList(Map<String, String> map);
	public int deleteUserInfo(Map<String, String> map);
	public int updateUserInfo(Map<String, String> map);
	public int selectUserInfoListCnt(Map<String, String> map);
}
