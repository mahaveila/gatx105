package leetcodeii.ZeroSeires;

/**
 * Created by Erebus on 2/12/18.
 */
public class RemoveDuplicateII80 {
    /**
     * better impl
     * @param nums
     * @return
     */
    public int remove(int [] nums){
        int i = 0;
        for (int n : nums)
        //larger than last last one
        /**
         e.g. 1,1,3
         or, 1,2,3
         or, 2,2,3
         or, 1,3,3
         as long as 3 > 1 or 2, doesn't care if it's 1,2 or 3 in between.
         */
            if (i < 2 || n > nums[i-2])
                nums[i++] = n;
        return i;
    }

    /**
     * bad impl
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if(nums==null || nums.length<1){
            return 0;
        }
        int i = 0, j = 0, len = nums.length;
        int dup=1;
        while(j<nums.length){
            if(j==0){
                j++;
                continue;
            }
            if(nums[i]!=nums[j]){
                nums[++i]=nums[j];
                dup=1;
            } else {
                if(dup<2){
                    //appear only once before
                    nums[++i]=nums[j];
                }
                dup++;
            }
            j++;
        }
        return i+1;
    }
}
