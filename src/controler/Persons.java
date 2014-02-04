package controler;

import java.util.Vector;

/**
 * Created by krzysztof on 03/02/14.
 */
public class Persons {

    /**
     *
     * get students
     *
     */
    public static Vector<Vector<Object>> getStudents(String... columns) {
        StringBuilder query = new StringBuilder("SELECT ");
        for(String s: columns) query.append(s).append(',');
        query.deleteCharAt(query.length()-1).append(" FROM students");
        return Utility.GetData(query.toString());
    }

    /**
     *
     * get staff
     *
     */
    public static Vector<Vector<Object>> getStaff(String... columns) {
        StringBuilder query = new StringBuilder("SELECT ");
        for(String s: columns) query.append(s).append(',');
        query.deleteCharAt(query.length()-1).append(" FROM staff");
        return Utility.GetData(query.toString());
    }

    /**
     *
     * add student
     *
     */
    public static void addStudent(int studentBook,
                                  String fName,
                                  String sName,
                                  String lName,
                                  String pesel,
                                  String dateOfBirth,
                                  String placeOfBirth,
                                  String address,
                                  String phone,
                                  String mail) {
        StringBuilder insert = new StringBuilder("INSERT INTO students(student_book,first_name,second_name,last_name,pesel,date_of_birth,place_of_birth,address,phone,mail) VALUES('");

        insert.append(Integer.toString(studentBook)).append('\'').append(',')
                .append('\'').append(fName).append('\'').append(',')
                .append((sName != null ? "'"+sName+"'" : "null")).append(',')
                .append('\'').append(lName).append('\'').append(',')
                .append('\'').append(pesel).append('\'').append(',')
                .append('\'').append(dateOfBirth).append('\'').append(',')
                .append('\'').append(placeOfBirth).append('\'').append(',')
                .append((address != null ? "'"+address+"'" : "null")).append(',')
                .append((phone != null ? "'"+phone+"'" : "null")).append(',')
                .append((mail != null ? "'"+mail+"'" : "null"))
                .append(")");

        Utility.InsertData(insert.toString());
    }

    /**
     *
     * add staff
     *
     */
    public static void addStaff(String staffCode,
                                String fName,
                                String sName,
                                String lName,
                                String pesel,
                                String dateOfBirth,
                                String placeOfBirth,
                                String address,
                                String phone,
                                String mail,
                                String academic_title,
                                Integer room,
                                String post,
                                Integer cathedral_id) {
        StringBuilder insert = new StringBuilder("INSERT INTO staff(staff_code,first_name,second_name,last_name,pesel,date_of_birth,place_of_birth,address,phone,mail,academic_title,room,post,cathedral_id) VALUES('");
        insert.append(staffCode).append('\'').append(',')
                .append('\'').append(fName).append('\'').append(',')
                .append((sName != null ? "'"+sName+"'" : "null")).append(',')
                .append('\'').append(lName).append('\'').append(',')
                .append('\'').append(pesel).append('\'').append(',')
                .append('\'').append(dateOfBirth).append('\'').append(',')
                .append('\'').append(placeOfBirth).append('\'').append(',')
                .append((address != null ? "'"+address+"'" : "null")).append(',')
                .append((phone != null ? "'"+phone+"'" : "null")).append(',')
                .append((mail != null ? "'"+mail+"'" : "null")).append(',')
                .append((academic_title != null ? "'"+academic_title+"'" : "null")).append(',')
                .append((room != null ? room : "null")).append(',')
                .append('\'').append(post).append('\'').append(',')
                .append((cathedral_id != null ? cathedral_id : "null"))
                .append(")");

        Utility.InsertData(insert.toString());
    }
}
