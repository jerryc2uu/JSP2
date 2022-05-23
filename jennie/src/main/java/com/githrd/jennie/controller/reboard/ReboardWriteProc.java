package com.githrd.jennie.controller.reboard;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.githrd.jennie.controller.BlpInter;
import com.githrd.jennie.vo.*;
import com.githrd.jennie.dao.*;

public class ReboardWriteProc implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("isRedirect", true);
		String view = "/whistle/reboard/reboardList.blp";
		
		//할일
		//로그인 체크
		String sid = (String) req.getSession().getAttribute("SID");
		if(sid == null) {
			view = "/whistle/member/login.blp";
			return view;
		}
		
		//파라미터 꺼내고
		String sno = req.getParameter("mno");
		String spage = req.getParameter("nowPage");
		String body = req.getParameter("body");
		String supno = req.getParameter("upno");
		
		BoardVO bVO = new BoardVO();
		bVO.setMno(Integer.parseInt(sno));
		bVO.setBody(body);
		
		if(supno != null) {
			bVO.setUpno(Integer.parseInt(supno));
		}
		
		//db 작업
		ReboardDao rDao = new ReboardDao();
		int cnt = rDao.addReboard(bVO);
		
		req.setAttribute("NOWPAGE", spage);

		//결과 따라 처리
		if(cnt == 0 && supno == null) {
			//원글 등록 실패한 경우
			//view = "/whistle/reboard/reboardWrite.blp?nowPage=" + spage; //get 방식
			
			//post 방식
			req.setAttribute("isRedirect", false);
			req.setAttribute("VIEW", "/whistle/reboard/reboardWrite.blp");
			view = "/reboard/redirect";
		} else if(cnt == 0 && supno != null) {
			//댓글 등록 실패한 경우
			req.setAttribute("isRedirect", false);
			req.setAttribute("VIEW", "/whistle/reboard/reboardComment.blp");
			view = "/reboard/redirect";
		
		} else if(cnt == 1 && supno != null) {
			//댓글 등록 성공한 경우
			req.setAttribute("isRedirect", false);
			req.setAttribute("VIEW", "/whistle/reboard/reboardList.blp");
			view = "/reboard/redirect";			
		}
		
		return view;
	}

}
