package leetcodeii.FourSeries;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Erebus on 9/19/17.
 */
public class ArithmeticSlicesII446 {

    public int numberOfArithmeticSlices(int[] A) {
        /**
         * [2,4,6,8,10]
         * i=0, n = 2, no number before 2, skip
         *      only 1 number
         *
         * i=1, n = 4, j=2, d=2, map[1].put(d=2, 1) --> init for d=2
         *      found 2 number, got first diff, put diff as key "d=2" into [i]=[1] map, with
         *
         * i=2, n = 6, j=2,4, d=4,2, map[2].put(d=2,  map[2].get(2)+map[1].get(1) + 1 = 0+1+1) -> map[2].put(2,2) => res+=1
         *                           map[2].put(d=4, 1)
         *      found >=3 number, for key=2, map exist for key(2)@j=1, means must be >= 2 numbers with same d before, put d->2 (i=2)
         *      found 2 number for key=4 for the first time, put d=4->1, at (i=2)
         *
         *
         * i=3, n = 8,
         *      [j]=[0]=2, d=6, put 6->1 in map[3]
         *      [j]=[1]=4, d=4, map[1] has no 4 pairs, put 4->1 @map[3]
         *      [j]=[2]=6, d=2, map[2] has 2 pairs of d2, res+=2, put 2->3 @map[3]
         *
         * i = 4, n=10,
         *      j@0, d=8, put 8->1 in map[4]
         *      j@1, d=6, map[1] has no d6, put 6->1 in map[4]
         *      j@2, d=4, map[2] has 1 pair, added at i2, res+=1, put 4->2 in map[4]
         *      j@3, d=2, map[3] has 3 pair, added at i3, res+=3, put 2->4 in map[4]
         *
         * in the end,
         * i=2, +1  2,4,6
         * i=3, +2  2,4,6,8  4,6,8
         * i=4, +3  2,4,6,8,10  4,6,8,10  6,8,10
         *      +1  2,6,10
         * total = 7
         *
         * d=2: 2,4,6; 4,6,8; 6,8,10;2,4,6,8;4,6,8,10;2,4,6,8,10
         * d=4: 2,6,10
         */
        int res = 0;
        Map<Integer, Integer>[] maps = new Map[A.length];
        for(int ii=0;ii<maps.length;ii++){
            maps[ii]=new HashMap<>();
        }
        for(int ii=0; ii<A.length; ii++){

            for(int jj = 0; jj < ii; jj++){
                long diff = (long)A[ii]-(long)A[jj];
                if(diff<=Integer.MIN_VALUE || diff>Integer.MAX_VALUE){
                    continue;
                }
                int d = (int)diff;
                //the duplicate prev is for 2,2,3,4, e.g. i=2, num=3, first 2 has been put into cur [3] map,
                //and second 2 wants to count this duplicate, add 1+1, not initially put 1 into cur [3] map
                /**
                 * 2,2,3,4
                 * i=2, j=0, d=1, map[3] put 1->1
                 *      j=1, d=1, map[3] put 1->1 (existing [3] map from i)+1
                 *
                 * before i=4, map[3]has 1->2
                 */
                int duplicatePrev = maps[ii].getOrDefault(d, 0);
                int prev = maps[jj].getOrDefault(d, 0);
                //found prev pairs till ii, append cur ii num to them, make them valid (len>=3, & diff=d) and append to result
                res += prev;
                maps[ii].put(d, duplicatePrev+prev+1);
            }

        }
        return res;
    }

}
