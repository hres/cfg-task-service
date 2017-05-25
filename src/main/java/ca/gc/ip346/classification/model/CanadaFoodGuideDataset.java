package ca.gc.ip346.classification.model;

import java.util.Date;

public class CanadaFoodGuideDataset {
	private String        type;                                                                    /* smallint         */
	private Integer       code;                                                                    /* integer          */
	private String        name;                                                                    /* text             */
	private Integer       cnfGroupCode;                                                            /* integer          */
	private PseudoInteger cfgCode;                                                                 /* integer          */ // editable
	private Integer       classifiedCfgCode;                                                       /* integer          */
	private Date          cfgCodeUpdateDate;                                                       /* date             */
	private Double        energyKcal; /* = 0.0; */                                                 /* double precision */
	private PseudoDouble  sodiumAmountPer100g;                                                     /* double precision */ // editable
	private PseudoString  sodiumImputationReference;                                               /* text             */
	private Date          sodiumImputationDate;                                                    /* date             */
	private PseudoDouble  sugarAmountPer100g;                                                      /* double precision */ // editable
	private PseudoString  sugarImputationReference;                                                /* text             */
	private Date          sugarImputationDate;                                                     /* date             */
	private PseudoDouble  transfatAmountPer100g;                                                   /* double precision */ // editable
	private PseudoString  transfatImputationReference;                                             /* text             */
	private Date          transfatImputationDate;                                                  /* date             */
	private PseudoDouble  satfatAmountPer100g;                                                     /* double precision */ // editable
	private PseudoString  satfatImputationReference;                                               /* text             */
	private Date          satfatImputationDate;                                                    /* date             */
	private PseudoDouble  totalfatAmountPer100g;                                                   /* double precision */ // editable
	private PseudoString  totalfatImputationReference;                                             /* text             */
	private Date          totalfatImputationDate;                                                  /* date             */
	private PseudoBoolean containsAddedSodium;                                                     /* smallint         */ // editable
	private Date          containsAddedSodiumUpdateDate;                                           /* date             */
	private PseudoBoolean containsAddedSugar;                                                      /* smallint         */ // editable
	private Date          containsAddedSugarUpdateDate;                                            /* date             */
	private PseudoBoolean containsFreeSugars;                                                      /* smallint         */ // editable
	private Date          containsFreeSugarsUpdateDate;                                            /* date             */
	private PseudoBoolean containsAddedFat;                                                        /* smallint         */ // editable
	private Date          containsAddedFatUpdateDate;                                              /* date             */
	private PseudoBoolean containsAddedTransfat;                                                   /* smallint         */ // editable
	private Date          containsAddedTransfatUpdateDate;                                         /* date             */
	private PseudoBoolean containsCaffeine;                                                        /* smallint         */ // editable
	private Date          containsCaffeineUpdateDate;                                              /* date             */
	private PseudoBoolean containsSugarSubstitutes;                                                /* smallint         */ // editable
	private Date          containsSugarSubstitutesUpdateDate;                                      /* date             */
	private Double        referenceAmountG = 0.0;                                                  /* double precision */
	private String        referenceAmountMeasure;                                                  /* text             */
	private Date          referenceAmountUpdateDate;                                               /* date             */
	private PseudoDouble  foodGuideServingG;                                                       /* double precision */ // editable
	private PseudoString  foodGuideServingMeasure;                                                 /* text             */ // editable
	private Date          foodGuideUpdateDate;                                                     /* date             */
	private PseudoDouble  tier4ServingG;                                                           /* double precision */ // editable
	private PseudoString  tier4ServingMeasure;                                                     /* text             */ // editable
	private Date          tier4ServingUpdateDate;                                                  /* date             */
	private PseudoBoolean rolledUp;                                                                /* smallint         */ // editable
	private Date          rolledUpUpdateDate;                                                      /* date             */
	private Boolean       overrideSmallRaAdjustment; /* = false; */                                /* smallint         */
	private PseudoInteger replacementCode;                                                         /* integer          */ // editable
	private Date          commitDate;                                                              /* date             */
	private PseudoString  comments;                                                                /* text             */ // editable

	// special input
	private Double  sodiumDV;
	private Double  sugarDV;
	private Double  satFatDV;

	// added by Robin
	private Boolean lowSodium;
	private Boolean highSodium;
	private Boolean lowSugar;
	private Boolean highSugar;
	private Boolean lowFat;
	private Boolean highFat;
	private Boolean lowSatFat;
	private Boolean highSatFat;
	private Boolean lowTransFat;
	private Boolean highTransFat;

	private Double      adjustedReferenceAmount;
	private Double   fopAdjustedReferenceAmount;
	private Double     sodiumPerReferenceAmount;
	private Double      sugarPerReferenceAmount;
	private Double        fatPerReferenceAmount;
	private Double     satFatPerReferenceAmount;
	private Double   transFatPerReferenceAmount;
	private Double   fopSugarPerReferenceAmount;
	private Double  fopSodiumPerReferenceAmount;

	// checks to see if it has finished classification
	private Boolean done;
	private Integer tier;
	private Integer shift;
	private Boolean absolute;

	private Boolean satFatFopWarning;
	private Boolean sugarFopWarning;
	private Boolean sodiumFopWarning;
	private Boolean marketedToKids = false;
	private Integer initialCfgCode;

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
	public PseudoInteger getCfgCode() {
		return cfgCode;
	}

	/**
	 * @param cfgCode the cfgCode to set
	 */
	public void setCfgCode(PseudoInteger cfgCode) {
		this.cfgCode = cfgCode;
	}

	/**
	 * @return the classifiedCfgCode
	 */
	public Integer getClassifiedCfgCode() {
		return classifiedCfgCode;
	}

	/**
	 * @param classifiedCfgCode the classifiedCfgCode to set
	 */
	public void setClassifiedCfgCode(Integer classifiedCfgCode) {
		this.classifiedCfgCode = classifiedCfgCode;
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
	public PseudoDouble getSodiumAmountPer100g() {
		return sodiumAmountPer100g;
	}

	/**
	 * @param sodiumAmountPer100g the sodiumAmountPer100g to set
	 */
	public void setSodiumAmountPer100g(PseudoDouble sodiumAmountPer100g) {
		this.sodiumAmountPer100g = sodiumAmountPer100g;
	}

	/**
	 * @return the sodiumImputationReference
	 */
	public PseudoString getSodiumImputationReference() {
		return sodiumImputationReference;
	}

	/**
	 * @param sodiumImputationReference the sodiumImputationReference to set
	 */
	public void setSodiumImputationReference(PseudoString sodiumImputationReference) {
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
	public PseudoDouble getSugarAmountPer100g() {
		return sugarAmountPer100g;
	}

	/**
	 * @param sugarAmountPer100g the sugarAmountPer100g to set
	 */
	public void setSugarAmountPer100g(PseudoDouble sugarAmountPer100g) {
		this.sugarAmountPer100g = sugarAmountPer100g;
	}

	/**
	 * @return the sugarImputationReference
	 */
	public PseudoString getSugarImputationReference() {
		return sugarImputationReference;
	}

	/**
	 * @param sugarImputationReference the sugarImputationReference to set
	 */
	public void setSugarImputationReference(PseudoString sugarImputationReference) {
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
	public PseudoDouble getTransfatAmountPer100g() {
		return transfatAmountPer100g;
	}

	/**
	 * @param transfatAmountPer100g the transfatAmountPer100g to set
	 */
	public void setTransfatAmountPer100g(PseudoDouble transfatAmountPer100g) {
		this.transfatAmountPer100g = transfatAmountPer100g;
	}

	/**
	 * @return the transfatImputationReference
	 */
	public PseudoString getTransfatImputationReference() {
		return transfatImputationReference;
	}

	/**
	 * @param transfatImputationReference the transfatImputationReference to set
	 */
	public void setTransfatImputationReference(PseudoString transfatImputationReference) {
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
	 * @return the satfatAmountPer100g
	 */
	public PseudoDouble getSatfatAmountPer100g() {
		return satfatAmountPer100g;
	}

	/**
	 * @param satfatAmountPer100g the satfatAmountPer100g to set
	 */
	public void setSatfatAmountPer100g(PseudoDouble satfatAmountPer100g) {
		this.satfatAmountPer100g = satfatAmountPer100g;
	}

	/**
	 * @return the satfatImputationReference
	 */
	public PseudoString getSatfatImputationReference() {
		return satfatImputationReference;
	}

	/**
	 * @param satfatImputationReference the satfatImputationReference to set
	 */
	public void setSatfatImputationReference(PseudoString satfatImputationReference) {
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
	 * @return the totalfatAmountPer100g
	 */
	public PseudoDouble getTotalfatAmountPer100g() {
		return totalfatAmountPer100g;
	}

	/**
	 * @param totalfatAmountPer100g the totalfatAmountPer100g to set
	 */
	public void setTotalfatAmountPer100g(PseudoDouble totalfatAmountPer100g) {
		this.totalfatAmountPer100g = totalfatAmountPer100g;
	}

	/**
	 * @return the totalfatImputationReference
	 */
	public PseudoString getTotalfatImputationReference() {
		return totalfatImputationReference;
	}

	/**
	 * @param totalfatImputationReference the totalfatImputationReference to set
	 */
	public void setTotalfatImputationReference(PseudoString totalfatImputationReference) {
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
	public PseudoBoolean getContainsAddedSodium() {
		return containsAddedSodium;
	}

	/**
	 * @param containsAddedSodium the containsAddedSodium to set
	 */
	public void setContainsAddedSodium(PseudoBoolean containsAddedSodium) {
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
	public PseudoBoolean getContainsAddedSugar() {
		return containsAddedSugar;
	}

	/**
	 * @param containsAddedSugar the containsAddedSugar to set
	 */
	public void setContainsAddedSugar(PseudoBoolean containsAddedSugar) {
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
	public PseudoBoolean getContainsFreeSugars() {
		return containsFreeSugars;
	}

	/**
	 * @param containsFreeSugars the containsFreeSugars to set
	 */
	public void setContainsFreeSugars(PseudoBoolean containsFreeSugars) {
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
	public PseudoBoolean getContainsAddedFat() {
		return containsAddedFat;
	}

	/**
	 * @param containsAddedFat the containsAddedFat to set
	 */
	public void setContainsAddedFat(PseudoBoolean containsAddedFat) {
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
	public PseudoBoolean getContainsAddedTransfat() {
		return containsAddedTransfat;
	}

	/**
	 * @param containsAddedTransfat the containsAddedTransfat to set
	 */
	public void setContainsAddedTransfat(PseudoBoolean containsAddedTransfat) {
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
	public PseudoBoolean getContainsCaffeine() {
		return containsCaffeine;
	}

	/**
	 * @param containsCaffeine the containsCaffeine to set
	 */
	public void setContainsCaffeine(PseudoBoolean containsCaffeine) {
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
	public PseudoBoolean getContainsSugarSubstitutes() {
		return containsSugarSubstitutes;
	}

	/**
	 * @param containsSugarSubstitutes the containsSugarSubstitutes to set
	 */
	public void setContainsSugarSubstitutes(PseudoBoolean containsSugarSubstitutes) {
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
	public PseudoDouble getFoodGuideServingG() {
		return foodGuideServingG;
	}

	/**
	 * @param foodGuideServingG the foodGuideServingG to set
	 */
	public void setFoodGuideServingG(PseudoDouble foodGuideServingG) {
		this.foodGuideServingG = foodGuideServingG;
	}

	/**
	 * @return the foodGuideServingMeasure
	 */
	public PseudoString getFoodGuideServingMeasure() {
		return foodGuideServingMeasure;
	}

	/**
	 * @param foodGuideServingMeasure the foodGuideServingMeasure to set
	 */
	public void setFoodGuideServingMeasure(PseudoString foodGuideServingMeasure) {
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
	public PseudoDouble getTier4ServingG() {
		return tier4ServingG;
	}

	/**
	 * @param tier4ServingG the tier4ServingG to set
	 */
	public void setTier4ServingG(PseudoDouble tier4ServingG) {
		this.tier4ServingG = tier4ServingG;
	}

	/**
	 * @return the tier4ServingMeasure
	 */
	public PseudoString getTier4ServingMeasure() {
		return tier4ServingMeasure;
	}

	/**
	 * @param tier4ServingMeasure the tier4ServingMeasure to set
	 */
	public void setTier4ServingMeasure(PseudoString tier4ServingMeasure) {
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
	public PseudoBoolean getRolledUp() {
		return rolledUp;
	}

	/**
	 * @param rolledUp the rolledUp to set
	 */
	public void setRolledUp(PseudoBoolean rolledUp) {
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
	 * @return the overrideSmallRaAdjustment
	 */
	public Boolean getOverrideSmallRaAdjustment() {
		return overrideSmallRaAdjustment;
	}

	/**
	 * @param overrideSmallRaAdjustment the overrideSmallRaAdjustment to set
	 */
	public void setOverrideSmallRaAdjustment(Boolean overrideSmallRaAdjustment) {
		this.overrideSmallRaAdjustment = overrideSmallRaAdjustment;
	}

	/**
	 * @return the replacementCode
	 */
	public PseudoInteger getReplacementCode() {
		return replacementCode;
	}

	/**
	 * @param replacementCode the replacementCode to set
	 */
	public void setReplacementCode(PseudoInteger replacementCode) {
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
	public PseudoString getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(PseudoString comments) {
		this.comments = comments;
	}

	/**
	 * @return the sodiumDV
	 */
	public Double getSodiumDV() {
		return sodiumDV;
	}

	/**
	 * @param sodiumDV the sodiumDV to set
	 */
	public void setSodiumDV(Double sodiumDV) {
		this.sodiumDV = sodiumDV;
	}

	/**
	 * @return the sugarDV
	 */
	public Double getSugarDV() {
		return sugarDV;
	}

	/**
	 * @param sugarDV the sugarDV to set
	 */
	public void setSugarDV(Double sugarDV) {
		this.sugarDV = sugarDV;
	}

	/**
	 * @return the satFatDV
	 */
	public Double getSatFatDV() {
		return satFatDV;
	}

	/**
	 * @param satFatDV the satFatDV to set
	 */
	public void setSatFatDV(Double satFatDV) {
		this.satFatDV = satFatDV;
	}

	/**
	 * @return the lowSodium
	 */
	public Boolean isLowSodium() {
		return lowSodium;
	}

	/**
	 * @param lowSodium the lowSodium to set
	 */
	public void setLowSodium(Boolean lowSodium) {
		this.lowSodium = lowSodium;
	}

	/**
	 * @return the highSodium
	 */
	public Boolean isHighSodium() {
		return highSodium;
	}

	/**
	 * @param highSodium the highSodium to set
	 */
	public void setHighSodium(Boolean highSodium) {
		this.highSodium = highSodium;
	}

	/**
	 * @return the lowSugar
	 */
	public Boolean isLowSugar() {
		return lowSugar;
	}

	/**
	 * @param lowSugar the lowSugar to set
	 */
	public void setLowSugar(Boolean lowSugar) {
		this.lowSugar = lowSugar;
	}

	/**
	 * @return the highSugar
	 */
	public Boolean isHighSugar() {
		return highSugar;
	}

	/**
	 * @param highSugar the highSugar to set
	 */
	public void setHighSugar(Boolean highSugar) {
		this.highSugar = highSugar;
	}

	/**
	 * @return the lowFat
	 */
	public Boolean isLowFat() {
		return lowFat;
	}

	/**
	 * @param lowFat the lowFat to set
	 */
	public void setLowFat(Boolean lowFat) {
		this.lowFat = lowFat;
	}

	/**
	 * @return the highFat
	 */
	public Boolean isHighFat() {
		return highFat;
	}

	/**
	 * @param highFat the highFat to set
	 */
	public void setHighFat(Boolean highFat) {
		this.highFat = highFat;
	}

	/**
	 * @return the lowSatFat
	 */
	public Boolean isLowSatFat() {
		return lowSatFat;
	}

	/**
	 * @param lowSatFat the lowSatFat to set
	 */
	public void setLowSatFat(Boolean lowSatFat) {
		this.lowSatFat = lowSatFat;
	}

	/**
	 * @return the highSatFat
	 */
	public Boolean isHighSatFat() {
		return highSatFat;
	}

	/**
	 * @param highSatFat the highSatFat to set
	 */
	public void setHighSatFat(Boolean highSatFat) {
		this.highSatFat = highSatFat;
	}

	/**
	 * @return the lowTransFat
	 */
	public Boolean isLowTransFat() {
		return lowTransFat;
	}

	/**
	 * @param lowTransFat the lowTransFat to set
	 */
	public void setLowTransFat(Boolean lowTransFat) {
		this.lowTransFat = lowTransFat;
	}

	/**
	 * @return the highTransFat
	 */
	public Boolean isHighTransFat() {
		return highTransFat;
	}

	/**
	 * @param highTransFat the highTransFat to set
	 */
	public void setHighTransFat(Boolean highTransFat) {
		this.highTransFat = highTransFat;
	}

	/**
	 * @return the adjustedReferenceAmount
	 */
	public Double getAdjustedReferenceAmount() {
		return adjustedReferenceAmount;
	}

	/**
	 * @param adjustedReferenceAmount the adjustedReferenceAmount to set
	 */
	public void setAdjustedReferenceAmount(Double adjustedReferenceAmount) {
		this.adjustedReferenceAmount = adjustedReferenceAmount;
	}

	/**
	 * @return the fopAdjustedReferenceAmount
	 */
	public Double getFopAdjustedReferenceAmount() {
		return fopAdjustedReferenceAmount;
	}

	/**
	 * @param fopAdjustedReferenceAmount the fopAdjustedReferenceAmount to set
	 */
	public void setFopAdjustedReferenceAmount(Double fopAdjustedReferenceAmount) {
		this.fopAdjustedReferenceAmount = fopAdjustedReferenceAmount;
	}

	/**
	 * @return the sodiumPerReferenceAmount
	 */
	public Double getSodiumPerReferenceAmount() {
		return sodiumPerReferenceAmount;
	}

	/**
	 * @param sodiumPerReferenceAmount the sodiumPerReferenceAmount to set
	 */
	public void setSodiumPerReferenceAmount(Double sodiumPerReferenceAmount) {
		this.sodiumPerReferenceAmount = sodiumPerReferenceAmount;
	}

	/**
	 * @return the sugarPerReferenceAmount
	 */
	public Double getSugarPerReferenceAmount() {
		return sugarPerReferenceAmount;
	}

	/**
	 * @param sugarPerReferenceAmount the sugarPerReferenceAmount to set
	 */
	public void setSugarPerReferenceAmount(Double sugarPerReferenceAmount) {
		this.sugarPerReferenceAmount = sugarPerReferenceAmount;
	}

	/**
	 * @return the fatPerReferenceAmount
	 */
	public Double getFatPerReferenceAmount() {
		return fatPerReferenceAmount;
	}

	/**
	 * @param fatPerReferenceAmount the fatPerReferenceAmount to set
	 */
	public void setFatPerReferenceAmount(Double fatPerReferenceAmount) {
		this.fatPerReferenceAmount = fatPerReferenceAmount;
	}

	/**
	 * @return the satFatPerReferenceAmount
	 */
	public Double getSatFatPerReferenceAmount() {
		return satFatPerReferenceAmount;
	}

	/**
	 * @param satFatPerReferenceAmount the satFatPerReferenceAmount to set
	 */
	public void setSatFatPerReferenceAmount(Double satFatPerReferenceAmount) {
		this.satFatPerReferenceAmount = satFatPerReferenceAmount;
	}

	/**
	 * @return the transFatPerReferenceAmount
	 */
	public Double getTransFatPerReferenceAmount() {
		return transFatPerReferenceAmount;
	}

	/**
	 * @param transFatPerReferenceAmount the transFatPerReferenceAmount to set
	 */
	public void setTransFatPerReferenceAmount(Double transFatPerReferenceAmount) {
		this.transFatPerReferenceAmount = transFatPerReferenceAmount;
	}

	/**
	 * @return the fopSugarPerReferenceAmount
	 */
	public Double getFopSugarPerReferenceAmount() {
		return fopSugarPerReferenceAmount;
	}

	/**
	 * @param fopSugarPerReferenceAmount the fopSugarPerReferenceAmount to set
	 */
	public void setFopSugarPerReferenceAmount(Double fopSugarPerReferenceAmount) {
		this.fopSugarPerReferenceAmount = fopSugarPerReferenceAmount;
	}

	/**
	 * @return the fopSodiumPerReferenceAmount
	 */
	public Double getFopSodiumPerReferenceAmount() {
		return fopSodiumPerReferenceAmount;
	}

	/**
	 * @param fopSodiumPerReferenceAmount the fopSodiumPerReferenceAmount to set
	 */
	public void setFopSodiumPerReferenceAmount(Double fopSodiumPerReferenceAmount) {
		this.fopSodiumPerReferenceAmount = fopSodiumPerReferenceAmount;
	}

	/**
	 * @return the done
	 */
	public Boolean isDone() {
		return done;
	}

	/**
	 * @param done the done to set
	 */
	public void setDone(Boolean done) {
		this.done = done;
	}

	/**
	 * @return the tier
	 */
	public Integer getTier() {
		return tier;
	}

	/**
	 * @param tier the tier to set
	 */
	public void setTier(Integer tier) {
		this.tier = tier;
	}
	public void setAdjustedTier(Integer tier) {
		this.tier     = tier;
		this.done     = true;
		this.absolute = true;
	}
	public void shiftTier(int shift) {
		if (!done) {
			this.tier  += shift;
			this.shift += shift;
		}
	}

	/**
	 * @return the shift
	 */
	public Integer getShift() {
		return shift;
	}

	/**
	 * @param shift the shift to set
	 */
	public void setShift(Integer shift) {
		this.shift = shift;
	}

	/**
	 * @return the absolute
	 */
	public Boolean getAbsolute() {
		return absolute;
	}

	/**
	 * @param absolute the absolute to set
	 */
	public void setAbsolute(Boolean absolute) {
		this.absolute = absolute;
	}

	/**
	 * @return the satFatFopWarning
	 */
	public Boolean getSatFatFopWarning() {
		return satFatFopWarning;
	}

	/**
	 * @param satFatFopWarning the satFatFopWarning to set
	 */
	public void setSatFatFopWarning(Boolean satFatFopWarning) {
		this.satFatFopWarning = satFatFopWarning;
	}

	/**
	 * @return the sugarFopWarning
	 */
	public Boolean getSugarFopWarning() {
		return sugarFopWarning;
	}

	/**
	 * @param sugarFopWarning the sugarFopWarning to set
	 */
	public void setSugarFopWarning(Boolean sugarFopWarning) {
		this.sugarFopWarning = sugarFopWarning;
	}

	/**
	 * @return the sodiumFopWarning
	 */
	public Boolean getSodiumFopWarning() {
		return sodiumFopWarning;
	}

	/**
	 * @param sodiumFopWarning the sodiumFopWarning to set
	 */
	public void setSodiumFopWarning(Boolean sodiumFopWarning) {
		this.sodiumFopWarning = sodiumFopWarning;
	}

	/**
	 * @return the marketedToKids
	 */
	public Boolean getMarketedToKids() {
		return marketedToKids;
	}

	/**
	 * @param marketedToKids the marketedToKids to set
	 */
	public void setMarketedToKids(Boolean marketedToKids) {
		this.marketedToKids = marketedToKids;
	}

	/**
	 * @return the initialCfgCode
	 */
	public Integer getInitialCfgCode() {
		return initialCfgCode;
	}

	/**
	 * @param initialCfgCode the initialCfgCode to set
	 */
	public void setInitialCfgCode(Integer initialCfgCode) {
		this.initialCfgCode = initialCfgCode;
	}
}
