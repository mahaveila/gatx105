package leetcodeii.FourSeries;

/**
 * Created by Youming on 4/19/2017.
 */
public class MaxConsecutiveOnes485 {

    int maxConsecutiveOnes(int [] nums){
        int global = 0;
        int local = 0;
        for(int i : nums){
            if(i==0){
                global = global >= local ? global : local;
                local = 0;
            } else {
                local ++;
            }
        }
        return global >= local? global : local;
    }

}
