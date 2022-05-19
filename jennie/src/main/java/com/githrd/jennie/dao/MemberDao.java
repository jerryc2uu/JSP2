package com.githrd.jennie.dao;

import java.sql.*;



import java.util.*;

import com.githrd.jennie.db.*;
import com.githrd.jennie.sql.*;
import com.githrd.jennie.vo.*;

/*
 *	이 클래스는 회원 관련 데이터베이스 작업을 전담해서 처리하는 클래스 
 *	@author	박소연
 *	@since	2022/05/12
 *	@version v.1.0
 *	
 *	[작업이력]
 *		2022/05/12 - 클래스 제작
 *					 담당자 : 박소연
 */
public class MemberDao {

	/*
	 	이 클래스가 new 되는 순간 db 작업할 준비가 돼야 한다.
	 	커넥션 풀을 찾아내서 커넥션을 꺼내올 준비가 돼 있어야 한다.
	 */
	
	private BlpDBCP db;
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private MemberSQL mSQL;
	public MemberDao() {
		db = new BlpDBCP();
		mSQL = new MemberSQL();
	}
	
	//로그인 데이터베이스 작업 전담 처리 함수
	public int getLogin(String id, String pw) {
		
		int cnt = 0;
		
		//커넥션
		con = db.getCon();
		//질의명령
		String sql = mSQL.getSQL(mSQL.SEL_LOGIN_CNT);
		//명령전달도구
		pstmt = db.getPSTMT(con, sql);
		
		try {
			//질의명령 완성
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			//질의명령 보내고 결과 받고
			rs = pstmt.executeQuery();
			//결과에서 데이터 꺼내고
			rs.next();
			cnt = rs.getInt("cnt");
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		//데이터 내보내고
		return cnt;
	}
	
	//회원 가입 데이터베이스 작업 전담 처리함수
	public int addMember(MemberVO mVO) {
		//반환값 변수
		int cnt = 0;

		//ResultSet => 인라인 테이블 기억하는 자바 객체.. 따라서 SELECT에서만 생김
		
		//할일
		//커넥션
		con = db.getCon();
		//질의명령
		String sql = mSQL.getSQL(mSQL.ADD_MEMBER);
		//명령 전달 도구
		pstmt = db.getPSTMT(con, sql);
		
		try {
			//질의명령 완성
			pstmt.setString(1, mVO.getName());
			pstmt.setString(2, mVO.getId());
			pstmt.setString(3, mVO.getPw());
			pstmt.setString(4, mVO.getMail());
			pstmt.setString(5, mVO.getTel());
			pstmt.setInt(6, mVO.getAno());
			pstmt.setString(7, mVO.getGen());
			
			//질의명령 보내고 결과 받고
			cnt = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt);
			db.close(con);		
		}
		
		return cnt;
	}
	
	//아바타 리스트 조회 전담 처리함수
	public ArrayList<MemberVO> getAvtList() {
		//반환값 변수
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		
		//커넥션
		con = db.getCon();
		//질의명령
		String sql = mSQL.getSQL(mSQL.SEL_ALL_AVT);
		//명령 전달 도구
		stmt = db.getSTMT(con);
		try {
			//질의명령 보내고 결과 받고
			rs = stmt.executeQuery(sql);
			
			//반복해서 결과 꺼내고 VO에 담고
			while(rs.next()) {//rs.next() : 반환값 T of F
				//반복할 때마다 아바타 한 개의 정보를 기억할 수 있는 VO가 만들어져야 한다.
				MemberVO mVO = new MemberVO();
				//VO에 아바타 정보 채우고
				mVO.setAno(rs.getInt("ano"));
				mVO.setSavename(rs.getString("savename"));
				mVO.setGen(rs.getString("gen"));
				
				//vo가 완성됐으면 리스트에 담고
				list.add(mVO);
			}			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(con);
			db.close(stmt);
		}
		
		//리스트 반환
		return list;
	}
	
	//아이디 카운트 조회 전담 처리 함수
	public int getIdCount(String id) {
		//반환값 변수
		int cnt = 0;
		
		//커넥션
		con = db.getCon();
		//질의명령
		String sql = mSQL.getSQL(mSQL.SEL_ID_CNT);
		//명령전달도구
		pstmt = db.getPSTMT(con, sql);  
		try{
			//질의명령 완성
			pstmt.setString(1, id);
			//보내고 결과 받고
			rs = pstmt.executeQuery();
			//레코드 포인터 한 줄 내리고
			rs.next();
			//데이터 꺼내서 변수에 담고
			cnt =rs.getInt("cnt");
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
	
	//회원 목록 조회 전담 처리 함수
	public ArrayList<MemberVO> getMemberList(){
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		
		//커넥션
		con = db.getCon();
		//질의명령
		String sql = mSQL.getSQL(mSQL.SEL_MEMBER_LIST);
		//명령전달도구
		stmt = db.getSTMT(con);
		try {
			//질의명령 보내고 결과 받고
			rs = stmt.executeQuery(sql);
			while(rs.next()) {				
				//데이터 꺼내서 vo 담고
				MemberVO mVO = new MemberVO();
				int mno = rs.getInt("mno");
				String name = rs.getString("name");
				
				mVO.setMno(mno);
				mVO.setName(name);
				
				//vo리스트에 채우고
				list.add(mVO);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(stmt);
			db.close(con);
		}
		
		//리스트 반환
		return list;
	}
	//회원번호로 회원 정보 조회 전담 처리 함수
	public MemberVO getMnoInfo(int mno) {
		MemberVO mvo = new MemberVO();
		
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.SEL_MNO_INFO);
		pstmt = db.getPSTMT(con, sql);
		try {
			pstmt.setInt(1, mno);
			rs = pstmt.executeQuery();
			rs.next();
			mvo.setMno(rs.getInt("mno"));
			mvo.setName(rs.getString("name"));
			mvo.setId(rs.getString("id"));
			mvo.setMail(rs.getString("mail"));
			mvo.setTel(rs.getString("tel"));
			mvo.setJdate(rs.getDate("joindate"));
			mvo.setJtime(rs.getTime("joindate"));
			mvo.setSdate();
			mvo.setGen(rs.getString("gen"));
			mvo.setSavename(rs.getString("savename"));
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		return mvo;
	}
	
	
	// 아이디로 회원 정보조회 전담 처리함수
		public MemberVO getIdInfo(String id) {
			// 반환값 변수
			MemberVO mVO = new MemberVO();
			// 커넥션
			con = db.getCon();
			// 질의명령
			String sql = mSQL.getSQL(mSQL.SEL_MEMBER_INFO);
			// 명령전달도구
			pstmt = db.getPSTMT(con, sql);
			try {
				// 질의명령 완성
				pstmt.setString(1, id);
				// 질의명령 보내고 결과받고
				rs = pstmt.executeQuery();
				// 꺼내서 VO에 담고
				rs.next();
				// vo setting
				mVO.setMno(rs.getInt("mno"));
				mVO.setName(rs.getString("name"));
				mVO.setId(rs.getString("id"));
				mVO.setMail(rs.getString("mail"));
				mVO.setTel(rs.getString("tel"));
				mVO.setGen(rs.getString("gen"));
				mVO.setAno(rs.getInt("ano"));
				mVO.setSavename(rs.getString("savename"));
				mVO.setJdate(rs.getDate("joindate"));
				mVO.setJtime(rs.getTime("joindate"));
				mVO.setSdate();
				
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				db.close(rs);
				db.close(pstmt);
				db.close(con);
			}
			
			// 데이터 반환해주고
			return mVO;
		}
		
		//회원 탈퇴 데이터 베이스 작업 전담 처리 함수
		public int delMember(int mno) {
			//할일
			//반환값 변수
			int cnt = 0;
			//커넥션
			con = db.getCon();
			//질의명령
			String sql = mSQL.getSQL(mSQL.DEL_MEMBER);
			//명령전달도구
			pstmt = db.getPSTMT(con, sql);
			
			try {
				//질의명령 완성				
				pstmt.setInt(1, mno);
				//질의명령 보내고 결과 받고
				cnt = pstmt.executeUpdate();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				db.close(pstmt);
				db.close(con);
			}
			
			//반환
			return cnt;
		}
		
		//회원 정보 수정 데이터베이스 작업 전담 처리 함수
		public int editMyInfo(int mno, String psql) {
			int cnt = 0;
			
			//커넥션
			con = db.getCon();
			//질의명령
			String sql = mSQL.getSQL(mSQL.EDIT_MEMBER);
			//질의명령 수정
			sql = sql.replace("###", psql);
			//명령 전달 도구
			pstmt = db.getPSTMT(con, sql);
			try {
				//질의명령 완성
				pstmt.setInt(1, mno);
				//질의명령 보내고 결과 받고
				cnt = pstmt.executeUpdate();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				db.close(pstmt);
				db.close(con);
			}
			
			//결과 반환
			return cnt;
		}
}
