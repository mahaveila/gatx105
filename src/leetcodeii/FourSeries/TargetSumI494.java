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
        log("entry S: " + S);
        int total = Arrays.stream(nums).reduce(0,(sum, i)-> sum += i );
        log("entry: "+total);
        boolean invalid = total < S || (total+S)%2==1;
        boolean invalid1 = total<S;
        boolean invalid2 = (total+S)%2==1;
        log("array invalid = " + invalid + " | " + invalid1 + " | " + invalid2);
        return  invalid? 0 : dps(nums, (total+S)/2);
    }

    private int dps(int[] nums, int S){
        log("dps on " + S);
        int[] dp = new int[S+1];
        dp[0] = 1; //there is always 1 way to sum 0 to 0
        for(int n : nums){
            log(""+n);
            for(int ii = S; ii>=n; ii--){
                //1. not go out of index
                //2. set the possible outcome ranges,  below n, larger positive integer can never be sum to smaller value; larger than S, cannot reach.
                dp[ii] += dp[ii-n];
                print(dp);
            }
            print(dp);
        }
        return dp[S];
    }

    /**
     * new way -> using partition_equal_sum
     * @param args 
    */
    public static void main(String [] args){
        TargetSumI494 t = new TargetSumI494();
        int [] input = {1,1,1,1,1};
//        int [] input = {1,0};
        int res = t.findTargetSumWays2(input, 3);
        t.print(""+res);
    }
}
