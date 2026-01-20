package com.deepanshu.orderprocessing.domainentity;

public class Orders {

	private int ordersId;
	private Items itemName;
	private int orderQuantity;

	public Orders(int ordersId, Items itemName, int orderQuantity) {
		super();
		this.ordersId = ordersId;
		this.itemName = itemName;
		this.orderQuantity = orderQuantity;
	}

	public int getOrdersId() {
		return ordersId;
	}

	public Items getItems() {
		return itemName;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

}
