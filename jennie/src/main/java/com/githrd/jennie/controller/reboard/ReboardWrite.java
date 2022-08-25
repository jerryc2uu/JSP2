package com.githrd.jennie.controller.reboard;

import java.io.IOException;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.githrd.jennie.controller.BlpInter;
import com.githrd.jennie.dao.*;
import com.githrd.jennie.vo.*;

public class ReboardWrite implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/reboard/reboardWrite";

		//로그인 체크
		String sid = (String) req.getSession().getAttribute("SID");
		if(sid == null) {
			req.setAttribute("isRedirect", true);
			view = "/whistle/member/login.blp";
			return view;
		}
		
		//로그인한 경우 db에서 데이터 꺼내온다.
		ReboardDao rDao = new ReboardDao();
		BoardVO bVO = rDao.getWriterInfo(sid);
		
		//데이터 심고
		req.setAttribute("DATA", bVO);
		
		return view;
	}

}
