package com;
import model.buyer;
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;

@Path("/buyers") 
public class buyerService {
	
	buyer buyerObj = new buyer(); 
	
	//read buyers list
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readBuyer() 
	 { 
	 return buyerObj.readBuyer();
	 }
	
	// insert/ create buyers
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertBuyer(@FormParam("name") String name, 
	 @FormParam("email") String email, 
	 @FormParam("phone") String phone)
	 { 
	 String output = buyerObj.insertBuyer(name, email, phone); 
	return output; 
	}
	
	// Update operation
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateBuyer(String buyerData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject buyerObject = new JsonParser().parse(buyerData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String id = buyerObject.get("id").getAsString(); 
	 String name = buyerObject.get("name").getAsString(); 
	 String email = buyerObject.get("email").getAsString(); 
	 String phone = buyerObject.get("phone").getAsString(); 
	 
	 
	 String output = buyerObj.updateBuyer(id, name, email, phone); 
	return output; 
	}
	
	//Delete operation
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteItem(String buyerData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(buyerData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String id = doc.select("id").text(); 
	 String output = buyerObj.deleteBuyer(id); 
	return output; 
	}

	
	

}
