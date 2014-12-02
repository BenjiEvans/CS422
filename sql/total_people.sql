CREATE OR REPLACE FUNCTION total_people(Integer) RETURNS Integer  AS '
	DECLARE 
		project_id ALIAS FOR $1;
		total_grad Integer := 0;
		total_prof Integer := 0;
		PI Integer := 0;
		total Integer := 0;
	BEGIN 
		SELECT count(project_num) INTO total_grad FROM Grad_works
              WHERE project_num = project_id; 
		SELECT count(project_num) INTO total_prof FROM Works_on
              WHERE project_num = project_id;		
			  
		total := total_grad + total_prof;
		
		SELECT count(project_num) INTO PI FROM Works_on
              WHERE project_num = project_id and ssn=(select ssn from Project where project_num = project_id);
			  
		IF PI = 0
			THEN
			total := total+1;
		END IF;
			  
        RETURN total; 
	END; 
' LANGUAGE 'plpgsql'; 
