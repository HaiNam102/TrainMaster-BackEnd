use train_master;

ALTER TABLE mealplan
ADD total_kcal FLOAT DEFAULT 0,
ADD total_protein FLOAT DEFAULT 0,
ADD total_carb FLOAT DEFAULT 0,
ADD total_fat FLOAT DEFAULT 0;
DELIMITER $$

DROP TRIGGER IF EXISTS AfterMealPlanInsert;

CREATE TRIGGER AfterMealPlanInsert
AFTER INSERT ON food_of_meal
FOR EACH ROW
BEGIN
    -- Declare variables for total nutrition values
    DECLARE kcal_sum FLOAT DEFAULT 0;
    DECLARE protein_sum FLOAT DEFAULT 0;
    DECLARE carb_sum FLOAT DEFAULT 0;
    DECLARE fat_sum FLOAT DEFAULT 0;

    -- Calculate total nutrition for the meal plan
    SELECT SUM(f.kcal), SUM(f.protein), SUM(f.carb), SUM(f.fat)
    INTO kcal_sum, protein_sum, carb_sum, fat_sum
    FROM food_of_meal fom
    JOIN food f ON fom.food_id = f.food_id
    WHERE fom.mealplan_id = NEW.mealplan_id;

    -- Update the mealplan table with the calculated totals
    UPDATE mealplan
    SET total_kcal = kcal_sum,
        total_protein = protein_sum,
        total_carb = carb_sum,
        total_fat = fat_sum
    WHERE mealplan_id = NEW.mealplan_id;
END $$

DELIMITER ;
