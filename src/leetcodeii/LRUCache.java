package leetcodeii;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by Erebus on 3/17/18.
 * //FIXME: use double linkedlist + hashmap
 */
class LRUCache implements Tracker{

    int capacity;
    Map<Integer, Node> map;
    Node prehead;
    Node tail;

    public static class Node{
        Node pre;
        Node next;
        int val;
        int key;
        public Node(int key, int val){
            this.val = val;
            this.key = key;
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        prehead = new Node(-1, -1); // the previous node of head
        map = new HashMap<>();
    }

    public int get(int key) {
        if(map.containsKey(key)){
            int res = map.get(key).val;
            moveToFront(key);
            return res;
        }
        return -1;
    }

    public void moveToFront(int key){
        Node n = map.get(key);

        //cut it off
        Node pre = n.pre;
        Node next = n.next;
        pre.next = next;
        if(next!=null){
            next.pre = pre;
        }
        if(tail==n && pre!=prehead){
            //cutted the last node off, need to update tail
            //unless tail is the only node.
            tail = n.pre;
        }

        //insert into front; recent used
        if(prehead.next!=null){
            prehead.next.pre = n;
        }
        n.next = prehead.next;
        prehead.next = n;
        n.pre = prehead;
    }

    public void put(int key, int value) {
        cout("add " + key + " -> " + value + ", to capacity: " + capacity);
        if(map.containsKey(key)){
            //need to update the node's value
            map.get(key).val = value;
            moveToFront(key);

        } else {
            if(capacity==0){
                cout("not enough memory");
                int lastKey = tail.key;
                cout("removing " + lastKey);
                map.remove(lastKey);
                if(tail.pre == prehead){
                    //capacity = 1;
                    tail = null;
                    prehead.next = null;
                } else {
                    tail.pre.next = null;
                    tail = tail.pre;
                }
                capacity ++;
            }

            //inset a new node
            Node n = new Node(key, value);
            map.put(key, n);
            if(tail==null){
                tail = n;
            }
            n.next = prehead.next;
            if(prehead.next!=null){
                prehead.next.pre = n;
            }
            prehead.next = n;
            n.pre = prehead;
            capacity --;
        }
    }

    public static void main(String [] args){
        LRUCache cache = new LRUCache(1);

        cache.put(2,1);
        cache.cout(cache.get(2));
//        cache.cout("current cache size: " + cache.map.keySet().size());

        cache.put(3,1);
//        cache.cout("current cache size: " + cache.map.keySet().size());
//        cache.cout(cache.map.keySet());
        cache.cout(cache.get(2));
        cache.cout(cache.get(3));
    }
}
