package thread;

public class AutoSave extends Thread {

    private final int sleepTime;
    private boolean shouldStop = false;

    /**
     * Constructor for AutoSave
     * <p>
     *     Kaster en IllegalArgumentException hvis sleepTime er mindre end 0
     *     <p>
     *         For at stoppe dette skal man kalde Interrupt()
     * @param sleepTime Tiden mellem hver autosave i minutter
     */
    public AutoSave(int sleepTime) {
        this.sleepTime = sleepTime*1000;
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
