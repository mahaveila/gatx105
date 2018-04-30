package leetcodeii.Arrays;

import leetcodeii.Tracker;

/**
 * Created by Erebus on 4/20/18.
 */
public class MinimumCycleSection implements Tracker{

    public static void main(String [] args){
        MinimumCycleSection m = new MinimumCycleSection();
        int [] size2 = {1,2,1,2,1};
        int [] size3 = {1,2,3,1,2,3};
        int [] size6 = {1,2,1,2,1,6};
        m.cout(m.minimumCycleSection(size2));
        m.cout(m.minimumCycleSection(size3));
        m.cout(m.minimumCycleSection(size6));
    }

    /**
     * @param array: an integer array
     * @return: the length of the minimum cycle section
     */
    public int minimumCycleSection(int[] array) {
        // Write your code here



        /**
         * need to find if two regions are cycle.
         *
         * brutal force,
         * for each i
         *      if j == i, find i~j-1 till j~2j-1 is cycle or not.
         *  int k = size = j-i;
         *  if two sections are cycles, i.e.
         *  [i          j           ]
         *              j=i+k       j+k
         * numbers in i~i+k,  j~j+k are same
         *
         * i.e. if [i    i1    i+k]
         *                           [j     j1   j+k]
         * if i~i1 & j~j1 are cycle section
         * && i1~i+k   &&  j1~j+k  are cycle section
         *
         * i~i+k && j~j+k are cycle section
         */
        int len = array.length;
        //dp stands for starting from ii, the next k length is cycle
        //  boolean [][] dp = new boolean[len][len];
        //  for(int ii = 0; ii < len; ii ++){
        //      //each characters are cycle secion.
        //      dp[0][ii] = true;
        //  }
        //time limit exceed, we need to remember the result calculated before.

        //further more, if we found a cycle,
        //that cycle must be cycle same from the beginning
        //i.e. every cycle is as same as the first cycle.
        boolean dp[][] = new boolean[len+1][len];
        //dp stands for dp[k][ii], from ii till ii+k is cycle.
        for(int ii = 0; ii < len; ii ++){
            dp[0][ii] = true; // ii to 0 is cycle
        }
        int res = len;
        for(int k = 1; k <= len; k ++){
            //this guy can be optimized.
            //  if(isCycle(array, 0, len, k)){
            //      return k;
            //  }

            // ii iteration


            //the starting of ii only can be 0, 1, 2, 3, 4  ii+1    k=1
            //or  0, 2, 4, 6 ii+2        k=2
            //or  0, 3, 6 ii+3     k=3
            boolean cycle = true;
            dp[k][0] = dp[k-1][0];
            for(int ii = k; ii < len; ii=ii+k) {

                //corner case ii+k might be > than len
                //dp[k-1][ii] means from ii till ii+k-1 are cycle from beginning.

                //i.e. 1 1 1 1, will have all ture
                // 1 2 1 2 have 1,2,1,2   T F T F,  k=1,  ii=0  ii=1, 2, {1 v.s 2}  ii=2   dp[k-1]
                //then k=2 see if 3,5,7,etc == 1,
                //1 2 1 2 will be T T T T
                //1 2 1 2 1 6 will be T T T T T F
                if (ii + k - 1 >= len) {
                    break;
                }
                //k=3,    ii=3    ii+k-1=[5] = 3
            //                         |
              //5%3 = [2]=3            v
                //1 2 3        ii->1 2 3   1 2 -> dp[k-1][ii]  array[ii+k-1] == array[(ii+k-1)%k]
                //cout("k= " + k + ", ii = "+ii+", comparing [" + (ii+k-1) + "]="+array[ii+k-1]+", with beginning [" + (ii + k - 1) % k+"]="+array[(ii + k - 1) % k]);
                //first check for [ii       ] ii+k-1 section, then check if ii+k-1 is cycle as well

                /**
                 * k row,   n, n/2, n/3, n/4 = n(1 + 1/2 + 1/3 + 1/4 + ... 1/n)
                 * 123 123
                 * k=1   row[1]  ii: 012345:  TFFTFF
                 * k=2   row[2]  ii: 024      TFFFFF
                 * k=3   row
                 */
                if (dp[k - 1][ii] && array[ii + k - 1] == array[(ii + k - 1) % k]) {
                    cout("[O]             k= " + k + ", ii = "+ii+", comparing [" + (ii+k-1) + "]="+array[ii+k-1]+", with beginning [" + (ii + k - 1) % k+"]="+array[(ii + k - 1) % k]);
                    dp[k][ii] = true;
                } else {
                    //
                    cout("                        dp[k-1][ii] = " + (dp[k-1][ii]?"true":"false"));
                    cout("[X]             k= " + k + ", ii = "+ii+", comparing [" + (ii+k-1) + "]="+array[ii+k-1]+", with beginning [" + (ii + k - 1) % k+"]="+array[(ii + k - 1) % k]);
                    cycle = false;
                }
            }

            if(cycle){
                cout("find all cycles at k " + k);
                //all true
                res = Math.min(res, k);
            }
        }
        cout(dp);
        //dp might not work lets brutal force first
        return res;
    }

    private boolean isCycle(int [] array, int st, int ed, int k){
        for(int ii = st; ii < ed; ii ++){
            //e.g. k=n,   1%n=1, n-1%n=n-1.
            if(array[ii]!=array[st+ii%k]){
                return false;
            }
        }
        return true;
    }
}
