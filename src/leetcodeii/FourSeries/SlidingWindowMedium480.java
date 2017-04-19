package leetcodeii.FourSeries;

/** TODO: Fix
 * Created by Youming on 4/19/2017.
 */
public class SlidingWindowMedium480 {

    int[] slidingWindowMedium(int  [] input, int k){
        int size = input.length - k + 1;
        int []  res = new int[size];
        for(int ii = 0; ii<size; ii++){
            if(k%2==0){
                res[ii] = (input[(ii+k/2)-1] + input[ii+k/2])/2;
            } else {
                res[ii] = input[ii+k/2];
            }
        }
        return res;
    }

}
