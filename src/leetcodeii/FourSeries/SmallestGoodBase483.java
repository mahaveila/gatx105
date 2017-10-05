package leetcodeii.FourSeries;

import java.math.BigInteger;

/** FIXME optimize?
 * Created by Youming on 4/19/2017.
 */
public class SmallestGoodBase483 {

    public String smallestGoodBase(String n) {
        BigInteger N = new BigInteger(n);
        long base = Long.MAX_VALUE;

        for (int k = 2; k < 66; k++) {

            long l = 2, r = Long.MAX_VALUE - 5;
            while (l <= r) {
                long mid = l + (r - l) / 2;

                BigInteger cb = BigInteger.valueOf(mid).pow(k).subtract(BigInteger.ONE);
                BigInteger wb = N.multiply(BigInteger.valueOf(mid).subtract(BigInteger.ONE));

                int cmp = cb.compareTo(wb);
                if (cmp == 0) {
                    base = Math.min(base, mid);
                    break;
                } else if (cmp < 0) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }

        return "" + base;
    }


    //TLE
    public String smallestGoodBaseTLE(String nn) {
        long n = Long.parseLong(nn);
        long res = 0;
        for(int k=60; k>=0; k--){
            //possible pows, s-> start, e->end
            long s = 2, e=n;
            while(s<e){
                //we have n*(x-1) = x^k-1 <= n = x^k + x^(k-1) +... + x^0
                long m = s + (e-s)/2;
                BigInteger left = BigInteger.valueOf(m);
                left = left.pow(k).subtract(BigInteger.ONE); //left = x^k-1
                //right = n*(x-1)
                BigInteger right = BigInteger.valueOf(n).multiply(BigInteger.valueOf(m).subtract(BigInteger.ONE));
                int cmp = left.compareTo(right);
                if(cmp==0){
                    res = m;
                } else if(cmp>0){
                    //left is larger, x is too small
                    e= m -1;
                } else {
                    s = m+1;
                }
            }
            if(res!=0){
                //k from larger to smaller, then m is from smaller to larger, get the limit -> i.e. smallest m
                break;
            }
        }
        return ""+res;
    }

}
