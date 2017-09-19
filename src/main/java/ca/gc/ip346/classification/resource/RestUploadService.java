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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path("")
public class RestUploadService {

	/**
	 * TODO: Inject FOLDER_PATH value from a properties file
	 */
	private static final String FOLDER_PATH = "/var/tmp/rulesets/";

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response uploadFile(@FormDataParam("file") InputStream fis, @FormDataParam("file") FormDataContentDisposition fdcd) {
		OutputStream outpuStream = null;
		String fileName          = fdcd.getFileName();
		String filePath          = FOLDER_PATH + fileName;
		System.out.println("[01;03;31m" + "File Name: " + fdcd.getFileName() + "[00;00;00m");

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
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", "File Upload Successfully!!");
		return FoodsResource.getResponse(POST, Response.Status.OK, map);
	}
}
