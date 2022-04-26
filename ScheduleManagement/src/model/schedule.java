package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class schedule {
	
	public String insertDetails(int group, String date, String period, String area) {
		
		String output = "";
		
		try {
			
			connection con = new connection();
			
			Connection con1 = con.connect();
			
			if(con1 == null) {
				
				return"Error while connecting to the databse for inserting";
				
			}
			
			String query = "insert into schedule.schedulemanage values (?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con1.prepareStatement(query);
			
			//binding values
			preparedStmt.setInt(1,group);
			preparedStmt.setString(2,date);
			preparedStmt.setString(3,period);
			preparedStmt.setDouble(4,Double.parseDouble(area));
			
			
			//execute the statement
			preparedStmt.execute();
			con1.close();
			
			output = "Inserted Successfully";
			
		}
		catch(Exception e) {
			
			output = "Error while inserting";
			System.err.println(e.getMessage());
			
		}
		
		return output;
		
	}
	
	public String readItems() {
		
		String output = "";
		
		try {
			
			connection con = new connection();
			
			Connection con1 = con.connect();
			
			if(con1 == null) {
				
				return "Error while connecting to the database for reading";
				
			}
			
			//HTML table
			output = "<table border=\"1\">\r\n"
					+ "		<tr>\r\n"
					+ "			<th>Group</th><th>Date</th><th>Time period</th><th>Area</th><th>Update</th><th>Remove</th>\r\n"
					+ "		</tr>";
			
			String query = "select * from schedulemanage";
			Statement stmt = con1.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				
				String group = Integer.toString(rs.getInt("group"));
				String date = rs.getString("date");
				String period = rs.getString("period");
				String area = Double.toString(rs.getDouble("area"));
				
				
				//add a row into the html table
				output += "<tr>"
						+ "			<td>" +group+ "</td><td>" +date+ "</td><td>" +period+ "</td><td>" +area+ "</td>"
						+ "			<td><form method='post' action='Home.jsp'><input name='btnUpdate' type='submit' value='Update'><input name='itemID' type='hidden' value='"+group+"'><input name='itemCode' type='hidden' value='"+date+"'><input name='itemName' type='hidden' value='"+period+"'><input name='itemPrice' type='hidden' value='"+area+"'></form></td>"
						+ "			<td><form method='post' action='Home.jsp'><input name='btnRemove' type='submit' value='Remove'><input name='itemID' type='hidden' value='"+group+"'><input name='itemCode' type='hidden' value='"+date+"'></form></td>"
						+ "		</tr>";
				
			}
			
			con1.close();
			
			//complete the html table
			output += "</table>";
			
			
			
		}
		catch(Exception e) {
			
			output = "Error while reading the items";
			System.err.println(e.getMessage());
			
		}
		
		return output;
		
	}
	
	public String updateItem(String group, String date, String period, String area) {
		
		String output = "";
		
		try {
			
			connection con = new connection();
			
			Connection con1 = con.connect();
			
			if(con1 == null) {
				
				return"Error while connecting to the databse for updating";
				
			}
			
			String query = "update schedulemanage set group=?, date=?, period=?, area=? where group=?";
			
			PreparedStatement preparedStmt = con1.prepareStatement(query);
			
			//binding values
			preparedStmt.setString(1,group);
			preparedStmt.setString(2,date);
			preparedStmt.setDouble(3,Double.parseDouble(period));
			preparedStmt.setString(4,area);
			
			
			//execute the statement
			preparedStmt.execute();
			con1.close();
			
			output = "Updated Successfully";
			
		}
		catch(Exception e) {
			
			output = "Error while updating";
			System.err.println(e.getMessage());
			
		}
		
		return output;
		
	}
	
	public String deleteItem(String group) {
		
		String output = "";
		
		try {
			
			connection con = new connection();
			
			Connection con1 = con.connect();
			
			if(con1 == null) {
				
				return"Error while connecting to the databse for deleting";
				
			}
			
			String query = "delete from schedulemanage where group=?";
			
			PreparedStatement preparedStmt = con1.prepareStatement(query);
			
			//binding values
			preparedStmt.setInt(1,Integer.parseInt(group));
			
			//execute the statement
			preparedStmt.execute();
			con1.close();
			
			output = "Deleted Successfully";
			
		}
		catch(Exception e) {
			
			output = "Error while deleting";
			System.err.println(e.getMessage());
			
		}
		
		return output;
		
	}

}
