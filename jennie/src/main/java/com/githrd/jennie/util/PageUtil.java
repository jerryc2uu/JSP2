package com.githrd.jennie.util;

/*
 * 이 클래스는 페이지 이동 기능에 필요한 정보를 계산하고
 * 데이터를 기억시키기 위해 만든 유틸리티적인 클래스
 * @author	박소연
 * @since	2022/05/17
 * @version	v.1.0
 * 		
 * 			작업 이력
 * 				2022/05/17	담당자 : 박소연
 * 							클래스제작
 */
public class PageUtil {
	//필요한 변수 선언
	private int nowPage = 1; 	//현재 보고 있는 페이지
	private int totalCount; 	//총 게시물 수
	
	private int pageRow = 3;	//한 페이지에 보여줄 게시물 수
	private int pageGroup = 3;	//한 화면 당 이동가능한 페이지 수
	
	private int startPage;		//현재 페이지에서 보여줄 시작 페이지
	private int endPage;		//현재 페이지에서 보여줄 마지막 페이지
	
	private int startCont;		//현재 페이지에서 보여줄 시작 게시글의 rownum
	private int endCont;		//현재 페이지에서 보여줄 마지막 게시글의 rownum
	
	private int totalPage = 1;	//총 페이지 수
	
	public PageUtil() {}

	public PageUtil(int nowPage, int totalCount) {
		this(nowPage, totalCount, 3, 3); // this() : 현재 실행중인 객체의 생성자 호출할 수 있는 유일한 방법
	}
	
	public PageUtil(int nowPage, int totalCount, int pageRow, int pageGroup) {
		this.nowPage = nowPage;
		this.totalCount = totalCount;
		this.pageRow = pageRow;
		this.pageGroup = pageGroup;
		
		setTotalPage();
		setStartPage();
		setEndPage();
		setCont();
	}
	
	//총 페이지 수 계산하는 함수
	public void setTotalPage() {
		/*
		 	총 페이지 수 = 총 게시물 수 / 한 화면에 표시할 게시물 갯수(pageRow)
			
			단, 경우에 따라 한 페이지가 증가될 수 있다. 
		*/
		totalPage = (totalCount % pageRow == 0) ? (totalCount == 0 ? 1 : (totalCount / pageRow)) : (totalCount / pageRow + 1);  
	}
	
	//시작 페이지 계산해주는 함수
	public void setStartPage() {
		//11페이지 보는 중
		// (11 - 1) / 3 ==> 3
		//					3 * 3 => 9 + 1
		// ==> 현재 페이지(nowPage) - 1, 거기에 한 화면에 보여질 이동 가능한 페이지 수(pageGroup)로 나누고 
		
		/*
		 	10 11 12
		 	
		 	12 / 3 = 4
		 */
		
		/*
			10	11	12
			
			이전 페이지 그룹의 마지막 페이지 + 1
		*/
		
		int tmp = (nowPage - 1) / pageGroup;
		startPage = tmp * pageGroup + 1;
	}
	
	//종료 페이지 계산 함수
	public void setEndPage() {
		//현재 보고 있는 페이지 그룹을 계산하고
		int tmp = (nowPage - 1) / pageGroup;
		endPage = (tmp + 1) * pageGroup;
		
		if(endPage > totalPage) {
			endPage = totalPage;
		}
	}
	
	//시작과 게시물 번호를 계산해주는 함수
	public void setCont() {
		// 현재 8페이지를 보고 있는 경우 시작 게시글 번호는
		// 7페이지의 마지막 게시글 번호 + 1
		// 마지막 게시글 번호는
		// 현재 페이지 * 페이지당 보여질 게시글 수
		//	[주의] 총 게시글 수보다 마지막 게시글 번호가 크면 안 된다.
		startCont = (nowPage - 1) * pageRow + 1;
		endCont = nowPage * pageRow > totalCount ? totalCount : nowPage * pageRow;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageRow() {
		return pageRow;
	}

	public void setPageRow(int pageRow) {
		this.pageRow = pageRow;
	}

	public int getPageGroup() {
		return pageGroup;
	}

	public void setPageGroup(int pageGroup) {
		this.pageGroup = pageGroup;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getStartCont() {
		return startCont;
	}

	public void setStartCont(int startCont) {
		this.startCont = startCont;
	}

	public int getEndCont() {
		return endCont;
	}

	public void setEndCont(int endCont) {
		this.endCont = endCont;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

}
