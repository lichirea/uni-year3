public class KthElement implements Runnable {
    private final int[][] m1;
    private final int[][] m2;
    private final int[][] m3;
    private final Integer size;
    private final Integer stepSize;
    private final Integer startingIndex;


    KthElement(int[][] m1, int[][] m2, int[][] m3, int size, int si, int ss) {
        this.m1 = m1;
        this.m2 = m2;
        this.m3 = m3;
        this.size = size;
        this.startingIndex = si;
        this.stepSize = ss;

    }

    @Override
    public void run () {
        int index = startingIndex;
        int row = index / size;
        int column = index % size;
        while (row < size){
            this.m3[row][column] = this.calc(row, column);
            index += stepSize;
            row = index / size;
            column = index % size;
        }

    }

    public int calc(int a, int b){
        int result = 0;
        for (int x = 0; x < size; x++)
            result += m1[a][x] * m2[x][b];
        return result;
    }
}