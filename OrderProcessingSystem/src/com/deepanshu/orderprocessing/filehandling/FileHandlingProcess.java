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
		
		try(BufferedReader fileReader = new BufferedReader(new FileReader(fileLocation))){
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
