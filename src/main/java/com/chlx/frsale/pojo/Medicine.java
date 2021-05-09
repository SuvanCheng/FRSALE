package com.xiaowang.mesqle.pojo;

public class Medicine {
    private int mid;
    private String mname;
    private String mproduce;
    private int mprice;
    private String freshdate;
    private String form;
    private String component;
    private String cure;
    private String explain;

    public Medicine(int mid, String mname, String mproduce, int mprice, String freshdate, String form, String component, String cure, String explain) {
        this.mid = mid;
        this.mname = mname;
        this.mproduce = mproduce;
        this.mprice = mprice;
        this.freshdate = freshdate;
        this.form = form;
        this.component = component;
        this.cure = cure;
        this.explain = explain;
    }

    public Medicine(String mname, String mproduce, int mprice, String freshdate, String form, String component, String cure, String explain) {
        this.mname = mname;
        this.mproduce = mproduce;
        this.mprice = mprice;
        this.freshdate = freshdate;
        this.form = form;
        this.component = component;
        this.cure = cure;
        this.explain = explain;
    }
    public Medicine(){}
    public int getMid() {
        return mid;
    }

    public String getMname() {
        return mname;
    }

    public String getMproduce() {
        return mproduce;
    }

    public int getMprice() {
        return mprice;
    }

    public String getFreshdate() {
        return freshdate;
    }

    public String getForm() {
        return form;
    }

    public String getComponent() {
        return component;
    }

    public String getCure() {
        return cure;
    }

    public String getExplain() {
        return explain;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public void setMproduce(String mproduce) {
        this.mproduce = mproduce;
    }

    public void setMprice(int mprice) {
        this.mprice = mprice;
    }

    public void setFreshdate(String freshdate) {
        this.freshdate = freshdate;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public void setCure(String cure) {
        this.cure = cure;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }
}
