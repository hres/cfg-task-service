package ca.gc.ip346.classification.resource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ContentHandler {
	private static final Logger logger = LogManager.getLogger(ContentHandler.class);

	public static String read(String file, Class<?> cls) {
		Path path       = null;
		byte[] contents = null;

		try {
			path     = Paths.get(cls.getResource(file).toURI());
			contents = Files.readAllBytes(path);
		} catch(URISyntaxException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
			logger.error("Dude! your file is missing: " + file);
		}

		return new String(contents);
	}
}
