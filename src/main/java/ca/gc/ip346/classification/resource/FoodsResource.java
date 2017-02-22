package ca.gc.ip346.classification.resource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.annotation.JacksonFeatures;
import com.google.gson.GsonBuilder;

import ca.gc.ip346.classification.model.FoodItem;
import ca.gc.ip346.util.DBConnection;

@Path("/foods")

@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class FoodsResource {
	private static final Logger logger = LogManager.getLogger(FoodsResource.class);

	Connection conn       = null;
	DatabaseMetaData meta = null;

	public FoodsResource() {
		try {
			// Context initCtx = new InitialContext();
			// Context envCtx  = (Context)initCtx.lookup("java:comp/env");
			// DataSource ds   = (DataSource)envCtx.lookup("jdbc/FoodDB");
			// conn = ds.getConnection();
			conn = DBConnection.getConnections();
		} catch(NamingException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
	public List<FoodItem> getFoodList() {
		List<FoodItem> list = new ArrayList<FoodItem>(); // Create list

		try {
			meta = conn.getMetaData(); // Create Oracle DatabaseMetaData object
			logger.trace("[01;34mJDBC driver version is " + meta.getDriverVersion() + "[00;00m"); // Retrieve driver information
			String sql = ContentHandler.read("food_table_cnf.sql", getClass());
			PreparedStatement stmt = conn.prepareStatement(sql); // Create PreparedStatement
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				FoodItem food = new FoodItem();
				food.setId(rs.getInt("FOOD_C"));
				food.setName(rs.getString("ENG_NAME"));
				food.setLabel(rs.getString("FOOD_DESC"));
				food.setGroup(rs.getString("GROUP_C"));
				food.setSubGroup(rs.getString("CANADA_FOOD_SUBGROUP_ID"));
				food.setCountryCode(rs.getString("COUNTRY_C"));
				list.add(food);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}

		// logger.trace(new GsonBuilder().setDateFormat("yyyy-MM-dd").setPrettyPrinting().create().toJson(list));

		return list;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
	public List<FoodItem> getFoodItem(@PathParam("id") Integer id) {
		List<FoodItem> list = new ArrayList<FoodItem>(); // Create list

		try {
			meta = conn.getMetaData(); // Create Oracle DatabaseMetaData object
			logger.trace("[01;34mJDBC driver version is " + meta.getDriverVersion() + "[00;00m"); // Retrieve driver information
			String sql = ContentHandler.read("food_item_cnf.sql", getClass());
			PreparedStatement stmt = conn.prepareStatement(sql); // Create PreparedStatement
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				FoodItem food = new FoodItem();
				food.setId(rs.getInt("FOOD_C"));
				food.setName(rs.getString("ENG_NAME"));
				food.setLabel(rs.getString("FOOD_DESC"));
				food.setGroup(rs.getString("GROUP_C"));
				food.setSubGroup(rs.getString("CANADA_FOOD_SUBGROUP_ID"));
				food.setCountryCode(rs.getString("COUNTRY_C"));
				list.add(food);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}

		logger.trace(new GsonBuilder().setDateFormat("yyyy-MM-dd").setPrettyPrinting().create().toJson(list));

		return list;
	}

	@GET
	@Path("/group/{groupId}")
	@Produces(MediaType.APPLICATION_JSON)
	@JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
	public List<FoodItem> getFoodListForGroup(@PathParam("groupId") Integer groupId) {
		List<FoodItem> list = new ArrayList<FoodItem>(); // Create list

		try {
			meta = conn.getMetaData(); // Create Oracle DatabaseMetaData object
			logger.trace("[01;34mJDBC driver version is " + meta.getDriverVersion() + "[00;00m"); // Retrieve driver information
			String sql = ContentHandler.read("food_table_group_cnf.sql", getClass());
			PreparedStatement stmt = conn.prepareStatement(sql); // Create PreparedStatement
			stmt.setInt(1, groupId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				FoodItem food = new FoodItem();
				food.setId(rs.getInt("FOOD_C"));
				food.setName(rs.getString("ENG_NAME"));
				food.setLabel(rs.getString("FOOD_DESC"));
				food.setGroup(rs.getString("GROUP_C"));
				food.setSubGroup(rs.getString("CANADA_FOOD_SUBGROUP_ID"));
				food.setCountryCode(rs.getString("COUNTRY_C"));
				list.add(food);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}

		// logger.trace(new GsonBuilder().setDateFormat("yyyy-MM-dd").setPrettyPrinting().create().toJson(list));

		return list;
	}

	@GET
	@Path("/subgroup/{subgroupId}")
	@Produces(MediaType.APPLICATION_JSON)
	@JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
	public List<FoodItem> getFoodListForsubGroup(@PathParam("subgroupId") Integer subgroupId) {
		List<FoodItem> list = new ArrayList<FoodItem>(); // Create list

		try {
			meta = conn.getMetaData(); // Create Oracle DatabaseMetaData object
			logger.trace("[01;34mJDBC driver version is " + meta.getDriverVersion() + "[00;00m"); // Retrieve driver information
			String sql = ContentHandler.read("food_table_subgroup_cnf.sql", getClass());
			PreparedStatement stmt = conn.prepareStatement(sql); // Create PreparedStatement
			stmt.setInt(1, subgroupId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				FoodItem food = new FoodItem();
				food.setId(rs.getInt("FOOD_C"));
				food.setName(rs.getString("ENG_NAME"));
				food.setLabel(rs.getString("FOOD_DESC"));
				food.setGroup(rs.getString("GROUP_C"));
				food.setSubGroup(rs.getString("CANADA_FOOD_SUBGROUP_ID"));
				food.setCountryCode(rs.getString("COUNTRY_C"));
				list.add(food);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}

		// logger.trace(new GsonBuilder().setDateFormat("yyyy-MM-dd").setPrettyPrinting().create().toJson(list));

		return list;
	}
}
