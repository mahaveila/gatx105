package leetcodeii.Arrays;

import leetcodeii.Tracker;

/**
 * Created by Erebus on 3/21/18.
 */
public class MaxSubarrayAverageII implements Tracker{

    public double maxAverage(int[] nums, int k) {
        // write your code here

        /**
         * the key here is to use binary guess
         */
        double min = nums[0];
        double max = nums[0];
        for(int i : nums){
            min=Math.min(min, i);
            max=Math.max(max, i);
        }
        cout(max);
        cout(min);
        double mid = 0.0;
        int count = 0;
        while(max-min>0.0001 && count++<20   ){
            mid = (max-min)/2;
            cout(String.format("max=%10.7f, min=%10.7f, mid=%10.7f", max, min, mid));
            if(canIncrease(mid, nums, k)){
                cout("can increase");
                min = mid-1;
            } else {
                cout("need decrease");
                max = mid+1;
            }
        }
        return mid;
    }

    private boolean canIncrease(double mid, int[] nums, int k){
        double minSum = 0; //to maintain the [j]=[i-k]'s min value
        for(int i=1; i<nums.length; i++){
            //starting from size k window, and expanding to right
            double s[] = new double[nums.length];
            s[0]=nums[0]-mid;
            s[i]=nums[i]+s[i-1]-mid;
            if(i>=k){
                //narrow down window from left
                for(int j=0; j<i-k; j++){
                    minSum = Math.min(minSum, s[j]); //keep a min value
                }
                if(s[i]>minSum){
                    //sum-minSum>mid, mid can increase
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String [] args){
        MaxSubarrayAverageII m = new MaxSubarrayAverageII();
        int [] input = {1,12,-5,-6,50,3};
        m.cout(m.maxAverage(input, 3));
    }
}
