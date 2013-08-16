package com.naonworks.iptms.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;

import com.naonworks.iptms.mybatis.StatChartDaoImpl;
import com.naonworks.iptms.vo.AbstractBean;

@Service
public class StatChartService {
	private static final Logger logger = LoggerFactory.getLogger(StatChartService.class);
	
	@Resource(name="statChartDaoImpl")
	private StatChartDaoImpl statChartDaoImpl;

	/**
	 * 초기 차트 데이터를 읽어 온다. 
	 * @return JSONArray 초기 차트 데이터
	 */
	public JSONArray getInitStatChartData() {
		return convertToJson(statChartDaoImpl.getInitStatChartData());
	}
	
	/**
	 * 10초 주기 차트 데이터를 읽어 온다. 
	 * @return JSONArray 10초 주기 차트 데이터를 읽어 온다.
	 */
	public JSONArray getCurrStatChartData() {
		return convertToJson(statChartDaoImpl.getCurrStatChartData());
	}
	
	/**
	 * 수신 받은 차트데이터를 JSON 형태로 변환한다
	 * @return JSONArray로 변환
	 */
	public JSONArray convertToJson(List<? extends AbstractBean> toConvert) {
		return JSONArray.fromObject(toConvert);
	}
}
