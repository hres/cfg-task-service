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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.annotation.JacksonFeatures;
import com.google.gson.GsonBuilder;

import ca.gc.ip346.classification.model.Added;
import ca.gc.ip346.classification.model.CanadaFoodGuideDataset;
import ca.gc.ip346.classification.model.CfgTier;
import ca.gc.ip346.classification.model.FoodItem;
import ca.gc.ip346.classification.model.RecipeRolled;
// import ca.gc.ip346.classification.model.NewAndImprovedFoodItem;
import ca.gc.ip346.classification.model.CfgFilter;
import ca.gc.ip346.util.DBConnection;

@Path("/datasets")
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

// data-source=both
// food-recipe-name=
// food-recipe-code=1684
// commit-date-begin=
// commit-date-end=
// cnf-code=
// subgroup-code=
//
// cfg-tier=0
// recipe=0
// sodium=0
// sugar=0
// fat=0
// transfat=0
// caffeine=0
// free-sugars=0
// sugar-substitutes=0
//
// reference-amount-missing=on
// cfg-serving-missing=on
// tier-4-serving-missing=on
// energy-value-missing=on
// cnf-code-missing=on
// recipe-rolled-up-down-missing=on
// sodium-value-missing=on
// sugar-value-missing=on
// fat-value-missing=on
// transfat-value-missing=on
// satfat-value-missing=on
// select-all-missing=on
// added-sodium-missing=on
// added-sugar-missing=on
// added-transfat-missing=on
// added-caffeine-missing=on
// added-free-sugars-missing=on
// added-sugar-substitutes-missing=on

//         id="data-source"
//         id="food-recipe-name"
//         id="food-recipe-code"
//         id="commit-date-from"
//         id="commit-date-to"
//         id="cnf-code"
//         id="subgroup-code"
//         id="cfg-tier"
//         id="recipe"
//         id="sodium"
//         id="sugar"
//         id="fat"
//         id="transfat"
//         id="caffeine"
//         id="free-sugars"
//         id="sugar-substitutes"
//         id="reference-amount-missing"
//         id="cfg-serving-missing"
//         id="tier-4-serving-missing"
//         id="energy-value-missing"
//         id="cnf-code-missing"
//         id="recipe-rolled-up-down-missing"
//         id="sodium-value-missing"
//         id="sugar-value-missing"
//         id="fat-value-missing"
//         id="transfat-value-missing"
//         id="satfat-value-missing"
//         id="added-sodium-missing"
//         id="added-sugar-missing"
//         id="added-transfat-missing"
//         id="added-caffeine-missing"
//         id="added-free-sugars-missing"
//         id="added-sugar-substitutes-missing"
//         id="comments"
//         id="last-update-date-From"
//         id="last-update-date-To"
//         id="reference-amount-last-updated"
//         id="cfg-serving-last-updated"
//         id="tier-4-serving-last-updated"
//         id="energy-value-last-updated"
//         id="cnf-code-last-updated"
//         id="recipe-rolled-up-down-last-updated"
//         id="sodium-value-last-updated"
//         id="sugar-value-last-updated"
//         id="fat-value-last-updated"
//         id="transfat-value-last-updated"
//         id="satfat-value-last-updated"
//         id="added-sodium-last-updated"
//         id="added-sugar-last-updated"
//         id="added-transfat-last-updated"
//         id="added-caffeine-last-updated"
//         id="added-free-sugars-last-updated"
//         id="added-sugar-substitutes-last-updated"

//             data-source=both
//             food-recipe-name=
//             food-recipe-code=
//             commit-date-from=
//             commit-date-to=
//             cnf-code=
//             subgroup-code=
//             cfg-tier=0
//             recipe=0
//             sodium=1
//             sugar=1
//             fat=1
//             transfat=1
//             caffeine=1
//             free-sugars=1
//             sugar-substitutes=1
//             reference-amount-missing=on
//             cfg-serving-missing=on
//             tier-4-serving-missing=on
//             energy-value-missing=on
//             cnf-code-missing=on
//             recipe-rolled-up-down-missing=on
//             sodium-value-missing=on
//             sugar-value-missing=on
//             fat-value-missing=on
//             transfat-value-missing=on
//             satfat-value-missing=on
//             added-sodium-missing=on
//             added-sugar-missing=on
//             added-transfat-missing=on
//             added-caffeine-missing=on
//             added-free-sugars-missing=on
//             added-sugar-substitutes-missing=on
//             comments=
//             last-update-date-From=
//             last-update-date-To=
//             reference-amount-last-updated=on
//             cfg-serving-last-updated=on
//             tier-4-serving-last-updated=on
//             energy-value-last-updated=on
//             cnf-code-last-updated=on
//             recipe-rolled-up-down-last-updated=on
//             sodium-value-last-updated=on
//             sugar-value-last-updated=on
//             fat-value-last-updated=on
//             transfat-value-last-updated=on
//             satfat-value-last-updated=on
//             added-sodium-last-updated=on
//             added-sugar-last-updated=on
//             added-transfat-last-updated=on
//             added-caffeine-last-updated=on
//             added-free-sugars-last-updated=on
//             added-sugar-substitutes-last-updated=on

	@GET
	@Path("/search")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	// @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
	public List<CanadaFoodGuideDataset> getFoodList(@BeanParam CfgFilter search) {
		String sql = ContentHandler.read("canada_food_guide_dataset.sql", getClass());
		search.setSql(sql);
		return doSearchCriteria(search);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void saveDataset() {
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
	public void getDatasets() {
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
	public void deleteDataset() {
	}

	@POST
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

	@GET
	@Path("/cfg")
	@Produces(MediaType.APPLICATION_JSON)
	@JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
	public Map<String, String> getSampleObj() {
		Map<String, String> map = new HashMap<String, String>();

		map.put("food_recipe_type",                       "boolean"         );
		map.put("code",                                   "text"            );
		map.put("name",                                   "text"            );
		map.put("cnf_code",                               "text"            );
		map.put("cfg_code",                               "text"            );
		map.put("cfg_code_commit_date",                   "date"            );
		map.put("energy_kcal",                            "double precision");
		map.put("sodium_amount_per_100g",                 "double precision");
		map.put("sodium_imputation_reference",            "text"            );
		map.put("sodium_imputation_date",                 "date"            );
		map.put("sugar_amount_per_100g",                  "double precision");
		map.put("sugar_imputation_reference",             "text"            );
		map.put("sugar_imputation_date",                  "date"            );
		map.put("transfat_amount_per_100g",               "double precision");
		map.put("transfat_imputation_reference",          "text"            );
		map.put("transfat_imputation_date",               "date"            );
		map.put("satfat_amout_per_100g",                  "double precision");
		map.put("satfat_imputation_reference",            "text"            );
		map.put("satfat_imputation_date",                 "date"            );
		map.put("totalfat_amout_per_100g",                "double precision");
		map.put("totalfat_imputation_reference",          "text"            );
		map.put("totalfat_imputation_date",               "date"            );
		map.put("contains_added_sodium",                  "boolean"         );
		map.put("contains_added_sodium_commit_date",      "date"            );
		map.put("contains_added_sugar",                   "boolean"         );
		map.put("contains_added_sugar_commit_date",       "date"            );
		map.put("contains_free_sugars",                   "boolean"         );
		map.put("contains_free_sugars_commit_date",       "date"            );
		map.put("contains_added_fat",                     "boolean"         );
		map.put("contains_added_fat_commit_date",         "date"            );
		map.put("contains_added_transfat",                "boolean"         );
		map.put("contains_added_transfat_commit_date",    "date"            );
		map.put("contains_caffeine",                      "boolean"         );
		map.put("contains_caffeine_commit_date",          "date"            );
		map.put("contains_sugar_substitutes",             "boolean"         );
		map.put("contains_sugar_substitutes_commit_date", "date"            );
		map.put("reference_amount_g",                     "double precision");
		map.put("reference_amount_measure",               "text"            );
		map.put("reference_amount_commit_date",           "date"            );
		map.put("food_guide_serving_g",                   "double precision");
		map.put("food_guide_serving_measure",             "text"            );
		map.put("food_guide_commit_date",                 "date"            );
		map.put("tier_4_serving_g",                       "double precision");
		map.put("tier_4_serving_measure",                 "text"            );
		map.put("tier_4_serving_commit_date",             "date"            );
		map.put("rolled_up",                              "boolean"         );
		map.put("rolled_up_commit_date",                  "date"            );
		map.put("apply_small_ra_adjustment",              "boolean"         );
		map.put("comments",                               "text"            );

		return map;
	}

	@GET
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	@JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
	public Map<String, String> getCfgDataSet() {
		Map<String, String> map = new HashMap<String, String>();

		map.put("type",                                "Boolean");
		map.put("code",                                "String");
		map.put("name",                                "String");
		map.put("cnfCode",                             "String");
		map.put("cfgCode",                             "String");
		map.put("cfgCodeCommitDate",                   "Date");
		map.put("energyKcal",                          "Number");
		map.put("sodiumAmountPer100g",                 "Number");
		map.put("sodiumImputationReference",           "String");
		map.put("sodiumImputationDate",                "Date");
		map.put("sugarAmountPer100g",                  "Number");
		map.put("sugarImputationReference",            "String");
		map.put("sugarImputationDate",                 "Date");
		map.put("transfatAmountPer100g",               "Number");
		map.put("transfatImputationReference",         "String");
		map.put("transfatImputationDate",              "Date");
		map.put("satfatAmoutPer100g",                  "Number");
		map.put("satfatImputationReference",           "String");
		map.put("satfatImputationDate",                "Date");
		map.put("totalfatAmoutPer100g",                "Number");
		map.put("totalfatImputationReference",         "String");
		map.put("totalfatImputationDate",              "Date");
		map.put("containsAddedSodium",                 "Boolean");
		map.put("containsAddedSodiumCommitDate",       "Date");
		map.put("containsAddedSugar",                  "Boolean");
		map.put("containsAddedSugarCommitDate",        "Date");
		map.put("containsFreeSugars",                  "Boolean");
		map.put("containsFreeSugarsCommitDate",        "Date");
		map.put("containsAddedFat",                    "Boolean");
		map.put("containsAddedFatCommitDate",          "Date");
		map.put("containsAddedTransfat",               "Boolean");
		map.put("containsAddedTransfatCommitDate",     "Date");
		map.put("containsCaffeine",                    "Boolean");
		map.put("containsCaffeineCommitDate",          "Date");
		map.put("containsSugarSubstitutes",            "Boolean");
		map.put("containsSugarSubstitutesCommitDate",  "Date");
		map.put("referenceAmountG",                    "Number");
		map.put("referenceAmountMeasure",              "String");
		map.put("referenceAmountCommitDate",           "Date");
		map.put("foodGuideServingG",                   "Number");
		map.put("foodGuideServingMeasure",             "String");
		map.put("foodGuideCommitDate",                 "Date");
		map.put("tier4ServingG",                       "Number");
		map.put("tier4ServingMeasure",                 "String");
		map.put("tier4ServingCommitDate",              "Date");
		map.put("rolledUp",                            "Boolean");
		map.put("rolledUpCommitDate",                  "Date");
		map.put("applySmallRaAdjustment",              "Boolean");
		map.put("comments",                            "String");

		return map;
	}

	private List<CanadaFoodGuideDataset> doSearchCriteria(CfgFilter search) {
		List<CanadaFoodGuideDataset> list = new ArrayList<CanadaFoodGuideDataset>();

		if (search != null) {
			StringBuffer sb = new StringBuffer(search.getSql());

			sb.append(" WHERE length('this where-clause is an artifact') = 32 ").append("\n");
			if (!search.getDataSource().equals("0")) {
				sb.append("   AND type = ?").append("\n");
			}
			if (!search.getFoodRecipeName().                                         isEmpty ()) {
				sb.append("   AND LOWER(name) LIKE ?").append("\n");
			}
			if (!search.getFoodRecipeCode().                                         isEmpty ()) {
				sb.append("   AND code = ? OR CAST(code AS text) LIKE ?").append("\n");
			}
			if (!search.getCommitDateFrom().isEmpty () && !search.getCommitDateTo(). isEmpty ()) {
				sb.append("   AND commit_date BETWEEN ? AND ?").append("\n");
			}
			if (!search.getCnfCode().                                                isEmpty ()) {
				sb.append("   AND cnf_group_code = ?").append("\n");
			}
			if (!search.getSubgroupCode().                                           isEmpty ()) {
				sb.append("   AND CAST(cfg_code AS text) LIKE ?").append("\n");
			}

			if (!search.getCfgTier().equals(CfgTier.ALL.getCode())) {
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
			if (search.getRecipe()           != null && !search.getRecipe().           equals (RecipeRolled.IGNORE.getCode())) {
				logger.error("[01;32m" + search.getRecipe() + "[00;00m");
				sb.append("   AND rolled_up = ?").append("\n");
			}
			if (search.getSodium()           != null && !search.getSodium().           equals (Added.IGNORE.getCode())) {
				logger.error("[01;32m" + search.getSodium() + "[00;00m");
				sb.append("   AND contains_added_sodium = ?").append("\n");
			}
			if (search.getSugar()            != null && !search.getSugar().            equals (Added.IGNORE.getCode())) {
				logger.error("[01;32m" + search.getSugar() + "[00;00m");
				sb.append("   AND contains_added_sugar = ?").append("\n");
			}
			if (search.getFat()              != null && !search.getFat().              equals (Added.IGNORE.getCode())) {
				logger.error("[01;32m" + search.getFat() + "[00;00m");
				sb.append("   AND contains_added_fat = ?").append("\n");
			}
			if (search.getTransfat()         != null && !search.getTransfat().         equals (Added.IGNORE.getCode())) {
				logger.error("[01;32m" + search.getTransfat() + "[00;00m");
				sb.append("   AND contains_added_transfat = ?").append("\n");
			}
			if (search.getCaffeine()         != null && !search.getCaffeine().         equals (Added.IGNORE.getCode())) {
				logger.error("[01;32m" + search.getCaffeine() + "[00;00m");
				sb.append("   AND contains_caffeine = ?").append("\n");
			}
			if (search.getFreeSugars()       != null && !search.getFreeSugars().       equals (Added.IGNORE.getCode())) {
				logger.error("[01;32m" + search.getFreeSugars() + "[00;00m");
				sb.append("   AND contains_free_sugars = ?").append("\n");
			}
			if (search.getSugarSubstitutes() != null && !search.getSugarSubstitutes(). equals (Added.IGNORE.getCode())) {
				logger.error("[01;32m" + search.getSugarSubstitutes() + "[00;00m");
				sb.append("   AND contains_sugar_substitutes = ?").append("\n");
			}

			if (search.getReferenceAmountMissing()       != null && !search.getReferenceAmountMissing().       isEmpty ())  {
				logger.error("[01;31m" + search.getReferenceAmountMissing() + "[00;00m");
			}
			if (search.getCfgServingMissing()            != null && !search.getCfgServingMissing().            isEmpty ())  {
				logger.error("[01;31m" + search.getCfgServingMissing() + "[00;00m");
			}
			if (search.getTier4ServingMissing()          != null && !search.getTier4ServingMissing().          isEmpty ())  {
				logger.error("[01;31m" + search.getTier4ServingMissing() + "[00;00m");
			}
			if (search.getEnergyValueMissing()           != null && !search.getEnergyValueMissing().           isEmpty ())  {
				logger.error("[01;31m" + search.getEnergyValueMissing() + "[00;00m");
			}
			if (search.getCnfCodeMissing()               != null && !search.getCnfCodeMissing().               isEmpty ())  {
				logger.error("[01;31m" + search.getCnfCodeMissing() + "[00;00m");
			}
			if (search.getRecipeRolledUpDownMissing()    != null && !search.getRecipeRolledUpDownMissing().    isEmpty ())  {
				logger.error("[01;31m" + search.getRecipeRolledUpDownMissing() + "[00;00m");
			}
			if (search.getSodiumValueMissing()           != null && !search.getSodiumValueMissing().           isEmpty ())  {
				logger.error("[01;31m" + search.getSodiumValueMissing() + "[00;00m");
			}
			if (search.getSugarValueMissing()            != null && !search.getSugarValueMissing().            isEmpty ())  {
				logger.error("[01;31m" + search.getSugarValueMissing() + "[00;00m");
			}
			if (search.getFatValueMissing()              != null && !search.getFatValueMissing().              isEmpty ())  {
				logger.error("[01;31m" + search.getFatValueMissing() + "[00;00m");
			}
			if (search.getTransfatValueMissing()         != null && !search.getTransfatValueMissing().         isEmpty ())  {
				logger.error("[01;31m" + search.getTransfatValueMissing() + "[00;00m");
			}
			if (search.getSatfatValueMissing()           != null && !search.getSatfatValueMissing().           isEmpty ())  {
				logger.error("[01;31m" + search.getSatfatValueMissing() + "[00;00m");
			}
			if (search.getAddedSodiumMissing()           != null && !search.getAddedSodiumMissing().           isEmpty ())  {
				logger.error("[01;31m" + search.getAddedSodiumMissing() + "[00;00m");
			}
			if (search.getAddedSugarMissing()            != null && !search.getAddedSugarMissing().            isEmpty ())  {
				logger.error("[01;31m" + search.getAddedSugarMissing() + "[00;00m");
			}
			if (search.getAddedTransfatMissing()         != null && !search.getAddedTransfatMissing().         isEmpty ())  {
				logger.error("[01;31m" + search.getAddedTransfatMissing() + "[00;00m");
			}
			if (search.getAddedCaffeineMissing()         != null && !search.getAddedCaffeineMissing().         isEmpty ())  {
				logger.error("[01;31m" + search.getAddedCaffeineMissing() + "[00;00m");
			}
			if (search.getAddedFreeSugarsMissing()       != null && !search.getAddedFreeSugarsMissing().       isEmpty ())  {
				logger.error("[01;31m" + search.getAddedFreeSugarsMissing() + "[00;00m");
			}
			if (search.getAddedSugarSubstitutesMissing() != null && !search.getAddedSugarSubstitutesMissing(). isEmpty ())  {
				logger.error("[01;31m" + search.getAddedSugarSubstitutesMissing() + "[00;00m");
			}

			if (!search.getComments().isEmpty()) {
				logger.error("[01;30m" + search.getComments() + "[00;00m");
			}
			if (!search.getLastUpdateDateFrom().isEmpty() && !search.getLastUpdateDateTo().isEmpty()) {
				if (search.getReferenceAmountLastUpdated()       != null && !search.getReferenceAmountLastUpdated().       isEmpty ())  {
					logger.error("[01;30m" + search.getReferenceAmountLastUpdated() + "[00;00m");
				}
				if (search.getCfgServingLastUpdated()            != null && !search.getCfgServingLastUpdated().            isEmpty ())  {
					logger.error("[01;30m" + search.getCfgServingLastUpdated() + "[00;00m");
				}
				if (search.getTier4ServingLastUpdated()          != null && !search.getTier4ServingLastUpdated().          isEmpty ())  {
					logger.error("[01;30m" + search.getTier4ServingLastUpdated() + "[00;00m");
				}
				if (search.getEnergyValueLastUpdated()           != null && !search.getEnergyValueLastUpdated().           isEmpty ())  {
					logger.error("[01;30m" + search.getEnergyValueLastUpdated() + "[00;00m");
				}
				if (search.getCnfCodeLastUpdated()               != null && !search.getCnfCodeLastUpdated().               isEmpty ())  {
					logger.error("[01;30m" + search.getCnfCodeLastUpdated() + "[00;00m");
				}
				if (search.getRecipeRolledUpDownLastUpdated()    != null && !search.getRecipeRolledUpDownLastUpdated().    isEmpty ())  {
					logger.error("[01;30m" + search.getRecipeRolledUpDownLastUpdated() + "[00;00m");
				}
				if (search.getSodiumValueLastUpdated()           != null && !search.getSodiumValueLastUpdated().           isEmpty ())  {
					logger.error("[01;30m" + search.getSodiumValueLastUpdated() + "[00;00m");
				}
				if (search.getSugarValueLastUpdated()            != null && !search.getSugarValueLastUpdated().            isEmpty ())  {
					logger.error("[01;30m" + search.getSugarValueLastUpdated() + "[00;00m");
				}
				if (search.getFatValueLastUpdated()              != null && !search.getFatValueLastUpdated().              isEmpty ())  {
					logger.error("[01;30m" + search.getFatValueLastUpdated() + "[00;00m");
				}
				if (search.getTransfatValueLastUpdated()         != null && !search.getTransfatValueLastUpdated().         isEmpty ())  {
					logger.error("[01;30m" + search.getTransfatValueLastUpdated() + "[00;00m");
				}
				if (search.getSatfatValueLastUpdated()           != null && !search.getSatfatValueLastUpdated().           isEmpty ())  {
					logger.error("[01;30m" + search.getSatfatValueLastUpdated() + "[00;00m");
				}
				if (search.getAddedSodiumLastUpdated()           != null && !search.getAddedSodiumLastUpdated().           isEmpty ())  {
					logger.error("[01;30m" + search.getAddedSodiumLastUpdated() + "[00;00m");
				}
				if (search.getAddedSugarLastUpdated()            != null && !search.getAddedSugarLastUpdated().            isEmpty ())  {
					logger.error("[01;30m" + search.getAddedSugarLastUpdated() + "[00;00m");
				}
				if (search.getAddedTransfatLastUpdated()         != null && !search.getAddedTransfatLastUpdated().         isEmpty ())  {
					logger.error("[01;30m" + search.getAddedTransfatLastUpdated() + "[00;00m");
				}
				if (search.getAddedCaffeineLastUpdated()         != null && !search.getAddedCaffeineLastUpdated().         isEmpty ())  {
					logger.error("[01;30m" + search.getAddedCaffeineLastUpdated() + "[00;00m");
				}
				if (search.getAddedFreeSugarsLastUpdated()       != null && !search.getAddedFreeSugarsLastUpdated().       isEmpty ())  {
					logger.error("[01;30m" + search.getAddedFreeSugarsLastUpdated() + "[00;00m");
				}
				if (search.getAddedSugarSubstitutesLastUpdated() != null && !search.getAddedSugarSubstitutesLastUpdated(). isEmpty ())  {
					logger.error("[01;30m" + search.getAddedSugarSubstitutesLastUpdated() + "[00;00m");
				}
			}

			logger.error("[01;34mSQL query to follow:\n" + sb + "[00;00m");

			search.setSql(sb.toString());

			try {
				meta = conn.getMetaData(); // Create Oracle DatabaseMetaData object
				logger.error("[01;34mJDBC driver version is " + meta.getDriverVersion() + "[00;00m"); // Retrieve driver information
				PreparedStatement stmt = conn.prepareStatement(search.getSql()); // Create PreparedStatement

				int i = 0; // keeps count of the number of placeholders

				if (search != null) {
					if (!search.getDataSource().equals("0")) {
						stmt.setInt(++i, Integer.parseInt(search.getDataSource()));
					}
					if (!search.getFoodRecipeName().isEmpty()) {
						stmt.setString(++i, new String("%" + search.getFoodRecipeName() + "%").toLowerCase());
					}
					if (!search.getFoodRecipeCode().isEmpty()) {
						stmt.setInt(++i, Integer.parseInt(search.getFoodRecipeCode()));
						stmt.setString(++i, new String("" + search.getFoodRecipeCode() + "%"));
					}
					if (!search.getCommitDateFrom().isEmpty() && !search.getCommitDateTo().isEmpty()) {
						stmt.setString(++i, search.getCommitDateFrom());
						stmt.setString(++i, search.getCommitDateTo());
					}
					if (!search.getCnfCode().isEmpty()) {
						stmt.setInt(++i, Integer.parseInt(search.getCnfCode()));
					}
					if (!search.getSubgroupCode().isEmpty()) {
						stmt.setString(++i, new String("" + search.getSubgroupCode() + "%"));
					}
					if (!search.getCfgTier().equals(CfgTier.ALL.getCode())) {
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
					if (search.getSodium()           != null && !search.getSodium().           equals (Added.IGNORE.getCode())) {
						stmt.setInt(++i, search.getSodium());
					}
					if (search.getSugar()            != null && !search.getSugar().            equals (Added.IGNORE.getCode())) {
						stmt.setInt(++i, search.getSugar());
					}
					if (search.getFat()              != null && !search.getFat().              equals (Added.IGNORE.getCode())) {
						stmt.setInt(++i, search.getFat());
					}
					if (search.getTransfat()         != null && !search.getTransfat().         equals (Added.IGNORE.getCode())) {
						stmt.setInt(++i, search.getTransfat());
					}
					if (search.getCaffeine()         != null && !search.getCaffeine().         equals (Added.IGNORE.getCode())) {
						stmt.setInt(++i, search.getCaffeine());
					}
					if (search.getFreeSugars()       != null && !search.getFreeSugars().       equals (Added.IGNORE.getCode())) {
						stmt.setInt(++i, search.getFreeSugars());
					}
					if (search.getSugarSubstitutes() != null && !search.getSugarSubstitutes(). equals (Added.IGNORE.getCode())) {
						stmt.setInt(++i, search.getSugarSubstitutes());
					}
				}

				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					CanadaFoodGuideDataset foodItem = new CanadaFoodGuideDataset();

					foodItem.setType(Integer.parseInt(rs.getString("type")));
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
					foodItem.setSatfatAmoutPer100g(rs.getDouble("satfat_amout_per_100g"));
					foodItem.setSatfatImputationReference(rs.getString("satfat_imputation_reference"));
					foodItem.setSatfatImputationDate(rs.getDate("satfat_imputation_date"));
					foodItem.setTotalfatAmoutPer100g(rs.getDouble("totalfat_amout_per_100g"));
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

			// logger.error(new GsonBuilder().setDateFormat("yyyy-MM-dd").setPrettyPrinting().create().toJson(list));
		}

		return list;
	}
}
