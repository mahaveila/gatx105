package leetcodeii.Arrays;

import leetcodeii.Tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Erebus on 2/14/18.
 */
public class MaximalRectangle85 implements Tracker {
    /**
     * FIXME: acttually one array is enough, instead of 2D matrix
     * @param matrix
     * @return
     */
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length<1 || matrix[0].length<1){
            return 0;
        }
        int r = matrix.length;
        int c = matrix[0].length;
        int res = 0;

        int[][] M = new int[r][c];

        for(int i = 0; i < r; i++){
            for(int j = 0; j< c; j++){
                //each row stores max continuous value vertically
                if(i==0){
                    M[i][j]=matrix[i][j]-'0';
                } else {
                    M[i][j]=matrix[i][j]=='0'?0:(matrix[i][j]-'0'+M[i-1][j]);
                }
                // matrix[i][j]=matrix[i][j]==0?matrix[i][j]:(matrix[i][j] + matrix[i-1][j]);
            }
        }
        /**
         now we have
         1 0 1 0 0
         2 0 2 1 1
         3 1 3 2 2
         4 0 0 3 0
         */
        for(int[] row : M){
            res = Math.max(res, rowMax(row));
        }
        return res;
    }

    //calculate max rectangle for each row
    private int rowMax(int [] nums){
        int res = 0;
        //parent col isn't empty
        Stack<Integer> st = new Stack<>();
        List<Integer> ls = new ArrayList<>();
        //use <= nums.length to move the cursor out of nums,
        //so it include the case that: edge keeps increasing till the end
        for(int i=0; i<=nums.length; i++){
            int h = i==nums.length? 0:nums[i]; //getting the right edge
            if(st.isEmpty() || h>= nums[st.peek()]){
                //increasing height, keep pushing right edge
                st.push(i);
            } else {
                //smaller h found, grab the left edge
                int tp = st.pop();
                //use i-st.peek()-1 to include the very left edge of cur rectangle
                //if st.isEmpty, then use the i=-1, which has a height of 0, width=i
                int w = st.isEmpty()? i : (i-st.peek()-1);

                ls.add(w * nums[tp]);
                res = Math.max(res, w * nums[tp]);
                i--;
            }
        }
        cout(ls);
        cout(nums[nums.length-1]+":"+ res);
        return res;
    }

    public static void main(String [] args){
        boolean[][] input = {{true, false, false, true},{false, true, true, true},{true, false, false, true},{true, false, true, true}};
        MaximalRectangle85 m = new MaximalRectangle85();
        m.cout(m.maximalRectangle(largeinput));
    }

    /**
     * FIXME: maxRect is correct, But the aggregate function is wrong!
     * @param matrix
     * @return
     */
    public int maximalRectangle(boolean[][] matrix) {
        // write your code here
        /**
         * approach 1, aggregate from up to bottom if continuous 1
         * 1 1 0 0 1
         * 0 1 0 0 1
         * 1 0 1 1 1
         * become
         * 1 1 0 0 1
         * 0 2 0 0 2   agg[jj]=matrix[ii][jj]?(agg[jj]+1):0;
         * 1 0 1 1 3
         *
         * by doing this, 2D compressed to 1D
         * then we just find the max in 1D: using the max-rectangle approach for 1D
         *
         * i.e. increasing sequence + stack solution
         */
        if(matrix==null || matrix.length<1 ||matrix[0].length<1){
            return 0;
        }
        int[] agg = new int[matrix[0].length];
        int max = 0;
        for(int ii=0; ii<matrix.length; ii++){
            for(int jj=0; jj<matrix[0].length; jj++){
                //two situation
                if(ii==0){
                    //1 first row, no aggregation
                    agg[jj]=matrix[ii][jj]?1:0;
                } else {
                    //2 other rows, aggregation from top to bottom
                    int prev = agg[jj];
                    if(matrix[ii][jj]){
                        agg[jj] = prev + 1; //prev must be 0 if prev boolean = false
                    } else {
                        agg[jj]=0;
                    }
                }

            }

            max = Math.max(max, maxRect(agg));

        }
        return max;
    }

    /**
     * FIXME: use that while is Dangerous, it cannot get back to -1, the left boundary when it also has st is empty check in there.
     *
     * //FIXME: remember to use st.peek() as index AND GET THE VALUE! to compare
     * @param agg
     * @return
     */
    private int maxRect(int[] agg){

        if(agg[agg.length-1]!=31){
            return 0;
        }
        cout(agg);

        List<Integer> res = new ArrayList<>();
        rowMax(agg);

        Stack<Integer> st = new Stack<>();
        //to maintain a increasing sequence
        int local = 0;
        for(int ii=0; ii<=agg.length; ii++){
            int h = ii==agg.length? 0:agg[ii];

            if(st.isEmpty() || h>=agg[st.peek()]){
                cout("push");
                st.push(ii);
            } else {
                /**
                 * if [i] >= st.peek, increasing keep adding
                 * else pop st until find the element smaller than [i]
                 * each pop do a local calculate
                 */

                while(!st.isEmpty() && (ii==agg.length || agg[ii]<agg[st.peek()])){
                    if(ii==agg.length){
                        cout("   >>>   ii reaches the end");
                    } else {
                        cout(String.format("   >>>   agg[ii] @ %d = %d is less than peek = %d", ii, agg[ii], agg[st.peek()]));
                    }

                    //cur is the edge, at least 1 index behind the poped element
                    int pre = st.pop();
                    int w = st.isEmpty()?ii:ii-st.peek()-1; //since current is right boundary, and left edge is also boundary, need to -1 is the width
                    cout(String.format("       pre=%d, ii=%d, agg[pre]=%d, calculation= %d", pre, ii,agg[pre], agg[pre]*(w)));
                    local = Math.max(local, agg[pre]*w);
                }
                if(st.isEmpty()){
                    cout("   <<< stack is empty");
                } else {
                    cout(String.format("   <<<   agg[ii] @ %d = %d is NO less than peek = %d", ii, agg[ii], agg[st.peek()]));
                }
                st.push(ii);
                /**
                 * FIXME: this is WRONG! DO NOT put empty boundary here!
                 * TODO: should move push condition with st empty + is increasing sequence, else pop.
                 * for case 18, 12, 8
                 * after 18 poped, left boundary of 18, i.e. [-1]=0, is still there. need to reach that index to calculate 12 X 2
                 * and 8 X 3.
                 *
                 * now that boundary is broken because after 18 poped, st is empty! Need to find a way to reach that!
                 *
                 *

                 while(!st.isEmpty() && (ii==agg.length || agg[ii]<agg[st.peek()])){
                    if(ii==agg.length){
                        cout("   >>>   ii reaches the end");
                    } else {
                        cout(String.format("   >>>   agg[ii] @ %d = %d is less than peek = %d", ii, agg[ii], agg[st.peek()]));
                    }

                 //cur is the edge, at least 1 index behind the poped element
                    int pre = st.pop();

                    cout(String.format("       pre=%d, ii=%d, agg[pre]=%d, calculation= %d", pre, ii,agg[pre], agg[pre]*(ii-pre)));
                    local = Math.max(local, agg[pre]*(ii-pre));
                 }
                 if(st.isEmpty()){
                    cout("   <<< stack is empty");
                 } else {
                    cout(String.format("   <<<   agg[ii] @ %d = %d is NO less than peek = %d", ii, agg[ii], agg[st.peek()]));
                 }
                    st.push(ii);

                 */

            }
        }
        cout(res);
        cout("row = " + agg[agg.length-1]+ ", local max = " + local);
        return local;
    }

    static boolean[][] largeinput = {{true,true,true,true,true,true,true,true,true,true,true,true,true,true,false,true,false,false,true,true,true,true,true,true,true,true,false,false,true,true,true,false,true,true,true,true,true,true,true,true},{true,true,true,true,false,true,true,false,true,true,true,true,true,true,true,true,true,false,true,true,false,true,true,true,true,true,false,true,true,true,true,true,true,true,true,true,true,true,true,true},{false,true,true,true,true,false,true,false,true,true,true,true,true,true,false,true,true,false,true,true,false,true,true,false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},{false,true,false,true,true,false,true,false,true,true,true,true,true,true,true,true,true,true,true,true,false,true,true,true,true,true,true,true,false,true,false,true,true,false,true,true,true,true,true,true},{true,true,true,true,true,true,true,true,true,true,true,true,false,true,true,true,true,true,false,true,true,false,false,true,false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},{true,true,true,true,true,true,true,true,true,false,true,true,false,true,false,true,true,true,true,true,true,true,true,true,true,true,false,true,false,true,true,true,true,true,true,false,true,true,true,true},{false,true,true,false,true,true,false,true,false,true,true,true,false,false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,false,true,true,true,true,true,false,true,false,true},{false,true,true,true,true,true,true,true,true,true,true,true,true,true,false,false,true,true,true,true,true,true,true,false,false,true,true,false,false,true,true,false,true,true,false,true,false,true,false,true},{true,true,true,true,false,true,true,true,true,false,true,true,true,true,true,true,true,true,true,false,true,true,false,true,true,false,true,true,true,true,false,true,false,true,true,false,true,false,true,true},{true,true,true,true,true,true,true,true,true,false,true,true,true,true,true,true,true,true,true,true,false,true,true,false,true,true,false,true,true,true,false,true,true,true,true,false,true,true,true,true},{true,true,true,false,true,true,false,false,true,true,true,true,true,true,true,true,true,true,true,true,false,false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},{true,false,true,true,true,true,true,true,true,false,true,true,true,true,false,true,true,true,true,false,false,true,true,true,false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},{false,true,true,false,true,true,true,true,true,true,true,true,true,true,true,true,true,false,true,true,true,true,true,true,false,true,true,true,false,true,true,true,true,true,false,true,true,true,true,true},{true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,false,true,true,true,true,true,true,true,true,true,true,true,true,true,false,true,true,true,true,true,true,false,true,true},{true,true,true,true,true,false,false,true,true,true,true,true,true,true,true,false,true,false,true,true,false,false,true,true,true,true,true,true,true,true,true,true,true,true,true,false,true,true,true,true},{true,true,true,true,true,false,true,true,true,true,true,true,true,true,true,false,true,true,true,true,true,true,false,true,true,true,true,true,true,true,true,true,true,true,false,true,true,true,true,true},{true,true,true,true,true,false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,false,true,true,false,false,true,true,true,true,true,true,false,false,true,true,true,true,true},{true,true,true,true,true,true,false,true,true,true,true,true,true,true,true,true,false,true,true,true,true,true,true,true,true,true,true,true,true,true,false,true,true,true,true,true,false,true,true,true},{true,false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,false,true,false,true,true,true,true,true,false,false,true,false,true,true,true,true,true,false,true,true,true,true,true,true},{true,true,true,true,true,true,false,false,true,true,true,true,true,true,true,true,true,true,true,true,true,false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,false,true,true},{true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,false,true,true,true,false,true,true,true,true,true,false,true,true,true,true,true,false,true,true,false,true,true},{true,true,false,false,false,true,true,false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,false,true,true,true,true,true,false,true,true,true,true,true,true,true,true,true,true,true},{true,true,true,true,true,false,true,false,true,true,true,true,true,true,true,true,true,true,true,true,false,true,true,false,false,true,false,true,true,true,false,false,true,true,true,true,true,true,true,true},{true,true,true,false,false,true,false,true,true,true,true,true,true,true,true,true,true,true,true,false,true,true,false,true,true,true,true,false,true,true,true,true,false,true,true,true,true,true,false,true},{true,true,true,true,true,true,true,true,true,true,true,true,true,true,false,true,true,false,true,true,true,true,true,true,true,true,true,true,true,false,true,true,true,true,true,true,true,true,true,true},{true,true,true,true,true,true,true,false,true,true,true,true,true,true,false,true,true,true,true,false,true,true,true,true,true,true,true,true,true,true,true,true,true,false,true,true,true,true,true,true},{true,true,true,false,false,true,true,true,true,true,true,true,true,true,true,false,true,true,true,false,true,true,true,true,true,true,true,true,true,true,false,true,true,true,true,true,false,true,true,true},{true,true,true,true,true,true,true,true,true,true,true,true,true,true,false,false,true,true,true,true,true,true,false,true,false,true,true,true,true,true,true,true,true,true,true,true,false,true,true,true},{true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,false,false,false,true,true,true,true,true,true,true,true,true,false,true,true,true,false,true},{true,true,true,true,true,true,false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,false,true,true,false,false,true,true,true,false,true,true,false,true,true},{true,true,true,true,false,true,true,false,true,true,true,true,true,true,false,true,true,false,true,true,false,true,true,true,true,true,true,false,true,true,true,true,true,true,true,false,true,true,true,true},{true,true,true,true,true,true,true,true,true,true,false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},{true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},{true,true,false,false,false,true,true,true,true,true,true,true,true,true,true,false,true,true,true,true,true,true,true,true,true,true,true,true,true,false,true,true,true,true,true,true,true,false,true,true},{true,true,true,true,true,false,true,true,true,true,true,true,true,true,false,true,true,true,true,false,true,false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},{false,true,true,true,true,true,true,true,true,true,true,true,false,false,true,true,true,true,true,true,true,true,true,true,false,true,false,true,false,true,true,false,true,true,true,true,true,true,true,true},{true,false,true,true,false,true,true,true,true,true,true,false,true,true,true,true,true,true,true,true,true,true,true,false,true,true,true,true,true,true,true,true,true,true,true,true,false,false,true,true},{true,false,true,false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,false,false,true,true,true,true,true},{false,true,true,true,true,false,true,true,true,true,false,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,false,true,true,true,true,true,true,true,true,true},{false,true,true,true,true,true,true,false,true,true,true,true,true,true,true,true,false,true,true,true,false,true,true,true,true,false,true,true,true,false,true,true,true,true,true,true,true,true,true,true},{false,true,true,true,true,true,true,true,true,true,true,true,false,true,false,true,true,true,true,false,true,true,true,true,true,true,false,true,false,true,true,false,false,true,true,true,true,false,true,true},{true,true,true,true,true,true,true,true,true,true,true,true,true,false,true,true,true,true,false,true,true,true,true,true,true,true,false,true,true,true,true,true,true,true,true,false,true,true,true,false},{true,true,true,true,true,false,true,true,true,true,true,true,true,true,false,false,true,true,true,true,true,true,true,true,true,true,true,false,true,true,true,true,true,true,false,false,true,true,true,true},{true,true,false,true,true,false,true,true,true,true,true,true,false,true,false,true,true,true,true,true,false,true,true,true,true,true,true,true,true,false,false,true,true,true,false,true,false,true,false,false},{false,true,true,false,true,true,true,true,true,true,true,false,false,true,true,true,true,true,false,false,true,false,true,true,true,true,true,false,true,true,true,false,true,true,false,true,true,true,false,true}};
}
