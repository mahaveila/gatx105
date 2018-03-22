package leetcodeii.Arrays;

import leetcodeii.Tracker;

import java.util.Arrays;

/**
 * Created by Erebus on 3/21/18.
 */
public class MaxSubarrayAverageII implements Tracker{

    public double maxAverage(int[] nums, int k) {
        // write your code here


        /**
         * couple of points when using the binary guessing
         * 1. mid = min + (max-min)/2
         * 2. use sum and presum to optimize the sum[i] and sum[j]
         */

        double min = Arrays.stream(nums).min().getAsInt();
        double max = Arrays.stream(nums).max().getAsInt();
        double mid = 0.0;
        //1e-5=0.00001.
        while(max-min>1e-5){
            mid = min + (max-min)/2;
            cout(mid);
            double minPre = 0;
            //replacement of sum[i], accumulated sum for window >=k
            double sum=0;
            //replacement of sum[j], accumulated sum before window k
            double presum = 0;
            boolean canIncrease = false;
            for(int ii=0; ii<nums.length; ii++){
                sum += nums[ii] - mid;
                if(ii>=k){
                    presum += nums[ii-k] - mid;
                    minPre = Math.min(presum, minPre);
                }
                if(ii>=k-1 && sum>minPre){
                    canIncrease = true;
                    min = mid;
                    cout("mid = " + mid +", found increase available, break out");
                    //increase mid, and break out for loop
                    break;
                }
                cout("mid = " + mid +", reduce size");
                max = mid;
            }
//            if(canIncrease){
//                min = mid;
//            } else {
//                max = mid;
//            }
            cout("in the end: mid="+mid+", max="+max+", min="+min);
        }
        return mid;
    }

    double findMaxAverage(int[] nums, int k) {
        double left = Arrays.stream(nums).min().getAsInt();
        double right = Arrays.stream(nums).max().getAsInt();
        while (right - left > 1e-5) {
            double minSum = 0, sum = 0, preSum = 0, mid = left + (right - left) / 2;
            cout(mid);
            boolean check = false;
            for (int i = 0; i < nums.length; ++i) {
                sum += nums[i] - mid;
                if (i >= k) {
                    preSum += nums[i - k] - mid;
                    minSum = Math.min(minSum, preSum);
                }
                if (i >= k - 1 && sum > minSum) {check = true; break;}
            }
            if (check) left = mid;
            else right = mid;
            cout("in the end: mid="+mid+", max="+right+", min="+left);
        }
        return left;
    }

    public static void main(String [] args){
        MaxSubarrayAverageII m = new MaxSubarrayAverageII();
        int [] input = {1,12,-5,-6,50,3};
        m.cout(m.findMaxAverage(input, 3));
        m.cout(m.maxAverage(input, 3));
    }
}
