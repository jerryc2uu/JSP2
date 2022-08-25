package com.githrd.jennie.controller.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.githrd.jennie.controller.BlpInter;
import com.githrd.jennie.dao.BoardDao;
import com.githrd.jennie.util.FileUtil;
import com.githrd.jennie.vo.BoardVO;
import com.githrd.jennie.vo.FileVO;
import com.oreilly.servlet.MultipartRequest;

public class BoardWriteProc implements BlpInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("isRedirect", true);
		String view = "/whistle/board/boardList.blp";
		String sid = (String) req.getSession().getAttribute("SID");
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
		MultipartRequest multi = futil.getMulti();
		
		/*
		 	이 클래스에서는 다른 일반 컨트롤러와 다른 스트림 방식으로
		 	전달된 데이터를 처리해야 한다.
		 	
		 	일반 컨트롤러에서는 파라미터 꺼낼 때
		 	request 객체에서 꺼냈지만
		 	이 클래스처럼 MultipartRequest 방식으로 전달되는 데이터는
		 	request 객체에서 파라미터를 꺼낼 수 없다.
		 	이 때는 MultipartRequest 객체에서 파라미터를 꺼내 사용해야 한다.
		 	
		 	[방법]
		 		multi.getPratmeter("키값");
		 */
		String title = multi.getParameter("title");
		String body = multi.getParameter("body");
		ArrayList<FileVO> list = futil.getList();
		
		BoardVO bVO = new BoardVO();
		bVO.setId(sid);
		bVO.setTitle(body);
		bVO.setBody(body);
		bVO.setList(list);
		
		BoardDao bDao = new BoardDao();
		int cnt = bDao.insertBoardData(bVO);
		
		if(cnt == -1 || cnt != bVO.getList().size()) {
			view = "/whistle/board/boardWrite.blp?nowPage=" + multi.getParameter("nowPage");
		} else {
			view = "/whistle/board/boardList.blp";
		}
		return view;
	}

}
