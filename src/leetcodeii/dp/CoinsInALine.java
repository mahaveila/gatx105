package leetcodeii.dp;

/**
 * Created by Erebus on 3/25/18.
 */
public class CoinsInALine {
    /**
     * @param n: An integer
     * @return: A boolean which equals to true if the first player will win
     * FIXME: this will leads to stack overflow
     */
    public boolean firstWillWin(int n) {
        // write your code here
        /**
         basic idea: 1 st player's move
         first player will take last coin, or last 2 coins, i.e.
         dp[i] stands for current play will win, when there is i coins left.
         dp[0]=false
         dp[1]=true
         dp[2]=true

         dp[i] = !recursion(dp, i-1) && !recursion(dp, i-2)
         ERROR: after take 1 or 2 coins, next play will not win in both situation.
         only need 1 one them win -> can win, isn't must win

         */
        boolean[] dp = new boolean[n+1];
        boolean[] calculated = new boolean[n+1];
        return moveWin(n, dp, calculated);
    }

    private boolean moveWin(int n, boolean [] dp, boolean [] calculated){
        if(calculated[n]){
            return dp[n];
        }
        if(n==0){
            dp[n]=false;
        } else if(n==1 || n==2){
            dp[n]=true;
        } else {
            dp[n] = !moveWin(n-1, dp, calculated) || !moveWin(n-2, dp, calculated);
        }
        calculated[n]=true;
        return dp[n];
    }

    /**
     * use %3 method, one maintaining %3==0 coins left, the other cannot win.
     * A maintain 3,  B take 1, A can take 3-1 = 2 to keep maintain
     * same for B take 2.
     *
     * canWin(n) {return n%3!=0}
     */
}
