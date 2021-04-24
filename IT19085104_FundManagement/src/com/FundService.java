//IT19085104
//Somawansa R.P.

package com;

import model.Fund; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/Funds")
public class FundService {
	Fund fundObj = new Fund();

	//read fund Data
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readFunds() {
		return fundObj.readFunds();
	}

	//insert fund Data
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertFund(@FormParam("ID") String ID, @FormParam("researchID") String researchID,
			@FormParam("name") String name, @FormParam("price") String price, @FormParam("comments") String comments,
			@FormParam("email") String email)

	{
		String output = fundObj.insertFund(ID, researchID, name, price, comments, email);
		return output;
	}

	//update fund Data
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateFund(String fundData) {
//Convert the input string to a JSON object 
		JsonObject fundObject = new JsonParser().parse(fundData).getAsJsonObject();
//Read the values from the JSON object
		String id = fundObject.get("ID").getAsString();
		String researchID = fundObject.get("researchID").getAsString();
		String name = fundObject.get("name").getAsString();
		String price = fundObject.get("price").getAsString();
		String comments = fundObject.get("comments").getAsString();
		String email = fundObject.get("email").getAsString();
		String output = fundObj.updateFund(id, researchID, name, price, comments, email);
		return output;
	}

	//delete fundData
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteFund(String fundData) {
//Convert the input string to an XML document
		Document doc = Jsoup.parse(fundData, "", Parser.xmlParser());

//Read the value from the element <itemID>
		String ID = doc.select("ID").text();
		String output = fundObj.deleteFund(ID);
		return output;
	}

}
