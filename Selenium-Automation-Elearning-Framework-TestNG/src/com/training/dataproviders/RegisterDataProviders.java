package com.training.dataproviders;

import java.util.List;

import org.testng.annotations.DataProvider;

import com.training.bean.RegisterBean;
import com.training.dao.RetailDAO;
import com.training.readexcel.ApachePOIExcelRead;


public class RegisterDataProviders {
	
	@DataProvider(name = "db-inputs")
	public Object [][] getDBData() {

		List<RegisterBean> list = new RetailDAO().getUserDetails(); 
		
		Object[][] result = new Object[list.size()][]; 
		int count = 0; 
		for(RegisterBean temp : list){
			Object[]  obj = new Object[4]; 
			obj[0] = temp.getfirstName();
			obj[1] = temp.getlastName(); 
			obj[2] = temp.getEmail();
			obj[3] = temp.getPhone();
			
			result[count ++] = obj; 
		}
		return result;
	}
	
	@DataProvider(name = "excel-inputs")
	public Object[][] getExcelData(){
		String fileName ="C:/Users/ArokiyaJeniferL/Desktop/Selenium/data/testdata.xlsx"; 
		return new ApachePOIExcelRead().getExcelContent(fileName); 
	}
	
	@DataProvider(name = "excel-inputs1")
	public Object[][] getExcelData1(){
		String fileName ="C:/Users/ArokiyaJeniferL/Desktop/Selenium/data/testdata1.xlsx"; 
		return new ApachePOIExcelRead().getExcelContent(fileName); 
	}
}
