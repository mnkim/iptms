package com.naonworks.iptms.common;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.RedirectView; 
/**
 * 
 * <pre>
 * 설명 : Controller 호출 시마다 로그인 정보 처리를 위한 인터셉터 클래스.
 * </pre>
 * 
 * @author 개발팀 오지은
 * @since 2012. 5. 8.
 * @version 0.1
 * <pre>
 * &lt;&lt; 개정이력(Modification Information) &gt;&gt;
 *   
 *   수정일                    수정자           수정내용
 *  ------------  --------  ---------------------------
 *   2012. 5. 8.       오지은          최초 생성
 * 
 * </pre>
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session = request.getSession(); 
		
		String context = request.getContextPath();
		// @SkipLoginInterceptor 적용 여부 체크
        SkipLoginInterceptor chkController = handler.getClass().getAnnotation(SkipLoginInterceptor.class);

        // @SkipLoginInterceptor 없는 경우 로그인 체크
        if(chkController==null){
        
	        // 세션이 존재하지 않는 경우
			if (session.getAttribute("LOGIN_USER_ID") ==null) {
				throw new ModelAndViewDefiningException(new ModelAndView(new RedirectView(context + "/login/login.html"))); 
			} else{
				return true;
			}
        }
		return true;
       
	}
	
}




