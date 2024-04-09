package com.opencart.DataProvider;

import org.testng.annotations.DataProvider;

import com.opencart.util.ExcelUtil;

import open.cart.pojo.Product;

public class ProductDataProvider {
	
@DataProvider(name="dataProviderPojo")
public Object[][] dataProvider() {
	return new Object[][] {
		{new Product("Macbook","MacBook Pro",4)},
		{new Product("imac", "iMac",4)},
		{new Product("samsung","Samsung Galaxy Tab 10.1",7)},
		{new Product("samsung","Samsung SyncMaster 941BW",1)},
		
	};
	
}

@DataProvider(name="getProduct")
public Object[][] getProductImagesData() {
	return new Object[][] {
		
		{"Macbook","MacBook Pro",4},
		{"imac", "iMac",3},
		{"samsung","Samsung Galaxy Tab 10.1",7},
		{"samsung","Samsung SyncMaster 941BW",1}
		
	};
}

@DataProvider(name="productSearchKey")
public Object[][] getProductSearchKey() {
	return new Object[][] {
		
		{"Macbook"},
		{"iMac"},
		{"samsung"}
		
	};
}

@DataProvider(name="registrationDataExcel")
public Object[][] getRegistrationData(){
	Object data[][]=ExcelUtil.doGetData();

	return data;	
}
}
