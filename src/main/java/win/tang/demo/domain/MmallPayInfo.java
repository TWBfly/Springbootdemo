package win.tang.demo.domain;


public class MmallPayInfo {

  private Integer id;
  private Integer userId;
  private Integer orderNo;
  private Integer payPlatform;
  private String platformNumber;
  private String platformStatus;
  private java.util.Date createTime;
  private java.util.Date updateTime;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }


  public Integer getOrderNo() {
    return orderNo;
  }

  public void setOrderNo(Integer orderNo) {
    this.orderNo = orderNo;
  }


  public Integer getPayPlatform() {
    return payPlatform;
  }

  public void setPayPlatform(Integer payPlatform) {
    this.payPlatform = payPlatform;
  }


  public String getPlatformNumber() {
    return platformNumber;
  }

  public void setPlatformNumber(String platformNumber) {
    this.platformNumber = platformNumber;
  }


  public String getPlatformStatus() {
    return platformStatus;
  }

  public void setPlatformStatus(String platformStatus) {
    this.platformStatus = platformStatus;
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
