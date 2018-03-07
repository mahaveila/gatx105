package leetcodeii.Arrays;

import java.util.Arrays;

/**
 * Created by Erebus on 3/4/18.
 */
public class TriAngleCount {


    /** naive solution, count number of triangles that could be formed by int in the array
     * @param S: A list of integers
     * @return: An integer
     */
    public int triangleCountNaive(int[] S) {
        // write your code here
        int res = 0;
        for(int i=0; i<S.length; i++){
            for(int j=i+1; j<S.length; j++){
                for(int k=j+1; k<S.length; k++){
                    if((S[i]+S[j])>S[k]){
                        res++;
                    } else {
                        break;
                    }
                }
                //next j
            }
            //next i
        }
        return res;
    }

    /**
     * SLiding window
     */
    public int triangleCount(int[] S) {
        Arrays.sort(S);
        // write your code here
        int res = 0;
        for(int i = 0; i<S.length; i++){
            int st = 0;
            int ed = i-1; //use S[i] as the longest edge
            while(st<ed){
                if((S[st]+S[ed])>S[i]){
                    //can form triangle starting from st
                    res += ed-st;
                    ed--; //make ed smaller
                } else {
                    //still cannot
                    st++;
                    //make st larger
                }
            }
        }
        return res;
    }

}
