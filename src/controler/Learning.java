package controler;

import java.util.LinkedList;
import java.util.List;
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
    

    public static String getGroupStudentsSQL(int groupId, boolean branchInfoToo) {
    	return "select persons.id, fname, lname "
    			+ ((branchInfoToo) ? ("branch_id, students_branches.semester ") : "")
    				+ "from groups join students_branches__groups "
    				+ "on group_id = id join students_branches on student_branch_id "
    				+ " = students_branches.id join persons on student_id = persons.id "
    				+ "where group_id = " + groupId;
    	
    }
    
    public static List<Long> getMyStudents_branchesId(int myId)
    {
    	Vector<Vector<Object>> resVect = Utility.getData("select id from "
    			+ "students_branches where student_id = " + myId);
    	LinkedList<Long> l = new LinkedList<Long>();
    	for (Vector v : resVect)
    		l.add((Long)v.get(0));
    	return l;
    }
    
    public static boolean groupDetailsPermission (int myId, int groupId)
    {
    	Vector<Vector<Object>> vres;
    	vres = Utility.getData("SELECT (select count(*) from "
        		+ "staff_groups join groups on (group_id = groups.id) "
        		+ "where groups.id = " + groupId + "and staff_id = " + myId +
        		") > 0");
    	if((boolean)vres.get(0).get(0) == true) return true;
        
    	
    	List<Long> l = getMyStudents_branchesId(myId);
    	if(l.isEmpty()) return false;
    	
    	StringBuilder allIds = new StringBuilder();
    	boolean start = true;
    	for (Long i : l)
    	{
    		if(start) start = false;
    		else allIds.append(", ");
    		allIds.append(i);
    	}
    	vres = Utility.getData("SELECT (select count(*) from "
        		+ "students_branches__groups join groups on "
        		+ "(group_id = groups.id) "
        		+ "where groups.id = " + groupId + 
        		" and student_branch_id in (" + allIds + ")) > 0");
    	return (boolean)vres.get(0).get(0);
    }
}
