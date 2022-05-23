package com.githrd.jennie.controller.reboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.githrd.jennie.controller.BlpInter;
import com.githrd.jennie.dao.ReboardDao;

public class ReboardEditProc implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//로그인 체크
		String sid = (String) req.getSession().getAttribute("SID");
		if(sid == null) {
			req.setAttribute("isRedirect", true);
			return "/whistle/member/login.blp";
		}
		String view = "/reboard/redirect";
		
		//파라미터 꺼내고
		String spage = req.getParameter("nowPage");
		String sno = req.getParameter("bno");
		String body = req.getParameter("body");
		int bno = Integer.parseInt(sno);
		
		ReboardDao rDao = new ReboardDao();
		int cnt = rDao.editReboad(bno, body);
				
		if(cnt == 0) {
			req.setAttribute("VIEW", "/whistle/reboard/reboardEdit.blp");
		} else {
			req.setAttribute("VIEW", "/whistle/reboard/reboardList.blp");
		}
		return view;
	}

}
