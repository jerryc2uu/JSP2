package com.githrd.jennie.controller.member;

import java.io.IOException;

import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.githrd.jennie.controller.*;
import com.githrd.jennie.dao.*;
import com.githrd.jennie.vo.*;

public class JoinForm implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/member/join";
		
		//세션 검사
		//HttpSession session = req.getSession();
		if(req.getSession().getAttribute("SID") != null) {
			//로그인 한 경우
			//리다이렉트 셋팅
			req.setAttribute("isRedirect", true);
			//url 반환
			return "/whistle/main.blp";
		}
		
		MemberDao mDao = new MemberDao();
		ArrayList<MemberVO> list = mDao.getAvtList();
		
		//데이터 심고
		req.setAttribute("LIST", list); //파라미터는 set 함수 없므
		
		//뷰 부르고
		return view;
	}
	
	//forward : 요청객체 유지, 
}
