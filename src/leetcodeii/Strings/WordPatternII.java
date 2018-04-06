package leetcodeii.Strings;

import leetcodeii.Arrays.WordLadderII;
import leetcodeii.Tracker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Erebus on 3/31/18.
 */
public class WordPatternII implements Tracker{

    Map<Character, String> map = new HashMap<>();
    Set<String> set = new HashSet<>();

    public boolean wordPatternMatch(String pattern, String str) {
        cout("WP: pattern = " + pattern + ", str = " + str);
        if(pattern==null && str==null){
            return true;
        }
        if(pattern.length()<1 && str.length()<1){
            return true;
        }
        if(pattern.length()<1 || str.length()<1){
            return false;
        }

        Character c = pattern.charAt(0);
        cout("matching : " + c);
        if(map.containsKey(c)){
            //have this pattern
            String value = map.get(c);
            if(value.length()>str.length()){
                return false;
            }
            if(!value.equals(str.substring(0, value.length()))){
                return false;
            }
            cout("pattern found! going to next");
            return wordPatternMatch(pattern.substring(1), str.substring(value.length()));
        } else {
            //haven't seen this pattern before
            if(set.contains(str)){
                //but we find an old value
                return false;
            }
            for(int ii = 1; ii <= str.length(); ii++){
                map.put(c, str.substring(0, ii));
                set.add(str.substring(0, ii));
                if(wordPatternMatch(pattern.substring(1), str.substring(ii))){
                    return true;
                }
                map.remove(c);
                set.remove(str.substring(0, ii));
            }
        }
        return false;
    }

    public static void main(String [] args){
        WordPatternII w = new WordPatternII();
        w.wordPatternMatch("d","e");
    }
}
