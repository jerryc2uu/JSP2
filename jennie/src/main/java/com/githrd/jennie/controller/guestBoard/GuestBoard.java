package com.githrd.jennie.controller.guestBoard;

import java.io.*;
import java.util.*;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.githrd.jennie.controller.*;
import com.githrd.jennie.dao.*;
import com.githrd.jennie.util.PageUtil;
import com.githrd.jennie.vo.*;

public class GuestBoard implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/guestBoard/gBoardList";
		
		//현재 보고 있는 페이지 
		String spage = req.getParameter("nowPage");
		int nowPage = 1;
		if(spage != null) {
			nowPage = Integer.parseInt(spage);
		}
		
		GBoardDao gDao = new GBoardDao();
		
		//총 게시글 수 조회
		int total = gDao.getTotal();
		
		//페이지 객체 만들고
		PageUtil page = new PageUtil(nowPage, total);
		
		//데이터베이스에서 게시글 리스트 가져오고
		ArrayList<BoardVO> list = gDao.getGBoardList(page);
		int cnt = 0;
		String sid = (String) req.getSession().getAttribute("SID");
		
		if(sid != null) {
			cnt = gDao.getWriteCount(sid);			
		}
		
		//뷰에 데이터 심고
		req.setAttribute("LIST", list);
		req.setAttribute("CNT", cnt);
		req.setAttribute("PAGE", page);
		
		//뷰 부르고
		return view;
	}

}
