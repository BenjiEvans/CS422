CREATE OR REPLACE FUNCTION student_restrict() RETURNS trigger AS'
DECLARE
	total INTEGER := 0;
BEGIN
	SELECT count(project_num) INTO total FROM Grad_works 
    WHERE ssn = NEW.ssn; 
	IF total > 2 THEN 
     RAISE EXCEPTION ''Student cannot work on more than 2 projects'';
	END IF;
	
	RETURN NEW;
	
END;

' LANGUAGE 'plpgsql';


CREATE TRIGGER student_restrict
AFTER INSERT ON Grad_works
FOR EACH ROW EXECUTE PROCEDURE student_restrict();