package testyKK;
import java.util.List;
import java.util.Vector;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import controler.Learning;
import controler.Persons;
import controler.Utility;

public class Test1 {
	
	public static void printSth (Vector<Vector<Object>> res) {
		for (Vector v : res){
			 for (Object w : v)
				 System.out.print(w + " ");
		System.out.println();
		}
	}
	public static void main(String[] args) {
		 Vector<Vector<Object>> res;
		 res = Persons.getStudents("last_name", "first_name");
		 printSth(res);
		 res = Utility.getData(Learning.getGroupSQL(1));
		 printSth(res);
		 res = Utility.getData(Learning.getGroupTeachersSQL(1));
		 printSth(res);
		 res = Utility.getData(Learning.getGroupStudentsSQL(1, true));
		 printSth(res);
		 List<Long> l = Learning.getStudentIds(1);
		 for (Long ll : l)
	    		System.out.println(ll);
		 Persons.fillInStudentsBranchesIdsList();
		 System.out.println(Learning.groupDetailsPermissionTeacher(1));
		 System.out.println(Learning.groupDetailsPermissionStudent(1));
/*		 System.out.println(Learning.groupDetailsPermissionTeacher(1, 1));
		 System.out.println(Learning.groupDetailsPermissionTeacher(3, 1));
		 System.out.println(Learning.groupDetailsPermissionTeacher(12, 1));
		 System.out.println(Learning.groupDetailsPermissionTeacher(13, 1));
		 System.out.println(Learning.groupDetailsPermissionStudent(1, 1));
		 System.out.println(Learning.groupDetailsPermissionStudent(3, 1));
		 System.out.println(Learning.groupDetailsPermissionStudent(12, 1));
		 System.out.println(Learning.groupDetailsPermissionStudent(13, 1));
*/		 
		 res = Utility.getData(Learning.getClasses(1));
		 printSth(res);
		 res = Utility.getData(Learning.getMarks(2, 1, false));
		 printSth(res);
		 System.out.println("");
		 res = Utility.getData(Learning.getMarks(-1, -1, false));
		 printSth(res);
		 System.out.println("");
		 res = Utility.getData(Learning.getMarks(-1, -1, true));
		 printSth(res);
		 System.out.println("");
		 res = Utility.getData(Learning.getMarks(1, -1, false));
		 printSth(res);
		 System.out.println("");
		 
		 res = Utility.getData(Learning.getAverage(1, 1, 2013, 1));
		 printSth(res);
		 res = Utility.getData(Learning.getAverage(2, 1, 2013, 1));
		 printSth(res);
		 System.out.println(Learning.isItMyBranch(1, 1));
		 System.out.println("");
		 
		 res = Utility.getData(Learning.getBranch(1));
		 printSth(res);
		 res = Utility.getData(Learning.getCoursesOnBranch(1, -1, false, false));
		 printSth(res);
		 res = Utility.getData(Learning.getCoursesOnBranch(1, -1, false, true));
		 printSth(res);
		 res = Utility.getData(Learning.getCourse(1));
		 printSth(res);
		 res = Utility.getData(Learning.getCourseGroups(1, -1, 42));
		 printSth(res);
		 System.out.println(" ");
		 res = Utility.getData(Learning.getCourseGroups(1, 2013, 1));
		 printSth(res);
		 System.out.println(" ");
		 res = Utility.getData(Learning.getCourseGroups(1, 2012, 1));
		 printSth(res);
		 System.out.println(" ");
		 res = Utility.getData(Learning.getFinalMarks(1));
		 printSth(res);
		 System.out.println(" ");
		 res = Utility.getData(Learning.getFinalMarks(2));
		 printSth(res);
		 
		 System.out.println(Utility.insertSchema("courses", "name", "SO", "ects", "asd", "final_mark_group_type", 1));
		 
		 


	}
}
