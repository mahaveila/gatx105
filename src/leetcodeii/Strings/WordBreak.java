package leetcodeii.Strings;

import leetcodeii.Tracker;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Erebus on 4/4/18.
 */
public class WordBreak implements Tracker{

    public static void main(String [] args){
        WordBreak wb = new WordBreak();
        List<String> ls = Arrays.asList(new String[]{"leet","code"});
        wb.wordBreakTLE("leetcode", ls);
    }




    /**
     *
     * FIXME: TLE
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreakTLE(String s, List<String> wordDict) {
        Set<String> set = wordDict.stream().collect(Collectors.toSet());
        return brk(s, 1, set);
    }

    private boolean brk(String s, int cut, Set<String> set){
        cout("try to break:" + s);
        if(s.length()==0){
            cout("s is empty, break ends.");
            return true;
        }
        boolean can = false;
        for(int ii = cut; ii <= s.length(); ii++){
            String tmp = s.substring(0, ii);
            cout(s+"         " + tmp);
            if(set.contains(tmp)){
                cout(s+" - ii@"+ii+" can break :" + tmp);
                can |= brk(s.substring(ii), 1, set);
            }
            if(can){
                break;
            }
        }
        return can;
    }
}
