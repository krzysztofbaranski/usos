package testyKK;
import java.util.List;
import java.util.Vector;

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
		 List<Long> l = Learning.getMyStudents_branchesId(1);
		 for (Long ll : l)
	    		System.out.println(ll);
		 System.out.println(Learning.groupDetailsPermission(1, 1));
		 System.out.println(Learning.groupDetailsPermission(3, 1));
		 System.out.println(Learning.groupDetailsPermission(12, 1));
		 System.out.println(Learning.groupDetailsPermission(13, 1));
		 
		 
	}
}
