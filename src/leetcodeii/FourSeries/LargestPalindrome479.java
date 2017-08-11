package leetcodeii.FourSeries;

import leetcodeii.Tracker;

/**
 * Created by Erebus on 8/5/17.
 */
public class LargestPalindrome479 implements Tracker<Integer, LFUCache.Cache>{

    public int largestPalindrome(int n) {
        if(n==1){
            return 9;
        }
        //input n is the digits, e.g. 2 => max is 99 = 100-1= pow(10, 2)-1
        int max = (int)(Math.pow(10 , n) - 1);
//        cout("max -> " + max);
        //grab the palindrome number from largest to smallest, e.g. 99 -> 9999, 98 -> 9889.
        //lower boundary should be 10 -> 1001, 10 = (max+1)/10 > 99/10.
        for(int i = max; i>max/10; i--){
            long p = Long.valueOf(""+i + new StringBuilder().append(i).reverse().toString());
            //define if the p is the product of two n-digits int. j*j is max of the j-product, which insures j is a n-big int (cuz lower boundary of p is 1001. j^2>=p, j>10).
//            cout("current p -> " + p);
            /**debug
             9999,9889,9779,......
             TODO: key -> use long j, not int j. cuz j*j could be larger than integer.MAX.
             e.g.
             diff:
             long j -> 99979, -> 9995800441
             int j -> 99979, -> 1405865849
             int j -> 99803. -> 1370704217

             9999999999....
             9995800441, those numbers were not really hitted like it should be

             became: 9999999999%Integer.MAX, ... etc.
             */
            for(long j= max; j*j>=p; j--){

                if(p%j==0){
                    cout(" p->" + p);
                    cout("  j->" +j);

                    return (int)(p%1337);
                }
            }
        }
        return -1;
    }

    public static void main(String [] args){
        LargestPalindrome479 l = new LargestPalindrome479();
        l.cout(l.largestPalindrome(5));
        l.cout((int)(99979*99979));
        l.cout(""+(long)99979*(long)99979);
    }
}
