package com;

import model.ResearchApprovalStatus;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/ResearchStatus")
public class ResearchApprovalStatusManagementService {

	ResearchApprovalStatus ApprovalStatus = new ResearchApprovalStatus();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readResearch() {
		return ApprovalStatus.readApprovalstatus();
	}
}
