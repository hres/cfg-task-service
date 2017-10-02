UPDATE canada_food_guide_food_item
   SET override_small_ra_adjustment = FALSE
 WHERE override_small_ra_adjustment IS NULL;
