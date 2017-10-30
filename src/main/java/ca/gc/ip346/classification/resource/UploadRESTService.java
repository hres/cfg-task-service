package ca.gc.ip346.classification.resource;

import static javax.ws.rs.HttpMethod.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import ca.gc.ip346.util.ClassificationProperties;
import ca.gc.ip346.util.RequestURL;

@Path("")
public class RestUploadService {
	// import static org.apache.logging.log4j.Level.*;
	private static final Logger logger = LogManager.getLogger(RestUploadService.class);
	private String target = RequestURL.getAddr() + ClassificationProperties.getEndPoint();

	private static final String RULESETS_ROOT = "dtables";
	private static final String SLASH         = "/";

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response uploadFile(@FormDataParam("file") InputStream fis, @FormDataParam("file") FormDataContentDisposition fdcd, @FormDataParam("rule") String rule) {
		OutputStream outpuStream = null;
		String fileName          = fdcd.getFileName();
		Map<?, ?> externalPath   = (Map<?, ?>)getRulesetsHome().getEntity();
		Map<?, ?> availableSlot  = (Map<?, ?>)getAvailableSlot().getEntity();
		String home              = externalPath.get("rulesetshome").toString().replaceAll("^\\/", "").replaceAll("\\/$", "");
		String slot              = availableSlot.get("slot").toString();
		String filePath          = SLASH + home + SLASH + RULESETS_ROOT + SLASH + rule + SLASH + slot + SLASH + fileName;

		logger.debug("[01;03;31m" + "File Name: " + fdcd.getFileName()   + "[00;00;00m");
		logger.debug("[01;03;34m" + "File Path: " + filePath             + "[00;00;00m");

		try {
			int read     = 0;
			byte[] bytes = new byte[1024];
			outpuStream  = new FileOutputStream(new File(filePath));
			while ((read = fis.read(bytes)) != -1) {
				outpuStream.write(bytes, 0, read);
			}
			outpuStream.flush();
			outpuStream.close();
		} catch(IOException iox) {
			iox.printStackTrace();
		} finally {
			if (outpuStream != null) {
				try {
					outpuStream.close();
				} catch(Exception ex) {
				}
			}
		}

		Map<String, Object> ruleset = new HashMap<String, Object>();
		ruleset.put("active", true);
		ruleset.put("isProd", false);
		ruleset.put("name", "Ruleset " + slot);
		ruleset.put("rulesetId", Integer.valueOf(slot));
		Response response = ClientBuilder
			.newClient()
			.target(target)
			.path("/rulesets")
			.request()
			.post(Entity.entity(ruleset, MediaType.APPLICATION_JSON));

		return FoodsResource.getResponse(POST, Response.Status.OK, response.readEntity(Object.class));
	}

	public Response getRulesetsHome() {
		Response response = ClientBuilder
			.newClient()
			.target(target)
			.path("/rulesetshome")
			.request()
			.get();

		return FoodsResource.getResponse(GET, Response.Status.OK, response.readEntity(Object.class));
	}

	public Response getAvailableSlot() {
		Response response = ClientBuilder
			.newClient()
			.target(target)
			.path("/slot")
			.request()
			.get();

		return FoodsResource.getResponse(GET, Response.Status.OK, response.readEntity(Object.class));
	}
}
