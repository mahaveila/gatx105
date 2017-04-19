package leetcodeii.FourSeries;

import java.util.ArrayList;
import java.util.List;

/** TODO: test
 * Created by Youming on 4/19/2017.
 */
public class IncreasingSubSequence491 {

    List<List<Integer>> increaseSubseq(int [] input){
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        for(int ii = 0 ; ii < input.length;ii++){
            List<List<Integer>> tmp = new ArrayList<>();
            for(List<Integer> list : res){
                if(list.isEmpty() || list.get(list.size() -1) <= input[ii]){
                    List<Integer> duplicate = new ArrayList<>();
                    duplicate.addAll(list);
                    duplicate.add(input[ii]);
                    tmp.add(duplicate);
                }
            }
            res.addAll(tmp);
        }
        return res;
    }

}
