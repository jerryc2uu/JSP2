package com.githrd.jennie.controller.member;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.githrd.jennie.controller.BlpInter;
import com.githrd.jennie.dao.*;

public class EditProc implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("isRedirect", true);
		String view = "/whistle/member/myInfo.blp";
		// 로그인 체크
		String sid = (String) req.getSession().getAttribute("SID");
		if(sid == null) {
			return "/whistle/member/login.blp";
		}
		
		//통과한 경우 파라미터 가져오고
		String smno = req.getParameter("mno");
		int mno = Integer.parseInt(smno);
		String pw = req.getParameter("pw");
		String mail = req.getParameter("mail");
		String tel = req.getParameter("tel");
		String sno = req.getParameter("ano");
		
		StringBuffer buff = new StringBuffer();
		
		if(pw != null) {
			buff.append(" , pw = '" + pw + "' ");
		}
		if(mail != null) {
			buff.append(" , mail = '" + mail + "' ");
		}
		if(tel != null) {
			buff.append(" , tel = '" + tel + "' ");
		}
		if(sno != null) {
			buff.append(" , avt = " + sno + " ");
		}
		
		String psql = buff.toString().substring(3); 
		
		//데이터베이스 작업
		MemberDao mDao = new MemberDao();
		int cnt = mDao.editMyInfo(mno, psql);
		
		//결과 따라 처리
		if(cnt != 1) {
			//수정 실패
			view = "/whistle/member/editInfo.blp";
		}
		
		return view;
	}

}
