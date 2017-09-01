package leetcodeii.FourSeries;

import leetcodeii.Tracker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erebus on 8/30/17.
 */
public class FindAnagrams438 implements Tracker{

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if(s==null || s.length()<1 || p==null || p.length()>s.length()){
            return res;
        }
        //init the window
        int[] window = new int[256];
        for(char c : p.toCharArray()){
            window[c]++;
        }
        //should have total p.length() incremental
        //iterating and reduce count if cur char exists, when count drops to 0, means an anagram
        int left =0, right = 0, count=p.length(); //key, using the length and count to 0 as boundary
        char[] cs = s.toCharArray();
        while(right<s.length()){
            //from first char to the last char

            //check current char, reduce count for current char in window, and point right to the next char
            //result:<0, then not exist before, >=0:then exist before
            cout(String.format("Loop %s: window value = %d, count = %d", ""+cs[right],window[cs[right]], count));
            if(window[cs[right++]]-- >= 1){
                //exist, add right char into cur window = reduce its value (count left to be matched) in window
                count --; //reduce count of To Be Matched, one less char to be matched
                cout(String.format("    exist: window value = %d-1=%d, count = %d-1=%d", window[cs[right-1]]+1,window[cs[right-1]], count+1,count));
            }

            if(count==0){
                res.add(left); //starting of the window
            }

            if(right-left==p.length()){
                //maintain the window, move left to next
                //
                if(window[cs[left++]]++ >=0){
                    //kick left char out of window = increase its value in window
                    count ++; //increase 1 more count for next match (one more char to match)
                    cout(String.format("    maintain: window value = %d+1=%d, count = %d+1=%d", window[cs[left-1]]-1,window[cs[left-1]], count-1, count));
                }
            }
        }
        return res;
    }

    public static void main (String [] args){
        FindAnagrams438 f = new FindAnagrams438();
        f.cout(f.findAnagrams("cbaebabacd","abc"));
    }

}
