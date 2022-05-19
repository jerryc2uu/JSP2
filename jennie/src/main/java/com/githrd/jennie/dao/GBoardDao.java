package com.githrd.jennie.dao;

/*
 * 이 클래스는 방명록 관련 데이터베이스 작업을 전담해서 처리할 클래스
 * @author	박소연
 * @since	2022/05/17
 * @version	v.1.0
 * 	
 * 			[작업이력]
 * 				2022/05/17 - 담당자 : 박소연
 * 							 클래스 제작
 * 							 리스트 가져오기 전담 처리 함수 제작
 */
import java.sql.*;
import java.util.*;
import java.util.Date;

import com.githrd.jennie.db.*;
import com.githrd.jennie.sql.*;
import com.githrd.jennie.util.PageUtil;
import com.githrd.jennie.vo.*;

public class GBoardDao {
	
	private BlpDBCP db;
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private GBoardSQL gSQL;
	
	public GBoardDao() {
		//커넥션 풀찾고
		db = new BlpDBCP();
		//질의명령
		gSQL = new GBoardSQL();
	}
	
	// 게시글 리스트 가져오기 전담 처리 함수
	public ArrayList<BoardVO> getGBoardList(PageUtil page) {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		
		//커넥션
		con = db.getCon();
		//질의명령
		String sql = gSQL.getSQL(gSQL.SEL_GBRD_LIST);
		//명령 전달 도구
		pstmt = db.getPSTMT(con, sql);
		try {
			//질의명령 완성(페이징처리)
			pstmt.setInt(1, page.getStartCont());
			pstmt.setInt(2, page.getEndCont());
			//질의명령 보내고 결과 받고
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//결과 꺼내서 vo에 담고
				BoardVO bVO = new BoardVO();
				int rno = rs.getInt("rno");
				int bno = rs.getInt("gno");
				String id = rs.getString("id");
				String body = rs.getString("body");
				String avatar = rs.getString("savename");
				Date wdate = rs.getDate("wdate");
				Time wtime = rs.getTime("wdate");
				
				//vo에 채우고
				bVO.setRno(rno);
				bVO.setBno(bno);
				bVO.setId(id);
				bVO.setBody(body.replaceAll("\r\n", "<br>"));
				bVO.setAvatar(avatar);
				bVO.setWdate(wdate);
				bVO.setWtime(wtime);
				bVO.setSdate();

				//vo list에 담고
				list.add(bVO);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		//list 반환
		return list;
	}
	
	//작성한 게시글 수 조회 전담 처리 함수
	public int getWriteCount(String id) {
		int cnt = 0;
		
		// 커넥션
		con = db.getCon();
		//질의명령
		String sql = gSQL.getSQL(gSQL.SEL_WRITE_CNT);
		//명령 전달 도구
		pstmt = db.getPSTMT(con, sql);
		try {
			//질의명령 완성
			pstmt.setString(1, id);
			//보내고 결과 받고
			rs = pstmt.executeQuery();
			//데이터 꺼내고
			rs.next();
			cnt = rs.getInt("cnt");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		
		//데이터 반환
		return cnt;
	}
	
	//총 게시글 수 조회 전담 처리 함수
	public int getTotal() {
		int cnt = 0;
		//커넥션
		con = db.getCon();
		//질의명령
		String sql = gSQL.getSQL(gSQL.SEL_TOTAL_CNT);
		//명령전달도구
		stmt = db.getSTMT(con);
		try {
			//질의명령 보내고 결과 받고
			rs = stmt.executeQuery(sql);
			//결과 꺼내서 변수에 담고
			rs.next();
			cnt = rs.getInt("cnt");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(stmt);
			db.close(con);
		}
		
		//결과 반환해주고
		return cnt;
	}
	
	//작성자 정보 조회 전담 처리 함수
	public BoardVO getWriteInfo(String id) {
		BoardVO bVO = new BoardVO();
		
		//커넥션
		con = db.getCon();
		//질의명령
		String sql = gSQL.getSQL(gSQL.SEL_WRITER_INFO);
		//명령 전달 도구
		pstmt = db.getPSTMT(con, sql);
		
		try {
			//질의명령 완성
			pstmt.setString(1, id);
			//질의명령 보내고 결과 받고
			rs = pstmt.executeQuery();
			//데이터 꺼내서 VO에 담고
			rs.next();
			bVO.setMno(rs.getInt("mno"));
			bVO.setAvatar(rs.getString("savename"));
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);	
		}
		
		//VO 반환
		return bVO;
	}
	
	// 게시글 데이터베이스 등록 전담 처리함수
	public int addGBoard(String id, String body) {
		//반환값 변수
		int cnt = 0;
		
		//커넥션
		con = db.getCon();
		//질의명령
		String sql = gSQL.getSQL(gSQL.INSERT_GBOARD);
		//명령전달도구
		pstmt = db.getPSTMT(con, sql);
		try {
			//질의명령 완성
			pstmt.setString(1, id);
			pstmt.setString(2, body);
			//질의명령 보내고 결과 받고
			cnt = pstmt.executeUpdate();

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt);
			db.close(con);
		}
		
		//데이터 반환
		return cnt;
	}
}
