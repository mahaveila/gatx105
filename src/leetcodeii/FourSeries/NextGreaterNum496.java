/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leetcodeii.FourSeries;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


/**
 *
 * @author Erebus
 */
public class NextGreaterNum496 {
    public int [] nextGreaterNum(int [] subset, int [] master){
        if(subset==null){
            return null;
        }
        if(subset.length<1){
            return new int[0];
        }
        int [] res = new int[subset.length];
        Map<Integer, Integer> map = nextGreater(master);
        for(int ii = 0; ii<res.length;ii++){
            res[ii] = map.get(subset[ii]);
        }
        return res;
    }
    
    private Map<Integer, Integer> nextGreater(int [] a){
        Stack<Integer> lastMax = new Stack<>();
        Map<Integer, Integer> next = new HashMap<>();
        //backward
        for(int ii = a.length -1 ; ii>=0; ii--){
            if(ii<a.length -1 ){
                while(!lastMax.isEmpty()&&a[ii]>=lastMax.peek()){
                    lastMax.pop();
                }
            }
            if(lastMax.isEmpty()){
                next.put(a[ii], -1);
            } else {
                next.put(a[ii], lastMax.peek());
            }
            lastMax.push(a[ii]);
        }
        return next;
    }
}
