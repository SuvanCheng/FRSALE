package com.xiaowang.mesqle.pojo;

public class Stock {
    private int stoid;
    private int mid;
    private int stocknum;

    public Stock(int mid, int stocknum) {
        this.mid = mid;
        this.stocknum = stocknum;
    }

    public Stock(int stoid, int mid, int stocknum) {
        this.stoid = stoid;
        this.mid = mid;
        this.stocknum = stocknum;
    }
    public Stock(){}
    public void setStoid(int stoid) {
        this.stoid = stoid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public void setStocknum(int stocknum) {
        this.stocknum = stocknum;
    }

    public int getStoid() {
        return stoid;
    }

    public int getMid() {
        return mid;
    }

    public int getStocknum() {
        return stocknum;
    }
}
