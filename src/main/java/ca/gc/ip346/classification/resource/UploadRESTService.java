package ca.gc.ip346.classification.resource;

import static javax.ws.rs.HttpMethod.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
// import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataParam;

import ca.gc.ip346.classification.model.Ruleset;
import ca.gc.ip346.util.ClassificationProperties;
import ca.gc.ip346.util.RequestURL;

@Path("")
public class UploadRESTService {
	// import static org.apache.logging.log4j.Level.*;
	private static final Logger logger = LogManager.getLogger(UploadRESTService.class);

	private static final String RULESETS_ROOT = "dtables";
	private static final String SLASH         = "/";
	private static final String EXTENSION     = ".xls";

	@Context
	private HttpServletRequest request;

	// @OPTIONS
	// @Path("/upload")
	// @Consumes(MediaType.MULTIPART_FORM_DATA)
	// @Produces(MediaType.APPLICATION_JSON)
	// public Response uploadFilePreflight() {
		// Map<String, String> msg = new HashMap<String, String>();
		// msg.put("message", "upload REST service pre-flight");
		// return FoodsResource.getResponse(OPTIONS, Response.Status.OK, msg);
	// }

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response uploadFile(@BeanParam Ruleset bean, @FormDataParam("rulesetname") String rulesetName) {
		String target = buildTarget();
		Map<?, ?> externalPath = (Map<?, ?>)getRulesetsHome().getEntity();
		String home = externalPath.get("rulesetshome").toString().replaceAll("^\\/", "").replaceAll("\\/$", "");
		Map<?, ?> availableSlot = (Map<?, ?>)getAvailableSlot().getEntity();
		if (availableSlot.get("slot") == null) {
			Map<String, String> msg = new HashMap<String, String>();
			msg.put("message", "No ruleset slots available");
			return FoodsResource.getResponse(POST, Response.Status.OK, msg);
		}
		String slot = availableSlot.get("slot").toString();
		//check ruleset name duplicate
		if(rulesetNameDuplicated(rulesetName)){
			Map<String, String> msg = new HashMap<String, String>();
			msg.put("message", "Ruleset Name Duplicated.");
			return FoodsResource.getResponse(POST, Response.Status.OK, msg);
		}

		Map<String, InputStream> streams = new HashMap<String, InputStream>();
		if (bean.getRefamtZ()     != null) streams.put(bean.getRefamtZ()     .getName(), bean.getRefamt());
		if (bean.getFopZ()        != null) streams.put(bean.getFopZ()        .getName(), bean.getFop());
		if (bean.getShortcutZ()   != null) streams.put(bean.getShortcutZ()   .getName(), bean.getShortcut());
		if (bean.getThresholdsZ() != null) streams.put(bean.getThresholdsZ() .getName(), bean.getThresholds());
		if (bean.getInitZ()       != null) streams.put(bean.getInitZ()       .getName(), bean.getInit());
		if (bean.getTierZ()       != null) streams.put(bean.getTierZ()       .getName(), bean.getTier());

		if (bean.getRefamtZ() == null || bean.getFopZ() == null || bean.getShortcutZ() == null || bean.getThresholdsZ() == null || bean.getInitZ() == null || bean.getTierZ() == null) {
			Map<String, String> msg = new HashMap<String, String>();
			msg.put("message", "All upload files need to be selected");
			return FoodsResource.getResponse(POST, Response.Status.BAD_REQUEST, msg);
		}
		
		//validate rule uploading rule files
		StringBuilder validateError = new StringBuilder();
		
		for (String rule : streams.keySet()) {	
			InputStream inputStream = streams.get(rule);
			if(!ruleValidate(inputStream))
				validateError.append(rule + " validate failed!\n");
		
		}
		if (validateError.length() > 10) {
			Map<String, String> msg = new HashMap<String, String>();
			msg.put("message", validateError.toString());
			return FoodsResource.getResponse(POST, Response.Status.OK, msg);
		}
		

		for (String rule : streams.keySet()) {
			OutputStream outputStream = null;
			InputStream inputStream = null;
			
			String filePath = SLASH + home + SLASH + RULESETS_ROOT + SLASH + rule + SLASH + slot + SLASH + rule + slot + EXTENSION;
			logger.debug("===========output: " + filePath );
			logger.debug("===========input: " + streams.get(rule) );
			
			try {
				int read     = 0;
				byte[] bytes = new byte[8 * 1024];
				
				File file = new File(filePath);
				
				if(file.exists()) {
					outputStream = new FileOutputStream(new File(filePath), false);
				}else
					outputStream = new FileOutputStream(new File(filePath));
					
				
				inputStream = streams.get(rule);
				
				while ((read = inputStream.read(bytes)) != -1) {
					outputStream.write(bytes, 0, read);
				}
				outputStream.flush();
				outputStream.close();
				inputStream.close();
			} catch(FileNotFoundException e) {
				Map<String, String> msg = new HashMap<String, String>();
				msg.put("message", "Tomcat account needs permissions to write to filesystem");
				return FoodsResource.getResponse(POST, Response.Status.UNAUTHORIZED, msg);
			} catch(IOException e) {
				Map<String, String> msg = new HashMap<String, String>();
				msg.put("message", e.getMessage());
				return FoodsResource.getResponse(POST, Response.Status.UNAUTHORIZED, msg);
			} finally {
				if (outputStream != null) {
					try {
						outputStream.close();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

		Map<String, Object> ruleset = new HashMap<String, Object>();
		ruleset.put("active", true);
		ruleset.put("isProd", false);
		if (rulesetName == null || rulesetName.trim().isEmpty()) {
			Date date = new Date();
			rulesetName = date.toString();
		}
		ruleset.put("name", rulesetName);
		ruleset.put("rulesetId", Integer.valueOf(slot));
		Response response = ClientBuilder
			.newClient()
			.target(target)
			.path("/rulesets")
			.request()
			.post(Entity.entity(ruleset, MediaType.APPLICATION_JSON));
		Map<String, String> msg = new HashMap<String, String>();
		msg.put("message", "Upload rulesets successful.");
		//return FoodsResource.getResponse(POST, Response.Status.OK, msg);
		return FoodsResource.getResponse(POST, Response.Status.OK, response.readEntity(Object.class));
	}

	public Response getRulesetsHome() {
		String target = buildTarget();
		Response response = ClientBuilder
			.newClient()
			.target(target)
			.path("/rulesetshome")
			.request()
			.get();

		return FoodsResource.getResponse(GET, Response.Status.OK, response.readEntity(Object.class));
	}

	public Response getAvailableSlot() {
		String target = buildTarget();
		Response response = ClientBuilder
			.newClient()
			.target(target)
			.path("/slot")
			.request()
			.get();

		return FoodsResource.getResponse(GET, Response.Status.OK, response.readEntity(Object.class));
	}

	private String buildTarget() {
		if ((request.getServerPort() == 80) || (request.getServerPort() == 443)) {
			return RequestURL.getHost() + ClassificationProperties.getEndPoint();
		} else if ((request.getServerPort() == 8080) || (request.getServerPort() == 8443)) {
			return RequestURL.getHost() + ":8080" + /* request.getServerPort() + */ ClassificationProperties.getEndPoint();
		}
		return RequestURL.getAddr() + ClassificationProperties.getEndPoint();
	}
	
	private boolean rulesetNameDuplicated(String rulesetName) {
		return false;
	}
	
	private boolean ruleValidate(InputStream rulesetStream) {
		return true;
	}
}
