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

import ca.gc.ip346.classification.model.CanadaFoodGuideFoodItem;
import ca.gc.ip346.classification.model.FoodItem;
// import ca.gc.ip346.classification.model.NewAndImprovedFoodItem;
import ca.gc.ip346.classification.model.SearchBean;
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

// http://localhost:8080/cfg-task-service/service/foods?
// data-source=both
// food-recipe-name=
// food-recipe-code=1684
// commit-date-begin=
// commit-date-end=
// cnf-code=
// subgroup-code=
// cfg-tier=0
// recipe=0
// sodium=0
// sugar=0
// fat=0
// transfat=0
// caffeine=0
// free-sugars=0
// sugar-substitutes=0
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

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	// @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
	public List<CanadaFoodGuideFoodItem> getFoodList(@BeanParam SearchBean search) {
		List<CanadaFoodGuideFoodItem> list = new ArrayList<CanadaFoodGuideFoodItem>(); // Create list

		int i = 0; // keeps count of the number of placeholders

		String sql = ContentHandler.read("canada_food_guide.sql", getClass());
		StringBuffer sb = new StringBuffer(sql);

		sb.append(" WHERE 2 > 1 ").append("\n");
		if (!search.getDataSource().equals("both")) {
			sb.append("   AND fn_recipe_flg = ?").append("\n");
		}
		if (!search.getFoodRecipeName().isEmpty()) {
			sb.append("   AND LOWER(food_desc) like ? OR LOWER(eng_name) like ?").append("\n");
		}
		if (!search.getFoodRecipeCode().isEmpty()) {
			sb.append("   AND food_c = ?").append("\n");
		}
		if (!search.getCnfCode().isEmpty()) {
			sb.append("   AND ").append("\n");
		}
		if (!search.getSubgroupCode().isEmpty()) {
		}

		logger.error("\n" + sb);
		sql = sb.toString();
		logger.error("data source: " + search.getDataSource());

		try {
			meta = conn.getMetaData(); // Create Oracle DatabaseMetaData object
			logger.trace("[01;34mJDBC driver version is " + meta.getDriverVersion() + "[00;00m"); // Retrieve driver information
			PreparedStatement stmt = conn.prepareStatement(sql); // Create PreparedStatement

			if (!search.getDataSource().equals("both")) {
				stmt.setInt(++i, search.getDataSource().equals("cnf") ? 0 : 1);
			}
			if (!search.getFoodRecipeName().isEmpty()) {
				stmt.setString(++i, new String("%" + search.getFoodRecipeName() + "%").toLowerCase());
				stmt.setString(++i, new String("%" + search.getFoodRecipeName() + "%").toLowerCase());
			}
			if (!search.getFoodRecipeCode().isEmpty()) {
				stmt.setInt(++i, Integer.parseInt(search.getFoodRecipeCode()));
			}

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
//				 NewAndImprovedFoodItem food = new NewAndImprovedFoodItem();
				CanadaFoodGuideFoodItem foodItem = new CanadaFoodGuideFoodItem();

//				 food.setFoodC(rs.getDouble("food_c"));
//				 food.setEngName(rs.getString("eng_name"));
//				 food.setFrName(rs.getString("fr_name"));
//				 food.setFoodDesc(rs.getString("food_desc"));
//				 food.setFoodDescF(rs.getString("food_desc_f"));
//				 food.setNutCanC(rs.getDouble("nut_can_c"));
//				 food.setCnfFlag(rs.getInt("cnf_flag"));
//				 food.setDateEntry(rs.getDate("date_entry"));
//				 food.setDateChange(rs.getDate("date_change"));
//				 food.setDateEnd(rs.getDate("date_end"));
//				 food.setDatePublic(rs.getDate("date_public"));
//				 food.setCommentT(rs.getString("comment_t"));
//				 food.setGroupC(rs.getInt("group_c"));
//				 food.setSourceC(rs.getInt("source_c"));
//				 food.setItemC(rs.getInt("item_c"));
//				 food.setCountryC(rs.getString("country_c"));
//				 food.setCnfRelease(rs.getString("cnf_release"));
//				 food.setFoodReference(rs.getString("food_reference"));
//				 food.setScientificName(rs.getString("scientific_name"));
//				 food.setPublicationFlag(rs.getString("publication_flag"));
//				 food.setPublicationCode(rs.getString("publication_code"));
//				 food.setSequenceC(rs.getInt("sequence_c"));
//				 food.setLegacyGroupC(rs.getDouble("legacy_group_c"));
//				 food.setFnCommentF(rs.getString("fn_comment_f"));
//				 food.setFnDbSourceC(rs.getInt("fn_db_source_c"));
//				 food.setFnRecipeFlg(rs.getInt("fn_recipe_flg"));
//				 food.setFnSystemViewC(rs.getInt("fn_system_view_c"));
//				 food.setFnFatChange(rs.getDouble("fn_fat_change"));
//				 food.setFnMoistureChange(rs.getDouble("fn_moisture_change"));
//				 food.setFnSysUserCreateC(rs.getInt("fn_sys_user_create_c"));
//				 food.setFnSysUserEditC(rs.getInt("fn_sys_user_edit_c"));
//				 food.setFnTemplateC(rs.getDouble("fn_template_c"));
//				 food.setFnTemp(rs.getInt("fn_temp"));
//				 food.setFnArchived(rs.getDate("fn_archived"));
//				 food.setFnLegacyC(rs.getString("fn_legacy_c"));
//				 food.setUsRecipeC(rs.getDouble("us_recipe_c"));
//				 food.setUsdaModified(rs.getInt("usda_modified"));
//				 food.setUsdaTemp(rs.getString("usda_temp"));
//				 food.setCanadaFoodSubgroupId(rs.getDouble("canada_food_subgroup_id"));
//				 food.setCfgheFlag(rs.getInt("cfghe_flag"));
//				 food.setOrigCanadaFoodSubgroupId(rs.getDouble("orig_canada_food_subgroup_id"));
//				 food.setFoodOwner(rs.getInt("food_owner"));
//				 food.setSharedFood(rs.getInt("shared_food"));
//				 food.setCommonNmE(rs.getString("common_nm_e"));
//				 food.setCommonNmF(rs.getString("common_nm_f"));
//				 food.setCandiRecNum(rs.getString("candi_rec_num"));
//				 food.setInheritanceFlag(rs.getInt("inheritance_flag"));
//				 food.setFoodCode(rs.getDouble("food_code"));

//				 list.add(food);

				foodItem.setFoodRecipeType(rs.getInt("fn_recipe_flg") == 0);                                           // Boolean foodRecipeType
				foodItem.setCode(rs.getString("code"));                                                                // String code
				foodItem.setName(rs.getString("name"));                                                                // String name
				foodItem.setCnfCode(rs.getString("cnf_code"));                                                         // String cnfCode
				foodItem.setCfgCode(rs.getString("cfg_code"));                                                         // String cfgCode
				foodItem.setCfgCodeCommitDate(rs.getDate("cfg_code_commit_date"));                                     // Date cfgCodeCommitDate
				foodItem.setEnergyKcal(rs.getDouble("energy_kcal"));                                                   // Double energyKcal
				foodItem.setSodiumAmountPer100g(rs.getDouble("sodium_amount_per_100g"));                               // Double sodiumAmountPer100g
				foodItem.setSodiumImputationReference(rs.getString("sodium_imputation_reference"));                    // String sodiumImputationReference
				foodItem.setSodiumImputationDate(rs.getDate("sodium_imputation_date"));                                // Date sodiumImputationDate
				foodItem.setSugarAmountPer100g(rs.getDouble("sugar_amount_per_100g"));                                 // Double sugarAmountPer100g
				foodItem.setSugarImputationReference(rs.getString("sugar_imputation_reference"));                      // String sugarImputationReference
				foodItem.setSugarImputationDate(rs.getDate("sugar_imputation_date"));                                  // Date sugarImputationDate
				foodItem.setTransfatAmountPer100g(rs.getDouble("transfat_amount_per_100g"));                           // Double transfatAmountPer100g
				foodItem.setTransfatImputationReference(rs.getString("transfat_imputation_reference"));                // String transfatImputationReference
				foodItem.setTransfatImputationDate(rs.getDate("transfat_imputation_date"));                            // Date transfatImputationDate
				foodItem.setSatfatAmoutPer100g(rs.getDouble("satfat_amout_per_100g"));                                 // Double satfatAmoutPer100g
				foodItem.setSatfatImputationReference(rs.getString("satfat_imputation_reference"));                    // String satfatImputationReference
				foodItem.setSatfatImputationDate(rs.getDate("satfat_imputation_date"));                                // Date satfatImputationDate
				foodItem.setTotalfatAmoutPer100g(rs.getDouble("totalfat_amout_per_100g"));                             // Double totalfatAmoutPer100g
				foodItem.setTotalfatImputationReference(rs.getString("totalfat_imputation_reference"));                // String totalfatImputationReference
				foodItem.setTotalfatImputationDate(rs.getDate("totalfat_imputation_date"));                            // Date totalfatImputationDate
				foodItem.setContainsAddedSodium(rs.getInt("contains_added_sodium") == 0);                              // Boolean containsAddedSodium
				foodItem.setContainsAddedSodiumCommitDate(rs.getDate("contains_added_sodium_commit_date"));            // Date containsAddedSodiumCommitDate
				foodItem.setContainsAddedSugar(rs.getInt("contains_added_sugar") == 0);                                // Boolean containsAddedSugar
				foodItem.setContainsAddedSugarCommitDate(rs.getDate("contains_added_sugar_commit_date"));              // Date containsAddedSugarCommitDate
				foodItem.setContainsFreeSugars(rs.getInt("contains_free_sugars") == 0);                                // Boolean containsFreeSugars
				foodItem.setContainsFreeSugarsCommitDate(rs.getDate("contains_free_sugars_commit_date"));              // Date containsFreeSugarsCommitDate
				foodItem.setContainsAddedFat(rs.getInt("contains_added_fat") == 0);                                    // Boolean containsAddedFat
				foodItem.setContainsAddedFatCommitDate(rs.getDate("contains_added_fat_commit_date"));                  // Date containsAddedFatCommitDate
				foodItem.setContainsAddedTransfat(rs.getInt("contains_added_transfat") == 0);                          // Boolean containsAddedTransfat
				foodItem.setContainsAddedTransfatCommitDate(rs.getDate("contains_added_transfat_commit_date"));        // Date containsAddedTransfatCommitDate
				foodItem.setContainsCaffeine(rs.getInt("contains_caffeine") == 0);                                     // Boolean containsCaffeine
				foodItem.setContainsCaffeineCommitDate(rs.getDate("contains_caffeine_commit_date"));                   // Date containsCaffeineCommitDate
				foodItem.setContainsSugarSubstitutes(rs.getInt("contains_sugar_substitutes") == 0);                    // Boolean containsSugarSubstitutes
				foodItem.setContainsSugarSubstitutesCommitDate(rs.getDate("contains_sugar_substitutes_commit_date"));  // Date containsSugarSubstitutesCommitDate
				foodItem.setReferenceAmountG(rs.getDouble("reference_amount_g"));                                      // Double referenceAmountG
				foodItem.setReferenceAmountMeasure(rs.getString("reference_amount_measure"));                          // String referenceAmountMeasure
				foodItem.setReferenceAmountCommitDate(rs.getDate("reference_amount_commit_date"));                     // Date referenceAmountCommitDate
				foodItem.setFoodGuideServingG(rs.getDouble("food_guide_serving_g"));                                   // Double foodGuideServingG
				foodItem.setFoodGuideServingMeasure(rs.getString("food_guide_serving_measure"));                       // String foodGuideServingMeasure
				foodItem.setFoodGuideCommitDate(rs.getDate("food_guide_commit_date"));                                 // Date foodGuideCommitDate
				foodItem.setTier4ServingG(rs.getDouble("tier_4_serving_g"));                                           // Double tier4ServingG
				foodItem.setTier4ServingMeasure(rs.getString("tier_4_serving_measure"));                               // String tier4ServingMeasure
				foodItem.setTier4ServingCommitDate(rs.getDate("tier_4_serving_commit_date"));                          // Date tier4ServingCommitDate
				foodItem.setRolledUp(rs.getInt("rolled_up") == 0);                                                     // Boolean rolledUp
				foodItem.setRolledUpCommitDate(rs.getDate("rolled_up_commit_date"));                                   // Date rolledUpCommitDate
				foodItem.setApplySmallRaAdjustment(rs.getInt("apply_small_ra_adjustment") == 0);                       // Boolean applySmallRaAdjustment
				foodItem.setComments(rs.getString("comments"));                                                        // String comments

				list.add(foodItem);
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

		// logger.trace(new GsonBuilder().setDateFormat("yyyy-MM-dd").setPrettyPrinting().create().toJson(list));

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

		map.put("foodRecipeType",                      "Boolean");
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
}
