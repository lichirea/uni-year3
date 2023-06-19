package Entity;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Checker {
    private final ArrayList<Node> inputs;

    public Checker(ArrayList<Node> inputs) {
        this.inputs = inputs;
    }

    public boolean run() {
        lockAll();

        boolean result = checkNodes();

        unlockALl();

        return result;
    }

    public void lockAll() {
        inputs.forEach(node -> {
            node.mutex.lock();
            lockSecondaries(node);
        });
    }

    public void lockSecondaries(Node node) {
        node.getSecondaries().forEach(secondary -> {
            secondary.mutex.lock();
            lockSecondaries(secondary);
        });
    }

    public boolean checkNodes() {
        AtomicBoolean result = new AtomicBoolean(true);

        inputs.forEach(node -> {
            if(!check(node)) result.set(false);
        });

        return result.get();
    }

    public boolean check(Node node) {
        AtomicBoolean result = new AtomicBoolean(true);

        if(node.getSecondaries().size() > 0) {
            if (node.getPrimaries().size() > 0) {
                int sum = node.getPrimaries().stream().map(Node::getValue).reduce(0, Integer::sum);
                result.set(sum == node.getValue());
            }
            if(result.get()) {
                node.getSecondaries().forEach(secondary -> {
                    if(!check(secondary)) result.set(false);
                });
            }
        }
        return result.get();
    }

    public void unlockALl() {
        inputs.forEach(node -> {
            node.mutex.unlock();
            unlockSecondaries(node);
        });
    }

    public void unlockSecondaries(Node node) {
        node.getSecondaries().forEach(secondary -> {
            secondary.mutex.unlock();
            unlockSecondaries(secondary);
        });
    }
}
