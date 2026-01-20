package com.deepanshu.orderprocessing.orderprocess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.deepanshu.orderprocessing.domainentity.Items;
import com.deepanshu.orderprocessing.domainentity.Orders;
import com.deepanshu.orderprocessing.domainservice.Inventory;
import com.deepanshu.orderprocessing.filehandling.FileHandlingProcess;
import com.deepanshu.orderprocessing.multithreadingtask.OrderProcessingThreads;
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

		/*
		 * Step 3 processing the orders concurrently using executor service
		 */
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		List<Future<OrdersResult>> orderProcessingResult = new ArrayList<>();
		for (Orders listOfOrders : orders) {
			orderProcessingResult.add(executorService.submit(new OrderProcessingThreads(inventory, listOfOrders)));
		}

		// print the future result
		for (Future<OrdersResult> listOfSuccessResult : orderProcessingResult) {
			try {
				OrdersResult result = listOfSuccessResult.get();

				System.out.println("OrderID: " + result.getOrderId() + ", Status: " + result.getOrderStatus() + ", Reason: "
						+ result.getStatus());
				System.out.println("***********************************");

			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		// shutdown the services
		executorService.shutdown();
		
		
		
		
		
//		List<OrdersResult> result = new ArrayList<>();
//		for (Orders listOfOrders : orders) {
//			result.add(inventory.checkAndUpdateStock(listOfOrders));
//			
//		}
//		for(OrdersResult test : result) {
//			System.out.println(test.getOrderId()+ " " + test.getOrderStatus()  + " " + test.getStatus());
//		}

	}
}
