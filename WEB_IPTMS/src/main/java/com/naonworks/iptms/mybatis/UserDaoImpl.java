package com.naonworks.iptms.mybatis;

import java.util.Map;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.dao.DataAccessException;

import com.naonworks.iptms.dao.UserDao;

public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {
	
	@Override
	public int getLoginResult(Map<String, String> map) throws DataAccessException  {
		return (Integer) getSqlSession().selectOne("userDao.getLoginResult",map);
	}
}
