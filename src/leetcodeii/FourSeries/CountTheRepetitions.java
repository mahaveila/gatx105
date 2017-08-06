package leetcodeii.FourSeries;

/**
 * Created by Erebus on 6/4/17.
 */
public class CountTheRepetitions {

    /**
     * Say the lengths of s1 and s2 are m1 and m2. It's easy to prove that:
     If there are two pairs <i1, j1>, <i2, j2> satisfying:
     (i2 - i1) % m1 == 0 && (j2 - j1) % m2 == 0,
     let d1 = i2 - i1 and d2 = j2 - j1,
     then for all positive integer k, <i1 + d1 * k, j1 + d2 * k> will be pairs too.
     */
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        StringBuffer sb = new StringBuffer();
        for(int ii = 0; ii<n1; ii++){
            sb.append(s1);
        }
        char [] c1 = sb.toString().toCharArray();
        sb.delete(0, sb.length());
        for(int ii = 0; ii<n2; ii++){
            sb.append(s2);
        }
        char[] c2 = sb.toString().toCharArray();

        int sum = 0;
        int j = 0;
        for(int i = 0; i <c1.length; i++){

        }

        return sum;
    }

}
