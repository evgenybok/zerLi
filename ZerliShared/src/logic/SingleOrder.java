package logic;

import java.io.Serializable;

public class SingleOrder implements Serializable {
    private static final long serialVersionUID = -4476848811757027097L;

    private int OrderNumber;
    private double Price;
    private String StoreName;
    private String OrderDate;
    private String SupplyDate;
    private String SupplyType;
    private String StoreId;
    private double Refund;
    private String Status;

    public SingleOrder(int OrderNumber,double Price,String StoreID,String OrderDate,String SupplyDate
            ,String SupplyType,double Refund, String Status){

        this.OrderNumber=OrderNumber;
        this.Price=Price;
        this.StoreId=StoreID;
        this.OrderDate=OrderDate;
        this.SupplyDate=SupplyDate;
        this.SupplyType=SupplyType;
        this.Refund=Refund;
        this.Status=Status;

    }

    public int getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        OrderNumber = orderNumber;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String storeName) {
        StoreName = storeName;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }

    public String getSupplyDate() {
        return SupplyDate;
    }

    public void setSupplyDate(String supplyDate) {
        SupplyDate = supplyDate;
    }

    public String getSupplyType() {
        return SupplyType;
    }

    public void setSupplyType(String supplyType) {
        SupplyType = supplyType;
    }

    public String getStoreId() {
        return StoreId;
    }

    public void setStoreId(String storeId) {
        StoreId = storeId;
    }

    public double getRefund() {
        return Refund;
    }

    public void setRefund(double refund) {
        Refund = refund;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }




}
