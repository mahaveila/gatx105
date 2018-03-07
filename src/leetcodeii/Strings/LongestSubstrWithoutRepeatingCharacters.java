package leetcodeii.Strings
        ;

import leetcodeii.Tracker;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Erebus on 3/4/18.
 */
public class LongestSubstrWithoutRepeatingCharacters implements Tracker {

    public int lengthOfLongestSubstring(String s) {
        cout(s);
        // write your code here
        Map<Character, Integer> map = new HashMap<>();
        int st=-1;
        int max=0;
        for(int ed=0; ed<s.length(); ed++){
            char c = s.charAt(ed);
            if(!map.containsKey(c)){
                cout(String.format("map doesn't contain %s, fill in with index = %d", c, ed));
                //never exist before
                map.put(c, ed);
            } else {
                //exist before

                //FIXME: fixed, be careful with st = map.get(c);, could jump back to previous existing elements
                st = Math.max(map.get(c), st);
                cout(String.format("map contains %s, at idx = %d", c, st));
                //repeat from last, e.g. a,b,a 0,1,2, len=2-0=2
                //or e.g. a,b,c,d,e,b 012345 5 repeat at 1 len=4
                map.put(c, ed);
            }
            cout("  st = " + st + ", ed = " + ed);
            max = Math.max(ed-st, max);
        }
        //missing the last piece, check after all the iteration again

        return max;
    }

    public static void main(String [] args){
        LongestSubstrWithoutRepeatingCharacters l = new LongestSubstrWithoutRepeatingCharacters();
        l.cout(l.lengthOfLongestSubstring("an++--viaj"));
    }
}
