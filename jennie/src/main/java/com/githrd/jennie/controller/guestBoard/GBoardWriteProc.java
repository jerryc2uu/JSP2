package com.githrd.jennie.controller.guestBoard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.githrd.jennie.controller.BlpInter;
import com.githrd.jennie.dao.GBoardDao;

public class GBoardWriteProc implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 결과에 상관없이 redirect 돼야 한다
		req.setAttribute("isRedirect", true);
		String view = "/whistle/guestBoard/gBoardList.blp";
		
		//할일
		//세션 검사
		String sid = (String) req.getSession().getAttribute("SID");
		if(sid == null) {
			return "/whistle/member/login.blp";
		}
		//파라미터 꺼내고
		String sno = req.getParameter("mno");
		int mno = Integer.parseInt(sno);
		String spage = req.getParameter("nowPage");
		String body = req.getParameter("body");
		//글 등록 여부 검사
		GBoardDao gDao = new GBoardDao();
		int cnt = gDao.getWriteCount(sid);
		
		//현재 보는 페이지를 요청 객체에 등록
		req.setAttribute("NOWPAGE", spage);
		if(cnt == 1) {
			/*
			 	<GET 방식>
			return "/whistle/guestBoard/gBoardList.blp?nowPage=" + spage;
			 */
			
			//POST 방식 - redirect용 jsp 파일 이용
			req.setAttribute("isRedirect", false);
			
			//불러올 뷰를 요청 객체에 등록
			req.setAttribute("VIEW", "/whistle/guestBoard/gBoardList.blp");
			return "/guestBoard/redirect";
		}
		
		//데이터베이스 작업하고 결과 받고
		int result = gDao.addGBoard(sid, body);
		//결과에 따라서 뷰 작성
		if(result == 0) {
			/*
			//글 등록에 실패한 경우
			return "/whistle/guestBoard/gBoardWrite.blp?nowPage=" + spage;
			*/
			//POST 방식 - redirect용 jsp 파일 이용
			req.setAttribute("isRedirect", false);
			req.setAttribute("VIEW", "/whistle/guestBoard/gBoardWrite.blp");
			return "/guestBoard/redirect";
		}
		//뷰 반환
		return view;
	}

}
