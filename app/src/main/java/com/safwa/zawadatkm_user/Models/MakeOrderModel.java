package com.safwa.zawadatkm_user.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MakeOrderModel implements Serializable
{

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("paymentResponse")
    @Expose
    private Object paymentResponse;
    @SerializedName("payment")
    @Expose
    private Payment payment;
    private final static long serialVersionUID = -7239268551985402357L;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Object getPaymentResponse() {
        return paymentResponse;
    }

    public void setPaymentResponse(Object paymentResponse) {
        this.paymentResponse = paymentResponse;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }




public class Payment implements Serializable {

    @SerializedName("method")
    @Expose
    private String method;
    @SerializedName("total")
    @Expose
    private Double total;
    @SerializedName("paymentGateway")
    @Expose
    private String paymentGateway;
    @SerializedName("order_id")
    @Expose
    private Integer orderId;
    @SerializedName("paypalOrderId")
    @Expose
    private Object paypalOrderId;
    @SerializedName("approvalLink")
    @Expose
    private Object approvalLink;
    @SerializedName("gatewayStatus")
    @Expose
    private String gatewayStatus;
    @SerializedName("id")
    @Expose
    private Integer id;
    private final static long serialVersionUID = 6031556136087481902L;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getPaymentGateway() {
        return paymentGateway;
    }

    public void setPaymentGateway(String paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Object getPaypalOrderId() {
        return paypalOrderId;
    }

    public void setPaypalOrderId(Object paypalOrderId) {
        this.paypalOrderId = paypalOrderId;
    }

    public Object getApprovalLink() {
        return approvalLink;
    }

    public void setApprovalLink(Object approvalLink) {
        this.approvalLink = approvalLink;
    }

    public String getGatewayStatus() {
        return gatewayStatus;
    }

    public void setGatewayStatus(String gatewayStatus) {
        this.gatewayStatus = gatewayStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
}