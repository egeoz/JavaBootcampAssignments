import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Comparator;

class Priorities{
    // Create a private Priority Queue of Student type.
    private PriorityQueue<Student> studentPriorityQueue = new PriorityQueue<>(Comparator.comparing(Student::getCGPA).reversed() // Sort CGPA by descending order
                    .thenComparing(Student::getName)    // Sort name by ascending order
                    .thenComparing(Student::getID));    // Sort ID by ascending order
                    
    private List <Student> studentList = new ArrayList<Student>();
    
    public List<Student> getStudents(List<String> events){                    
        String[] line;
        Student student;
        
        int id;
        String name;
        double cgpa;
        
        // Iterate in the events list
        for (String event : events){
            // Split event by its whitespace
            line = event.split(" ");
            
            // Serve the head student
            if (line[0].equals("SERVED")){
                studentPriorityQueue.poll();
            // Process "ENTER" command and create a new student
            }else if (line[0].equals("ENTER")){
                // Parse (ENTER NAME CGPA ID)
                id = Integer.parseInt(line[3]);
                name = line[1];
                cgpa = Double.parseDouble(line[2]);
                
                student = new Student(id, name, cgpa);
                studentPriorityQueue.add(student);
            // Otherwise move on to the next iteration
            }else{continue;} 
        }
        
        // Set student to the head item of the priority queue and then add it to the list
        while (!studentPriorityQueue.isEmpty()){
            student = studentPriorityQueue.poll();
            studentList.add(student);
        }

        return studentList;
    }
}

class Student{
    private int id;
    private String name;
    private double cgpa;
    
    public Student(int id, String name, double cgpa){
        this.id     = id;
        this.name   = name;
        this.cgpa   = cgpa;   
    }
    
    // Getters
    
    public int getID(){
        return this.id;
    }   
    
    public String getName(){
        return this.name;
    }
    
    public double getCGPA(){
        return this.cgpa;
    }
    
    @Override
    public String toString(){
        return String.format("Student ID: %d%nStudent Name: %s%nStudent CGPA: %.2f%n", this.id, this.name, this.cgpa);
    }
        
}

// Hackerrank part of the solution
public class Solution {
    private final static Scanner scan = new Scanner(System.in);
    private final static Priorities priorities = new Priorities();
    
    public static void main(String[] args) {
        int totalEvents = Integer.parseInt(scan.nextLine());    
        List<String> events = new ArrayList<>();
        
        while (totalEvents-- != 0) {
            String event = scan.nextLine();
            events.add(event);
        }
        
        List<Student> students = priorities.getStudents(events);
        
        if (students.isEmpty()) {
            System.out.println("EMPTY");
        } else {
            for (Student st: students) {
                System.out.println(st.getName());
            }
        }
    }
}
