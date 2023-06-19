import java.util.LinkedList;
import java.util.Queue;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class UnboundedBuffer {
    private final Lock mutex = new ReentrantLock();
    private final Condition conditionVariable = mutex.newCondition();
    private final Queue<Integer> queue = new LinkedList<Integer>();

    public UnboundedBuffer() {}

    public void put(int value) throws Exception {
        mutex.lock();
        try {
            queue.add(value);
            System.out.println("added to queue: " + value);
            conditionVariable.signal();
        } finally {
            mutex.unlock();
        }
    }

    public int get() throws Exception {
        mutex.lock();
        try {
            if (queue.size() == 0) {
                System.out.println("waiting for something to be added into the buffer");
                conditionVariable.await();
            }

            int value = queue.poll();
            System.out.println("polled out of queue: " + value);
            conditionVariable.signal();
            return value;
        } catch (Exception e){
            e.printStackTrace();
            return 0;
        } finally {
            mutex.unlock();
        }
    }
}