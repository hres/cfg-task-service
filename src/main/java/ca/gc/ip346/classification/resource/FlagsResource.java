package ca.gc.ip346.classification.resource;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import ca.gc.ip346.classification.model.TieredFood;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.annotation.JacksonFeatures;
import com.google.gson.GsonBuilder;

@Path("/flags")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class FlagsResource {
	private static final Logger logger = LogManager.getLogger(FlagsResource.class);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
	public List<TieredFood> getFoodListForTest() {

		TieredFood food1 = new TieredFood();
		food1.setName("Crystalized Pineapple");
		food1.setLabel("dried, sweetened");
		food1.setSubGroup("112");
		food1.setTsatTier(1);

		TieredFood food2 = new TieredFood();
		food2.setName("Canned Peach");
		food2.setLabel("canned, sweetened");
		food2.setSubGroup("112");
		food2.setTsatTier(1);

		TieredFood food3 = new TieredFood();
		food3.setName("Canned Orange Juice");
		food3.setLabel("canned, juice pack");
		food3.setSubGroup("112");
		food3.setTsatTier(1);

		TieredFood food4 = new TieredFood();
		food4.setName("Heavy Syrup Pack");
		food4.setLabel("canned, heavy syrup");
		food4.setSubGroup("112");

		// create list
		List<TieredFood> list = new ArrayList<TieredFood>();
		// list.add(food1);
		// list.add(food2);
		// list.add(food3);
		// list.add(food4);

		// connect to oracle database
		try {
			DataHandler dh = new DataHandler();
			dh.getDBConnection();
			Connection conn = dh.getConn();
			// Create Oracle DatabaseMetaData object
			DatabaseMetaData meta = conn.getMetaData();

			// gets driver info:
			System.out.println("[01;34mJDBC driver version is " + meta.getDriverVersion() + "[00;00m");
			logger.trace("[01;34mJDBC driver version is " + meta.getDriverVersion() + "[00;00m");

			// Create PreparedStatement
			String sql = ContentHandler.read("food_table_classification_data_set.sql", getClass());
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				TieredFood food = new TieredFood();
				food.setName(rs.getString("ENG_NAME"));
				food.setLabel(rs.getString("FOOD_DESC"));
				food.setGroup(rs.getString("GROUP_C"));
				food.setSubGroup(rs.getString("CANADA_FOOD_SUBGROUP_ID"));
				food.setTsatTier(1);
				list.add(food);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}

		// logger.trace(new GsonBuilder().setDateFormat("yyyy-MM-dd").setPrettyPrinting().create().toJson(list));

		return list;
	}

	@GET
	@Path("{foodId}")
	@Produces(MediaType.APPLICATION_JSON)
	@JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
	public List<TieredFood> getFoodListFoodItem(@PathParam("foodId") Integer foodId) {
		List<TieredFood> list = new ArrayList<TieredFood>();
		// connect to oracle database
		try {
			DataHandler dh = new DataHandler();
			dh.getDBConnection();
			Connection conn = dh.getConn();
			// Create Oracle DatabaseMetaData object
			DatabaseMetaData meta = conn.getMetaData();

			// gets driver info:
			System.out.println("[01;34mJDBC driver version is " + meta.getDriverVersion() + "[00;00m");
			logger.trace("[01;34mJDBC driver version is " + meta.getDriverVersion() + "[00;00m");

			// Create PreparedStatement
			String sql = ContentHandler.read("food_table_classification_item.sql", getClass());
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, foodId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				TieredFood food = new TieredFood();
				food.setName(rs.getString("ENG_NAME"));
				food.setLabel(rs.getString("FOOD_DESC"));
				food.setGroup(rs.getString("GROUP_C"));
				food.setSubGroup(rs.getString("CANADA_FOOD_SUBGROUP_ID"));
				food.setTsatTier(1);
				list.add(food);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@GET
	@Path("/group/{groupId}")
	@Produces(MediaType.APPLICATION_JSON)
	@JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
	public List<TieredFood> getFoodListForGroup(@PathParam("groupId") Integer groupId) {
		List<TieredFood> list = new ArrayList<TieredFood>();
		// connect to oracle database
		try {
			DataHandler dh = new DataHandler();
			dh.getDBConnection();
			Connection conn = dh.getConn();
			// Create Oracle DatabaseMetaData object
			DatabaseMetaData meta = conn.getMetaData();

			// gets driver info:
			System.out.println("[01;34mJDBC driver version is " + meta.getDriverVersion() + "[00;00m");
			logger.trace("[01;34mJDBC driver version is " + meta.getDriverVersion() + "[00;00m");

			// Create PreparedStatement
			String sql = ContentHandler.read("food_table_classification_group.sql", getClass());
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, groupId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				TieredFood food = new TieredFood();
				food.setName(rs.getString("ENG_NAME"));
				food.setLabel(rs.getString("FOOD_DESC"));
				food.setGroup(rs.getString("GROUP_C"));
				food.setSubGroup(rs.getString("CANADA_FOOD_SUBGROUP_ID"));
				food.setTsatTier(1);
				list.add(food);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@POST
	public List<TieredFood> setFoodFlags(List<TieredFood> foodList) {
		try{
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			KieSession kSessionFlags = kContainer.newKieSession("ksession-process-flags");
			System.out.println("Started to process flags.");

			foodList.forEach(food->{
				kSessionFlags.insert(food);
				kSessionFlags.fireAllRules();
				/*
				System.out.println("Food Name: " + food.getName() +
						  "\nLabel: " + food.getLabel() +
						  "\nSub Group: " + food.getSubGroup() +
						  "\nTsat Tier: " + food.getTsatTier() +
						  "\nExclusion 1: " + food.getExclusion1() +
						  "\nSugar Added: " + food.isSugarAdded() +
						  "\nAdjusted Tier: " + food.getAdjustedTier());
				*/
			});
			System.out.println("Finished processing flags.");

			if ( kSessionFlags != null ) {
				kSessionFlags.dispose();
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		return foodList;
	}

	/*
	@POST
	public TieredFood setFoodFlags(TieredFood food)  {
		try{
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			KieSession kSessionFlags = kContainer.newKieSession("ksession-process-flags");

			kSessionFlags.insert(food);
			kSessionFlags.fireAllRules();

			System.out.println("Food Name: " + food.getName() +
					  "\nLabel: " + food.getLabel() +
					  "\nSub Group: " + food.getSubGroup() +
					  "\nTsat Tier: " + food.getTsatTier() +
					  "\nExclusion 1: " + food.getExclusion1() +
					  "\nSugar Added: " + food.isSugarAdded() +
					  "\nAdjusted Tier: " + food.getAdjustedTier());

			if ( kSessionFlags != null ) {
				kSessionFlags.dispose();
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		return food;
	}
	*/
}
