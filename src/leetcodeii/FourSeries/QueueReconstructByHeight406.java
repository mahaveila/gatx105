package leetcodeii.FourSeries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Erebus on 9/8/17.
 */
public class QueueReconstructByHeight406 {

    /**
     * Key is find the max Height peoples, sort input desc.
     * Based on tallest people, add into queue using k (how many before them), *.add(k, person)
     * @param people
     * @return
     */
    public int[][] reconstructQueue(int[][] people) {
        if(people==null){
            return new int[0][2];
        }
        List<int[]> res = new ArrayList<>(people.length);
        int[][] re = new int[people.length][2];
        if(people.length<1){
            return re;
        }
        Arrays.sort(people, (b1, b2)->b1[0]==b2[0]?b1[1]-b2[1]:b2[0]-b1[0]);
        int max = people[0][0];
        for(int ii = 0 ; ii< people.length; ii++){
            if(max == people[ii][0]){
                res.add(people[ii]);
            } else {
                res.add(people[ii][1], people[ii]);
            }
        }

        for(int ii = 0 ;ii<res.size();ii++){
            re[ii] = res.get(ii);
        }
        return re;
    }
}
