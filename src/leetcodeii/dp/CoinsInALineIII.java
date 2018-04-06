package leetcodeii.dp;

import leetcodeii.Tracker;

import java.util.Arrays;

/**
 * Created by Erebus on 4/1/18.
 */
public class CoinsInALineIII implements Tracker{

    public static void main(String [] args){
        int [] input = {1,2,3,4,5,6,7,8,13,11,10,9};
        CoinsInALineIII c= new CoinsInALineIII();
        c.firstWillWin(input);
    }


    /**
     * @param values: a vector of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
        // write your code here

        /**
         The idea is:

         either pick from begin or the end

         now dp[i] is no longer the number of coin left any more
         instead use dp[i][j], stands for the coins left are from position [0] to [len]

         final result = dp[0][len] > total/2

         init condition
         dp[i][i] = 0, starts at i, but already ends at i, nothing left
         dp[i][i+1] = values[i], only coin at i is left

         dp function: (From p1's point of view)
         when i till i+1 left, player 1 take them all
         when i till i+2 left, player 2's turn
         p2 choose the bigger one, left p1 the smaller one
         dp[i][i+2] = Math.min(dp[i][i+1], dp[i+1][i+2]);
         whatever left now in dp[i][i+2] is for p1.
         p2 takes bigger one, so p1 gets smaller one, use min.
         when i till i+3 left, player 1's turn, choose bigger one.
         dp[i][i+3] = Math.max(dp[i][i+2], dp[i+1][i+3]);
         i.e. player one picked left or right, and min result from p2's turn, respectively.
         dp[i][i+3] = Maht.max(
         //player 1 choose right, player 2 left the min for that situation for p1
         Math.min(dp[i][i+1], dp[i+1][i+2]),
         //player 1 choose left, p2 left corresponding min for p1
         Math.min(dp[i+1][i+2], dp[i+2][i+3])
         )
         in above case j = i+3, i = j-3
         i.e dp[i][j] = Math.max(
         Math.min(dp[i][j-2], dp[i+1][j-1]),
         Math.min(dp[i+1][j-1], dp[i+2][j])
         );

         KEY: remember to add the coin value that has been picked, i.e.
         i.e dp[i][j] = Math.max(
         Math.min(dp[i][j-2], dp[i+1][j-1]) + values[j-1],
         Math.min(dp[i+1][j-1], dp[i+2][j]) + values[i];
         );
         */
        int n = values.length;
        int [][] dp = new int[n][n+1];
        for(int ii = 0; ii < n; ii ++){
            //only coin ii is left
            dp[ii][ii+1] = values[ii];
            if(ii+1<n){
                dp[ii][ii+2] = Math.max(values[ii], values[ii+1]);
            }
        }
        cout(values);
        cout(dp);
        //k stands for the how many coins left.
        for(int k = 3; k <= n; k++){
            //k min = 3,  ii max = n-3, if 3 left, jj = ii + k = n-3 + 3 = n
            for(int ii = 0; ii <= n - k ; ii ++){
                int jj = ii + k; //jj max is n, ii+k<n,
                dp[ii][jj] = Math.max(
                        Math.min(dp[ii][jj-2], dp[ii+1][jj-1]) + values[jj-1],
                        Math.min(dp[ii+1][jj-1], dp[ii+2][jj]) + values[ii]);

            }
        }

        cout(dp);
        int sum = Arrays.stream(values).reduce(0, (a, b)-> a + b);
        return dp[0][n] > sum/2;
    }

}
