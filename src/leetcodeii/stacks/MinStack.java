package leetcodeii.stacks;

import java.util.Stack;

/**
 * Created by Erebus on 3/13/18.
 */
public class MinStack {
    Stack<Integer> num;
    Stack<Integer> min;

    public MinStack() {
        // do intialization if necessary
        min = new Stack<>();
        num = new Stack<>();
    }

    /*
     * @param number: An integer
     * @return: nothing
     */
    public void push(int number) {
        // write your code here
        num.push(number);
        if(min.isEmpty()){
            min.push(number);
        } else {
            //only push number to min when it's smaller than top of the min stack
            //FIXME: fixed: the equals sign is in case of duplicate elements run min stack dry
            if(number<=min.peek()){
                min.push(number);
            }
        }
    }

    /*
     * @return: An integer
     */
    public int pop() {
        // write your code here
        int n = num.pop();
        if(n==min.peek()){
            min.pop();
        }
        return n;
    }

    /*
     * @return: An integer
     */
    public int min() {
        // write your code here
        return min.peek();
    }
}
