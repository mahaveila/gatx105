package leetcodeii.FourSeries;

import leetcodeii.Tracker;

/**
 * Created by Erebus on 9/4/17.
 */
public class ReconstructOriginDigitsInEnglish423 implements Tracker{

    /**
     * could be simpler
     * e.g. seven = [v] - five, while five = f - four, four = r - 3 - 0.
     * @param s
     * @return
     */
    public String originalDigits(String s) {
        int[] c = new int[26];
        char [] cs = s.toCharArray();
        for(char cc : cs){
            c[cc-'a']++;
        }
        cout(c);
        int [] res = new int[10];
        //six
        res[6] = c['x'-'a'];
        c=reduce("six", res[6], c);
        //two
        res[2] = c['w'-'a'];
        c=reduce("two", res[2], c);
        //eight
        res[8] = c['g'-'a'];
        c=reduce("eight", res[8], c);
        //three
        res[3] = c['h'-'a'];
        c=reduce("three", res[3], c);
        //zero
        res[0] = c['z'-'a'];
        c=reduce("zero", res[0], c);
        //four
        res[4] = c['r'-'a'];
        c=reduce("four", res[4], c);
        //five
        res[5] = c['f'-'a'];
        c=reduce("five", res[5], c);
        //seven
        res[7] = c['v'-'a'];
        c=reduce("seven", res[7], c);
        //one
        res[1] = c['o'-'a'];
        c=reduce("one", res[1], c);
        //nine, last one, no need to reduce
        res[9] = c['e'-'a'];

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<res.length; i++){
            while(res[i]-->0){
                sb.append(""+i);
            }
        }
        return sb.toString();
    }

    private int[] reduce(String num, int n, int[] in){
        cout("reducing " + num);
        if(n==0){
            return in;
        }
        char[] cs = num.toCharArray();
        for(char c : cs){
            cout("reduce: "+ c + " by " + n);
            in[c-'a']-=n;
        }
        return in;
    }

    public static void main(String [] args){
        ReconstructOriginDigitsInEnglish423 r = new ReconstructOriginDigitsInEnglish423();
        r.cout(r.originalDigits("fviefuro"));
    }
}
