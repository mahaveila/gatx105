package leetcodeii.FourSeries;

import leetcodeii.Tracker;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created by Erebus on 9/4/17.
 */
public class MinGenericMutation433 implements Tracker{

    public int minMutation(String start, String end, String[] bank) {
        Set<String> path = new HashSet<>();
        for(String s:bank) {
            path.add(s);
        }

        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        q.offer(start);

        int level = 0;

        char[] muton = {'A','C','G','T'};

        while(!q.isEmpty()){
            int lvlCount = q.size();

            while(lvlCount-- >0){
                //poll current level
                String cur = q.poll();
                cout(String.format("current level=%d: mutate String: %s", level, cur));
                //FIXME: the == doesn't work here, must be equal(). otherwise, end!=cur even they have same string value
                if(end.equals(cur)){
                    return level;
                }
                //else,means cur is not, and we need to maintain the q by exploring mutations of cur.

                char [] m = cur.toCharArray();
                for(int ii = 0 ; ii< m.length; ii++){
                    //mutate each char
                    char old = m[ii];
                    for(int jj = 0; jj< muton.length; jj++){
                        m[ii] = muton[jj];
                        String next = new String(m);
                        if(path.contains(next) && !visited.contains(next)){
                            cout(String.format("%s is mutated from %s, eligible", next, cur));
                            visited.add(next);
                            q.offer(next);
                        }
                    }
                    m[ii] = old;
                }
            }
            level++;
        }
        return -1;
    }

    public static void main(String [] args){
        String st = "AACCGGTT";
        String end ="AACCGGTA";
        String [] bk = {"AACCGGTA"};
        MinGenericMutation433 m = new MinGenericMutation433();
        m.cout(m.minMutation(st, end, bk));
    }

}
