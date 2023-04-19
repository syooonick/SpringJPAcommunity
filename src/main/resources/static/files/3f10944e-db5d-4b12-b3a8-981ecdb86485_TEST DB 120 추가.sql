use board;
use flea;

DELIMITER $$

CREATE PROCEDURE testDataInsert()

BEGIN
	DECLARE i INT DEFAULT 1;
    
    WHILE i <= 15 DO
		INSERT INTO flea(title, content)
			VALUES(concat('TEST',i), concat('TEST',i));
		SET i = i + 1;
	END WHILE;
END$$
DELIMITER $$

DROP PROCEDURE IF EXISTS testdatainsert1;