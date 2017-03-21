package ca.gc.ip346.classification.model;

import javax.ws.rs.QueryParam;

public class CfgFilter {
	@QueryParam("data-source")                          private String dataSource;
	@QueryParam("food-recipe-name")                     private String foodRecipeName;
	@QueryParam("food-recipe-code")                     private String foodRecipeCode;
	@QueryParam("commit-date-from")                     private String commitDateFrom;
	@QueryParam("commit-date-to")                       private String commitDateTo;
	@QueryParam("cnf-code")                             private String cnfCode;
	@QueryParam("subgroup-code")                        private String subgroupCode;
	@QueryParam("cfg-tier")                             private /* CfgTier */      Integer cfgTier;
	@QueryParam("recipe")                               private /* RecipeRolled */ Integer recipe;
	@QueryParam("sodium")                               private /* Added */        Integer sodium;
	@QueryParam("sugar")                                private /* Added */        Integer sugar;
	@QueryParam("fat")                                  private /* Added */        Integer fat;
	@QueryParam("transfat")                             private /* Added */        Integer transfat;
	@QueryParam("caffeine")                             private /* Added */        Integer caffeine;
	@QueryParam("free-sugars")                          private /* Added */        Integer freeSugars;
	@QueryParam("sugar-substitutes")                    private /* Added */        Integer sugarSubstitutes;
	@QueryParam("reference-amount-missing")             private String referenceAmountMissing;
	@QueryParam("cfg-serving-missing")                  private String cfgServingMissing;
	@QueryParam("tier-4-serving-missing")               private String tier4ServingMissing;
	@QueryParam("energy-value-missing")                 private String energyValueMissing;
	@QueryParam("cnf-code-missing")                     private String cnfCodeMissing;
	@QueryParam("recipe-rolled-up-down-missing")        private String recipeRolledUpDownMissing;
	@QueryParam("sodium-value-missing")                 private String sodiumValueMissing;
	@QueryParam("sugar-value-missing")                  private String sugarValueMissing;
	@QueryParam("fat-value-missing")                    private String fatValueMissing;
	@QueryParam("transfat-value-missing")               private String transfatValueMissing;
	@QueryParam("satfat-value-missing")                 private String satfatValueMissing;
	@QueryParam("added-sodium-missing")                 private String addedSodiumMissing;
	@QueryParam("added-sugar-missing")                  private String addedSugarMissing;
	@QueryParam("added-fat-missing")                    private String addedFatMissing;
	@QueryParam("added-transfat-missing")               private String addedTransfatMissing;
	@QueryParam("added-caffeine-missing")               private String addedCaffeineMissing;
	@QueryParam("added-free-sugars-missing")            private String addedFreeSugarsMissing;
	@QueryParam("added-sugar-substitutes-missing")      private String addedSugarSubstitutesMissing;
	@QueryParam("comments")                             private String comments;
	@QueryParam("last-update-date-From")                private String lastUpdateDateFrom;
	@QueryParam("last-update-date-To")                  private String lastUpdateDateTo;
	@QueryParam("reference-amount-last-updated")        private String referenceAmountLastUpdated;
	@QueryParam("cfg-serving-last-updated")             private String cfgServingLastUpdated;
	@QueryParam("tier-4-serving-last-updated")          private String tier4ServingLastUpdated;
	@QueryParam("energy-value-last-updated")            private String energyValueLastUpdated;
	@QueryParam("cnf-code-last-updated")                private String cnfCodeLastUpdated;
	@QueryParam("recipe-rolled-up-down-last-updated")   private String recipeRolledUpDownLastUpdated;
	@QueryParam("sodium-value-last-updated")            private String sodiumValueLastUpdated;
	@QueryParam("sugar-value-last-updated")             private String sugarValueLastUpdated;
	@QueryParam("fat-value-last-updated")               private String fatValueLastUpdated;
	@QueryParam("transfat-value-last-updated")          private String transfatValueLastUpdated;
	@QueryParam("satfat-value-last-updated")            private String satfatValueLastUpdated;
	@QueryParam("added-sodium-last-updated")            private String addedSodiumLastUpdated;
	@QueryParam("added-sugar-last-updated")             private String addedSugarLastUpdated;
	@QueryParam("added-transfat-last-updated")          private String addedTransfatLastUpdated;
	@QueryParam("added-caffeine-last-updated")          private String addedCaffeineLastUpdated;
	@QueryParam("added-free-sugars-last-updated")       private String addedFreeSugarsLastUpdated;
	@QueryParam("added-sugar-substitutes-last-updated") private String addedSugarSubstitutesLastUpdated;

	private String sql;

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
	 * @return the commitDateFrom
	 */
	public String getCommitDateFrom() {
		return commitDateFrom;
	}

	/**
	 * @param commitDateFrom the commitDateFrom to set
	 */
	public void setCommitDateFrom(String commitDateFrom) {
		this.commitDateFrom = commitDateFrom;
	}

	/**
	 * @return the commitDateTo
	 */
	public String getCommitDateTo() {
		return commitDateTo;
	}

	/**
	 * @param commitDateTo the commitDateTo to set
	 */
	public void setCommitDateTo(String commitDateTo) {
		this.commitDateTo = commitDateTo;
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
	public /* CfgTier */ Integer getCfgTier() {
		return cfgTier;
	}

	/**
	 * @param cfgTier the cfgTier to set
	 */
	public void setCfgTier(/* CfgTier */ Integer cfgTier) {
		this.cfgTier = cfgTier;
	}

	/**
	 * @return the recipe
	 */
	public /* RecipeRolled */ Integer getRecipe() {
		return recipe;
	}

	/**
	 * @param recipe the recipe to set
	 */
	public void setRecipe(/* RecipeRolled */ Integer recipe) {
		this.recipe = recipe;
	}

	/**
	 * @return the sodium
	 */
	public /* Added */ Integer getSodium() {
		return sodium;
	}

	/**
	 * @param sodium the sodium to set
	 */
	public void setSodium(/* Added */ Integer sodium) {
		this.sodium = sodium;
	}

	/**
	 * @return the sugar
	 */
	public /* Added */ Integer getSugar() {
		return sugar;
	}

	/**
	 * @param sugar the sugar to set
	 */
	public void setSugar(/* Added */ Integer sugar) {
		this.sugar = sugar;
	}

	/**
	 * @return the fat
	 */
	public /* Added */ Integer getFat() {
		return fat;
	}

	/**
	 * @param fat the fat to set
	 */
	public void setFat(/* Added */ Integer fat) {
		this.fat = fat;
	}

	/**
	 * @return the transfat
	 */
	public /* Added */ Integer getTransfat() {
		return transfat;
	}

	/**
	 * @param transfat the transfat to set
	 */
	public void setTransfat(/* Added */ Integer transfat) {
		this.transfat = transfat;
	}

	/**
	 * @return the caffeine
	 */
	public /* Added */ Integer getCaffeine() {
		return caffeine;
	}

	/**
	 * @param caffeine the caffeine to set
	 */
	public void setCaffeine(/* Added */ Integer caffeine) {
		this.caffeine = caffeine;
	}

	/**
	 * @return the freeSugars
	 */
	public /* Added */ Integer getFreeSugars() {
		return freeSugars;
	}

	/**
	 * @param freeSugars the freeSugars to set
	 */
	public void setFreeSugars(/* Added */ Integer freeSugars) {
		this.freeSugars = freeSugars;
	}

	/**
	 * @return the sugarSubstitutes
	 */
	public /* Added */ Integer getSugarSubstitutes() {
		return sugarSubstitutes;
	}

	/**
	 * @param sugarSubstitutes the sugarSubstitutes to set
	 */
	public void setSugarSubstitutes(/* Added */ Integer sugarSubstitutes) {
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
	 * @return the addedFatMissing
	 */
	public String getAddedFatMissing() {
		return addedFatMissing;
	}

	/**
	 * @param addedFatMissing the addedFatMissing to set
	 */
	public void setAddedFatMissing(String addedFatMissing) {
		this.addedFatMissing = addedFatMissing;
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

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return the lastUpdateDateFrom
	 */
	public String getLastUpdateDateFrom() {
		return lastUpdateDateFrom;
	}

	/**
	 * @param lastUpdateDateFrom the lastUpdateDateFrom to set
	 */
	public void setLastUpdateDateFrom(String lastUpdateDateFrom) {
		this.lastUpdateDateFrom = lastUpdateDateFrom;
	}

	/**
	 * @return the lastUpdateDateTo
	 */
	public String getLastUpdateDateTo() {
		return lastUpdateDateTo;
	}

	/**
	 * @param lastUpdateDateTo the lastUpdateDateTo to set
	 */
	public void setLastUpdateDateTo(String lastUpdateDateTo) {
		this.lastUpdateDateTo = lastUpdateDateTo;
	}

	/**
	 * @return the referenceAmountLastUpdated
	 */
	public String getReferenceAmountLastUpdated() {
		return referenceAmountLastUpdated;
	}

	/**
	 * @param referenceAmountLastUpdated the referenceAmountLastUpdated to set
	 */
	public void setReferenceAmountLastUpdated(String referenceAmountLastUpdated) {
		this.referenceAmountLastUpdated = referenceAmountLastUpdated;
	}

	/**
	 * @return the cfgServingLastUpdated
	 */
	public String getCfgServingLastUpdated() {
		return cfgServingLastUpdated;
	}

	/**
	 * @param cfgServingLastUpdated the cfgServingLastUpdated to set
	 */
	public void setCfgServingLastUpdated(String cfgServingLastUpdated) {
		this.cfgServingLastUpdated = cfgServingLastUpdated;
	}

	/**
	 * @return the tier4ServingLastUpdated
	 */
	public String getTier4ServingLastUpdated() {
		return tier4ServingLastUpdated;
	}

	/**
	 * @param tier4ServingLastUpdated the tier4ServingLastUpdated to set
	 */
	public void setTier4ServingLastUpdated(String tier4ServingLastUpdated) {
		this.tier4ServingLastUpdated = tier4ServingLastUpdated;
	}

	/**
	 * @return the energyValueLastUpdated
	 */
	public String getEnergyValueLastUpdated() {
		return energyValueLastUpdated;
	}

	/**
	 * @param energyValueLastUpdated the energyValueLastUpdated to set
	 */
	public void setEnergyValueLastUpdated(String energyValueLastUpdated) {
		this.energyValueLastUpdated = energyValueLastUpdated;
	}

	/**
	 * @return the cnfCodeLastUpdated
	 */
	public String getCnfCodeLastUpdated() {
		return cnfCodeLastUpdated;
	}

	/**
	 * @param cnfCodeLastUpdated the cnfCodeLastUpdated to set
	 */
	public void setCnfCodeLastUpdated(String cnfCodeLastUpdated) {
		this.cnfCodeLastUpdated = cnfCodeLastUpdated;
	}

	/**
	 * @return the recipeRolledUpDownLastUpdated
	 */
	public String getRecipeRolledUpDownLastUpdated() {
		return recipeRolledUpDownLastUpdated;
	}

	/**
	 * @param recipeRolledUpDownLastUpdated the recipeRolledUpDownLastUpdated to set
	 */
	public void setRecipeRolledUpDownLastUpdated(String recipeRolledUpDownLastUpdated) {
		this.recipeRolledUpDownLastUpdated = recipeRolledUpDownLastUpdated;
	}

	/**
	 * @return the sodiumValueLastUpdated
	 */
	public String getSodiumValueLastUpdated() {
		return sodiumValueLastUpdated;
	}

	/**
	 * @param sodiumValueLastUpdated the sodiumValueLastUpdated to set
	 */
	public void setSodiumValueLastUpdated(String sodiumValueLastUpdated) {
		this.sodiumValueLastUpdated = sodiumValueLastUpdated;
	}

	/**
	 * @return the sugarValueLastUpdated
	 */
	public String getSugarValueLastUpdated() {
		return sugarValueLastUpdated;
	}

	/**
	 * @param sugarValueLastUpdated the sugarValueLastUpdated to set
	 */
	public void setSugarValueLastUpdated(String sugarValueLastUpdated) {
		this.sugarValueLastUpdated = sugarValueLastUpdated;
	}

	/**
	 * @return the fatValueLastUpdated
	 */
	public String getFatValueLastUpdated() {
		return fatValueLastUpdated;
	}

	/**
	 * @param fatValueLastUpdated the fatValueLastUpdated to set
	 */
	public void setFatValueLastUpdated(String fatValueLastUpdated) {
		this.fatValueLastUpdated = fatValueLastUpdated;
	}

	/**
	 * @return the transfatValueLastUpdated
	 */
	public String getTransfatValueLastUpdated() {
		return transfatValueLastUpdated;
	}

	/**
	 * @param transfatValueLastUpdated the transfatValueLastUpdated to set
	 */
	public void setTransfatValueLastUpdated(String transfatValueLastUpdated) {
		this.transfatValueLastUpdated = transfatValueLastUpdated;
	}

	/**
	 * @return the satfatValueLastUpdated
	 */
	public String getSatfatValueLastUpdated() {
		return satfatValueLastUpdated;
	}

	/**
	 * @param satfatValueLastUpdated the satfatValueLastUpdated to set
	 */
	public void setSatfatValueLastUpdated(String satfatValueLastUpdated) {
		this.satfatValueLastUpdated = satfatValueLastUpdated;
	}

	/**
	 * @return the addedSodiumLastUpdated
	 */
	public String getAddedSodiumLastUpdated() {
		return addedSodiumLastUpdated;
	}

	/**
	 * @param addedSodiumLastUpdated the addedSodiumLastUpdated to set
	 */
	public void setAddedSodiumLastUpdated(String addedSodiumLastUpdated) {
		this.addedSodiumLastUpdated = addedSodiumLastUpdated;
	}

	/**
	 * @return the addedSugarLastUpdated
	 */
	public String getAddedSugarLastUpdated() {
		return addedSugarLastUpdated;
	}

	/**
	 * @param addedSugarLastUpdated the addedSugarLastUpdated to set
	 */
	public void setAddedSugarLastUpdated(String addedSugarLastUpdated) {
		this.addedSugarLastUpdated = addedSugarLastUpdated;
	}

	/**
	 * @return the addedTransfatLastUpdated
	 */
	public String getAddedTransfatLastUpdated() {
		return addedTransfatLastUpdated;
	}

	/**
	 * @param addedTransfatLastUpdated the addedTransfatLastUpdated to set
	 */
	public void setAddedTransfatLastUpdated(String addedTransfatLastUpdated) {
		this.addedTransfatLastUpdated = addedTransfatLastUpdated;
	}

	/**
	 * @return the addedCaffeineLastUpdated
	 */
	public String getAddedCaffeineLastUpdated() {
		return addedCaffeineLastUpdated;
	}

	/**
	 * @param addedCaffeineLastUpdated the addedCaffeineLastUpdated to set
	 */
	public void setAddedCaffeineLastUpdated(String addedCaffeineLastUpdated) {
		this.addedCaffeineLastUpdated = addedCaffeineLastUpdated;
	}

	/**
	 * @return the addedFreeSugarsLastUpdated
	 */
	public String getAddedFreeSugarsLastUpdated() {
		return addedFreeSugarsLastUpdated;
	}

	/**
	 * @param addedFreeSugarsLastUpdated the addedFreeSugarsLastUpdated to set
	 */
	public void setAddedFreeSugarsLastUpdated(String addedFreeSugarsLastUpdated) {
		this.addedFreeSugarsLastUpdated = addedFreeSugarsLastUpdated;
	}

	/**
	 * @return the addedSugarSubstitutesLastUpdated
	 */
	public String getAddedSugarSubstitutesLastUpdated() {
		return addedSugarSubstitutesLastUpdated;
	}

	/**
	 * @param addedSugarSubstitutesLastUpdated the addedSugarSubstitutesLastUpdated to set
	 */
	public void setAddedSugarSubstitutesLastUpdated(String addedSugarSubstitutesLastUpdated) {
		this.addedSugarSubstitutesLastUpdated = addedSugarSubstitutesLastUpdated;
	}

	/**
	 * @return the sql
	 */
	public String getSql() {
		return sql;
	}

	/**
	 * @param sql the sql to set
	 */
	public void setSql(String sql) {
		this.sql = sql;
	}
}
