package com.naonworks.iptms.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

public class PageNavigationInfo {
	
	/**
	 * 목록을 페이지 단위로 조회시 쿼리에 사용될 변수의 begin, end 인덱스를 구한다.
	 *
	 * @param mParam
	 * @param sPageSize
	 * @param sPageNo
	 * @return
	 * @throws Exception
	 */ 
	public Map<String, String> getPageIndex(Map<String, String> mParam, int sPageSize, int sPageNo) throws Exception {
		return getPageIndex(mParam, sPageSize, sPageNo, "begin", "end");
	}

	/**
	 * 목록을 페이지 단위로 조회시 쿼리에 사용될 변수의 begin, end 인덱스를 구한다.
	 *
	 * @param mParam
	 * @param sPageSize
	 * @param sPageNo
	 * @param sBeginPropName
	 * @param sEndPropName
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, String> getPageIndex(Map mParam, int sPageSize, int sPageNo, String sBeginPropName, String sEndPropName) throws Exception {
		int iPageSize = sPageSize;
		int iPageNo = sPageNo;
		mParam.put(sBeginPropName, (iPageNo - 1) * iPageSize);
		mParam.put(sEndPropName, iPageNo * iPageSize);
		return mParam;
	}

	/**
	 * 리스트하단에 표현될 페이지 리스트 정보(first, prev, page_list, next, last)를
	 * 생성해서 ModelAndView 객체에 담아 리턴한다.
	 *
	 * @param mParam
	 * @param sPageSize
	 * @param sPageNo
	 * @param sBeginPropName
	 * @param sEndPropName
	 * @return
	 * @throws Exception
	 */
	public ModelAndView getPageNavigation(int sPageSize, int totalCount, int sNaviSize, int sPageNo) throws Exception {
		return getPageNavigation(sPageSize, totalCount, 10 , sPageNo, "page_navi");
	}

	/**
	 * 리스트하단에 표현될 페이지 리스트 정보(first, prev, page_list, next, last)를
	 * 생성해서 ModelAndView 객체에 담아 리턴한다.
	 *
	 * @param mav
	 * @param sPageSize
	 * @param totalCount
	 * @param sNaviSize
	 * @param sPageNo
	 * @param sNavPropName
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ModelAndView getPageNavigation( int sPageSize, int totalCount, int sNaviSize, int sPageNo, String sNavPropName) throws Exception {
		//if (totalCount < 1)	return mav;

		int iPageSize = sPageSize; //리스트페이지 사이즈
		int iNaviSize = sNaviSize; //리스트 사이즈
		int iPageNo = sPageNo; //페이지 번호
		int iPrev = iPageNo-((iPageNo%iNaviSize==0)?iNaviSize:iPageNo%iNaviSize);
		int iLast = (totalCount%iPageSize>0)?1+totalCount/iPageSize:totalCount/iPageSize;
		int iNext = iPrev+iNaviSize>iLast?iLast:iPrev+iNaviSize+1;

		Map mPageNavigation = new HashMap();
		if (iPrev > 1) {
			mPageNavigation.put("first", 1);
			mPageNavigation.put("prev", iPrev);
		}
		if (iLast > iNaviSize && iNext <= (iLast - iLast%iNaviSize + 1)) {
			mPageNavigation.put("next", iNext);
			mPageNavigation.put("last", iLast);
		}

		int iEndLoop = iNext<iPrev+iNaviSize?iNext+1:iNext;
		List lData = new ArrayList();
		for (int i = iPrev+1; i > iPrev && i < iEndLoop; i++) lData.add(i);

		mPageNavigation.put("page_list", lData);

		//logger.debug("[" + sClassName + "]mPageNavigation=" + mPageNavigation);
		ModelAndView mav = new ModelAndView();
		mav.addObject("page_size", sPageSize);
		mav.addObject("navi_size", sNaviSize);
		mav.addObject("page_no", sPageNo);
		mav.addObject("maxCnt", totalCount - (sPageSize * (sPageNo-1)));
		mav.addObject(sNavPropName, new Gson().toJson(mPageNavigation));
		return mav;
	}

}
