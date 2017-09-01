package leetcodeii.FourSeries;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erebus on 8/19/17.
 */
public class FindAllNumbersDisappearedInAnArray448 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        for(int ii = 0; ii<nums.length; ii++){
            //check for seen negative index, make them positive
            int val = Math.abs(nums[ii])-1;
            //check for unseeing, only mark those
            if(nums[val]>0){
                nums[val]=-nums[val];
            }
        }
        List<Integer> missing = new ArrayList<>();
        for(int jj = 0; jj<nums.length; jj++){
            if(nums[jj]>0){
                missing.add(jj+1);
            }
        }
        return missing;
    }
}
