package leetcodeii.Arrays;

import leetcodeii.Tracker;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO: using DP is straight forward, but how to transform to memorization.
 * Created by Erebus on 4/29/18.
 */
public class CoinChangeII302 implements Tracker {

    Map<Integer, Integer> memo = new HashMap<>();

    private int change(int amount, int[] coins, int end){
        if(amount==0){
            return 1;
        }
        //TODO optimize use memorization
        //BUG: use Memo and starting backward:  has duplicate for case 2->left3 => {..,.., 2,1,1,1}  and case 1,1->left 3 => {1,1,2,1, .. }

        /*
        if doing with DP
        dp[i][a] = previous i coins can have n ways to sum up to a amount.
        i.e.            case {1,2,5}
            a   0   1   2   3   4   5

        i   0   1   0   0   0   0   0       init dp[0][0] = 1;
                                        i=1, a=0, += dp[i-1][a];
            1   1   1   1   1   1   1   i=1, a=1, += dp[i-1][a] -> 0, && dp[1][a-coins[0]]=dp[1][1-1]=dp[1][0] -> 1
                                                            i.e. += dp[1][a-coins[i-1]]
                                        i=1, a=2, += dp[i-1][a] -> 0, += dp[1][a-coins[i-1]]:  coins[i-1]=[1-1]=[0]=1,
                                                                                a=2, 2-1=1, += dp[1][1] -> 1
                                        same for a=3,4,5, use left to populate
            2   1   1   2   2   3   3   i=2, a=0, += dp[i-1][a] -> 1, a-coins[1]=-2,
                                          2,   1,                  1,            -1
                                          2,   2,                  1,            0, += dp[2][0]=1 -> 2
                                          2,   3,                  1,            1,       2  1  1 -> 2
                                          2,   4,                  1,            2,       2  2  2 -> 3
                                            i.e. {1,2} can form 4 in 3 different ways: {2,2}{2,1,1}{1,1,1,1}
                                          2,   5,                  1,            3,       2  3  2 -> 3
            3   1
                                          3,   0                   1  0-5        -5
                                          3,   1                   1  1-5        -4
                                          3,   2                   2             -3
                                          3,   3                   2             -2
                                          3,   4                   3             -1
                                          3,   5                   3             0, += dp[3][0]=1 -> 4

            rules: based on backpacker problem
            1) dp[i][a] += dp[i-1][a], previous coins already have so many ways to form
            2) dp[i][a] += a-coins[i-1]>=0?dp[i][a-coins[i-1]]:0, pick current coin, add new ways to form a value



           //BUG: use Memo and starting backward:  has duplicate for case 2->left3 => {..,.., 2,1,1,1}  and case 1,1->left 3 => {1,1,2,1, .. }
           i.e. after case 2, picked 2
            this senario shouldn't pick 1 again, because amount-2 > 2 itself

        */
        // if(memo.containsKey(amount)){
        //     return memo.get(amount);
        // }
        int res = 0;
        //recursion for coins
        for(int ii = end; ii >= 0; ii --){
            int val = coins[ii]; //pick one coin at ii, we can get val
            if(amount>=val){
                //i.e. a - coins[i-1] >= 0
                res += change(amount - val, coins, ii);
            }
        }
        // memo.put(amount, res);
        return res;
    }

    public int change(int amount, int[] coins) {
        return change(amount, coins, coins.length-1);
    }
    public static void main(String [] args){
        CoinChangeII302 c = new CoinChangeII302();
        int [] coins = {1,2,5};
        c.cout(c.change(5, coins));
    }

}
