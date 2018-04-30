package leetcodeii;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Erebus on 4/18/18.
 */
public class MiniMemcache implements Tracker {

    public static void main (String [] args){
        MiniMemcache m = new MiniMemcache();
//        m.cout(m.get(1, 0));
//        m.set(2, 1, 1, 2);
//        m.cout(m.get(3, 1));
//        m.cout(m.get(4, 1));
//        m.cout(m.incr(5, 1, 1));
//        m.set(6, 1, 3, 0);
//        m.cout(m.incr(7, 1, 1));
//        m.cout(m.decr(8, 1, 1));
//        m.cout(m.get(9, 1));
//        m.delete(10, 1);
//        m.cout(m.get(11, 1));
//        m.cout(m.incr(12, 1, 1));


        m.set(3, 3, 5, 120);
        m.cout(m.get(9, 3));
        m.cout(m.get(19, 3));
        m.cout(m.get(121, 3));
        m.cout(m.get(122, 3));
        m.cout(m.get(123, 3));
        m.cout(m.get(124, 3));
    }

    int no_value = Integer.MAX_VALUE;
    Map<Integer, Data> cache;

    public static class Data{
        int value;
        int upsertTime;
        int ttl;

        public Data(int value, int upsertTime, int ttl){
            this.value = value;
            this.upsertTime = upsertTime;
            this.ttl = ttl;
        }
    }

    public MiniMemcache() {
        // do intialization if necessary
        cache = new HashMap<>();
    }

    /*
     * @param curtTime: An integer
     * @param key: An integer
     * @return: An integer
     */
    public int get(int curtTime, int key) {
        // write your code here
        if(cache.containsKey(key)){
            Data d = cache.get(key);
            cout("      key=" + key+", has a time out limit at " + (d.upsertTime + d.ttl));
            if(d.ttl == 0 || (d.upsertTime + d.ttl > curtTime)){
                //still alive
                return cache.get(key).value;
            }
            //timed out
            cache.remove(key);
        }
        return no_value;
    }

    /*
     * @param curtTime: An integer
     * @param key: An integer
     * @param value: An integer
     * @param ttl: An integer
     * @return: nothing
     */
    public void set(int curtTime, int key, int value, int ttl) {
        // write your code here
        //case value = no_value
        if(value == no_value){
            //invalid input
            return;
        }
        Data d = new Data(value, curtTime, ttl);
        cache.put(key, d);
        cout("    putting  " + key + " = " + value + " until " + (curtTime + ttl));
    }

    /*
     * @param curtTime: An integer
     * @param key: An integer
     * @return: nothing
     */
    public void delete(int curtTime, int key) {
        // write your code here
        if(no_value != get(curtTime, key)){
            cache.remove(key);
        }
    }

    /*
     * @param curtTime: An integer
     * @param key: An integer
     * @param delta: An integer
     * @return: An integer
     */
    public int incr(int curtTime, int key, int delta) {
        // write your code here
        cout("    increase value at " + curtTime+ ", key = " + key);
        int val = get(curtTime, key);
        if(val==no_value){
            cout("    No value found ");
            return no_value;
        }
        Data d = cache.get(key);
        //guess in memcache, updating value doesn't refresh living time in cache
//        d.upsertTime = curtTime;
        d.value += delta;
        cache.put(key, d);
        return d.value;
    }

    /*
     * @param curtTime: An integer
     * @param key: An integer
     * @param delta: An integer
     * @return: An integer
     */
    public int decr(int curtTime, int key, int delta) {
        // write your code here
        //need to check the overflow for value + delta, same for incr as well
        return incr(curtTime, key, delta * -1);
    }
}
