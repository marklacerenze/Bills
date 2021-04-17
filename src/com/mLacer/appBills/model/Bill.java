package com.mLacer.appBills.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Bill {
  private int id;
  private String description;
  private Date date;
  private Client client;
  private ItemBill[] itemBills;
  private int indexItem;
  public static final int MAX_ITEMS = 10;
  private static int lastId;

  public Bill(String description, Client client) {
    this.description = description;
    this.client = client;
    this.itemBills = new ItemBill[MAX_ITEMS];
    this.id = ++lastId;
    this.date = new Date();
  }

  public int getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public int getIndexItem() {
    return indexItem;
  }

  public void addItemFactura(ItemBill item) {
    if (indexItem < MAX_ITEMS) { // esta factura debe tener como maximo diez elementos
      this.itemBills[indexItem++] = item;
    }
  }

  public float calculateTotal() {
    float total = 0.0f;
    for (ItemBill item : this.itemBills) {
      if (item == null) {
        continue;
      }
      total += item.calculateImport();
    }
    return total;
  }

  public String seeDetail() {
    StringBuilder sb = new StringBuilder("Bill Nº: ");
    sb.append(id)
            .append("\nClient: ")
            .append(this.client.getName())
            .append("\tNIF: ")
            .append(client.getNif())
            .append("\nDescription: ")
            .append(this.description)
            .append("\n")
            .append("\n#\tName\t$\tAmount\tTotal\n");

    // fecha de emision de la factura
    SimpleDateFormat df = new SimpleDateFormat("dd 'de' MMMM, yyyy");
    sb.append("Date: ").append(df.format(this.date)).append("\n");

    for (ItemBill item : this.itemBills) {
      if (item == null) { continue; }
      sb.append(item.getProduct().getCodeBill())
              .append("\n").append(item.getProduct().getName())
              .append("\n").append(item.getProduct().getPrice())
              .append("\n").append(item.getAmount())
              .append("\n").append(item.calculateImport())
              .append("\n");
    }
    sb.append("\nTOTAL BILL: ").append(calculateTotal());

    return sb.toString();
  }
}
