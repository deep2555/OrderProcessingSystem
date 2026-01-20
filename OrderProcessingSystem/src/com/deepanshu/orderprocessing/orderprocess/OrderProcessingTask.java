package com.deepanshu.orderprocessing.orderprocess;

import com.deepanshu.orderprocessing.filehandling.FileHandlingProcess;

public class OrderProcessingTask {

	public static void main(String[] args) {
		String location = "C:\\Users\\deepa\\git\\OrderProcessingSystem\\Orders.txt";
		FileHandlingProcess fileHandlingProcess = new FileHandlingProcess();
		fileHandlingProcess.readFileFromLocation(location);
		
	}
}
