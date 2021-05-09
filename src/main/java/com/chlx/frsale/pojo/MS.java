package com.chlx.frsale.pojo;

public class MS {
    private int mid;
    private String mname;
    private String mproduce;
    private int mprice;
    private String freshdate;
    private String form;
    private String component;
    private String cure;
    private String explain;
    private int stoid;
    private int stocknum;

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
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

    public int getStoid() {
        return stoid;
    }

    public void setStoid(int stoid) {
        this.stoid = stoid;
    }

    public int getStocknum() {
        return stocknum;
    }

    public void setStocknum(int stocknum) {
        this.stocknum = stocknum;
    }

    public MS(int mid, String mname, String mproduce, int mprice, String freshdate, String form, String component, String cure, String explain, int stoid, int stocknum) {
        this.mid = mid;
        this.mname = mname;
        this.mproduce = mproduce;
        this.mprice = mprice;
        this.freshdate = freshdate;
        this.form = form;
        this.component = component;
        this.cure = cure;
        this.explain = explain;
        this.stoid = stoid;
        this.stocknum = stocknum;
    }

    public MS() {
    }

    @Override
    public String toString() {
        return "MS{" +
                "mid=" + mid +
                ", mname='" + mname + '\'' +
                ", mproduce='" + mproduce + '\'' +
                ", mprice=" + mprice +
                ", freshdate='" + freshdate + '\'' +
                ", form='" + form + '\'' +
                ", component='" + component + '\'' +
                ", cure='" + cure + '\'' +
                ", explain='" + explain + '\'' +
                ", stoid=" + stoid +
                ", stocknum=" + stocknum +
                '}';
    }
}
