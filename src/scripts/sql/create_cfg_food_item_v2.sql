CREATE TABLE new_and_improved_canada_food_guide_food_item (
	type                                   	 text,
	code                                   	 int,
	name                                   	 text,
	group_code                             	 int,
	cfg_code                               	 int,
	cfg_code_update_date                   	 date,
	energy_kcal                            	 float8,
	sodium_amount_per_100g                 	 float8,
	sodium_imputation_reference            	 text,
	sodium_imputation_date                 	 date,
	sugar_amount_per_100g                  	 float8,
	sugar_imputation_reference             	 text,
	sugar_imputation_date                  	 date,
	transfat_amount_per_100g               	 float8,
	transfat_imputation_reference          	 text,
	transfat_imputation_date               	 date,
	satfat_amout_per_100g                  	 float8,
	satfat_imputation_reference            	 text,
	satfat_imputation_date                 	 date,
	totalfat_amout_per_100g                	 float8,
	totalfat_imputation_reference          	 text,
	totalfat_imputation_date               	 date,
	contains_added_sodium                  	 smallint,
	contains_added_sodium_update_date      	 date,
	contains_added_sugar                   	 smallint,
	contains_added_sugar_update_date       	 date,
	contains_free_sugars                   	 smallint,
	contains_free_sugars_commit_date       	 date,
	contains_added_fat                     	 smallint,
	contains_added_fat_update_date         	 date,
	contains_added_transfat                	 smallint,
	contains_added_transfat_update_date    	 date,
	contains_caffeine                      	 smallint,
	contains_caffeine_update_date          	 date,
	contains_sugar_substitutes             	 smallint,
	contains_sugar_substitutes_update_date 	 date,
	reference_amount_g                     	 float8,
	reference_amount_measure               	 text,
	reference_amount_update_date           	 date,
	food_guide_serving_g                   	 float8,
	food_guide_serving_measure             	 text,
	food_guide_update_date                 	 date,
	tier_4_serving_g                       	 float8,
	tier_4_serving_measure                 	 text,
	tier_4_serving_update_date             	 date,
	rolled_up                              	 smallint,
	rolled_up_update_date                  	 date,
	apply_small_ra_adjustment              	 text,
	replacement_code                       	 int,
	commit_date                            	 date,
	comments                               	 text,
	PRIMARY KEY (code)
);
