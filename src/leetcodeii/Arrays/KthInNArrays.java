package leetcodeii.Arrays;

import leetcodeii.Tracker;

import java.util.PriorityQueue;

/**
 * Created by Erebus on 3/4/18.
 */
public class KthInNArrays implements Tracker {

    public int KthInArrays(int[][] arrays, int k) {
        // write your code here
        int res = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>(1,(a, b)->b-a);
        for(int[] arr: arrays){
            for(int i : arr){
                queue.offer(i);
            }
        }
        while(k-->0){
            res=queue.remove();
        }
        return res;
    }

    public static void main(String [] args){
        KthInNArrays k = new KthInNArrays();
        int[][] A = {
                {9,3,2,4,7},{1,2,3,4,8}
        };
        k.cout(k.KthInArrays(A, 3));
    }
}
