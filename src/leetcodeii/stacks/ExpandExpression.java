package leetcodeii.stacks;

import leetcodeii.Tracker;

import java.util.Stack;

/**
 * Created by Erebus on 3/13/18.
 */
public class ExpandExpression implements Tracker{


    public static void main(String [] args){
        ExpandExpression ee = new ExpandExpression();
        ee.cout(ee.expressionExpand("3[2[ad]3[pf]]xyz"));
    }


    /**
     * @param s: an expression includes numbers, letters and brackets
     * @return: a string
     */
    public String expressionExpand(String s) {
        // write your code here

        /**
         * 1) keep 2 stacks
         * 2) stack num push number
         * 3) stack str push previous string have formed
         * 4) tmp string keep adding characters found
         * 5) when a char, append to tmp
         * 6) when a number, add to number, checking '-' behavior
         * 7) when a [, push number and tmp
         * 8) when a ], tmp = str.pop + num.pop * [tmp]
         */

        char[] cs = s.toCharArray();
        Stack<Integer> num = new Stack<>();
        Stack<String> str = new Stack<>();
        //assume we ignore special chars
        String tmp = "";
        String n = "";
        for(Character c : cs){
            if(c>='0' && c<='9'){
                n += c;
                cout("n="+n);
            } else if((c>='a'&&c<='z') || (c>='A'&&c<='Z')){
                tmp += c;
                cout("tmp="+tmp);
            } else if(c=='['){
                str.push(tmp);
                cout("push: " + tmp);
                tmp="";
                //assume we don't have abc[[, i.e. two buckets continuously
                //and if so, we treat it as 0
                if(n.length()>0){
                    num.push(Integer.parseInt(n));
                    cout("pushing: "+ Integer.parseInt(n));
                } else {
                    num.push(0);
                    cout("pushing: 0");
                }
                n="";
            } else if(c==']'){
                String prev = str.pop();
                int count = num.pop();
                StringBuilder sb = new StringBuilder();
                sb.append(prev);
                for(int i = 0; i<count; i++){
                    sb.append(tmp);
                }
                tmp = sb.toString();
            }
        }
        return tmp;
    }
}
