package leetcodeii.FourSeries;

/**
 * Created by Youming on 4/19/2017.
 */
public class TotalHammingDistance {

    int totalHammingDist(int [] nums){
        int sum = 0;
        for(int i : nums){
            //TODO: implementation
        }
        return sum;
    }

    int getHammingDistance(int a, int b){
        int sum = 0;
        while(a>0 || b>0){
            sum += (1&a)^(1^b);
            a = a>>1;
            b = b>>1;
        }
        return sum;
    }
}
