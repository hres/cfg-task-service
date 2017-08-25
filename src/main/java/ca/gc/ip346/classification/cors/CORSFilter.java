package ca.gc.ip346.classification.cors;

import static org.apache.logging.log4j.Level.DEBUG;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Provider
public class CORSFilter implements ContainerResponseFilter {
	private static final Logger logger = LogManager.getLogger(CORSFilter.class);

	@Override
	public void filter(final ContainerRequestContext requestContext, final ContainerResponseContext responseContext) throws IOException {
		responseContext.getHeaders().add("Access-Control-Allow-Origin",      "*");
		responseContext.getHeaders().add("Access-Control-Allow-Headers",     "ORIGIN, ACCEPT, AUTHORIZATION, ACCESS_CONTROL_ALLOW_ORIGIN");
		responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
		responseContext.getHeaders().add("Access-Control-Allow-Methods",     "GET, POST, PUT, DELETE, OPTIONS, HEAD");
		responseContext.getHeaders().add("Access-Control-Max-Age",           "1209600");;

		logger.printf(DEBUG, "%s%s%s\n", "[01;03;33m", "Romario's CORS Magic!!", "[00;00;00m");
	}
}
