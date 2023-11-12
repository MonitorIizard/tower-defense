package helperMethod;

import java.util.ArrayList;

public class Utilz {

    public static int[][] ArrayListTo2Dint(ArrayList<Integer> list, int ySize, int xSize) {
        int[][] newArr = new int[ySize][xSize];

        for ( int i = 0; i < ySize; i++) {
            for ( int j = 0; j < xSize; j++ ){
                int index = i * xSize + j;
                newArr[i][j] = list.get(index);
            }
        }
        return newArr;
    }

    public static int[] TwoDto1Dint(int[][] list) {
        int row = list.length;
        int col = list[0].length;

        int[] next = new int[row * col];

        for ( int i = 0; i < row; i++) {
            for ( int j = 0; j < col; j++ ){
                int index = i * col + j;
                next[index] =  list[i][j];
            }
        }

        return next;
    }

    public static int GetHypoDistance(float x1, float x2, float y1, float y2) {
        float xDiff = Math.abs(x1 - x2);
        float yDiff = Math.abs(y1 - y2);

        return (int) Math.hypot(xDiff, yDiff);
    }
}
