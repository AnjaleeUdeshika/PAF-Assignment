package com;

//public class applicationService {
//
//}

import model.application;

//for REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//for JSON
import com.google.gson.*;

//for XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Application")

public class applicationService {
	
	application inqObj = new application();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String Application() {
		
		return appObj.readApplicationy();
		
	}
	
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public String insertApplication(@FormParam("name") String accNo,
							@FormParam("nic") String cusName,
							@FormParam("address") String date,
							@FormParam("phone") String complain),
							@FormParam("email") String cusName,
							@FormParam("area") String date,
							@FormParam("service_center") String complain),
							@FormParam("solar_panel") String complain)
	{
		
		String output = appObj.insertApplication(accNo, cusName, date, complain);
		return output;
		
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatApplication(String applicationData)
	{
		//Convert input string to a JSON object
		JsonObject appObject = new JsonParser().parse(applicationData).getAsJsonObject();
		
		//Read values from JSON object
		String applicationID = appObject.get("applicationID").getAsString();
		String name = appObject.get("name").getAsString();
		String nic = appObject.get("nic").getAsString();
		String address = appObject.get("address").getAsString();
		String phone = appObject.get("phone").getAsString();
		String email = appObject.get("email").getAsString();
		String area = appObject.get("area").getAsString();
		String service_center = appObject.get("service_center").getAsString();
		String solar_panel = appObject.get("solar_panel").getAsString();
		
		
		String output = appObj.updateApplication(applicationID, name, nic, address, phone, email, area, service_center, solar_panel);
		return output;
		
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteApplication(String applicationData)
	{
		//Convert input string to a JSON object
		Document doc = Jsoup.parse(applicationData, "", Parser.xmlParser());
		
		//Read values from JSON object
		String applicationID = doc.select("applicationID").text();
		
		String output = inqObj.deleteApplication(applicationID);
		return output;
		
	}

}
