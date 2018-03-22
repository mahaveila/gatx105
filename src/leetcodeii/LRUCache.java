package leetcodeii;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by Erebus on 3/17/18.
 * //FIXME: use double linkedlist + hashmap
 */
class LRUCache implements Tracker{

//    public static class Data{
//        int c;
//        int key;
//        int val;
//        public Data(int key, int val){
//            this.key = key;
//            this.val = val;
//            this.c = 0;
//        }
//    }
//
//    PriorityQueue<Data> rank;
//    Map<Integer, Data> map;
//    int size;

    public LRUCache(int capacity) {
        //using count to order
//        rank = new PriorityQueue<Data>(1, (data1,data2)->data1.c-data2.c);
//        map = new HashMap<>();
//        size = capacity;
    }

    public int get(int key) {
//        if(map.containsKey(key)){
//            Data d = map.get(key);
//            //FIXME: data polled from map won't affect the heap
//            d.c = d.c+1;
//            return d.val;
//        }
        return -1;
    }

    public void put(int key, int value) {
//        if(rank.size()>=size){
//            Data least = rank.poll();
//            map.remove(least.key);
//        }
//        Data d = new Data(key, value);
//        rank.offer(d);
//        map.put(key, d);
    }

    public static void main(String [] args){
//        LRUCache cache = new LRUCache(2);
//
//        cache.put(1,1);
//        cache.put(2,2);
//        cache.cout("current cache size: " + cache.map.keySet().size() + " | " + cache.rank.size());
//        cache.cout(cache.get(1));
//        cache.put(3,3);
//        cache.cout("current cache size: " + cache.map.keySet().size() + " | " + cache.rank.size());
//        cache.cout(cache.map.keySet());
    }
}
