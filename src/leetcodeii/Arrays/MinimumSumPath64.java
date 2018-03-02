package leetcodeii.Arrays;

/**
 * Created by Erebus on 2/6/18.
 */
public class MinimumSumPath64 {

    /**
     * This method use extra array, but without modifying the input grid array
     *
     * An alternative approach could be using the input grid directly.
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        if(grid.length<1 || grid[0].length<1){
            return 0;
        }
        int w = grid[0].length;
        int[] dp = new int[w];

        boolean init =false;
        for(int[] row : grid){
            if(!init){
                dp[0]=row[0];
                for(int i=1; i<w; i++){
                    dp[i] = dp[i-1]+row[i];
                }
                init = true;
            } else {
                dp[0] = dp[0]+row[0];
                for(int i=1; i<w; i++){
                    //get the min either from left or top(pre dp)
                    dp[i] = Math.min(row[i]+dp[i-1], row[i]+dp[i]);
                }
            }
        }
        return dp[w-1];
    }

}
