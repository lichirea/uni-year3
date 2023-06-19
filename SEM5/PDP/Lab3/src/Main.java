import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {

    public static int[][] m1;
    public static int[][] m2;
    public static int[][] m3;


    public static void main(String[] args) throws InterruptedException {
        int size = 200;
        m1 = new int[size][size];
        m2 = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                m1[i][j] = i + 1;
                m2[i][j] = i + 1;
            }
        }

        m3 = new int[size][size];

        long startTime = System.nanoTime();
        usingTasks(size);
        long endTime = System.nanoTime();
        long tasksDuration = (endTime - startTime) / 10000;

        startTime = System.nanoTime();
        usingPool(size);
        endTime = System.nanoTime();
        long poolDuration = (endTime - startTime) / 10000;



        System.out.print("Tasks Time: " + tasksDuration + "\n");
        System.out.print("Pool Time: " + poolDuration + "\n");


//        f
    }

    public static void usingTasks(int size) throws InterruptedException {
        ArrayList<Thread> threads = new ArrayList<>();
        int nr_of_threads = 100;
        for (int i = 0; i < nr_of_threads; i++) {
            threads.add(new Thread(new Row(m1, m2, m3, i*2, size, 2)));
//            threads.add(new Thread(new Column(m1, m2, m3, i*2, size, 2)));
//            threads.add(new Thread(new KthElement(m1, m2, m3, size, i, 4)));
        }

        for (int i = 0; i < nr_of_threads; i++){
            threads.get(i).start();
        }

        for (int i = 0; i < nr_of_threads; i++){
            threads.get(i).join();
        }
    }

    public static void usingPool(int size) throws InterruptedException{
        int nr_of_threads = 4;
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(nr_of_threads);
        ArrayList<Runnable> threads = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(new Row(m1, m2, m3, i*2, size, 2)));
//            threads.add(new Thread(new Column(m1, m2, m3, i*2, size, 2)));
//            threads.add(new Thread(new KthElement(m1, m2, m3, size, i, 4)));
        }

        for (Runnable thread : threads)
            executor.execute(thread);
        executor.shutdown();
    }
}