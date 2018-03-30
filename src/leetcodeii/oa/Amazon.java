package leetcodeii.oa;

import leetcodeii.Tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

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
}



