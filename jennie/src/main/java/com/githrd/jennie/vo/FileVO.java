package com.githrd.jennie.vo;

import java.sql.Time;
import java.util.Date;
import java.text.*;

public class FileVO {
	private int bno, fno, mno, rno, cnt;
	private long len;
	private String id, oriname, savename, dir, sdate, stime;
	private Date wdate;
	private Time wtime;
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getFno() {
		return fno;
	}
	public void setFno(int fno) {
		this.fno = fno;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public long getLen() {
		return len;
	}
	public void setLen(long len) {
		this.len = len;
	}
	public String getOriname() {
		return oriname;
	}
	public void setOriname(String oriname) {
		this.oriname = oriname;
	}
	public String getSavename() {
		return savename;
	}
	public void setSavename(String savename) {
		this.savename = savename;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate() {
		SimpleDateFormat form = new SimpleDateFormat("yyyy년 MM월 dd일");
		sdate = form.format(wdate);
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getStime() {
		return stime;
	}
	public void setStime() {
		SimpleDateFormat form = new SimpleDateFormat("HH24:mm:ss");
		stime = form.format(wtime);
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public Date getWdate() {
		return wdate;
	}
	public void setWdate(Date wdate) {
		this.wdate = wdate;
		setSdate();
	}
	public Time getWtime() {
		return wtime;
	}
	public void setWtime(Time wtime) {
		this.wtime = wtime;
		setStime();
	}
	@Override
	public String toString() {
		return "FileVO [fno=" + fno + ", mno=" + mno + ", rno=" + rno + ", cnt=" + cnt + ", len=" + len + ", oriname="
				+ oriname + ", savename=" + savename + ", dir=" + dir + ", sdate=" + sdate + ", stime=" + stime
				+ ", wdate=" + wdate + ", wtime=" + wtime + ", getFno()=" + getFno() + ", getMno()=" + getMno()
				+ ", getRno()=" + getRno() + ", getCnt()=" + getCnt() + ", getLen()=" + getLen() + ", getOriname()="
				+ getOriname() + ", getSavename()=" + getSavename() + ", getDir()=" + getDir() + ", getSdate()="
				+ getSdate() + ", getStime()=" + getStime() + ", getWdate()=" + getWdate() + ", getWtime()="
				+ getWtime() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
}