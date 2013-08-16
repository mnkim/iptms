package com.naonworks.iptms.mnkim.login.view;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.naonworks.iptms.common.CommonUtil;
import com.naonworks.iptms.common.MD5Encoding;
import com.naonworks.iptms.common.SkipLoginInterceptor;
import com.naonworks.iptms.mnkim.login.model.LoginVO;
import com.naonworks.iptms.mnkim.login.service.LoginService;


@Controller
public class LoginController {
	
	@Resource(name = "LoginService")
	protected LoginService loginService;
	 
	/**
	 * 
	 * <pre>
	 * 설명 : 로그인 페이지  
	 * </pre>
	 * 
	 *@return
	 *@throws Exception
	 */
	@RequestMapping("/login/login")
	public String login() throws Exception {
	
		
	    return "login/login";
	
	}
	/**
	 * 
	 * <pre>
	 * 설명 : 로그인 처리  
	 * </pre>
	 * 
	 *@param vo
	 *@param request
	 *@param response
	 *@param command
	 *@return
	 *@throws Exception
	 */
	@RequestMapping("/login/loginAjax") 
	public @ResponseBody String login(@ModelAttribute("loginVO")LoginVO vo, HttpServletRequest request
			, HttpServletResponse response, Object command) throws Exception{
		
		String result = "";
		
		// String jsonData = "" ; 
		// vo.setPasswd(MD5Encoding.md5(vo.getPasswd()));//비밀번호 인코딩
		// vo.setPasswd(vo.getPasswd());
		
		LoginVO info = (LoginVO) loginService.selectLogin(vo); 
		
		if(info!=null){
			System.out.println ( "login2" );
			HttpSession session = request.getSession();
			
			session.setAttribute("LOGIN_USER_ID", info.getUserId());
			session.setAttribute("LOGIN_USER_NAME", info.getPasswd());
			
			//jsonData = CommonUtil.convertObjectToJson(info) ; 
			result = "success";
		}else{
			result = "fail";
		}
		
		
		return result;
	}
	 
	         
	/**
	 * <pre>
	 * 설명 : 로그아웃 
	 * </pre>
	 * 
	 *@param request
	 *@param response
	 *@param command
	 *@return
	 *@throws IOException
	 */
	@RequestMapping("/login/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) throws IOException{ 
        HttpSession session = request.getSession();
        session.invalidate(); 
        return "redirect:/login/login.html";
	}
	 
}
