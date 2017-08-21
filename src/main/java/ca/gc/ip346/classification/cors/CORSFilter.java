package ca.gc.ip346.classification.cors;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CORSFilter implements Filter {
	private static final Logger logger = LogManager.getLogger(CORSFilter.class);

	public CORSFilter() {
	}

	public void init(FilterConfig config) throws ServletException {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		logger.error("[01;03;31m" + "CORSFilter HTTP Request: " + "[00;00m");

		// Allow (authorize) all domains to consume the content
		((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Origin", "*");
		((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");

		HttpServletResponse response = (HttpServletResponse) servletResponse;

		// For HTTP OPTIONS verb/method reply with ACCEPTED status code -- per CORS handshake
		if (request.getMethod().equals("OPTIONS")) {
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
			return;
		}

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}
}
