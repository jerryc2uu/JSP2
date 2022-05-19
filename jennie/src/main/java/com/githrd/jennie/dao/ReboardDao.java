package com.githrd.jennie.dao;

import java.sql.*;
import java.util.*;

import com.githrd.jennie.db.BlpDBCP;
import com.githrd.jennie.vo.*;
import com.githrd.jennie.sql.*;
import com.githrd.jennie.util.*;

public class ReboardDao {
	private BlpDBCP db;
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private ReboardSQL rSQL;

	public ReboardDao() {
		db = new BlpDBCP();
		rSQL = new ReboardSQL();
	}
	
	//게시글 리스트 조회 전담 처리 함수
	public ArrayList<BoardVO> getList(PageUtil page) {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		
		//커넥션
		con = db.getCon();
		//질의명령
		String sql = rSQL.getSQL(rSQL.SEL_ALL_LIST);
		//명령 전달 도구
		pstmt = db.getPSTMT(con, sql);
		try {
			//질의명령 완성
			pstmt.setInt(1, page.getStartCont());
			pstmt.setInt(2, page.getEndCont());
			//보내고 결과 받고
			rs = pstmt.executeQuery();
			//결과에서 꺼내서 vo에 담고
			while(rs.next()) {
				BoardVO bVO = new BoardVO();
				bVO.setRno(rs.getInt("rno"));
				bVO.setBno(rs.getInt("rbno"));
				bVO.setUpno(rs.getInt("upno"));
				bVO.setMno(rs.getInt("mno"));
				bVO.setId(rs.getString("id"));
				bVO.setBody(rs.getString("body"));
				bVO.setAvatar(rs.getString("savename"));
				bVO.setStep(rs.getInt("step"));
				bVO.setWdate(rs.getDate("wdate"));
				bVO.setWtime(rs.getTime("wdate"));
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
	
	//총 게시글 수 조회 전담 처리함수
	public int getTotalCount() {
		int cnt = 0;
		
		con = db.getCon();
		String sql = rSQL.getSQL(rSQL.SEL_TOTAL_CNT);
		stmt = db.getSTMT(con);
		try {
			rs = stmt.executeQuery(sql);
			rs.next();
			cnt = rs.getInt("cnt");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(stmt);
			db.close(con);
		}
		return cnt;
	}
	
	//작성자 정보 조회 전담 처리 함수
	public BoardVO getWriterInfo(String id) {
		BoardVO bVO = new BoardVO();
		
		//커넥션
		con = db.getCon();
		//질의명령
		String sql = rSQL.getSQL(rSQL.SEL_WRITER_INFO);
		//명령전달도구
		pstmt = db.getPSTMT(con, sql);
		try {
			//질의명령 완성
			pstmt.setString(1, id);
			//보내고 결과 받고
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
}
