package ca.gc.ip346.classification.resource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.naming.NamingException;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.bson.types.ObjectId;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.annotation.JacksonFeatures;
// import com.google.common.base.CaseFormat;
import com.google.gson.GsonBuilder;
// import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import static com.mongodb.client.model.Filters.*;
// import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
// import com.mongodb.util.JSON;

// import ca.gc.ip346.classification.model.Added;
import ca.gc.ip346.classification.model.CanadaFoodGuideDataset;
import ca.gc.ip346.classification.model.CfgTier;
import ca.gc.ip346.classification.model.ContainsAdded;
import ca.gc.ip346.classification.model.Dataset;
import ca.gc.ip346.classification.model.FoodItem;
import ca.gc.ip346.classification.model.Missing;
import ca.gc.ip346.classification.model.RecipeRolled;
// import ca.gc.ip346.classification.model.NewAndImprovedFoodItem;
import ca.gc.ip346.classification.model.CfgFilter;
import ca.gc.ip346.util.DBConnection;

@Path("/datasets")
// @Produces(MediaType.APPLICATION_JSON)
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
	@Path("/search")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
	public List<CanadaFoodGuideDataset> getFoodList(@BeanParam CfgFilter search) {
		String sql = ContentHandler.read("canada_food_guide_dataset.sql", getClass());
		search.setSql(sql);
		return doSearchCriteria(search);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> saveDataset(Dataset dataset) {
		MongoClient mongoClient = new MongoClient();
		MongoDatabase database = mongoClient.getDatabase("cfgDb");
		MongoCollection<Document> collection = database.getCollection("master");

		// String sql = ContentHandler.read("canada_food_guide_dataset.sql", getClass());
		// CfgFilter search = new CfgFilter();
		// search.setSql(sql);
		// List<CanadaFoodGuideDataset> list = doSearchCriteria(search);

		// String json = ContentHandler.read("search.json", getClass());
		// DBObject dbObject = (DBObject)JSON.parse(json);
		// DBObject dbObject = (DBObject)JSON.parse(dataset.getData());

		Map<String, Object> map = new HashMap<String, Object>();

		if (dataset.getData() != null && dataset.getName() != null && dataset.getComments() != null) {
			Document doc = new Document()
				// .append("data", dbObject)
				.append("data",     dataset.getData())
				.append("name",     dataset.getName())
				.append("owner",    dataset.getOwner())
				.append("status",   dataset.getStatus())
				.append("comments", dataset.getComments());
			collection.insertOne(doc);
			ObjectId id = (ObjectId)doc.get("_id");
			collection.updateOne(
					eq("_id", id),
					combine(
						set("name", dataset.getName()),
						set("comments", dataset.getComments()),
						currentDate("modifiedDate"))
					);

			logger.error("[01;34mLast inserted Dataset id: " + id + "[00;00m");

			logger.error("[01;34mCurrent number of Datasets: " + collection.count() + "[00;00m");

			mongoClient.close();

			map.put("id", id.toString());
		} else {
			List<String> list = new ArrayList<String>();

			if (dataset.getData()     == null) list.add("data");
			if (dataset.getName()     == null) list.add("name");
			if (dataset.getComments() == null) list.add("comments");

			map.put("code", 400);
			map.put("description", "Unable to insert Dataset!");
			map.put("fields", StringUtils.join(list, ", "));

			logger.error("[01;34mUnable to insert Dataset!" + "[00;00m");
		}

		return map;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
	public List<Map<String, String>> getDatasets() {
		MongoClient mongoClient = new MongoClient();
		MongoDatabase database = mongoClient.getDatabase("cfgDb");
		MongoCollection<Document> collection = database.getCollection("master");

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		MongoCursor<Document> cursorDocMap = collection.find().iterator();
		while (cursorDocMap.hasNext()) {
			Map<String, String> map = new HashMap<String, String>();
			Document doc = cursorDocMap.next();
			map.put("id", doc.get("_id").toString());

			if (doc.get("name")         != null) map.put("name",         doc.get("name")         .toString());
			if (doc.get("owner")        != null) map.put("owner",        doc.get("owner")        .toString());
			if (doc.get("status")       != null) map.put("status",       doc.get("status")       .toString());
			if (doc.get("comments")     != null) map.put("comments",     doc.get("comments")     .toString());
			if (doc.get("modifiedDate") != null) map.put("modifiedDate", doc.get("modifiedDate") .toString());

			list.add(map);
			logger.error("[01;34mDataset ID: " + doc.get("_id") + "[00;00m");
		}

		mongoClient.close();

		return list;
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
	public void deleteDataset() {
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
	public void updateDataset() {
	}

	@POST
	@Path("/{id}/classify")
	@Produces(MediaType.APPLICATION_JSON)
	@JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
	public void classifyDataset() {
	}

	@POST
	@Path("/{id}/commit")
	@Produces(MediaType.APPLICATION_JSON)
	@JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
	public void commitDataset() {
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
	public List<Map<String, Object>> getDataset(@PathParam("id") String id) {
		MongoClient mongoClient = new MongoClient();
		MongoDatabase database = mongoClient.getDatabase("cfgDb");
		MongoCollection<Document> collection = database.getCollection("master");

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		MongoCursor<Document> cursorDocMap = collection.find(new Document("_id", new ObjectId(id))).iterator();
		while (cursorDocMap.hasNext()) {
			Map<String, Object> map = new HashMap<String, Object>();
			Document doc = cursorDocMap.next();
			logger.error("[01;34mDataset ID: " + doc.get("_id") + "[00;00m");

			if (doc != null) {
				map.put("data",         doc.get("data"));
				map.put("name",         doc.get("name"));
				map.put("owner",        doc.get("owner"));
				map.put("status",       doc.get("status"));
				map.put("comments",     doc.get("comments"));
				map.put("modifiedDate", doc.get("modifiedDate").toString());
				list.add(map);
			}
		}

		mongoClient.close();

		return list;
	}

	public List<FoodItem> getFoodItem(@PathParam("id") Integer id) {
		List<FoodItem> list = new ArrayList<FoodItem>(); // Create list

		try {
			meta = conn.getMetaData(); // Create Oracle DatabaseMetaData object
			logger.error("[01;34mJDBC driver version is " + meta.getDriverVersion() + "[00;00m"); // Retrieve driver information
			String sql = ContentHandler.read("food_item_cnf.sql", getClass());
			PreparedStatement stmt = conn.prepareStatement(sql); // Create PreparedStatement
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				FoodItem food = new FoodItem();
				food.setId(rs.getInt("food_c"));
				food.setName(rs.getString("eng_name"));
				food.setLabel(rs.getString("food_desc"));
				food.setGroup(rs.getString("group_c"));
				food.setSubGroup(rs.getString("canada_food_subgroup_id"));
				food.setCountryCode(rs.getString("country_c"));
				list.add(food);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}

		logger.error(new GsonBuilder().setDateFormat("yyyy-MM-dd").setPrettyPrinting().create().toJson(list));

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
			logger.error("[01;34mJDBC driver version is " + meta.getDriverVersion() + "[00;00m"); // Retrieve driver information
			String sql = ContentHandler.read("food_table_group_cnf.sql", getClass());
			PreparedStatement stmt = conn.prepareStatement(sql); // Create PreparedStatement
			stmt.setInt(1, groupId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				FoodItem food = new FoodItem();
				food.setId(rs.getInt("food_c"));
				food.setName(rs.getString("eng_name"));
				food.setLabel(rs.getString("food_desc"));
				food.setGroup(rs.getString("group_c"));
				food.setSubGroup(rs.getString("canada_food_subgroup_id"));
				food.setCountryCode(rs.getString("country_c"));
				list.add(food);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}

		// logger.error(new GsonBuilder().setDateFormat("yyyy-MM-dd").setPrettyPrinting().create().toJson(list));

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
			logger.error("[01;34mJDBC driver version is " + meta.getDriverVersion() + "[00;00m"); // Retrieve driver information
			String sql = ContentHandler.read("food_table_subgroup_cnf.sql", getClass());
			PreparedStatement stmt = conn.prepareStatement(sql); // Create PreparedStatement
			stmt.setInt(1, subgroupId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				FoodItem food = new FoodItem();
				food.setId(rs.getInt("food_c"));
				food.setName(rs.getString("eng_name"));
				food.setLabel(rs.getString("food_desc"));
				food.setGroup(rs.getString("group_c"));
				food.setSubGroup(rs.getString("canada_food_subgroup_id"));
				food.setCountryCode(rs.getString("country_c"));
				list.add(food);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}

		// logger.error(new GsonBuilder().setDateFormat("yyyy-MM-dd").setPrettyPrinting().create().toJson(list));

		return list;
	}

	private List<CanadaFoodGuideDataset> doSearchCriteria(CfgFilter search) {
		List<CanadaFoodGuideDataset> list = new ArrayList<CanadaFoodGuideDataset>();

		if (search != null) {
			StringBuffer sb = new StringBuffer(search.getSql());

			logger.error("[01;30m" + search.getDataSource() + "[00;00m");

			sb.append(" WHERE length('this where-clause is an artifact') = 32 ").append("\n");
			if (search.getDataSource() != null && search.getDataSource().matches("food|recipe")) {
				sb.append("   AND type = ?").append("\n");
			}

			logger.error("[01;30m" + search.getFoodRecipeName() + "[00;00m");

			if (search.getFoodRecipeName() != null && !search.getFoodRecipeName().isEmpty()) {
				sb.append("   AND LOWER(name) LIKE ?").append("\n");
			}

			logger.error("[01;30m" + search.getFoodRecipeCode() + "[00;00m");

			if (search.getFoodRecipeCode() != null && !search.getFoodRecipeCode().isEmpty()) {
				sb.append("   AND code = ? OR CAST(code AS text) LIKE ?").append("\n");
			}

			logger.error("[01;30m" + search.getCnfCode() + "[00;00m");

			if (search.getCnfCode() != null && !search.getCnfCode().isEmpty()) {
				sb.append("   AND cnf_group_code = ?").append("\n");
			}

			logger.error("[01;30m" + search.getSubgroupCode() + "[00;00m");

			if (search.getSubgroupCode() != null && !search.getSubgroupCode().isEmpty()) {
				sb.append("   AND CAST(cfg_code AS text) LIKE ?").append("\n");
			}

			if (search.getCfgTier() != null && !search.getCfgTier().equals(CfgTier.ALL.getCode())) {
				switch (search.getCfgTier()) {
					case 1:
					case 2:
					case 3:
					case 4:
						logger.error("[01;31mCalling all codes with Tier " + search.getCfgTier() + "[00;00m");
						sb.append("   AND LENGTH(CAST(cfg_code AS text)) = 4").append("\n");
						sb.append("   AND CAST(SUBSTR(CAST(cfg_code AS text), 4, 1) AS integer) = ?").append("\n");
						break;
					case 12:
					case 13:
					case 14:
					case 23:
					case 24:
					case 34:
						logger.error("[01;31mCalling all codes with Tier " + search.getCfgTier() + "[00;00m");
						sb.append("   AND LENGTH(CAST(cfg_code AS text)) = 4").append("\n");
						sb.append("   AND CAST(SUBSTR(CAST(cfg_code AS text), 4, 1) AS integer) = ?").append("\n");
						sb.append("    OR CAST(SUBSTR(CAST(cfg_code AS text), 4, 1) AS integer) = ?").append("\n");
						break;
					case 123:
					case 124:
					case 134:
					case 234:
						logger.error("[01;31mCalling all codes with Tier " + search.getCfgTier() + "[00;00m");
						sb.append("   AND LENGTH(CAST(cfg_code AS text)) = 4").append("\n");
						sb.append("   AND CAST(SUBSTR(CAST(cfg_code AS text), 4, 1) AS integer) = ?").append("\n");
						sb.append("    OR CAST(SUBSTR(CAST(cfg_code AS text), 4, 1) AS integer) = ?").append("\n");
						sb.append("    OR CAST(SUBSTR(CAST(cfg_code AS text), 4, 1) AS integer) = ?").append("\n");
						break;
					case 1234:
						logger.error("[01;31mCalling all codes with Tier " + search.getCfgTier() + "[00;00m");
						sb.append("   AND LENGTH(CAST(cfg_code AS text)) = 4").append("\n");
						sb.append("   AND CAST(SUBSTR(CAST(cfg_code AS text), 4, 1) AS integer) = ?").append("\n");
						sb.append("    OR CAST(SUBSTR(CAST(cfg_code AS text), 4, 1) AS integer) = ?").append("\n");
						sb.append("    OR CAST(SUBSTR(CAST(cfg_code AS text), 4, 1) AS integer) = ?").append("\n");
						sb.append("    OR CAST(SUBSTR(CAST(cfg_code AS text), 4, 1) AS integer) = ?").append("\n");
						break;
					case 9:
						logger.error("[01;31mCalling all codes with missing Tier![00;00m");
						sb.append("   AND LENGTH(CAST(cfg_code AS text)) < 4").append("\n");
						break;
				}
			}

			if (search.getRecipe() != null && !search.getRecipe().equals(RecipeRolled.IGNORE.getCode())) {
				logger.error("[01;32m" + search.getRecipe() + "[00;00m");
				sb.append("   AND rolled_up = ?").append("\n");
			}

			boolean notIgnore = false;
			if (search.getContainsAdded() != null) {
				String[] arr = new String[search.getContainsAdded().size()];
				arr = search.getContainsAdded().toArray(arr);
				for (String i : arr) {
					logger.error("[01;32m" + i + "[00;00m");
					if (!i.equals("0")) {
						notIgnore = true;
					}
				}
			}

			Map<String, String> map = null;

			if (search.getContainsAdded() != null && notIgnore) {
				map = new HashMap<String, String>();
				logger.error("[01;32m" + search.getContainsAdded() + "[00;00m");
				String[] arr = new String[search.getContainsAdded().size()];
				arr = search.getContainsAdded().toArray(arr);
				for (String keyValue : arr) {
					StringTokenizer tokenizer = new StringTokenizer(keyValue, "=");
					// map.put(CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, tokenizer.nextToken()),tokenizer.nextToken());
					map.put(tokenizer.nextToken(), tokenizer.nextToken());
					logger.error("[01;32m" + keyValue + "[00;00m");
				}
				logger.error("\n[01;32m" + new GsonBuilder().setDateFormat("yyyy-MM-dd").setPrettyPrinting().create().toJson(map) + "[00;00m");

				Set<String> keys = map.keySet();
				for (String key : keys) {
					switch (ContainsAdded.valueOf(key)) {
						case sodium:
							sb.append("   AND contains_added_sodium      = ? ").append("\n");
							break;
						case sugar:
							sb.append("   AND contains_added_sugar       = ? ").append("\n");
							break;
						case fat:
							sb.append("   AND contains_added_fat         = ? ").append("\n");
							break;
						case transfat:
							sb.append("   AND contains_added_transfat    = ? ").append("\n");
							break;
						case caffeine:
							sb.append("   AND contains_caffeine          = ? ").append("\n");
							break;
						case freeSugars:
							sb.append("   AND contains_free_sugars       = ? ").append("\n");
							break;
						case sugarSubstitute:
							sb.append("   AND contains_sugar_substitutes = ? ").append("\n");
							break;
					}
				}
			}

			if (search.getMissingValues() != null) {
				logger.error("[01;32m" + search.getMissingValues() + "[00;00m");
				logger.error("\n[01;32m" + new GsonBuilder().setDateFormat("yyyy-MM-dd").setPrettyPrinting().create().toJson(search.getMissingValues()) + "[00;00m");
				for (String name : search.getMissingValues()) {
					// switch (Missing.valueOf(CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, name))) {
					switch (Missing.valueOf(name)) {
						case refAmount:
							sb.append("   AND (reference_amount_g         = NULL OR reference_amount_g         = 0)") .append("\n");
							break;
						case cfgServing:
							sb.append("   AND (food_guide_serving_g       = NULL OR food_guide_serving_g       = 0)") .append("\n");
							break;
						case tier4Serving:
							sb.append("   AND (tier_4_serving_g           = NULL OR tier_4_serving_g           = 0)") .append("\n");
							break;
						case energy:
							sb.append("   AND (energy_kcal                = NULL OR energy_kcal                = 0)") .append("\n");
							break;
						case cnfCode:
							sb.append("   AND (cnf_group_code             = NULL OR cnf_group_code             = 0)") .append("\n");
							break;
						case rollUp:
							sb.append("   AND (rolled_up                  = NULL OR rolled_up                  = 0)") .append("\n");
							break;
						case sodiumPer100g:
							sb.append("   AND (sodium_amount_per_100g     = NULL OR sodium_amount_per_100g     = 0)") .append("\n");
							break;
						case sugarPer100g:
							sb.append("   AND (sugar_amount_per_100g      = NULL OR sugar_amount_per_100g      = 0)") .append("\n");
							break;
						case fatPer100g:
							sb.append("   AND (totalfat_amount_per_100g   = NULL OR totalfat_amount_per_100g   = 0)") .append("\n");
							break;
						case transfatPer100g:
							sb.append("   AND (transfat_amount_per_100g   = NULL OR transfat_amount_per_100g   = 0)") .append("\n");
							break;
						case satFatPer100g:
							sb.append("   AND (satfat_amount_per_100g     = NULL OR satfat_amount_per_100g     = 0)") .append("\n");
							break;
						case addedSodium:
							sb.append("   AND (contains_added_sodium      = NULL OR contains_added_sodium      = 0)") .append("\n");
							break;
						case addedSugar:
							sb.append("   AND (contains_added_sugar       = NULL OR contains_added_sugar       = 0)") .append("\n");
							break;
						case addedFat:
							sb.append("   AND (contains_added_fat         = NULL OR contains_added_fat         = 0)") .append("\n");
							break;
						case addedTransfat:
							sb.append("   AND (contains_added_transfat    = NULL OR contains_added_transfat    = 0)") .append("\n");
							break;
						case caffeine:
							sb.append("   AND (contains_caffeine          = NULL OR contains_caffeine          = 0)") .append("\n");
							break;
						case freeSugars:
							sb.append("   AND (contains_free_sugars       = NULL OR contains_free_sugars       = 0)") .append("\n");
							break;
						case sugarSubstitute:
							sb.append("   AND (contains_sugar_substitutes = NULL OR contains_sugar_substitutes = 0)") .append("\n");
							break;
					}
				}
			}

			if (search.getLastUpdateDateFrom() != null && search.getLastUpdateDateFrom().matches("\\d{4}-\\d{2}-\\d{2}") && search.getLastUpdateDateTo() != null && search.getLastUpdateDateTo().matches("\\d{4}-\\d{2}-\\d{2}")) {
				if (search.getLastUpdatedFilter() != null) {
					logger.error("[01;32m" + search.getLastUpdatedFilter() + "[00;00m");
					for (String name : search.getLastUpdatedFilter()) {
						// switch (Missing.valueOf(CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, name))) {
						switch (Missing.valueOf(name)) {
							case refAmount:
								sb.append("   AND reference_amount_update_date           BETWEEN CAST(? AS date) AND CAST(? AS date)")           .append("\n");
								break;
							case cfgServing:
								sb.append("   AND food_guide_update_date                 BETWEEN CAST(? AS date) AND CAST(? AS date)")                 .append("\n");
								break;
							case tier4Serving:
								sb.append("   AND tier_4_serving_update_date             BETWEEN CAST(? AS date) AND CAST(? AS date)")             .append("\n");
								break;
							case energy:
							case cnfCode:
								break;
							case rollUp:
								sb.append("   AND rolled_up_update_date                  BETWEEN CAST(? AS date) AND CAST(? AS date)")                  .append("\n");
								break;
							case sodiumPer100g:
								sb.append("   AND sodium_imputation_date                 BETWEEN CAST(? AS date) AND CAST(? AS date)")                 .append("\n");
								break;
							case sugarPer100g:
								sb.append("   AND sugar_imputation_date                  BETWEEN CAST(? AS date) AND CAST(? AS date)")                  .append("\n");
								break;
							case fatPer100g:
								sb.append("   AND totalfat_imputation_date               BETWEEN CAST(? AS date) AND CAST(? AS date)")               .append("\n");
								break;
							case transfatPer100g:
								sb.append("   AND transfat_imputation_date               BETWEEN CAST(? AS date) AND CAST(? AS date)")               .append("\n");
								break;
							case satFatPer100g:
								sb.append("   AND satfat_imputation_date                 BETWEEN CAST(? AS date) AND CAST(? AS date)")                 .append("\n");
								break;
							case addedSodium:
								sb.append("   AND contains_added_sodium_update_date      BETWEEN CAST(? AS date) AND CAST(? AS date)")      .append("\n");
								break;
							case addedSugar:
								sb.append("   AND contains_added_sugar_update_date       BETWEEN CAST(? AS date) AND CAST(? AS date)")       .append("\n");
								break;
							case addedFat:
								sb.append("   AND contains_added_fat_update_date         BETWEEN CAST(? AS date) AND CAST(? AS date)")         .append("\n");
								break;
							case addedTransfat:
								sb.append("   AND contains_added_transfat_update_date    BETWEEN CAST(? AS date) AND CAST(? AS date)")    .append("\n");
								break;
							case caffeine:
								sb.append("   AND contains_caffeine_update_date          BETWEEN CAST(? AS date) AND CAST(? AS date)")          .append("\n");
								break;
							case freeSugars:
								sb.append("   AND contains_free_sugars_update_date       BETWEEN CAST(? AS date) AND CAST(? AS date)")       .append("\n");
								break;
							case sugarSubstitute:
								sb.append("   AND contains_sugar_substitutes_update_date BETWEEN CAST(? AS date) AND CAST(? AS date)") .append("\n");
								break;
						}
					}
				}
			}

			if (search.getComments() != null && !search.getComments().isEmpty()) {
				sb.append("   AND LOWER(comments) LIKE ?").append("\n");
			}

			if (search.getCommitDateFrom() != null && search.getCommitDateFrom().matches("\\d{4}-\\d{2}-\\d{2}") && search.getCommitDateTo() != null && search.getCommitDateTo().matches("\\d{4}-\\d{2}-\\d{2}")) {
				sb.append("   AND commit_date                            BETWEEN CAST(? AS date) AND CAST(? AS date)") .append("\n");
			}

			search.setSql(sb.toString());

			try {
				meta = conn.getMetaData(); // Create Oracle DatabaseMetaData object
				logger.error("[01;34mJDBC driver version is " + meta.getDriverVersion() + "[00;00m"); // Retrieve driver information
				PreparedStatement stmt = conn.prepareStatement(search.getSql()); // Create PreparedStatement

				int i = 0; // keeps count of the number of placeholders

				if (search != null) {
					if (search.getDataSource() != null && search.getDataSource().matches("food|recipe")) {
						stmt.setInt(++i, search.getDataSource().equals("food") ? 1 : 2);
					}
					if (search.getFoodRecipeName() != null && !search.getFoodRecipeName().isEmpty()) {
						stmt.setString(++i, new String("%" + search.getFoodRecipeName() + "%").toLowerCase());
					}
					if (search.getFoodRecipeCode() != null && !search.getFoodRecipeCode().isEmpty()) {
						stmt.setInt(++i, Integer.parseInt(search.getFoodRecipeCode()));
						stmt.setString(++i, new String("" + search.getFoodRecipeCode() + "%"));
					}
					if (search.getCnfCode() != null && !search.getCnfCode().isEmpty()) {
						stmt.setInt(++i, Integer.parseInt(search.getCnfCode()));
					}
					if (search.getSubgroupCode() != null && !search.getSubgroupCode().isEmpty()) {
						stmt.setString(++i, new String("" + search.getSubgroupCode() + "%"));
					}
					if (search.getCfgTier() != null && !search.getCfgTier().equals(CfgTier.ALL.getCode())) {
						switch (search.getCfgTier()) {
							case 1:
							case 2:
							case 3:
							case 4:
								stmt.setInt(++i, search.getCfgTier());
								break;
						}
					}
					if (search.getRecipe()           != null && !search.getRecipe().           equals (RecipeRolled.IGNORE.getCode())) {
						stmt.setInt(++i, search.getRecipe());
					}

					if (search.getContainsAdded() != null && notIgnore) {
						Set<String> keys = map.keySet();
						for (String key : keys) {
							switch (ContainsAdded.valueOf(key)) {
								case sodium:
								case sugar:
								case fat:
								case transfat:
								case caffeine:
								case freeSugars:
								case sugarSubstitute:
									stmt.setInt(++i, map.get(key).equals("true") ? 1 : 2);
									break;
							}
						}
					}

					if (search.getLastUpdateDateFrom() != null && search.getLastUpdateDateFrom().matches("\\d{4}-\\d{2}-\\d{2}") && search.getLastUpdateDateTo() != null && search.getLastUpdateDateTo().matches("\\d{4}-\\d{2}-\\d{2}")) {
						if (search.getLastUpdatedFilter() != null) {
							logger.error("[01;32m" + search.getLastUpdatedFilter() + "[00;00m");
							for (String name : search.getLastUpdatedFilter()) {
								// switch (Missing.valueOf(CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, name))) {
								switch (Missing.valueOf(name)) {
									case refAmount:
									case cfgServing:
									case tier4Serving:
										stmt.setString(++i, search.getLastUpdateDateFrom());
										stmt.setString(++i, search.getLastUpdateDateTo());
										break;
									case energy:
									case cnfCode:
										break;
									case rollUp:
									case sodiumPer100g:
									case sugarPer100g:
									case fatPer100g:
									case transfatPer100g:
									case satFatPer100g:
									case addedSodium:
									case addedSugar:
									case addedFat:
									case addedTransfat:
									case caffeine:
									case freeSugars:
									case sugarSubstitute:
										stmt.setString(++i, search.getLastUpdateDateFrom());
										stmt.setString(++i, search.getLastUpdateDateTo());
										break;
								}
							}
						}
					}

					if (search.getComments() != null && !search.getComments().isEmpty()) {
						stmt.setString(++i, new String("%" + search.getComments() + "%").toLowerCase());
					}

					if (search.getCommitDateFrom() != null && search.getCommitDateFrom().matches("\\d{4}-\\d{2}-\\d{2}") && search.getCommitDateTo() != null && search.getCommitDateTo().matches("\\d{4}-\\d{2}-\\d{2}")) {
						stmt.setString(++i, search.getCommitDateFrom());
						stmt.setString(++i, search.getCommitDateTo());
					}
				}

				logger.error("[01;34mSQL query to follow:\n" + stmt.toString() + "[00;00m");

				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					CanadaFoodGuideDataset foodItem = new CanadaFoodGuideDataset();

					foodItem.setType(rs.getInt("type") == 1 ? "food" : "recipe");
					foodItem.setCode(Integer.parseInt(rs.getString("code")));
					foodItem.setName(rs.getString("name"));
					foodItem.setCnfGroupCode(rs.getInt("cnf_group_code"));
					foodItem.setCfgCode(rs.getInt("cfg_code"));
					foodItem.setCommitDate(rs.getDate("commit_date"));
					foodItem.setEnergyKcal(rs.getDouble("energy_kcal"));
					foodItem.setSodiumAmountPer100g(rs.getDouble("sodium_amount_per_100g"));
					foodItem.setSodiumImputationReference(rs.getString("sodium_imputation_reference"));
					foodItem.setSodiumImputationDate(rs.getDate("sodium_imputation_date"));
					foodItem.setSugarAmountPer100g(rs.getDouble("sugar_amount_per_100g"));
					foodItem.setSugarImputationReference(rs.getString("sugar_imputation_reference"));
					foodItem.setSugarImputationDate(rs.getDate("sugar_imputation_date"));
					foodItem.setTransfatAmountPer100g(rs.getDouble("transfat_amount_per_100g"));
					foodItem.setTransfatImputationReference(rs.getString("transfat_imputation_reference"));
					foodItem.setTransfatImputationDate(rs.getDate("transfat_imputation_date"));
					foodItem.setSatfatAmountPer100g(rs.getDouble("satfat_amount_per_100g"));
					foodItem.setSatfatImputationReference(rs.getString("satfat_imputation_reference"));
					foodItem.setSatfatImputationDate(rs.getDate("satfat_imputation_date"));
					foodItem.setTotalfatAmountPer100g(rs.getDouble("totalfat_amount_per_100g"));
					foodItem.setTotalfatImputationReference(rs.getString("totalfat_imputation_reference"));
					foodItem.setTotalfatImputationDate(rs.getDate("totalfat_imputation_date"));
					foodItem.setContainsAddedSodium(rs.getInt("contains_added_sodium"));
					foodItem.setContainsAddedSodiumUpdateDate(rs.getDate("contains_added_sodium_update_date"));
					foodItem.setContainsAddedSugar(rs.getInt("contains_added_sugar"));
					foodItem.setContainsAddedSugarUpdateDate(rs.getDate("contains_added_sugar_update_date"));
					foodItem.setContainsFreeSugars(rs.getInt("contains_free_sugars"));
					foodItem.setContainsFreeSugarsUpdateDate(rs.getDate("contains_free_sugars_update_date"));
					foodItem.setContainsAddedFat(rs.getInt("contains_added_fat"));
					foodItem.setContainsAddedFatUpdateDate(rs.getDate("contains_added_fat_update_date"));
					foodItem.setContainsAddedTransfat(rs.getInt("contains_added_transfat"));
					foodItem.setContainsAddedTransfatUpdateDate(rs.getDate("contains_added_transfat_update_date"));
					foodItem.setContainsCaffeine(rs.getInt("contains_caffeine"));
					foodItem.setContainsCaffeineUpdateDate(rs.getDate("contains_caffeine_update_date"));
					foodItem.setContainsSugarSubstitutes(rs.getInt("contains_sugar_substitutes"));
					foodItem.setContainsSugarSubstitutesUpdateDate(rs.getDate("contains_sugar_substitutes_update_date"));
					foodItem.setReferenceAmountG(rs.getDouble("reference_amount_g"));
					foodItem.setReferenceAmountMeasure(rs.getString("reference_amount_measure"));
					foodItem.setReferenceAmountUpdateDate(rs.getDate("reference_amount_update_date"));
					foodItem.setFoodGuideServingG(rs.getDouble("food_guide_serving_g"));
					foodItem.setFoodGuideServingMeasure(rs.getString("food_guide_serving_measure"));
					foodItem.setFoodGuideUpdateDate(rs.getDate("food_guide_update_date"));
					foodItem.setTier4ServingG(rs.getDouble("tier_4_serving_g"));
					foodItem.setTier4ServingMeasure(rs.getString("tier_4_serving_measure"));
					foodItem.setTier4ServingUpdateDate(rs.getDate("tier_4_serving_update_date"));
					foodItem.setRolledUp(rs.getInt("rolled_up"));
					foodItem.setRolledUpUpdateDate(rs.getDate("rolled_up_update_date"));
					foodItem.setApplySmallRaAdjustment(rs.getInt("apply_small_ra_adjustment"));
					foodItem.setComments(rs.getString("comments"));

					list.add(foodItem);
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}

//			 logger.error(new GsonBuilder().setDateFormat("yyyy-MM-dd").setPrettyPrinting().create().toJson(list));
		}

		return list;
	}
}
