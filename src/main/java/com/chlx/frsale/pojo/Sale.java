package com.xiaowang.mesqle.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

public class Sale {
    private int sid;
    private int uid;
    private int cid;
    private int mid;
    private int snum;
    private Date sdate;
    private int samount;
    private int sflag;

    public Sale(int sid, int uid, int cid, int mid, int snum, Date sdate, int samount, int sflag) {
        this.sid = sid;
        this.uid = uid;
        this.cid = cid;
        this.mid = mid;
        this.snum = snum;
        this.sdate = sdate;
        this.samount = samount;
        this.sflag = sflag;
    }

    public Sale(int uid, int cid, int mid, int snum, Date sdate, int samount, int sflag) {
        this.uid = uid;
        this.cid = cid;
        this.mid = mid;
        this.snum = snum;
        this.sdate = sdate;
        this.samount = samount;
        this.sflag = sflag;
    }
    public Sale(){}
    public void setSid(int sid) {
        this.sid = sid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public void setSnum(int snum) {
        this.snum = snum;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public void setSamount(int samount) {
        this.samount = samount;
    }

    public void setSflag(int sflag) {
        this.sflag = sflag;
    }

    public int getSid() {
        return sid;
    }

    public int getUid() {
        return uid;
    }

    public int getCid() {
        return cid;
    }

    public int getMid() {
        return mid;
    }

    public int getSnum() {
        return snum;
    }

    public Date getSdate() {
        return sdate;
    }

    public int getSamount() {
        return samount;
    }

    public int getSflag() {
        return sflag;
    }
}
