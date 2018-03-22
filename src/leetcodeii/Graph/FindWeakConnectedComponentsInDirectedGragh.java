package leetcodeii.Graph;

import java.util.*;

/**
 * Created by Erebus on 3/12/18.
 */
public class FindWeakConnectedComponentsInDirectedGragh {

    /**
     * TODO: why [[1,2,4],[6,3,5]] is wrong but [[1,2,4],[3,5,6]] is right?
     * {1,2,4#2,4#3,5#4#5#6,5}
     *
     A----->B  C
     \     |  |
     \    |  |
     \   |  |
     \  v  v
     ->D  E <- F

     output {A,B,D} and {C,E,F}
     */
    class DirectedGraphNode {
      int label;
      ArrayList<DirectedGraphNode> neighbors;
      DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
 };


    //not a integer or size unbound, then use map for union find
    Map<Integer, Integer> union;
    Map<Integer, List<Integer>> res;

    /*
     * @param nodes: a array of Directed graph node
     * @return: a connected set of a directed graph
     */
    public List<List<Integer>> connectedSet2(ArrayList<DirectedGraphNode> nodes) {
        // write your code here
        union = new HashMap<>();
        res = new HashMap<>();

        for(DirectedGraphNode node : nodes){
            union.put(node.label, node.label);
            for(DirectedGraphNode nb : node.neighbors){
                union.put(nb.label, nb.label);
            }
        }
        //union init-ed

        //now it's time to connect
        Set<Integer> visited = new HashSet<>();


        for(DirectedGraphNode node : nodes){
            for(DirectedGraphNode nb : node.neighbors){
                connect(node, nb);
            }
        }
        List<List<Integer>> lsres = new ArrayList<>();
        for(List<Integer> value: res.values()){
            lsres.add(value);
        }
        return lsres;
    }

    //could be lots of node point to one node
    public void connect(DirectedGraphNode node, DirectedGraphNode nb){
        int root_a = root(node.label);
        int root_b = root(nb.label);
        if(root_b!=root_a){
            union.put(root_b, root_a);
            List<Integer> tobe = res.get(root_a);
            if(tobe==null){
                tobe = new ArrayList<>();
                tobe.add(root_a);
                res.put(root_a, tobe);
            }
            List<Integer> nottobe = res.get(root_b);
            if(nottobe!=null){
                //transfer b's connection to a
                tobe.addAll(nottobe);
                res.remove(root_b);
            } else {
                //not value for root_b as well
                //means nothing else connect to be yet
                //just add b's value
                tobe.add(root_b);
            }
        }
    }

    //need to assume all the possible values are in the union
    public int root(int node){
        if(node==union.get(node)){
            return node;
        } else {
            return root(union.get(node));
        }
    }


    /**
     * Another solution
     */
    class UnionFind {
        HashMap<Integer, Integer> map;
        //Constructor: makes sure all the key has a parent initially
        UnionFind(HashSet<Integer> set) {
            map = new HashMap<Integer, Integer>();
            for (int num : set) {
                map.put(num, num);
            }
        }
        //Find:
        //Root parent should have itself as child in map<child,parent>
        int find(int x) {
            int parent = map.get(x);
            while (parent != map.get(parent)) {
                parent = map.get(parent);
            }
            return parent;
        }
        void union(int x, int y) {
            int findX = find(x);
            int findY = find(y);
            if (findX != findY) {
                //put findX's parent = findY
                map.put(findX, findY);
            }
        }
    }

    /**
     * //FIXME: the idea is, each set of elements, all the elements share the same parent
     * use that parent to create map list for the result
     *
     * //TODO: this is much easier way than moving list from parent's to parent's during union,
     * //FIXME: separate into 2 steps: 1) unions all possibilities, then 2) gather results
     * @param rst
     * @param uf
     * @param set
     * @return
     */
    public List<List<Integer>> generateRst (List<List<Integer>> rst, UnionFind uf, HashSet<Integer> set) {

        HashMap<Integer, List<Integer>> listMap = new HashMap<Integer, List<Integer>>();
        for (int num : set) {
            int rootParent = uf.find(num);//uf.map.get(num);
            if (!listMap.containsKey(rootParent)) {
                listMap.put(rootParent, new ArrayList<Integer>());
            }
            //Add num into its correct set (meaning its root ancestor)
            listMap.get(rootParent).add(num);
        }

        for (List<Integer> list: listMap.values()) {
            Collections.sort(list);
            rst.add(list);
        }
        return rst;
    }

    public List<List<Integer>> connectedSet22(ArrayList<DirectedGraphNode> nodes) {
        List<List<Integer>> rst = new ArrayList<List<Integer>>();
        if (nodes == null || nodes.size() == 0) {
            return rst;
        }
        HashSet<Integer> set = new HashSet<Integer>();
        for (DirectedGraphNode node : nodes) {
            set.add(node.label);
            for (DirectedGraphNode neighbor : node.neighbors) {
                //FIXME: used a set to reduce input size, no redundant
                set.add(neighbor.label);
            }
        }
        UnionFind uf = new UnionFind(set);

        //find and union: construct the map<child,parent> structure
        for (DirectedGraphNode node : nodes) {
            for (DirectedGraphNode neighbor : node.neighbors) {
                uf.union(node.label, neighbor.label);
            }
        }
        return generateRst(rst, uf, set);
    }

}
