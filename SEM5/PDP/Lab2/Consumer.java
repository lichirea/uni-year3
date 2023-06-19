public class Consumer extends Thread{
    private final UnboundedBuffer buffer;
    private final int vectorSize;
    private int sum = 0;

    public Consumer(UnboundedBuffer buffer, int vectorSize){
        this.buffer = buffer;
        this.vectorSize = vectorSize;
    }

    @Override
    public void run() {
        for(int i = 0; i < vectorSize; i++){
            try {
                sum = sum + buffer.get();
                System.out.println("new sum: " + sum);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println("sum: " + sum);
    }
}