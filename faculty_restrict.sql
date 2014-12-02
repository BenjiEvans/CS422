CREATE OR REPLACE FUNCTION faculty_restrict() RETURNS trigger AS'
DECLARE
	ssncheck INTEGER;
	count INTEGER := 0;
	row_data works_on%ROWTYPE;
BEGIN
	FOR row_data IN SELECT * FROM works_on WHERE project_num = NEW.project_num
	LOOP
		SELECT ssn into ssncheck FROM professor WHERE SSN=row_data.SSN;
		IF FOUND THEN 
			count := count + 1;
		END IF;
	END LOOP;
	
	RAISE NOTICE ''Professors working on project is %'', count;
	
	IF count < 4 THEN
		IF TG_OP = ''UPDATE''
			THEN UPDATE works_on SET project_num = NEW.project_num WHERE ssn=NEW.ssn;
		END IF;
	ELSE
		RAISE EXCEPTION ''Too many professors working on that project.'';
	END IF;
	
	RETURN NEW;
	
END;

' LANGUAGE 'plpgsql';

CREATE TRIGGER faculty_restrict
BEFORE INSERT OR UPDATE ON works_on
FOR EACH ROW EXECUTE PROCEDURE faculty_restrict();