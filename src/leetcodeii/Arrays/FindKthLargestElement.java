package leetcodeii.Arrays;

import leetcodeii.Tracker;

/**
 * Created by Erebus on 4/9/18.
 */
public class FindKthLargestElement implements Tracker{

    int counter = 20;

    public static void main(String [] args){
        FindKthLargestElement f = new FindKthLargestElement();
        int [] input = {2,1};
        f.cout(f.findKthLargest(input, 1));
    }

    /**
     * FIXME bug
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        cout(nums);
        //the best way to do it would be use quick select
        if(nums.length<1 || k < 0 || k>nums.length){
            return 0;
        }
        return findK(nums, k, 0, nums.length-1);
    }

    /*
    case -1, 2, 0, pivot = 0, find 1st largest, k = 1
    -1, 2, 0 -> st = 0, ed = 1, will directly swap -> 0, 2, -1
          0 1 2 3 4 5
    case [5,2,4,1,3,6,0], k = 4, pivot = 0, st = 0, ed= 5
          6         5       st=0, ed=4
          3       6         st=0, ed=3
          1     3           st=0, ed=2
          4   1             st=0, ed=1
          2 4               st=0, ed=0
          4 2 1 3 6 5  st=0, ed=0, st > pivot -> 0 4 1 3 6 5 2
          k=1, p=0;
          ed=n-1=6, [6]=2, 2>nums[0], select from behind
          4,1,3,6,5,2 select 1th pivot = 2,


    case [2,1] 1
    st=0, ed=1, pivot = 1, st=0,ed=0 swap -> {1,2}, p_index = 0
    kth from last 1-1+1=1, [1]>[0], search 1st from {2}, range 1,1
    {2}, st=1, ed=1, k=1,
    no sorting, kth from last=1-1+1=1, [kth:1] = 2, pivot=[1]
    2==2, should return 2
    */
    private int findK(int [] nums, int k, int st, int ed){

        if(counter<1){
            throw new RuntimeException("Stack overflow");
        }
        counter--;


        int p = quickSelect(nums, k, st, ed);
        cout(nums);
        cout("pivot at: " + p);
        //total number = ed-st+1, the kth from behind: ed-k+1, cuz 1st from behind = ed (k=1)
        cout("     the kth ("+k+") element from last is [" + (ed-k+1) + "] = " + nums[ed-k+1]);
        if(ed-p+1<k){
            //k elements from behind are larger than the pivot, search in the first part, exclusively
            //how many elements at p: ed-p+1;
            cout("           find next in the first part from: st="+(st)+" till p-1="+(p-1)+",  find k=" + (k-ed+p-1));
            return findK(nums, k-ed+p-1, st, p-1);
        } else if(ed-p+1==k){
            //the kth element from behind is equal to p
            return nums[p];
        } else { //kth is > than pivot
            //search the second part
            cout("           find kth("+k+") in the second part from: p+1="+(p+1)+" till ed="+ed);
            return findK(nums, k, p+1, ed); //still find k th, has enough element in the second half
        }
    }

    private int quickSelect(int nums[], int k, int i, int j){
        int pivot = nums[j];
        int st = i;
        int ed = j;
        while(st<ed){ //e.g.  14765 pivot at 4, st0 ed3,  14, st->2, ed=3 the loop will break at st=ed-1
            //case 16675  st=1, ed=3,  17665 ed=2, break, swap 1 with last 15667.
            if(nums[st]<=pivot){
                st ++;
            } else {
                ed--;
                swap(nums, st, ed);
            }
        }
        cout("sorted before swap pivot: " + stringfy(nums));
        swap(nums, st, j);
        cout("sorted: " + stringfy(nums));
        return st;
    }

    private void swap(int [] nums, int st, int ed){
        int tmp = nums[st];
        nums[st] = nums[ed];
        nums[ed] = tmp;
    }

}
