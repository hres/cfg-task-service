package ca.gc.ip346.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ClassificationProperties {
	private static Properties getProps() throws IOException {
		Properties props = new Properties();
		InputStream in   = ClassificationProperties.class.getClassLoader().getResourceAsStream("ca/gc/ip346/util/classification.properties");
		props.load(in);
		in.close();
		return props;
	}

	public static String getEndPoint() {
		String endPoint = null;
		try {
			endPoint = getProps().getProperty("endpoint");
		} catch(IOException e) {
			e.printStackTrace();
		}
		return endPoint;
	}
}
