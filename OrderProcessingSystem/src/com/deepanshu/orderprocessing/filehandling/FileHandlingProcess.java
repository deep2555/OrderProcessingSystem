package com.deepanshu.orderprocessing.filehandling;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.deepanshu.orderprocessing.domainentity.Items;
import com.deepanshu.orderprocessing.domainentity.Orders;
import com.deepanshu.orderprocessing.domainservice.Inventory;

/*
 * file handling class to deal with the reading and writing or file related process
*/
public class FileHandlingProcess {

	public List<Orders> readFileFromLocation(String fileLocation, Inventory inventory) {

		List<Orders> listOfOrders = new ArrayList<>();

		try (BufferedReader fileReader = new BufferedReader(new FileReader(fileLocation))) {
			String line;

			// skip the header
			fileReader.readLine();

			while ((line = fileReader.readLine()) != null) {
				// remove the empty space or trim

				if (line.trim().isEmpty()) {
					continue;
				}
				// Split data stored in the string array
				String[] data = line.split(",");

				if (data.length != 3) {
					System.err.println("Invalid line (wrong format): " + line);
					continue;
				}
				try {

					int orderId = Integer.parseInt(data[0].trim());
					String orderName = data[1].trim();
					int itemQuantity = Integer.parseInt(data[2].trim());

					System.out.println("order Id is: " + orderId);
					System.out.println("order item is: " + orderName);
					System.out.println("order quantity is: " + itemQuantity);
					System.out.println("********************************");

					/*
					 * now here to put the item name in order object get the reference for the Item
					 * object by comparing the order item name to inventory item name.
					 * 
					 */
					Items itemName = inventory.getItemName(orderName);
					listOfOrders.add(new Orders(orderId, itemName, itemQuantity));

				} catch (NumberFormatException numberFormatException) {
					System.err.println("format of file is not correct: " + numberFormatException);
				}

			}

		} catch (FileNotFoundException e) {
			System.err.println("file is not located on the location");

		} catch (IOException e) {
			System.err.println("an error occured at io opoeration" + e);
		}
		return listOfOrders;
	}
}
