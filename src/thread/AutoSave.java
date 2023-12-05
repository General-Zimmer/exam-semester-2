package thread;

public class AutoSave extends Thread {

        private final int sleepTime;

        public AutoSave(int sleepTime) {
            this.sleepTime = sleepTime;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(sleepTime);
                    System.out.println("AutoSave");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
}
