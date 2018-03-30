package leetcodeii.dp;

import leetcodeii.Tracker;

/**
 * Created by Erebus on 3/22/18.
 */
public class RegularExpressionMatch implements Tracker {

    public boolean isMatch(String s, String p) {
        /**
         recap on the ideas:
         0 1 2 3
         a b * c
         0 a
         1 b
         2 b
         3 b
         4 b
         5 c
         character match case:
         the a==a at 0,0, init set 1
         other s[i]==p[j] case, use dp[i-1][j-1], top left corner populate, i.e. [1][1]= s[1](b)==p[1](b)?[0][0]:false

         character not match case:
         . case: any char, just use dp[i-1][j-1]

         * case:
         1. zero count: populate from char c and * 's left, i.e.  ab* -> match a, use dp[i][j-2]
         2. 1 count: populate from left char c, i.e. ab* -> match ab, use dp[i][j-1]
         3. multiple count: requirement: P[j-1] = S[i], e.g. p[2-1]=S[1]=S[2]=S[3]=S[4], use previous matching
         i.e. ab* match  abb based on prev ab,
         abbb              abb
         abbbb             abbb -> [4][2]=true
         finally [5][3] has same char, use top left [4][2] = true

         */

        //use +1 to get around the -1 out of boundary condition
        boolean[][] m = new boolean[s.length()+1][p.length()+1];
        m[0][0]=true;
        for (int i = 0; i < p.length(); i++) {
            //to hand c*a match a case, i=1 is a, j=3 is a after c*, need to set [i-1][j-1] =[0][2] =true
            //for it starts at a is valid.
            if (p.charAt(i) == '*' && m[0][i-1]) {
                m[0][i+1] = true;
            }
        }
        for(int i=1; i<=s.length(); i++){
            //i=1, pick a -> [1][1]=[0][0]=true, [1][2] = [1][1] || [1][0] || [0][2] = true
            //i=2, pick a -> [2][1]=[1][0]=false, [2][2] = [2][1] || [2][0] || [1][2] = true
            for(int j=1; j<=p.length(); j++){

                if(s.charAt(i-1)==p.charAt(j-1)){
                    if(i==1){
                        m[i][j]=true;
                    } else {
                        m[i][j]=m[i-1][j-1];
                    }
                } else if(p.charAt(j-1)=='.'){
                    //wildcard
                    m[i][j]=m[i-1][j-1];
                } else if(p.charAt(j-1)=='*'){
                    boolean res = false;
                    res |= m[i][j-1]; //count 1, imply that char before * already matched
                    res |= m[i][j-2]; //not count at all, imply that char before char+* pattern already match
                    //fall into char+* pattern, multiple count
                    if(p.charAt(j-2)==s.charAt(i-1) || p.charAt(j-2)=='.'){
                        res |= m[i-1][j];
                    }
                    m[i][j]=res;
                }
            }
            cout(m);
        }
        return m[s.length()][p.length()];
    }

    public static void main(String [] args){
        RegularExpressionMatch r = new RegularExpressionMatch();
        r.cout(r.isMatch("aab","c*a*b"));
    }
}
