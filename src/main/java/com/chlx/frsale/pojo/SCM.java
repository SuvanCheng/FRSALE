package com.chlx.frsale.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class SCM {
    private int sid;
    private int uid;
    private int cid;
    private int mid;
    private int snum;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date sdate;
    private int samount;
    private int sflag;
    private String cname;
    private String cphonenum;
    private String caddress;
    private String cemail;
    private String mname;
    private String mproduce;
    private int mprice;
    private String freshdate;
    private String form;
    private String component;
    private String cure;
    private String explain;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
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

    public int getSnum() {
        return snum;
    }

    public void setSnum(int snum) {
        this.snum = snum;
    }

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public int getSamount() {
        return samount;
    }

    public void setSamount(int samount) {
        this.samount = samount;
    }

    public int getSflag() {
        return sflag;
    }

    public void setSflag(int sflag) {
        this.sflag = sflag;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCphonenum() {
        return cphonenum;
    }

    public void setCphonenum(String cphonenum) {
        this.cphonenum = cphonenum;
    }

    public String getCaddress() {
        return caddress;
    }

    public void setCaddress(String caddress) {
        this.caddress = caddress;
    }

    public String getCemail() {
        return cemail;
    }

    public void setCemail(String cemail) {
        this.cemail = cemail;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getMproduce() {
        return mproduce;
    }

    public void setMproduce(String mproduce) {
        this.mproduce = mproduce;
    }

    public int getMprice() {
        return mprice;
    }

    public void setMprice(int mprice) {
        this.mprice = mprice;
    }

    public String getFreshdate() {
        return freshdate;
    }

    public void setFreshdate(String freshdate) {
        this.freshdate = freshdate;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getCure() {
        return cure;
    }

    public void setCure(String cure) {
        this.cure = cure;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public SCM(int sid, int uid, int cid, int mid, int snum, Date sdate, int samount, int sflag, String cname, String cphonenum, String caddress, String cemail, String mname, String mproduce, int mprice, String freshdate, String form, String component, String cure, String explain) {
        this.sid = sid;
        this.uid = uid;
        this.cid = cid;
        this.mid = mid;
        this.snum = snum;
        this.sdate = sdate;
        this.samount = samount;
        this.sflag = sflag;
        this.cname = cname;
        this.cphonenum = cphonenum;
        this.caddress = caddress;
        this.cemail = cemail;
        this.mname = mname;
        this.mproduce = mproduce;
        this.mprice = mprice;
        this.freshdate = freshdate;
        this.form = form;
        this.component = component;
        this.cure = cure;
        this.explain = explain;
    }

    public SCM() {
    }

    @Override
    public String toString() {
        return "SCM{" +
                "sid=" + sid +
                ", uid=" + uid +
                ", cid=" + cid +
                ", mid=" + mid +
                ", snum=" + snum +
                ", sdate=" + sdate +
                ", samount=" + samount +
                ", sflag=" + sflag +
                ", cname='" + cname + '\'' +
                ", cphonenum='" + cphonenum + '\'' +
                ", caddress='" + caddress + '\'' +
                ", cemail='" + cemail + '\'' +
                ", mname='" + mname + '\'' +
                ", mproduce='" + mproduce + '\'' +
                ", mprice=" + mprice +
                ", freshdate='" + freshdate + '\'' +
                ", form='" + form + '\'' +
                ", component='" + component + '\'' +
                ", cure='" + cure + '\'' +
                ", explain='" + explain + '\'' +
                '}';
    }
}
