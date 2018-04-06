package leetcodeii.oa;

import leetcodeii.Tracker;

import java.util.*;

/**
 * Created by Erebus on 3/24/18.
 */
public class Amazon implements Tracker {

    public static void main (String [] args){

        String input = "Baby is ealelfk o ea jeo122 o12 j a dev";
        String input2 = "today we is go a to good party day we go out";
        String input3 = "All appear once";
        String input4 = "oneword";
        String input5 = "party2out";
        List<String> wordsToExclude = new ArrayList<>();
        wordsToExclude.add("o");
        wordsToExclude.add("party");
        wordsToExclude.add("out");
        Amazon a = new Amazon();
        a.cout(a.retrieveMostFrequentlyUsedWords(input, wordsToExclude));
        a.cout(a.retrieveMostFrequentlyUsedWords(input2, wordsToExclude));
        a.cout(a.retrieveMostFrequentlyUsedWords(input3, wordsToExclude));
        a.cout(a.retrieveMostFrequentlyUsedWords(input4, wordsToExclude));
        a.cout(a.retrieveMostFrequentlyUsedWords(input5, wordsToExclude));
    }


    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    List<String> retrieveMostFrequentlyUsedWords(String input,
                                                 List<String> wordsToExclude)
    {
        List<String> maxList = new ArrayList<String>();
        if(input == null || input.length()==0){
            return maxList;
        }
        int max = 0;
        HashMap<String, Integer> map = new HashMap<>();
        HashSet<String> exc = new HashSet<String>();
        for(int a = 0; a<wordsToExclude.size(); a++){
            if(wordsToExclude.get(a)!=null){
                exc.add(wordsToExclude.get(a).toLowerCase());
            }
        }
        for(int i=0; i<input.length(); i++){
            int j=i;

            while(j<input.length()&&Character.isLetter(input.charAt(j))){
                j++;
            }
            if(j>i){
                String tmp = input.substring(i, j);
                tmp = tmp.toLowerCase();

                if(!exc.contains(tmp)){
                    int count = map.getOrDefault(tmp, 0) + 1;
                    map.put(tmp, count);
                    if(count>max){
                        max = count;
                    }
                }
            }
            i = j;
        }
        for(String s: map.keySet()){
            int len = map.get(s);
            if(len==max){
                maxList.add(s);
            }
        }
        return maxList;

    }

    // METHOD SIGNATURE ENDS



    public static class Point{
        char c;
        boolean start;
        int index;
        public Point(char c, boolean start, int index){
            this.c = c;
            this.start = start;
            this.index = index;
        }
    }

    /**
     * FIXME: instead of using sweep lines, we can use extending end method
     * @param S
     * @return
     */
    public List<Integer> partitionLabelsSweepLineApproach(String S) {
        Map<Character, Integer> start = new HashMap<>();
        Map<Character, Integer> end = new HashMap<>();
        for(int ii = 0; ii < S.length(); ii++){
            char c = S.charAt(ii);
            if(end.put(c, ii)==null){
                //null means c doesn't exist in end, put in start as well to mark its first appearance
                start.put(c, ii);
            }
        }
        PriorityQueue<Point> q = new PriorityQueue<Point>(1, (p1, p2)->p1.index==p2.index?(p1.start?-1:1):(p1.index-p2.index));
        for(Character key : start.keySet()){
            q.offer(new Point(key, true, start.get(key)));
            q.offer(new Point(key, false, end.get(key)));
        }

        List<Integer> res = new ArrayList<>();
        Set<Character> set = new HashSet<>();
        int st = 0;
        while(!q.isEmpty()){
            Point p = q.poll();
            if(p.start==false){
                //the end
                set.remove(p.c);
                if(set.isEmpty()){
                    //e.g. 1,2 is b, 0,3 is a, now index=3
                    res.add(p.index-st+1);
                }
            } else {
                if(set.isEmpty()){
                    //e.g. put in 0,
                    st = p.index;
                }
                set.add(p.c);
            }
        }
        return res;
    }

    /**
     * FIXME: this is better than sweepline approach
     * @param S
     * @return
     */
    public List<Integer> partitionLabelsExtendingEndApproach(String S) {
        int [] map = new int[26];
        for(int ii = 0; ii < S.length(); ii++){
            map[S.charAt(ii)-'a'] = ii;
        }
        List<Integer> res = new ArrayList<>();
        int last = 0;
        int start = -1;
        for(int jj = 0; jj < S.length(); jj++){
            //last appearance of past compared chars
            last = Math.max(last, map[S.charAt(jj)-'a']);
            if(last == jj){
                //past sequence ends here
                res.add(jj-start);
                start=jj;
            }
        }
        return res;
    }

    /**
     * TODO
     * @param forest
     * @return
     */
    public int cutOffTree(List<List<Integer>> forest) {
        return -1;
    }

}



