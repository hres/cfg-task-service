package ca.gc.ip346.classification.resource;

import static javax.ws.rs.HttpMethod.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
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
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;

import ca.gc.ip346.classification.model.Ruleset;
import ca.gc.ip346.util.ClassificationProperties;
import ca.gc.ip346.util.RequestURL;
import ca.gc.ip346.util.Utils;

@Path("")
public class UploadRESTService {
	// import static org.apache.logging.log4j.Level.*;
	private static final Logger logger = LogManager.getLogger(UploadRESTService.class);

	private static final String RULESETS_ROOT = "dtables";
	private static final String SLASH         = "/";
	private static final String EXTENSION     = ".xls";
	
	private static final int BUFFER_SIZE = 8192;
	

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
		
		Map<String, InputStream> streamsRulesetValide = new HashMap<String, InputStream>();
		if (bean.getRefamtZ()     != null) streamsRulesetValide.put(bean.getRefamtZ()     .getName(), bean.getRefamt());
		if (bean.getFopZ()        != null) streamsRulesetValide.put(bean.getFopZ()        .getName(), bean.getFop());
		if (bean.getShortcutZ()   != null) streamsRulesetValide.put(bean.getShortcutZ()   .getName(), bean.getShortcut());
		if (bean.getThresholdsZ() != null) streamsRulesetValide.put(bean.getThresholdsZ() .getName(), bean.getThresholds());
		if (bean.getInitZ()       != null) streamsRulesetValide.put(bean.getInitZ()       .getName(), bean.getInit());
		if (bean.getTierZ()       != null) streamsRulesetValide.put(bean.getTierZ()       .getName(), bean.getTier());
		
		Map<String, InputStream> streamsRulesetCreate = new HashMap<String, InputStream>();
		if (bean.getRefamtZ()     != null) streamsRulesetCreate.put(bean.getRefamtZ()     .getName(), copyInputStream(bean.getRefamt()));
		if (bean.getFopZ()        != null) streamsRulesetCreate.put(bean.getFopZ()        .getName(), copyInputStream(bean.getFop()));
		if (bean.getShortcutZ()   != null) streamsRulesetCreate.put(bean.getShortcutZ()   .getName(), copyInputStream(bean.getShortcut()));
		if (bean.getThresholdsZ() != null) streamsRulesetCreate.put(bean.getThresholdsZ() .getName(), copyInputStream(bean.getThresholds()));
		if (bean.getInitZ()       != null) streamsRulesetCreate.put(bean.getInitZ()       .getName(), copyInputStream(bean.getInit()));
		if (bean.getTierZ()       != null) streamsRulesetCreate.put(bean.getTierZ()       .getName(), copyInputStream(bean.getTier()));
		
		
		if (bean.getRefamtZ() == null || bean.getFopZ() == null || bean.getShortcutZ() == null || bean.getThresholdsZ() == null || bean.getInitZ() == null || bean.getTierZ() == null) {
			Map<String, String> msg = new HashMap<String, String>();
			msg.put("message", "All upload files need to be selected");
			return FoodsResource.getResponse(POST, Response.Status.BAD_REQUEST, msg);
		}
		
		//check ruleset name duplicate
		if(rulesetNameDuplicated(rulesetName)){
			Map<String, String> msg = new HashMap<String, String>();
			msg.put("message", "Ruleset Name Duplicated.");
			return FoodsResource.getResponse(POST, Response.Status.BAD_REQUEST, msg);
		}

		
		//validate rule uploading rule files
		StringBuilder 	validateError = new StringBuilder();
		InputStream 	inputStream = null;
		OutputStream 	outputStream = null;
		String 			validate = null;
		
		for (String rule : streamsRulesetValide.keySet()) {	
			inputStream = streamsRulesetValide.get(rule);
			
			validate = ruleValidate(rule, inputStream);
			if(validate != null) {
				validateError.append(rule + " Rule File validate failed!=> <br>" + validate + " <br>");
				System.out.println(rule + " Rule File validate failed!=>" + validate + "\n");
			}
			
			
		}
		if (validateError.length() > 10) {
			Map<String, String> msg = new HashMap<String, String>();
			msg.put("message", Utils.toHtml(validateError.toString()));
			return FoodsResource.getResponse(POST, Response.Status.BAD_REQUEST, msg);
		}
		

		//write rule files	
		String filePath = null;
		
		for (String rule : streamsRulesetCreate.keySet()) {
			
			filePath = SLASH + home + SLASH + RULESETS_ROOT + SLASH + rule + SLASH + slot + SLASH + rule + slot + EXTENSION;

			try {
				int read     = 0;
				byte[] bytes = new byte[BUFFER_SIZE];
				
				File file = new File(filePath);
				
				if(file.exists()) {
					outputStream = new FileOutputStream(new File(filePath), false);
				}else
					outputStream = new FileOutputStream(new File(filePath));
					
				
				inputStream = streamsRulesetCreate.get(rule);
				
				
				while ((read = inputStream.read(bytes)) >= 0) {
					System.out.println("read byte size: " + bytes + " size:" + read);
					outputStream.write(bytes, 0, read);
				}
	
				outputStream.flush();
				outputStream.close();
				//inputStream.close();
				
			} catch(FileNotFoundException e) {
				Map<String, String> msg = new HashMap<String, String>();
				msg.put("message", "Tomcat account needs permissions to write to filesystem");
				return FoodsResource.getResponse(POST, Response.Status.UNAUTHORIZED, msg);
			} catch(IOException e) {
				//Map<String, String> msg = new HashMap<String, String>();
				//msg.put("message IOException: ", e.getMessage());
				//return FoodsResource.getResponse(POST, Response.Status.UNAUTHORIZED, msg);
				System.out.println("ioexception: " + e.getMessage());
				e.printStackTrace();
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
	
		if(rulesetName.equals("") || rulesetName.isEmpty() || rulesetName.equals("undefined"))
			return false;
		
		String target = buildTarget();
		
		Response response = ClientBuilder
			.newClient()
			.target(target)
			.path("/rulesets")
			.request()
			.get();
		
		Map<String, Object> rulesetsMap = (Map<String, Object>)response.readEntity(Object.class);
		
		List<Map<String, Object>> rulesets =  (List<Map<String, Object>>) rulesetsMap.get("rulesets");
		
		for (Map<String, Object> ruleset : rulesets) {
			System.out.println("ruleset get name: " + ruleset.get("name"));
			if(ruleset.get("name").equals(rulesetName))
				return true;
		}
		
		return false;
	}
	
	private String ruleValidate(String fileName, InputStream rulesetStream) {
		
		KieServices ks          = KieServices.Factory.get();		
		KieFileSystem kfs = ks.newKieFileSystem();
		KieBuilder kieBuilder = null;
		//kfs.write(ks.getResources().newFileSystemResource(filePath)
				//.setResourceType(ResourceType.DTABLE));
		System.out.println("go into rulevalidate: file: " + fileName);
		
		try {			
			kfs.write(ks.getResources().newInputStreamResource(rulesetStream)
					.setSourcePath("/opt/ruleset/cfg-classification-rulesets/rulesets/dtables/" + fileName + ".xls"));
			
			kieBuilder = ks.newKieBuilder(kfs).buildAll();
		}catch(Exception ex)
		{
			return("Excel rule file format Error!" + ex.getMessage());
		}
		
		System.out.println("go after builder rulevalidate: file: " + fileName);
		
		if (kieBuilder.getResults().hasMessages(Message.Level.ERROR)) {
			//System.out.println(kieBuilder.getResults().toString());			
			return kieBuilder.getResults().toString();
		}
		
		return null;
	}
	
	private InputStream copyInputStream(InputStream in) {
		
		ByteArrayInputStream bais = null;
		
		try {
		
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			org.apache.commons.io.IOUtils.copy(in, baos);
			byte[] bytes = baos.toByteArray();
			bais = new ByteArrayInputStream(bytes);
			
		}catch(IOException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		
		return bais;
		
	}
	
	
}
