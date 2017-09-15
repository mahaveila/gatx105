package leetcodeii.FourSeries;

import leetcodeii.Tracker;

/**
 * Created by Erebus on 9/12/17.
 */
public class NthDigit400 implements Tracker{

    //DOTO
    /**
     * error on 100, should be 5
     * @param n
     * @return
     */
    public int getNthDigit(int n) {
        int start = 1;
        //FIXME: gotcha!! use long to prevent overflow!!!
        long range = 9;
        int digitPerNum = 1;
        while(n > range * digitPerNum){
            //n need to be in next range
            /**
             range = 9, start = 1, dpn = 1: 1~9 #=9, #digits = 9
             range = 90, start = 10, dpn = 2: 10~99 #=90, #digits = 180
             range = 900, start = 100, dpn = 3: 100~999, #=900, #digits = 2700
             */
            n -= (range ) * digitPerNum++;
            start = start * 10;
            range = range * 10;
        }

        //now n whatever left in range start ~ start*10-1;
        //num = i th number starting from start, n=0,1,2 <== get from n-1%len, n-1 is for counting 0 itself. then it's e.g. dpn = 3, n/3=0.
        start += (n-1)/digitPerNum;
        int digit = (n-1)%digitPerNum;
        //e.g. dpn=3, digit=2, mask = pow(10, 0) = 1, if digit =1, 2nd: mask=pow(10,1)=10;
        return Character.getNumericValue(Integer.toString(start).charAt(digit));
    }

    public static void main(String [] args){
        NthDigit400 n = new NthDigit400();
        n.cout(n.getNthDigit(3));
        n.cout(n.getNthDigit(11));
        n.cout(n.getNthDigit(10));
        n.cout(n.getNthDigit(100));
        n.cout(n.getNthDigit(1000));
        n.cout(n.getNthDigit(1000000000));
    }

}
