package com;

import model.inquiry;

//for REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//for JSON
import com.google.gson.*;

//for XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Bill")

public class BillService {
	
	Bill BillObj = new Bill();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readBill() {
		
		return BillObj.readBill();
		
	}
	
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public String insertBill(@FormParam("userName") String accNo,
							@FormParam("userAddress") String cusName,
							@FormParam("userMobile") String date,
							@FormParam("units") String complain),
							@FormParam("amount") String complain),
							@FormParam("arrears") String complain),
							@FormParam("finalBill") String complain)
	{
		
		String output = BillObj.insertBill(userName, userAddress, userMobile, units , amount , arrears, finalBill);
		return output;
		
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateBill(String BillData)
	{
		//Convert input string to a JSON object
		JsonObject BillObject = new JsonParser().parse(BillData).getAsJsonObject();
		
		//Read values from JSON object
		String userID = BillObject.get("userID").getAsString();
		String userName = BillObject.get("userName").getAsString();
		String userAddress = BillObject.get("userAddress").getAsString();
		String userMobile = BillObject.get("userMobile").getAsString();
		String units = BillObject.get("units").getAsString();
		String amount = BillObject.get("amount").getAsString();
		String arrears = BillObject.get("arrears").getAsString();
		String finalBill = BillObject.get("finalBill").getAsString();
		
		
		String output = BillObj.updateBill(userID, userName, userAddress, userMobile, units , amount , arrears , finalBill);
		return output;
		
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteBill(String BillData)
	{
		//Convert input string to a JSON object
		Document doc = Jsoup.parse(BillData, "", Parser.xmlParser());
		
		//Read values from JSON object
		String userID = doc.select("userID").text();
		
		String output = BillObj.deleteBill(inqID);
		return output;
		
	}

}
