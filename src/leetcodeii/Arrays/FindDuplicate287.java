package leetcodeii.Arrays;

import leetcodeii.Tracker;

/**
 * Created by Erebus on 2/28/18.
 */
public class FindDuplicate287 implements Tracker{

    public int findDuplicate(int[] nums) {
        cout(nums);
        //array can be represented as a linkedlist [2]=6 -> [6]=3 -> [3]=n, etc
        if(nums==null||nums.length<2){
            return -1;
        }
        //0 is never gonna be pointed back since all value range (1:n), so 0 must be outside of circle
        int slow=nums[0];
        int fast=nums[nums[0]];
        while(nums[slow]!=nums[fast]){
            cout(String.format("slow=%d, fast=%d", slow, fast));
            slow=nums[slow];
            fast=nums[nums[fast]];
        }
        cout("meet at idx: " + slow + ": " + nums[slow]);
        fast=0;
        //!! they needs to move together, i.e. fast is one step ahead of 0, and thus the slow needs to move one step ahead of the meeting point.
        while(nums[slow]!=nums[fast]){
            cout(String.format("slow=%d, fast=%d", slow, fast));
            slow=nums[slow];
            fast=nums[fast];
        }
        //they must meet, if there is a duplicate
        return nums[slow];
    }

    public static void main(String [] args){
        FindDuplicate287 f = new FindDuplicate287();
        int[] in1 = {2,5,9,6,9,3,8,9,7,1};
        f.cout(f.findDuplicate(in1));
    }

}
