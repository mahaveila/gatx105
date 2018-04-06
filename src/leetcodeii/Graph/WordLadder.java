package leetcodeii.Graph;

import leetcodeii.Tracker;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Erebus on 4/2/18.
 */
public class WordLadder implements Tracker{

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //basic idea is to use BFS
        //and it cannot be formed into a matrix, so we use the neighbor list to represents the map
        Set<String> unvisited = wordList.stream().collect(Collectors.toSet());
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        unvisited.add(endWord);
        int lvl = 0;
        String last = beginWord;
        while(!q.isEmpty()){

            int size = q.size();
            for(int k = 0; k < size; k++){

                String s = q.poll();
                cout("poll: " + s);
                if(endWord.equals(s)){
                    return lvl;
                }
                char [] cs = s.toCharArray();

                for(int ii = 0; ii < cs.length; ii ++){
                    char orig = cs[ii];
                    for(int jj = 0; jj < 26; jj++){
                        char tmp = (char)('a'+jj);
                        cs[ii] = tmp;
                        String next = new String(cs);
                        if(cs[ii]!=orig && unvisited.contains(next)){
                            unvisited.remove(next);
                            cout("offer: " + next);
                            q.offer(next);
                        }
                    }
                    cs[ii] = orig;
                }

            }
            lvl++;
        }

        return -1;
    }

    public static void main(String [] args){
        WordLadder w = new WordLadder();
        String [] in = {"hot","dot","dog","lot","log","cog"};
        List<String> words = Arrays.asList(in);
        w.cout(w.ladderLength("hit", "cog", words));
    }
}
