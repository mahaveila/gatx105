package leetcodeii.FourSeries;

import leetcodeii.Tracker;

/**
 * Created by Erebus on 5/20/17.
 */
public class OnesAndZeros474 implements Tracker{

    /** TODO: TIMEOUT
     *
     * @param strs
     * @param m number of 0s
     * @param n number of 1s
     * @return
     */
    public int findMaxForm(String [] strs, int m, int n){
        cout(strs);
        return helper(strs, 0, m, n);
    }

    private int helper(String [] strs, int idx, int m, int n){
        //return condition, m or n is used up
        if(m<0 || n<0){
//            cout("[X] Terminated");
            return 0; //no more
        }
        if(m==0 || n==0){
//            cout("[X] Terminated");
            return 1;
        }
        int sum = 0;
        String cur1 = strs[idx];
        String cur0 = strs[idx];
        int zeros = strs[idx].length() - cur0.replaceAll("0","").length();
        int ones = strs[idx].length() - cur1.replaceAll("1","").length();
        for(int ii = idx+1; ii<strs.length; ii++){

//            cout(String.format("@[%d]=%s, %d 0_left, %d 1_left, next select [%d]=%s", idx, strs[idx], m-zeros, n-ones, ii, strs[ii]));
            //if cur idx max out, there would no more next combination, wrong selection.
            sum = Math.max(sum, helper(strs, ii, m-zeros, n-ones) + 1);
        }
        return sum;
    }

    public static void main(String [] args){
        String [] input1 = {"10", "0001", "111001", "1", "0"};
        String [] input2 = {"10", "0", "1"};
        OnesAndZeros474 o = new OnesAndZeros474();
        o.cout(o.findMaxForm(input1, 5, 3));
        o.cout(o.findMaxForm(input2, 1, 1));
    }

    public int findMaxFormDP(String[] strs, int m, int n) {
        int [][] res = new int[m+1][n+1];
        for(String s : strs){

        }
        return res[m][n];
    }

    public int[] count(String str){
        int [] res = new int[2];
        for(int ii = 0 ; ii<str.length(); ii++){
            res[str.charAt(ii)-'0']++;
        }
        return res;
    }
}
