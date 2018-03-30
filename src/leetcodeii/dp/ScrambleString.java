package leetcodeii.dp;

import leetcodeii.Tracker;

/**
 * Created by Erebus on 3/24/18.
 */
public class ScrambleString implements Tracker{

    public static void main(String [] args){
        ScrambleString s = new ScrambleString();
        s.isScramble("abcd","badc");
    }

    public boolean isScramble(String s1, String s2) {
        /**
         s1 divided into [    |         ]
         i    q        | i+k
         |
         v
         i+k-1
         so does s2, use j
         s1 and s2 need to scramble corresponding part
         i.e. (s1[i, q] can scramble s2[j, q]) && (s1[i+q, i+k] can scramble s2[j+q, j+k])

         and another case:
         s2 divided into [        |     ]
         j               j+k
         j+k-q
         need  (s1[i,q] can scramble s2[j+k-q, j+k])  && (s1[i+q, i+k] can scramble s2[j, j+k-q])

         so use dp[k][i][j] stands for at length k,
         substring starts from i in s1, and substring starts from j in s1 can scramble or not.

         the init condition is if k==1, [k][i][j]=s1.charAt(i)==s2.charAt(j);
         no need to +1, cuz no external init condition required, i.e. empty case etc.

         */
        int len = s1.length();
        char[] cs1 = s1.toCharArray();
        char[] cs2 = s2.toCharArray();

        int[] counts = new int[26];
        for(char c : cs1){
            counts[c-'a']++;
        }
        for(char c : cs2){
            counts[c-'a']--;
        }
        for(int i : counts){
            if(i!=0){
                return false;
            }
        }

        boolean[][][] dp = new boolean[len][len][len];
        for(int i=0; i<len; i++){
            for(int j=0; j<len; j++){
                if(cs1[i]==cs2[j]){
                    dp[0][i][j]=true;
                }
            }
        }
        for(int k=2; k<=len; k++){
            /*
            Key is to use i,j from len-k till 0, because i+(k-1) should < len, so i+k <= len
            why starting from back? it doesn't matter, starting from 0 also works.
            */
            // for(int i=len-k; i>=0; i--){
            //     for(int j=len-k; j>=0; j--){

            for(int i=0; i<=len-k; i++){
                //max i or j is len-k, i.e. len-2, 2nd last element
                for(int j=0; j<=len-k; j++){

                    for(int q=1; q<k; q++){
                        dp[k-1][i][j]=(dp[q-1][i][j] && dp[k-q-1][i+q][j+q])
                                || (dp[q-1][i][j+k-q] && dp[k-q-1][i+q][j]);
                        //q-1 min = 0, k-q min =1, k-q-1 min=0.
                        //q-1 max, k-1-1 = k-2
                        if(dp[k-1][i][j]){
                            break;
                        }
                    }

                }
            }
            cout(dp[k-1]);
        }
        cout(dp[len-1]);
        return dp[len-1][0][0];
    }

}
