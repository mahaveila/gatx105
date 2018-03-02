package leetcodeii.Arrays;

import leetcodeii.Tracker;

/**
 * Created by Erebus on 2/25/18.
 */
public class MaxProdSubarray implements Tracker{

    public int maxProduct(int[] nums) {
        if(nums==null||nums.length<1){
            return 0;
        }
        boolean localhit = false;
        int local = nums[0];
        int res = nums[0];
        for(int ii=1; ii<nums.length; ii++){
            int cur =nums[ii];
            cout("num: " + cur);
            if(cur<=nums[ii-1]+1&&cur>=nums[ii-1]-1){
                cout("  cur="+cur+", local updated "+local+"->"+local*cur);
                local *= cur;
            } else {
                //not consecutive
                if(localhit){
                    res = Math.max(res, local);
                    cout("  localhit, res="+res);
                    local=nums[ii];
                }else{
                    res = local;
                    localhit = true;
                    cout("  localhit first hit, res="+res);
                    local = nums[ii];
                }
            }
        }
        res = Math.max(res,local);
        cout("  final set res="+res);
        return res;
    }
    public static void main (String [] args){
        MaxProdSubarray m = new MaxProdSubarray();
        int[] in1 = {0,2};
        m.cout(m.maxProduct(in1));
    }
}
