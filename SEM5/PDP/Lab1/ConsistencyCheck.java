import Entity.Checker;


public class ConsistencyCheck extends Thread {
    @Override
    public void run() {
        for(int i = 0; i < 2; i++){
            Checker c = new Checker(Main.inputs);
            System.out.println("i will now look for errors in the graph....");
            boolean result = c.run();
            if(result)
                System.out.println("ITS ALL GOOD");
            else
                System.out.println("there are errors in the graph");
        }

    }
}
