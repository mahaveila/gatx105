package leetcodeii.ZeroSeires;

import leetcodeii.Tracker;

/**
 * Created by Erebus on 2/5/18.
 */
public class UniquePathWithObstacles63 implements Tracker{

    public int uniquePathsWithObstacles(int[][] grid) {

            if(grid.length<1 || grid[0].length<1 || grid[0][0]==1){
                return 0;
            }
            boolean blocked = false;
            for(int ii=0; ii<grid.length; ii++){
                if(blocked){
                    grid[ii][0]=0;
                } else {
                    if(grid[ii][0]==1){
                        grid[ii][0]=0;
                        blocked=true;
                    } else {
                        grid[ii][0]=1;
                    }
                }
            }
            blocked = false;
            cout(grid);
            for(int jj=1; jj<grid[0].length; jj++){
                if(blocked){
                    grid[0][jj]=0;
                } else {
                    if(grid[0][jj]==1){
                        grid[0][jj]=0;
                        blocked=true;
                    } else {
                        grid[0][jj]=1;
                    }
                }
            }
            cout(grid);
            for(int m=1; m<grid.length; m++){
                for(int n=1; n<grid[0].length; n++){
                    if(grid[m][n]==1){
                        grid[m][n]=0;
                    } else {
                        grid[m][n]=grid[m-1][n]+grid[m][n-1];
                    }
                }
            }
            cout(grid);
            return grid[grid.length-1][grid[0].length-1];

    }

    //better solution
    public int uniquePathsWithObstaclesDP(int[][] obstacleGrid) {
        int width = obstacleGrid[0].length;
        int[] dp = new int[width];
        dp[0] = 1;
        for (int[] row : obstacleGrid) {
            for (int j = 0; j < width; j++) {
                if (row[j] == 1)
                    dp[j] = 0;
                else if (j > 0)
                    dp[j] += dp[j - 1];
            }
        }
        return dp[width - 1];
    }

    public static void main (String [] args){
        UniquePathWithObstacles63 up = new UniquePathWithObstacles63();
//        int[][] input = {{0},{1},{0},{0},{0},{0},{0},{0},{0},{0},{0},{0},{1},{0},{0},{0},{0},{1},{0},{0},{0},{0},{0},{0},{0},{0},{0},{0},{1},{1},{0},{1},{0},{0},{1},{0},{0},{0},{0},{1}};
        int[][] input = {{0,0},{1,0}};
        up.cout(up.uniquePathsWithObstacles(input));
    }

}
