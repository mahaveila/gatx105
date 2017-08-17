package leetcodeii.FourSeries;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Erebus on 8/16/17.
 */
public class MinArrowsBurstBalloons452 {

    public int findMinArrowShots(int[][] points) {
        if(points==null||points.length<1){
            return 0;
        }
        Arrays.sort(points, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]==o2[0]?o1[1]-o2[1]:o1[0]-o2[0];
            }
        });
        int arrows = 1;
        int limit = points[0][1];
        for(int ii = 1; ii<points.length; ii++){
            int [] cur = points[ii];
            if(cur[0]>limit){
                arrows++;
                limit =cur[1];
            } else {
                limit = Math.min(limit, cur[1]);
            }
        }
        return arrows;
    }


}
