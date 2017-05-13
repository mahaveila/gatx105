package leetcodeii.FourSeries;

import leetcodeii.Tracker;

/**
 * Created by Youming on 4/19/2017.
 */
public class ReversePairs493 implements Tracker {
    public int reversePairs(int[] nums) {
        if(nums==null || nums.length<1){
            return 0;
        }
        return merge_count(0, nums.length-1, nums);
    }

    private int merge_count(int start, int end, int[] nums){
        //[MISS]
        int mid = start + (end-start)/2;
        cout(String.format("[start: %d, end: %d], mid: %d", start, end, mid));
        if(start>=end){
            return 0;
        }

        /**
         * 1,2,3,4,5 -> start=0, end=4, mid=2. i.e. 3
         * 1,2,3,4 -> start=0, end=3, mid=1. i.e. 2
         */
        //[MISS], the mid+1 not mid, avoid 0,0 | 0,1 into infinite for loop overflow
        int count = merge_count(start, mid, nums) + merge_count(mid+1, end, nums);
        //divide into 2s, at this point, left side sorted, right side sorted.

        cout(String.format("split into [%d:%d] | [%d:%d], counted: %d",start,mid,mid+1,end,count));
        //time to count
        int curcount = 0;
        for(int left = start, right = mid+1; left <=mid;){
            if(right>end || (long)nums[left]<=(long)2*nums[right]){
                left ++;
                count += curcount;
                /** [MISS]
                 * 1) right ends, keep using left. each new left element must be larger than before and be critical. 7,8 create 10 pairs each, then 11 should create another 10 pairs
                 * 2) critical not found, but current value is still bigger than previous, should add previous value
                 */
            } else {
                right ++;
                curcount++; //find a new pair
            }
        }
        cout("current level count:"+curcount);

        //merge two sides
        int [] tmp = new int[end-start +1];
        cout(nums);
        for(int l = start, r = mid+1, i = 0; (l<=mid || r<=end)&& i<tmp.length;){
            //[MISS], be consistent with context, and add validations on i for return condition => check return conditions for every parameters

            if(l>mid){
                tmp[i++] = nums[r++];
                continue;
            }
            if(r>end){
                tmp[i++] = nums[l++];
                continue;
            }
            if(nums[l]<nums[r]){
                //left smaller
                cout(String.format("left [%d] < right [%d], at %d", l, r, i));
                tmp[i++] = nums[l++];
            } else {
                cout(String.format("left [%d] >= right [%d], at %d", l, r, i));
                tmp[i++] = nums[r++];
            }
        }

        //[MISS], start + ii for arrays that starts in middle
        for(int ii = 0; ii<tmp.length; ii++){
            nums[start + ii] = tmp[ii];
        }
        cout(tmp);
        cout(nums);
        return count;
    }

    public static void main(String [] args){
        int[] input = {1,3,2,3,1};
        int[] input2 = {2,4,3,5,1};
        int [] input3 = {2147483647,2147483647,2147483647,2147483647,2147483647,2147483647};
        ReversePairs493 tsk = new ReversePairs493();
        tsk.cout(tsk.reversePairs(input3));
        tsk.specialCase();
    }

    //FIXME: the boundary condition: 2147483647 * 2 is smaller

    /**
     *
     Integer.MAX_VALUE * 1 = 0x7fffffff which is decimal 2147483647

     Integer.MAX_VALUE * 2 = 0xfffffffe which is -2

     Integer.MAX_VALUE * 3 = 0x17ffffffd but it is 33 bits, after truncation it is 0x7ffffffd which is 2147483645
     */
    private void specialCase(){
        int a = 2147483647;
        int b = 2147483647;
        System.out.println(a<=2*b?"a smaller":a>2*b?"b smaller":"neither");
    }

}
