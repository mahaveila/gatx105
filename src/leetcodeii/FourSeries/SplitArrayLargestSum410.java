package leetcodeii.FourSeries;

/**
 * Created by Erebus on 9/16/17.
 */
public class SplitArrayLargestSum410 {

    public int splitArray(int[] nums, int m) {
        int max = 0;
        int sum = 0;
        for(int n : nums){
            max=Math.max(max, n);
            sum += n;
        }
        long l = max;
        long r = sum;
        while(l<=r){
            long mid = (l+r)/2;
            if(valid(mid, nums, m)){
                //count = k, splitted at most m pieces, each piece is no greater than mid.
                //try push for smaller mid
                r = mid - 1;
            } else {
                //piece > than k, to reduce piece, reduce sum>target, increase target. i.e. mid
                //in other words, too many pieces to split, increase mid to reduce # of subarrays
                l = mid + 1;
            }
        }
        return (int)l;
    }

    //test if nums can be splitted at most k sub-arrays that has max sum no larger than target
    private boolean valid(long target, int[] nums, int k){
        int piece=1;
        int sum = 0;
        for(int n : nums){
            sum += n;
            if(sum>target){
                sum=n;
                piece++;
                if(piece > k){
                    return false;
                }
            }
        }
        //2 cases, n is last element, and sum == target, or sum < target
        return true;
    }
}
