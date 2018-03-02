package leetcodeii.Arrays;

/**
 * Created by Youming on 2/9/2018.
 */
public class SortColors75 {

    //one pass solution
    public void sortColors(int[] nums) {
        if(nums==null || nums.length<1){
            return;
        }
        //use one pass
        int p0 = 0;
        int p2 = nums.length-1;
        int i = 0;
        while(i<=p2){
            if(nums[i]==0){
                //find a zero, moves forward, append to the 0s.
                nums[i]=nums[p0];
                nums[p0]=0;
                p0++;
            }
            if(nums[i]==2){
                //find a 2, moves backward, pre-pend to the 2s
                nums[i]=nums[p2];
                nums[p2]=2;
                p2--;
                i--;
            }
            i++;
        }

    }
}
