package leetcodeii.Arrays;

import leetcodeii.Tracker;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Erebus on 2/25/18.
 */
public class LongestConsecutiveSubsequence128 implements Tracker {




    public static void main(String [] args){
        int[] in = {4,0,-4,-2,2,5,2,0,-8,-8,-8,-8,-1,7,4,5,5,-4,6,6,-3};
        LongestConsecutiveSubsequence128 ll = new LongestConsecutiveSubsequence128();
        ll.cout(ll.longestConsecutive(in));
    }

    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        /**
         use a map to store a int val, and its max left and right length;
         e.g. found 3, not 4, not 5, put 3->1, left end = 3, right end =3
         found 4, has 3->1, left end = 4-get(3) = 4-1 = 3, update 4->2, right end doesn't exist, still 4->2,
         set (leftend)=3->2, (rightend)=4->2
         found 2, has 3->2, right end = 2+(3)=2+2 =4,
         update right end +1: 4->3, left end update 2-> 3.

         we now have (2)->3, (4)->3, skipping 2,3,4
         assume we got (6)->10 (15)->10,

         next is 5, left=(4)->3, right=(6)->10, right edge = 10+3+1
         5-3=2
         5+10=15
         !! if duplicate exist in map, skip.

         and so on, along with updating global max
         */
        int max = 0;
        for (int i : nums) {
            if (map.containsKey(i)) {
                continue;
            }
            int l = 0;
            int r = 0;
            if (map.containsKey(i - 1)) {
                //has left
                l = map.get(i - 1); //
            }
            if (map.containsKey(i + 1)) {
                //has right
                r = map.get(i + 1); //2 finds 3, 3->1, 2+1 = 3, right edge
            }
            int le = i - l;//init node 3, leftEdge is self
            int re = i + r;
            int sum = l + r + 1;
            cout(String.format("value: %d, left len = %d, right len = %d, left edge = %d, right edge = %d, put total %d",
                    i, l, r, le, re, sum));
            map.put(le, sum);
            map.put(re, sum);
            map.put(i, sum);

            max = Math.max(max, sum);

        }
        return max;
    }
    }
