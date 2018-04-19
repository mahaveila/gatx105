package leetcodeii.Arrays;

import leetcodeii.Tracker;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Erebus on 4/14/18.
 */
public class submatrixSum implements Tracker{

    public static void main(String [] args){
        submatrixSum s = new submatrixSum();
        int[][] input = {{1,5,7},{3,7,-8},{4,-8,9}};
        int [][] input2 = {{1,1,1,1,1,1,1,1,1,1,1,-10,1,1,1,1,1,1,1,1,1,1,1}};

        s.cout(s.submatrixSumERROR(input2));
    }

    /*
     * @param matrix: an integer matrix
     * @return: the coordinate of the left-up and right-down number
     */
    public int[][] submatrixSumERROR(int[][] matrix) {
        // write your code here

        //the idea is to make use of the coordinates of right bottom of a matrix

        //ERROR: the one D array is not enough, it would lose the:
        //case:   1 1 1 1 1 1 1 1 1 1 -> jj=9, presum = 10
        //2nd row 9                      -> 1+9 = 10, appreared, but they cannot form a matrix.
        //the col sum for different rows
        // int [] col_sum = new int[n];


        int m = matrix.length;
        int n = matrix[0].length;

        int [][] msum = new int[m][n];
        int [][] res = new int[2][2];
        for(int ii = 0; ii < m; ii ++){
            for(int jj = 0; jj < n; jj ++){
                //FIXME:                                                     SWEET, use matrix ii-1 not msum ii-1
                msum[ii][jj] = (jj>0?msum[ii][jj-1]:0) + matrix[ii][jj] + (ii>0?msum[ii-1][jj]:0) - (ii>0&&jj>0?msum[ii-1][jj-1]:0);

                cout((jj>0?msum[ii][jj-1]:0) +" + "+ matrix[ii][jj] +" + "+ (ii>0?matrix[ii-1][jj]:0) + " = " + msum[ii][jj]);
            }
        }
        cout(matrix);
        cout(msum);

        //using hashmap cannot accurately find the 111111 & 1,5 case
        //so use 2 lines to scan the presum array;

        for(int ii = 0; ii < m; ii ++){
            //it's missing row ii itself
            for(int jj = ii; jj < m; jj ++){
                cout("line " + ii + " and " + jj);
                Map<Integer, Integer> diffMap = new HashMap<>();
                //init point should be ii-1, -1;
                diffMap.put(0, -1);
                //scan columns using two rows.
                for(int kk = 0; kk < n; kk ++){
                    int diff = msum[jj][kk] - (ii>0?msum[ii-1][kk]:0);
                    cout(String.format("  [%d][%d] = %d  -  [%d][%d] = %d   => diff: %d",jj,kk, msum[jj][kk],ii,kk,(ii>0?msum[ii-1][kk]:0),diff));
                    if(diffMap.containsKey(diff)){
                        int [] st = {ii, diffMap.get(diff)+1};
                        res[0] = st;
                        res[1][0] = jj;
                        res[1][1] = kk;
                        cout("find result at: " + stringfy(res));
                        return res;
                    }
                    diffMap.put(diff, kk);
                }
            }
        }
        return res;
    }

}
