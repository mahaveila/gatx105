package leetcodeii.FourSeries;

import leetcodeii.Tracker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/** TODO: test
 * Created by Youming on 4/19/2017.
 */
public class IncreasingSubSequence491 implements Tracker<Integer, Integer>{

    /**
     * no pro: FIXME, the pro in one time input iteration is at cost of temp table iterations
     * also works!
     * TODO: method 1, analysis complexity
     */
    List<List<Integer>> increaseSubseqOld(int [] input){
        Set<List<Integer>> res = new HashSet<>();
        res.add(new ArrayList<>());
        for(int ii = 0 ; ii < input.length;ii++){
            List<List<Integer>> tmp = new ArrayList<>();
            for(List<Integer> list : res){
                //every previous list
                if(list.isEmpty() || list.get(list.size() -1) <= input[ii]){
                    List<Integer> duplicate = new ArrayList<>();
                    duplicate.addAll(list);
                    duplicate.add(input[ii]);
                    tmp.add(duplicate);
                }
            }
            res.addAll(tmp);
        }
        return res.stream().filter(ls->ls.size()>1).collect(Collectors.toList());
//        return res.stream().filter(l->l.size()>1).collect(Collectors.toList());
    }

    //Optimized, just iterate input, no iteration on the temp list
    List<List<Integer>> increaseSubseq(int [] input){
        Set<List<Integer>> res = new HashSet<>();
        List<Integer> holder = new ArrayList<>();
        helper(res, holder, 0,input);
        return res.stream().collect(Collectors.toList());
    }

    /**
     * the solution doesn't really handling the duplicates, but it avoid the incorrect combinations than above
     * @param res
     * @param tmp
     * @param index
     * @param input
     * TODO: method 2, analyze big O, works
     */
    private void helper(Set<List<Integer>> res, List<Integer> tmp, int index, int[] input){
        if(tmp.size()>1){
            cout(tmp);
            //[MISS] this will actually add values into result, otherwise, original res.add(tmp) will have tmp empty because caller removed the element after helper called
            res.add(new ArrayList<>(tmp));
        }

        for(int ii = index; ii<input.length; ii++){
            if(input[ii]==input[index]&&ii>index){
                continue;
            }
            if(tmp.size()<1 || tmp.get(tmp.size()-1)<=input[ii]){
                tmp.add(input[ii]);
                helper(res, tmp, ii+1, input);
                tmp.remove(tmp.size()-1);
            }
        }
    }

    public static void main(String [] args){
        IncreasingSubSequence491 iss = new IncreasingSubSequence491();
        int[] input = {4,6,7,7};
        List<List<Integer>> output = iss.increaseSubseqOld(input);
        iss.coutlistlist(output);
    }

}
