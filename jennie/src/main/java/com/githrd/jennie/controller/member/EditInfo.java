package com.githrd.jennie.controller.member;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import com.githrd.jennie.controller.*;
import com.githrd.jennie.dao.*;
import com.githrd.jennie.vo.*;

public class EditInfo implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/member/editInfo";
		
		//로그인 체크
		HttpSession session = req.getSession();
		String sid = (String) session.getAttribute("SID");
		if(sid == null) {
			view = "/whistle/member/login.blp";
			req.setAttribute("isRedirect", true);
			return view;
		}
		
		//데이터베이스에서 내 정보 꺼내오고
		MemberDao mDao = new MemberDao();
		MemberVO mVO = mDao.getIdInfo(sid);
		ArrayList<MemberVO> list = mDao.getAvtList();
		//데이터 심고
		req.setAttribute("DATA", mVO);
		req.setAttribute("LIST", list);
		
		return view;
	}

}
