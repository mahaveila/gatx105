package leetcodeii.FourSeries;

import java.util.*;

/**
 * Created by Erebus on 9/12/17.
 */
public class FrogJump403 {


    /**
     * Use set to avoid duplicate dests from different steps, O(1)
     * use map to store past steps that might cross, reduce overlapping.
     * skip storing steps for the last one, i.e. the final dest
     * @param units
     * @return
     */
    public boolean canCross(int[] units) {

        Map<Integer, Set<Integer>> map = new HashMap<>();
        //init
        map.put(0, new HashSet<>());
        map.get(0).add(1);
        for(int ii =1; ii< units.length; ii++){
            //put in existing dest
            map.put(units[ii], new HashSet<>());
        }
        for(int ii = 0; ii<units.length; ii++){
            int stone = units[ii];
            for(int step:map.get(stone)){
                int reach = stone + step;
                if(reach == units[units.length-1]){
                    return true;
                }
                //get the dest stones, e.g. init 0->{1}, dest=1.
                Set<Integer> set = map.get(reach);
                if(set != null){
                    //can move to next step (that the dest exist), add k-1, k, k+1
                    set.add(step);
                    if(step-1>0){
                        set.add(step-1);
                    }
                    set.add(step+1);
                }
            }
        }
        return false;
    }

    //TODO TLE, to improve, use hash map to store what steps can be taken to the stone i -> {prev0, prev1, ... }
    //This process is populated by
    //On each step, we look if any other stone can be reached from it, if so, we update that stone's steps by adding step, step + 1, step - 1

    /**
     * TLE
     * @param units
     * @return
     */
    public boolean canCrossTLE(int[] units) {
        boolean res = false;
        Stack<Integer> next = new Stack<>();
        Stack<Integer> dist = new Stack<>();
        next.push(0);
        dist.push(1);
        while(!next.isEmpty()){
            int cur = next.pop();
            int k = dist.pop();
            if(cur==units.length-1){
                return true;
            }
            for(int ii = cur; ii<units.length; ii++){
                if(units[ii]-units[cur]>=k-1 && units[ii]-units[cur]<=k+1){
                    next.push(ii);
                    dist.push(units[ii]-units[cur]);
                } else if(units[ii]-units[cur]>k+1){
                    break;
                }
            }
        }
        return false;
    }

}
