package leetcodeii.Strings;

import leetcodeii.Tracker;

/**
 * Created by Erebus on 4/2/18.
 */
public class Atoi implements Tracker{

    public int myAtoi(String str) {
        long res = 0;
        if(str==null || str.length()<1){
            return 0;
        }
        int flag = 1;
        int start = 0;
        while(str.charAt(start)==' '){
            start ++;
        }
        str = str.substring(start);
        if('-' == str.charAt(0)){
            str = str.substring(1);
            flag = -1;
        } else if('+'== str.charAt(0)){
            str = str.substring(1);
        }
        cout("flag: " + flag);

        for(int ii = 0; ii < str.length(); ii ++){
            char c = str.charAt(ii);
            if(Character.isDigit(c)){
                res = res * 10 + (c-'0');
            } else {
                return (int)(res*flag);
            }
            if(flag>0 && res>Integer.MAX_VALUE){
                //out of boundary
                return Integer.MAX_VALUE;
            } else if(res*flag < Integer.MIN_VALUE){
                return Integer.MIN_VALUE;
            }
        }
        cout("res no sign: " + res);

        return (int)(res*flag);

    }

    public static void main(String [] args){
        Atoi a = new Atoi();
        a.cout(a.myAtoi("-2147483648"));
    }

}
