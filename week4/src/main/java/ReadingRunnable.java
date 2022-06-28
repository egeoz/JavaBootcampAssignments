public class ReadingRunnable implements Runnable{
    @Override
    public void run() {
        // Activate readLock semaphore.
        Semaphores.readLock = true;
        // Parse JSON data from student_list.json.
        StudentJSONHandler studentJSON = new StudentJSONHandler("student_list.json");
        System.out.println("Loading students from \"student_list.json\" file...");
        studentJSON.readJSONFile();
        // Deactivate readLock semaphore.
        Semaphores.readLock = false;
    }
}
