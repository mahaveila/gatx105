package leetcodeii.Graph;

import leetcodeii.Tracker;

import java.util.*;

/**
 * Created by Erebus on 4/16/18.
 */
public class CopyGraph implements Tracker {

    static class UndirectedGraphNode {
      int label;
      ArrayList<UndirectedGraphNode> neighbors;
      UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
  };

    public static void main(String [] args){
        CopyGraph copyGraph = new CopyGraph();
        UndirectedGraphNode node = new UndirectedGraphNode(-1);
        UndirectedGraphNode copy = copyGraph.cloneGraph(node);
        System.out.println(copy.label);
    }

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        // write your code here
        if(node == null){
            return node;
        }
        Map<UndirectedGraphNode, UndirectedGraphNode> map = copyToMap(node);
        cout(map.keySet().size());
        assignNeighbors(map, node);
        return map.get(node);
    }

    public void assignNeighbors(Map<UndirectedGraphNode, UndirectedGraphNode> map, UndirectedGraphNode node){
        Queue<UndirectedGraphNode> q = new LinkedList<>();
        q.offer(node);
        Set<UndirectedGraphNode> visited = new HashSet<>();
        while(!q.isEmpty()){
            UndirectedGraphNode cur = q.poll();
            if(!visited.contains(cur)){
                for(UndirectedGraphNode neighbor : cur.neighbors){
                    map.get(cur).neighbors.add(map.get(neighbor));
                    q.offer(neighbor);
                }
                visited.add(cur);
            }
        }
    }

    public Map<UndirectedGraphNode, UndirectedGraphNode> copyToMap(UndirectedGraphNode node){

        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        Queue<UndirectedGraphNode> q = new LinkedList<>();

        q.offer(node);
        while(!q.isEmpty()){
            UndirectedGraphNode cur = q.poll();
            cout("copy to map: " + cur.label);
            if(!map.containsKey(cur)){
                //haven't seen this before
                map.put(cur, new UndirectedGraphNode(cur.label));
                cout("map now has " + map.keySet()+" elements");
                for(UndirectedGraphNode neighbor : cur.neighbors){
                    q.offer(neighbor);
                }
            }
        }
        return map;
    }

}
