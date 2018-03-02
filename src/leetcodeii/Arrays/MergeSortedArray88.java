package leetcodeii.Arrays;

import leetcodeii.Tracker;

/**
 * Created by Erebus on 2/15/18.
 */
public class MergeSortedArray88 implements Tracker{

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        cout(nums1);
        cout(nums2);
        int p1=m-1;
        int p2=n-1;
        int p=m+n-1;
        while(p1>=0&&p2>=0){
            cout("both nonempty");
            nums1[p]=nums1[p1]>nums2[p2]?
                    nums1[p1]:nums2[p2];
            if(nums1[p1]>nums2[p2]){
                p1--;
            } else {
                p2--;
            }
            p--;
        }
        if(p1>=0){
            cout("array 2 is empty");
            while(p1>=0){
                nums1[p--]=nums1[p1--];
            }
        }
        if(p2>=0){
            cout("array 1 is empty");
            while(p2>=0){
                nums1[p--]=nums2[p2--];
            }
        }
        cout(nums1);
    }

    public static  void  main(String [] args){
        MergeSortedArray88 m = new MergeSortedArray88();
        int[] in1 = {0};
        int[] in2 = {1};
        m.merge(in1, 0, in2, 1);
    }
}
