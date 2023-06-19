public class Column implements Runnable {
    private final int[][] m1;
    private final int[][] m2;
    private final int[][] m3;
    private final Integer column;
    private final Integer size;
    private final Integer columnNumbers;

    Column(int[][] m1, int[][] m2, int[][] m3, int column, int size, int columnNumbers) {
        this.m1 = m1;
        this.m2 = m2;
        this.m3 = m3;
        this.column = column;
        this.size = size;
        this.columnNumbers = columnNumbers;
    }

    @Override
    public void run () {
        for (int c = column; c < column + columnNumbers; c++) {
            for (int i = 0; i < size; i++) {
                this.m3[i][c] = this.calc(c, i);
            }
        }
    }

    public int calc(int a, int b){
        int result = 0;
        for (int x = 0; x < size; x++)
            result += m1[x][a] * m2[b][x];
        return result;
    }
}