package leetcodeii.FourSeries;

import leetcodeii.Tracker;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: SUBMIT, probably has better data structure
 * Created by Youming on 4/19/2017.
 *
 * to maintain a addable, removable and sorted data structure. Also need accessible middle at O(1)
 */
public class SlidingWindowMedium480 implements Tracker<Integer>{

    double[] slidingWindowMedium(int[] input, int k) {
        int size = input.length - k + 1; //len - k = extra ele, + itself as a beginning window
        size = size > 0 ? size:1;
        double[] res = new double[size];

        List<Integer> ls = new ArrayList<>();
        for(int ii = 0; ii<k & ii<input.length; ii++){
            insert(input[ii], ls);
        }
        cout("preset finished " + stringfy(ls));
        for(int jj=k; jj< input.length; jj++){
            cout(String.format("looping %d", jj));
            res[jj-k] = getMedium(ls);
            ls.remove(indexOf(input[jj-k], ls));
            insert(input[jj], ls);
        }
        res[res.length-1] = getMedium(ls);
        return res;
    }

    private void insert(int n, List<Integer> list){
        int ii = 0; // insert index
        for(; ii < list.size(); ii++){
            if(n<list.get(ii)){
                break;
            } //>=, keep moving
        }
        String pre = stringfy(list);

        if(ii>=list.size()){
            list.add(n);
        } else {
            list.add(ii, n);
        }
        String post = stringfy(list);
        cout(String.format("inserting %d at %d: %s -> %s", n, ii, pre, post));
    }

    private int indexOf(int n, List<Integer> ls){
        int res = -1;
        for(int ii = 0; ii<ls.size();ii++){
            if(ls.get(ii)==n){
                res = ii;
                break;
            }
        }
        return res;
    }

    private double getMedium(List<Integer> ls){
//        cout("getting medium .. ");
//        if(ls.size()%2==0){
//            cout("even");
//            cout(String.format("first [%d]=%d, second [%d]=%d", ls.size()/2, ls.get(ls.size()/2), ls.size()/2-1, ls.get(ls.size()/2-1)));
//        } else {
//            cout("odd");
//        }

        return ls.size()%2==0? (((double) ls.get(ls.size()/2)) + ((double )ls.get(ls.size()/2 -1)))/2 : (double) ls.get(ls.size()/2);
    }

    public static void main (String [] args){
//        int[] input = {1,3,-1,-3,5,3,6,7};
        int[] input = {1,4,2,3};

        SlidingWindowMedium480 s = new SlidingWindowMedium480();
//        s.cout(s.slidingWindowMedium(input, 3));
        s.cout(s.slidingWindowMedium(input, 4));
    }

    /**
     * where to find next medium? cannot just store mid info for shortcut
     * 1) Option 1, maintain an AVL tree O(nlogn), -> O(n^2logn)
     * 2) Option 2, brutal sorting, need algo of quickly find medium O(nlogn), O(n^2logn)
     */

        /*
    int[] slidingWindowMedium(int  [] input, int k){
        int size = input.length - k + 1;
        int []  res = new int[size];
        for(int ii = 0; ii<size; ii++){
            if(k%2==0){
                res[ii] = (input[(ii+k/2)-1] + input[ii+k/2])/2;
            } else {
                res[ii] = input[ii+k/2];
            }
        }
        return res;
    }*/

}
