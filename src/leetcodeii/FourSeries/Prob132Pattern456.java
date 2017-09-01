package leetcodeii.FourSeries;

import leetcodeii.Tracker;

import java.util.Arrays;

/**
 * Created by Erebus on 8/13/17.
 */
public class Prob132Pattern456 implements Tracker<Integer, Integer>{

    public boolean find132pattern(int[] nums) {
        if(nums==null || nums.length<3){
            return false;
        }
        int [] left = new int[nums.length];
        left[0] = nums[0];
        for (int ii = 1 ; ii< nums.length; ii++){
            left[ii] = Math.min(nums[ii-1], left[ii-1]);
        }

        for(int j = nums.length-1, top = nums.length; j>=0; j--){
            if(nums[j]<=left[j]){//skip numbers that are not greater than their left, //e.g. skip -4 cuz -4 < 0
                continue;
            }
            while (top < nums.length && left[top] <= left[j]){
                //prevent 1,0,1,-4,-3 to be true at j=2
                /**
                 * see below
                 */
                top++;
            }
            if(top<nums.length && nums[j]>left[top]){
                return true;
            }
            left[--top] = nums[j];
        }
        return false;
    }
    /**
     *         [1,0,1,-4,-3] :
     * left => [1,1,0,0,-4] : iterations
     * j=4; -3 > -4, top at length, left = [1,1,0,0,-3], top = 4
     * j=3; -4 < 0, skip, top =4
     * j=2; 1 > 0, and 1 > left[4] = -3, prev number
     *  But, we also need -3 > 0 as well. to keep this, whenever we find left[top] (-3) <= left[j] (0):
     *  top moves to end (for put 0 @ end to ensure 1*2 pattern, or till we reach left[top]>left[j]
     */


    public static  void main (String [] args){
        Prob132Pattern456 f123 = new Prob132Pattern456();
        int [] test = {1,0,1,-4,-3};
        f123.cout(""+f123.find132pattern(test));
    }
}
