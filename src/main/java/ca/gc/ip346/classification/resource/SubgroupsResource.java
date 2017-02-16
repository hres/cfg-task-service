package ca.gc.ip346.classification.resource;

import java.sql.Connection;
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

import ca.gc.ip346.classification.model.CanadaFoodSubgroup;

@Path("/subgroups")

@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class SubgroupsResource {
	private static final Logger logger = LogManager.getLogger(SubgroupsResource.class);
	Connection conn = null;

	public SubgroupsResource() {
		try {
			Context initCtx = new InitialContext();
			Context envCtx  = (Context)initCtx.lookup("java:comp/env");
			DataSource ds   = (DataSource)envCtx.lookup("jdbc/FoodDB");
			conn = ds.getConnection();
		} catch(NamingException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
	public List<CanadaFoodSubgroup> doGetCanadaFoodSubgroup() {
		List<CanadaFoodSubgroup> subgroups = new ArrayList<CanadaFoodSubgroup>();
		String sql = ContentHandler.read("canada_food_subgroups.sql", getClass());

		logger.trace("\n" + sql);

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				CanadaFoodSubgroup subgroup = new CanadaFoodSubgroup();
				subgroup.setCanadaFoodSubgroupId(rs.getInt("CANADA_FOOD_SUBGROUP_ID"));
				subgroup.setCanadaFoodSubgroupCode(rs.getInt("CANADA_FOOD_SUBGROUP_CODE"));
				subgroup.setCanadaFoodSubgroupDescE(rs.getString("CANADA_FOOD_SUBGROUP_DESC_E"));
				subgroup.setCanadaFoodSubgroupDescF(rs.getString("CANADA_FOOD_SUBGROUP_DESC_F"));
				subgroup.setCanadaFoodGroupId(rs.getInt("CANADA_FOOD_GROUP_ID"));
				subgroup.setPublicationCode(rs.getString("PUBLICATION_CODE"));
				subgroups.add(subgroup);
			}
			stmt.close();
			rs.close();
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return subgroups;
	}

	@GET
	@Path("{subgroupId: \\d+?}")
	@Produces(MediaType.APPLICATION_JSON)
	@JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
	public List<CanadaFoodSubgroup> doGetCanadaFoodSubgroup(@PathParam("subgroupId") Integer subgroupId) {
		List<CanadaFoodSubgroup> subgroups = new ArrayList<CanadaFoodSubgroup>();
		String sql = ContentHandler.read("canada_food_subgroup.sql", getClass());

		logger.trace("\n" + sql);

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, subgroupId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				CanadaFoodSubgroup subgroup = new CanadaFoodSubgroup();
				subgroup.setCanadaFoodSubgroupId(rs.getInt("CANADA_FOOD_SUBGROUP_ID"));
				subgroup.setCanadaFoodSubgroupCode(rs.getInt("CANADA_FOOD_SUBGROUP_CODE"));
				subgroup.setCanadaFoodSubgroupDescE(rs.getString("CANADA_FOOD_SUBGROUP_DESC_E"));
				subgroup.setCanadaFoodSubgroupDescF(rs.getString("CANADA_FOOD_SUBGROUP_DESC_F"));
				subgroup.setCanadaFoodGroupId(rs.getInt("CANADA_FOOD_GROUP_ID"));
				subgroup.setPublicationCode(rs.getString("PUBLICATION_CODE"));
				subgroups.add(subgroup);
			}
			stmt.close();
			rs.close();
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return subgroups;
	}
}
