public class NoVisibility extends Thread {

    private int number;
    private boolean ready;
    private int index;

    public NoVisibility(int index) {
        this.index = index;
    }

    private class ReaderThread extends Thread {
        public void run() {
            while (!ready)
                Thread.yield();
            System.out.println("Index: " + index + " Value: " + number);
        }
    }
    
    public void run() {
        new ReaderThread().start();
        number = 42;
        ready = true;
    }

}
