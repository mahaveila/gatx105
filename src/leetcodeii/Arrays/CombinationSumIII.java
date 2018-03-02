package leetcodeii.Arrays;

import leetcodeii.Tracker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erebus on 2/27/18.
 */
public class CombinationSumIII implements Tracker {


    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        combo(res, new ArrayList<>(), k, n, 1);
        return res;
    }

    /** use k numbers to sum up to n, val is next available int
     */
    private void combo(List<List<Integer>> res, List<Integer> curList, int k, int s, int val){
        cout("   combo: val - " + val + " : " + stringfy(curList));
        if(s<0){
            cout(" --- killed");
            //either sum is short or count is short (below), i.e. too large combo or too small combo, early termination
            return; // do nothing about the result
        }
        if(k==0){
            if(s==0){
                //find a match
                cout("find combo: " + stringfy(curList));
                res.add(new ArrayList<>(curList));
                return;
            } else {
                cout(" --- killed");
                return; // running out of count
            }
        }

        //manage all the next values to sum, control the recursion
        //FIXME: assume previous call already add i=3, and curVal=2, next val=3, will be adding duplicate 3
        //FIXME: fixed, therefore, combo use i+1 to next, instead of val+1 (which will allow the duplicate)
        for(int i = val; i<10; i++){
            //add number horizontally to curlist -> 1,2; 1,3; 1,4... etc
            curList.add(i);
            combo(res, curList, k-1, s-i, i+1); //add number vertically: 1,2; 2,3; 3,4;
            //reset for next val
            curList.remove(curList.size()-1);
        }
    }

    public static void main(String [] args){
        CombinationSumIII c = new CombinationSumIII();
        c.combinationSum3(3,7);
    }
}
