package com.githrd.jennie.controller.board;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.githrd.jennie.controller.BlpInter;
import com.githrd.jennie.util.FileUtil;

public class BoardWriteProc implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("isRedirect", true);
		String view = "/whistle/board/boardList.blp";
		
		//글 등록 작업
		
		/*
			form 태그가 스트림 방식으로 전송되는 경우, 
			전송되는 부가정보(파라미터, 파일)를 HttpServletRequest에서
			꺼내는 것이 아니고
			MultipartRequest에서 꺼내 사용해야 한다.
			
			우리는 별도의 유틸리티 클래스를 제작해서
			데이터베이스 작업에 필요한 데이터를 받기로 하자.
		 */
		FileUtil futil = new FileUtil(req, "/resources/upload");
		
		
		return view;
	}

}
