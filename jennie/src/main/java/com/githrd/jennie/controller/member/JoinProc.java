package com.githrd.jennie.controller.member;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.githrd.jennie.controller.BlpInter;
import com.githrd.jennie.dao.MemberDao;
import com.githrd.jennie.vo.MemberVO;

public class JoinProc implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("isRedirect", true);
		
		String view = "/whistle/main.blp";
		
		if(req.getSession().getAttribute("SID") != null) {
			return view;
		}
		
		// 파라미터 받고
		String name = req.getParameter("name");
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String mail = req.getParameter("mail");
		String tel = req.getParameter("tel");
		String gen = req.getParameter("gen");
		String sno = req.getParameter("ano");
		int ano = Integer.parseInt(sno);
		// vo에 담고
		MemberVO mVO = new MemberVO();
		mVO.setName(name);
		mVO.setId(id);
		mVO.setPw(pw);
		mVO.setMail(mail);
		mVO.setTel(tel);
		mVO.setGen(gen);
		mVO.setAno(ano);
		// db 작업하고
		MemberDao mDao = new MemberDao();
		int cnt = mDao.addMember(mVO);
		// 결과값에 따라 처리하고
		if(cnt != 1) {
			view = "/whistle/member/join.blp";
		} else {
			//로그인 처리
			req.getSession().setAttribute("SID", id);
		}
		return view;
	}

}
