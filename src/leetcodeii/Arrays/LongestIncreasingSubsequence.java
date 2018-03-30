package leetcodeii.Arrays;

import leetcodeii.Tracker;

/**
 * Created by Erebus on 3/25/18.
 */
public class LongestIncreasingSubsequence implements Tracker{

    public int longestIncreasingSubsequence(int[] nums) {
        // write your code here

        //dp[i], longest ending at i
        //so for i+1,
        //compare nums[i+1] with d[j]: j range 0~i,
        //if nums[i+1]>nums[i], then dp[i+1] = max out of dp[j] + 1;
        int dp[] = new int[nums.length];
        int max = 1;
        for(int ii = 0; ii<nums.length; ii++){
            dp[ii]=1;
            if(ii>0){
                for(int jj=0; jj<ii; jj++){
                    if(nums[jj]<nums[ii]){
                        //increasing sequence
                        dp[ii]=Math.max(dp[ii], dp[jj]+1);
                        if(dp[ii]==10){
                            cout(ii);
                        }
                        max = dp[ii];
                    }
                }
            }
        }
        cout(nums);
        cout(dp);
        return nums.length<1?0:max;
    }

    public static void main(String [] args){
        LongestIncreasingSubsequence l = new LongestIncreasingSubsequence();
        int [] input = {88,4,24,82,86,1,56,74,71,9,8,18,26,53,77,87,60,27,69,17,76,23,67,14,98,13,10,83,20,43,39,29,92,31,0,30,90,70,37,59};
        l.cout(l.longestIncreasingSubsequence(input));
    }
}
