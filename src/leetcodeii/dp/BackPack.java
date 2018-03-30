package leetcodeii.dp;

import leetcodeii.Tracker;

/**
 * Created by Erebus on 3/27/18.
 */
public class BackPack implements Tracker{

    public static void main(String [] args){
        BackPack b = new BackPack();
        int[] in = {3,4,8,5};
        b.backPack(10, in);
    }

    public int backPack(int m, int[] A) {
        // write your code here
        // int[] bag = new int[m+1];
        // return backPack(bag, m, 0, A);


        /**
         basic idea: dp[i][M]: previous i elements could fill up M
         */

        boolean [][] dp = new boolean[A.length+1][m+1];
        dp[0][0] = true;

        for(int ii=1; ii<=A.length; ii++){

            //KEY: jj starts from 0, cuz needs result populated from [0][0] to [1][0]
            //because 0, ele1, simply 0 can fill up 0.

            //Because we need previous [ii-1][0] can be filled up, which should be true
            for(int jj=0; jj<=m; jj++){
                //previous elements already have jj
                //OR: jj - a[i], before filled in a[i], rest of space filled previously
                //!! for 2nd case, we also need to check we have enough space,
                //!!! otherwise, jj-A[i] will be negative, out of index
                dp[ii][jj] = dp[ii-1][jj] || (jj-A[ii-1]>=0 && dp[ii-1][jj-A[ii-1]]);
                if(dp[ii][jj]){
                    cout("prev "+ii+" elements can fill up jj="+jj);
                }
            }
        }
        cout(A);
        cout(dp);
        for(int jj=m; jj>=0; jj--){
            if(dp[A.length][jj]){
                return jj;
            }
        }
        return 0;
    }

    // private int backPack(int[] bag, int remaining, int i, int[] A){

    //     if(bag[remaining]>0){
    //         return bag[remaining];
    //     }
    //     int res = 0;
    //     for(int j=i+1; j<A.length && A[j]<=remaining; j++){
    //         res= Math.max(backPack(bag, remaining-A[j], j, A) + A[j], res);
    //     }
    //     bag[remaining] = res;
    //     return res;
    // }
}
