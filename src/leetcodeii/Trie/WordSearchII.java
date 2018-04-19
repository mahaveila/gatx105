package leetcodeii.Trie;

import leetcodeii.Tracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Erebus on 4/8/18.
 */
public class WordSearchII implements Tracker {

    public static void main(String [] args){
        WordSearchII ws = new WordSearchII();
        //[["a","b"],["a","a"]]
//["aba","baa","bab","aaab","aaa","aaaa","aaba"]
        char [][] input = {{'a','b'},{'a','a'}};
        String [] words = {"aba","baa","bab","aaab","aaa","aaaa","aaba"};
        ws.cout(ws.findWords(input, words));
    }


    public static class Trie {

        Trie [] next;
        char c;
        boolean isLeaf;

        /** Initialize your data structure here. */
        public Trie() {
            next = new Trie[26];
            isLeaf = false;
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            if(word.length()<1){
                return;
            }
            char fc = word.charAt(0);
            if(next[fc-'a'] == null){
                next[fc-'a'] = new Trie();
                next[fc-'a'].c = fc;
            }

            String s = word.substring(1);
            if(s.length()>0){
                next[fc-'a'].insert(s);
            } else {
                next[fc-'a'].isLeaf = true;
            }
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            char fc = word.charAt(0);
            if(next[fc-'a']==null){
                return false;
            }
            if(word.length()==1){
                return next[fc-'a'].isLeaf;
            } else {
                return next[fc-'a'].search(word.substring(1));
            }
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String word) {
            char fc = word.charAt(0);
            if(next[fc-'a']==null){
                return false;
            }
            if(word.length()==1){
                return true;
            } else {
                return next[fc-'a'].startsWith(word.substring(1));
            }
        }
    }

    int [] dx = {0,0,1,-1};
    int [] dy = {1,-1,0,0};

    public List<String> findWords(char[][] board, String[] words) {
        //so here comes the dfs backtracking and trie problem

        //1st starting with dfs
        //2nd optimize with trie
        Trie root = new Trie();
        for(String s : words){
            root.insert(s);
        }

        Set<String> set = Arrays.stream(words).collect(Collectors.toSet());
        boolean [][] V = new boolean [board.length][board[0].length];
        List<String> res = new ArrayList<>();
        for(int ii = 0; ii < board.length; ii ++){
            for(int jj = 0; jj < board[0].length; jj ++){
                //dfs starts from each point on the board
                dfs(board, set, "", ii, jj, V, res, root);
            }
        }
        //SWEET, do not miss the return statement
        return res;
    }

    //let's assume we return as long as we've find the tmp
    private void dfs(char[][] board, Set<String> set, String tmp, int i, int j, boolean[][] V, List<String> res, Trie root){
        cout(V);
        if(valid(board, i, j, V, root)){
            V[i][j] = true; // set to visited
            //and now trie has the char node node.next[c-'a']

            char cc = board[i][j];
            tmp = tmp + cc; //make the new string.
            if(set.contains(tmp)){
                res.add(tmp);
//                V[i][j] = false;
//                return;
            }
            for(int ii = 0; ii < 4; ii ++){
                //fan out the problem -> 4^n complexity, need to pruning use Trie
                cout(String.format("%s: moving from [%d][%d] to [%d][%d]", tmp, i, j, i+dx[ii], j+dy[ii]));
                dfs(board, set, tmp, i+dx[ii], j+dy[ii], V, res, root.next[cc-'a']);
            }
            //backtracking
            V[i][j] = false;
        }
    }

    private boolean valid(char[][] board, int i, int j, boolean[][] V, Trie root){
        if(i>= board.length || j >= board[0].length || i < 0 || j < 0 || V[i][j]){
            cout("            invalid move at: " + i + ", " + j);
            return false;
        }
        char c = board[i][j];
        if(root.next[c-'a']==null){
            //trie doesn't have this node
            cout("            trie has no this char: " + board[i][j]);
            return false;
        }
        return true;
    }

}
