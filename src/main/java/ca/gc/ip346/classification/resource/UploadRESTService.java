package ca.gc.ip346.classification.resource;

import static javax.ws.rs.HttpMethod.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
// import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
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
	private String target = RequestURL.getAddr() + ClassificationProperties.getEndPoint();

	private static final String RULESETS_ROOT = "dtables";
	private static final String SLASH         = "/";
	private static final String EXTENSION     = ".xls";

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
	public Response uploadFile(@BeanParam Ruleset bean, @FormDataParam("rulesetname") String rulesetname) {
		Map<?, ?> externalPath = (Map<?, ?>)getRulesetsHome().getEntity();
		String home = externalPath.get("rulesetshome").toString().replaceAll("^\\/", "").replaceAll("\\/$", "");
		Map<?, ?> availableSlot = (Map<?, ?>)getAvailableSlot().getEntity();
		if (availableSlot.get("slot") == null) {
			Map<String, String> msg = new HashMap<String, String>();
			msg.put("message", "No ruleset slots available");
			return FoodsResource.getResponse(POST, Response.Status.OK, msg);
		}
		String slot = availableSlot.get("slot").toString();

		// if (bean.getRefamtZ() == null || bean.getFopZ() == null || bean.getShortcutZ() == null || bean.getThresholdsZ() == null || bean.getInitZ() == null || bean.getTierZ() == null) {
			// Map<String, String> msg = new HashMap<String, String>();
			// msg.put("message", "Files appear to be of the wrong media-type");
			// return FoodsResource.getResponse(POST, Response.Status.UNSUPPORTED_MEDIA_TYPE, msg);
		// }

		Map<String, InputStream> streams = new HashMap<String, InputStream>();
		if (bean.getRefamtZ()     != null) streams.put(bean.getRefamtZ()     .getName(), bean.getRefamt());
		if (bean.getFopZ()        != null) streams.put(bean.getFopZ()        .getName(), bean.getFop());
		if (bean.getShortcutZ()   != null) streams.put(bean.getShortcutZ()   .getName(), bean.getShortcut());
		if (bean.getThresholdsZ() != null) streams.put(bean.getThresholdsZ() .getName(), bean.getThresholds());
		if (bean.getInitZ()       != null) streams.put(bean.getInitZ()       .getName(), bean.getInit());
		if (bean.getTierZ()       != null) streams.put(bean.getTierZ()       .getName(), bean.getTier());

		// logger.debug("[01;03;34m" + "Empty: " + bean     .isRefamtEmpty() + "[00;00;00m");
		// logger.debug("[01;03;31m" + "Empty: " + bean        .isFopEmpty() + "[00;00;00m");
		// logger.debug("[01;03;34m" + "Empty: " + bean   .isShortcutEmpty() + "[00;00;00m");
		// logger.debug("[01;03;31m" + "Empty: " + bean .isThresholdsEmpty() + "[00;00;00m");
		// logger.debug("[01;03;34m" + "Empty: " + bean       .isInitEmpty() + "[00;00;00m");
		// logger.debug("[01;03;31m" + "Empty: " + bean       .isTierEmpty() + "[00;00;00m");

		// if (bean.isRefamtEmpty() || bean.isFopEmpty() || bean.isShortcutEmpty() || bean.isThresholdsEmpty() || bean.isInitEmpty() || bean.isTierEmpty()) {
		if (bean.getRefamtZ() == null || bean.getFopZ() == null || bean.getShortcutZ() == null || bean.getThresholdsZ() == null || bean.getInitZ() == null || bean.getTierZ() == null) {
			Map<String, String> msg = new HashMap<String, String>();
			msg.put("message", "All files need to be selected");
			return FoodsResource.getResponse(POST, Response.Status.BAD_REQUEST, msg);
		}

		for (String rule : streams.keySet()) {
			OutputStream outputStream = null;
			String filePath = SLASH + home + SLASH + RULESETS_ROOT + SLASH + rule + SLASH + slot + SLASH + rule + EXTENSION;

			try {
				int read     = 0;
				byte[] bytes = new byte[1024];
				outputStream = new FileOutputStream(new File(filePath));
				while ((read = streams.get(rule).read(bytes)) != -1) {
					outputStream.write(bytes, 0, read);
				}
				outputStream.flush();
				outputStream.close();
			} catch(IOException e) {
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
		if (rulesetname == null || rulesetname.trim().isEmpty()) {
			Date date = new Date();
			rulesetname = date.toString();
		}
		ruleset.put("name", rulesetname);
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
