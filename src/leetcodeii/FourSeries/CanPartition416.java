package leetcodeii.FourSeries;

import leetcodeii.Tracker;

import java.util.Arrays;

/**
 * Created by Youming on 4/6/2017.
 */
public class CanPartition416 implements Tracker{

    public boolean canPartition(int[] nums) {
        /**
         * false condition:
         * nums == null, nums is empty*, nums length = 1, sum is odd
         * null/empty will be wrapped in steam
         */
        int sum = Arrays.stream(nums).reduce(0, (s, i)-> s+= i);
        if(nums==null || nums.length==1 || sum%2!=0){
            return false;
        }
        int[] dp = new int[sum/2 + 1];
        dp[0] = 1;
        for(int n: nums){
            for(int ii=sum/2; ii>=n; ii--){
                dp[ii] = dp[ii-n];
            }
            log(dp);
            if(dp[sum/2]>0){
                return true;
            }
        }
        return false;
    }

    /**
     * TODO: dp[i] = dp[i] || dp[i-num];, use boolean array instead of integer array, since it's asking for just the boolean result
     */
    public void solution2(){

    }

    public static void main(String [] args){
        int [] input = {1,5,11,5};
        CanPartition416 c = new CanPartition416();
        c.canPartition(input);
    }
}
