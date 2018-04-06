package leetcodeii;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Erebus on 4/1/18.
 */
public class LoggerRateLimiter implements Tracker {

    public static void main(String [] args){
        LoggerRateLimiter l = new LoggerRateLimiter();
        l.cout(l.shouldPrintMessage(1, "foo"));
        l.cout(l.shouldPrintMessage(2, "bar"));
        l.cout(l.shouldPrintMessage(3, "foo"));
        l.cout(l.shouldPrintMessage(8, "bar"));
        l.cout(l.shouldPrintMessage(10, "foo"));
        l.cout(l.shouldPrintMessage(11, "foo"));
    }

    Map<String, Integer> map;
    /** Initialize your data structure here. */
    public LoggerRateLimiter() {
        map = new HashMap<>();
    }

    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
     If this method returns false, the message will not be printed.
     The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        Integer prev = map.put(message, timestamp);
        if(prev==null || (timestamp - prev.intValue() >= 10)){
            return true;
        } else {
            if(prev!=null){
                map.put(message, prev); //reset to the unexpired first value
            }
            return false;
        }
    }

}
