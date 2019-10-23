package win.tang.demo.domain;


public class MmallOrderItem {

  private Integer id;
  private Integer userId;
  private Integer orderNo;
  private Integer productId;
  private String productName;
  private String productImage;
  private double currentUnitPrice;
  private Integer quantity;
  private double totalPrice;
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


  public Integer getProductId() {
    return productId;
  }

  public void setProductId(Integer productId) {
    this.productId = productId;
  }


  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }


  public String getProductImage() {
    return productImage;
  }

  public void setProductImage(String productImage) {
    this.productImage = productImage;
  }


  public double getCurrentUnitPrice() {
    return currentUnitPrice;
  }

  public void setCurrentUnitPrice(double currentUnitPrice) {
    this.currentUnitPrice = currentUnitPrice;
  }


  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }


  public double getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(double totalPrice) {
    this.totalPrice = totalPrice;
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
