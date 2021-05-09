package com.xiaowang.mesqle.pojo;

public class Customer {
    private int cid;
    private String cname;
    private String cphonenum;
    private String caddress;
    private String cemail;

    public Customer(int cid, String cname, String cphonenum, String caddress, String cemail) {
        this.cid = cid;
        this.cname = cname;
        this.cphonenum = cphonenum;
        this.caddress = caddress;
        this.cemail = cemail;
    }

    public Customer(String cname, String cphonenum, String caddress, String cemail) {
        this.cname = cname;
        this.cphonenum = cphonenum;
        this.caddress = caddress;
        this.cemail = cemail;
    }
    public Customer(){}
    public int getCid() {
        return cid;
    }

    public String getCname() {
        return cname;
    }

    public String getCphonenum() {
        return cphonenum;
    }

    public String getCaddress() {
        return caddress;
    }

    public String getCemail() {
        return cemail;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public void setCphonenum(String cphonenum) {
        this.cphonenum = cphonenum;
    }

    public void setCaddress(String caddress) {
        this.caddress = caddress;
    }

    public void setCemail(String cemail) {
        this.cemail = cemail;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", cphonenum='" + cphonenum + '\'' +
                ", caddress='" + caddress + '\'' +
                ", cemail='" + cemail + '\'' +
                '}';
    }
}
