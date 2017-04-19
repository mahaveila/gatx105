package leetcodeii.FourSeries;

/**
 * Created by Youming on 4/19/2017.
 */
public class PredictTheWinner486 {

    boolean p1win(int [] nums){
        int p1=0, p2=0, start=0, end=nums.length-1;
        int order = 0;
        while(start<=end){
            if(order%2==0){
                p1 += nums[start] > nums[end]? nums[start++]:nums[end--];
            } else {
                p2 += nums[start] > nums[end]? nums[start++]:nums[end--];
            }
            order ++;
        }
        return p1 > p2;
    }

}
