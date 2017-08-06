package leetcodeii.FourSeries;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Erebus on 6/3/17.
 */
public class ConcatenatedWords472 {

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Set<String> dict = Arrays.stream(words).collect(Collectors.toSet());
        List<String> res = new ArrayList<>();
        for(String s : words){
            if(isSub(dict, s)){
                res.add(s);
            }
        }
        return res;
    }

    //TODO: too complex, need to use Trie, instead of Set<String>
    //FIXME: use Word-Break DP, Trie is too complex
    private boolean isSub(Set<String> dict, String s){
        if(dict==null || dict.isEmpty()){
            return false;
        }
        boolean [] dp = new boolean[s.length()+1];
        dp[0] = true; //setting beginning bound
        for(int bound = 1; bound<dp.length;bound++){
            for(int start = 0; start<bound; start++){
                //skip cur bound if not continuous
                if(!dp[start]){
                    continue;
                }
                if(dict.contains(s.substring(start, bound))){
                    dp[bound] = true;
                    break;
                }
            }
        }
        //check the last bound
        return dp[s.length()];
    }

    public List<String> findAllConcatenatedWordsInADictDP(String[] words) {
        Set<String> dict = new HashSet<>();
        List<String> res = new ArrayList<>();
        //sorting by length ASC
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        for(String s: words){
            if(isSub(dict, s)){
                res.add(s);
            }
            dict.add(s);
        }
        return res;
    }

}
