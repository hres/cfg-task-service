package ca.gc.ip346.classification.resource;

import static javax.ws.rs.HttpMethod.*;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.GsonBuilder;

@Path("/rulesets")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class RulesResource {
	private static final Logger logger = LogManager.getLogger(RulesResource.class);
	private Map<String, String> map = null;

	public RulesResource() {
		map = new HashMap<String, String>();
	}

	/**
	 * Sprint 5 - Build REST service to return rulesets
	 */
	@GET
	public Response getRules() {
		map.put("message", "REST service to return rulesets");
		logger.error("\n[01;32m" + new GsonBuilder().setDateFormat("yyyy-MM-dd").setPrettyPrinting().create().toJson(map) + "[00;00m");
		return FoodsResource.getResponse(GET, Response.Status.OK, map);
	}

	/**
	 * Sprint 5 - Build REST service to return a particular ruleset
	 */
	@GET
	@Path("id")
	public Response selectRules(@PathParam("id") String id) {
		map.put("message", "REST service to return a particular ruleset");
		logger.error("\n[01;32m" + new GsonBuilder().setDateFormat("yyyy-MM-dd").setPrettyPrinting().create().toJson(map) + "[00;00m");
		return FoodsResource.getResponse(GET, Response.Status.OK, map);
	}

	/**
	 * Sprint 5 - Build REST service to create new ruleset
	 */
	@POST
	public Response createRules() {
		map.put("message", "REST service to create new ruleset");
		logger.error("\n[01;32m" + new GsonBuilder().setDateFormat("yyyy-MM-dd").setPrettyPrinting().create().toJson(map) + "[00;00m");
		return FoodsResource.getResponse(POST, Response.Status.OK, map);
	}

	/**
	 * Sprint 5 - Build REST service to update an existing ruleset
	 */
	@PUT
	@Path("id")
	public Response updateRules(@PathParam("id") String id) {
		map.put("message", "REST service to update an existing ruleset");
		logger.error("\n[01;32m" + new GsonBuilder().setDateFormat("yyyy-MM-dd").setPrettyPrinting().create().toJson(map) + "[00;00m");
		return FoodsResource.getResponse(PUT, Response.Status.OK, map);
	}

	/**
	 * Sprint 5 - Build REST service to delete an existing ruleset
	 */
	@DELETE
	@Path("id")
	public Response deleteRules(@PathParam("id") String id) {
		map.put("message", "REST service to delete an existing ruleset");
		logger.error("\n[01;32m" + new GsonBuilder().setDateFormat("yyyy-MM-dd").setPrettyPrinting().create().toJson(map) + "[00;00m");
		return FoodsResource.getResponse(DELETE, Response.Status.OK, map);
	}
}
