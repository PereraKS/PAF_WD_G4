package com;

import model.MembershipLevels;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Membership")
public class MembershipManagementService {
	
	MembershipLevels levels = new MembershipLevels();

	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readMembershipLevels() {
		return levels.readMembershipLevels();
	}
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertMembershipLevels(@FormParam("membership_Name") String membership_Name,
			@FormParam("pricing") String pricing,
			@FormParam("benefits") String benefits,
			@FormParam("researchID") String researchID) {
		String output = levels.insertMembershipLevels(membership_Name, pricing, benefits, researchID);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateMembershipLevels(String MembershipLevelData) {
		// Convert the input string to a JSON object
		JsonObject MembershipLevelObject = new JsonParser().parse(MembershipLevelData).getAsJsonObject();
		// Read the values from the JSON object

		String id = MembershipLevelObject.get("id").getAsString();
		String membership_Name = MembershipLevelObject.get("membership_Name").getAsString();
		String pricing = MembershipLevelObject.get("pricing").getAsString();
		String benefits = MembershipLevelObject.get("benefits").getAsString();
		String researchID = MembershipLevelObject.get("researchID").getAsString();
		String output = levels.updateMembershipLevels(id, membership_Name, pricing, benefits, researchID);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteResearchStatus(String MembershipLevelData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(MembershipLevelData, "", Parser.xmlParser());

		String id = doc.select("id").text();
		String output = levels.deleteMembershipLevels(id);
		return output;
	}
	
}
