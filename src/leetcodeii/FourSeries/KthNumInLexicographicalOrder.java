package leetcodeii.FourSeries;

import leetcodeii.Tracker;

import javax.sound.midi.Track;

/**
 * Created by Erebus on 9/17/17.
 */
public class KthNumInLexicographicalOrder implements Tracker {


    /**
     * Use trie instead of direct construct
     *
     * direct construct :
     *
     * digits = n's digits count,
     * SUM(i=0, digits-1)[10^i] is the range
     * k th % range = left,
     * for left, digits -1, calculate the sub range, call it again,
     * ..... till digit = 1 range
     *
     * Trie:
     * key: calculate the gap between cur node to next (cousin) node.
     *
     * @param n
     * @param k
     * @return
     */
    public int findKthNumber(int n, int k) {
        int curr = 1;
        k = k - 1;
        while (k > 0) {
            /**
             *
                 -> n=50, n1=1, n2=2
                 <- steps = 11
             1. steps<=k, n=50, k=17, cur=1, steps=11
                 -> n=50, n1=2, n2=3
                 <- steps = 11
             2. steps>k, n=50, k=6, cur=2, steps=11
                 -> n=50, n1=20, n2=21
                 <- steps = 1
             1. steps<=k, n=50, k=5, cur=20, steps=1
                 -> n=50, n1=21, n2=22
                 <- steps = 1
             1. steps<=k, n=50, k=4, cur=21, steps=1
                 -> n=50, n1=22, n2=23
                 <- steps = 1
             1. steps<=k, n=50, k=3, cur=22, steps=1
                 -> n=50, n1=23, n2=24
                 <- steps = 1
             1. steps<=k, n=50, k=2, cur=23, steps=1
                 -> n=50, n1=24, n2=25
                 <- steps = 1
             1. steps<=k, n=50, k=1, cur=24, steps=1

             25
             */
            int steps = calSteps(n, curr, curr + 1);
            if (steps <= k) {
                cout(String.format("1. steps<=k, n=%d, k=%d, cur=%d, steps=%d", n, k, curr, steps));
                curr += 1;
                k -= steps;
            } else {
                cout(String.format("2. steps>k, n=%d, k=%d, cur=%d, steps=%d", n, k, curr, steps));
                curr *= 10;
                k -= 1;
            }
        }
        return curr;
    }
    //use long in case of overflow

    /**
     * Let n1 = curr, n2 = curr + 1.
     n2 is always the next right node beside n1's right most node (who shares the same ancestor "curr")
     (refer to the pic, 2 is right next to 1, 20 is right next to 19, 200 is right next to 199).
     * @param n
     * @param n1
     * @param n2
     * @return
     */
    public int calSteps(int n, long n1, long n2) {
        cout(String.format("  -> n=%d, n1=%d, n2=%d", n, n1, n2));
        //e.g. n=50, n1=1, n2 =2
        int steps = 0;
        while (n1 <= n) {

            steps += Math.min(n + 1, n2) - n1;
            n1 *= 10;
            n2 *= 10;
        }
        cout(String.format("  <- steps = %d",steps));
        return steps;
    }

    public static void main(String [] args){
        KthNumInLexicographicalOrder k = new KthNumInLexicographicalOrder();
        k.cout(k.findKthNumber(50, 18));
    }
}
