public class Runner {

    private static int THREAD_COUNT = 5000;

    public static void main(String[] args) throws InterruptedException{
        Thread[] executors = new Thread[THREAD_COUNT];
        for (int i = 0; i < executors.length; i++) {
            executors[i] = new NoVisibility(i);
        }
        for (int i = 0; i < executors.length; i++) {
            executors[i].start();
            executors[i].join();
        }
        System.out.println("All threads finished");
    }
}
