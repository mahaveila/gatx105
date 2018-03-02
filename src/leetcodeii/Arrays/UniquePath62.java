package leetcodeii.Arrays;

/**
 * Created by Erebus on 2/4/18.
 */
public class UniquePath62 {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for(int ii = 0; ii<n; ii++){
            dp[0][ii] = 1;
        }
        for(int jj =0; jj<m; jj++){
            dp[m][0]=1;
        }

        for(int kk=1; kk<m; kk++){
            for(int ll=1; ll<n; ll++){
                //current steps = upper steps + left steps
                dp[kk][ll]=dp[kk-1][ll] + dp[kk][ll-1];
            }
        }
        return dp[m-1][n-1];
    }
}
