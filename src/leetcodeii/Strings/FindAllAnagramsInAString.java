package leetcodeii.Strings;

import leetcodeii.Tracker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erebus on 4/2/18.
 */
public class FindAllAnagramsInAString implements Tracker{

    /**
     * TODO better approach:
     *
     *  the basic idea is get hash from pattern
     *  and maintain a counter of how many match left,
     *
     *  Move right side of the window:
     *  when hash[char] >= 1, means it's coming from pattern, then we reduce it by 1 -> found 1 for the pattern
     *  And we reduce hash[char] like always.
     *
     *  Move left side of the window
     *  when pop a char, we might want to add 1 empty slot for to be find
     *  if( hash[char] >= 0 ) means it has >=1 before poped.
     *  else if hash[char] < 0, means we don't have this char in the first place, counter for ToBeMatch doesn't increase for this char
     *
     *  KEY: i.e. we only increase and decrease counter for those chars come from pattern.
     *  w
     *  which indicates how many left to be matched.
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagramsBetter(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0) return list;

        int[] hash = new int[256]; //character hash

        //record each character in p to hash
        for (char c : p.toCharArray()) {
            hash[c]++;
        }
        //two points, initialize count to p's length
        int left = 0, right = 0, count = p.length();

        while (right < s.length()) {
            //move right everytime, if the character exists in p's hash, decrease the count
            //current hash value >= 1 means the character is existing in p
            if (hash[s.charAt(right)] >= 1) {
                count--;
            }
            hash[s.charAt(right)]--;
            right++;

            //when the count is down to 0, means we found the right anagram
            //then add window's left to result list
            if (count == 0) {
                list.add(left);
            }
            //if we find the window's size equals to p, then we have to move left (narrow the window) to find the new match window
            //++ to reset the hash because we kicked out the left
            //only increase the count if the character is in p
            //the count >= 0 indicate it was original in the hash, cuz it won't go below 0
            if (right - left == p.length() ) {

                if (hash[s.charAt(left)] >= 0) {
                    count++;
                }
                hash[s.charAt(left)]++;
                left++;

            }


        }
        return list;
    }

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int [] map = new int[26];
        int [] window = new int[26];
        for(int jj = 0; jj < p.length(); jj++){
            window[p.charAt(jj)-'a'] ++;
        }
        int winSt = 0;
        cout(window);
        cout(p);
        for(int ii = 0; ii < s.length(); ii++){
            cout("ii = " + ii + " char is " + s.charAt(ii) + ", map: " + stringfy(map));
            if(ii<p.length()){
                //doesn't have enough string yet
                //e.g. p = abc, and jj is at 2, we just need to add c -> abc
                map[s.charAt(ii)-'a'] ++;
            } else {
                //jj at 3, now we've had 'abc' inside map, winSt = 0
                if(clear(map, window)){
                    //see if acb anagram
                    res.add(winSt);
                }
                //now we have to remove s.charAt(winSt) from window, corresponding --, winSt -> 1
                map[s.charAt(winSt++)-'a']--;
                //now we add jj=3, 'd' to the map -> bcd
                map[s.charAt(ii)-'a']++;
            }
        }
        //compare the map and window for one last time.
        if(clear(map, window)){
            res.add(winSt);
        }
        return res;
    }

    private boolean clear(int [] map, int [] window){

        for(int ii = 0; ii < map.length; ii++){
            if(map[ii]!=window[ii]){
                return false;
            }
        }
        return true;
    }

    public static void main(String [] args){
        FindAllAnagramsInAString f = new FindAllAnagramsInAString();
        f.cout(f.findAnagrams("cbaebabacd", "abc"));

    }
}
