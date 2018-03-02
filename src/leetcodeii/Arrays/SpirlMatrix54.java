package leetcodeii.Arrays;

import leetcodeii.Tracker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erebus on 1/30/18.
 */
public class SpirlMatrix54 implements Tracker{

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if(matrix==null || matrix.length<1 || matrix[0].length<1){
            return res;
        }
        int M = matrix.length;
        int N = matrix[0].length;
        //limit M/2, N/2;
        int row = 0;
        int col = 0;
//could be easier if use start and end, instead of calculate /2 situation.
        while((row<M/2 || (row==M/2 && M%2!=0)) && (col<N/2 || (col==N/2 && N%2!=0))){
            cout(String.format("\n>> begin loop: M=%d, N=%d, row = %d, col = %d, rowEnd=%d, colEnd=%d", M, N, row, col, M-row-1 ,N-col-1));

            if(row==M-row-1){
                //only 1 row
                for(int jj=col; jj<=N-col-1; jj++){
                    res.add(matrix[row][jj]);
                }
            } else if(col==N-col-1){
                //only 1 col
                for(int ii=row; ii<=M-row-1; ii++){
                    res.add(matrix[ii][N-col-1]);
                }
            } else {

                for (int jj = col; jj < N - col - 1; jj++) {
                    res.add(matrix[row][jj]);
                }
                cout(res);
                for (int ii = row; ii < M - row - 1; ii++) {
                    res.add(matrix[ii][N - col - 1]);
                }
                cout(res);
                for (int jj = N - col - 1; jj > col; jj--) {
                    res.add(matrix[M - row - 1][jj]);
                }
                cout(res);
                for (int ii = M - row - 1; ii > row; ii--) {
                    res.add(matrix[ii][col]);
                }
                cout(res);

            }
            row++;
            col++;
            cout(String.format("  << end loop: M=%d, N=%d, row = %d, col = %d, rowEnd=%d, colEnd=%d", M, N, row, col, M-row-1 ,N-col-1));
        }
        return res;
    }

    public static void main(String [] args){
        SpirlMatrix54 sm = new SpirlMatrix54();
        int[][] input = {{1,2,3},{4,5,6},{7,8,9}};
        sm.cout(sm.spiralOrder(input));
        int[][] input2 = {{1,2,3,4,5,6,7,8,9,10},{11,12,13,14,15,16,17,18,19,20}};
        sm.cout(sm.spiralOrder(input2));
    }
}
