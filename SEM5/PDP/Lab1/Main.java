import Entity.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
    public static ArrayList<Node> inputs = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        onInit();
        generateInputs();
    }

    private static void onInit(){
        Node p1 = new Node(1, 7);
        inputs.add(p1);
        Node p2 = new Node(2, 2);
        inputs.add(p2);
        Node p3 = new Node(3, 3);
        inputs.add(p3);
        Node p4 = new Node(4, 6);
        inputs.add(p4);

        Node s1 = new Node(10);//9
        Node s2 = new Node(11);//5

        //9 = 7 + 2
        p1.addSecondary(s1);
        p2.addSecondary(s1);

        //5 = 2 + 3
        p2.addSecondary(s2);
        p3.addSecondary(s2);

        Node s3 = new Node(12); //20

        // 20 = 9 + 5 + 6
        p4.addSecondary(s3);
        s1.addSecondary(s3);
        s2.addSecondary(s3);
    }

    private static void generateInputs() throws InterruptedException {
        List<NewInputGenerator> threadList = new ArrayList<>();
        for(int i = 1; i < 100; i++) {
            NewInputGenerator t = new NewInputGenerator();
            threadList.add(t);
            t.start();
        }
        runConsistencyCheck();
        threadList.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static void runConsistencyCheck() throws InterruptedException {
        ConsistencyCheck t = new ConsistencyCheck();
        t.start();
        t.join();
    }

}
