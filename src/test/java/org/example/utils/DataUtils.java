package org.example.utils;

import java.lang.reflect.Method;

import org.example.base.BaseTest;
import org.testng.annotations.DataProvider;



public class DataUtils extends BaseTest {
	@DataProvider(name="dp")
	public Object[][] getData(Method m){
				
		String sheetName=m.getName();
		int noOfRows=excel.getRowCount(sheetName);
		int noOfCols=excel.getColumnCount(sheetName);
		
		Object[][] data = new Object[noOfRows-1][noOfCols];
		
		for(int row=2;row<=noOfRows;row++) {
			for(int col=0;col<noOfCols;col++) {
				data[row-2][col] = excel.getCellData(sheetName,col,row);
				
			}
		}
		return data; 
	}
	
	
}
