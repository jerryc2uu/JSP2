package com.githrd.jennie.controller.reboard;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.githrd.jennie.controller.BlpInter;
import com.githrd.jennie.dao.*;
import com.githrd.jennie.util.PageUtil;
import com.githrd.jennie.vo.*;

public class ReboardList implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/reboard/reboardList";
		//할일
		//파라미터 꺼내고
		int nowPage = 1;
		String spage = req.getParameter("nowPage");
		if(spage != null) {
			nowPage = Integer.parseInt(spage);
		}
		
		String msg = req.getParameter("msg");
		
		//총 게시글 수
		ReboardDao rDao = new ReboardDao();
		int total = rDao.getTotalCount();
		
		PageUtil page = new PageUtil(nowPage, total);
		
		ArrayList<BoardVO> list = rDao.getList(page);		
		
		//데이터 심고
		req.setAttribute("LIST", list);
		req.setAttribute("PAGE", page);
		if(msg != null) {			
			req.setAttribute("MSG", msg);
		}
		//뷰 부르고
		return view;
	}

}
