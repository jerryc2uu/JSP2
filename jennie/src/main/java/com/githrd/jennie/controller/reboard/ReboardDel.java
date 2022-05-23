package com.githrd.jennie.controller.reboard;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.githrd.jennie.controller.BlpInter;
import com.githrd.jennie.dao.*;

public class ReboardDel implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/reboard/redirect";
		
		//할일
		//파라미터 받고
		String sno = req.getParameter("bno");
		String spage = req.getParameter("nowPage");
		int rbno = Integer.parseInt(sno);
		
		//데이터베이스 작업
		ReboardDao rDao = new ReboardDao();
		int cnt = rDao.delReboard(rbno);
		
		req.setAttribute("VIEW", "/whistle/reboard/reboardList.blp");
		req.setAttribute("NOWPAGE", spage);
		if(cnt == 0) {
			//실패
			req.setAttribute("MSG", sno + " 번 글, 삭제 작업에 실패했습니다.");			
		} else {
			req.setAttribute("MSG", sno + " 번 글, 삭제 작업에 성공했습니다.");
		}
		
		return view;
	}

}
