package ca.gc.ip346.classification.resource;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.currentDate;
import static com.mongodb.client.model.Updates.set;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.*;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.annotation.JacksonFeatures;
import static com.google.common.net.HttpHeaders.*;
import com.google.gson.GsonBuilder;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

// import ca.gc.ip346.classification.model.Added;
import ca.gc.ip346.classification.model.CanadaFoodGuideFoodItem;
import ca.gc.ip346.classification.model.CfgFilter;
import ca.gc.ip346.classification.model.CfgTier;
import ca.gc.ip346.classification.model.ContainsAdded;
import ca.gc.ip346.classification.model.Dataset;
import ca.gc.ip346.classification.model.Missing;
import ca.gc.ip346.classification.model.PseudoBoolean;
import ca.gc.ip346.classification.model.PseudoDouble;
import ca.gc.ip346.classification.model.PseudoInteger;
import ca.gc.ip346.classification.model.PseudoString;
import ca.gc.ip346.classification.model.RecipeRolled;
import ca.gc.ip346.util.ClassificationProperties;
import ca.gc.ip346.util.DBConnection;
import ca.gc.ip346.util.MongoClientFactory;
import ca.gc.ip346.util.RequestURI;

@Path("/datasets")
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class FoodsResource {
	private static final Logger logger = LogManager.getLogger(FoodsResource.class);

	private Connection conn                      = null;
	private DatabaseMetaData meta                = null;
	private MongoClient mongoClient              = null;
	private MongoCollection<Document> collection = null;

	public FoodsResource() {
		mongoClient = MongoClientFactory.getMongoClient();
		collection  = mongoClient.getDatabase(MongoClientFactory.getDatabase()).getCollection(MongoClientFactory.getCollection());

		try {
			conn = DBConnection.getConnections();
		} catch(Exception e) {
			// TODO: proper response to handle exceptions
			logger.error("[01;03;31m" + e.getMessage() + "[00;00;00m");
			logger.error("[01;03;31m" + e.getMessage() + "[00;00;00m");
		}
	}

	@OPTIONS
	@Path("/search")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getFoodListPreflight() {
		Map<String, String> msg = new HashMap<String, String>();
		msg.put("message", "options-catch-all");
		return getResponse(OPTIONS, Response.Status.OK, msg);
	}

	@GET
	@Path("/search")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
	public /* List<CanadaFoodGuideFoodItem> */ Response getFoodList(@BeanParam CfgFilter search) {
		String sql = ContentHandler.read("canada_food_guide_food_item.sql", getClass());
		search.setSql(sql);
		mongoClient.close();
		return doSearchCriteria(search);
	}

	@OPTIONS
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response saveDatasetPreflight() {
		Map<String, String> msg = new HashMap<String, String>();
		msg.put("message", "options-catch-all");
		return getResponse(OPTIONS, Response.Status.OK, msg);
	}

	@POST
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public /* Map<String, Object> */ Response saveDataset(Dataset dataset) {
		ResponseBuilder response = null;
		Status status = null;

		Map<String, Object> map = new HashMap<String, Object>();

		if (dataset.getData() != null && dataset.getName() != null && dataset.getComments() != null) {
			Document doc = new Document()
				.append("data",     dataset.getData())
				.append("name",     dataset.getName())
				.append("env",      dataset.getEnv())
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

			map.put("id", id.toString());

			logger.error("[01;34m" + Response.Status.CREATED.getStatusCode() + " " + Response.Status.CREATED.toString() + "[00;00m");

			status   = Response.Status.CREATED;
			response = Response.status(status);
		} else {
			List<String> list = new ArrayList<String>();

			if (dataset.getData()     == null) list.add("data");
			if (dataset.getName()     == null) list.add("name");
			if (dataset.getEnv()      == null) list.add("env");
			if (dataset.getComments() == null) list.add("comments");

			map.put("code", Response.Status.BAD_REQUEST.getStatusCode());
			map.put("description", Response.Status.BAD_REQUEST.toString() + " - Unable to insert Dataset!");
			map.put("fields", StringUtils.join(list, ", "));

			logger.error("[01;34m" + Response.Status.BAD_REQUEST.toString() + " - Unable to insert Dataset!" + "[00;00m");
			logger.error("[01;34m" + Response.Status.BAD_REQUEST.getStatusCode() + " " + Response.Status.BAD_REQUEST.toString() + "[00;00m");

			status   = Response.Status.BAD_REQUEST;
			response = Response.status(status);
		}

		// mongoClient.close();

		logger.error("[01;31m" + "response status: " + response.build().getStatusInfo() + "[00;00m");

		return getResponse(POST, status, map);
	}

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
	public /* List<Map<String, String>> */ Response getDatasets(@QueryParam("env") String env) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		MongoCursor<Document> cursorDocMap = collection.find(eq("env", env)).iterator();
		while (cursorDocMap.hasNext()) {
			Map<String, String> map = new HashMap<String, String>();
			Document doc = cursorDocMap.next();
			map.put("id", doc.get("_id").toString());

			if (doc.get("name"        ) != null) map.put("name",         doc.get("name"        ).toString());
			if (doc.get("env"         ) != null) map.put("env",          doc.get("env"         ).toString());
			if (doc.get("owner"       ) != null) map.put("owner",        doc.get("owner"       ).toString());
			if (doc.get("status"      ) != null) map.put("status",       doc.get("status"      ).toString());
			if (doc.get("comments"    ) != null) map.put("comments",     doc.get("comments"    ).toString());
			if (doc.get("modifiedDate") != null) map.put("modifiedDate", doc.get("modifiedDate").toString());

			list.add(map);
			logger.error("[01;34mDataset ID: " + doc.get("_id") + "[00;00m");
		}

		mongoClient.close();

		return getResponse(GET, Response.Status.OK, list);
	}

	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
	public /* List<Map<String, Object>> */ Response getDataset(@PathParam("id") String id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		MongoCursor<Document> cursorDocMap = null;

		if (ObjectId.isValid(id)) {
			System.out.println("[01;31m" + "Valid hexadecimal representation of ObjectId " + id + "[00;00m");

			cursorDocMap = collection.find(new Document("_id", new ObjectId(id))).iterator();
		} else {
			Map<String, String> msg = new HashMap<String, String>();
			msg.put("message", "Invalid hexadecimal representation of ObjectId " + id + "");

			System.out.println("[01;31m" + "Invalid hexadecimal representation of ObjectId " + id + "[00;00m");

			mongoClient.close();

			return getResponse(GET, Response.Status.BAD_REQUEST, msg);
		}

		if (!cursorDocMap.hasNext()) {
			Map<String, String> msg = new HashMap<String, String>();
			msg.put("message", "Dataset with ID " + id + " does not exist!");

			logger.error("[01;34m" + "Dataset with ID " + id + " does not exist!" + "[00;00m");

			mongoClient.close();

			return getResponse(GET, Response.Status.NOT_FOUND, msg);
		}

		while (cursorDocMap.hasNext()) {
			Map<String, Object> map = new HashMap<String, Object>();
			Document doc = cursorDocMap.next();
			logger.error("[01;34mDataset ID: " + doc.get("_id") + "[00;00m");

			if (doc != null) {
				map.put("id",           id);
				map.put("data",         doc.get("data"));
				map.put("name",         doc.get("name"));
				map.put("env",          doc.get("env"));
				map.put("owner",        doc.get("owner"));
				map.put("status",       doc.get("status"));
				map.put("comments",     doc.get("comments"));
				map.put("modifiedDate", doc.get("modifiedDate"));
				list.add(map);
			}
		}

		mongoClient.close();

		return getResponse(GET, Response.Status.OK, list.get(0));
	}

	@OPTIONS
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
	public Response deleteDatasetPreflight() {
		Map<String, String> msg = new HashMap<String, String>();
		msg.put("message", "options-catch-all");
		return getResponse(OPTIONS, Response.Status.OK, msg);
	}

	@DELETE
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
	public Response deleteDataset(@PathParam("id") String id) {
		MongoCursor<Document> cursorDocMap = collection.find(new Document("_id", new ObjectId(id))).iterator();

		if (!cursorDocMap.hasNext()) {
			Map<String, String> msg = new HashMap<String, String>();
			msg.put("message", "Dataset with ID " + id + " does not exist!");

			logger.error("[01;34m" + "Dataset with ID " + id + " does not exist!" + "[00;00m");

			mongoClient.close();

			return getResponse(DELETE, Response.Status.NOT_FOUND, msg);
		}

		while (cursorDocMap.hasNext()) {
			Document doc = cursorDocMap.next();
			logger.error("[01;34mDataset ID: " + doc.get("_id") + "[00;00m");

			collection.deleteOne(doc);
		}

		mongoClient.close();

		Map<String, String> msg = new HashMap<String, String>();
		msg.put("message", "Successfully deleted dataset with ID: " + id);

		return getResponse(DELETE, Response.Status.OK, msg);
	}

	@DELETE
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
	public Response deleteAllDatasets() {
		collection.deleteMany(new Document());

		mongoClient.close();

		Map<String, String> msg = new HashMap<String, String>();
		msg.put("message", "Successfully deleted all datasets");

		return getResponse(DELETE, Response.Status.OK, msg);
	}

	@PUT
	@Path("/{id}")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
	public Response updateDataset(@PathParam("id") String id, Dataset dataset) {
		Map<Integer, Map<String, Object>> original_values_map = new HashMap<Integer, Map<String, Object>>();
		Map<Integer, Map<String, Object>> toupdate_values_map = new HashMap<Integer, Map<String, Object>>();
		List<Object> list = null;
		List<Bson> firstLevelSets = new ArrayList<Bson>();
		int changes = 0;

		// retrive the corresponding dataset with the given id
		MongoCursor<Document> cursorDocMap = null;

		if (ObjectId.isValid(id)) {
			System.out.println("[01;31m" + "Valid hexadecimal representation of ObjectId " + id + "[00;00m");

			cursorDocMap = collection.find(new Document("_id", new ObjectId(id))).iterator();
		} else {
			Map<String, String> msg = new HashMap<String, String>();
			msg.put("message", "Invalid hexadecimal representation of ObjectId " + id + "");

			System.out.println("[01;31m" + "Invalid hexadecimal representation of ObjectId " + id + "[00;00m");

			mongoClient.close();

			return getResponse(PUT, Response.Status.BAD_REQUEST, msg);
		}

		if (!cursorDocMap.hasNext()) {
			Map<String, String> msg = new HashMap<String, String>();
			msg.put("message", "Dataset with ID " + id + " does not exist!");

			logger.error("[01;34m" + "Dataset with ID " + id + " does not exist!" + "[00;00m");

			mongoClient.close();

			return getResponse(PUT, Response.Status.NOT_FOUND, msg);
		}

		while (cursorDocMap.hasNext()) {
			Document doc = cursorDocMap.next();

			list = castList(doc.get("data"), Object.class);
			for (Object obj : list) {
				Map<?, ?> mObj = (Map<?, ?>)obj;
				Map<String, Object> tmp = new HashMap<String, Object>();
				Iterator<?> it = mObj.keySet().iterator();
				while (it.hasNext()) {
					String key = (String)it.next();
					tmp.put(key, mObj.get(key));
				}
				original_values_map.put((Integer)tmp.get("code"), tmp);
			}

			logger.error("[01;31mUpdate: " + "casting property 'data' seems to have passed!" + "[00;00m");

			if (!dataset.getName     ().equals(doc.get("name")    )) {
				firstLevelSets.add(set("name", dataset.getName()));
				++changes;
			}
			if (!dataset.getEnv      ().equals(doc.get("env")     )) {
				firstLevelSets.add(set("env", dataset.getEnv()));
				++changes;
			}
			if (!dataset.getOwner    ().equals(doc.get("owner")   )) {
				firstLevelSets.add(set("owner", dataset.getOwner()));
				++changes;
			}
			if (!dataset.getStatus   ().equals(doc.get("status")  )) {
				firstLevelSets.add(set("status", dataset.getStatus()));
				++changes;
			}
			if (!dataset.getComments ().equals(doc.get("comments"))) {
				firstLevelSets.add(set("comments", dataset.getComments()));
				++changes;
			}
		}

		List<Map<String, Object>> updates = dataset.getData();
		for (Map<String, Object> map : updates) {
			toupdate_values_map.put((Integer)map.get("code"), map);

			logger.error("[01;34mDataset: " + toupdate_values_map.get(map.get("code"))             + "[00;00m");
			logger.error("[01;31mname: "    + toupdate_values_map.get(map.get("code")).get("name") + "[00;00m");
		}

		Map<String, String> updateDatePair = new HashMap<String, String>();
		updateDatePair.put("cfgCode",                     "cfgCodeUpdateDate");
		updateDatePair.put("comments",                    "");
		updateDatePair.put("containsAddedFat",            "containsAddedFatUpdateDate");
		updateDatePair.put("containsAddedSodium",         "containsAddedSodiumUpdateDate");
		updateDatePair.put("containsAddedSugar",          "containsAddedSugarUpdateDate");
		updateDatePair.put("containsAddedTransfat",       "containsAddedTransfatUpdateDate");
		updateDatePair.put("containsCaffeine",            "containsCaffeineUpdateDate");
		updateDatePair.put("containsFreeSugars",          "containsFreeSugarsUpdateDate");
		updateDatePair.put("containsSugarSubstitutes",    "containsSugarSubstitutesUpdateDate");
		updateDatePair.put("foodGuideServingG",           "foodGuideUpdateDate");
		updateDatePair.put("foodGuideServingMeasure",     "foodGuideUpdateDate");
		updateDatePair.put("marketedToKids",              "");
		updateDatePair.put("overrideSmallRaAdjustment",   "");
		updateDatePair.put("replacementCode",             "");
		updateDatePair.put("rolledUp",                    "rolledUpUpdateDate");
		updateDatePair.put("satfatAmountPer100g",         "satfatImputationDate");
		updateDatePair.put("satfatImputationReference",   "satfatImputationDate");
		updateDatePair.put("sodiumAmountPer100g",         "sodiumImputationDate");
		updateDatePair.put("sodiumImputationReference",   "sodiumImputationDate");
		updateDatePair.put("sugarAmountPer100g",          "sugarImputationDate");
		updateDatePair.put("sugarImputationReference",    "sugarImputationDate");
		updateDatePair.put("tier4ServingG",               "tier4ServingUpdateDate");
		updateDatePair.put("tier4ServingMeasure",         "tier4ServingUpdateDate");
		updateDatePair.put("totalFatAmountPer100g",       "totalFatImputationDate");
		updateDatePair.put("totalFatImputationReference", "totalFatImputationDate");
		updateDatePair.put("transfatAmountPer100g",       "transfatImputationDate");
		updateDatePair.put("transfatImputationReference", "transfatImputationDate");

		for (Map<String, Object> map : updates) {
			List<Bson> sets = new ArrayList<Bson>();

			logger.error("[01;31msize: " + sets.size() + "[00;00m");

			if (toupdate_values_map.get(map.get("code")).get("name") != null && !toupdate_values_map .get (map .get ("code")) .get ("name") .equals (original_values_map .get (map .get ("code")) .get ("name"))) {
				sets.add(set("data.$.name", map.get("name")));
				++changes;
				logger.error("[01;31mvalue changed: " + map.get("name") + "[00;00m");
			}

			if (toupdate_values_map .get (map .get ("code")) .get ("cnfGroupCode") != null && !toupdate_values_map .get (map .get ("code")) .get ("cnfGroupCode") .equals (original_values_map .get (map .get ("code")) .get ("cnfGroupCode"))) {
				sets.add(set("data.$.cnfGroupCode", map.get("cnfGroupCode")));
				++changes;
				logger.error("[01;31mvalue changed: " + map.get("cnfGroupCode") + "[00;00m");
			}

			for (String key : updateDatePair.keySet()) {
				changes = updateIfModified(key, updateDatePair.get(key), sets, changes, original_values_map, toupdate_values_map, map);
			}

			if (toupdate_values_map .get (map .get ("code")) .get ("cfgCodeUpdateDate") != null && !toupdate_values_map .get (map .get ("code")) .get ("cfgCodeUpdateDate") .equals (original_values_map .get (map .get ("code")) .get ("cfgCodeUpdateDate"))) {
			}

			if (toupdate_values_map .get (map .get ("code")) .get ("energyKcal") != null && !toupdate_values_map .get (map .get ("code")) .get ("energyKcal") .equals (original_values_map .get (map .get ("code")) .get ("energyKcal"))) {
				sets.add(set("data.$.energyKcal", map.get("energyKcal")));
				++changes;
				logger.error("[01;31mvalue changed: " + map.get("energyKcal") + "[00;00m");
			}

			if (toupdate_values_map .get (map .get ("code")) .get ("sodiumImputationDate") != null && !toupdate_values_map .get (map .get ("code")) .get ("sodiumImputationDate") .equals (original_values_map .get (map .get ("code")) .get ("sodiumImputationDate"))) {
			}

			if (toupdate_values_map .get (map .get ("code")) .get ("sugarImputationDate") != null && !toupdate_values_map .get (map .get ("code")) .get ("sugarImputationDate") .equals (original_values_map .get (map .get ("code")) .get ("sugarImputationDate"))) {
			}

			if (toupdate_values_map .get (map .get ("code")) .get ("transfatImputationDate") != null && !toupdate_values_map .get (map .get ("code")) .get ("transfatImputationDate") .equals (original_values_map .get (map .get ("code")) .get ("transfatImputationDate"))) {
			}

			if (toupdate_values_map .get (map .get ("code")) .get ("satfatImputationDate") != null && !toupdate_values_map .get (map .get ("code")) .get ("satfatImputationDate") .equals (original_values_map .get (map .get ("code")) .get ("satfatImputationDate"))) {
			}

			if (toupdate_values_map .get (map .get ("code")) .get ("totalFatImputationDate") != null && !toupdate_values_map .get (map .get ("code")) .get ("totalFatImputationDate") .equals (original_values_map .get (map .get ("code")) .get ("totalFatImputationDate"))) {
			}

			if (toupdate_values_map .get (map .get ("code")) .get ("containsAddedSodiumUpdateDate") != null && !toupdate_values_map .get (map .get ("code")) .get ("containsAddedSodiumUpdateDate") .equals (original_values_map .get (map .get ("code")) .get ("containsAddedSodiumUpdateDate"))) {
			}

			if (toupdate_values_map .get (map .get ("code")) .get ("containsAddedSugarUpdateDate") != null && !toupdate_values_map .get (map .get ("code")) .get ("containsAddedSugarUpdateDate") .equals (original_values_map .get (map .get ("code")) .get ("containsAddedSugarUpdateDate"))) {
			}

			if (toupdate_values_map .get (map .get ("code")) .get ("containsFreeSugarsUpdateDate") != null && !toupdate_values_map .get (map .get ("code")) .get ("containsFreeSugarsUpdateDate") .equals (original_values_map .get (map .get ("code")) .get ("containsFreeSugarsUpdateDate"))) {
			}

			if (toupdate_values_map .get (map .get ("code")) .get ("containsAddedFatUpdateDate") != null && !toupdate_values_map .get (map .get ("code")) .get ("containsAddedFatUpdateDate") .equals (original_values_map .get (map .get ("code")) .get ("containsAddedFatUpdateDate"))) {
			}

			if (toupdate_values_map .get (map .get ("code")) .get ("containsAddedTransfatUpdateDate") != null && !toupdate_values_map .get (map .get ("code")) .get ("containsAddedTransfatUpdateDate") .equals (original_values_map .get (map .get ("code")) .get ("containsAddedTransfatUpdateDate"))) {
			}

			if (toupdate_values_map .get (map .get ("code")) .get ("containsCaffeineUpdateDate") != null && !toupdate_values_map .get (map .get ("code")) .get ("containsCaffeineUpdateDate") .equals (original_values_map .get (map .get ("code")) .get ("containsCaffeineUpdateDate"))) {
			}

			if (toupdate_values_map .get (map .get ("code")) .get ("containsSugarSubstitutesUpdateDate") != null && !toupdate_values_map .get (map .get ("code")) .get ("containsSugarSubstitutesUpdateDate") .equals (original_values_map .get (map .get ("code")) .get ("containsSugarSubstitutesUpdateDate"))) {
			}

			if (toupdate_values_map .get (map .get ("code")) .get ("referenceAmountG") != null && !toupdate_values_map .get (map .get ("code")) .get ("referenceAmountG") .equals (original_values_map .get (map .get ("code")) .get ("referenceAmountG"))) {
				sets.add(set("data.$.referenceAmountG", map.get("referenceAmountG")));
				sets.add(currentDate("data.$.referenceAmountUpdateDate"));
				++changes;
				logger.error("[01;31mvalue changed: " + map.get("referenceAmountG") + "[00;00m");
			}

			if (toupdate_values_map .get (map .get ("code")) .get ("referenceAmountMeasure") != null && !toupdate_values_map .get (map .get ("code")) .get ("referenceAmountMeasure") .equals (original_values_map .get (map .get ("code")) .get ("referenceAmountMeasure"))) {
				sets.add(set("data.$.referenceAmountMeasure", map.get("referenceAmountMeasure")));
				++changes;
				logger.error("[01;31mvalue changed: " + map.get("referenceAmountMeasure") + "[00;00m");
			}

			if (toupdate_values_map .get (map .get ("code")) .get ("referenceAmountUpdateDate") != null && !toupdate_values_map .get (map .get ("code")) .get ("referenceAmountUpdateDate") .equals (original_values_map .get (map .get ("code")) .get ("referenceAmountUpdateDate"))) {
			}

			if (toupdate_values_map .get (map .get ("code")) .get ("foodGuideUpdateDate") != null && !toupdate_values_map .get (map .get ("code")) .get ("foodGuideUpdateDate") .equals (original_values_map .get (map .get ("code")) .get ("foodGuideUpdateDate"))) {
			}

			if (toupdate_values_map .get (map .get ("code")) .get ("tier4ServingUpdateDate") != null && !toupdate_values_map .get (map .get ("code")) .get ("tier4ServingUpdateDate") .equals (original_values_map .get (map .get ("code")) .get ("tier4ServingUpdateDate"))) {
			}

			if (toupdate_values_map .get (map .get ("code")) .get ("rolledUpUpdateDate") != null && !toupdate_values_map .get (map .get ("code")) .get ("rolledUpUpdateDate") .equals (original_values_map .get (map .get ("code")) .get ("rolledUpUpdateDate"))) {
			}

			// if (toupdate_values_map .get (map .get ("code")) .get ("overrideSmallRaAdjustment")                                                               != null && !toupdate_values_map .get (map .get ("code")) .get ("overrideSmallRaAdjustment")                                    .equals (original_values_map .get (map .get ("code")) .get ("overrideSmallRaAdjustment"))) {
				// sets.add(set("data.$.overrideSmallRaAdjustment", map.get("overrideSmallRaAdjustment")));
				// ++changes;
				// logger.error("[01;31mvalue changed: " + map.get("overrideSmallRaAdjustment") + "[00;00m");
			// }

			if (toupdate_values_map .get (map .get ("code")) .get ("adjustedReferenceAmount")                                                               != null && !toupdate_values_map .get (map .get ("code")) .get ("adjustedReferenceAmount")                                    .equals (original_values_map .get (map .get ("code")) .get ("adjustedReferenceAmount"))) {
				sets.add(set("data.$.adjustedReferenceAmount", map.get("adjustedReferenceAmount")));
				++changes;
				logger.error("[01;31mvalue changed: " + map.get("adjustedReferenceAmount") + "[00;00m");
			}

			if (toupdate_values_map .get (map .get ("code")) .get ("commitDate") != null && !toupdate_values_map .get (map .get ("code")) .get ("commitDate") .equals (original_values_map .get (map .get ("code")) .get ("commitDate"))) {
			}

			logger.error("[01;31msize: " + sets.size() + "[00;00m");

			logger.error("[01;35mcode: " + map.get("code") + "[00;00m");

			if (sets.size() > 0) {
				collection.updateOne(and(eq("_id", new ObjectId(id)), eq("data.code", map.get("code"))), combine(sets));
			}
		}

		if (changes != 0) {
			firstLevelSets.add(currentDate("modifiedDate"));
			collection.updateOne(eq("_id", new ObjectId(id)), combine(firstLevelSets));
		}

		mongoClient.close();

		Map<String, String> msg = new HashMap<String, String>();
		msg.put("message", "Successfully updated dataset");

		return getResponse(PUT, Response.Status.OK, msg);
	}

	@OPTIONS
	@Path("/classify")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
	public Response classifyDatasetPreflight(Dataset dataset) {
		Map<String, String> msg = new HashMap<String, String>();
		msg.put("message", "options-catch-all");
		return getResponse(OPTIONS, Response.Status.OK, msg);
	}

	@POST
	@Path("/classify")
	public Response classifyDataset(Dataset dataset) {
		Response response = null;
		Map<String, String> msg = new HashMap<String, String>();

		if (dataset.getEnv().equals("sandbox")) {
			/**
			 *
			 * first, create a new transient dataset
			 *
			 */
			Map<?, ?> tmp = (Map<?, ?>)saveDataset(dataset).getEntity();
			Map<String, String> map = new HashMap<String, String>();
			for (Entry<?, ?> entry : tmp.entrySet()) {
				map.put((String)entry.getKey(), (String)entry.getValue());
			}
			String id = map.get("id");

			/**
			 *
			 * second, with newly created ID, classify dataset
			 *
			 */
			response = classifyDataset(id);

			/**
			 *
			 * third, delete transient dataset
			 *
			 */
			deleteDataset(id);

			return response;
		} else {
			msg.put("message", "Invalid environment: " + dataset.getEnv() + "");
			return getResponse(POST, Response.Status.BAD_REQUEST, msg);
		}
	}

	@OPTIONS
	@Path("/{id}/classify")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
	public Response classifyDatasetPreflight(String id) {
		Map<String, String> msg = new HashMap<String, String>();
		msg.put("message", "options-catch-all");
		return getResponse(OPTIONS, Response.Status.OK, msg);
	}

	@POST
	@Path("/{id}/classify")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
	@SuppressWarnings("unchecked")
	public Response classifyDataset(@PathParam("id") String id) {
		Map<String, Object> map = null;
		MongoCursor<Document> cursorDocMap = collection.find(new Document("_id", new ObjectId(id))).iterator();
		List<Object> list = null;
		List<Document> dox = new ArrayList<Document>();

		if (!cursorDocMap.hasNext()) {
			Map<String, String> msg = new HashMap<String, String>();
			msg.put("message", "Dataset with ID " + id + " does not exist!");

			logger.error("[01;34m" + "Dataset with ID " + id + " does not exist!" + "[00;00m");

			mongoClient.close();

			return getResponse(POST, Response.Status.NOT_FOUND, msg);
		}

		/**
		 *
		 * first server-side validation: required for classification
		 *
		 */

		List<Map<String, Object>> dataToBeValidated   = new ArrayList<Map<String, Object>>();
		Map<String, String> requiredForClassification = new HashMap<String, String>();
		requiredForClassification.put("type",                      "Scalar"); // Type                         Y                            CFG        Indicates if the item is a Food or Recipe                                                                                             --
		requiredForClassification.put("code",                      "Scalar"); // Code                         Y                            CNF/NSS    CNF Food Code or NSS Recipe Code                                                                                                      --
		requiredForClassification.put("name",                      "Scalar"); // Name                         Y                            CNF/NSS    Food or Recipe Name                                                                                                                   --
		requiredForClassification.put("cfgCode",                   "Object"); // CFG Code                     Y (at least three digits)    CFG        Up to four digit CFG Code (includes tier, if available)                                                                               --
		requiredForClassification.put("sodiumAmountPer100g",       "Object"); // Sodium    Amount (per 100g)  Y                            CNF/NSS    Amount of Sodium per 100 g                                                                                                            --
		requiredForClassification.put("sugarAmountPer100g",        "Object"); // Sugar     Amount (per 100g)  Y                            CNF/NSS    Amount of Sugar per 100g - Provided by source database, unless blank in which case it can be filled in by CFG Classification.         --
		requiredForClassification.put("satfatAmountPer100g",       "Object"); // SatFat    Amount (per 100g)  Y                            CNF/NSS    Amount of Saturated Fat per 100g - Provided by source database, unless blank in which case it can be filled in by CFG Classification. --
		requiredForClassification.put("totalFatAmountPer100g",     "Object"); // TotalFat  Amount (per 100g)  Y                            CNF/NSS    Amount of Total Fat per 100g - Provided by source database, unless blank in which case it can be filled in by CFG Classification.     --
		requiredForClassification.put("containsAddedSodium",       "Object"); // Contains  Added  Sodium      Y                            CFG        Indicates if the item contains added sodium                                                                                           --
		requiredForClassification.put("containsAddedSugar",        "Object"); // Contains  Added  Sugar       Y                            CFG        Indicates if the item contains added sugar                                                                                            --
		requiredForClassification.put("containsAddedFat",          "Object"); // Contains  Added  Fat         Y                            CFG        Indicates if the item contains added fat                                                                                              --
		// requiredForClassification.put("referenceAmountG",          "Scalar"); // Reference Amount (g)         Y                            CNF/NSS                                                                                                                                          --
		// requiredForClassification.put("toddlerItem",               "Scalar"); // Toddler   Item               Y                            CFG                                                                                                                                              --
		requiredForClassification.put("overrideSmallRaAdjustment", "Object"); // Override Small RA Adjustment Y                            CFG        Overrides the Small RA Adjustment that is made for foods that have RA lower than the Small RA Threshold (ie 30g)                      --

		while (cursorDocMap.hasNext()) {
			Boolean isInvalid = false;
			Document doc = cursorDocMap.next();
			if (doc != null) {
				list = castList(doc.get("data"), Object.class);
				for (Object obj : list) {
					Map<String, Object> requiredOnly = new HashMap<String, Object>();
					for (String key : requiredForClassification.keySet()) {
						if (requiredForClassification.get(key).equals("Object")) {
							Document objectifiedValue = (Document)((Document)obj).get(key + "");
							if (objectifiedValue.get("value") != null) {
								requiredOnly.put(key, objectifiedValue.get("value"));
								if (key.equals("cfgCode") && ((Integer)requiredOnly.get(key)).toString().length() != 3 && ((Integer)requiredOnly.get(key)).toString().length() != 4) {
									isInvalid = true;
								}
							} else {
								isInvalid = true;
							}
						}
						if (requiredForClassification.get(key).equals("Scalar")) {
							if (((Document)obj).get(key + "") != null) {
								requiredOnly.put(key, ((Document)obj).get(key + ""));
							} else {
								isInvalid = true;
							}
						}
					}
					dataToBeValidated.add(requiredOnly);
				}
			}

			// use validation rules on "data" property and return response if invalid
			logger.error("[01;03;31m" + "only required fields:\n" + new GsonBuilder().setDateFormat("yyyy-MM-dd").setPrettyPrinting().create().toJson(dataToBeValidated) + "[00;00m");

			if (isInvalid) {
				Map<String, String> msg = new HashMap<String, String>();
				msg.put("message", "Expected required field(s) failed to pass validation in preparation for classification.");
				return getResponse(POST, Response.Status.EXPECTATION_FAILED, msg);
			}
		}

		/**
		 *
		 * rewind dataset to do the actual classification
		 *
		 */

		cursorDocMap = collection.find(new Document("_id", new ObjectId(id))).iterator();

		/**
		 *
		 * objectified properties
		 *
		 */

		Map<String, String> updateDatePair = new HashMap<String, String>();
		updateDatePair.put("cfgCode",                     "cfgCodeUpdateDate"                  );
		updateDatePair.put("comments",                    ""                                   );
		updateDatePair.put("containsAddedFat",            "containsAddedFatUpdateDate"         );
		updateDatePair.put("containsAddedSodium",         "containsAddedSodiumUpdateDate"      );
		updateDatePair.put("containsAddedSugar",          "containsAddedSugarUpdateDate"       );
		updateDatePair.put("containsAddedTransfat",       "containsAddedTransfatUpdateDate"    );
		updateDatePair.put("containsCaffeine",            "containsCaffeineUpdateDate"         );
		updateDatePair.put("containsFreeSugars",          "containsFreeSugarsUpdateDate"       );
		updateDatePair.put("containsSugarSubstitutes",    "containsSugarSubstitutesUpdateDate" );
		updateDatePair.put("foodGuideServingG",           "foodGuideUpdateDate"                );
		updateDatePair.put("foodGuideServingMeasure",     "foodGuideUpdateDate"                );
		updateDatePair.put("marketedToKids",              ""                                   );
		updateDatePair.put("overrideSmallRaAdjustment",   ""                                   );
		updateDatePair.put("replacementCode",             ""                                   );
		updateDatePair.put("rolledUp",                    "rolledUpUpdateDate"                 );
		updateDatePair.put("satfatAmountPer100g",         "satfatImputationDate"               );
		updateDatePair.put("satfatImputationReference",   "satfatImputationDate"               );
		updateDatePair.put("sodiumAmountPer100g",         "sodiumImputationDate"               );
		updateDatePair.put("sodiumImputationReference",   "sodiumImputationDate"               );
		updateDatePair.put("sugarAmountPer100g",          "sugarImputationDate"                );
		updateDatePair.put("sugarImputationReference",    "sugarImputationDate"                );
		updateDatePair.put("tier4ServingG",               "tier4ServingUpdateDate"             );
		updateDatePair.put("tier4ServingMeasure",         "tier4ServingUpdateDate"             );
		updateDatePair.put("totalFatAmountPer100g",       "totalFatImputationDate"             );
		updateDatePair.put("totalFatImputationReference", "totalFatImputationDate"             );
		updateDatePair.put("transfatAmountPer100g",       "transfatImputationDate"             );
		updateDatePair.put("transfatImputationReference", "transfatImputationDate"             );

		/**
		 *
		 * only transform objectified values to literal values
		 *
		 */

		while (cursorDocMap.hasNext()) {
			Document doc = cursorDocMap.next();
			map = new HashMap<String, Object>();
			logger.error("[01;34mDataset ID: " + doc.get("_id") + "[00;00m");

			if (doc != null) {
				list = castList(doc.get("data"), Object.class);
				for (Object obj : list) {
					for (String key : updateDatePair.keySet()) {
						Document objectifiedValue = (Document)((Document)obj).get(key + "");
						((Document)obj).put(key, objectifiedValue.get("value"));
					}
					dox.add((Document)obj);
				}

				map.put("data",         dox);

				map.put("name",         doc.get("name"));
				map.put("env",          doc.get("env"));
				map.put("owner",        doc.get("owner"));
				map.put("status",       doc.get("status"));
				map.put("comments",     doc.get("comments"));
				map.put("modifiedDate", doc.get("modifiedDate"));
			}
		}

		// mongoClient.close();

		Response response = ClientBuilder
			.newClient()
			.target(RequestURI.getUri() + ClassificationProperties.getEndPoint())
			.path("/classify")
			.request()
			.post(Entity.entity(map, MediaType.APPLICATION_JSON));

		logger.error("[01;31m" + "response status: " + response.getStatusInfo() + "[00;00m");
		logger.error("[01;31m" + "request URI: " + RequestURI.getUri() + "[00;00m");

		Map<String, Object> deserialized = (Map<String, Object>)response.readEntity(Object.class);
		List<Object> dataArray = (List<Object>)(deserialized).get("data");
		for (Object obj : dataArray) {
			for (String key : updateDatePair.keySet()) {
				Object value = ((Map<String, Object>)obj).get(key + "");
				Map<String, Object> metadataObject = new HashMap<String, Object>();
				metadataObject.put("value", value);
				metadataObject.put("modified", false);
				((Map<String, Object>)obj).put(key, metadataObject);
			}
		}

		deserialized.put("id", id);

		logger.error("[01;31m" + "response status: " + ((Map<String, Object>)dataArray.get(0)).get("sodiumAmountPer100g") + "[00;00m");

		return getResponse(POST, Response.Status.OK, deserialized);
	}

	@POST
	@Path("/{id}/flags")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
	public Response flagsDataset(@PathParam("id") String id, Dataset dataset) {
		Response response = ClientBuilder
			.newClient()
			.target(RequestURI.getUri() + ClassificationProperties.getEndPoint())
			.path("/flags")
			.request()
			.post(Entity.entity(dataset, MediaType.APPLICATION_JSON));
		return response;
	}

	@POST
	@Path("/{id}/init")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
	public Response initDataset(@PathParam("id") String id, Dataset dataset) {
		Response response = ClientBuilder
			.newClient()
			.target(RequestURI.getUri() + ClassificationProperties.getEndPoint())
			.path("/init")
			.request()
			.post(Entity.entity(dataset, MediaType.APPLICATION_JSON));
		return response;
	}

	@POST
	@Path("/{id}/adjustment")
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
	public Response adjustmentDataset(@PathParam("id") String id, Dataset dataset) {
		Response response = ClientBuilder
			.newClient()
			.target(RequestURI.getUri() + ClassificationProperties.getEndPoint())
			.path("/adjustment")
			.request()
			.post(Entity.entity(dataset, MediaType.APPLICATION_JSON));
		return response;
	}

	@POST
	@Path("/{id}/commit")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
	public void commitDataset() {
	}

	@GET
	@Path("/status")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@JacksonFeatures(serializationEnable = {SerializationFeature.INDENT_OUTPUT})
	public Response getStatusCodes() {
		Map<Integer, String> list = new HashMap<Integer, String>();
		String sql = ContentHandler.read("connectivity_test.sql", getClass());

		Map<Integer, String> map = new HashMap<Integer, String>();
		for (Response.Status obj : Response.Status.values()) {
			map.put(obj.getStatusCode(), obj.name());
		}
		int len = 0;
		for (Integer key : map.keySet()) {
			String value = map.get(key);
			if (value.length() > len) {
				len = value.length();
			}
		}
		String format = new StringBuffer()
			.append("  %d %-")
			.append(len)
			.append("s")
			.toString();
//		 String tamrof = new StringBuffer()
//			 .append("%-")
//			 .append(len + 6)
//			 .append("s")
//			 .toString();
		Integer[][] arr = new Integer[6][18];
		Integer[][] brr = new Integer[2][18];
		Integer series = 0;
		int i = 0;
		int k = 0;
		for (Integer key : map.keySet()) {
			if (key / 100 != series) {
				series = key / 100;
				i = 0;
			}
			if (series == 4) {
				brr[0][i] = key;
			} else {
				brr[1][k++] = key;
			}
			arr[series][i++] = key;
		}
		for (int j = 0; j < 18; ++j) {
			arr[0][j] = null;
			arr[1][j] = null;
		}
//		 for (int l = 0; l < 18; ++l) {
//			 for (int m = 2; m < 6; ++m) {
//				 Integer key = arr[m][l];
//				 if (key != null) {
//					 System.out.printf("[01;%dm" + format + "[00;00m", l % 2 == 0 ? 36 : 35, key, Response.Status.fromStatusCode(key).name());
//				 } else {
//					 System.out.printf(tamrof, "");
//				 }
//			 }
//			 System.out.println();
//		 }
//		 System.out.println();
//		 System.out.println();
//		 for (int l = 0; l < 18; ++l) {
//			 for (int m = 2; m < 6; ++m) {
//				 Integer key = arr[m][l];
//				 if (key != null) {
//					 System.out.printf("[01;%dm" + format + "[00;00m", l % 2 == 0 ? 34 : 31, key, Response.Status.fromStatusCode(key));
//				 } else {
//					 System.out.printf(tamrof, "");
//				 }
//			 }
//			 System.out.println();
//		 }
//		 System.out.println();
		for (int l = 0; l < 18; ++l) {
			for (int m = 0; m < 2; ++m) {
				Integer key = brr[m][l];
				System.out.printf("[01;03;%dm" + format + "[00;00m", l % 2 == 0 ? 34 : 31, key, Response.Status.fromStatusCode(key));
			}
			System.out.println();
		}

		try {
			if (conn != null) {
				PreparedStatement stmt = conn.prepareStatement(sql); // Create PreparedStatement
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					list.put(rs.getInt("canada_food_group_id"), rs.getString("canada_food_group_desc_e"));
				}
				list.put(666, "mongo connectivity test: " + mongoClient.getDB(MongoClientFactory.getDatabase()).command("buildInfo").getString("version"));
				conn.close();
			} else {
				list.put(Response.Status.SERVICE_UNAVAILABLE.getStatusCode(), "PostgreSQL database connectivity test: failed - service unavailable");
				return getResponse(GET, Response.Status.SERVICE_UNAVAILABLE, list);
			}
		} catch(SQLException e) {
			// TODO: proper response to handle exceptions
			logger.error("[01;03;31m" + e.getMessage() + "[00;00;00m");
			for (Response.Status status : Response.Status.values()) {
				list.put(new Integer(status.getStatusCode()), status.getReasonPhrase());
			}
			list.put(666, "mongo connectivity test: " + mongoClient.getDB(MongoClientFactory.getDatabase()).command("buildInfo").getString("version"));
		}

		try {
			logger.error("[01;03;31m" + "mongo connectivity test: " + mongoClient.getAddress() + "[00;00m");
			logger.error("[01;03;31m" + "mongo connectivity test: " + mongoClient.getConnectPoint() + "[00;00m");
			logger.error("[01;03;31m" + "mongo connectivity test: " + mongoClient.getDB(MongoClientFactory.getDatabase()).command("buildInfo").getString("version") + "[00;00m");
		} catch(Exception e) {
			// TODO: proper response to handle exceptions
			Map<String, String> msg = new HashMap<String, String>();
			msg.put("message", e.getMessage());
			logger.error("[01;03;31m" + e.getMessage() + "[00;00;00m");
			mongoClient.close();
			return getResponse(GET, Response.Status.GATEWAY_TIMEOUT, msg);
		}
		mongoClient.close();

		// return getResponse(GET, Response.Status.OK, Response.Status.values());
		return getResponse(GET, Response.Status.OK, list);
	}

	private /* List<CanadaFoodGuideFoodItem> */ Response doSearchCriteria(CfgFilter search) {
		List<CanadaFoodGuideFoodItem> list = new ArrayList<CanadaFoodGuideFoodItem>();

		if (search != null) {
			StringBuffer sb = new StringBuffer(search.getSql());

			logger.error("[01;30m" + search.getDataSource() + "[00;00m");

			sb.append(" WHERE length('this where-clause is an artifact') = 32").append("\n");
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
				switch (search.getRecipe()) {
					case 1:
					case 2:
						sb.append("   AND rolled_up = ?").append("\n");
						break;
					case 3:
						sb.append("   AND (rolled_up = 1 OR rolled_up = 2)").append("\n");
						break;
				}
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
					switch (Missing.valueOf(name)) {
						case refAmount:
							sb.append("   AND reference_amount_g         IS NULL").append("\n");
							break;
						case cfgServing:
							sb.append("   AND food_guide_serving_g       IS NULL").append("\n");
							break;
						case tier4Serving:
							sb.append("   AND tier_4_serving_g           IS NULL").append("\n");
							break;
						case energy:
							sb.append("   AND energy_kcal                IS NULL").append("\n");
							break;
						case cnfCode:
							sb.append("   AND cnf_group_code             IS NULL").append("\n");
							break;
						case rollUp:
							sb.append("   AND rolled_up                  IS NULL").append("\n");
							break;
						case sodiumPer100g:
							sb.append("   AND sodium_amount_per_100g     IS NULL").append("\n");
							break;
						case sugarPer100g:
							sb.append("   AND sugar_amount_per_100g      IS NULL").append("\n");
							break;
						case fatPer100g:
							sb.append("   AND totalfat_amount_per_100g   IS NULL").append("\n");
							break;
						case transfatPer100g:
							sb.append("   AND transfat_amount_per_100g   IS NULL").append("\n");
							break;
						case satFatPer100g:
							sb.append("   AND satfat_amount_per_100g     IS NULL").append("\n");
							break;
						case addedSodium:
							sb.append("   AND contains_added_sodium      IS NULL").append("\n");
							break;
						case addedSugar:
							sb.append("   AND contains_added_sugar       IS NULL").append("\n");
							break;
						case addedFat:
							sb.append("   AND contains_added_fat         IS NULL").append("\n");
							break;
						case addedTransfat:
							sb.append("   AND contains_added_transfat    IS NULL").append("\n");
							break;
						case caffeine:
							sb.append("   AND contains_caffeine          IS NULL").append("\n");
							break;
						case freeSugars:
							sb.append("   AND contains_free_sugars       IS NULL").append("\n");
							break;
						case sugarSubstitute:
							sb.append("   AND contains_sugar_substitutes IS NULL").append("\n");
							break;
					}
				}
			}

			if (search.getLastUpdateDateFrom() != null && search.getLastUpdateDateFrom().matches("\\d{4}-\\d{2}-\\d{2}") && search.getLastUpdateDateTo() != null && search.getLastUpdateDateTo().matches("\\d{4}-\\d{2}-\\d{2}")) {
				if (search.getLastUpdatedFilter() != null) {
					logger.error("[01;32m" + search.getLastUpdatedFilter() + "[00;00m");
					for (String name : search.getLastUpdatedFilter()) {
						switch (Missing.valueOf(name)) {
							case refAmount:
								sb.append("   AND reference_amount_update_date           BETWEEN CAST(? AS date) AND CAST(? AS date)").append("\n");
								break;
							case cfgServing:
								sb.append("   AND food_guide_update_date                 BETWEEN CAST(? AS date) AND CAST(? AS date)").append("\n");
								break;
							case tier4Serving:
								sb.append("   AND tier_4_serving_update_date             BETWEEN CAST(? AS date) AND CAST(? AS date)").append("\n");
								break;
							case energy:
							case cnfCode:
								break;
							case rollUp:
								sb.append("   AND rolled_up_update_date                  BETWEEN CAST(? AS date) AND CAST(? AS date)").append("\n");
								break;
							case sodiumPer100g:
								sb.append("   AND sodium_imputation_date                 BETWEEN CAST(? AS date) AND CAST(? AS date)").append("\n");
								break;
							case sugarPer100g:
								sb.append("   AND sugar_imputation_date                  BETWEEN CAST(? AS date) AND CAST(? AS date)").append("\n");
								break;
							case fatPer100g:
								sb.append("   AND totalfat_imputation_date               BETWEEN CAST(? AS date) AND CAST(? AS date)").append("\n");
								break;
							case transfatPer100g:
								sb.append("   AND transfat_imputation_date               BETWEEN CAST(? AS date) AND CAST(? AS date)").append("\n");
								break;
							case satFatPer100g:
								sb.append("   AND satfat_imputation_date                 BETWEEN CAST(? AS date) AND CAST(? AS date)").append("\n");
								break;
							case addedSodium:
								sb.append("   AND contains_added_sodium_update_date      BETWEEN CAST(? AS date) AND CAST(? AS date)").append("\n");
								break;
							case addedSugar:
								sb.append("   AND contains_added_sugar_update_date       BETWEEN CAST(? AS date) AND CAST(? AS date)").append("\n");
								break;
							case addedFat:
								sb.append("   AND contains_added_fat_update_date         BETWEEN CAST(? AS date) AND CAST(? AS date)").append("\n");
								break;
							case addedTransfat:
								sb.append("   AND contains_added_transfat_update_date    BETWEEN CAST(? AS date) AND CAST(? AS date)").append("\n");
								break;
							case caffeine:
								sb.append("   AND contains_caffeine_update_date          BETWEEN CAST(? AS date) AND CAST(? AS date)").append("\n");
								break;
							case freeSugars:
								sb.append("   AND contains_free_sugars_update_date       BETWEEN CAST(? AS date) AND CAST(? AS date)").append("\n");
								break;
							case sugarSubstitute:
								sb.append("   AND contains_sugar_substitutes_update_date BETWEEN CAST(? AS date) AND CAST(? AS date)").append("\n");
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
				PreparedStatement stmt = conn.prepareStatement(search.getSql(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); // Create PreparedStatement

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
					if (search.getRecipe() != null && !search.getRecipe().equals(RecipeRolled.IGNORE.getCode())) {
						switch (search.getRecipe()) {
							case 1:
							case 2:
								stmt.setInt(++i, search.getRecipe());
								break;
						}
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
									stmt.setBoolean(++i, map.get(key).equals("true"));
									break;
							}
						}
					}

					if (search.getLastUpdateDateFrom() != null && search.getLastUpdateDateFrom().matches("\\d{4}-\\d{2}-\\d{2}") && search.getLastUpdateDateTo() != null && search.getLastUpdateDateTo().matches("\\d{4}-\\d{2}-\\d{2}")) {
						if (search.getLastUpdatedFilter() != null) {
							logger.error("[01;32m" + search.getLastUpdatedFilter() + "[00;00m");
							for (String name : search.getLastUpdatedFilter()) {
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

				rs.last();
				logger.error("[01;03;31m" + rs.getRow() + " row" + (rs.getRow() == 1 ? "" : "s") + "[00;00m");
				rs.beforeFirst();

				while (rs.next()) {
					CanadaFoodGuideFoodItem foodItem = new CanadaFoodGuideFoodItem();

					foodItem.setType(rs.getInt                                                ("type") == 1 ? "food" : "recipe"          );
					foodItem.setCode(rs.getInt                                                ("code")                                   );
					foodItem.setName(rs.getString                                             ("name")                                   );
					if (rs.getString("cnf_group_code") != null) {
						foodItem.setCnfGroupCode(rs.getInt                                    ("cnf_group_code")                         );
					}
					if (rs.getString("cfg_code") != null) {
						foodItem.setCfgCode(new PseudoInteger(rs.getInt                       ("cfg_code"))                              ); // editable
					} else {
						foodItem.setCfgCode(new PseudoInteger()                                                                          );
					}
					foodItem.setCommitDate(rs.getDate                                         ("cfg_code_update_date")                   );
					if (rs.getString("energy_kcal") != null) {
						foodItem.setEnergyKcal(rs.getDouble                                   ("energy_kcal")                            );
					}
					if (rs.getString("sodium_amount_per_100g") != null) {
						foodItem.setSodiumAmountPer100g(new PseudoDouble(rs.getDouble         ("sodium_amount_per_100g"))                ); // editable
					} else {
						foodItem.setSodiumAmountPer100g(new PseudoDouble()                                                               );
					}
					foodItem.setSodiumImputationReference(new PseudoString(rs.getString       ("sodium_imputation_reference"))           );
					foodItem.setSodiumImputationDate(rs.getDate                               ("sodium_imputation_date")                 );
					if (rs.getString("sugar_amount_per_100g") != null) {
						foodItem.setSugarAmountPer100g(new PseudoDouble(rs.getDouble          ("sugar_amount_per_100g"))                 ); // editable
					} else {
						foodItem.setSugarAmountPer100g(new PseudoDouble()                                                                );
					}
					foodItem.setSugarImputationReference(new PseudoString(rs.getString        ("sugar_imputation_reference"))            );
					foodItem.setSugarImputationDate(rs.getDate                                ("sugar_imputation_date")                  );
					if (rs.getString("transfat_amount_per_100g") != null) {
						foodItem.setTransfatAmountPer100g(new PseudoDouble(rs.getDouble       ("transfat_amount_per_100g"))              ); // editable
					} else {
						foodItem.setTransfatAmountPer100g(new PseudoDouble()                                                             );
					}
					foodItem.setTransfatImputationReference(new PseudoString(rs.getString     ("transfat_imputation_reference"))         );
					foodItem.setTransfatImputationDate(rs.getDate                             ("transfat_imputation_date")               );
					if (rs.getString("satfat_amount_per_100g") != null) {
						foodItem.setSatfatAmountPer100g(new PseudoDouble(rs.getDouble         ("satfat_amount_per_100g"))                ); // editable
					} else {
						foodItem.setSatfatAmountPer100g(new PseudoDouble()                                                               );
					}
					foodItem.setSatfatImputationReference(new PseudoString(rs.getString       ("satfat_imputation_reference"))           );
					foodItem.setSatfatImputationDate(rs.getDate                               ("satfat_imputation_date")                 );
					if (rs.getString("totalfat_amount_per_100g") != null) {
						foodItem.setTotalFatAmountPer100g(new PseudoDouble(rs.getDouble       ("totalfat_amount_per_100g"))              ); // editable
					} else {
						foodItem.setTotalFatAmountPer100g(new PseudoDouble()                                                             );
					}
					foodItem.setTotalFatImputationReference(new PseudoString(rs.getString     ("totalfat_imputation_reference"))         );
					foodItem.setTotalFatImputationDate(rs.getDate                             ("totalfat_imputation_date")               );
					if (rs.getString("contains_added_sodium") != null) {
						foodItem.setContainsAddedSodium(new PseudoBoolean(rs.getBoolean       ("contains_added_sodium"))                 ); // editable
					} else {
						foodItem.setContainsAddedSodium(new PseudoBoolean()                                                              );
					}
					foodItem.setContainsAddedSodiumUpdateDate(rs.getDate                      ("contains_added_sodium_update_date")      );
					if (rs.getString("contains_added_sugar") != null) {
						foodItem.setContainsAddedSugar(new PseudoBoolean(rs.getBoolean        ("contains_added_sugar"))                  ); // editable
					} else {
						foodItem.setContainsAddedSugar(new PseudoBoolean()                                                               );
					}
					foodItem.setContainsAddedSugarUpdateDate(rs.getDate                       ("contains_added_sugar_update_date")       );
					if (rs.getString("contains_free_sugars") != null) {
						foodItem.setContainsFreeSugars(new PseudoBoolean(rs.getBoolean        ("contains_free_sugars"))                  ); // editable
					} else {
						foodItem.setContainsFreeSugars(new PseudoBoolean()                                                               );
					}
					foodItem.setContainsFreeSugarsUpdateDate(rs.getDate                       ("contains_free_sugars_update_date")       );
					if (rs.getString("contains_added_fat") != null) {
						foodItem.setContainsAddedFat(new PseudoBoolean(rs.getBoolean          ("contains_added_fat"))                    ); // editable
					} else {
						foodItem.setContainsAddedFat(new PseudoBoolean()                                                                 );
					}
					foodItem.setContainsAddedFatUpdateDate(rs.getDate                         ("contains_added_fat_update_date")         );
					if (rs.getString("contains_added_transfat") != null) {
						foodItem.setContainsAddedTransfat(new PseudoBoolean(rs.getBoolean     ("contains_added_transfat"))               ); // editable
					} else {
						foodItem.setContainsAddedTransfat(new PseudoBoolean()                                                            );
					}
					foodItem.setContainsAddedTransfatUpdateDate(rs.getDate                    ("contains_added_transfat_update_date")    );
					if (rs.getString("contains_caffeine") != null) {
						foodItem.setContainsCaffeine(new PseudoBoolean(rs.getBoolean          ("contains_caffeine"))                     ); // editable
					} else {
						foodItem.setContainsCaffeine(new PseudoBoolean()                                                                 );
					}
					foodItem.setContainsCaffeineUpdateDate(rs.getDate                         ("contains_caffeine_update_date")          );
					if (rs.getString("contains_sugar_substitutes") != null) {
						foodItem.setContainsSugarSubstitutes(new PseudoBoolean(rs.getBoolean  ("contains_sugar_substitutes"))            ); // editable
					} else {
						foodItem.setContainsSugarSubstitutes(new PseudoBoolean()                                                         );
					}
					foodItem.setContainsSugarSubstitutesUpdateDate(rs.getDate                 ("contains_sugar_substitutes_update_date") );
					if (rs.getString("reference_amount_g") != null) {
						foodItem.setReferenceAmountG(rs.getDouble                             ("reference_amount_g")                     ); // editable
					}
					foodItem.setReferenceAmountMeasure(rs.getString                           ("reference_amount_measure")               );
					// foodItem.setReferenceAmountUpdateDate(rs.getDate                          ("reference_amount_update_date")           );
					if (rs.getString("food_guide_serving_g") != null) {
						foodItem.setFoodGuideServingG(new PseudoDouble(rs.getDouble           ("food_guide_serving_g"))                  ); // editable
					} else {
						foodItem.setFoodGuideServingG(new PseudoDouble()                                                                 );
					}
					foodItem.setFoodGuideServingMeasure(new PseudoString(rs.getString         ("food_guide_serving_measure"))            );
					foodItem.setFoodGuideUpdateDate(rs.getDate                                ("food_guide_update_date")                 );
					if (rs.getString("tier_4_serving_g") != null) {
						foodItem.setTier4ServingG(new PseudoDouble(rs.getDouble               ("tier_4_serving_g"))                      ); // editable
					} else {
						foodItem.setTier4ServingG(new PseudoDouble()                                                                     );
					}
					foodItem.setTier4ServingMeasure(new PseudoString(rs.getString             ("tier_4_serving_measure"))                );
					foodItem.setTier4ServingUpdateDate(rs.getDate                             ("tier_4_serving_update_date")             );
					if (rs.getString("rolled_up") != null) {
						foodItem.setRolledUp(new PseudoBoolean(rs.getBoolean                  ("rolled_up"))                             ); // editable
					} else {
						foodItem.setRolledUp(new PseudoBoolean()                                                                         );
					}
					foodItem.setRolledUpUpdateDate(rs.getDate                                 ("rolled_up_update_date")                  );
					if (rs.getString("override_small_ra_adjustment") != null) {
						foodItem.setOverrideSmallRaAdjustment(new PseudoBoolean(rs.getBoolean ("override_small_ra_adjustment"))             );
					} else {
						foodItem.setOverrideSmallRaAdjustment(new PseudoBoolean()                                                        );
					}
					if (rs.getString("replacement_code") != null) {
						foodItem.setReplacementCode(new PseudoInteger(rs.getInt               ("replacement_code"))                      ); // editable
					} else {
						foodItem.setReplacementCode(new PseudoInteger()                                                                  );
					}
					foodItem.setCommitDate(rs.getDate                                         ("commit_date")                            );
					foodItem.setComments(new PseudoString(rs.getString                        ("comments"))                              ); // editable

					list.add(foodItem);
				}
				conn.close();
			} catch(SQLException e) {
				// TODO: proper response to handle exceptions
				logger.error("[01;03;31m" + "" + e.getMessage() + "[00;00m");
				Map<String, String> msg = new HashMap<String, String>();
				msg.put("message", e.getMessage());
				return getResponse(GET, Response.Status.SERVICE_UNAVAILABLE, msg);
			}
		}

		return getResponse(GET, Response.Status.OK, list);
	}

	public static Response getResponse(String method, Response.Status status, Object obj) {
		List<String> allowedHttpOrigins = null;
		List<String> allowedHttpHeaders = null;
		List<String> allowedHttpMethods = null;
		List<String> requestHttpMethods = null;

		allowedHttpOrigins = new ArrayList<String>();
		// allowedHttpOrigins.add("http://10.148.178.250");
		allowedHttpOrigins.add("*");

		allowedHttpHeaders = new ArrayList<String>();
		allowedHttpHeaders.add(ORIGIN);
		allowedHttpHeaders.add(X_REQUESTED_WITH);
		allowedHttpHeaders.add(CONTENT_TYPE);
		allowedHttpHeaders.add(ACCEPT);
		allowedHttpHeaders.add(ACCESS_CONTROL_ALLOW_HEADERS);
		allowedHttpHeaders.add(ACCESS_CONTROL_ALLOW_METHODS);
		allowedHttpHeaders.add(ACCESS_CONTROL_ALLOW_ORIGIN);
		allowedHttpHeaders.add(AUTHORIZATION);

		allowedHttpMethods = new ArrayList<String>();
		allowedHttpMethods.add(GET);
		allowedHttpMethods.add(POST);
		allowedHttpMethods.add(PUT);
		allowedHttpMethods.add(DELETE);
		allowedHttpMethods.add(OPTIONS);
		allowedHttpMethods.add(HEAD);

		requestHttpMethods = new ArrayList<String>();
		requestHttpMethods.add(DELETE);
		requestHttpMethods.add(POST);

		logger.error("[01;03;31m" + StringUtils.join(allowedHttpOrigins.toArray(), ", ") + "[00;00m");
		logger.error("[01;03;31m" + StringUtils.join(allowedHttpHeaders.toArray(), ", ") + "[00;00m");
		logger.error("[01;03;31m" + StringUtils.join(allowedHttpMethods.toArray(), ", ") + "[00;00m");

		ResponseBuilder rb = Response.status(status);
		rb.header(ACCESS_CONTROL_ALLOW_ORIGIN, StringUtils.join(allowedHttpOrigins.toArray(), ", "));
		rb.header(ACCESS_CONTROL_ALLOW_HEADERS, StringUtils.join(allowedHttpHeaders.toArray(), ", "));
		rb.header(ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
		rb.header(ACCESS_CONTROL_ALLOW_METHODS, StringUtils.join(allowedHttpMethods.toArray(), ", "));
		if (method.equals(OPTIONS)) {
			rb.header(ACCESS_CONTROL_REQUEST_METHOD, StringUtils.join(requestHttpMethods.toArray(), ", "));
			rb.header(ACCESS_CONTROL_REQUEST_HEADERS, ACCESS_CONTROL_ALLOW_ORIGIN);
			rb.header(CONTENT_TYPE, MediaType.APPLICATION_JSON);
		}
		rb.header(ACCESS_CONTROL_MAX_AGE, "1209600");

		return rb.entity(obj).build();
	}

	private static <T> List<T> castList(Object obj, Class<T> clazz) {
		List<T> result = new ArrayList<T>();
		if (obj instanceof List<?>) {
			for (Object o : (List<?>)obj) {
				result.add(clazz.cast(o));
			}
			return result;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private int updateIfModified(String key, String value, List<Bson> sets, int changes, Map<Integer, Map<String, Object>> original_values_map, Map<Integer, Map<String, Object>> toupdate_values_map, Map<String, Object> map) {
		logger.error("[01;34m" + key + ": " + ((Map<String, Object>)toupdate_values_map.get(map.get("code")).get(key)).get("value") + "[00;00m");

		if (((Map<String, Object>)toupdate_values_map.get(map.get("code")).get(key)).get("value") != null && !((Map<String, Object>)toupdate_values_map.get(map.get("code")).get(key)).get("value").equals(((Map<String, Object>)original_values_map.get(map.get("code")).get(key)).get("value"))) {
			sets.add(set("data.$." + key + ".value", ((Map<String, Object>)map.get(key)).get("value")));
			if (!value.isEmpty()) {
				sets.add(currentDate("data.$." + value));
			}
			++changes;
			logger.error("[01;31mvalue changed: " + ((Map<String, Object>)map.get(key)).get("value") + "[00;00m");
		}

		if (((Map<String, Object>)toupdate_values_map.get(map.get("code")).get(key)).get("modified") != null && !((Map<String, Object>)toupdate_values_map.get(map.get("code")).get(key)).get("modified").equals(((Map<String, Object>)original_values_map.get(map.get("code")).get(key)).get("modified"))) {
			sets.add(set("data.$." + key + ".modified", ((Map<String, Object>)map.get(key)).get("modified")));
		}

		return changes;
	}
}
