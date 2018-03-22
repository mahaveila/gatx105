package leetcodeii.FourSeries;

import leetcodeii.Tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * TODO: SUBMIT, probably has better data structure
 * Created by Youming on 4/19/2017.
 *
 * to maintain a addable, removable and sorted data structure. Also need accessible middle at O(1)
 */
public class SlidingWindowMedium480 implements Tracker<String, Integer>{

    /**
     * Another way to insert is
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> medianSlidingWindow(int[] nums, int k) {
        // write your code here
        List<Integer> res = new ArrayList<>();
        int len = nums.length;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(1);
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(1, (a,b)->b-a);

        //assume we stores >= in min heap, i.e. maxHeap is < minHeap
        for(int ed=0; ed<len; ed++){
            int n = nums[ed];
            if(minHeap.size()==0 && maxHeap.size()==0){
                cout("both empty, add " + n + " to minHeap");
                minHeap.offer(n);
            } else {
                if(!minHeap.isEmpty() && n>=minHeap.peek()){
                    cout(String.format("Adding %d to minHeap", n));
                    minHeap.offer(n);
                } else {
                    cout(String.format("Adding %d to maxHeap", n));
                    //n doesn't meet requirements of max
                    maxHeap.offer(n);
                }
            }
            cout(String.format("     maxHeap size= %d, minHeap size= %d", maxHeap.size(), minHeap.size()));
            //the maintanence
            while(minHeap.size()>maxHeap.size()+1){
                //e.g. minHeap has 3, maxHeap has 1, rebalance to 2,2
                int tmp = minHeap.poll();
                cout(String.format("                Polling %d from minHeap to maxHeap", tmp));
                maxHeap.offer(tmp);
            }
            while(maxHeap.size()>minHeap.size()){
                //e.g. maxHeap=3, minHeap=2, maintain to 2,3
                int tmp = maxHeap.poll();
                cout(String.format("                Polling %d from maxHeap to minHeap", tmp));
                minHeap.offer(tmp);
            }

            if(ed>=k-1) {
                //satisfy the window, now let's move and grab medians
                //ed =k-1, now we have k elements inside heap
                int median = minHeap.peek();
                cout("median = " + median);
                if(maxHeap.size()==minHeap.size()){
                    median = (median+maxHeap.peek())/2;
                }
                res.add(median);
                if(nums[ed-k+1]>=minHeap.peek()){ //i.e. ed - (k-1), the first element in current window
                    //needs to remove that first one, for add the next.
                    minHeap.remove(nums[ed-k+1]);
                    cout("minHeap.pop");
                } else {
                    //doesn't meet the minimun of larger side
                    //pop on smaller side.
                    maxHeap.remove(nums[ed-k+1]);
                }
            }
            cout(String.format("[END]    maxHeap size= %d, minHeap size= %d", maxHeap.size(), minHeap.size()));
        }
        return res;
    }


    /**
     * A very simple way is that maintain a list of integers,
     * for every number n, find it's inserted index, which is O(n)
     * so insertion for all is O(n^2)
     * if use binary search, could be logN. But, the insert on index is O(n), moving elements in space
     * total time is not O(nlogn), but O(n^2)
     * @param input
     * @param k
     * @return
     */
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

        int [] input2 = {1,2,7,7,2,10,3,4,5};
        s.cout(s.medianSlidingWindow(input2, 2));
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
