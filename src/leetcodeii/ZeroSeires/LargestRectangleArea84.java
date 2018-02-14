package leetcodeii.ZeroSeires;

import leetcodeii.Tracker;

import java.util.Stack;

/**
 * Created by Erebus on 2/14/18.
 */
public class LargestRectangleArea84 implements Tracker{

    public static void main(String [] args){
        LargestRectangleArea84 lr = new LargestRectangleArea84();
        int [] input = {2,1,5,6,2,3};
        int [] input2 = {1,1};
        int [] input3 = {2,1,2};
        lr.cout(lr.largestRectangleArea(input3));
    }

    public int largestRectangleArea(int[] heights) {
        //the result = height * width
        //width = abs |index (i-j)|
        //height = min([i],[j]), minimal dominant
        Stack<Integer> stk = new Stack<>();
        int res= 0;

        /** example: 4,2,0,3,2,5
         0: push 0, i++
         1: 2<4, pop=0, h=4, stk=empty, width = i=1, S=4, || (pop=0,i=1, w=1, S=4), stays 1
         1: stk empty, push 1
         2: 0<2, pop=1, h=2, stk=empty, width=i = 2, S=4, || (pop=1, i=2, w=1, S=2, which is WRONG)
         */


        for(int i=0; i<=heights.length;i++){
            //since we add i=heights.length, will out of index, to finish the right side edge in the end
            int h = i==heights.length? 0 : heights[i];
            if(stk.isEmpty() || heights[stk.peek()]<=h){
                //create or maintain the left edge
                stk.push(i);
            } else {
                //stk.peek()'s height > i's height, need to include stk.peek in area
                //smaller right edge found (smaller than the left edge)
                int leftEdge = stk.pop();
                //calculate area based on the smallest height (left edge) and witdh (right edge - left edge)
                res = Math.max(res, (stk.isEmpty()?i:(i-stk.peek()-1)) * heights[leftEdge]);
                //index stays same to compare next left edge with right edge, if smaller goes here, otherwise goes to push i++ bucket
                i--;
            }
        }

        return res;
    }

    /**
     * FIXME: this is wrong because i-pop miss the very beginning of 3,2,1, when stk is empty, the width should be i, not i-pop
     * TODO: therefore, stk is empty, means all the left edges are larger than right edge, goes back to the 0 (beginning)
     * it only calculates 3->2, 2->1, e.g.
     * example: 4,2,0,3,2,5
     0: push 0, i++
     1: 2<4, pop=0, h=4, stk=empty, width = i=1, S=4, || (pop=0,i=1, w=1, S=4), stays 1
     1: stk empty, push 1
     2: 0<2, pop=1, h=2, stk=empty, width=i = 2, S=4, || (pop=1, i=2, w=1, S=2, which is WRONG)
     * @param heights
     * @return
     */
    public int largestRectangleAreaWrong(int[] heights) {
        //the result = height * width
        //width = abs |index (i-j)|
        //height = min([i],[j]), minimal dominant
        Stack<Integer> stk = new Stack<>();
        int res= 0;

        for(int i=0; i<=heights.length;i++){
            int h = i==heights.length?0:heights[i];

            if(stk.isEmpty() || heights[stk.peek()]<=h){
                stk.push(i);
            } else {
                //cur i has a height smaller than the left edge bar, calculate that area, pop to next left edge
                //until finds a left edge smaller than cur, that edge will be used with next right edge.
                int prevIdx = stk.pop();
                cout(String.format("width between %d and %d is %d", prevIdx, i, i-prevIdx));
                res = Math.max(res, heights[prevIdx] * (stk.isEmpty()?i:(i-prevIdx)));
                i--;
            }
        }

        return res;
    }

}
