package leetcodeii.dp;

import leetcodeii.Tracker;

import java.util.Arrays;

/**
 * Created by Erebus on 3/24/18.
 */
public class CopyBooks implements Tracker{
    /**
     * @param pages: an array of integers
     * @param k: An integer
     * @return: an integer
     */
    public int copyBooks(int[] pages, int k) {
        // write your code here

        /**

         basic idea: find min avg for all subarray
         use dp[i][j]: till i th books given to j people
         init condition: dp[0][0] = integer.Max
         dp[0][1] = books[0];
         KEY: dp[i][1] = books[i]; given each book to 1 person

         dp[1][2] = min(books[0],[1]), dp[i][i+1] = min(min, books[i])
         dp[i][j]: if j<i+1, there are more books then ppl
         for each i, j:
         params:
         k=j-i-1, the books overhead
         p    and h, dp[h][j-1]:  h books given to prev j-1 ppl, h<i.
         n    new person will get i-h ~ i books. i.e. SUM:(books[i-h]:books[i])

         KEY: since it's just j-1, new person's time: T[i][1]-T[h][1]
         i.e. 即一个人copy从h＋1到i本需要的时间）

         for h's iteration:
         we knows that h must be > j-1, because if h<j-1, less book than ppl,
         i.e. mustn't be the best answer.


         */

        if(pages == null || pages.length == 0){
            return 0;
        }

        if(k < 1){
            return -1;
        }

        int n = pages.length;
        int[][] T = new int[n + 1][k + 1];

        for(int j = 1; j <= k; j++){
            //first 1 book distributed to all person
            //only one of them can copy, so equal for all of ppl
            T[1][j] = pages[0];
        }

        int sum = 0;
        for(int i = 1; i <= n; i++){
            sum += pages[i - 1];
            //i.e. [i][i+1] case, keep rolling, 1 person copy all i books
            T[i][1] = sum;
        }

        for(int i = 2; i <= n; i++){
            for(int j = 2; j <= k; j++){
                if(j > i){
                    //j is ppl, more ppl than books, someone is free
                    T[i][j] = T[i][j - 1];
                    continue;
                }
                int min = Integer.MAX_VALUE;
                //h starting from 1
                //but when h<j-1, more books than ppl, not ideal, so skip
                for(int h = j - 1; h <= i; h++){
                    //for each h:
                    //j-1 ppl copy h books, last 1 copy i-h books
                    int temp = Math.max(T[h][j - 1], T[i][1] - T[h][1]);
                    //the max means take whoever is the slowest one, the result

                    //then updating the global minimum result, i.e. fastest
                    min = Math.min(min, temp);
                }
                T[i][j] = min;
            }
        }

        return T[n][k];
    }

    /**
     * @param pages: an array of integers
     * @param k: An integer
     * @return: an integer
     */
    public int copyBooksBinary(int[] pages, int k) {
        // write your code here

        /**
         * we can also use the binary search
         *
         * let's assume given the limit for a person can read p pages at most
         *
         * for each page at i:
         * if alreadyRead + pages[i] <= p (that person can read more)
         * alreadyRead += page[i++];
         *
         * else if (page[i] > p){
         the book itself is larger than limit, we need to increase limit
         } else {
         //book is reasonable size, and the guy cannot read any more
         pass to next guy
         pplCount ++;
         alreadyRead = 0;
         }


         in the end, return pplCount <= k (total ppl available)
         if true, at most k ppl can finish we can decrease the limit
         (i.e. less page per person, more ppl)
         if false, we need more ppl to read, we need to increase the limit
         so, the page[i]>p case will also be align with this case, return false
         *
         */
        if(pages==null || pages.length<1){
            return 0;
        }
        int total = Arrays.stream(pages).reduce(0, (a, b) -> a + b);
        int l = 0;
        int r = total;
        int count = 0;
        /**
         * FIXME:
         * ERROR: l=4, r=5, mid=4, easy to get into infinite loops,
         therefore, use mid+1 and mid-1.
         l=mid+1, in case of above
         */
        while(l<r && count++<20){
            int mid = (l + r)/2;
            cout("l="+l+", r=" + r + ", mid="+mid);
            if(canFinish(pages, mid, k)){
                //reduce pagePerPerson
                r = mid;
                cout("can finish, r = mid = " + r);
            } else {
                l = mid+1;
                cout("cannot finish, l = mid = " + l);
            }
        }
        return l;
    }

    public boolean canFinish(int [] pages, int pagePerPerson, int k){
        int ppl = 1;
        int read = 0;
        for(int i=0; i<pages.length; ){
            if(pages[i]+read <= pagePerPerson){
                //read more
                read += pages[i++];
            } else if(pages[i]>pagePerPerson){
                //book too large
                return false;
            } else {
                //pass to next guy
                ppl++;
                read = 0;
            }
        }
        //true, can increase ppl, i.e. total/ppl = pagePerPerson decrease
        //false, need more ppl, need to reduce ppl, pagePerPerson increase
        return ppl <= k;
    }

    public static void main(String [] args){
        CopyBooks c = new CopyBooks();
        int [] books = {3,2,4};
        c.copyBooksBinary(books, 2);
    }
}
