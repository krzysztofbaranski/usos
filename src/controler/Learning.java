package controler;

import java.util.Vector;

/**
 * Created by krzysztof on 03/02/14.
 */
public class Learning {
	
    public static String getGroupSQL(int groupId) {
        return "SELECT groups.id, groups.name,"
        	+ "group_types.type_name, year, semester,"
        	+ "courses.name from (groups join group_types on "
        	+ "(groups.type = group_types.id)) join courses on "
        	+ "(course_id = courses.id) where groups.id = " + groupId;
    }
    
    
    public static String getGroupTeachersSQL(int groupId) {
        return "SELECT persons.id, fname, lname "
        		+ "from persons join staff_groups on (staff_id = persons.id) "
        		+ "join groups on (group_id = groups.id) "
        		+ "where groups.id = " + groupId;
    }
    

}
