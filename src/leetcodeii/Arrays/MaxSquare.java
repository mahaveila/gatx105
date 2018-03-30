package leetcodeii.Arrays;

import leetcodeii.Tracker;

import javax.sound.midi.Track;

/**
 * Created by Erebus on 3/25/18.
 */
public class MaxSquare implements Tracker {

    public int maxSquare(int[][] matrix) {
        // write your code here
        //would be too complicated if using maxRectangle method
        // int max = 0;
        // int [] row = new int[matrix[0].length];
        // for(int [] r : matrix){
        //     for(int j = 0; j < row.length; j++){
        //         row[j] = (r[j]==0? 0 : row[j]+1);
        //     }
        //     max = Math.max(findS(row), max);
        // }
        // return max;
        int area = 0;
        cout(matrix);

        int[][] dp = new int[matrix.length][matrix[0].length];
cout(dp);
        for(int i = 0; i < matrix.length; i ++){
            for(int j = 0; j < matrix[0].length; j++){
                if(i==0 || j==0){
                    //starting from 1 because the first row and column automatically matchs
                    dp[i][j] = matrix[i][j];
                    area = Math.max(area, dp[i][j]);
                    continue;
                }
                /*
                basic idea:
                if matrix[i][j] = 0, dp[i][j]=0;
                else：
                check left and upper part:
                dp[i][j] = 1 + min(upper, left);
                dp stands for the max len to form a square at cur i,j
                and cur i,j is the right corner of the square.
                also needs to check [i-1][j-1]
                i.e.
                1 0 1 0 0
                1 0 1 1 1
                1 1 1 1 1
                1 0 0 1 0
                will become
                1 0 1 0 0
                1 0 1 1 1
                1 1 1 2 1
                1 0 0 1 0
                then calculate the area.
                */
                if(matrix[i][j]==0){
                    dp[i][j]=0;
                } else {
                    int side = Math.min(dp[i-1][j], dp[i][j-1]);
                    dp[i][j] = 1 + Math.min(side, dp[i-1][j-1]);
                    int a = dp[i][j] * dp[i][j];
                    cout(a);
                    area = Math.max(a, area);
                    cout(area);
                }
            }
        }
        cout(area);
        cout(dp);
        return area;
    }

    public static void main(String [] args){
        MaxSquare m = new MaxSquare();
        int [][] matrix = {{1,0,1,0,0},{1,0,1,1,1},{1,1,1,1,1},{1,0,0,1,0}};

        m.cout(m.maxSquare(matrix));
    }
}
