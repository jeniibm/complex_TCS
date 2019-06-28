package com.training.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


import com.training.bean.RegisterBean;
import com.training.connection.GetConnection;
import com.training.utility.LoadDBDetails;

public class RetailDAO {
	

	Properties properties; 
	
	public RetailDAO() {
		 try {
			properties = new Properties();
			FileInputStream inStream = new FileInputStream("./resources/sql.properties");
			properties.load(inStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<RegisterBean> getUserDetails(){
		String sql = properties.getProperty("get.UserDetails"); 
		
		GetConnection gc  = new GetConnection(); 
		List<RegisterBean> list = null;
		try {
			gc.ps1 = GetConnection.getMySqlConnection(LoadDBDetails.getDBDetails()).prepareStatement(sql); 
			list = new ArrayList<RegisterBean>(); 
			
			gc.rs1 = gc.ps1.executeQuery(); 
			
			while(gc.rs1.next()) {
			
				RegisterBean temp = new RegisterBean(); 
				temp.setfirstName(gc.rs1.getString(1));
				temp.setlastName(gc.rs1.getString(2));
				temp.setEmail(gc.rs1.getString(3));
				temp.setPhone(gc.rs1.getLong(4));

				list.add(temp); 
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list; 
	}
	
	public static void main(String[] args) {
		new RetailDAO().getUserDetails().forEach(System.out :: println);
	}
	

}
