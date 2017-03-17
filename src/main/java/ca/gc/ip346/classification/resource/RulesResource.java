package ca.gc.ip346.classification.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Path("/rulesets")

@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class RulesResource {
	private static final Logger logger = LogManager.getLogger(RulesResource.class);

	public RulesResource() {
	}

	/**
	 * Sprint  9 - Build a REST service to return rulesets
	 */
	@GET
	public void getRules() {
	}

	/**
	 * Sprint 10 - Build a REST service to return a particular ruleset
	 */
	@GET
	@Path("id")
	public void selectRules() {
	}

	/**
	 * Sprint 11 - Build REST service to create new ruleset
	 */
	@PUT
	public void createRules() {
	}

	/**
	 * Sprint 12 - Build REST service to update and existing ruleset
	 */
	@POST
	@Path("id")
	public void updateRules() {
	}
}
