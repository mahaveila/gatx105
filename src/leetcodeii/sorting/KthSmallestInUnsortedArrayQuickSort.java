package leetcodeii.sorting;

import leetcodeii.Tracker;


/**
 * The basic idea is to use Quick Select algorithm to partition the array with pivot:

 Put numbers < pivot to pivot's left
 Put numbers > pivot to pivot's right
 Then

 if indexOfPivot == k, return A[k]
 else if indexOfPivot < k, keep checking left part to pivot
 else if indexOfPivot > k, keep checking right part to pivot
 Time complexity = O(n)

 Discard half each time: n+(n/2)+(n/4)…1 = n + (n-1) = O(2n-1) = O(n), because n/2+n/4+n/8+…1=n-1.
 Sn=n*a1(q=1) Sn=a1(1-q^n)/(1-q) =(a1-anq)/(1-q) ---> q=1/2, a1=n, an= ----> 趋近于 n^(1/infinit) = n^0 = 1
 * Created by Erebus on 3/6/18.
 */
public class KthSmallestInUnsortedArrayQuickSort implements Tracker{

    public static void main(String [] args){
        KthSmallestInUnsortedArrayQuickSort k = new KthSmallestInUnsortedArrayQuickSort();
        int [] in = {1,2,3,4,5,6,8,9,10,7};
        k.cout(k.kthSmallest(10, in));
    }


    /**
     * @param k: An integer
     * @param nums: An integer array
     * @return: kth smallest element
     */
    public int kthSmallest(int k, int[] nums) {
        // write your code here
        return kthSmallest(k-1, nums, 0, nums.length-1);
    }

    private int kthSmallest(int k, int[] nums, int st, int ed){
        if(st>ed){
            return Integer.MAX_VALUE;
        }
        //choose ed as the pivot
        int pivot = nums[ed];
        //way 1 [X] to swaps, if < p, put int st++, else put in ed--; _> won't work
        //way 2 [O], use start=st, iterating, any num<p, swap[start++]&[i]
        cout(String.format("k=%d, st=[%d], ed=[%d], nums=%s",k,st,ed,stringfy(nums)));
        int start = st;
        for(int ii = st; ii<ed;ii++){
            if(nums[ii]<=pivot){
                //swap nums[ii] to [start], found a match (<=p)
                swap(ii, start++, nums);
            }
        }
        /**
         * FIXME: easy to miss this put pivot step
         */
        swap(start, ed, nums);// Finally, swap A[ed] with A[pivotStart], move pivot to the middle, where it should be
        cout(nums);
        //now start is at beginning of the right side
        /**
         * FIXME: fixed, use start looking for kth index, and return values of that num
         */
        if(start==k){
            return nums[start];
        } else if(start>k){ //start is on k's right, check left
            return kthSmallest(k, nums, st, start-1);
        } else {
            //the pivot selected is smaller(in front of) than k, look right
            return kthSmallest(k, nums, start+1, ed);
        }
    }

    private void swap(int i, int j, int [] nums){
        int tmp = nums[i];
        nums[i]=nums[j];
        nums[j]=tmp;
    }

}
