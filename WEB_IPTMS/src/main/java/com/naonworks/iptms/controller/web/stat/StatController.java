package com.naonworks.iptms.controller.web.stat;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.naonworks.iptms.mybatis.StatChartDaoImpl;
import com.naonworks.iptms.mybatis.UserDaoImpl;

@Controller
public class StatController {    
	private static final Logger logger = LoggerFactory.getLogger(StatController.class);

	@Resource(name="userDaoImpl")
	private UserDaoImpl userDaoImpl;
	
	@Resource(name="statChartDaoImpl")
	private StatChartDaoImpl statChartDaoImpl;
	
	@RequestMapping("/stat")
	public ModelAndView stat() {
		ModelAndView mav = new ModelAndView("stat/stat");
		return mav; 
	}
	
	@RequestMapping("/stat2")
	public ModelAndView stat2() {
		ModelAndView mav = new ModelAndView("stat/stat2");
		return mav; 
	}
}