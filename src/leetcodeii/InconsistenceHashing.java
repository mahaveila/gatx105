package leetcodeii;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by Erebus on 4/18/18.
 */
public class InconsistenceHashing implements Tracker{

    public static class Range{
        int id;
        int upper;
        int lower;
        int size(){
            return upper - lower;
        }
        public Range(int id, int upper, int lower){
            this.id = id;
            this.upper = upper;
            this.lower = lower;
        }
    }

    public static void main(String [] args){

        InconsistenceHashing ih = new InconsistenceHashing();
        ih.cout(ih.consistentHashing(1));

    }

    public List<List<Integer>> consistentHashing(int n) {
        // write your code here
        List<List<Integer>> res = new ArrayList<>();
        if(n<1){
            return res;
        }
        PriorityQueue<Range> q = new PriorityQueue<>(1, (r1, r2) -> r1.size()==r2.size() ? (r1.id - r2.id) : (r2.size()-r1.size()) );
        Range singleRange = new Range(1, 359, 0);
        q.offer(singleRange);
        int cur = 1;
        while(cur++ < n){
            //cur starts at 2
            Range top = q.poll();
            int mid = (top.upper - top.lower)/2 + top.lower;
            //e.g. 359-0 = 359, 359/2 = 179.
            Range newRange = new Range(cur, top.upper, mid+1);
            top.upper = mid;
            q.offer(top);
            q.offer(newRange);
        }
        while(!q.isEmpty()){
            Range range = q.poll();
            List<Integer> resRecord = new ArrayList<>();
            resRecord.add(range.lower);
            resRecord.add(range.upper);
            resRecord.add(range.id);
            res.add(resRecord);
        }
        return res;
    }
}
