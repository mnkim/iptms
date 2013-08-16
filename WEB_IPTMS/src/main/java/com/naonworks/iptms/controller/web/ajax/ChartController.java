package com.naonworks.iptms.controller.web.ajax;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.naonworks.iptms.constants.GlobalStored;
import com.naonworks.iptms.service.StatChartService;
import com.naonworks.iptms.util.MyUtil;

@Controller
@RequestMapping("/ajax")
public class ChartController {    
	private static final Logger logger = LoggerFactory.getLogger(ChartController.class);

	@Autowired
	StatChartService statChartService;
	
	//@RequestMapping(value="/chart")
	@RequestMapping(value="/chart", method=RequestMethod.POST)
	public @ResponseBody String getChartData(HttpServletRequest request) {
		 String type = MyUtil.replaceNull(request.getParameter("type"));
		 logger.debug("Recevied /ajax/chart type: " + type);
		 JSONArray jsonRet = new JSONArray();
		 JSONArray jsonArray = null;
		 if (type.equals("CURR")) {
			 //10초 단위 데이터인 경우 
			 jsonArray = GlobalStored.getInstance().chartData;
		 }
		 else if (type.equals("INIT")) {
			 //초기 데이터인 경우 
			 jsonArray = statChartService.getInitStatChartData();			 
		 }
		 else {
			 jsonArray = null;
		 }
		 
		 //데이터 fetch 타입. 
		 JSONObject obj = new JSONObject();
		 obj.put("type",  type);
		 if (jsonArray!=null) {
			 //성공/실패 여부
			 obj.put("success", true);
			 //data에 읽어온 데이터 넣음. 
			 obj.put("data", jsonArray);
			 jsonRet.add(obj);
			 
			 return MyUtil.replaceNull(jsonRet.toString());
		 }
		 else {
			 //실패 여부
			 obj.put("success", false);
			 jsonRet.add(obj);
			 
			 return MyUtil.replaceNull(jsonRet.toString());
		 }
	}
}
