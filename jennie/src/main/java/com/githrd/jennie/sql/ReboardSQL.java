package com.githrd.jennie.sql;

public class ReboardSQL {
	public final int SEL_ALL_LIST = 1001;
	public final int SEL_TOTAL_CNT = 1002;
	public final int SEL_WRITER_INFO = 1003;
	public final int SEL_REBOARD_INFO = 1004;

	public final int DEL_REBOARD = 2001;
	public final int UPDATE_REBOARD = 2002;

	public final int INSERT_REBOARD = 3001;
	
	public String getSQL(int code) {
		StringBuffer buff = new StringBuffer();
		
		switch(code) {
		case SEL_ALL_LIST:
			buff.append("SELECT ");
			buff.append("    rno,  rbno, upno, mno, id, body, savename, wdate, step ");
			buff.append("FROM ");
			buff.append("    ( ");
			buff.append("        SELECT ");
			buff.append("            ROWNUM rno,  rbno, upno, mno, id, body, savename, wdate, step ");
			buff.append("        FROM ");
			buff.append("            ( ");
			buff.append("                SELECT ");
			buff.append("                    rbno, upno, mno, id, body, savename, wdate, (level - 1) step "); 
			buff.append("                FROM ");
			buff.append("                    reboard r, member m, avatar a ");
			buff.append("                WHERE ");
			buff.append("                    r.isshow = 'Y' ");
			buff.append("                    AND rbmno = mno ");
			buff.append("                    AND avt = ano ");
			buff.append("                START WITH ");
			buff.append("                    upno IS NULL ");
			buff.append("                CONNECT BY ");
			buff.append("                    PRIOR rbno = upno ");
			buff.append("                ORDER SIBLINGS BY ");
			buff.append("                    wdate DESC ");
			buff.append("            ) ");
			buff.append("    ) ");
			buff.append("WHERE ");
			buff.append("    rno BETWEEN ? AND ? ");		
			break;
		case SEL_TOTAL_CNT:
			buff.append("SELECT ");
			buff.append("    COUNT(*) cnt ");
			buff.append("FROM ");
			buff.append("	 reboard ");
			buff.append("WHERE ");
			buff.append("	 isshow = 'Y' ");
			break;
		case SEL_REBOARD_INFO:
			buff.append("SELECT ");
			buff.append("    rbno, body, wdate, mno, id, savename ");
			buff.append("FROM ");
			buff.append("	reboard r, member m, avatar a ");
			buff.append("WHERE ");
			buff.append("	r.isshow = 'Y' ");
			buff.append("	AND  rbno = ? ");
			buff.append("	AND  avt = ano ");
			buff.append("	AND  id = ? ");
			break;
		case SEL_WRITER_INFO:
			buff.append("SELECT ");
			buff.append("    mno, savename ");   
			buff.append("FROM ");
			buff.append("    member m, avatar a ");
			buff.append("WHERE ");
			buff.append("    m.isshow = 'Y' ");
			buff.append("    AND avt = ano ");
			buff.append("    AND id = ? ");
			break;
		case INSERT_REBOARD:
			buff.append("INSERT INTO ");
			buff.append("    reboard(rbno, upno, rbmno, body) ");
			buff.append("VALUES( ");
			buff.append("    (SELECT NVL(MAX(rbno) + 1, 100001) FROM reboard), "); 
			buff.append("    ?, ?, ? ");
			buff.append(") ");			
		case DEL_REBOARD:
			buff.append("UPDATE ");
			buff.append("    reboard ");
			buff.append("SET ");
			buff.append("    isshow = 'N' ");
			buff.append("WHERE ");
			buff.append("    rbno = ? ");
			break;
		case UPDATE_REBOARD:
			buff.append("UPDATE ");
			buff.append("    reboard ");
			buff.append("SET ");
			buff.append("    body = ? ");	
			buff.append("WHERE ");
			buff.append("    rbno = ? ");
			break;
		}
		
		return buff.toString();
	}
}
