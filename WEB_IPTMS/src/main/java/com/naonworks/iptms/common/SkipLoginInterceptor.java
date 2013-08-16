package com.naonworks.iptms.common;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/**
 * 
 * <pre>
 * 설명 : 로그인 인터셉터 작동 중 비로그인 시 예외를 발생하여 작업 중단할 때 사용하는 예외 클래스 정의. 
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
@Retention(RetentionPolicy.RUNTIME)
public @interface SkipLoginInterceptor {

}
