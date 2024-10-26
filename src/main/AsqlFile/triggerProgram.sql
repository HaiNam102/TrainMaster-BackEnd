USE train_master;
-- Add muscle_group to exercise table
ALTER TABLE exercise
ADD muscle_group NVARCHAR(255);

-- Add total_volume to program table
ALTER TABLE program
ADD total_volume FLOAT DEFAULT 0;

-- Add volume to exercise_of_program table
ALTER TABLE exercise_of_program
ADD volume FLOAT DEFAULT 0;
-- Trigger to calculate volume in exercise_of_program
-- Trigger to calculate volume after inserting into exercise_of_progra
DELIMITER $$

CREATE TRIGGER BeforeExerciseUpdate
BEFORE UPDATE ON exercise_of_program
FOR EACH ROW
BEGIN
    -- Calculate the volume before updating the row based on the new set values
    SET NEW.volume = COALESCE(NEW.set1, 0) + COALESCE(NEW.set2, 0) + COALESCE(NEW.set3, 0) + COALESCE(NEW.set4, 0) + COALESCE(NEW.set5, 0);
END $$

DELIMITER ;
DELIMITER $$

CREATE TRIGGER AfterExerciseUpdate
AFTER UPDATE ON exercise_of_program
FOR EACH ROW
BEGIN
    -- Update the total volume for the associated program after the exercise's volume is updated
    UPDATE program
    SET total_volume = (SELECT SUM(volume) FROM exercise_of_program WHERE program_id = NEW.program_id)
    WHERE program_id = NEW.program_id;
END $$

DELIMITER ;




UPDATE exercise SET muscle_group = 'Legs' WHERE exercise_id = 1;
UPDATE exercise SET muscle_group = 'Chest' WHERE exercise_id = 2;
UPDATE exercise SET muscle_group = 'Back' WHERE exercise_id = 3;
UPDATE exercise SET muscle_group = 'Back' WHERE exercise_id = 4;
UPDATE exercise SET muscle_group = 'Chest' WHERE exercise_id = 5;
UPDATE exercise SET muscle_group = 'Legs' WHERE exercise_id = 6;
UPDATE exercise SET muscle_group = 'Core' WHERE exercise_id = 7;
UPDATE exercise SET muscle_group = 'Full Body' WHERE exercise_id = 8;
UPDATE exercise SET muscle_group = 'Core' WHERE exercise_id = 9;
UPDATE exercise SET muscle_group = 'Cardio' WHERE exercise_id = 10;



UPDATE exercise_of_program
SET 
    set1 = 10, 
    set2 = 12, 
    set3 = 14, 
    set4 = 16, 
    set5 = 18
WHERE 
    exercise_of_program_id = 1;


UPDATE exercise_of_program
SET
    set1 = 0,
    set2 = 0,
    set3 = 0,
    set4 = 0,
    set5 = 0
WHERE
    exercise_of_program_id = 1;

