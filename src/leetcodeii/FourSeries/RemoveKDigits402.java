package leetcodeii.FourSeries;

import leetcodeii.Tracker;

import java.util.Stack;

/**
 * Created by Erebus on 9/10/17.
 */
public class RemoveKDigits402 implements Tracker {
    public String removeKdigits(String num, int k) {
        //key, remove the first char that larger than its left -> repeat k times
        //to optimize the search everytime => stack
        //stack 1. keep push until smaller than before, 2. pop till cur larger than before 3. stop at size k
        if(num==null||num.length()<=k){
            return "0";
        }
        if(k<=0){
            return num;
        }
        Stack<Integer> st = new Stack<>();
        for(int ii =0; ii< num.length();ii++){
            cout(String.format("current ii = %d, k = %d",ii,k));

            int cur= Integer.parseInt(""+num.charAt(ii));
            cout(String.format("  char->'%d'",cur));
            while(!st.isEmpty()&&st.peek()>cur && k>0){
                int tmp = st.pop();
                k--;
                cout(String.format("  pop %d cuz it's no less than %d, k = %d -1 = %d", tmp, cur, k+1, k));
            }
            st.push(cur);
        }
        //1111 coner case, combined with previous peek > cur, otherwise peek>=cur will cause 112,1 -> remove 1 instead of 2
        while(k>0 && !st.isEmpty()){
            st.pop();
            k--;
        }
        StringBuilder sb = new StringBuilder();
        while(!st.isEmpty()){
            sb.append(st.pop());
        }
        String res = sb.reverse().toString();
        int sub = -1;
        for(int jj=0;jj<res.length();jj++){
            if(res.charAt(jj)!='0'){
                break;
            } else {
                sub = jj;
            }
        }
        if(sub<res.length()){
            res = res.substring(sub+1);
        }
        return res.length()>0?res:"0";
    }

    public static void main(String [] args){
        RemoveKDigits402 r = new RemoveKDigits402();
//        r.cout(r.removeKdigits("1432219", 3));
        r.cout(r.removeKdigits("112", 1));
    }
}
