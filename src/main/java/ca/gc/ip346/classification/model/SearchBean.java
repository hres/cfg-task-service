package ca.gc.ip346.classification.model;

import javax.ws.rs.QueryParam;

public class SearchBean {
	@QueryParam("data-source")
	private String dataSource;
	@QueryParam("food-recipe-name")
	private String foodRecipeName;
	@QueryParam("food-recipe-code")
	private String foodRecipeCode;
	@QueryParam("commit-date")
	private String commitDate;
	@QueryParam("cnf-code")
	private String cnfCode;
	@QueryParam("subgroup-code")
	private String subgroupCode;
	@QueryParam("cfg-tier")
	private String cfgTier;
	@QueryParam("recipe")
	private String recipe;
	@QueryParam("sodium")
	private String sodium;
	@QueryParam("sugar")
	private String sugar;
	@QueryParam("fat")
	private String fat;
	@QueryParam("transfat")
	private String transfat;
	@QueryParam("caffeine")
	private String caffeine;
	@QueryParam("free-sugars")
	private String freeSugars;
	@QueryParam("sugar-substitutes")
	private String sugarSubstitutes;
	@QueryParam("reference-amount-missing")
	private String referenceAmountMissing;
	@QueryParam("cfg-serving-missing")
	private String cfgServingMissing;
	@QueryParam("tier-4-serving-missing")
	private String tier4ServingMissing;
	@QueryParam("energy-value-missing")
	private String energyValueMissing;
	@QueryParam("cnf-code-missing")
	private String cnfCodeMissing;
	@QueryParam("recipe-rolled-up-down-missing")
	private String recipeRolledUpDownMissing;
	@QueryParam("sodium-value-missing")
	private String sodiumValueMissing;
	@QueryParam("sugar-value-missing")
	private String sugarValueMissing;
	@QueryParam("fat-value-missing")
	private String fatValueMissing;
	@QueryParam("transfat-value-missing")
	private String transfatValueMissing;
	@QueryParam("satfat-value-missing")
	private String satfatValueMissing;
	@QueryParam("select-all-missing")
	private String selectAllMissing;
	@QueryParam("added-sodium-missing")
	private String addedSodiumMissing;
	@QueryParam("added-sugar-missing")
	private String addedSugarMissing;
	@QueryParam("added-transfat-missing")
	private String addedTransfatMissing;
	@QueryParam("added-caffeine-missing")
	private String addedCaffeineMissing;
	@QueryParam("added-free-sugars-missing")
	private String addedFreeSugarsMissing;
	@QueryParam("added-sugar-substitutes-missing")
	private String addedSugarSubstitutesMissing;

	/**
	 * @return the dataSource
	 */
	public String getDataSource() {
		return dataSource;
	}

	/**
	 * @param dataSource the dataSource to set
	 */
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * @return the foodRecipeName
	 */
	public String getFoodRecipeName() {
		return foodRecipeName;
	}

	/**
	 * @param foodRecipeName the foodRecipeName to set
	 */
	public void setFoodRecipeName(String foodRecipeName) {
		this.foodRecipeName = foodRecipeName;
	}

	/**
	 * @return the foodRecipeCode
	 */
	public String getFoodRecipeCode() {
		return foodRecipeCode;
	}

	/**
	 * @param foodRecipeCode the foodRecipeCode to set
	 */
	public void setFoodRecipeCode(String foodRecipeCode) {
		this.foodRecipeCode = foodRecipeCode;
	}

	/**
	 * @return the commitDate
	 */
	public String getCommitDate() {
		return commitDate;
	}

	/**
	 * @param commitDate the commitDate to set
	 */
	public void setCommitDate(String commitDate) {
		this.commitDate = commitDate;
	}

	/**
	 * @return the cnfCode
	 */
	public String getCnfCode() {
		return cnfCode;
	}

	/**
	 * @param cnfCode the cnfCode to set
	 */
	public void setCnfCode(String cnfCode) {
		this.cnfCode = cnfCode;
	}

	/**
	 * @return the subgroupCode
	 */
	public String getSubgroupCode() {
		return subgroupCode;
	}

	/**
	 * @param subgroupCode the subgroupCode to set
	 */
	public void setSubgroupCode(String subgroupCode) {
		this.subgroupCode = subgroupCode;
	}

	/**
	 * @return the cfgTier
	 */
	public String getCfgTier() {
		return cfgTier;
	}

	/**
	 * @param cfgTier the cfgTier to set
	 */
	public void setCfgTier(String cfgTier) {
		this.cfgTier = cfgTier;
	}

	/**
	 * @return the recipe
	 */
	public String getRecipe() {
		return recipe;
	}

	/**
	 * @param recipe the recipe to set
	 */
	public void setRecipe(String recipe) {
		this.recipe = recipe;
	}

	/**
	 * @return the sodium
	 */
	public String getSodium() {
		return sodium;
	}

	/**
	 * @param sodium the sodium to set
	 */
	public void setSodium(String sodium) {
		this.sodium = sodium;
	}

	/**
	 * @return the sugar
	 */
	public String getSugar() {
		return sugar;
	}

	/**
	 * @param sugar the sugar to set
	 */
	public void setSugar(String sugar) {
		this.sugar = sugar;
	}

	/**
	 * @return the fat
	 */
	public String getFat() {
		return fat;
	}

	/**
	 * @param fat the fat to set
	 */
	public void setFat(String fat) {
		this.fat = fat;
	}

	/**
	 * @return the transfat
	 */
	public String getTransfat() {
		return transfat;
	}

	/**
	 * @param transfat the transfat to set
	 */
	public void setTransfat(String transfat) {
		this.transfat = transfat;
	}

	/**
	 * @return the caffeine
	 */
	public String getCaffeine() {
		return caffeine;
	}

	/**
	 * @param caffeine the caffeine to set
	 */
	public void setCaffeine(String caffeine) {
		this.caffeine = caffeine;
	}

	/**
	 * @return the freeSugars
	 */
	public String getFreeSugars() {
		return freeSugars;
	}

	/**
	 * @param freeSugars the freeSugars to set
	 */
	public void setFreeSugars(String freeSugars) {
		this.freeSugars = freeSugars;
	}

	/**
	 * @return the sugarSubstitutes
	 */
	public String getSugarSubstitutes() {
		return sugarSubstitutes;
	}

	/**
	 * @param sugarSubstitutes the sugarSubstitutes to set
	 */
	public void setSugarSubstitutes(String sugarSubstitutes) {
		this.sugarSubstitutes = sugarSubstitutes;
	}

	/**
	 * @return the referenceAmountMissing
	 */
	public String getReferenceAmountMissing() {
		return referenceAmountMissing;
	}

	/**
	 * @param referenceAmountMissing the referenceAmountMissing to set
	 */
	public void setReferenceAmountMissing(String referenceAmountMissing) {
		this.referenceAmountMissing = referenceAmountMissing;
	}

	/**
	 * @return the cfgServingMissing
	 */
	public String getCfgServingMissing() {
		return cfgServingMissing;
	}

	/**
	 * @param cfgServingMissing the cfgServingMissing to set
	 */
	public void setCfgServingMissing(String cfgServingMissing) {
		this.cfgServingMissing = cfgServingMissing;
	}

	/**
	 * @return the tier4ServingMissing
	 */
	public String getTier4ServingMissing() {
		return tier4ServingMissing;
	}

	/**
	 * @param tier4ServingMissing the tier4ServingMissing to set
	 */
	public void setTier4ServingMissing(String tier4ServingMissing) {
		this.tier4ServingMissing = tier4ServingMissing;
	}

	/**
	 * @return the energyValueMissing
	 */
	public String getEnergyValueMissing() {
		return energyValueMissing;
	}

	/**
	 * @param energyValueMissing the energyValueMissing to set
	 */
	public void setEnergyValueMissing(String energyValueMissing) {
		this.energyValueMissing = energyValueMissing;
	}

	/**
	 * @return the cnfCodeMissing
	 */
	public String getCnfCodeMissing() {
		return cnfCodeMissing;
	}

	/**
	 * @param cnfCodeMissing the cnfCodeMissing to set
	 */
	public void setCnfCodeMissing(String cnfCodeMissing) {
		this.cnfCodeMissing = cnfCodeMissing;
	}

	/**
	 * @return the recipeRolledUpDownMissing
	 */
	public String getRecipeRolledUpDownMissing() {
		return recipeRolledUpDownMissing;
	}

	/**
	 * @param recipeRolledUpDownMissing the recipeRolledUpDownMissing to set
	 */
	public void setRecipeRolledUpDownMissing(String recipeRolledUpDownMissing) {
		this.recipeRolledUpDownMissing = recipeRolledUpDownMissing;
	}

	/**
	 * @return the sodiumValueMissing
	 */
	public String getSodiumValueMissing() {
		return sodiumValueMissing;
	}

	/**
	 * @param sodiumValueMissing the sodiumValueMissing to set
	 */
	public void setSodiumValueMissing(String sodiumValueMissing) {
		this.sodiumValueMissing = sodiumValueMissing;
	}

	/**
	 * @return the sugarValueMissing
	 */
	public String getSugarValueMissing() {
		return sugarValueMissing;
	}

	/**
	 * @param sugarValueMissing the sugarValueMissing to set
	 */
	public void setSugarValueMissing(String sugarValueMissing) {
		this.sugarValueMissing = sugarValueMissing;
	}

	/**
	 * @return the fatValueMissing
	 */
	public String getFatValueMissing() {
		return fatValueMissing;
	}

	/**
	 * @param fatValueMissing the fatValueMissing to set
	 */
	public void setFatValueMissing(String fatValueMissing) {
		this.fatValueMissing = fatValueMissing;
	}

	/**
	 * @return the transfatValueMissing
	 */
	public String getTransfatValueMissing() {
		return transfatValueMissing;
	}

	/**
	 * @param transfatValueMissing the transfatValueMissing to set
	 */
	public void setTransfatValueMissing(String transfatValueMissing) {
		this.transfatValueMissing = transfatValueMissing;
	}

	/**
	 * @return the satfatValueMissing
	 */
	public String getSatfatValueMissing() {
		return satfatValueMissing;
	}

	/**
	 * @param satfatValueMissing the satfatValueMissing to set
	 */
	public void setSatfatValueMissing(String satfatValueMissing) {
		this.satfatValueMissing = satfatValueMissing;
	}

	/**
	 * @return the selectAllMissing
	 */
	public String getSelectAllMissing() {
		return selectAllMissing;
	}

	/**
	 * @param selectAllMissing the selectAllMissing to set
	 */
	public void setSelectAllMissing(String selectAllMissing) {
		this.selectAllMissing = selectAllMissing;
	}

	/**
	 * @return the addedSodiumMissing
	 */
	public String getAddedSodiumMissing() {
		return addedSodiumMissing;
	}

	/**
	 * @param addedSodiumMissing the addedSodiumMissing to set
	 */
	public void setAddedSodiumMissing(String addedSodiumMissing) {
		this.addedSodiumMissing = addedSodiumMissing;
	}

	/**
	 * @return the addedSugarMissing
	 */
	public String getAddedSugarMissing() {
		return addedSugarMissing;
	}

	/**
	 * @param addedSugarMissing the addedSugarMissing to set
	 */
	public void setAddedSugarMissing(String addedSugarMissing) {
		this.addedSugarMissing = addedSugarMissing;
	}

	/**
	 * @return the addedTransfatMissing
	 */
	public String getAddedTransfatMissing() {
		return addedTransfatMissing;
	}

	/**
	 * @param addedTransfatMissing the addedTransfatMissing to set
	 */
	public void setAddedTransfatMissing(String addedTransfatMissing) {
		this.addedTransfatMissing = addedTransfatMissing;
	}

	/**
	 * @return the addedCaffeineMissing
	 */
	public String getAddedCaffeineMissing() {
		return addedCaffeineMissing;
	}

	/**
	 * @param addedCaffeineMissing the addedCaffeineMissing to set
	 */
	public void setAddedCaffeineMissing(String addedCaffeineMissing) {
		this.addedCaffeineMissing = addedCaffeineMissing;
	}

	/**
	 * @return the addedFreeSugarsMissing
	 */
	public String getAddedFreeSugarsMissing() {
		return addedFreeSugarsMissing;
	}

	/**
	 * @param addedFreeSugarsMissing the addedFreeSugarsMissing to set
	 */
	public void setAddedFreeSugarsMissing(String addedFreeSugarsMissing) {
		this.addedFreeSugarsMissing = addedFreeSugarsMissing;
	}

	/**
	 * @return the addedSugarSubstitutesMissing
	 */
	public String getAddedSugarSubstitutesMissing() {
		return addedSugarSubstitutesMissing;
	}

	/**
	 * @param addedSugarSubstitutesMissing the addedSugarSubstitutesMissing to set
	 */
	public void setAddedSugarSubstitutesMissing(String addedSugarSubstitutesMissing) {
		this.addedSugarSubstitutesMissing = addedSugarSubstitutesMissing;
	}
}
