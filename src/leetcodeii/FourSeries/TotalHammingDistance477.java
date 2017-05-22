package leetcodeii.FourSeries;

import leetcodeii.Tracker;

/** TODO: Time limited exceeded
 * Created by Youming on 4/19/2017.
 */
public class TotalHammingDistance477 implements Tracker{

    int totalHammingDist(int [] nums){
        int sum = 0;
        for(int ii = 0 ; ii<nums.length; ii++){
//        Double for loop solution is easy
            sum += helper(ii, nums);
        }
        return sum;
    }

    int helper(int start, int [] nums){
        if(start>=nums.length-1){
            return 0;
        }
        //return condition

        int sum = 0;
        for(int ii = start + 1; ii < nums.length; ii++){
            int tmp = getHammingDistance(nums[start], nums[ii]);
            cout(String.format("[%d, %d]=%d", nums[start], nums[ii], tmp));
            sum += tmp;
            /**
             * sum += helper(ii, nums);// 1 : 2,3,4, but 3,4 will occurs again when in 1:3 s loop
             * ii = 1, 1-2, 2-3, 3-4, 2-4, 1-3, 3-4 ! duplicates
             */
        }
        return sum;
    }

    int getHammingDistance(int a, int b){
        int sum = 0;
        while(a>0 || b>0){

            sum += (1&a)^(1&b);
            a = a>>1;
            b = b>>1;
        }
        return sum;
    }

    public static void main (String [] args){
        TotalHammingDistance477 t = new TotalHammingDistance477();
        int[] in = {4,14,2};
        t.cout(t.totalHammingDistance(in));
        t.cout(t.totalHammingDist(in));
        t.cout("TEST");

        t.cout(""+ (1<<1));
    }

    public int totalHammingDistance(int [] nums){

        int total = 0, n = nums.length;

        for(int ii = 0; ii<32; ii++){
            //iterating each bit
            int count = 0;
            for(int jj=0; jj<n; jj++){
                //counting how many number have current ii_th bit set
//                int mask = 1<<ii;
//                cout(String.format("current mask for << %d is %d", ii, mask));
//                count += nums[jj]&mask;
                //[MISS], use num >>, instead 1 <<, because 1 << 31 = -2147483648
//                Integer.MIN_VALUE = 0X80000000;
                count += nums[jj]>>ii & 1;
            }
            //ii th bit, count # has 1, rest of them has 0, the total distance is count*rest
            total += count * (n-count);
        }
        return total;
    }
}
