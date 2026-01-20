package com.deepanshu.orderprocessing.filehandling;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
 * file handling class to deal with the reading and writing or file related process
*/
public class FileHandlingProcess {

	public void readFileFromLocation(String fileLocation) {

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
					String item = data[1].trim();
					int itemQuantity = Integer.parseInt(data[2].trim());

					System.out.println("order Id is: " + orderId);
					System.out.println("order item is: " + item);
					System.out.println("order quantity is: " + itemQuantity);
					System.out.println("********************************");

				} catch (NumberFormatException numberFormatException) {
					System.err.println("format of file is not correct: " + numberFormatException);
				}

			}

		} catch (FileNotFoundException e) {
			System.err.println("file is not located on the location");

		} catch (IOException e) {
			System.err.println("an error occured at io opoeration" + e);
		}
	}
}
