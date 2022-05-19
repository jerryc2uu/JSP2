package com.githrd.jennie.controller.member;

import java.io.*;



import javax.servlet.*;
import javax.servlet.http.*;

import com.githrd.jennie.controller.*;
import com.githrd.jennie.dao.*;

public class LoginProc implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/*
			여기에서 작업은 로그인 처리 결과에 상관없이
			요청이 새로 생겨나야 한다.
			로그인 처리에 성공한 경우에는
				main.blp로 다시 요청
			로그인 실패한 경우에는
				/memeber/login.blp를 다시 요청
			
			 만약 이미 로그인한 경우 /main.blp로 다시 요청
			 
			 위에서 살폈뜻이 어떤 상황에서든 요청 새롭게 만들어야 한다.
			 따라서 리다이렉트 되어야 한다.
		 */
		req.setAttribute("isRedirect", true);
		String view = "/whistle/main.blp";

		//이미 로그인 한 상태
		if(req.getSession().getAttribute("SID") != null) {
		
			return view;
		}
		
		//아직 로그인 안 된 상태
		//할일
		//파라미터 받고 (파라미터 키값은 input 태그의 name 속성값)
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		
		//데이터베이스 작업하고 결과 받고
		MemberDao mDao = new MemberDao();
		int cnt = mDao.getLogin(id, pw);
		
		//결과 따라서 처리
		if(cnt == 1) {
			//아이디와 비번 일치하는 회원이 1명 존재한다는 것이므로
			//로그인 처리함
			req.getSession().setAttribute("SID", id);
			//세션에 데이터 기록했으면 메인 페이지로 돌려보낸다.
			//메인페이지로 돌려보내는 뷰는 위에서 만들어놨으니 그냥 사용
		} else {
			//정보 틀렸거나 없는 회원, 로그인 처리 안 함
			view = "/whistle/member/login.blp";
		}
		
		return view;
	}

}
