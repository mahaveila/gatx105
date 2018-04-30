package leetcodeii.Strings;

import leetcodeii.Tracker;

import java.util.Arrays;

/**
 * Created by Erebus on 4/26/18.
 */
public class NextClosestTime  implements Tracker{

    public static void main(String [] args){
        NextClosestTime n = new NextClosestTime();
        n.cout(n.nextClosestTime("19:34"));
    }

    public String nextClosestTime(String time) {
        String timed = time.replaceAll(":", "");
        int [] input = new int[4];
        for(int ii = 0; ii < 4; ii ++){
            input[ii] = timed.charAt(ii) - '0';
        }
        Arrays.sort(input);
        int cur = strToTime(timed);
        cout("timed = "+time+", cur = " + cur);
        int nextTime = getNext(cur, input, "");
        if(nextTime == Integer.MAX_VALUE){
            return ""+input[0] + input[0] + ":" + input[0] + input[0];
        }
        return timeToString(nextTime);
    }

    private int getNext(int cur, int [] input, String time){
        if(time.length() == input.length){
            cout("time = " + time);
            if(isValidTime(time) && strToTime(time)>cur){
                cout("valid, return " + strToTime(time));
                return strToTime(time);
            } else {
                cout("not valid, return -1, strToTime is: " + strToTime(time));
                return -1;
            }
        }
        int min = Integer.MAX_VALUE;
        for(int ii = 0; ii < input.length; ii ++){
            int tmp = getNext(cur, input, time+input[ii]);
            if(tmp >= 0){
                min = Math.min(min, tmp);
            }
        }
        return min;
    }

    //define range
    private boolean isValidTime(String s){
        cout("checking: " + s);
        //hour 00~09, 10~19
        if(s.charAt(0) > '2'){
            cout("  hour > 2");
            return false;
        }
        //hour 20~23
        if(s.charAt(0) == '2' && s.charAt(1) > '3'){
            cout("  hour > 24 ");
            return false;
        }
        //minute 00 ~ 59
        if(s.charAt(2) > '5'){
            cout("  minute > 59");
            return false;
        }
        return true;
    }

    //input 4 digits
    private int strToTime(String s){
        return Integer.parseInt(s.substring(0,2)) * 60 + Integer.parseInt(s.substring(2,4));
    }

    private String timeToString(int i){
        int hour = i/60;
        int minute = i%60;
        return "" + hour + ":" + minute;
    }
}
