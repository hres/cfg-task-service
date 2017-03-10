CREATE TABLE canada_food_guide_food_name (
    fn_recipe_flg smallinttext,                           -- Boolean   foodRecipeType
    code float8text,                                      -- String    code
    name texttext,                                        -- String    name
    cnf_code texttext,                                    -- String    cnfCode
    cfg_code texttext,                                    -- String    cfgCode
    cfg_code_commit_date datetext,                        -- Date      cfgCodeCommitDate
    energy_kcal float8text,                               -- Double    energyKcal
    sodium_amount_per_100g float8text,                    -- Double    sodiumAmountPer100g
    sodium_imputation_reference texttext,                 -- String    sodiumImputationReference
    sodium_imputation_date datetext,                      -- Date      sodiumImputationDate
    sugar_amount_per_100g float8text,                     -- Double    sugarAmountPer100g
    sugar_imputation_reference texttext,                  -- String    sugarImputationReference
    sugar_imputation_date datetext,                       -- Date      sugarImputationDate
    transfat_amount_per_100g float8text,                  -- Double    transfatAmountPer100g
    transfat_imputation_reference texttext,               -- String    transfatImputationReference
    transfat_imputation_date datetext,                    -- Date      transfatImputationDate
    satfat_amout_per_100g float8text,                     -- Double    satfatAmoutPer100g
    satfat_imputation_reference texttext,                 -- String    satfatImputationReference
    satfat_imputation_date datetext,                      -- Date      satfatImputationDate
    totalfat_amout_per_100g float8text,                   -- Double    totalfatAmoutPer100g
    totalfat_imputation_reference texttext,               -- String    totalfatImputationReference
    totalfat_imputation_date datetext,                    -- Date      totalfatImputationDate
    contains_added_sodium booleantext,                    -- Boolean   containsAddedSodium
    contains_added_sodium_commit_date datetext,           -- Date      containsAddedSodiumCommitDate
    contains_added_sugar booleantext,                     -- Boolean   containsAddedSugar
    contains_added_sugar_commit_date datetext,            -- Date      containsAddedSugarCommitDate
    contains_free_sugars booleantext,                     -- Boolean   containsFreeSugars
    contains_free_sugars_commit_date datetext,            -- Date      containsFreeSugarsCommitDate
    contains_added_fat booleantext,                       -- Boolean   containsAddedFat
    contains_added_fat_commit_date datetext,              -- Date      containsAddedFatCommitDate
    contains_added_transfat booleantext,                  -- Boolean   containsAddedTransfat
    contains_added_transfat_commit_date datetext,         -- Date      containsAddedTransfatCommitDate
    contains_caffeine booleantext,                        -- Boolean   containsCaffeine
    contains_caffeine_commit_date datetext,               -- Date      containsCaffeineCommitDate
    contains_sugar_substitutes booleantext,               -- Boolean   containsSugarSubstitutes
    contains_sugar_substitutes_commit_date datetext,      -- Date      containsSugarSubstitutesCommitDate
    reference_amount_g float8text,                        -- Double    referenceAmountG
    reference_amount_measure texttext,                    -- String    referenceAmountMeasure
    reference_amount_commit_date datetext,                -- Date      referenceAmountCommitDate
    food_guide_serving_g float8text,                      -- Double    foodGuideServingG
    food_guide_serving_measure texttext,                  -- String    foodGuideServingMeasure
    food_guide_commit_date datetext,                      -- Date      foodGuideCommitDate
    tier_4_serving_g float8text,                          -- Double    tier4ServingG
    tier_4_serving_measure texttext,                      -- String    tier4ServingMeasure
    tier_4_serving_commit_date datetext,                  -- Date      tier4ServingCommitDate
    rolled_up booleantext,                                -- Boolean   rolledUp
    rolled_up_commit_date datetext,                       -- Date      rolledUpCommitDate
    apply_small_ra_adjustment booleantext,                -- Boolean   applySmallRaAdjustment
    comments text                                         -- String    comments
);

INSERT INTO canada_food_guide_food_name(fn_recipe_flgtext, code, name, cnf_code, cfg_code)
SELECT fn_recipe_flg AS fn_recipe_flgtext, food_code AS code, food_desc AS name, group_c AS cnf_code, canada_food_subgroup_id AS cfg_code
  FROM food_name

