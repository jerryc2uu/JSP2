package com.githrd.jennie.controller.reboard;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.githrd.jennie.controller.*;
import com.githrd.jennie.dao.*;
import com.githrd.jennie.vo.*;

public class ReboardComment implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/reboard/reboardComment";	
		//할일
		//로그인 체크
		String sid = (String) req.getSession().getAttribute("SID");
		//파라미터 받고
		if(sid == null) {
			req.setAttribute("isRedirect", true);
			return "/whistle/member/login.blp";
		}
		
		//파라미터 받고
		String sno = req.getParameter("bno");
		int bno = Integer.parseInt(sno);
		String spage = req.getParameter("nowPage");
		//db 조회
		ReboardDao rDao = new ReboardDao();
		BoardVO bVO = rDao.getReboardInfo(bno, sid);
		//데이터 심고
		System.out.println(bVO);
		req.setAttribute("DATA", bVO);
		//뷰 부르고
		return view;
	}

}
