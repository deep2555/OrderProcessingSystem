package com.deepanshu.orderprocessing.orderresultstatus;

public class OrdersResult {

	private int orderId;
	private OrderStatus orderStatus;
	private String reason;
	
	
	public OrdersResult(int orderId, OrderStatus orderStatus, String reason) {
		super();
		this.orderId = orderId;
		this.orderStatus = orderStatus;
		this.reason = reason;
	}
	public int getOrderId() {
		return orderId;
	}
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	public String getStatus() {
		return reason;
	}
	
	
}
