package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Bill {
	
public String insertBill(String userName, String userAddress, String userMobile, float units , float amount , float arrears , float finalBill) {
		
		String output = "";
		
		try {
			
			connection con = new connection();
			
			Connection con1 = con.connect();
			
			if(con1 == null) {
				
				return"DB connection is unsucessfull";
				
			}
			
			String query = "insert into inquiry values (?, ?, ?, ?, ? , ? , ? )";
			
			PreparedStatement preparedStmt = con1.prepareStatement(query);
			
			//binding values
			preparedStmt.setInt(1,0);
			preparedStmt.setString(2,userName);
			preparedStmt.setString(3,userAddress);
			preparedStmt.setString(4,userMobile);
			preparedStmt.setFloat(5,units);
			preparedStmt.setFloat(6,amount);
			preparedStmt.setFloat(7,arrears);
			preparedStmt.setFloat(8,finalBill);
			
			//execute the statement
			preparedStmt.execute();
			con1.close();
			
			output = "Data Inserted Successfully";
			
		}
		catch(Exception e) {
			
			output = "Not Inserted Sucessfully!";
			System.err.println(e.getMessage());
			
		}
		
		return output;
		
	}
	
	public String readBill() {
		
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
					+ "			<th>User ID</th><th>User Name</th><th>user Address</th><th>User Mobile</th><th>Units</th><th>Amount</th><th>Arrears</th><th>Final Bill</th> <th>Update</th><th>Remove</th>\r\n"
					+ "		</tr>";
			
			String query = "select * from bills";
			Statement stmt = con1.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				
				String userID = Integer.toString(rs.getInt("userID"));
				String userName = rs.getString("userName");
				String userAddress = rs.getString("userAddress");
				String userMobile = Integer.toString(rs.getInt("userMobile"));
				String units = Float.toString(rs.getFloat("units"));
				String amount = Float.toString(rs.getFloat("amount"));
				String arrears = Float.toString(rs.getFloat("arrears"));
				String finalBill = Float.toString(rs.getFloat("finalBill"));
				
				//add a row into the html table
				output += "<tr>"
						+ "			<td>" +userID+ "</td><td>" +userName+ "</td><td>" +userAddress+ "</td><td>" +userMobile+ "</td> <td>\" +units+ \"</td> <td>\" +amount+ \"</td> <td>\" +arrears+ \"</td> <td>\" +finalBill+ \"</td> "
						+ "			<td><form method='post' action='TotalBills.jsp'><input name='btnUpdate' type='submit' value='Update'><input name='userID' type='hidden' value='"+userID+"'><input name='userName' type='hidden' value='"+userName+"'><input name='cusName' type='hidden' value='"+userAddress+"'><input name='date' type='hidden' value='"+userMobile+"'><input name='complain' type='hidden' value='"+userMobile+"'><input name='units' type='hidden' value='\"+units+\"'><input name='amount' type='hidden' value='\"+amount+\"'><input name='arrears' type='hidden' value='\"+arrears+\"'><input name='finalBill' type='hidden' value='\"+finalBill+\"'></form></td>"
					
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
	
	public String updateBills(Integer userID, String userName, String userAddress, String userMobile , Float units , Float smount , Float arrears , Float finalBill  ) {
		
		String output = "";
		
		try {
			
			connection con = new connection();
			
			Connection con1 = con.connect();
			
			if(con1 == null) {
				
				return"Error while connecting to the databse for updating";
				
			}
			
			String query = "update inquiry set userID=?, userName=?, userAddress=?, userMobile=? units=?, amount=?,arrears=?,finalBill=? where userID =?";
			
			PreparedStatement preparedStmt = con1.prepareStatement(query);
			
			//binding values
			
			preparedStmt.setString(1,userName);
			preparedStmt.setString(2,userAddress);
			preparedStmt.setString(3,userMobile);
			preparedStmt.setFloat(4, Float.parseFloat(units));
			preparedStmt.setFloat(5, Float.parseFloat(amount));
			preparedStmt.setFloat(6, Float.parseFloat(arrears));
			preparedStmt.setFloat(7, Float.parseFloat(finalBill));
			preparedStmt.setInt(8, Integer.parseInt(userID));
			
			
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
	
	public String deleteBill(String inqID) {
		
		String output = "";
		
		try {
			
			connection con = new connection();
			
			Connection con1 = con.connect();
			
			if(con1 == null) {
				
				return"Error while connecting to the databse for deleting";
				
			}
			
			String query = "delete from bills where userID = ? ";
			
			PreparedStatement preparedStmt = con1.prepareStatement(query);
			
			//binding values
			preparedStmt.setInt(1,Integer.parseInt(userID));
			
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
