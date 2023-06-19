import java.util.ArrayList;

public class Producer extends Thread {
    public UnboundedBuffer buffer;
    public ArrayList<Integer> vector1, vector2;

    public Producer(UnboundedBuffer buffer, ArrayList<Integer> vector1, ArrayList<Integer> vector2) {
        this.buffer = buffer;
        this.vector1 = vector1;
        this.vector2 = vector2;
    }

    @Override
    public void run() {
        for (int i = 0; i < vector1.size(); i++) {
            try {
                int product = vector1.get(i) * vector2.get(i);
                System.out.println("sending to buffer product between " +
                        vector1.get(i) + " and " + vector2.get(i) + " = " + product);
                buffer.put(product);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println("finished adding products to buffer");
    }
}