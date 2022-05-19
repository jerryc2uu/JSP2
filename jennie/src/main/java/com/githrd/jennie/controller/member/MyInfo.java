package com.githrd.jennie.controller.member;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.githrd.jennie.controller.*;
import com.githrd.jennie.dao.*;
import com.githrd.jennie.vo.*;

public class MyInfo implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//로그인 검사
		HttpSession session = req.getSession();
		String sid = (String) session.getAttribute("SID");
		/*
		 	session에는 object 타입으로 저장됨
		 	사용하려면 강제 형변환 필요
			
			내용 채워졌는지만 확인할 거면 형변환 필요 없지만
			세션에 기억시켜놓은 데이터 사용해야 하므로 원래 타입으로 강제 형변환 해준다.
		 */

		String view = "/member/memberInfo";
		
		//로그인 안 된 경우
		if(sid == null) {
			req.setAttribute("isRedirect", true);
			view = "/whistle/member/login.blp";
			return view;
		}
		
		//로그인 된 경우
		MemberDao mDao = new MemberDao();
		MemberVO mVO = mDao.getIdInfo(sid);
		
		req.setAttribute("DATA", mVO);
		
		
		return view;
	}

}
