package leetcodeii.FourSeries;

import leetcodeii.Tracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Youming on 4/19/2017.
 */
public class PredictTheWinner486 implements Tracker{

    /**
     * Doesn't work, not this easy
     * actual need to consider it as a binary decision tree, and looking for the maximum result
     * @param nums
     * @return
     */
    boolean p1win(int [] nums){
        int p1=0, p2=0, start=0, end=nums.length-1;
        int order = 0;
        while(start<=end){
            if(order%2==0){
                p1 += nums[start] > nums[end]? nums[start++]:nums[end--];
            } else {
                p2 += nums[start] > nums[end]? nums[start++]:nums[end--];
            }
            order ++;
        }
        return p1 > p2;
    }

    boolean predictWin(int [] nums){
        cout(nums);
        if(nums==null || nums.length<3){
            return true;
        }
        return Math.max(nums[0] - leaf(nums,1,nums.length-1),nums[nums.length-1] - leaf(nums,0,nums.length-2)) >= 0;
    }

    private int leaf(int [] nums, int start, int end){
//        cout(String.format("[%d,%d]", start, end));
        if(start>=end){
            return nums[start];
        }
        //select start
        int left = nums[start] - leaf(nums, start + 1, end);
        int right = nums[end] - leaf(nums, start, end -1);
        return Math.max(left, right);
    }
 
    public static void main(String [] args){
        int [] input = {1,5,233,7};
        int [] input2= {1,5,2};
        PredictTheWinner486 p = new PredictTheWinner486();
        System.out.println(p.predictWin(input));
        System.out.println(p.predictWin(input2));
    }
}
