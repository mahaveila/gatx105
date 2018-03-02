package leetcodeii.Arrays;

import java.util.Stack;

/**
 * Created by Erebus on 2/14/18.
 */
public class MaximalRectangle85 {
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
                res = Math.max(res, w * nums[tp]);
                i--;
            }
        }
        return res;
    }
}
