package ca.gc.ip346.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class RequestURL {
	private static Properties getProps() throws IOException {
		Properties props = new Properties();
		InputStream in   = RequestURL.class.getClassLoader().getResourceAsStream("ca/gc/ip346/util/requestUri.properties");
		props.load(in);
		in.close();
		return props;
	}

	public static String getHost() {
		String host  = null;
		try {
			host = getProps().getProperty("host");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return host;
	}

	public static String getPort() {
		String port = null;
		try {
			port = getProps().getProperty("port");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return port;
	}

	public static String getAddr() {
		return getHost() + ":" + getPort();
	}
}
