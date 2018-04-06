package leetcodeii.Trie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erebus on 4/1/18.
 */
public class KthEditDistance {

    /**
     * @param words: a set of stirngs
     * @param target: a target string
     * @param k: An integer
     * @return: output all the strings that meet the requirements
     */
    public List<String> kDistance(String[] words, String target, int k) {
        // write your code here

        Trie root = new Trie('$');
        for(String s: words){
            root.add(s);
        }

        //for each string, try to match target.
        //so vertical is dynamically changing using trie
        //and horizontal is still fixed.
        int [] dp = new int[target.length()+1];
        for(int ii = 0; ii < dp.length; ii++){
            dp[ii] = ii;
        }
        List<String> res = new ArrayList<>();
        match(root, target, k, dp, res);
        return res;
    }

    public void match(Trie root, String target, int k, int [] dp, List<String> res){
        //return condition
        if(root.isEnd && dp[target.length()]<=k){
            res.add(root.word.substring(1));
        }

        //dp function
        int [] tmpDP = new int[target.length()+1];
        for(int nb = 0; nb < root.next.length; nb++){
            Trie neighbor = root.next[nb];
            if(neighbor!=null){
                //try to match the target, cost of add new chars
                tmpDP[0] = dp[0] + 1;
                for(int jj = 1; jj < tmpDP.length; jj++){
                    //rolling array optimization
                    int diff = (target.charAt(jj-1) - 'a') == nb? 0 : 1;
                    int replace = dp[jj-1]+diff;
                    //tmpDP is current row, dp is previous row
                    int addOrDelete = Math.min(tmpDP[jj-1]+1, dp[jj]+1);
                    tmpDP[jj] = Math.min(replace, addOrDelete);
                }
                match(neighbor, target, k, tmpDP, res);
            }
        }
    }


    public class Trie{
        Trie[] next;
        char c;
        String word;
        boolean isEnd;
        public Trie(Character c){
            this.c = c;
            this.next = new Trie[26];
            isEnd = false;
            word = "" + c;
        }

        public void add(String s){
            if(s==null || s.length()<1){
                isEnd = true;
                return;
            }
            char c = s.charAt(0);
            if(next[c-'a']==null){
                next[c-'a'] = new Trie(c);
            }
            if(next[c-'a'].word==null){
                next[c-'a'].word=""+c;
            } else {
                next[c-'a'].word = this.word + c;
            }
            next[c-'a'].add(s.substring(1));
        }
    }

}
