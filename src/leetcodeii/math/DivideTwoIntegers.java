package leetcodeii.math;

import leetcodeii.Tracker;

/**
 * Created by Erebus on 3/24/18.
 */
public class DivideTwoIntegers implements Tracker{

    public static void main(String [] args){
        DivideTwoIntegers d = new DivideTwoIntegers();
        d.cout(d.divide(-2147483648, 2));
    }

    /**
     * @param dividend: the dividend
     * @param divisor: the divisor
     * @return: the result
     */
    public int divide(int dividend, int divisor) {
        if(dividend==0){
            return 0;
        }
        if(divisor==1){
            return dividend;
        }
        // write your code here
        // long d1 = dividend;
        // long d2 = divisor;
        // if(dividend)
        long longDiv = dividend;
        //KEY: handle in the front, avoid unnecessary computings
        if(longDiv<=Integer.MIN_VALUE && divisor == -1){
            return 2147483647;
        }
        //FIXME: wrap to long first, then convert the sign, otherwise will overflow
        long pdividend = Math.abs((long)dividend);
        long pdivisor = Math.abs((long)divisor);
        if(pdividend<pdivisor){
            cout(String.format("%d/%d, divisor bigger, return 0", pdividend, pdivisor));
            return 0;
        }
        int count = 0;
        while(pdividend >= pdivisor){
            //KEY: the shift takes care of +1 and +2 case,
            //no need for separate d+d (init 1 or 0) consideration

            //KEY i.e. shift advantage over add: shift*2 starts from 1,2,4
            //while add*2 starts at 2,4,8
            int shift = 0;
            cout(String.format("%d/%d", pdividend, pdivisor));
            while(pdividend >= (pdivisor<<shift)){
                shift++;
            }

            //KEY: counts also need shift to compute
            count += (1<<(shift-1));
            //did one more shift when last comparing
            pdividend -= (pdivisor<<(shift-1));

            // int d2 = divisor;
            // int ct = 1;
            // if(dividend < d2 + divisor){
            //     //odd
            //     count += 1;
            //     dividend -= d2;
            // } else {
            //     //2 or more
            //     while(dividend>=d2){
            //         divisor = divisor << 1;
            //         ct = ct << 1;
            //     }
            //     count += ct;
            //     dividend -= d2;
            // }

        }
        if((dividend > 0 && divisor >0) || (dividend <0 && divisor <0)){
            return count;
        } else {
            return -count;
        }

    }
}
