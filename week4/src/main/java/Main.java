public class Main {
    public static void main(String[] args){
        // Importing runnable implementations for our three threads.
        ReadingRunnable readingRunnable = new ReadingRunnable();
        OutputRunnable outputRunnable = new OutputRunnable();
        MenuRunnable menuRunnable = new MenuRunnable();

        // To ensure no overlap, each method will run separately on different threads.
        Thread readingThread = new Thread(readingRunnable); // Thread for reading and parsing the JSON file.
        Thread outputThread = new Thread(outputRunnable);   // Thread for printing the parsed JSON data.
        Thread menuThread = new Thread(menuRunnable);       // Thread for showing the utility menu.

        // Start the threads.
        readingThread.start();
        outputThread.start();
        menuThread.start();
    }
}
