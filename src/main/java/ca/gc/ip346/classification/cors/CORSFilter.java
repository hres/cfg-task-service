package ca.gc.ip346.classification.cors;

import static org.apache.logging.log4j.Level.DEBUG;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Provider
public class CORSFilter implements ContainerResponseFilter {
	private static final Logger logger = LogManager.getLogger(CORSFilter.class);

	@Context
	private HttpServletRequest httpServletRequest;

	/**
	 * TODO: read ACCESS_CONTROL_ALLOW_ORIGIN value from a properties file
	 */

	@Override
	public void filter(final ContainerRequestContext request, final ContainerResponseContext response) throws IOException {
		StringBuffer asterisk  = new StringBuffer("*");
		Properties props = new Properties();
		InputStream in   = CORSFilter.class.getClassLoader().getResourceAsStream("ca/gc/ip346/classification/cors/cors.properties");
		props.load(in);
		in.close();

		if (in != null) {
			asterisk
				.delete(0, asterisk.length())
				.append(props.getProperty("protocol"))
				.append("://")
				.append(props.getProperty("host"));
			if (!props.getProperty("port").equals("80")) {
				asterisk
					.append(":")
					.append(props.getProperty("port"));
			}
		}

		asterisk.delete(0, asterisk.length()).append("http");
		if (httpServletRequest.getServerPort() == 443) {
			asterisk.append("s");
		}    if (httpServletRequest.getServerName().equals("localhost"))                              asterisk.append("://never.gonna.happen");
		else if (httpServletRequest.getServerName().equals("52.235.40.44"))                           asterisk.append("://lam-dev.canadaeast.cloudapp.azure.com");
		else if (httpServletRequest.getServerName().equals("java-dev.canadaeast.cloudapp.azure.com")) asterisk.append("://lam-dev.canadaeast.cloudapp.azure.com");
		else if (httpServletRequest.getServerName().equals("52.235.42.198"))                          asterisk.append("://lam-qa.canadaeast.cloudapp.azure.com");
		else if (httpServletRequest.getServerName().equals("java-qa.canadaeast.cloudapp.azure.com"))  asterisk.append("://lam-qa.canadaeast.cloudapp.azure.com");
		else if (httpServletRequest.getServerName().equals("10.148.180.111"))                         asterisk.append("://10.148.180.111");
		else if (httpServletRequest.getServerName().equals("10.148.180.111"))                         asterisk.append("://localhost:4200");
		else if (httpServletRequest.getServerName().equals("10.148.180.231"))                         asterisk.append("://10.148.180.231");
		else if (httpServletRequest.getServerName().equals("10.148.181.159"))                         asterisk.append("://10.148.181.159");

		response.getHeaders().add("Access-Control-Allow-Origin",      asterisk);
		response.getHeaders().add("Access-Control-Allow-Headers",     "ORIGIN, CONTENT-TYPE, ACCEPT, AUTHORIZATION, ACCESS_CONTROL_ALLOW_ORIGIN, X-REQUESTED-WITH");
		response.getHeaders().add("Access-Control-Allow-Credentials", "true");
		response.getHeaders().add("Access-Control-Allow-Methods",     "GET, POST, PUT, DELETE, OPTIONS, HEAD");
		response.getHeaders().add("Access-Control-Max-Age",           "1209600");
		response.getHeaders().add("Referrer-Policy",                  "no-referrer");

		// logger.printf(DEBUG, "%s%s%s", "[01;03;33m", "Romario's CORS Magic!! - *",       "[00;00;00m");
		// logger.printf(DEBUG, "%s%s%s", "[01;03;33m", "Romario's CORS Magic!! - *",       "[00;00;00m");
		// logger.printf(DEBUG, "%s%s%s", "[01;03;33m", "Romario's CORS Magic!! - *",       "[00;00;00m");
		// logger.printf(DEBUG, "%s%s%s", "[01;03;33m", "Romario's CORS Magic!! - *",       "[00;00;00m");
		logger.printf(DEBUG, "%s%s%s", "[01;03;33m", asterisk,                           "[00;00;00m");
		logger.printf(DEBUG, "%s%s%s", "[01;03;33m", httpServletRequest.getServerName(), "[00;00;00m");
	}
}
