package leetcodeii.FourSeries;

import java.util.Arrays;

/**
 * Created by Erebus on 6/3/17.
 */
public class MatchesSquare473 {


    public boolean makesquare(int[] nums) {
        if(nums==null || nums.length < 4){
            return false;
        }
        int sum = Arrays.stream(nums).sum();
        if(sum%4!=0){
            return false;
        }
        // Arrays.sort(nums);
        // reverse(nums);
        return dfs(nums, 0, new int[4], sum/4);
    }

    private boolean dfs(int [] nums, int index, int [] matches, int sum){
        //return condition
        if(index>=nums.length){
            if(matches[0]==sum && matches[1]==sum && matches[2]==sum && matches[3]==sum){
                return true;
            } else {
                return false;
            }
        }

        for(int ii = 0 ; ii<4; ii++){
            //reduce loop
            if(matches[ii]+nums[index]>sum){
                continue;
            }
            matches[ii] += nums[index];
            if(dfs(nums, index+1, matches, sum)){
                return true;
            }
            //recover
            matches[ii] -= nums[index];
        }
        return false;
    }
}
