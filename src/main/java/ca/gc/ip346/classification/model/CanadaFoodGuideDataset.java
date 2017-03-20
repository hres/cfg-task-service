package ca.gc.ip346.classification.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.ALWAYS)
public class CanadaFoodGuideDataset {
	private Integer   type;                               /* smallint */
	private Integer   code;                               /* integer */
	private String    name;                               /* text */
	private Integer   cnfGroupCode;                       /* integer */
	private Integer   cfgCode;                            /* integer */
	private Date      cfgCodeUpdateDate;                  /* date */
	private Double    energyKcal;                         /* double precision */
	private Double    sodiumAmountPer100g;                /* double precision */
	private String    sodiumImputationReference;          /* text */
	private Date      sodiumImputationDate;               /* date */
	private Double    sugarAmountPer100g;                 /* double precision */
	private String    sugarImputationReference;           /* text */
	private Date      sugarImputationDate;                /* date */
	private Double    transfatAmountPer100g;              /* double precision */
	private String    transfatImputationReference;        /* text */
	private Date      transfatImputationDate;             /* date */
	private Double    satfatAmoutPer100g;                 /* double precision */
	private String    satfatImputationReference;          /* text */
	private Date      satfatImputationDate;               /* date */
	private Double    totalfatAmoutPer100g;               /* double precision */
	private String    totalfatImputationReference;        /* text */
	private Date      totalfatImputationDate;             /* date */
	private Integer   containsAddedSodium;                /* smallint */
	private Date      containsAddedSodiumUpdateDate;      /* date */
	private Integer   containsAddedSugar;                 /* smallint */
	private Date      containsAddedSugarUpdateDate;       /* date */
	private Integer   containsFreeSugars;                 /* smallint */
	private Date      containsFreeSugarsUpdateDate;       /* date */
	private Integer   containsAddedFat;                   /* smallint */
	private Date      containsAddedFatUpdateDate;         /* date */
	private Integer   containsAddedTransfat;              /* smallint */
	private Date      containsAddedTransfatUpdateDate;    /* date */
	private Integer   containsCaffeine;                   /* smallint */
	private Date      containsCaffeineUpdateDate;         /* date */
	private Integer   containsSugarSubstitutes;           /* smallint */
	private Date      containsSugarSubstitutesUpdateDate; /* date */
	private Double    referenceAmountG;                   /* double precision */
	private String    referenceAmountMeasure;             /* text */
	private Date      referenceAmountUpdateDate;          /* date */
	private Double    foodGuideServingG;                  /* double precision */
	private String    foodGuideServingMeasure;            /* text */
	private Date      foodGuideUpdateDate;                /* date */
	private Double    tier4ServingG;                      /* double precision */
	private String    tier4ServingMeasure;                /* text */
	private Date      tier4ServingUpdateDate;             /* date */
	private Integer   rolledUp;                           /* smallint */
	private Date      rolledUpUpdateDate;                 /* date */
	private Integer   applySmallRaAdjustment;             /* smallint */
	private Integer   replacementCode;                    /* integer */
	private Date      commitDate;                         /* date */
	private String comments; /* text */

	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(Integer code) {
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
	 * @return the cnfGroupCode
	 */
	public Integer getCnfGroupCode() {
		return cnfGroupCode;
	}

	/**
	 * @param cnfGroupCode the cnfGroupCode to set
	 */
	public void setCnfGroupCode(Integer cnfGroupCode) {
		this.cnfGroupCode = cnfGroupCode;
	}

	/**
	 * @return the cfgCode
	 */
	public Integer getCfgCode() {
		return cfgCode;
	}

	/**
	 * @param cfgCode the cfgCode to set
	 */
	public void setCfgCode(Integer cfgCode) {
		this.cfgCode = cfgCode;
	}

	/**
	 * @return the cfgCodeUpdateDate
	 */
	public Date getCfgCodeUpdateDate() {
		return cfgCodeUpdateDate;
	}

	/**
	 * @param cfgCodeUpdateDate the cfgCodeUpdateDate to set
	 */
	public void setCfgCodeUpdateDate(Date cfgCodeUpdateDate) {
		this.cfgCodeUpdateDate = cfgCodeUpdateDate;
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
	public Integer getContainsAddedSodium() {
		return containsAddedSodium;
	}

	/**
	 * @param containsAddedSodium the containsAddedSodium to set
	 */
	public void setContainsAddedSodium(Integer containsAddedSodium) {
		this.containsAddedSodium = containsAddedSodium;
	}

	/**
	 * @return the containsAddedSodiumUpdateDate
	 */
	public Date getContainsAddedSodiumUpdateDate() {
		return containsAddedSodiumUpdateDate;
	}

	/**
	 * @param containsAddedSodiumUpdateDate the containsAddedSodiumUpdateDate to set
	 */
	public void setContainsAddedSodiumUpdateDate(Date containsAddedSodiumUpdateDate) {
		this.containsAddedSodiumUpdateDate = containsAddedSodiumUpdateDate;
	}

	/**
	 * @return the containsAddedSugar
	 */
	public Integer getContainsAddedSugar() {
		return containsAddedSugar;
	}

	/**
	 * @param containsAddedSugar the containsAddedSugar to set
	 */
	public void setContainsAddedSugar(Integer containsAddedSugar) {
		this.containsAddedSugar = containsAddedSugar;
	}

	/**
	 * @return the containsAddedSugarUpdateDate
	 */
	public Date getContainsAddedSugarUpdateDate() {
		return containsAddedSugarUpdateDate;
	}

	/**
	 * @param containsAddedSugarUpdateDate the containsAddedSugarUpdateDate to set
	 */
	public void setContainsAddedSugarUpdateDate(Date containsAddedSugarUpdateDate) {
		this.containsAddedSugarUpdateDate = containsAddedSugarUpdateDate;
	}

	/**
	 * @return the containsFreeSugars
	 */
	public Integer getContainsFreeSugars() {
		return containsFreeSugars;
	}

	/**
	 * @param containsFreeSugars the containsFreeSugars to set
	 */
	public void setContainsFreeSugars(Integer containsFreeSugars) {
		this.containsFreeSugars = containsFreeSugars;
	}

	/**
	 * @return the containsFreeSugarsUpdateDate
	 */
	public Date getContainsFreeSugarsUpdateDate() {
		return containsFreeSugarsUpdateDate;
	}

	/**
	 * @param containsFreeSugarsUpdateDate the containsFreeSugarsUpdateDate to set
	 */
	public void setContainsFreeSugarsUpdateDate(Date containsFreeSugarsUpdateDate) {
		this.containsFreeSugarsUpdateDate = containsFreeSugarsUpdateDate;
	}

	/**
	 * @return the containsAddedFat
	 */
	public Integer getContainsAddedFat() {
		return containsAddedFat;
	}

	/**
	 * @param containsAddedFat the containsAddedFat to set
	 */
	public void setContainsAddedFat(Integer containsAddedFat) {
		this.containsAddedFat = containsAddedFat;
	}

	/**
	 * @return the containsAddedFatUpdateDate
	 */
	public Date getContainsAddedFatUpdateDate() {
		return containsAddedFatUpdateDate;
	}

	/**
	 * @param containsAddedFatUpdateDate the containsAddedFatUpdateDate to set
	 */
	public void setContainsAddedFatUpdateDate(Date containsAddedFatUpdateDate) {
		this.containsAddedFatUpdateDate = containsAddedFatUpdateDate;
	}

	/**
	 * @return the containsAddedTransfat
	 */
	public Integer getContainsAddedTransfat() {
		return containsAddedTransfat;
	}

	/**
	 * @param containsAddedTransfat the containsAddedTransfat to set
	 */
	public void setContainsAddedTransfat(Integer containsAddedTransfat) {
		this.containsAddedTransfat = containsAddedTransfat;
	}

	/**
	 * @return the containsAddedTransfatUpdateDate
	 */
	public Date getContainsAddedTransfatUpdateDate() {
		return containsAddedTransfatUpdateDate;
	}

	/**
	 * @param containsAddedTransfatUpdateDate the containsAddedTransfatUpdateDate to set
	 */
	public void setContainsAddedTransfatUpdateDate(Date containsAddedTransfatUpdateDate) {
		this.containsAddedTransfatUpdateDate = containsAddedTransfatUpdateDate;
	}

	/**
	 * @return the containsCaffeine
	 */
	public Integer getContainsCaffeine() {
		return containsCaffeine;
	}

	/**
	 * @param containsCaffeine the containsCaffeine to set
	 */
	public void setContainsCaffeine(Integer containsCaffeine) {
		this.containsCaffeine = containsCaffeine;
	}

	/**
	 * @return the containsCaffeineUpdateDate
	 */
	public Date getContainsCaffeineUpdateDate() {
		return containsCaffeineUpdateDate;
	}

	/**
	 * @param containsCaffeineUpdateDate the containsCaffeineUpdateDate to set
	 */
	public void setContainsCaffeineUpdateDate(Date containsCaffeineUpdateDate) {
		this.containsCaffeineUpdateDate = containsCaffeineUpdateDate;
	}

	/**
	 * @return the containsSugarSubstitutes
	 */
	public Integer getContainsSugarSubstitutes() {
		return containsSugarSubstitutes;
	}

	/**
	 * @param containsSugarSubstitutes the containsSugarSubstitutes to set
	 */
	public void setContainsSugarSubstitutes(Integer containsSugarSubstitutes) {
		this.containsSugarSubstitutes = containsSugarSubstitutes;
	}

	/**
	 * @return the containsSugarSubstitutesUpdateDate
	 */
	public Date getContainsSugarSubstitutesUpdateDate() {
		return containsSugarSubstitutesUpdateDate;
	}

	/**
	 * @param containsSugarSubstitutesUpdateDate the containsSugarSubstitutesUpdateDate to set
	 */
	public void setContainsSugarSubstitutesUpdateDate(Date containsSugarSubstitutesUpdateDate) {
		this.containsSugarSubstitutesUpdateDate = containsSugarSubstitutesUpdateDate;
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
	 * @return the referenceAmountUpdateDate
	 */
	public Date getReferenceAmountUpdateDate() {
		return referenceAmountUpdateDate;
	}

	/**
	 * @param referenceAmountUpdateDate the referenceAmountUpdateDate to set
	 */
	public void setReferenceAmountUpdateDate(Date referenceAmountUpdateDate) {
		this.referenceAmountUpdateDate = referenceAmountUpdateDate;
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
	 * @return the foodGuideUpdateDate
	 */
	public Date getFoodGuideUpdateDate() {
		return foodGuideUpdateDate;
	}

	/**
	 * @param foodGuideUpdateDate the foodGuideUpdateDate to set
	 */
	public void setFoodGuideUpdateDate(Date foodGuideUpdateDate) {
		this.foodGuideUpdateDate = foodGuideUpdateDate;
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
	 * @return the tier4ServingUpdateDate
	 */
	public Date getTier4ServingUpdateDate() {
		return tier4ServingUpdateDate;
	}

	/**
	 * @param tier4ServingUpdateDate the tier4ServingUpdateDate to set
	 */
	public void setTier4ServingUpdateDate(Date tier4ServingUpdateDate) {
		this.tier4ServingUpdateDate = tier4ServingUpdateDate;
	}

	/**
	 * @return the rolledUp
	 */
	public Integer getRolledUp() {
		return rolledUp;
	}

	/**
	 * @param rolledUp the rolledUp to set
	 */
	public void setRolledUp(Integer rolledUp) {
		this.rolledUp = rolledUp;
	}

	/**
	 * @return the rolledUpUpdateDate
	 */
	public Date getRolledUpUpdateDate() {
		return rolledUpUpdateDate;
	}

	/**
	 * @param rolledUpUpdateDate the rolledUpUpdateDate to set
	 */
	public void setRolledUpUpdateDate(Date rolledUpUpdateDate) {
		this.rolledUpUpdateDate = rolledUpUpdateDate;
	}

	/**
	 * @return the applySmallRaAdjustment
	 */
	public Integer getApplySmallRaAdjustment() {
		return applySmallRaAdjustment;
	}

	/**
	 * @param applySmallRaAdjustment the applySmallRaAdjustment to set
	 */
	public void setApplySmallRaAdjustment(Integer applySmallRaAdjustment) {
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
	 * @return the commitDate
	 */
	public Date getCommitDate() {
		return commitDate;
	}

	/**
	 * @param commitDate the commitDate to set
	 */
	public void setCommitDate(Date commitDate) {
		this.commitDate = commitDate;
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
