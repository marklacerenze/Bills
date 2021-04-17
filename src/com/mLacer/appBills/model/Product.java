package com.mLacer.appBills.model;

public class Product {
  private int codeBill;
  private String name;
  private float price;
  private static int lastCodeBill;

  public Product() {
    this.codeBill = ++lastCodeBill;
  }

  public int getCodeBill() {
    return codeBill;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }
}
