package leetcodeii.Arrays;

import leetcodeii.Tracker;

/**
 * Created by Erebus on 2/27/18.
 */
public class RotatedArray189 implements Tracker{

    /**
     * just use the reserver 3 times, the whole, the left, and the right
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        //re-assign the elements.
        k=k%nums.length;
        rotate(nums, 0, nums.length-1);
        rotate(nums, 0, k-1);
        rotate(nums, k, nums.length-1);
    }

    private void rotate(int [] nums, int st, int ed){
        while(st<ed){
            int tmp = nums[st];
            nums[st]=nums[ed];
            nums[ed]=tmp;
            st++;
            ed--;
        }
    }


    /**
     * doesn't work for len = 6 or other even numbers, cannot cover all the buckets
     * @param nums
     * @param k
     */
    public void rotateNotWork(int[] nums, int k) {
        //re-assign the elements.
        int len = nums.length;
        int o = k%len;
        //now [0] should be at [0+overhead]
        //function j= (i+overhead)%len, target j;

        int i=0;
        int idx = 0; //init target
        int val = nums[0];
        while(i++<len){

            int targetLocation = (idx + o)%len;
            int tmp_val = nums[targetLocation];
            cout(String.format("move [%d]%d to target location = [%d]=%d", idx, val, targetLocation, tmp_val));
            nums[targetLocation] = val; //update target location
            idx = targetLocation; //time to assign the value just pop-ed
            val = tmp_val;
        }
    }

    public static void main(String [] args){
        RotatedArray189 r = new RotatedArray189();
        int [] nums ={1,2,3,4,5,6};
//r.rotateNotWork(nums,2);
        r.cout(nums);
    }
}
