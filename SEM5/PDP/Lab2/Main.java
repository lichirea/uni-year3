import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> vector1 = new ArrayList<>();
        for(int i = 1; i <= 100; i++) {
            vector1.add(5);
        }

        ArrayList<Integer> vector2 = new ArrayList<>();
        for(int i = 1; i <= 100; i++) {
            vector2.add(2);
        }

        UnboundedBuffer buffer = new UnboundedBuffer();

        Consumer consumer = new Consumer(buffer,vector1.size());
        consumer.start();
        Producer producer = new Producer(buffer,vector1, vector2);
        producer.start();
        try {
            consumer.join();
            producer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}