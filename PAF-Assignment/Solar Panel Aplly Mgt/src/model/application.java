package model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ws.rs.FormParam;



//@FormParam("nic") String cusName,
//@FormParam("address") String date,
//@FormParam("phone") String complain),
//@FormParam("email") String cusName,
//@FormParam("area") String date,
//@FormParam("service_center") String complain),
//@FormParam("solar_panel") String complain)

public class application {
	
public String insertApplication(String applicationNo, String name, String address, int phone, String email, String area, String service_center, String solar_panel) {
		
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
			preparedStmt.setString(2,applicationNo);
			preparedStmt.setString(3,name);
			preparedStmt.setString(4,address);
			preparedStmt.setInt(5,phone);
			preparedStmt.setString(6,email);
			preparedStmt.setString(7,area);
			preparedStmt.setString(8,service_center);
			preparedStmt.setString(9,solar_panel);
			
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
	
	public String readApplication() {
		
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
					+ "			<th>Name</th><th>Address</th><th>Phone</th><th>E-mail</th><th>Area</th><th>Service Center</th><th>Solar Panel</th><th>Remove</th>\r\n"
					+ "		</tr>";
			
			String query = "select * from personal";
			Statement stmt = con1.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				
				String applicationID = Integer.toString(rs.getInt("applicationID"));
				String name = rs.getString("name");
				String address = rs.getString("address");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				String area = rs.getString("area");
				String service_center = rs.getString("service_center");
				String solar_panel = rs.getString("solar_panel");
				
				//add a row into the html table
				output += "<tr>"
						+ "			<td>" +applicationNo+ "</td><td>" +name+ "</td><td>" +address+ "</td><td>" +phone+ "</td>"<td>" +phone+ "</td> <td>" +email+ "</td> <td>" +area+ "</td><td>" +service_center+ "</td>"<td>\" +solar_panel+ \"</td>\
						+ "			<td><form method='post' action='Home.jsp'><input name='btnUpdate' type='submit' value='Update'><input name='inqID' type='hidden' value='"+applicationID+"'><input name='applicationNo' type='hidden' value='"+applicationNo+"'><input name='name' type='hidden' value='"+name+"'><input name='address' type='hidden' value='"+address+"'><input name='phone' type='hidden' value='"+phone+"'></form></td>"
						+ "			<td><form method='post' action='Home.jsp'><input name='btnRemove' type='submit' value='Remove'><input name='inqID' type='hidden' value='"+applicationID+"'><input name='applicationNo' type='hidden' value='"+applicationNo+"'></form></td>"
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
	
	public String updateApplication(String applicationID, String applicationNo, String name, String address, String phone, String email, String area, String service_center, String solar_panel) {
		
		String output = "";
		
		try {
			
			connection con = new connection();
			
			Connection con1 = con.connect();
			
			if(con1 == null) {
				
				return"Error while connecting to the databse for updating";
				
			}
			
			String query = "update inquiry set applicationNo=?, name=?, date=?, address=? where applicationID=?";
			
			PreparedStatement preparedStmt = con1.prepareStatement(query);
			
			//binding values
			preparedStmt.setString(1,applicationNo);
			preparedStmt.setString(2,name);
			preparedStmt.setString(3,phone);
			preparedStmt.setString(6,email);
			preparedStmt.setString(7,area);
			preparedStmt.setString(8,service_center);
			preparedStmt.setString(9,solar_panel);
			preparedStmt.setInt(5, Integer.parseInt(applicationID));
			
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
	
	public String deleteApplication(String applicationID) {
		
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
			preparedStmt.setInt(1,Integer.parseInt(applicationID));
			
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
