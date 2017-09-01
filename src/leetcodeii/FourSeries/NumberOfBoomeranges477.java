package leetcodeii.FourSeries;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Erebus on 8/19/17.
 */
public class NumberOfBoomeranges477 {
    public int numberOfBoomerangs(int[][] points) {
        if(points==null || points.length<3){
            return 0;
        }
        Map<Integer, Integer> distCount = new HashMap<>();
        int res = 0;
        for(int i = 0; i< points.length; i++){
            for(int j = 0; j< points.length; j++){
                if(i==j){
                    //same point
                    continue;
                }
                int[] p1 = points[i];
                int[] p2 = points[j];
                int d = getDistSq(p1, p2);
                if(distCount.containsKey(d)){
                    distCount.put(d, distCount.get(d)+1);
                } else {
                    distCount.put(d, 1);
                }
            }
            for(int ii : distCount.values()){
                if(ii>1){
                    res += (ii-1)*ii; //i points 2-2 combination, i.e. 5 points => 1+2+3+4 = equal dist addition => n=(i-1). And each combination * 2 cuz bidirection. n*(n+1)/2 = (i-1)*i
                }
            }
            //clear the map for each point, avoid collisions.  e.g. 1,2,3 & 4,5,6
            distCount.clear();
        }



        return res;
    }

    private int getDistSq(int[] p1, int[] p2){
        int hori = p1[0] - p2[0];
        int vert = p1[1] - p2[1];
        return hori*hori + vert*vert;
    }
}
