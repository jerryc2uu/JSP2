package com.githrd.jennie.sql;

public class GBoardSQL {
	public final int SEL_GBRD_LIST = 1001;
	public final int SEL_WRITE_CNT = 1002;
	public final int SEL_TOTAL_CNT = 1003;
	public final int SEL_WRITER_INFO = 1005;
	
	public final int INSERT_GBOARD = 3001;
	
	public String getSQL(int code) {
		StringBuffer buff = new StringBuffer();
		
		switch(code) {
		case SEL_GBRD_LIST:
			buff.append("SELECT ");
			buff.append("    rno, gno, id, body, wdate, savename ");
			buff.append("FROM ");
			buff.append("( ");
			buff.append("    SELECT ");
			buff.append("        ROWNUM rno, gno, id, body, wdate, savename ");
			buff.append("    FROM ");
			buff.append("        (   SELECT ");
			buff.append("                gno, id, body, wdate, savename ");
			buff.append("            FROM ");
			buff.append("                member m, guestboard g, avatar a ");
			buff.append("            WHERE ");
			buff.append("                g.isshow = 'Y' ");
			buff.append("                AND mno = writer ");
			buff.append("                AND avt = ano ");
			buff.append("            ORDER BY ");
			buff.append("                wdate DESC ");
			buff.append("        ) ");
			buff.append(") ");
			buff.append("WHERE ");
			buff.append("    rno BETWEEN ? AND ? ");			
			break;
		case SEL_WRITE_CNT:
			buff.append("SELECT ");
			buff.append("    COUNT(*) cnt ");
			buff.append("FROM ");
			buff.append("	 guestboard ");
			buff.append("WHERE ");
			buff.append("	 writer = ( ");
			buff.append("	 			SELECT ");
			buff.append("	 				mno ");
			buff.append("	 			FROM ");
			buff.append("	 				member ");
			buff.append("	 			WHERE ");
			buff.append("	 				id = ? ");
			buff.append("	 		  ) ");
			break;
		case SEL_TOTAL_CNT:
			buff.append("SELECT ");
			buff.append("    COUNT(*) cnt ");
			buff.append("FROM ");
			buff.append("	 guestboard ");
			buff.append("WHERE ");
			buff.append("	 isshow = 'Y' ");
			break;
		case SEL_WRITER_INFO:
			buff.append("SELECT ");
			buff.append("    mno, savename ");
			buff.append("FROM ");
			buff.append("	 member m, avatar a ");
			buff.append("WHERE ");
			buff.append("	 m.isshow = 'Y' ");
			buff.append("	 AND avt = ano ");
			buff.append("	 AND id = ? ");
			break;
		case INSERT_GBOARD:
			buff.append("INSERT INTO ");
			buff.append("    guestboard(gno, writer, body) ");
			buff.append("VALUES( ");
			buff.append("    (SELECT NVL(MAX(gno) + 1, 1001) FROM guestboard), ");
			buff.append("    (SELECT mno FROM member WHERE id = ?), ");
			buff.append("    ? ");
			buff.append(") ");
			break;
		}
		return buff.toString();
	}
}
