package leetcodeii.sorting;

import leetcodeii.Tracker;

/**
 * Created by Youming on 5/5/2017.
 */
public class MergeSort implements Tracker {

    /**
     * TODO:  fix the broken function. 1)
     * @param start
     * @param mid
     * @param end
     * @param nums
     */
    private void merge(int start, int mid, int end, int [] nums){
        /**
         * pseudo code
         * 0
         */
    }

    private void swap(int i, int j, int [] nums){
        int buffer = nums[i];
        nums[i] = nums[j];
        nums[j] = buffer;
    }

    public static void main(String [] args){
        int[] a = {1,3,5,2,4,6};
        new MergeSort().merge(0,3,5,a);
    }

}
