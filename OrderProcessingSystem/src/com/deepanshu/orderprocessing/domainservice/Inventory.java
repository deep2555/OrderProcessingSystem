package com.deepanshu.orderprocessing.domainservice;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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

	private final Lock reLock = new ReentrantLock(true); // fairness true

	public Inventory(Map<Items, Integer> presentStock) {
		super();
		this.presentStock = presentStock;
	}

	/*
	 * * this method first fetch the item name and quantity than check and update
	 * the * stock first the condition check if the given order present in the
	 * inventory * or not also check the quantity requested is available or not if
	 * not than * return the result or status if yes than add in map with the
	 * updated field and quantity.
	 * 
	 * using the reentrant lock to acquire the lock and access the method
	 * concurrently
	 */
	public OrdersResult checkAndUpdateStock(Orders order) {

		boolean locked = false;

		try {
			System.out.println(Thread.currentThread().getName() + " trying to lock");
			locked = reLock.tryLock(2000, TimeUnit.MILLISECONDS);

			if (!locked) {
				System.out.println(Thread.currentThread().getName() + " could not acquire lock");
				return new OrdersResult(order.getOrdersId(), OrderStatus.FAILED, "not able to acquire the lock");

			} else {
				System.out.println(
						order.getOrdersId() + " " + order.getItems().getItemName() + " " + order.getOrderQuantity());
				Items orderItem = order.getItems();
				int orderQuantity = order.getOrderQuantity();
				int availableQuantityInInventory = presentStock.get(order.getItems());

				if (orderItem == null) {
					return new OrdersResult(order.getOrdersId(), OrderStatus.FAILED, "Item not found in the inventory");
				}

				// out of stock or invalid
				if (availableQuantityInInventory <= 0) {
					presentStock.put(orderItem, 0);
					return new OrdersResult(order.getOrdersId(), OrderStatus.FAILED, "insufficient stock");
				}

				if (orderQuantity > availableQuantityInInventory) {
					return new OrdersResult(order.getOrdersId(), OrderStatus.FAILED, "insufficient stock");
				}
				// update the stock if all right
				int remaningQuantity = orderQuantity - availableQuantityInInventory;
				presentStock.put(orderItem, remaningQuantity);

				return new OrdersResult(order.getOrdersId(), OrderStatus.SUCCESS, "Order placed successfully");

			}

		} catch (InterruptedException e) {
			e.printStackTrace();
			return new OrdersResult(order.getOrdersId(), OrderStatus.FAILED,
					"an exception occur while trying the lock");

		} finally {
			if (locked) {
				reLock.unlock();
			}
		}

	}

	public Items getItemName(String itemName) {
		return presentStock.keySet().stream().filter(i -> i.getItemName().equalsIgnoreCase(itemName)).findFirst()
				.orElse(null);
	}
}
