import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuRunnable implements Runnable{
    private StudentJSONHandler jsonHandler = new StudentJSONHandler("student_list.json");
    private Scanner sc = new Scanner(System.in);
    private int menuChoice = 0;
    private String name, surname, age;

    // Returns the utility menu as a string.
    public static String getMenu(){
        StringBuilder output = new StringBuilder();
        output.append("\nStudent Management Utility\n");
        output.append("1 - Add new student\n");
        output.append("2 - Remove a student\n");
        output.append("3 - Print the list of all students\n");
        output.append("Press any other key to quit.\n");
        output.append("> ");
        return output.toString();
    }

    // Checks if the parameter is a valid integer.
    public static boolean isNumeric(String age) {
        if (age == null) {
            return false;
        }
        try {
            // Try parsing age to see if it triggers NumberFormatException.
            Integer.parseInt(age);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    @Override
    public void run() {
        try {
            // Sleep for 5 seconds to avoid collision with the other threads.
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        }
        while (!Semaphores.outputLock){
            System.out.print(getMenu());

            // Get menu option, quit if it is not an integer.
            try{
                menuChoice = sc.nextInt();
            } catch (InputMismatchException e){
                System.exit(0);
            }

            sc.nextLine();

            switch (menuChoice){
                case 1: // Add Student
                    System.out.print("Name: ");
                    name = sc.nextLine();

                    System.out.print("Surname: ");
                    surname = sc.nextLine();

                    // Keep asking for an age until a valid integer is entered.
                    do{
                        System.out.print("Age: ");
                        age = sc.nextLine();
                    } while (!isNumeric(age));

                    StudentList.addStudent(new Student(name, surname, Integer.parseInt(age)));
                    // Save the new list to the JSON file.
                    try {
                        jsonHandler.writeJSONFile();
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                    break;
                case 2: // Remove student
                    System.out.print("Name: ");
                    name = sc.nextLine();

                    System.out.print("Surname: ");
                    surname = sc.nextLine();

                    // Keep asking for an age until a valid integer is entered.
                    do{
                        System.out.print("Age: ");
                        age = sc.nextLine();
                    } while (!isNumeric(age));

                    StudentList.removeStudent(name, surname, Integer.parseInt(age));
                    // Save the new list to the JSON file.
                    try {
                        jsonHandler.writeJSONFile();
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                    break;
                case 3: // Print the list of students.
                    System.out.println(StudentList.getAll());
                    break;
                default: // Quit if menuChoice is any other integer.
                    System.exit(0);
            }
        }
    }
}
