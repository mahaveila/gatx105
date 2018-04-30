package leetcodeii.Arrays;

import leetcodeii.Tracker;

/**
 * Created by Erebus on 4/29/18.
 */
public class RangeSumQueryBinaryIndexTree implements Tracker{

    public static void main(String [] args){
        int [] input = {};
        RangeSumQueryBinaryIndexTree r = new RangeSumQueryBinaryIndexTree(input);
        r.cout(r.intToBinary(2));
        r.cout(r.intToBinary(-2));


        r.cout(r.intToBinary(5));
        r.cout(r.intToBinary(-5));

        r.cout(r.intToBinary(1));
        r.cout(r.intToBinary(-1));

        r.cout(r.intToBinary(0));
        r.cout(r.intToBinary(-0));
    }

    private String intToBinary(int x){
        return Integer.toBinaryString(x);
    }

    int [] tree;
    //A is for recording and compute the difference to updatae
    int [] A;

    /**
     use bubble up mechanism in update method to build tree from 0s.
     */
    private void buildTree(int [] nums){
        for(int ii = 0; ii < nums.length; ii ++){
            updateBIT(ii, nums[ii]);
        }
    }

    /**
     BIT[1] = A[1];
     {           a[x],                  if x is odd
     BIT[x] =                    a[1] + ... + a[x],     if x is power of 2
     }
     but still need to handle case: BIT[6] = A[5] + A[6] -> [110] = [101] + [110]
     therefore, update can use bubble up, initial all are 0
     update(1): update to [1], bubble up to 1,2,4,8,16,etc
     for(; x <= n; x += x&-x)
     BIT[x] += val;
     i.e.  x      01             10          100     1000
     lowbit       01             10
     next       01+01=10        10+10=100
     update(5): bubble up to 5,6,8,16
     x       101     110     1000
     low     001     010     1000
     next    110    1000    10000
     */
    private void updateBIT(int i, int val){
        int orig = A[i];
        A[i] = val;
        int diff = val - orig; //the difference to bubble up.
        for(int x = i+1; x <= A.length; x += lowbit(x)){
            tree[x] += diff;
        }
    }

    /**
     preSum(16) = BIT[16]  10000, lowbit = 10000, then done.
     preSum(15) = BIT[15] + BIT[14] + BIT[12] + BIT[8];
     01111   01110   01100       01000
     */
    private int getPreSum(int i){
        int x = i+1;
        int sum = 0;
        while(x>0){
            sum+= tree[x];
            int low = lowbit(x);
            x = x-low;
        }
        return sum;
    }

    /**
     low bit function: x&(-x)
     e.g. 1 = 0000...00001
     -1 = 1111...11111,    e.g. flip(1-1) = flip(0)
     2 = 0000...00010
     -2 = 1111...11110,    e.g. flip(2-1)

     5 = 0000...00101
     -5 = 1111...11011     e.g.  flip(0000...00100) = flip(5-1)
     */
    private int lowbit(int x){
        return x&(-1*x);
    }

    public RangeSumQueryBinaryIndexTree(int[] nums) {
        // root = buildTree(nums, 0, nums.length-1);
        //SWEET: the tree[0] is empty slot, not used. for correct binary index calculation.
        this.tree = new int[nums.length+1];
        this.A = new int[nums.length];
        buildTree(nums);
    }

    public void update(int i, int val) {
        // update(root, i, val);
        updateBIT(i, val);
    }

    public int sumRange(int i, int j) {
        // return sumRange(root, i, j);
        //exclusive i from [0~i]
        //[i~j] = [0~j] - [0~i-1]
        return getPreSum(j) - getPreSum(i-1);
    }
}
