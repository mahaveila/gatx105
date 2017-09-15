package leetcodeii.FourSeries;

import java.util.Stack;

/**
 * Created by Erebus on 9/12/17.
 */
public class FrogJump403 {

    //TODO TLE
    /**
     * TLE
     * @param units
     * @return
     */
    public boolean canCross(int[] units) {
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
