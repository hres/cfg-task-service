package ca.gc.ip346.classification.model;

import java.util.Date;

public class CanadaFoodGuideFoodItem {
	private /* text             */ String type;
	private /* integer          */ String code;
	private /* text             */ String name;
	private /* integer          */ String cnfCode;
	private /* integer          */ String cfgCode;
	private /* date             */ Date cfgCodeCommitDate;
	private /* double precision */ Double energyKcal;
	private /* double precision */ Double sodiumAmountPer100g;
	private /* text             */ String sodiumImputationReference;
	private /* date             */ Date sodiumImputationDate;
	private /* double precision */ Double sugarAmountPer100g;
	private /* text             */ String sugarImputationReference;
	private /* date             */ Date sugarImputationDate;
	private /* double precision */ Double transfatAmountPer100g;
	private /* text             */ String transfatImputationReference;
	private /* date             */ Date transfatImputationDate;
	private /* double precision */ Double satfatAmoutPer100g;
	private /* text             */ String satfatImputationReference;
	private /* date             */ Date satfatImputationDate;
	private /* double precision */ Double totalfatAmoutPer100g;
	private /* text             */ String totalfatImputationReference;
	private /* date             */ Date totalfatImputationDate;
	private /* smallint         */ Boolean containsAddedSodium;
	private /* date             */ Date containsAddedSodiumCommitDate;
	private /* smallint         */ Boolean containsAddedSugar;
	private /* date             */ Date containsAddedSugarCommitDate;
	private /* smallint         */ Boolean containsFreeSugars;
	private /* date             */ Date containsFreeSugarsCommitDate;
	private /* smallint         */ Boolean containsAddedFat;
	private /* date             */ Date containsAddedFatCommitDate;
	private /* smallint         */ Boolean containsAddedTransfat;
	private /* date             */ Date containsAddedTransfatCommitDate;
	private /* smallint         */ Boolean containsCaffeine;
	private /* date             */ Date containsCaffeineCommitDate;
	private /* smallint         */ Boolean containsSugarSubstitutes;
	private /* date             */ Date containsSugarSubstitutesCommitDate;
	private /* double precision */ Double referenceAmountG;
	private /* text             */ String referenceAmountMeasure;
	private /* date             */ Date referenceAmountCommitDate;
	private /* double precision */ Double foodGuideServingG;
	private /* text             */ String foodGuideServingMeasure;
	private /* date             */ Date foodGuideCommitDate;
	private /* double precision */ Double tier4ServingG;
	private /* text             */ String tier4ServingMeasure;
	private /* date             */ Date tier4ServingCommitDate;
	private /* smallint         */ Boolean rolledUp;
	private /* date             */ Date rolledUpCommitDate;
	private /* text             */ Boolean applySmallRaAdjustment;
	private /* integer          */ Integer replacementCode;
	private /* text             */ String comments;

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the cfgCode
	 */
	public String getCfgCode() {
		return cfgCode;
	}

	/**
	 * @param cfgCode the cfgCode to set
	 */
	public void setCfgCode(String cfgCode) {
		this.cfgCode = cfgCode;
	}

	/**
	 * @return the cfgCodeCommitDate
	 */
	public Date getCfgCodeCommitDate() {
		return cfgCodeCommitDate;
	}

	/**
	 * @param cfgCodeCommitDate the cfgCodeCommitDate to set
	 */
	public void setCfgCodeCommitDate(Date cfgCodeCommitDate) {
		this.cfgCodeCommitDate = cfgCodeCommitDate;
	}

	/**
	 * @return the energyKcal
	 */
	public Double getEnergyKcal() {
		return energyKcal;
	}

	/**
	 * @param energyKcal the energyKcal to set
	 */
	public void setEnergyKcal(Double energyKcal) {
		this.energyKcal = energyKcal;
	}

	/**
	 * @return the sodiumAmountPer100g
	 */
	public Double getSodiumAmountPer100g() {
		return sodiumAmountPer100g;
	}

	/**
	 * @param sodiumAmountPer100g the sodiumAmountPer100g to set
	 */
	public void setSodiumAmountPer100g(Double sodiumAmountPer100g) {
		this.sodiumAmountPer100g = sodiumAmountPer100g;
	}

	/**
	 * @return the sodiumImputationReference
	 */
	public String getSodiumImputationReference() {
		return sodiumImputationReference;
	}

	/**
	 * @param sodiumImputationReference the sodiumImputationReference to set
	 */
	public void setSodiumImputationReference(String sodiumImputationReference) {
		this.sodiumImputationReference = sodiumImputationReference;
	}

	/**
	 * @return the sodiumImputationDate
	 */
	public Date getSodiumImputationDate() {
		return sodiumImputationDate;
	}

	/**
	 * @param sodiumImputationDate the sodiumImputationDate to set
	 */
	public void setSodiumImputationDate(Date sodiumImputationDate) {
		this.sodiumImputationDate = sodiumImputationDate;
	}

	/**
	 * @return the sugarAmountPer100g
	 */
	public Double getSugarAmountPer100g() {
		return sugarAmountPer100g;
	}

	/**
	 * @param sugarAmountPer100g the sugarAmountPer100g to set
	 */
	public void setSugarAmountPer100g(Double sugarAmountPer100g) {
		this.sugarAmountPer100g = sugarAmountPer100g;
	}

	/**
	 * @return the sugarImputationReference
	 */
	public String getSugarImputationReference() {
		return sugarImputationReference;
	}

	/**
	 * @param sugarImputationReference the sugarImputationReference to set
	 */
	public void setSugarImputationReference(String sugarImputationReference) {
		this.sugarImputationReference = sugarImputationReference;
	}

	/**
	 * @return the sugarImputationDate
	 */
	public Date getSugarImputationDate() {
		return sugarImputationDate;
	}

	/**
	 * @param sugarImputationDate the sugarImputationDate to set
	 */
	public void setSugarImputationDate(Date sugarImputationDate) {
		this.sugarImputationDate = sugarImputationDate;
	}

	/**
	 * @return the transfatAmountPer100g
	 */
	public Double getTransfatAmountPer100g() {
		return transfatAmountPer100g;
	}

	/**
	 * @param transfatAmountPer100g the transfatAmountPer100g to set
	 */
	public void setTransfatAmountPer100g(Double transfatAmountPer100g) {
		this.transfatAmountPer100g = transfatAmountPer100g;
	}

	/**
	 * @return the transfatImputationReference
	 */
	public String getTransfatImputationReference() {
		return transfatImputationReference;
	}

	/**
	 * @param transfatImputationReference the transfatImputationReference to set
	 */
	public void setTransfatImputationReference(String transfatImputationReference) {
		this.transfatImputationReference = transfatImputationReference;
	}

	/**
	 * @return the transfatImputationDate
	 */
	public Date getTransfatImputationDate() {
		return transfatImputationDate;
	}

	/**
	 * @param transfatImputationDate the transfatImputationDate to set
	 */
	public void setTransfatImputationDate(Date transfatImputationDate) {
		this.transfatImputationDate = transfatImputationDate;
	}

	/**
	 * @return the satfatAmoutPer100g
	 */
	public Double getSatfatAmoutPer100g() {
		return satfatAmoutPer100g;
	}

	/**
	 * @param satfatAmoutPer100g the satfatAmoutPer100g to set
	 */
	public void setSatfatAmoutPer100g(Double satfatAmoutPer100g) {
		this.satfatAmoutPer100g = satfatAmoutPer100g;
	}

	/**
	 * @return the satfatImputationReference
	 */
	public String getSatfatImputationReference() {
		return satfatImputationReference;
	}

	/**
	 * @param satfatImputationReference the satfatImputationReference to set
	 */
	public void setSatfatImputationReference(String satfatImputationReference) {
		this.satfatImputationReference = satfatImputationReference;
	}

	/**
	 * @return the satfatImputationDate
	 */
	public Date getSatfatImputationDate() {
		return satfatImputationDate;
	}

	/**
	 * @param satfatImputationDate the satfatImputationDate to set
	 */
	public void setSatfatImputationDate(Date satfatImputationDate) {
		this.satfatImputationDate = satfatImputationDate;
	}

	/**
	 * @return the totalfatAmoutPer100g
	 */
	public Double getTotalfatAmoutPer100g() {
		return totalfatAmoutPer100g;
	}

	/**
	 * @param totalfatAmoutPer100g the totalfatAmoutPer100g to set
	 */
	public void setTotalfatAmoutPer100g(Double totalfatAmoutPer100g) {
		this.totalfatAmoutPer100g = totalfatAmoutPer100g;
	}

	/**
	 * @return the totalfatImputationReference
	 */
	public String getTotalfatImputationReference() {
		return totalfatImputationReference;
	}

	/**
	 * @param totalfatImputationReference the totalfatImputationReference to set
	 */
	public void setTotalfatImputationReference(String totalfatImputationReference) {
		this.totalfatImputationReference = totalfatImputationReference;
	}

	/**
	 * @return the totalfatImputationDate
	 */
	public Date getTotalfatImputationDate() {
		return totalfatImputationDate;
	}

	/**
	 * @param totalfatImputationDate the totalfatImputationDate to set
	 */
	public void setTotalfatImputationDate(Date totalfatImputationDate) {
		this.totalfatImputationDate = totalfatImputationDate;
	}

	/**
	 * @return the containsAddedSodium
	 */
	public Boolean getContainsAddedSodium() {
		return containsAddedSodium;
	}

	/**
	 * @param containsAddedSodium the containsAddedSodium to set
	 */
	public void setContainsAddedSodium(Boolean containsAddedSodium) {
		this.containsAddedSodium = containsAddedSodium;
	}

	/**
	 * @return the containsAddedSodiumCommitDate
	 */
	public Date getContainsAddedSodiumCommitDate() {
		return containsAddedSodiumCommitDate;
	}

	/**
	 * @param containsAddedSodiumCommitDate the containsAddedSodiumCommitDate to set
	 */
	public void setContainsAddedSodiumCommitDate(Date containsAddedSodiumCommitDate) {
		this.containsAddedSodiumCommitDate = containsAddedSodiumCommitDate;
	}

	/**
	 * @return the containsAddedSugar
	 */
	public Boolean getContainsAddedSugar() {
		return containsAddedSugar;
	}

	/**
	 * @param containsAddedSugar the containsAddedSugar to set
	 */
	public void setContainsAddedSugar(Boolean containsAddedSugar) {
		this.containsAddedSugar = containsAddedSugar;
	}

	/**
	 * @return the containsAddedSugarCommitDate
	 */
	public Date getContainsAddedSugarCommitDate() {
		return containsAddedSugarCommitDate;
	}

	/**
	 * @param containsAddedSugarCommitDate the containsAddedSugarCommitDate to set
	 */
	public void setContainsAddedSugarCommitDate(Date containsAddedSugarCommitDate) {
		this.containsAddedSugarCommitDate = containsAddedSugarCommitDate;
	}

	/**
	 * @return the containsFreeSugars
	 */
	public Boolean getContainsFreeSugars() {
		return containsFreeSugars;
	}

	/**
	 * @param containsFreeSugars the containsFreeSugars to set
	 */
	public void setContainsFreeSugars(Boolean containsFreeSugars) {
		this.containsFreeSugars = containsFreeSugars;
	}

	/**
	 * @return the containsFreeSugarsCommitDate
	 */
	public Date getContainsFreeSugarsCommitDate() {
		return containsFreeSugarsCommitDate;
	}

	/**
	 * @param containsFreeSugarsCommitDate the containsFreeSugarsCommitDate to set
	 */
	public void setContainsFreeSugarsCommitDate(Date containsFreeSugarsCommitDate) {
		this.containsFreeSugarsCommitDate = containsFreeSugarsCommitDate;
	}

	/**
	 * @return the containsAddedFat
	 */
	public Boolean getContainsAddedFat() {
		return containsAddedFat;
	}

	/**
	 * @param containsAddedFat the containsAddedFat to set
	 */
	public void setContainsAddedFat(Boolean containsAddedFat) {
		this.containsAddedFat = containsAddedFat;
	}

	/**
	 * @return the containsAddedFatCommitDate
	 */
	public Date getContainsAddedFatCommitDate() {
		return containsAddedFatCommitDate;
	}

	/**
	 * @param containsAddedFatCommitDate the containsAddedFatCommitDate to set
	 */
	public void setContainsAddedFatCommitDate(Date containsAddedFatCommitDate) {
		this.containsAddedFatCommitDate = containsAddedFatCommitDate;
	}

	/**
	 * @return the containsAddedTransfat
	 */
	public Boolean getContainsAddedTransfat() {
		return containsAddedTransfat;
	}

	/**
	 * @param containsAddedTransfat the containsAddedTransfat to set
	 */
	public void setContainsAddedTransfat(Boolean containsAddedTransfat) {
		this.containsAddedTransfat = containsAddedTransfat;
	}

	/**
	 * @return the containsAddedTransfatCommitDate
	 */
	public Date getContainsAddedTransfatCommitDate() {
		return containsAddedTransfatCommitDate;
	}

	/**
	 * @param containsAddedTransfatCommitDate the containsAddedTransfatCommitDate to set
	 */
	public void setContainsAddedTransfatCommitDate(Date containsAddedTransfatCommitDate) {
		this.containsAddedTransfatCommitDate = containsAddedTransfatCommitDate;
	}

	/**
	 * @return the containsCaffeine
	 */
	public Boolean getContainsCaffeine() {
		return containsCaffeine;
	}

	/**
	 * @param containsCaffeine the containsCaffeine to set
	 */
	public void setContainsCaffeine(Boolean containsCaffeine) {
		this.containsCaffeine = containsCaffeine;
	}

	/**
	 * @return the containsCaffeineCommitDate
	 */
	public Date getContainsCaffeineCommitDate() {
		return containsCaffeineCommitDate;
	}

	/**
	 * @param containsCaffeineCommitDate the containsCaffeineCommitDate to set
	 */
	public void setContainsCaffeineCommitDate(Date containsCaffeineCommitDate) {
		this.containsCaffeineCommitDate = containsCaffeineCommitDate;
	}

	/**
	 * @return the containsSugarSubstitutes
	 */
	public Boolean getContainsSugarSubstitutes() {
		return containsSugarSubstitutes;
	}

	/**
	 * @param containsSugarSubstitutes the containsSugarSubstitutes to set
	 */
	public void setContainsSugarSubstitutes(Boolean containsSugarSubstitutes) {
		this.containsSugarSubstitutes = containsSugarSubstitutes;
	}

	/**
	 * @return the containsSugarSubstitutesCommitDate
	 */
	public Date getContainsSugarSubstitutesCommitDate() {
		return containsSugarSubstitutesCommitDate;
	}

	/**
	 * @param containsSugarSubstitutesCommitDate the containsSugarSubstitutesCommitDate to set
	 */
	public void setContainsSugarSubstitutesCommitDate(Date containsSugarSubstitutesCommitDate) {
		this.containsSugarSubstitutesCommitDate = containsSugarSubstitutesCommitDate;
	}

	/**
	 * @return the referenceAmountG
	 */
	public Double getReferenceAmountG() {
		return referenceAmountG;
	}

	/**
	 * @param referenceAmountG the referenceAmountG to set
	 */
	public void setReferenceAmountG(Double referenceAmountG) {
		this.referenceAmountG = referenceAmountG;
	}

	/**
	 * @return the referenceAmountMeasure
	 */
	public String getReferenceAmountMeasure() {
		return referenceAmountMeasure;
	}

	/**
	 * @param referenceAmountMeasure the referenceAmountMeasure to set
	 */
	public void setReferenceAmountMeasure(String referenceAmountMeasure) {
		this.referenceAmountMeasure = referenceAmountMeasure;
	}

	/**
	 * @return the referenceAmountCommitDate
	 */
	public Date getReferenceAmountCommitDate() {
		return referenceAmountCommitDate;
	}

	/**
	 * @param referenceAmountCommitDate the referenceAmountCommitDate to set
	 */
	public void setReferenceAmountCommitDate(Date referenceAmountCommitDate) {
		this.referenceAmountCommitDate = referenceAmountCommitDate;
	}

	/**
	 * @return the foodGuideServingG
	 */
	public Double getFoodGuideServingG() {
		return foodGuideServingG;
	}

	/**
	 * @param foodGuideServingG the foodGuideServingG to set
	 */
	public void setFoodGuideServingG(Double foodGuideServingG) {
		this.foodGuideServingG = foodGuideServingG;
	}

	/**
	 * @return the foodGuideServingMeasure
	 */
	public String getFoodGuideServingMeasure() {
		return foodGuideServingMeasure;
	}

	/**
	 * @param foodGuideServingMeasure the foodGuideServingMeasure to set
	 */
	public void setFoodGuideServingMeasure(String foodGuideServingMeasure) {
		this.foodGuideServingMeasure = foodGuideServingMeasure;
	}

	/**
	 * @return the foodGuideCommitDate
	 */
	public Date getFoodGuideCommitDate() {
		return foodGuideCommitDate;
	}

	/**
	 * @param foodGuideCommitDate the foodGuideCommitDate to set
	 */
	public void setFoodGuideCommitDate(Date foodGuideCommitDate) {
		this.foodGuideCommitDate = foodGuideCommitDate;
	}

	/**
	 * @return the tier4ServingG
	 */
	public Double getTier4ServingG() {
		return tier4ServingG;
	}

	/**
	 * @param tier4ServingG the tier4ServingG to set
	 */
	public void setTier4ServingG(Double tier4ServingG) {
		this.tier4ServingG = tier4ServingG;
	}

	/**
	 * @return the tier4ServingMeasure
	 */
	public String getTier4ServingMeasure() {
		return tier4ServingMeasure;
	}

	/**
	 * @param tier4ServingMeasure the tier4ServingMeasure to set
	 */
	public void setTier4ServingMeasure(String tier4ServingMeasure) {
		this.tier4ServingMeasure = tier4ServingMeasure;
	}

	/**
	 * @return the tier4ServingCommitDate
	 */
	public Date getTier4ServingCommitDate() {
		return tier4ServingCommitDate;
	}

	/**
	 * @param tier4ServingCommitDate the tier4ServingCommitDate to set
	 */
	public void setTier4ServingCommitDate(Date tier4ServingCommitDate) {
		this.tier4ServingCommitDate = tier4ServingCommitDate;
	}

	/**
	 * @return the rolledUp
	 */
	public Boolean getRolledUp() {
		return rolledUp;
	}

	/**
	 * @param rolledUp the rolledUp to set
	 */
	public void setRolledUp(Boolean rolledUp) {
		this.rolledUp = rolledUp;
	}

	/**
	 * @return the rolledUpCommitDate
	 */
	public Date getRolledUpCommitDate() {
		return rolledUpCommitDate;
	}

	/**
	 * @param rolledUpCommitDate the rolledUpCommitDate to set
	 */
	public void setRolledUpCommitDate(Date rolledUpCommitDate) {
		this.rolledUpCommitDate = rolledUpCommitDate;
	}

	/**
	 * @return the applySmallRaAdjustment
	 */
	public Boolean getApplySmallRaAdjustment() {
		return applySmallRaAdjustment;
	}

	/**
	 * @param applySmallRaAdjustment the applySmallRaAdjustment to set
	 */
	public void setApplySmallRaAdjustment(Boolean applySmallRaAdjustment) {
		this.applySmallRaAdjustment = applySmallRaAdjustment;
	}

	/**
	 * @return the replacementCode
	 */
	public Integer getReplacementCode() {
		return replacementCode;
	}

	/**
	 * @param replacementCode the replacementCode to set
	 */
	public void setReplacementCode(Integer replacementCode) {
		this.replacementCode = replacementCode;
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
}
