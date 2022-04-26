package model;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Consumer {
	

		private Connection connect() {
			Connection con = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");

				// Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/paf?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
						"root", "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return con;
		}

	//insert function
		public String insertConsumer(String name, String address, String mobnum, String email, String nic, String username, String password) {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for inserting.";
				}
				// create a prepared statement
				String query = " insert into consumerp(`conID`,`conName`,`conAddress`,`mobNum`,`conEmail`,`conNIc`,`username`,`password`)"
						+ " values (?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, name);
				preparedStmt.setString(3, address);
				preparedStmt.setString(4, mobnum);
				preparedStmt.setString(5, email);
				preparedStmt.setString(6, nic);
				preparedStmt.setString(7, username);
				preparedStmt.setString(8, password);
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Inserted successfully";
			} catch (Exception e) {
				output = "Error while inserting the customer.";
				System.err.println(e.getMessage());
			}
			return output;
		}

	//read function
		public String readConsumer() {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for reading.";
				}
				// Prepare the html table to be displayed
				output = "<table border=\"1\"><tr><th>Consumer ID</th><th>Consumer Name</th><th>Address</th><th>Mobile Number</th><th>Email</th><th>NIC</th><th>username</th><th>password</th></tr>";
				String query = "select * from consumerp";
				Statement stmt = (Statement) con.createStatement();
				ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
				// iterate through the rows in the result set
				while (rs.next()) {
					String conID = Integer.toString(rs.getInt("conID"));
					String conName = rs.getString("conName");
					String conAddress = rs.getString("conAddress");
					String mobNum = rs.getString("mobNum");
					String conEmail = rs.getString("conEmail");
					String conNic = rs.getString("conNic");
					String username = rs.getString("username");
					String password = rs.getString("password");

					// Add into the html table
					output += "<tr><td>" + conID + "</td>";
					output += "<td>" + conName + "</td>";
					output += "<td>" + conAddress + "</td>";
					output += "<td>" + mobNum + "</td>";
					output += "<td>" + conEmail + "</td>";
					output += "<td>" + conNic + "</td>";
					output += "<td>" + username + "</td>";
					output += "<td>" + password + "</td>";
					
				}
				con.close();
				// Complete the html table
				output += "</table>";
			} catch (Exception e) {
				output = "Error while reading the customer.";
				System.err.println(e.getMessage());
			}
			return output;
		}

	//update function
		public String updateConsumer(String ID, String name, String address, String mobnum, String email, String nic, String username,
				String password) {
			String output = "";

			try {
				Connection con = connect();

				if (con == null) {
					return "Error while connecting to the database for updating.";
				}

				// create a prepared statement
				String query = "UPDATE consumerp SET conName=?,conAddress=?,mobNum=?,conEmail=?,conNic=?,username=?,password=?" + "WHERE conID=?";

				PreparedStatement preparedStmt = con.prepareStatement(query);

				// binding values
				preparedStmt.setString(1, name);
				preparedStmt.setString(2, address);
				preparedStmt.setString(3, mobnum);
				preparedStmt.setString(4, email);
				preparedStmt.setString(5, nic);
				preparedStmt.setString(6, username);
				preparedStmt.setString(7, password);
				preparedStmt.setInt(8, Integer.parseInt(ID));

				// execute the statement
				preparedStmt.execute();
				con.close();

				output = "Updated successfully";
			} catch (Exception e) {
				output = "Error while updating the consumer.";
				System.err.println(e.getMessage());
			}

			return output;
		}

	//delete function
		public String deleteConsumer(String conID) {
			String output = "";

			try {
				Connection con = connect();

				if (con == null) {
					return "Error while connecting to the database for deleting.";
				}

				// create a prepared statement
				String query = "delete from consumerp where conID=?";

				PreparedStatement preparedStmt = con.prepareStatement(query);

				// binding values
				preparedStmt.setInt(1, Integer.parseInt(conID));

				// execute the statement
				preparedStmt.execute();
				con.close();

				output = "Deleted successfully";
			} catch (Exception e) {
				output = "Error while deleting the customer.";
				System.err.println(e.getMessage());
			}

			return output;
		}

	}
