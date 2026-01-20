package com.deepanshu.orderprocessing.domainservice;

import java.util.HashMap;
import java.util.Map;

import com.deepanshu.orderprocessing.domainentity.Items;
import com.deepanshu.orderprocessing.domainentity.Orders;
import com.deepanshu.orderprocessing.orderresultstatus.OrderStatus;
import com.deepanshu.orderprocessing.orderresultstatus.OrdersResult;

/*
 * inventory class behaves like the storage where items are present
 * and when the order are placed items are checked in the inventory
 * either it available or not 
*/
public class Inventory {

	// to store all the available stocks
	private Map<Items, Integer> presentStock = new HashMap<>();

	public Inventory(Map<Items, Integer> presentStock) {
		super();
		this.presentStock = presentStock;
	}

	/*
	 * * this method first fetch the item name and quantity than check and update
	 * the * stock first the condition check if the given order present in the
	 * inventory * or not also check the quantity requested is available or not if
	 * not than * return the result or status if yes than add in map with the
	 * updated field and quantity. Making this synchronized so only one thread can
	 * access or update the result * at time. *
	 */
	public OrdersResult checkAndUpdateStock(Orders order) {
		System.out.println(order.getOrdersId() + " " + order.getItems().getItemName() + " " + order.getOrderQuantity());

		Items orderItem = order.getItems();
		int orderQuantity = order.getOrderQuantity();
		int availableQuantityInInventory = presentStock.get(order.getItems());

		System.out.println("order quanttity: " + orderQuantity + " available quantity" + availableQuantityInInventory);

		if (orderItem == null) {
			return new OrdersResult(order.getOrdersId(), OrderStatus.FAILED, "Item not found in the inventory");
		}

		if (orderQuantity > availableQuantityInInventory) {
			return new OrdersResult(order.getOrdersId(), OrderStatus.FAILED, "insufficient stock");
		}
		// update the stock if all right
		int remaningQuantity = orderQuantity - availableQuantityInInventory;
		presentStock.put(orderItem, remaningQuantity);

		return new OrdersResult(order.getOrdersId(), OrderStatus.SUCCESS, "Order placed successfully");

	}

	public Items getItemName(String itemName) {
		return presentStock.keySet().stream().filter(i -> i.getItemName().equalsIgnoreCase(itemName)).findFirst()
				.orElse(null);
	}
}
