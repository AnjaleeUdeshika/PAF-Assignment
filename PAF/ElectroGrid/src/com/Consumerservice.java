package com;


//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
import com.google.gson.*;
import model.Consumer;

//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/ConsumerID")
public class Consumerservice {
	
		Consumer consumerObj = new Consumer();

	//read
		@GET
		@Path("/{consumerId}")
		@Produces(MediaType.TEXT_HTML)
		public String readConsumer() {
			return consumerObj.readConsumer();
		}

	//insert	
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertConsumer(@FormParam("conName") String conName,
				
		 @FormParam("conAddress") String conAddress,
		 @FormParam("mobNum") String mobNum,
		 @FormParam("conEmail") String conEmail,
		 @FormParam("conNic") String conNic,
		 @FormParam("username") String username,
		 @FormParam("password") String password)
		{
		 String output = consumerObj.insertConsumer(conName, conAddress, mobNum, conEmail, conNic, username, password);
		return output;
		}
		
		//update
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateConsumer(String consumerData)
		{
		//Convert the input string to a JSON object
		 JsonObject consumerObject = new JsonParser().parse(consumerData).getAsJsonObject();
		//Read the values from the JSON object
		 String conID = consumerObject.get("conID").getAsString();
		 String conName = consumerObject.get("conName").getAsString();
		 String conAddress = consumerObject.get("conAddress").getAsString();
		 String mobNum = consumerObject.get("mobNum").getAsString();
		 String conEmail = consumerObject.get("conEmail").getAsString();
		 String conNic = consumerObject.get("conNic").getAsString();
		 String username = consumerObject.get("username").getAsString();
		 String password = consumerObject.get("password").getAsString();
		 String output = consumerObj.updateConsumer(conID, conName, conAddress, mobNum, conEmail, conNic,username, password);
		return output;
		} 
		
		
		//delete
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		public String deleteConsumer(String consumerData)
		{
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(consumerData, "", Parser.xmlParser());

		//Read the value from the element <itemID>
		 String conID = doc.select("conID").text();
		 String output = consumerObj.deleteConsumer(conID);
		return output;
		}
	
}
