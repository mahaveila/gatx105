package leetcodeii.FourSeries;

import leetcodeii.Tracker;

/**
 * Created by Youming on 4/19/2017.
 */
public class NumberComplement476 implements Tracker{

    //TODO: SUBMIT and compare
    public int findComplement(int n){
        //TODO: find a way to represents the 11111 seq for all integers

        //alternative, the hard way
        int m = 1;
        while(m<n){
            m = m | m<<1; // 1 | 01 = 11;
        }
        return m ^ n;
    }

    public static void main(String [] args){
        NumberComplement476 n = new NumberComplement476();
        n.cout(n.findComplement(1));
        n.cout(n.findComplement(5));
    }
}
