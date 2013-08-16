package com.naonworks.iptms.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.naonworks.iptms.vo.SbcIdleDBBean;

@Repository
public class StatChartDaoImpl extends SqlSessionDaoSupport  {
	private static final Logger logger = LoggerFactory.getLogger(StatChartDaoImpl.class);
	
	/**
	 * 최근 데이터를 읽어 온다. 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SbcIdleDBBean> getInitStatChartData() {
		List<SbcIdleDBBean> resultData = null;
		logger.debug("fetching getInitStatChartData");
		resultData = getSqlSession().selectList("statDao.getInitChartData");
		logger.debug("fetched getInitStatChartData");
		return resultData;
	}
	
	/**
	 * 10초 주기 차트데이터를 읽어 온다. 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SbcIdleDBBean> getCurrStatChartData() {		
		List<SbcIdleDBBean> resultData = null;
		logger.debug("fetching getCurrStatChartData");
		resultData = getSqlSession().selectList("statDao.getChartData");
		logger.debug("fetched getCurrStatChartData");
		return resultData;
	}	
}
