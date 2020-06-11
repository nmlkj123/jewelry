package com.jewel.myPage.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jewel.common.CommandMap;
import com.jewel.myPage.service.MyCartService;
import com.jewel.paging.MyCartPaging;

@Controller
public class MyCartController {
	
	@Resource(name="myCartService")
	private MyCartService myCartService;
	
	@Resource(name="myCartPaging")
	private MyCartPaging myCartPaging;
	
	@RequestMapping(value="/myPage/myCart")
	public ModelAndView myCartList(CommandMap commandMap, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("myCart");
		
		Map<String, Object> map =commandMap.getMap();
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("MEM_ID");		
		commandMap.put("MEM_ID",id);

		String pg=(String) commandMap.get("pg");  
		
		if(map.get("pg")==null|| map.get("pg").equals("")) {
	    	  pg="1";   	  
		}
		
		int show=5;//페이지당 보여줄 상품 개수
    	int block=5;//페이지당 보여줄 페이지개수
    	int endNum = Integer.parseInt(pg)*show;
		int startNum = endNum-(show-1);

		
		commandMap.put("START_NUM", startNum);
		commandMap.put("END_NUM", endNum);
		
    	@SuppressWarnings("unused")
		int totalList =  myCartService.myCartTotal(map);
    	myCartPaging.setPath(request.getContextPath());
    	myCartPaging.setCurrentPage(pg);
    	myCartPaging.setTotalList(totalList);
    	myCartPaging.setPageBlock(block);
    	myCartPaging.setPageSize(show);

    	myCartPaging.makePagingHTML();
		mv.addObject("myCartPaging",myCartPaging);
		
		Map<String, Object> myInfo = myCartService.selectMyInfo(commandMap.getMap());
		mv.addObject("myInfo", myInfo);
		
		List<Map<String, Object>> myCart = myCartService.selectMyCartList(commandMap.getMap());
		mv.addObject("myCart", myCart);
		
		return mv;
			
	}
	
	@RequestMapping(value="/myPage/myCartUpdate", method=RequestMethod.POST)
	public ModelAndView myCartUpdate(CommandMap commandMap, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("MEM_ID");		
		commandMap.put("MEM_ID",id);
		
		myCartService.updateMyCart(commandMap.getMap());
		
		return mv;
	}
	
	@RequestMapping(value="/myPage/myCartDelete")
	public ModelAndView myCartDelete(CommandMap commandMap, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("jsonView");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("MEM_ID");		
		commandMap.put("MEM_ID",id);
		
		myCartService.deleteMyCart(commandMap.getMap());
		
		return mv;
	}
	
}
