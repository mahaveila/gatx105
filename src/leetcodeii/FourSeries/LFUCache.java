package leetcodeii.FourSeries;

import leetcodeii.Tracker;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * Created by Youming on 8/10/2017.
 */
public class LFUCache implements Tracker{

    int recent = 0;
    int capacity = 0;
    //stores the values
    Map<Integer, Integer> keyValues;
    //store the recentness
    TreeSet<Cache> tree;
    //store the cache itself, freq
    Map<Integer, Cache> caches;

    class Cache implements Comparable<Cache>{
        int freq;
        int rect;
        int key;
        public Cache(int key, int recent, int freq){
            this.key = key;
            this.rect = recent;
            this.freq = freq;
        }

        @Override
        public int compareTo(Cache c){
            //compare frequency first and then compare the recentness
            return c.freq == this.freq? this.rect - c.rect : this.freq - c.freq;
        }

        //overriding the default for use key for hash value in Sets
        public boolean equals(Object object) {return key==((Cache) object).key;}
        public int hashCode() {return key;}

        @Override
        public String toString(){
            return String.format("%d => [f:%d, r:%d]",key, freq, rect);
        }
    }

    public LFUCache(int capacity) {
        keyValues = new HashMap<>();
        tree = new TreeSet<>();
        caches = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        if(keyValues.containsKey(key)){
            update(key);
            return keyValues.get(key);
        }
        return -1;
    }

    public void put(int key, int value) {
        if(caches.containsKey(key) && keyValues.containsKey(key)){
            update(key);
            keyValues.put(key, value);
        } else {
            //need to create a new one
            if(caches.size()==capacity){
                //need to remove one least used
                Cache lu = tree.first();
                keyValues.remove(lu.key);
                caches.remove(lu.key);
                tree.remove(lu);
            }
            Cache c = new Cache(key, ++recent,1);
            caches.put(key, c);
            tree.add(c);
            keyValues.put(key, value);
        }
    }

    private void update(int key){
        //used one more time
        Cache c = caches.get(key);
        tree.remove(c);
        Cache c2 = new Cache(key, ++recent, c.freq+1);
        tree.add(c2);
        caches.put(key, c2);
    }

    public void status(){
//        cout(tree);
        cout(keyValues);
        cout(caches);
    }

    public static void main(String [] args){
        LFUCache l = new LFUCache(3);
        l.put(2,2);
        l.put(1,1);
        l.cout("after put 1 and 2");
        l.status();

        l.get(2);
        l.get(1);
        l.get(2);
        l.cout("after get 1 and 2");
        l.status();

        l.put(3,3);
        l.put(4,4);
        l.cout("after put 3 and 4");
        l.status();
    }
}
