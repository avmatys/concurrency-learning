import java.util.concurrent.atomic.AtomicReference;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap; 
import java.util.Random;

public class UnsafeSequence {

    private static int REPEAT_COUNT = 10000;
    private static int THREAD_COUNT = 1000;

    private static boolean RANDOM_THREAD_SIZE = true;
    private static boolean SHOW_START = false;
    private static boolean SHOW_JOIN = false;
    private static boolean SHOW_THREAD_NUM = false;
    private static boolean SHOW_GOT_VALUE = false;

    private int value; 

    // Non-thread safe operation
    public int getNext() {
        return this.value++;
    }
    
    private static int[] execute() {
        Set<Integer> threadSafeSet = ConcurrentHashMap.newKeySet();
        UnsafeSequence us1 = new UnsafeSequence();
        int threadSize = THREAD_COUNT;
        if (RANDOM_THREAD_SIZE) {
            threadSize = new Random().nextInt(THREAD_COUNT) + 1;
        }
        Thread[] t = new Thread[threadSize];
        for (int i = 0; i < t.length; i++) {
            AtomicReference<Integer> refIdx = new AtomicReference<>(i);
            t[i] = new Thread(() -> {
                if (SHOW_THREAD_NUM)
                    System.out.println("Thread " + refIdx.get());
                int value = us1.getNext();
                if (SHOW_GOT_VALUE)
                    System.out.println("Value " + value);
                threadSafeSet.add(value);
            });
        }
        for (int i = 0; i < t.length; i++) {
            if (SHOW_START)
                System.out.println("Calling a start for the Thread " + i);
            t[i].start();
        } 
        for (int i = 0; i < t.length; i++) {
            try {
                if (SHOW_JOIN)
                    System.out.println("Calling a join for the Thread " + i);
                t[i].join();
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Expected set size: " + t.length + " Actual set size: " + threadSafeSet.size());
        return new int[] {t.length, threadSafeSet.size()};
    }

    public static void main(String[] args) {
        int equalCount = 0;
        for (int k = 0; k < REPEAT_COUNT; k++) {
            int[] result = execute();
            if (result[0] == result[1]) equalCount++;
        }
        System.out.println("Total runs: " + REPEAT_COUNT + " Non equal: " + (REPEAT_COUNT - equalCount));
    }
}
