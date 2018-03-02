package leetcodeii.Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Erebus on 2/15/18.
 */
public class SubsetII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        subset(nums, 0, res, new ArrayList<>());
        return res;

    }
    /**
     input=1,2,2,3, res=[]
     1 -> [],[1],[1,2],[1,2,2],[1,2,2,3], missing [1,3], [1,2,3]
     2 -> [2],[2,2],[2,3]
     2 skip repeated num
     3 -> [3]
     */
    private void subset(int [] nums, int idx, List<List<Integer>> res, List<Integer> tmp){
        List<Integer> ls = new ArrayList<>();
        ls.addAll(tmp);
        res.add(ls);

        if(idx>=nums.length){
            return;
        }

        for(int i=idx; i<nums.length; i++){
            if(i!=idx && nums[i]==nums[i-1]){
                //skip the duplicate
                continue;
            }
            tmp.add(nums[i]);
            subset(nums, i+1, res, tmp);
            tmp.remove(tmp.size()-1);
        }
    }
}
