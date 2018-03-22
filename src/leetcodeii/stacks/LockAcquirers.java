package leetcodeii.stacks;

import leetcodeii.Tracker;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.function.Function;

/**
 * Created by Erebus on 3/15/18.
 */
public class LockAcquirers implements Tracker{

    //acquire_
    /**
     * release_
     * acquire_
     * 012345678
     */
    private int act_st = 0;
    private int act_ed = 7;
    private int num_st = 8;




    private String getLockNumber(String s){
        return s.substring(8);
    }

    private boolean lock(String s){
        if("ACQUIRE".equalsIgnoreCase(s.substring(act_st, act_ed))){
            return true;
        } else {
            return false;
        }
    }


    public int valid(String [] actions){
        Stack<String> locks = new Stack<>();
        Set<String> register = new HashSet<>();

        for(int ii=0; ii<actions.length; ii++){
            String s = actions[ii];
            String n = getLockNumber(s);
            if(lock(s)){
                /**
                 * Prevent duplicate lock, deadlock
                 */
                if(register.contains(n)){
                    cout(s + " has been locked");
                    return ii+1;
                }
                locks.push(n);
                register.add(n);
            } else {
                /**
                 * prevent releasing non-existing locks
                 */
                //releasing lock
                if(locks.isEmpty() || !locks.peek().equals(n)){
                    //unable to release lock
                    cout(locks.isEmpty()?("no locks to release: " + n):("lock doesn't match st=" + locks.peek()+" v.s. "+n));
                    return ii+1;
                }
                locks.pop();
                register.remove(n);
            }
        }
        return locks.isEmpty()?0: (actions.length+1);
    }

    private static Function<Integer, String> lock = i->"ACQUIRE "+i;
    private static Function<Integer, String> unlock = i->"RELEASE "+i;

    public static  void main(String [] args){
        LockAcquirers l = new LockAcquirers();

        String [] test1 = {lock.apply(364), lock.apply(84), unlock.apply(84), lock.apply(1337),unlock.apply(1337),unlock.apply(364)};
        String [] test2 = {lock.apply(364),lock.apply(84),unlock.apply(364),unlock.apply(84)};
        String [] test3 = {lock.apply(123),lock.apply(364), lock.apply(84), unlock.apply(84),unlock.apply(364),lock.apply(456)};
        String [] test4 = {lock.apply(364), lock.apply(84), lock.apply(364),unlock.apply(364)};

        String [] test5 = {lock.apply(364), lock.apply(84), unlock.apply(84), unlock.apply(364)};
        String [] test6 = {lock.apply(123), lock.apply(364), lock.apply(84),unlock.apply(84),unlock.apply(364),lock.apply(789),unlock.apply(456),unlock.apply(123)};

        l.cout(l.valid(test5));
        l.cout(l.valid(test2));
        l.cout(l.valid(test3));
        l.cout(l.valid(test6));
        l.cout(l.valid(test4));

    }
}
