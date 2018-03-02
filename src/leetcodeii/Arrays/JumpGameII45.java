package leetcodeii.Arrays;

import leetcodeii.Tracker;

/**
 * Created by Erebus on 1/30/18.
 */
public class JumpGameII45 implements Tracker{

    public int minJump(int[] A){
        cout(A);
        int sc = 0;
        int e = 0;
        int max = 0;
        for(int i=0; i<A.length-1; i++) {

            max = Math.max(max, i+A[i]);
            cout(String.format("current index = %d, max reach = %d", i, max));
            if( i == e ) {
                cout(String.format("  i=%d, e=%d, i==e, sc++ -> %d, e=%d", i, e, sc+1, max));
                sc++;
                e = max;
            }
        }
        return sc;
    }

    public static void main(String [] args){
        JumpGameII45 j = new JumpGameII45();

        int[] t1={1,2,3,4};
        int[] t2={2,1,3,4};
        j.cout(j.minJump(t1));
        j.cout(j.minJump(t2));
    }
}
