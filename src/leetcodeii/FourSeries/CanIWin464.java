package leetcodeii.FourSeries;

import leetcodeii.Tracker;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Erebus on 8/7/17.
 */
public class CanIWin464 implements Tracker{


        Map<Integer, Boolean> res;
        boolean [] used;

        public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
            int sum = (1 + maxChoosableInteger)*maxChoosableInteger/2;
            if(sum<desiredTotal){
                return false;
            }
            if(desiredTotal<=0){
                return true;
            }
            res = new HashMap<>();
            //by default, all of them are not used.
            used = new boolean[maxChoosableInteger+1];
            return helper(desiredTotal);
        }

        private boolean helper(int target){
            //p1's turn, target already <=0, p1 lost
            if(target<=0){
                return false;
            }
            //p1's move
            int key = format(used);
            if(!res.containsKey(key)){
                //new combo from pool
                cout(Integer.toBinaryString(key)+" [X], -----> target = " + target);
                //FIXME: done, ii needs to start from 1, to reflects the value drawn from the pool, thus used length will be total + 1
                for(int ii = 1; ii<used.length; ii++) {
                    if (!used[ii]) {
                        //not used
                        used[ii] = true;
                        //next player's move, if alreay <=0, current move wins, and next player lost
                        if (!helper(target - ii)) {
                            //store result, and reset pool
                            res.put(key, true);
                            cout("  -  "+Integer.toBinaryString(key)+"  can win");
                            used[ii] = false;
                            return true;
                        }
                        //reset
                        used[ii] = false;
                    }
                }
                cout("  -  "+Integer.toBinaryString(key)+" -------------------- cannot win");
                //not win yet
                res.put(key,false);
            } else {
                cout(Integer.toBinaryString(key)+" [O] =====> " + res.get(key));
            }
            return res.get(key);
        }

        private int format(boolean [] bools){
            int key = 0;
            for(boolean u : used){
                key <<=1;
                if(u){
                    key |=1;
                }
                //first iteration 0<<=1 -> 0, 0|1 = 1;
                //second iteration 1<<=1 -> 10, 10|1=11;
            }
//            cout(Integer.toBinaryString(key));
            return key;
        }


        public static void main(String [] args){
            CanIWin464 c = new CanIWin464();
            System.out.println(c.canIWin(4, 6));
            /**
             * 0 [X], ----->
             1000 [X], ----->
             1100 [X], ----->
             -  1100  can win
             1010 [X], ----->
             -  1010  can win
             1001 [X], ----->
             -  1001  can win
             -  1000 -------------------- cannot win
             -  0  can win
             true
             */
        }

}
