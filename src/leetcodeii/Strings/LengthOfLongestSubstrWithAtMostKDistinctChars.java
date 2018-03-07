package leetcodeii.Strings;

import leetcodeii.Tracker;

import java.util.*;

/**
 * Created by Erebus on 3/4/18.
 */
public class LengthOfLongestSubstrWithAtMostKDistinctChars implements Tracker{

    /**
     * FIXME: fixed, need to think througly where and when exactly to update the global variable, don't mixed up
     * @param s
     * @param k
     * @return
     */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        // write your code here
        Map<Character, Queue<Integer>> map = new HashMap<>();
        //cout(s);
        int st = 0;
        int head =-1;
        int maxLen = 0;
        for(int ii = 0; ii<s.length(); ii++){
            char c = s.charAt(ii);
            //cout(">> char at ii + " + ii + " is "+ c);
            if(map.containsKey(c)){
                //keep going
                map.get(c).add(ii);
                //cout("    has c: " + c);
                //Expanding case, valid case 1/2
                maxLen = Math.max(maxLen, ii-head);
            } else {
                while(map.keySet().size()>=k){
                    char tmp = s.charAt(st++);
                    head=map.get(tmp).poll();
                    //cout(String.format("    count=%d > k=%d, removing tmp=%s, head polled=%d", map.keySet().size(), k,tmp, head));
                    if(map.get(tmp).isEmpty()){
                        map.remove(tmp);
                    }
                }
//                cout("minimize window to " + s.substring(head+1, ii)+", length is " + s.substring(head+1, ii).length());
                //actually is head+1 till ii+1, simplified as ii-head
//                cout("    add new char: " + c + ", with idx: " + ii +", expand to " + (ii-head)+"， str=" + s.substring(head+1, ii+1));
                Queue<Integer> newSet = new LinkedList<>();
                newSet.add(ii);
                map.put(c, newSet);
                //Remove previous and Expanding case, valid case 2/2
                maxLen = Math.max(maxLen, ii-head);
            }
        }
        //no tailing needed
        return maxLen;
    }

    public  static  void main(String [] args){
        LengthOfLongestSubstrWithAtMostKDistinctChars l = new LengthOfLongestSubstrWithAtMostKDistinctChars();
        l.cout(l.lengthOfLongestSubstringKDistinct("igtpevzimytyukifgezynnksysssnohespcwiqpheetgjtgmxkeqqoxldqkribsrkmooiyqkpjxaxllmizwiqzribq",17));
//
//        String s = "jjmxutystqdfhuMblWbylgjxsxgn";
//        l.cout(s.length());
//        int[] cc = new int[256];
//        for(char c : s.toCharArray()){
//            cc[c]+=1;
//        }
//        l.cout(Arrays.stream(cc).map(i->i>0?1:0).reduce(0, (a,b)->a+b));
//
//
//        l.cout(l.lengthOfLongestSubstringKDistinct("eceba",3));
    }
}
