package win.tang.demo.domain;


import java.io.Serializable;

public class MmallOrder implements Serializable {

  private Integer id;
  private Integer orderNo;
  private Integer userId;
  private Integer shippingId;
  private double payment;
  private Integer paymentType;
  private Integer postage;
  private Integer status;
  private java.util.Date paymentTime;
  private java.util.Date sendTime;
  private java.util.Date endTime;
  private java.util.Date closeTime;
  private java.util.Date createTime;
  private java.util.Date updateTime;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public Integer getOrderNo() {
    return orderNo;
  }

  public void setOrderNo(Integer orderNo) {
    this.orderNo = orderNo;
  }


  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }


  public Integer getShippingId() {
    return shippingId;
  }

  public void setShippingId(Integer shippingId) {
    this.shippingId = shippingId;
  }


  public double getPayment() {
    return payment;
  }

  public void setPayment(double payment) {
    this.payment = payment;
  }


  public Integer getPaymentType() {
    return paymentType;
  }

  public void setPaymentType(Integer paymentType) {
    this.paymentType = paymentType;
  }


  public Integer getPostage() {
    return postage;
  }

  public void setPostage(Integer postage) {
    this.postage = postage;
  }


  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }


  public java.util.Date getPaymentTime() {
    return paymentTime;
  }

  public void setPaymentTime(java.util.Date paymentTime) {
    this.paymentTime = paymentTime;
  }


  public java.util.Date getSendTime() {
    return sendTime;
  }

  public void setSendTime(java.util.Date sendTime) {
    this.sendTime = sendTime;
  }


  public java.util.Date getEndTime() {
    return endTime;
  }

  public void setEndTime(java.util.Date endTime) {
    this.endTime = endTime;
  }


  public java.util.Date getCloseTime() {
    return closeTime;
  }

  public void setCloseTime(java.util.Date closeTime) {
    this.closeTime = closeTime;
  }


  public java.util.Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.util.Date createTime) {
    this.createTime = createTime;
  }


  public java.util.Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(java.util.Date updateTime) {
    this.updateTime = updateTime;
  }

}
