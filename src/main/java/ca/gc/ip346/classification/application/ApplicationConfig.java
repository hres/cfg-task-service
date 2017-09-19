package ca.gc.ip346.classification.application;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


@ApplicationPath("/service")
public class ApplicationConfig extends Application {

	@Override
	public Set<Class<?>> getClasses() {

		Set<Class<?>> resources = new HashSet<>();

		System.out.println("[01;34mFood Classification REST configuration starting: getClasses()[00;00m");

		// features
		// this will register Jackson JSON providers
		resources.add(org.glassfish.jersey.jackson.JacksonFeature.class);
		resources.add(com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider.class);
		// or we can do it manually:
		// resources.add(ca.gc.ip346.classification.provider.MyJacksonJsonProvider.class);

		/**
		 * Before you can use capabilities of the jersey-media-multipart module
		 * in your client/server code, you need to register MultiPartFeature.
		 */
		resources.add(org.glassfish.jersey.media.multipart.MultiPartFeature.class);
		resources.add(ca.gc.ip346.classification.resource.RestUploadService.class);

		resources.add(ca.gc.ip346.classification.cors.CORSFilter.class);
		resources.add(ca.gc.ip346.classification.resource.FoodsResource.class);
		// resources.add(ca.gc.ip346.classification.resource.SubgroupsResource.class);
		// resources.add(ca.gc.ip346.classification.resource.GroupsResource.class);
		// resources.add(ca.gc.ip346.classification.resource.FlagsResource.class);
		// resources.add(ca.gc.ip346.classification.resource.TierAdjustmentsResource.class);
		resources.add(ca.gc.ip346.classification.resource.RulesResource.class);

		// ==> we could also choose packages, see below getProperties()
		System.out.println("[01;34mFood Classification REST configuration ended successfully.[00;00m");

		return resources;
	}

	@Override
	public Set<Object> getSingletons() {
		return Collections.emptySet();
	}

	@Override
	public Map<String, Object> getProperties() {
		Map<String, Object> properties = new HashMap<>();

		//in Jersey WADL generation is enabled by default, but we don't
		//want to expose too much information about our apis.
		//therefore we want to disable wadl (http://localhost:8080/service/application.wadl should return http 404)
		//see https://jersey.java.net/nonav/documentation/latest/user-guide.html#d0e9020 for details
		properties.put("jersey.config.server.wadl.disableWadl", true);

		//we could also use something like this instead of adding each of our resources
		//explicitly in getClasses():
		//properties.put("jersey.config.server.provider.packages", "com.nabisoft.tutorials.mavenstruts.service");


		return properties;
	}
}
