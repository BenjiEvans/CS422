CREATE OR REPLACE FUNCTION female_faculty() RETURNS real AS'
DECLARE
	count REAL := 0;
	total REAL := 0;
BEGIN
	SELECT COUNT(gender) INTO count FROM professor WHERE gender=''F'';
	SELECT COUNT(*) INTO total FROM professor;
	
	RETURN (count/total) * 100;
END;

' LANGUAGE 'plpgsql';