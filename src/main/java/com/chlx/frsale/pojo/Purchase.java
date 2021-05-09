package com.xiaowang.mesqle.pojo;
import java.sql.Date;
public class Purchase {
    private int pid;
    private int uid;
    private int cid;
    private int mid;
    private int pnum;
    private int pprice;
    private Date pdate;
    private int pamount;
    private int pflag;
    public int getPid() {
        return pid;
    }
    public void setPid(int pid) {
        this.pid = pid;
    }
    public int getUid() {
        return uid;
    }
    public void setUid(int uid) {
        this.uid = uid;
    }
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getPnum() {
        return pnum;
    }

    public void setPnum(int pnum) {
        this.pnum = pnum;
    }

    public int getPprice() {
        return pprice;
    }

    public void setPprice(int pprice) {
        this.pprice = pprice;
    }

    public Date getPdate() {
        return pdate;
    }

    public void setPdate(Date pdate) {
        this.pdate = pdate;
    }

    public int getPamount() {
        return pamount;
    }

    public void setPamount(int pamount) {
        this.pamount = pamount;
    }

    public int getPflag() {
        return pflag;
    }

    public void setPflag(int pflag) {
        this.pflag = pflag;
    }

    public Purchase(int pid, int uid, int cid, int mid, int pnum, int pprice, Date pdate, int pamount, int pflag) {
        this.pid = pid;
        this.uid = uid;
        this.cid = cid;
        this.mid = mid;
        this.pnum = pnum;
        this.pprice = pprice;
        this.pdate = pdate;
        this.pamount = pamount;
        this.pflag = pflag;
    }

    public Purchase() {
    }
}
