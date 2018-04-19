package leetcodeii.Arrays;

import leetcodeii.Tracker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erebus on 4/14/18.
 */
public class ContinuousSubarraySum implements Tracker{


    public static void main(String [] args){
        ContinuousSubarraySum c = new ContinuousSubarraySum();
        int [] input = {-3,1,3,-3,4};
        c.cout(c.continuousSubarraySum(input));


    }


    public List<Integer> continuousSubarraySum(int[] A) {
        // write your code here
        cout(A);
        List<Integer> res = new ArrayList<>();
        res.add(0);
        res.add(0);
        if(A.length<1){
            return res;
        }
        int global = A[0];
        int local = A[0];
        int pre = 0;
        for(int ii = 1; ii < A.length; ii++){
            if(A[ii] > local + A[ii]){
                pre = ii;
                local = A[ii];
            } else {
                local += A[ii];
            }
            cout("ii = " + ii + ", local = " + local);
            if(local > global){
                global = local;
                res.set(0, pre);
                res.set(1, ii);
            }
        }
        return res;
    }
}
