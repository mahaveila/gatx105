/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leetcodeii.FourSeries;

import leetcodeii.Tracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Erebus
 */
public class TargetSumI494 implements Tracker{
    //Time Limit Exceed
    public int waysOfSum(final int [] eles, final int target){
        List<Integer> sums = new ArrayList<>();
        return combine(sums, eles, 0, target);
    }

    //Time Limit Exceed
    private int combine(final List<Integer> sums, final int [] eles, final int index, final int target){
        if(index == eles.length){
            return (int) sums.stream().filter(e->target==e).count();
        } else {
            List<Integer> next = new ArrayList<>();
            if(sums.size()>0){
                sums.stream().forEach(ii->{
                    next.add(ii-eles[index]);
                    next.add(ii+eles[index]);
                });
            } else {
                next.add(-1*eles[index]);
                next.add(eles[index]);
            }
            return combine(next, eles, index+1, target);
        }
    }

    /**
     *
     *
     * Solution: use subsetA - subsetB = target
     * subsetA*2 = target + total
     * subsetA = target/2 + total/2
     */
    public int findTargetSumWays2(int[] nums, int S) {
        if(nums==null || nums.length<1){
            return 0;
        }
        int total = Arrays.stream(nums).reduce(0, (sum, n) -> sum += n);
        if((S+total)%2!=0){
            return 0;
        }
        return dps(nums, (S+total)/2, 0);

    }


    private int dps(int[] nums, int S, int start){
        //return condition: Y->sum=target N->sum>target
        //continue: sum<target
        print("DPS(IN) - current start = " + start);
        if(start>=nums.length){
            return 0;
        }
        int res = 0;
        for(int ii = start; ii<nums.length;ii++){
            print(" - iteration - " + ii + ", cur num: " + nums[ii] + ", target:" + S);
            if(nums[ii]==S){
                print("res ++ ");
                res ++;
            }
            if (nums[ii]<=S){
                res += dps(nums, S-nums[ii], ii+1);
            } // >S, end
        }
        print("DPS(OUT) - current start = " + start + ", res = " + res);
        return res;
    }

    /**
     * new way -> using partition_equal_sum
     * @param args 
    */
    public static void main(String [] args){
        TargetSumI494 t = new TargetSumI494();
        int [] input = {1,1,1,1,1};
//        int [] input = {1,0};
        t.print(""+t.findTargetSumWays2(input, 3));
    }
}
