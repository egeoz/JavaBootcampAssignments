import java.util.ArrayList;
import java.util.List;

public class StudentList {
    protected static List<Student> studentList= new ArrayList<>();

    // Method to add new students to the student list.
    public static void addStudent(Student student){
        studentList.add(student);
    }

    // Method to remove students from the student list.
    public static void removeStudent(String name, String surname, int age){
        Student student = getStudent(name, surname, age);
        if (student != null){
            System.out.printf("Removing:%n%s",student.toString());
            studentList.remove(getStudent(name, surname, age));
        }else{
            System.out.println("Student does not exist.");
        }
    }

    // Method to check if student exists in a list. Otherwise, return null.
    public static Student getStudent(String name, String surname, int age){
        for (Student student : studentList){
            if (student.getName().equals(name) & student.getSurname().equals(surname) & student.getAge() == age){
                return student;
            }
        }
        return null;
    }

    // Return a string representation of the student list by
    // iterating in the student list and appending the toString() values to the output.
    public static String getAll(){
        StringBuilder output = new StringBuilder();
        for (Student student : studentList){
            output.append(student.toString());
        }
        return output.toString();
    }

}
