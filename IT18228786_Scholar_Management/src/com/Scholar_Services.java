package com;

import model.Scholar;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Scholars")

public class Scholar_Services {

	Scholar scholar = new Scholar();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readresearchstatus() {
		return scholar.readresearchstatus();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertresearchstatus(@FormParam("researchID") String researchID,
			@FormParam("progress") String progress, @FormParam("comment") String comment,
			@FormParam("approval") String approval) {
		String output = scholar.insertresearchstatus(researchID, progress, comment, approval);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatesresearchstatus(String ResearchStatusData) {
		// Convert the input string to a JSON object
		JsonObject ResearchStatusObject = new JsonParser().parse(ResearchStatusData).getAsJsonObject();
		// Read the values from the JSON object

		String refID = ResearchStatusObject.get("refID").getAsString();
		String researchID = ResearchStatusObject.get("researchID").getAsString();
		String progress = ResearchStatusObject.get("progress").getAsString();
		String comment = ResearchStatusObject.get("comment").getAsString();
		String approval = ResearchStatusObject.get("approval").getAsString();
		String output = scholar.updatesresearchstatus(refID, researchID, progress, comment, approval);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteResearchStatus(String ResearchStatusData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(ResearchStatusData, "", Parser.xmlParser());

		String refid = doc.select("refID").text();
		String output = scholar.deleteresearchstatus(refid);
		return output;
	}

}
