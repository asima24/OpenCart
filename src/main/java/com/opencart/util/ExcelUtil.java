package com.opencart.util;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	private static String path=".\\src\\test\\resources\\testData\\OpenCart_data.xlsx";
    
	public static String sheetName="register";
	public static FileInputStream fis;
	public static Workbook workbook;
	public  static Object[][] doGetData(){
	
		System.out.println("Reading data from sheet:"+sheetName);
		Object data[][]=null;
       try {
		fis=new FileInputStream(path);
	
	    workbook=WorkbookFactory.create(fis);
	   Sheet sheet=workbook.getSheet(sheetName);
	   int rowNum=sheet.getLastRowNum();
	   int colNum=sheet.getRow(0).getLastCellNum();
	   data=new Object[rowNum][colNum];
	      for(int i=0;i<rowNum;i++) {
	    	 for(int j=0;j<colNum;j++) {
	    		 data[i][j]=sheet.getRow(i+1).getCell(j).toString();
	    		 System.out.print( data[i][j] +"---");
	    	 }
	    	 System.out.println(" ");
	      }
	   
	}catch(Exception ex) {
		ex.printStackTrace();
	}
     return data;
	

	}}
