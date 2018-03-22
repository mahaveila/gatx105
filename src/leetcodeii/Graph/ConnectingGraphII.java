package leetcodeii.Graph;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Erebus on 3/11/18.
 */
public class ConnectingGraphII {

    /**
     * Previous problem simply ask void connect and boolean query
     *
     * Now impl:
     * query(a), Returns the number of connected component nodes which include node a.
     *
     * basic idea is to store the counts, and use root.count = root1.count + root2.count when union
     */

    int[] set;
    Map<Integer, Integer> counts;

    /*
    * @param n: An integer
    */public ConnectingGraphII(int n) {
        // do intialization if necessary
        set = new int[n+1];
        counts = new HashMap<>();
        for(int i=1; i<=n; i++){
            set[i]=i;
            counts.put(i, 1);
        }
        //initialized a group of unique solo set
    }

    private int root(int a){
        return set[a]==a?a:root(set[a]);
    }

    /*
     * @param a: An integer
     * @param b: An integer
     * @return: nothing
     */
    public void connect(int a, int b) {
        // write your code here
        int root_a = root(a);
        int root_b = root(b);
        if(root_a!=root_b){
            int size_a = counts.get(root_a);
            int size_b = counts.get(root_b);
            counts.put(root_b, size_a + size_b);
            set[root_a] = root_b;
        }
    }

    /*
     * @param a: An integer
     * @return: An integer
     */
    public int query(int a) {
        // write your code here
        int root = root(a);
        return counts.get(root);
    }



}
