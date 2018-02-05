package leetcodeii.FourSeries;

import leetcodeii.Tracker;

/**
 * Created by Erebus on 8/13/17.
 */
public class CircularArrayLoop457 implements Tracker{

    /** TODO: check later for problem clarifications
     * doesn't work, [-1, -2, -3, -4, -5] expected to have no loop
     * @param nums
     * @return
     */
    public boolean circularArrayLoop(int[] nums) {
        //2, -1, 1, 2, 2
        //0,  1, 2, 3, 4
        //2,  0, 3, 5%5=0, 6%5=1
        //    ^-> could be any, no AND logic
        if(nums==null || nums.length<2){
            return true;
        }
        boolean positive = nums[0]>0;
        int index = 0;
        for(int ii =0 ; ii< nums.length; ii++){
            if(positive && nums[index]<0){
                cout("detect negative in positive");
                return false;
            } else if(!positive && nums[index]>0){
                cout("detect positive in negative");
                return false;
            }
            cout(String.format("index= %d, [%d]=%d", index, index, nums[index]));
//            cout(String.format("index: %d => %s", index, positive?(index+nums[index])%nums.length:(nums.length + (index+nums[index])%nums.length))%nums.length);
            index = positive?(index+nums[index])%nums.length:(nums.length + (index+nums[index])%nums.length)%nums.length;
            cout(String.format("current ii=%d, index= %d", ii, index));
        }
        return true;
    }

    public static void main(String [] args) {
        CircularArrayLoop457 c = new CircularArrayLoop457();
//        int[] input = {-2, 1, -1, -2, -2};
        int[] input = {0,  1, 2, 3, 4};

        /**
         * 0 + -2 = -2 % 5 = -2 + 5 = 3;
         * 3 + -2 = 1 % 5 = 1 + 5 = 6 --> error.
         */
        c.cout("result is " + c.circularArrayLoop(input));
    }
}
