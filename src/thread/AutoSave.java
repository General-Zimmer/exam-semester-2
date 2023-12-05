package thread;

public class AutoSave extends Thread {

    private final int sleepTime;
    private boolean shouldStop = false;

    public AutoSave(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    @Override
    public synchronized void run() {
        while (!shouldStop) {
            try {
                wait(sleepTime);
                System.out.println("AutoSave");
            } catch (InterruptedException e) {
                System.out.println("Auto save stopped");
                shouldStop = true;
            }
        }
    }
}
