package com.deepanshu.orderprocessing.multithreadingtask;

import java.util.concurrent.Callable;

import com.deepanshu.orderprocessing.domainentity.Orders;
import com.deepanshu.orderprocessing.domainservice.Inventory;
import com.deepanshu.orderprocessing.orderresultstatus.OrdersResult;

public class OrderProcessingThreads implements Callable<OrdersResult> {

	private Inventory inventory;
	private Orders orders;

	public OrderProcessingThreads(Inventory inventory, Orders orders) {
		super();
		this.inventory = inventory;
		this.orders = orders;
	}

	@Override
	public OrdersResult call() throws Exception {
		System.out.println(Thread.currentThread().getName()+ " Processing order: "+ orders.getOrdersId());
		return inventory.checkAndUpdateStock(orders);

	}

}
