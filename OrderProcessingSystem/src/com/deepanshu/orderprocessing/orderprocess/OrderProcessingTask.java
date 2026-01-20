package com.deepanshu.orderprocessing.orderprocess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deepanshu.orderprocessing.domainentity.Items;
import com.deepanshu.orderprocessing.domainentity.Orders;
import com.deepanshu.orderprocessing.domainservice.Inventory;
import com.deepanshu.orderprocessing.filehandling.FileHandlingProcess;
import com.deepanshu.orderprocessing.orderresultstatus.OrdersResult;

public class OrderProcessingTask {

	public static void main(String[] args) {
		String location = "C:\\Users\\deepa\\git\\OrderProcessingSystem\\Orders.txt";
		FileHandlingProcess fileHandlingProcess = new FileHandlingProcess();

		// Step 1 create inventory
		Map<Items, Integer> listOfItem = new HashMap<>();
		Items laptop = new Items(1, "Laptop");
		Items mobile = new Items(2, "Mobile");
		Items tablet = new Items(3, "Tablet");
		Items headphone = new Items(3, "Headphone");

		listOfItem.put(laptop, 3);
		listOfItem.put(mobile, 3);
		listOfItem.put(tablet, 3);
		listOfItem.put(headphone, 3);

		Inventory inventory = new Inventory(listOfItem);

		// Step 2 read orders from file
		List<Orders> orders = fileHandlingProcess.readFileFromLocation(location, inventory);

		/*Step 3 validate the order list with inventory
		 * after getting list of orders now validate it with the inventory like the
		 * quantity is available or not
		 */
		List<OrdersResult> result = new ArrayList<>();
		for (Orders listOfOrders : orders) {
			result.add(inventory.checkAndUpdateStock(listOfOrders));
			
		}
		for(OrdersResult test : result) {
			System.out.println(test.getOrderId()+ " " + test.getOrderStatus()  + " " + test.getStatus());
		}

	}
}
