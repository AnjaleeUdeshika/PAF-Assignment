package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.schedule;

public class scheduleManag {

	schedule schObj = new schedule();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readSchedule() {
		
		return schObj.readSchedule();
		
	}
	
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public String insertSchedule(@FormParam("group") String group,
							@FormParam("date") String date,
							@FormParam("period") String period,
							@FormParam("area") String area)
	{
		
		String output = schObj.insertSchedule(group, date, period, area);
		return output;
		
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateSchedule(String group)
	{
		//Convert input string to a JSON object
		JsonObject scheduleObject = new JsonParser().parse(group).getAsJsonObject();
		
		//Read values from JSON object
		String group = group.get("group").getAsString();
		String date = date.get("date").getAsString();
		String period = period.get("period").getAsString();
		String area = area.get("area").getAsString();
		
		String output = schObj.updateSchedule(group, date, period, area);
		return output;
		
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteSchedule(String group)
	{
		//Convert input string to a JSON object
		Document doc = Jsoup.parse(group, "", Parser.xmlParser());
		
		//Read values from JSON object
		String groupS = doc.select("group").text();
		
		String output = schObj.deleteItem(groupS);
		return output;
		
	}
	
}
