package com.chlx.frsale.pojo;

public class CMP {
    private Medicine medicine;
    private Customer customer;
    private Purchase purchase;

    public CMP() {
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    @Override
    public String toString() {
        return "CMP{" +
                "medicine=" + medicine.toString() +
                ", customer=" + customer.toString() +
                ", purchase=" + purchase.toString() +
                '}';
    }
}
