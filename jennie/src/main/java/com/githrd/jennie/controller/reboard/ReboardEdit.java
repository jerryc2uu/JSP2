package com.githrd.jennie.controller.reboard;

import java.io.IOException;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.githrd.jennie.controller.BlpInter;
import com.githrd.jennie.dao.*;
import com.githrd.jennie.vo.*;

public class ReboardEdit implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/reboard/reboardEdit";
		//할일
		String sid = (String) req.getSession().getAttribute("SID");
		
		//로그인 안 된 경우
		if(sid == null) {
			req.setAttribute("isRedirect", true);
			return "/whistle/member/login.blp";
		}
		
		//파라미터 꺼내고
		String sno = req.getParameter("bno");
		int bno = Integer.parseInt(sno);
		
		ReboardDao rDao = new ReboardDao();
		BoardVO bVO = rDao.getEditData(bno, sid);
		
		//데이터 심고
		req.setAttribute("DATA", bVO);
		
		return view;
	}

}
