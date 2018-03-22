package leetcodeii.Graph;

import java.util.*;

/**
 * Created by Erebus on 3/6/18.
 */
public class ConnectingGraph {

//    BFS, O(1) connect, O(V) query, O(V + E) space
    Map<Integer, Set<Integer>> map;
    /* FIXME, use lots of time & space, not an ideal solution
    * @param n: An integer
    */
    public ConnectingGraph(int n) {
        // do intialization if necessary
        map = new HashMap<>();
        while(n>0){
            map.put(n--, new HashSet<>());
        }
    }
    public void connect(int a, int b) {
        // write your code here
        //e.g. conn 5,9, 2,3  8,1  1,2, then why the hell 9,1 is connected? the editor of jiuzhang must be wrong
        map.get(a).add(b);
        map.get(b).add(a);
    }
    public boolean query(int a, int b) {
        // write your code here
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> q = new LinkedList();
        q.add(a);
        while(!q.isEmpty()){
            int node = q.poll();
            if(node==b){
                return true;
            }
            for(int i : map.get(node)){
                //go through neighbors
                if(!visited.contains(i)){
                    q.add(i);
                    visited.add(i);
                }
            }
        }
        return false;
    }

    //Union Find, doesn't store edge information O(V) complexity
    /**
     * A disjoint-set data structure: keeps track of a set of elements partitioned into a number of disjoint (non-overlapping) subsets.
     * e.g. {123456789} -> {123}, {578}, {469}
     * Union-Find Algorithm can be used to check whether an undirected graph contains cycle or not
     */
    int[] map2;
    /*
    * @param n: An integer
    */
    public void init(int n) {
        // do intialization if necessary
        map2 = new int[n+1];//initially pointing to 0, which stands for no node
        for(int i=1; i<=n; i++){
            map2[i]=i;
        }
        //created a group of sets, each set has 1 value
    }

    private int findRoot(int a){
        if(map2[a]==a){
            return a;
        }
        return findRoot(map2[a]);
    }

    public void connect2(int a, int b) {
        // write your code here

        int rta = findRoot(a);
        int rtb = findRoot(b);

        if(a!=b){
            //connecting a to b, put a's Set's root.val as b's set's root.val
            map2[rta]=rtb; //b=map[b]
        }
    }

    public boolean query2(int a, int b) {
        // write your code here
        return findRoot(a)==findRoot(b);
    }
}
