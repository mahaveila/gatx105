package leetcodeii.Arrays;

import leetcodeii.Tracker;

/**
 * Created by Erebus on 4/29/18.
 */
public class NumMatrix implements Tracker{

    public static void main(String [] args){

        int[][] matrix = {{3,0,1,4,2},{5,6,3,2,1},{1,2,0,1,5},{4,1,0,1,7},{1,0,3,0,5}};
        NumMatrix n = new NumMatrix(matrix);
    }

    int [][] BITs;
    int [][] M;

    public NumMatrix(int[][] matrix) {
        if(matrix==null || matrix.length < 1 || matrix[0].length < 1){
            //error handling.
            M = new int[0][0];
            BITs = new int[0][0];
        } else {
            this.M = new int[matrix.length][matrix[0].length];
            //BIT has 0 in the beginning for place holding.
            this.BITs = new int[matrix.length][matrix[0].length+1];
            initTree(matrix);
        }

    }

    private void initTree(int[][] matrix){
        for(int ii = 0; ii < matrix.length; ii ++){
            for(int jj = 0; jj < matrix[0].length; jj ++){
                update(ii, jj, matrix[ii][jj]);
            }
        }
    }

    public void update(int row, int col, int val) {
        int origVal = M[row][col];
        int diff = val - origVal;
        M[row][col] = val;
        // for(int ii = row; ii < M.length; ii++){
        //update each row's BIT
        updateBIT(row, col, diff);
        // }
    }

    private void updateBIT(int row, int col, int diff){
        int[] BIT = BITs[row];
        int x = col+1; //match the BIT[x] has placeholder [0]
        //SWEET: use col len for x
        while(x<=M[0].length){
            BIT[x] += diff;
            x += lowBit(x);
        }
    }

    private int lowBit(int x){
        return x & (-x);
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        //inclusive for row1 and row2
        for(int row = row1; row <= row2; row++){
            int [] BIT = BITs[row];
            //exclusive the col1
            sum += getPreSum(col2, BIT) - getPreSum(col1-1, BIT);
        }
        return sum;
    }

    private int getPreSum(int col, int [] BIT){
        int x = col + 1; //match the BIT index
        int sum = 0;
        while(x>0){
            sum += BIT[x];
            x -= lowBit(x);
        }
        return sum;
    }

}
