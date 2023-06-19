import Entity.Node;

import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

public class NewInputGenerator extends Thread {
    @Override
    public void run() {
        int i = 0;
        while(i < 1){
            int randomNode = ThreadLocalRandom.current().nextInt(0, Main.inputs.size());
            Node node = Main.inputs.get(randomNode);

            int newValue = ThreadLocalRandom.current().nextInt(0, 20);
            int difference = newValue - node.getValue();
            int oldValue = node.getValue();
            node.addValue(difference);
            System.out.println("input node " + oldValue + " changed value to " + newValue);
            i++;
        }
    }
}