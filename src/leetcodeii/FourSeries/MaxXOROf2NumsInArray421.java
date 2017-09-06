package leetcodeii.FourSeries;

import leetcodeii.Tracker;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Erebus on 9/4/17.
 */
public class MaxXOROf2NumsInArray421 implements Tracker{

    //TODO: find the explanation
    public int findMaximumXOR(int[] nums) {
        int max = 0, mask = 0;
        for(int i = 31; i >= 0; i--){

            mask = mask | (1 << i);
            cout("current bit : " + i+ ", mask = "+Integer.toBinaryString(mask));
            Set<Integer> set = new HashSet<>();
            for(int num : nums){
                if(!set.contains(num & mask)){
                    //insert new set
                    cout("Insert " + Integer.toBinaryString(num&mask)+", # : "+num);
                }
                set.add(num & mask);
            }
            coutIntBinary(set);
            int tmp = max | (1 << i);
            //get the next possible result
            /**
             * e.g. previously 11000 is valid,
             * now try 11000 | 100 = 11100, if valid update max = 11100
             * next try 11100 | 10 = 11110, if not possible, not updating max
             * next try 11100 | 01 = 11101, if still not possible, go next
             *
             * ..
             *
             * till the end. max is the maximum possible result
             */
            cout("  tmp="+Integer.toBinaryString(tmp));
            for(int prefix : set){
                //num ^ num = tmp --> tmp is the result, while here num is prefix till current bit.
                if(set.contains(tmp ^ prefix)) {
                    //found target prefix, current result is possible.
                    max = tmp;
                    cout("    [O] tmp^prefix = " + Integer.toBinaryString(tmp^prefix)+", max = "+Integer.toBinaryString(max));
                    break;
                } else {
                    cout("    [X] tmp^prefix = " + Integer.toBinaryString(tmp^prefix));
                }
            }
        }
        return max;
    }

    private String bi(int i){
        return Integer.toBinaryString(i);
    }

    public static void main(String [] args){
        MaxXOROf2NumsInArray421 m = new MaxXOROf2NumsInArray421();
//        int[] in = {8,10,2};
        int[] in = {3,10,5,25,2,8};
        m.cout(m.findMaximumXOR(in));

    }

}
