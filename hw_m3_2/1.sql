ALTER TABLE developers ADD salary NUMERIC;

UPDATE developers SET salary = 5000 WHERE id = 1;
UPDATE developers SET salary = 1500 WHERE id = 2;
UPDATE developers SET salary = 2500 WHERE id = 3;
UPDATE developers SET salary = 2000 WHERE id = 4;
UPDATE developers SET salary = 2500 WHERE id = 5;
UPDATE developers SET salary = 1000 WHERE id = 6;