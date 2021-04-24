package com;

import model.Research;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Research")
public class ResearchManagementService {
	Research research = new Research();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readResearch() {
		return research.readResearch();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertResearch(@FormParam("title") String title, @FormParam("category") String category,
			@FormParam("description") String description, @FormParam("progress") String progress,
			@FormParam("estimateBudget") String estimateBudget, @FormParam("addedDate") String addedDate,
			@FormParam("approvalStatus") String approvalStatus, @FormParam("resercherName") String resercherName,
			@FormParam("resercherEmail") String resercherEmail) {
		String output = research.insertResearch(title, category, description, progress, estimateBudget, addedDate,
				approvalStatus, resercherName, resercherEmail);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateResearchData(String ResearchData) {
		// Convert the input string to a JSON object
		JsonObject ResearchDataObject = new JsonParser().parse(ResearchData).getAsJsonObject();
		// Read the values from the JSON object
		String id = ResearchDataObject.get("id").getAsString();
		String title = ResearchDataObject.get("title").getAsString();
		String category = ResearchDataObject.get("category").getAsString();
		String description = ResearchDataObject.get("description").getAsString();
		String progress = ResearchDataObject.get("progress").getAsString();
		String estimateBudget = ResearchDataObject.get("estimateBudget").getAsString();
		String approvalStatus = ResearchDataObject.get("approvalStatus").getAsString();
		String resercherName = ResearchDataObject.get("resercherName").getAsString();
		String resercherEmail = ResearchDataObject.get("resercherEmail").getAsString();

		String output = research.updateResearchData(id, title, category, description, progress, estimateBudget, approvalStatus, resercherName, resercherEmail);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteResearch(String ResearchData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(ResearchData, "", Parser.xmlParser());

		String id = doc.select("id").text();
		String output = research.deleteResearch(id);
		return output;
	}
}
