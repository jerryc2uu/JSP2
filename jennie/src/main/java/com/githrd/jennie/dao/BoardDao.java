package com.githrd.jennie.dao;

import java.sql.*;
import java.util.*;
import java.util.Date;

import com.githrd.jennie.db.*;
import com.githrd.jennie.sql.*;
import com.githrd.jennie.util.PageUtil;
import com.githrd.jennie.vo.*;

public class BoardDao {
	private BlpDBCP db;
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private BoardSQL bSQL;
	
	public BoardDao() {
		db = new BlpDBCP();
		bSQL = new BoardSQL();
	}
	
	// 단일 파일 정보 데이터베이스 입력 전담 처리함수
	public int addFileInfo(FileVO fVO) {
		int cnt = 0;
		con = db.getCon();
		String sql = bSQL.getSQL(bSQL.INSERT_FILEINFO);
		pstmt = db.getPSTMT(con, sql);
		try {
			pstmt.setString(1, fVO.getId());
			pstmt.setString(2, fVO.getOriname());
			pstmt.setString(3, fVO.getSavename());
			pstmt.setString(4, fVO.getDir());
			pstmt.setLong(5, fVO.getLen());
			
			cnt = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt);
			db.close(con);
		}
		return cnt;
	}
	
	// 다중파일 데이터베이스 입력 전담 처리 함수
	public int addFileList(ArrayList<FileVO> list) {
		int cnt = 0;
		for(FileVO fVO : list) {
			cnt += addFileInfo(fVO);
		}
		
		return cnt;
	}
	
	// 게시글 정보입력 데이터베이스 작업 전담 처리함수
	public int addBoard(BoardVO bVO) {
		int cnt = 0;
		con = db.getCon();
		String sql = bSQL.getSQL(bSQL.INSERT_BOARD);
		pstmt = db.getPSTMT(con, sql);
		try {
			pstmt.setString(1, bVO.getId());
			pstmt.setString(2, bVO.getTitle());
			pstmt.setString(3, bVO.getBody());
			
			cnt = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt);
			db.close(con);
		}
		return cnt;
	}
	
	// 게시판 데이터베이스 등록 전담 처리함수
	public int insertBoardData(BoardVO bVO) {
		int cnt = 0;
		cnt = addBoard(bVO);
		if(cnt != 1) {
			return cnt;
		}
		cnt = addFileList(bVO.getList());
		if(cnt != bVO.getList().size()) {
			return -1;
		}
		return cnt;
	}
	

	public int getTotalCount() {
		int cnt = 0;
		con = db.getCon();
		String sql = bSQL.getSQL(bSQL.SEL_TOTAL_COUNT);
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
	
	//파일 게시판 게시물 리스트 보기
	public ArrayList<BoardVO> getBoardList(PageUtil page){
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		con = db.getCon();
		String sql = bSQL.getSQL(bSQL.SEL_BOARD_LIST);
		pstmt = db.getPSTMT(con, sql);
		try {
			pstmt.setInt(1, page.getStartCont());
			pstmt.setInt(2, page.getEndCont());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardVO bVO = new BoardVO();
				bVO.setBno(rs.getInt("bno"));
				bVO.setId(rs.getString("id"));
				bVO.setTitle(rs.getString("title"));
				bVO.setWdate(rs.getDate("wdate"));
				bVO.setClick(rs.getInt("click"));
				bVO.setCnt(rs.getInt("cnt"));
				
				list.add(bVO);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(stmt);
			db.close(con);
		}
		return list;
	}
	
	// 파일 게시판 게시글 상세보기 데이터조회 전담 처리함수
		public BoardVO getBoardDetail(int bno) {
			BoardVO bVO = new BoardVO();
			ArrayList<FileVO> list = new ArrayList<FileVO>();
			bVO.setList(list);
			con = db.getCon();
			String sql = bSQL.getSQL(bSQL.SEL_BOARD_DETAIL);
			pstmt = db.getPSTMT(con, sql);
			try {
				pstmt.setInt(1, bno);
				
				rs = pstmt.executeQuery();
				while(rs.next()) {
					if(rs.isFirst()) {
						bVO.setBno(rs.getInt("bno"));
						bVO.setId(rs.getString("id"));
						bVO.setTitle(rs.getString("title"));
						bVO.setBody(rs.getString("body").replaceAll("\r\n", "<br>"));
						bVO.setWdate(rs.getDate("wdate"));
						bVO.setWtime(rs.getTime("wdate"));
						bVO.setClick(rs.getInt("click"));
					}
					
					FileVO fVO = new FileVO();
					fVO.setFno(rs.getInt("fno"));
					fVO.setOriname(rs.getString("oriname"));
					fVO.setSavename(rs.getString("savename"));
					fVO.setDir(rs.getString("dir"));
					list.add(fVO);
				}
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				db.close(rs);
				db.close(pstmt);
				db.close(con);
			}
			return bVO;
		}
}