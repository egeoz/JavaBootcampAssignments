public class OutputRunnable implements Runnable{
    @Override
    public void run() {
        try {
            // Sleep for 3 seconds to avoid collision with reading thread.
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        }
        // If readLock semaphore is deactivated enable outputLock semaphore until student list is printed.
        if(!Semaphores.readLock){
            Semaphores.outputLock = true;
            System.out.println(StudentList.getAll());
            Semaphores.outputLock = false;
        }
    }
}
