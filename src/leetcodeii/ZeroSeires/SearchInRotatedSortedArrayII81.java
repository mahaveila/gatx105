package leetcodeii.ZeroSeires;

import leetcodeii.Tracker;

/**
 * Created by Erebus on 2/13/18.
 */
public class SearchInRotatedSortedArrayII81 implements Tracker{


    public boolean search(int[] nums, int target) {
        if(nums==null||nums.length<1){
            return false;
        }
        return search(nums, target, 0, nums.length-1);

    }

    private boolean search(int[] nums, int target, int l, int r){
cout("searching [" + l +"] to [" + r + "] for "+ target);
        if(l>r){
            return false;
        }
        //need to narrow down range l to r,
        //case 1: in order, means all the same l<r&&n[l]==n[r], l++
        //case 2: 4,4,4,1,2,3,4 or 4,1,2,3,4,4,4
        while(l<r && nums[l]==nums[r]){
            l++;
        }
        int mid = (l+r)/2;
        cout(String.format("mid:[%d]=%d", mid, nums[mid]));
        if(nums[mid]==target){
            return true;
        } else if(nums[mid]<target){
            //mid < n
            //[6,7,1,3,4,5], 6,7 > 1,3, still on left because nums[l]>=nums[r] && target > nums[r]
            //3,4,5 on the right, because target<nums[r], i.e. mid < nums[r]

            //complexity of worse case: 1151111, cannot compare mid with edges because them are all 1
            //so after reduce to 51111, more easy to identify

            //FIXME: fixed, use nums[l]>nums[mid} only for rotated case, otherwise >= might have equal/in order case
            if(nums[l]>nums[mid] && target>=nums[l]){
                cout("case 1, search left");
                //left rotated and has larger elements
                return search(nums, target, l, mid-1);
            } else {
                //left in order, not in left side
                cout("case 2, search right");
                return search(nums, target, mid+1, r);
            }

        } else {
            //mid > target, maybe on the left side, the only case not in left is left edge > target, so right side is rotated
            if(nums[mid]>nums[r] && target<=nums[r]){
                //right side rotated and contains smaller elements, [4,5,6,7,1,2]
                cout("case 3, search right");
                return search(nums, target, mid+1, r);
            } else {
                //right side in order, not in right side
                cout("case 4, search left");
                return search(nums, target, l, mid-1);
            }
        }
    }

    public static void  main(String [] args){
        SearchInRotatedSortedArrayII81 sirs = new SearchInRotatedSortedArrayII81();
        int [] input = {3,5,1};
        sirs.cout(sirs.search(input, 3)?1:-1);
    }
}
