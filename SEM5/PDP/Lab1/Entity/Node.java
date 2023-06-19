package Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class Node {
    public ReentrantLock mutex = new ReentrantLock();
    private final ArrayList<Node> primaries = new ArrayList<>();
    private final ArrayList<Node> secondaries = new ArrayList<>();
    private int value = 0;

    private final int id;

    public Node(int id) {
        this.id = id;
    }

    public Node (int id, int value){
        this.id = id;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Node> getPrimaries() {
        return primaries;
    }

    public ArrayList<Node> getSecondaries() {
        List<Node> list = secondaries.stream().sorted(Node::compare).toList();
        System.out.println(list);
        return new ArrayList<Node>(list);
    }

    public void addPrimary(Node input){
        this.primaries.add(input);
    }

    public void addSecondary(Node secondary){
        this.secondaries.add(secondary);
        secondary.addPrimary(this);
        secondary.addValue(value);
    }

    public void addValue(int value){
        mutex.lock();

        this.value += value;
        this.getSecondaries().forEach(secondary -> {
            secondary.addValue(value);
        });

        mutex.unlock();
    }

    public int compare(Node n1) {
        return this.getId() - n1.getId();
    }
}
