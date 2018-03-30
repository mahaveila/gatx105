package leetcodeii.dp;

import leetcodeii.Tracker;

import java.util.Arrays;

/**
 * Created by Erebus on 3/26/18.
 */
public class CoinsInALineII implements Tracker{

    public boolean firstWillWin(int[] values) {
        // write your code here
        /**
         dp[i] = how many has been taken before i th move

         dp[0]=0;
         dp[1]=values[len-1]
         dp[2]=values[len-2]+values[len-1]
         -------first hand win boundary----
         -------second hand strategy
         dp[3]=values[len-3]+values[len-2];

         say, i=4: 4 coins left, and now it's first hands' turn
         1,2,3,4
         1st take 1        1st take 2
         left           right
         dp[i-1]      dp[i-2]
         2,3,4          3,4
         :now it's second hands' turn
         take 2,3    2       3,4     3

         left:4,      3,4     0      4  to first hand.

         dp[i-3]          dp[i-4]  dp[i-3]
         dp[i-2]

         dp[1]    d[2]     dp[0]   dp[1]  <= inited

         therefore, 2nd player will pick smaller side for 1st player given left situation:
         i.e. left= Math.min(dp[i-3], dp[i-2]);
         right = Math.min(dp[i-4], dp[i-3]);
         and at i, player 1 needs to pick the larger side for himself

         dp[i]= Math.max(left+values[i], right+values[i]+values[i+1]);
         */
        int len = values.length;
        if(len<3){
            return true;
        }
        int total = Arrays.stream(values).reduce(0, (a, b)->a+b);
        int[] dp = new int[values.length+1];
        dp[0]=0;
        dp[1]=values[len-1];
        dp[2]=values[len-2]+values[len-1];
        dp[3]=values[len-3]+values[len-2];
        for(int i=4; i<=len; i++){
            cout("i = "+ i);
            int left = Math.min(dp[i-3], dp[i-2]);
            int right = Math.min(dp[i-4], dp[i-3]);
            cout(String.format("dp i-2 = %d, dp i-3 = %d, dp i-4 = %d", dp[i-2], dp[i-3], dp[i-4]));
            cout("left="+left+", right="+right);
            dp[i] = Math.max(left+values[len-i], right+values[len-i] + values[len-i+1]);
            cout("dp[i]="+dp[i]);
        }
        cout(dp);
        return dp[len]>total/2;
        /**
         * for case 1,2,4,8
         i=4
         dp0=0, dp1=8, dp2=4+8=12, dp3=2+4=6
         left=min(dpi-3, dpi-2)=min(dp1,dp2)=8
         right=min(dpi-3,dpi-4)=min(dp0,dp1)=0
         dp4=max(left+1, right+1+2)=max(9,3)=9.
         total=15,/2=7
         9>7-> true

         */
    }

    public static void main(String [] args){
        CoinsInALineII c2 = new CoinsInALineII();
        int [] in = {1,2,4,8};
        c2.cout(c2.firstWillWin(in)?"yes":"no");
    }
}
