package leetcodeii.Arrays;

import leetcodeii.Tracker;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Erebus on 2/24/18.
 */
public class WordLadderII implements Tracker {


    public List<List<String>> findLaddersTLEII(String beginWord, String endWord, List<String> wordList) {
        //BFS is probably too slow.

        HashMap<String, List<List<String>>> paths = new HashMap<>();

        HashMap<String, List<String>> graph = new HashMap<>();
        Set<String> words = wordList.stream().collect(Collectors.toSet());
        for(String s : wordList){
            graph.put(s, getNeighbor(words, s));
        }
        graph.put(beginWord, getNeighbor(words, beginWord));

        //now we have the graph ready
        Set<String> visited = new HashSet<>(); //avoid circles

        Queue<String> q = new LinkedList<>();
        q.add(beginWord);
        visited.add(beginWord);

        List<List<String>> initPath = new ArrayList<>();
        initPath.add(new ArrayList<>());
        initPath.get(0).add(beginWord);
        paths.put(beginWord, initPath);

        while(!q.isEmpty()){
            String s = q.remove();
            List<String> neighbors = graph.get(s);
            cout("getting neighbors for : " + s);
            cout(neighbors);
            //get all the paths to s
            List<List<String>> spaths = paths.get(s);
            cout("paths to s: ");
            cout(spaths);
            boolean found = false;
            for(String nb : neighbors){
                if(endWord.equals(nb)){
                    found = true;
                }
                if(found && !endWord.equals(nb)){
                    //found already, early termination. no more neighbors are needed
                    break;
                }

                //get paths to the nb
                List<List<String>> nbpaths = paths.get(nb);
                if(nbpaths==null){
                    nbpaths = new ArrayList<>();
                }
                cout("    neighbor: "+nb+", it has paths: ");
                cout(nbpaths);

                if(!visited.contains(nb)){
                    //append its unvisited neighbors to q
                    cout("    [O] unvisited " + nb +", add to queue");
                    q.add(nb);
                    visited.add(nb);
                }

                //append all paths to nb, spaths mustn't be empty
                for(List<String> sp : spaths){
                    List<String> nbp = new ArrayList<>(sp);
                    nbp.add(nb);
                    nbpaths.add(nbp);
                    cout("    +++ create new path for nb : " + nb);
                    cout(nbp);
                }
                if(!paths.containsKey(nb)){
                    paths.put(nb, nbpaths);
                }
            }
        }
        List<List<String>> pathToEnd = paths.get(endWord);
        if(pathToEnd!=null){
            int minLen = pathToEnd.stream()
                    .map(ls -> ls.size()).reduce(Integer.MAX_VALUE,(s1,s2)->Math.min(s1,s2));
            pathToEnd = pathToEnd.stream()
                    .filter(ls -> ls.size()==minLen)
                    .collect(Collectors.toList());
        }
        return pathToEnd;
    }

    private List<String> getNeighbor(Set<String> words, String s){
        List<String> neighbors = new ArrayList<>();
        char[] cc = s.toCharArray();
        for(int ii = 0 ; ii<cc.length; ii++){
            char c = cc[ii];
            for(int jj=0; jj<26; jj++){
                cc[ii]=(char) (97+jj);
                String tmp = new String(cc);
                //doesn't include s itself
                if(!s.equals(tmp) && words.contains(tmp)){
                    neighbors.add(tmp);
                }
            }
            //reset tmp string, flip one char at a time
            cc[ii] = c;
        }
        return neighbors;
    }

    public List<List<String>> findLaddersTLE(String beginWord, String endWord, List<String> wordList) {
        //BFS is probably too slow.
        List<List<String>> res = new ArrayList<>();
        Set<String> words = wordList.stream().collect(Collectors.toSet());
        if(!words.contains(endWord)){
            return res;
        }
        boolean isAtCurLvl = false;
        List<String> tmp = new ArrayList<>();
        tmp.add(beginWord);
        res.add(tmp);
        words.remove(beginWord);
        res = getLevel(res, words, endWord);
        return res;
        /**
         DP? DP can do one string to another and counts flips, or matching strings
         */

    }

    private List<List<String>> getLevel(List<List<String>> res, Set<String> words, String endWord){
        cout("available words: ");
        cout(words);
        cout("input res: ");
        cout(res);

        Set<String> used = new HashSet<>();
        boolean isAtCurLvl = false;

        List<List<String>> tmpRes= new ArrayList<>();
        for(List<String> tmp : res){
            String ending = tmp.get(tmp.size()-1);
            cout("--> node: " + ending);
            for(String s: words){
                if(isNeighbor(ending, s)){
                    cout("---------> can transform: " + s);
                    if(s.equals(endWord)){
                        isAtCurLvl = true;
                        cout("-----------------> !!! equals: "+ s+" = "+endWord);
                    }
                    used.add(s);
                    List<String> newTemp = new ArrayList<>();
                    newTemp.addAll(tmp);
                    newTemp.add(s);
                    tmpRes.add(newTemp);
                }
            }
        }
        if(isAtCurLvl == false && used.isEmpty()){
            //no more path is available, nothing found
            cout("no more path available");
            res.clear();
            return res;
        }
        if(isAtCurLvl){
            cout("!!!!!!!!!!!!!!!!!!!!!!!!!!    found at current level");
            //return current lvl
            res.clear();
            for(List<String> tmp : tmpRes){
                if(endWord.equals(tmp.get(tmp.size()-1))){
                    res.add(tmp);
                }
            }
            return res;
        }
        //all paths added, clean up used words
        cout("remove used words and move to next level;");
        cout(used);
        for(String s : used){
            words.remove(s);
        }
        return getLevel(tmpRes, words, endWord);
    }

    public static void main(String [] args){
        String [] input = {"hot","dot","dog","lot","log","cog"};
        List<String> words = Arrays.asList(input);
        WordLadderII w = new WordLadderII();

        String [] input2 = {"a","b","c"};
        List<String> words2 = Arrays.asList(input2);

        w.cout(w.findLadders("hit", "cog", words));
//        w.cout(w.findLadders("a", "c", words2));
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        /**
         1. to get shortest levels, use count to counts each level's neighbor, once reached 0, check if found?
         found: break queue condition
         unfound: set count = nextLevel neighbor counts
         2. build up map along with found neighbors, then use dfs to find paths from back to front;
         */
        List<List<String>> res = new ArrayList<>();

        HashMap<String, List<String>> map = new HashMap<>();

        boolean found = false;
        Set<String> words = wordList.stream().collect(Collectors.toSet());
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(beginWord);
        visited.add(beginWord);
        int curLvlNodes= 1;
        int nextLvlNodes = 0;

        while(!queue.isEmpty()){

            String s = queue.remove();
            cout("current node: " + s);
            char[] cc =s.toCharArray();
            curLvlNodes --;
            for(int i=0; i<s.length(); i++){
                char orig = cc[i];

                for(char c='a'; c<='z'; c++){
                    cc[i] = c;
                    String tmp = new String(cc);
                    if(words.contains(tmp)){
                        cout("  avaiable neighbor: " + tmp);
                        if(tmp.equals(endWord)){
                            found = true;
                        }
                        //fall into available words scope
                        if(!visited.contains(tmp)){
                            cout("  new neighbor add to queue");
                            //new unique word
                            //unvisited,  append the word to queue
                            nextLvlNodes ++;
                            queue.add(tmp);
                            visited.add(tmp);
                        }
                        //add map for back trace, stores tmp<-s, neighbors to tmp, and those neighbors are unique due to queue unvisited check
                        //e.g. hot->dot, while another path dit->dot
                        List<String> neighbors = map.get(tmp);
                        if(neighbors==null){
                            neighbors = new ArrayList<>();
                            neighbors.add(s);
                            map.put(tmp, neighbors);
                        } else {
                            neighbors.add(s);
                        }
                        cout("  add path to neighbor "+ tmp+" <- "+s);
                    }
                    cc[i] = orig;
                }
            }
            //current lvl searched through
            if(curLvlNodes ==0){
                if(found){
                    //early termination
                    break;
                }
                curLvlNodes = nextLvlNodes;
                nextLvlNodes = 0;
                words.removeAll(visited);
//                visited.clear();
            }
        }
        if(map.containsKey(endWord)){
            List<String> initLs= new ArrayList<>();
//            initLs.add(endWord);
            dfs(endWord, beginWord, map, initLs, res);
        }
        return res;
    }

    private void dfs(String endWord, String beginWord, Map<String, List<String>> map, List<String> cur, List<List<String>> res){
        cout("search " +endWord + " <- " + beginWord);
        cout(cur);
        if(endWord.equals(beginWord)){
            cur.add(0, endWord);
            res.add(new ArrayList<>(cur));
            cur.remove(0);
            return;
        }
        cur.add(0, endWord);
        List<String> neighbors = map.get(endWord);
        cout("DFS: available path to neighbor: "+endWord+" <- " + stringfy(neighbors));
        //could neighbors be null? when building map, we haven't create any map for the very beginning beginWord.
        for(String s : neighbors){
            dfs(s, beginWord, map, cur, res);
        }
        cur.remove(0);
    }




    /**
     1. create the graph:
     a. Set<> s = wordList.toSet
     b. Set<> visited = avoid circle back
     */

    //string lengths are same
    public boolean isNeighbor(String a, String b){
        int diff = 0;
        for(int i = 0; i<a.length(); i++){
            if(a.charAt(i)!=b.charAt(i)){
                diff++;
            }
        }
        return diff==1;
    }


}
