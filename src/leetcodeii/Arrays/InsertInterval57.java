package leetcodeii.Arrays;

import leetcodeii.Interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Erebus on 2/4/18.
 */
public class InsertInterval57 {

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<>();
        Collections.sort(intervals, (i1, i2) -> i1.start==i2.start?
                (i1.end - i2.end):(i1.start-i2.start));
        boolean inserted = false;

        for(Interval itv : intervals){
            if(newInterval.end<itv.start){
                res.add(newInterval);
                newInterval = itv;
            } else {
                if(itv.end<newInterval.start){
                    //no overlapping, itv in front
                    res.add(itv);
                } else {
                    //there is an overlapping, merge them
                    newInterval.start= Math.min(itv.start, newInterval.start);
                    newInterval.end=Math.max(itv.end, newInterval.end);
                }
            }
        }
        res.add(newInterval);

        return res;
    }

}
