package com.naonworks.iptms.mnkim.config.userAndAccMngm.userMngm.view;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.naonworks.iptms.common.SkipLoginInterceptor;
import com.naonworks.iptms.mnkim.config.userAndAccMngm.userMngm.model.UserInfoVO;
import com.naonworks.iptms.mnkim.config.userAndAccMngm.userMngm.service.UserInfoService;
import com.naonworks.iptms.util.PageNavigationInfo;
import com.naonworks.iptms.util.StringUtils;

@Controller
@SkipLoginInterceptor
public class UserController {    
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Resource(name = "UserInfoService")
	protected UserInfoService userInfoService;
	
	@RequestMapping("/user/userEmpty")
	public ModelAndView userEmpty() {
		ModelAndView mav = new ModelAndView("user/userEmpty");
		return mav; 
	}
	
	@RequestMapping("/user/userSave")
	public ModelAndView userSave ( HttpServletRequest request) throws Exception {
		 	
		String userId = request.getParameter("userId");
		String passwd = request.getParameter("passwd");
		 	
		UserInfoVO userInfoVO = new UserInfoVO();
		
		userInfoVO.setUserId(userId);
		userInfoVO.setPasswd(passwd);
		
		HashMap<String, String> vo = new HashMap();
		vo.put("userId", userId);
		vo.put("passwd", passwd);
		
		userInfoService.insertUserInfo(vo);
		
		// ModelAndView mav = new ModelAndView("user/userList");
		return new ModelAndView(new RedirectView("/user/userList.html"));
	}
	
	@RequestMapping("/user/userList")
	public ModelAndView userList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		
		// 검색조건
		String userId = request.getParameter("strSearch");
		
		int sPageSize =3; // 리스트노출개수
		int sNaviSize = 5 ; // 페이지노출개수
		int sPageNo = StringUtils.nvlIntr(request.getParameter("page_no"), 1 ) ; // 페이지번호(default: 1)
		
		PageNavigationInfo  pageNavigationInfo = new PageNavigationInfo();
		
		Map<String, String> params = new HashMap<String, String>();
		params = pageNavigationInfo.getPageIndex(params, sPageSize, sPageNo);
		params.put("userId", userId);
		
		List<UserInfoVO> userInfoList = userInfoService.selectUserInfoList(params);
		
		/*
		UserInfoVO userInfoVO = new UserInfoVO();
		userInfoVO.setUserId(userId);
		HashMap<String, String> vo = new HashMap();
		vo.put("userId", userId);
		*/
		
		int totalCount = userInfoService.selectUserInfoListCnt(params);
		
		ModelAndView mav = new ModelAndView();
		mav = pageNavigationInfo.getPageNavigation( sPageSize, totalCount , sNaviSize, sPageNo);
		
		mav.addObject("userInfoList", userInfoList);
		mav.addObject("totCnt", totalCount);
		mav.addObject("userId", userId);
		
		
		// 오늘 날짜 / 시간
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
		SimpleDateFormat mHour = new SimpleDateFormat ( "HH", Locale.KOREA );
		SimpleDateFormat mMin = new SimpleDateFormat ( "mm", Locale.KOREA );
		Date currentTime = new Date();
		String date = mSimpleDateFormat.format ( currentTime );
		String hour = mHour.format ( currentTime );
		String min = mMin.format ( currentTime );
		
		// 한시간 전
		StringBuffer sb = new StringBuffer();
		Calendar cl = Calendar.getInstance();
		cl.add(Calendar.HOUR_OF_DAY, -1); // 한시간전 시간 구하기
		String dateVal = new SimpleDateFormat("HH").format( cl.getTime() );

		//System.out.println ( date );
		//System.out.println ( hour );
		//System.out.println ( min );
		//System.out.println ( dateVal );
		
		mav.addObject("start_date", date);
		mav.addObject("finish_date", date);
		
		mav.addObject("f_hour", hour);
		mav.addObject("f_min", min);		
		
		mav.addObject("s_hour", dateVal);
		mav.addObject("s_min", min);
		
		mav.setViewName("user/userList");
		
		return mav; 
	}
	
	@RequestMapping("/user/userDelete")
	public ModelAndView userDelete( HttpServletRequest request ) throws Exception {
		
		String userId = request.getParameter("userId");
		
		HashMap<String, String> vo = new HashMap();
		vo.put("userId", userId);
		
		userInfoService.deleteUserInfo(vo);
		
		// ModelAndView mav = new ModelAndView("user/userList");
		// mav.addObject("userInfoList", userInfoList);
		
		return new ModelAndView(new RedirectView("/user/userList.html"));
	}
	
	@RequestMapping("/user/userUpdate")
	public ModelAndView userUpdate( HttpServletRequest request ) throws Exception {
		
		String userId = request.getParameter("userId");
		String passwd = request.getParameter("passwd");
		
		UserInfoVO userInfoVO = new UserInfoVO();
		
		// System.out.println("userId " + userId);
		// System.out.println("passwd " + passwd);
		
		userInfoVO.setUserId(userId);
		userInfoVO.setPasswd(passwd);
		
		HashMap<String, String> vo = new HashMap();
		vo.put("userId", userId);
		vo.put("passwd", passwd);
		
		userInfoService.updateUserInfo(vo);
		
		// ModelAndView mav = new ModelAndView("user/userList");
		// mav.addObject("userInfoList", userInfoList);
		
		return new ModelAndView(new RedirectView("/user/userList.html"));
	}
	
	
	 /*

	@RequestMapping("/excelExport")
	public void excelExport (Map<String, Object> model, WritableWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
     String fileName = createFileName();
     setFileNameToResponse(request, response, fileName);        
     
     String userId = request.getParameter("strSearch");
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		
		List<UserInfoVO> userInfoList = userInfoService.selectUserInfoList(params);
     
     WritableSheet sheet = workbook.createSheet("설문 목록", 0);
     
     sheet.addCell(new jxl.write.Label(0, 0, "사용자아이디"));
     for(int i=1; i < 13; i++) {
         sheet.addCell(new jxl.write.Label(i, 0, "답변" + i));
     }
     
     int size = userInfoList.size();
     int row = 1;
     for( int i = 0; i < size; i++ )

     {
    	 
    	 sheet.addCell(new jxl.write.Label(0, row, ((UserInfoVO) userInfoList).getUserId()));
         sheet.addCell(new jxl.write.Label(1, row, ((UserInfoVO) userInfoList).getPasswd()));
         row++;
     }


 }
	
	

 private void setFileNameToResponse
    (HttpServletRequest request, HttpServletResponse response, String fileName) {
     String userAgent = request.getHeader("User-Agent");
     if (userAgent.indexOf("MSIE 5.5") >= 0) {
         response.setContentType("doesn/matter");
         response.setHeader("Content-Disposition","filename=\""+fileName+"\"");
     } else {
         response.setHeader("Content-Disposition","attachment; filename=\""+fileName+"\"");
     }
 }

 private String createFileName() {
     SimpleDateFormat fileFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
     return new StringBuilder("설문조사")
         .append("-").append(fileFormat.format(new Date())).append(".xls").toString();
 }


 
protected void buildExcelDocument(Map<String, Object> model,
		WritableWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	// TODO Auto-generated method stub
	
	String fileName = createFileName();
    setFileNameToResponse(request, response, fileName);        
    
    String userId = request.getParameter("strSearch");
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		
		List<UserInfoVO> userInfoList = userInfoService.selectUserInfoList(params);
    
    WritableSheet sheet = workbook.createSheet("설문 목록", 0);
    
    sheet.addCell(new jxl.write.Label(0, 0, "사용자아이디"));
    for(int i=1; i < 13; i++) {
        sheet.addCell(new jxl.write.Label(i, 0, "답변" + i));
    }
    
    int size = userInfoList.size();
    int row = 1;
    for( int i = 0; i < size; i++ )

    {
   	 
   	 sheet.addCell(new jxl.write.Label(0, row, ((UserInfoVO) userInfoList).getUserId()));
        sheet.addCell(new jxl.write.Label(1, row, ((UserInfoVO) userInfoList).getPasswd()));
        row++;
    }
	
}
 */


	
}