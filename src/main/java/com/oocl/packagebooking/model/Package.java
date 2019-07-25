package com.oocl.packagebooking.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Package {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int orderId;

  @Column(nullable = false, unique = true)
  private String orderNumber;

  @Column(nullable = false)
  private String receiver;

  @Column(nullable = false)
  private String phone;

  @Column(nullable = false)
  private String status;

  @Temporal(TemporalType.TIMESTAMP)
  private Date bookDate;

  public int getOrderId() {
    return orderId;
  }

  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }

  public String getOrderNumber() {
    return orderNumber;
  }

  public void setOrderNumber(String orderNumber) {
    this.orderNumber = orderNumber;
  }

  public String getReceiver() {
    return receiver;
  }

  public void setReceiver(String receiver) {
    this.receiver = receiver;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Date getBookDate() {
    return bookDate;
  }

  public void setBookDate(Date bookDate) {
    this.bookDate = bookDate;
  }
}
