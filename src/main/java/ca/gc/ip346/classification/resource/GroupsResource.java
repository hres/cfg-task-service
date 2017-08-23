package ca.gc.ip346.classification.resource;

import java.io.IOException;
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

import ca.gc.ip346.classification.model.CanadaFoodGroup;
import ca.gc.ip346.util.DBConnection;

@Path("/groups")

@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public class GroupsResource {
	private static final Logger logger = LogManager.getLogger(GroupsResource.class);

	Connection conn = null;

	public GroupsResource() {
		try {
			// Context initCtx = new InitialContext();
			// Context envCtx  = (Context) initCtx.lookup("java:comp/env");
			// DataSource ds   = (DataSource) envCtx.lookup("jdbc/FoodDB");
			// conn            = ds.getConnection();
			conn = DBConnection.getConnection();
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
	@JacksonFeatures(serializationEnable={SerializationFeature.INDENT_OUTPUT})
	public List<CanadaFoodGroup> doGetCanadaFoodGroup() {
		List<CanadaFoodGroup> groups = new ArrayList<CanadaFoodGroup>();
		String sql = ContentHandler.read("canada_food_groups.sql", getClass());

		logger.trace("\n" + sql);

		try {
			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				CanadaFoodGroup group = new CanadaFoodGroup();
				group.setCanadaFoodGroupId(rs.getInt("CANADA_FOOD_GROUP_ID"));
				group.setCanadaFoodGroupCode(rs.getInt("CANADA_FOOD_GROUP_CODE"));
				group.setCanadaFoodGroupDescE(rs.getString("CANADA_FOOD_GROUP_DESC_E"));
				group.setCanadaFoodGroupDescF(rs.getString("CANADA_FOOD_GROUP_DESC_F"));
				group.setPublicationCode(rs.getString("PUBLICATION_CODE"));
				groups.add(group);
			}
			stmt.close();
			rs.close();
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return groups;
	}

	@GET
	@Path("{groupId: \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	@JacksonFeatures(serializationEnable={SerializationFeature.INDENT_OUTPUT})
	public List<CanadaFoodGroup> doGetCanadaFoodGroup(@PathParam("groupId") Integer groupId) {
		List<CanadaFoodGroup> groups = new ArrayList<CanadaFoodGroup>();
		String sql = ContentHandler.read("canada_food_group.sql", getClass());

		// DataSourceHandler foo = new DataSourceHandler();

		logger.trace("\n" + sql);

		try {
			// foo.dataSourceHandler();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, groupId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				CanadaFoodGroup group = new CanadaFoodGroup();
				group.setCanadaFoodGroupId(rs.getInt("CANADA_FOOD_GROUP_ID"));
				group.setCanadaFoodGroupCode(rs.getInt("CANADA_FOOD_GROUP_CODE"));
				group.setCanadaFoodGroupDescE(rs.getString("CANADA_FOOD_GROUP_DESC_E"));
				group.setCanadaFoodGroupDescF(rs.getString("CANADA_FOOD_GROUP_DESC_F"));
				group.setPublicationCode(rs.getString("PUBLICATION_CODE"));
				groups.add(group);
			}
			stmt.close();
			rs.close();
			conn.close();
			// } catch(NamingException e) {
			// e.printStackTrace();
	} catch(SQLException e) {
		e.printStackTrace();
	}

	return groups;
	}
}
