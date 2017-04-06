/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leetcodeii;

import java.util.Arrays;
import java.util.logging.Logger;

/**
 *
 * @author Erebus
 */
public interface Tracker {
    Logger LOG = Logger.getLogger("Tracker");

    default void print(String [] strings){
        Arrays.stream(strings).forEach(s -> LOG.info(s));
    }
    default void print(int [] ints){
        final StringBuilder sb = new StringBuilder();
        Arrays.stream(ints).forEach(s -> sb.append(sb.length()==0?"[":",").append(""+s));
        sb.append("]");
        LOG.info(sb.toString());
    }
    default void print(String str){
        System.out.println(str);
    }
    default void print(int i ) {System.out.println(""+i);}

    default void log(String str){
        LOG.info(str);
    }
}
