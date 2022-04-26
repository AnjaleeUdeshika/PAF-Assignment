package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class inquiry {
	
public String insertInquiry(String accNo, String cusName, String date, String complain) {
		
		String output = "";
		
		try {
			
			connection con = new connection();
			
			Connection con1 = con.connect();
			
			if(con1 == null) {
				
				return"Error while connecting to the databse for inserting";
				
			}
			
			String query = "insert into inquiry values (?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con1.prepareStatement(query);
			
			//binding values
			preparedStmt.setInt(1,0);
			preparedStmt.setString(2,accNo);
			preparedStmt.setString(3,cusName);
			preparedStmt.setString(4,date);
			preparedStmt.setString(5,complain);
			
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
	
	public String readInquiry() {
		
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
					+ "			<th>Account No</th><th>Customer Name</th><th>Date</th><th>Complain</th><th>Update</th><th>Remove</th>\r\n"
					+ "		</tr>";
			
			String query = "select * from inquiry";
			Statement stmt = con1.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				
				String inqID = Integer.toString(rs.getInt("inqID"));
				String accNo = rs.getString("accNo");
				String cusName = rs.getString("cusName");
				String date = rs.getString("date");
				String complain = rs.getString("complain");
				
				//add a row into the html table
				output += "<tr>"
						+ "			<td>" +accNo+ "</td><td>" +cusName+ "</td><td>" +date+ "</td><td>" +complain+ "</td>"
						+ "			<td><form method='post' action='Home.jsp'><input name='btnUpdate' type='submit' value='Update'><input name='inqID' type='hidden' value='"+inqID+"'><input name='accNo' type='hidden' value='"+accNo+"'><input name='cusName' type='hidden' value='"+cusName+"'><input name='date' type='hidden' value='"+date+"'><input name='complain' type='hidden' value='"+complain+"'></form></td>"
						+ "			<td><form method='post' action='Home.jsp'><input name='btnRemove' type='submit' value='Remove'><input name='inqID' type='hidden' value='"+inqID+"'><input name='accNo' type='hidden' value='"+accNo+"'></form></td>"
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
	
	public String updateInquiry(String inqID, String accNo, String cusName, String date, String complain) {
		
		String output = "";
		
		try {
			
			connection con = new connection();
			
			Connection con1 = con.connect();
			
			if(con1 == null) {
				
				return"Error while connecting to the databse for updating";
				
			}
			
			String query = "update inquiry set accNo=?, cusName=?, date=?, complain=? where inqID=?";
			
			PreparedStatement preparedStmt = con1.prepareStatement(query);
			
			//binding values
			preparedStmt.setString(1,accNo);
			preparedStmt.setString(2,cusName);
			preparedStmt.setString(3,date);
			preparedStmt.setString(4,complain);
			preparedStmt.setInt(5, Integer.parseInt(inqID));
			
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
	
	public String deleteInquiry(String inqID) {
		
		String output = "";
		
		try {
			
			connection con = new connection();
			
			Connection con1 = con.connect();
			
			if(con1 == null) {
				
				return"Error while connecting to the databse for deleting";
				
			}
			
			String query = "delete from inquiry where inqID=?";
			
			PreparedStatement preparedStmt = con1.prepareStatement(query);
			
			//binding values
			preparedStmt.setInt(1,Integer.parseInt(inqID));
			
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
