public class Row implements Runnable {
    private final int[][] m1;
    private final int[][] m2;
    private final int[][] m3;
    private final Integer row;
    private final Integer size;
    private final Integer rowNumbers;

    Row(int[][] m1, int[][] m2, int[][] m3, int row, int size, int rowNumbers) {
        this.m1 = m1;
        this.m2 = m2;
        this.m3 = m3;
        this.row = row;
        this.size = size;
        this.rowNumbers = rowNumbers;
    }

    @Override
    public void run () {
        for (int r = row; r < row + rowNumbers; r++) {
            for (int i = 0; i < size; i++) {
                this.m3[r][i] = this.calc(r, i);
            }
        }
    }

    public int calc(int a, int b){
        int result = 0;
        for (int x = 0; x < size; x++)
            result += m1[a][x] * m2[x][b];
        return result;
    }
}