package leetcodeii.dp;

import leetcodeii.Tracker;

/**
 * Created by Erebus on 3/23/18.
 */
public class EditDistance implements Tracker{

    public static void main(String [] args){
        EditDistance ed = new EditDistance();
        ed.cout(ed.minDistance("zoolog",
                "zoog"));
        ed.cout(ed.minDistance("zoologicoarchaeologist",
                "zoogeologist"));
        //zoo lo g icoarcha eologist
        //zoo    g          eologist
        //why zoolg -> zoo is 1?
        /**  z o o g
          [0,1,2,3,4],
        z [1,0,1,2,3],
        o [2,1,0,0,1], zo -> zo is 0
        o [3,2,0,0,1], zoo->zoo is 0, why zoo -> zo is 0?
        l [4,3,1,1,1],
        o [5,4,1,1,2],
        g [6,5,2,2,1],
         */
    }

    public int minDistance(String word1, String word2) {
        /* notice the vertical 0,1,2,3 along c,o,... that's delete char [i][j]=[i-1][j]+1
            a b o d b
          0 1 2 3 4 5 : init, empty char match empty=0, [0][0]=0, empty match any char is 1.
                        empty match abodb is 12345, keep adding chars [i][j] = [i][j-1]+1
        c 1 1 2 3 4 5 : similar c match aboab is replacement at j=0, then add chars: [i][j] = [i][j-1]+1
                        but [1][1]=1 is because [i-1][j-1](pre match, i.e. empty match) +1 (replace)
        o 2 2 2 2 3 4 : when o v.s o, [i]==[j], replace: [i-1][j-1]+0, add: [i][j-1]+1, min=replace=2+0=2, rest of them is add
                       also notice the init 2,2 at j=0,1, first 2 is co->empty delete 2, second also consider replace+[i-1][j-1]
        o 3 3 3 2 3 4 : 3332, the 2 is min(add,del,upd), upd=0, then 0+[i-1][j-1]=2, i.e. coo->abo, replace by 2

        b 4 4 3 3 3 4 : similarly, 443, the 3 is because b==b, therefore [i][j]=replace(0)+[i-1][j-1]=3
                        notice 4433, the 2nd 3 is because delete 1, coob -> abo, i.e. delete b(1), replace coo->abo(2), total=3.
               the 3rd 3: because coo->abo is 2, coob to abod is through replacement: 1+[i-1][j-1]=3
               the last 4, is just add b in the end of abod = 1 + [i-1][j-1]
                   or, it can be coo->abod=3, coob->abodb=3+1, one replacement.

        Overall, cur = s1[i]==s2[j]?0:1
        [i][j]= min (cur+[i][j-1]: add,  cur+[i-1][j-1]: replace, cur+[i][j-1]: delete)
        */

        int len1 = word1.length();
        int len2 = word2.length();
        int [][] dp = new int[len1+1][len2+1];
        //FIXME: always check the i iteration range, make sure cover all and not out of bound.
        for(int i=1; i<=len1; i++){
            //s1 delete chars to match empty (vertical)
            dp[i][0] = dp[i-1][0]+1;
        }
        for(int j=1; j<=len2; j++){
            //empty add chars to match s2 (horizontal)
            dp[0][j] = dp[0][j-1]+1;
        }
        for(int i=1; i<=len1; i++){
            for(int j=1; j<=len2; j++){
                int cur = (word1.charAt(i-1)==word2.charAt(j-1)?0:1);
                //FIXME: KEY: the add and delete is a hard +1, regardless cur's value
                int addOrDel = Math.min(dp[i][j-1]+1, dp[i-1][j]+1);
                //only replace is depends on cur's value
                dp[i][j] = Math.min(addOrDel, dp[i-1][j-1]+cur);
            }
        }
        cout(dp);
        return dp[len1][len2];
    }

}
