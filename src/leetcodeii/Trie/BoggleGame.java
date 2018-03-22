package leetcodeii.Trie;

import leetcodeii.Tracker;

import java.util.*;

/**
 * FIXME, What is that "start" parameter doing?
 * Created by Youming on 3/22/2018.
 */
public class BoggleGame implements Tracker{

    public static void main(String [] args){
        BoggleGame b = new BoggleGame();
        char[][] input = {{'a','b','c'},{'d','e','f'},{'g','h','i'}};
//        char[][] input2 = {{'a','a','a','a'},{'a','a','a','a'},{'a','a','a','a'}};
//        String [] word = {"aa","aaaaa","a","aaa","aaaaaa","aaaa"};
        String [] word = {"abc","defi","gh"};
        b.cout(b.boggleGame(input,word));
    }

    /*
         * @param board: a list of lists of character
         * @param words: a list of string
         * @return: an integer
         */
    public int boggleGame(char[][] board, String[] words) {
        // Write your code here
        TrieNode root = new TrieNode();
        for(String s : words)
            TrieNode.insert(root, s);

        int[] max = {0};
        int row = board.length;
        int col = board[0].length;
        boolean[][] visited = new boolean[row][col];
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < row * col; i++)
            searchBoard(board, i, i, row, col, visited, root, root, sb, 0, max);

        return max[0];
    }

    int[] dx = {0,0,1,-1};
    int[] dy = {1,-1,0,0};
    int max = 0;

    public void searchBoard(char[][] board, int curPos, int start, int row, int col, boolean[][] visited, TrieNode root, TrieNode node, StringBuilder sb, int curMax, int[] max){
        if(node == null)
            return;
        int x = curPos / col;
        int y = curPos % col;
        int x1 = 0, y1 = 0;
        visited[x][y] = true;

        if(node.nodes[board[x][y] - 'a'] != null){
            sb.append(board[x][y]);
            if(node.nodes[board[x][y] - 'a'].isString){
                curMax++;
                max[0] = Math.max(max[0], curMax);
                //StringBuilder sb1 = new StringBuilder();
                for(int i = start + 1; i < row * col; i++){
                    if(!visited[i / col][i % col])
                        searchBoard(board, i, i, row, col, visited, root, root, sb, curMax, max);
                }
            }
            else{
                for(int i = 0; i < dx.length; i++){
                    x1 = x + dx[i];
                    y1 = y + dy[i];

                    if((x1 >= 0) && (x1 < row) && (y1 >= 0) && (y1 < col) && !visited[x1][y1]){
                        searchBoard(board, x1 * col + y1, start, row, col, visited, root, node.nodes[board[x][y] - 'a'], sb, curMax, max);
                    }
                }
            }
        }
        visited[x][y] = false;
    }

    public static class TrieNode{
        boolean isString = false;
        TrieNode[] nodes = new TrieNode[26];
        int count = 0;

        public TrieNode(){

        }

        public static TrieNode search(TrieNode root, String s){
            TrieNode node = root;
            for(int i = 0; i < s.length(); i++){
                if(node.nodes[s.charAt(i) - 'a'] == null){
                    return null;
                }
                node = node.nodes[s.charAt(i) - 'a'];
            }
            return node;
        }

        public static void insert(TrieNode root, String s){
            TrieNode node = root;
            for(int i = 0; i < s.length(); i++){
                if(node.nodes[s.charAt(i) - 'a'] == null){
                    node.nodes[s.charAt(i) - 'a'] = new TrieNode();
                }
                node.count++;
                node = node.nodes[s.charAt(i) - 'a'];
            }
            node.isString = true;
        }
    }

//    public int boggleGame(char[][] board, String[] words) {
//        // write your code here
//        Trie root = new Trie('0');
//        for(String s : words){
//            char [] cs = s.toCharArray();
//            createTrie(cs, 0, root);
//        }
//
//        int m = board.length;
//        int n = board[0].length;
//        boolean[][] visited = new boolean[m][n];
//        int res = 0;
//        int count = 0;
//        for(int ii=0; ii<m; ii++){
//            for(int jj=0; jj<n; jj++){
//                cout("Main search count = "+count++);
//                int tmp = searchBoard(new ArrayList<>(), ii, jj, root, visited, board, "", root);
//                res = Math.max(res, tmp);
//            }
//        }
//
//        return res;
//    }
//
//    private int searchBoard(List<String> cur, int ii, int jj, Trie root, boolean[][] visited, char[][] board, String s, Trie origin){
//        if(ii<0 || jj<0 || ii>=board.length || jj>=board[0].length || visited[ii][jj]){
////            cout("invalid at , i= "+ii+", j="+jj);
//            return cur.size();
//        }
//
//        visited[ii][jj]=true;
//        char c = board[ii][jj];
//        int tmp = cur.size();
//        if(root.children.containsKey(c)){
//            Trie child = root.children.get(c);
//            s+=c;
//            if(child.isLeaf){
//                cur.add(s);
////                cout(String.format("find string: %s at [%d][%d], cur size = %d, cur char is: %s ",s, ii, jj, cur.size(), board[ii][jj]));
//                //not only needs spreading out, but also needs to find from all possible startings
//                int randomId = new Random().nextInt(100);
//                int count=0;
//                for(int m=0; m<board.length; m++){
//                    for(int n=0; n<board[0].length; n++){
//                        cout(randomId+"-sub-"+count++);
//                        tmp=Math.max(searchBoard(cur, m, n, origin, visited, board, "", origin), tmp);
//                    }
//                }
//                cur.remove(cur.size()-1);
//            } else {
//                //spread out for continuous word
//                tmp=Math.max(searchBoard( cur, ii, jj+1, child, visited, board, s, origin), tmp);
//                tmp=Math.max(searchBoard( cur, ii, jj-1, child, visited, board, s, origin), tmp);
//                tmp=Math.max(searchBoard( cur, ii+1, jj, child, visited, board, s, origin), tmp);
//                tmp=Math.max(searchBoard( cur, ii-1, jj, child, visited, board, s, origin), tmp);
//            }
//        }
//        //else not a valid char, stop search on it.
//        //reset visited
//        visited[ii][jj]=false;
//        return tmp;
//    }
//
//    public class Trie{
//        Character c;
//        Map<Character, Trie> children;
//        boolean isLeaf;
//        public Trie(char c){
//            this.c = c;
//            isLeaf=false;
//            children = new HashMap<>();
//        }
//    }
//
//    private void createTrie(char[] cs, int i, Trie root){
//        if(i==cs.length){
//            root.isLeaf=true;
//            return;
//        }
//        char c= cs[i];
//        if(root.children.containsKey(c)){
//            Trie child = root.children.get(c);
//            createTrie(cs, i+1, child);
//        } else {
//            Trie child = new Trie(c);
//            root.children.put(c, child);
//            createTrie(cs, i+1, child);
//        }
//    }
}
