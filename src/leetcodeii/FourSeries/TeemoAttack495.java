/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leetcodeii.FourSeries;

import java.util.Arrays;
import java.util.stream.IntStream;
import static leetcodeii.Tracker.LOG;

/**
 *
 * @author Erebus
 */
public class TeemoAttack495 {
    
    /**
     * Wrong on 1,2,3,4,5,6 & 1
     * 2nd edition wrong on 1,2 & 2
     * Reason (a,b) in reduce, a represents the accumulated value
     * @param jobs
     * @param duration
     * @return 
     */
    public int teemo(final int [] jobs, final int duration){
        if(jobs == null || jobs.length <1){
            return 0;
        }
        return Arrays.stream(jobs).reduce(0,(a,b)->{
            int range = a+duration>b?b:a+duration;
            LOG.info("a: " + a + ", b: " + b + " -> range: " + range);
            return range + a;
                    });
    }
    
    public int teemoII(final int [] jobs, final int duration){
        if(jobs == null || jobs.length <1){
            return 0;
        }
        return IntStream.range(0, jobs.length-1).map(i->jobs[i]+duration>jobs[i+1]?jobs[i+1]-jobs[i]:duration).sum() + duration;
    }
    
    public static void main(String [] args){
        TeemoAttack495 ta = new TeemoAttack495();
        int[] jobs = {1,2,3,4,5};
        int[] jobs2 = {1,4};
        int[] jobs3 = {1,2};
        LOG.info(""+ta.teemoII(jobs, 1));
        LOG.info(""+ta.teemoII(jobs2, 2));
        LOG.info(""+ta.teemoII(jobs3, 2));
    }
    
}
