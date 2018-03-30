package leetcodeii.dp;

import leetcodeii.Tracker;

/**
 * Created by Erebus on 3/22/18.
 */
public class LongestPalindromeSubstring implements Tracker{

    public String longestPalindrome(String s) {
        if(s==null || s.length()<1){
            return null;
        }

        int[][] p = new int[s.length()][s.length()];
        int[] res = {0,0};
        int max = 1;
        for(int i=0; i<p.length; i++){
            p[i][i] = 1;
        }
        char [] cs = s.toCharArray();
        //[i][j] stands for from i to j, the max palindrome length
        //if i,j not palindrome found, end at j, =0.

        for(int i=p.length-1; i>=0; i--){
            for(int j=i+1; j<p.length; j++){
                if(j==i+1){
                    //neighbours
                    p[i][j]=cs[i]==cs[j]?2:0;
                } else if(j==i+2){
                    //has one char in middle
                    p[i][j]=cs[i]==cs[j]?3:0;
                } else {
                    if(cs[i]==cs[j]){
                        /**
                         * Key: Need to maintain continuous palin
                         */
                        if(cs[i+1]==cs[j-1] && p[i+1][j-1]>0){
                            p[i][j]=2+p[i+1][j-1];
                        } else {
                            p[i][j]=0;
                        }
                    }
                }
                if(p[i][j]>max){
                    cout("find palin at: "+ i +", "+j);
                    max=p[i][j];
                    res[0]=i;
                    res[1]=j;
                }
            }
            cout(p);
        }
        if(max>0){
            return s.substring(res[0],res[1]+1);
        } else {
            return "";
        }
    }


    public static void main(String [] args){
        LongestPalindromeSubstring l = new LongestPalindromeSubstring();
//        l.cout(l.longestPalindrome("abcdasdfghjkldcba"));

        l.cout(l.longestPalindromeBoolean("bb"));
    }

    public String longestPalindromeBoolean(String s) {
        // write your code here
        boolean [][] dp = new boolean[s.length()][s.length()];
        /**
         * the basic idea is that:
         dp[i][j] stands for whether i to j is palindrome

         init dp[i][i]=true;

         if s.charAt(i)==s.charAt(j):
         //because of i+1 and j-1 feature,
         iteration order is from bottom up, left to right
         1) if j-i > 2, dp[i][j] = dp[i+1][j-1]; [a][a-z][a-z][a]
         2) if j-i == 2, dp[i][j] = true, 3 chars  [a][any][a]
         3) if j-1 == 1, dp[i][j] = true, 2 chars  [c][c]
         */
        int max = s.length()<1?0:1;
        String p = s.length()<1?"":(""+s.charAt(0));

        for(int ii = 0; ii < dp.length; ii ++){
            dp[ii][ii] = true;
        }

        for(int ii = s.length()-1; ii >= 0; ii--){
            for(int jj = ii+1; jj < s.length(); jj++){
                if(s.charAt(ii) == s.charAt(jj)){
                    if(jj - ii > 2){
                        dp[ii][jj] = dp[ii+1][jj-1];
                    } else {
                        //3 chars or 2 chars
                        dp[ii][jj] = true;
                    }
                } else {
                    dp[ii][jj] = false;
                }
                if(dp[ii][jj]){
                    cout("find palin at: "+ ii +", "+jj);
                    cout(s.substring(ii, jj+1));
                }
                if(dp[ii][jj] && jj-ii+1>max){
                    max = jj-ii+1;
                    p = s.substring(ii, jj+1);
                }
            }
        }

        return p;
    }

}
