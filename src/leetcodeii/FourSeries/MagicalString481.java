package leetcodeii.FourSeries;

import java.util.function.Function;

/**
 * Created by Youming on 4/19/2017.
 */
public class MagicalString481 {

    int oneInMagicString(int n){
        String magic = generateMgiString(n);
        int sum = 0;
        for (char c:magic.toCharArray()){
            sum += c-32; //1-2 = -1, 2-2=0;
        }
        return sum * -1;
    }

    String generateMgiString(int n){
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        sb.append(1);
        Function<Integer, Integer> next = i->i==1?2:1;
        while(sb.length()<n){
            int cur = sb.charAt(idx++);
            sb.append(computeNextSequence(cur, next.apply(cur)));
        }
        return sb.toString();
    }

    private int computeNextSequence(int cur, Integer next) {
        return cur == 1 ? next : next *10 + next;
    }

}
