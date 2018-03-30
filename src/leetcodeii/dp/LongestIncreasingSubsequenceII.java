package leetcodeii.dp;

import leetcodeii.Tracker;

/**
 * Created by Erebus on 3/27/18.
 */
public class LongestIncreasingSubsequenceII implements Tracker{

    public int longestIncreasingContinuousSubsequenceII(int[][] A) {
        cout(A);
        // write your code here
        /** basic idea is to use DFS like a ski probleam
         ! And use memorized DP matrix to remember all the calculated results before.
         bl
         */
        int [][] dp = new int[A.length][A[0].length];
        int res = 0;
        for(int ii=0; ii<A.length; ii++){
            for(int jj=0; jj<A.length; jj++){
                res=Math.max(res, next(dp, ii, jj, A, A[ii][jj]));
                cout(dp);
            }
        }
        return res;
    }

    private int next(int[][] dp, int i, int j, int[][] A, int val){
        if(!validMove(i, j, A, val)){
            return 0;
        }
        if(dp[i][j]>1){
            //already went through
            return dp[i][j];
        }
        int topLeft = Math.max(next(dp, i, j-1, A, A[i][j]), next(dp, i-1, j, A, A[i][j]));
        int bottomRight = Math.max(next(dp, i+1, j, A, A[i][j]), next(dp, i, j+1, A, A[i][j]));
        //current counts as 1.
        dp[i][j] = Math.max(topLeft, bottomRight) + 1;
        return dp[i][j];
    }

    private boolean validMove(int i, int j, int [][] A, int val){
        if(i < 0 || j < 0 || i >= A.length || j >= A[0].length || A[i][j]<val){
            return false;
        }
        return true;
    }

    public static void main(String [] args){
        LongestIncreasingSubsequenceII l = new LongestIncreasingSubsequenceII();
        int [][] input = {{1,2,3,4,5,6},{14,15,16,17,18,8},{12,13,11,10,9,7}};
        l.cout(l.longestIncreasingContinuousSubsequenceII(input));
    }
}
