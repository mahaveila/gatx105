/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leetcodeii;

import java.util.*;
import java.util.function.Consumer;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * @author Erebus
 */
public interface Tracker<K, T> {

    Logger LOG = Logger.getLogger("Tracker");

    default void log(String str) {
        LOG.info(str);
    }

    default void log(String[] strings) {
        Arrays.stream(strings).forEach(s -> LOG.info(s));
    }

    default void log(int[] ints) {
        final StringBuilder sb = new StringBuilder();
        Arrays.stream(ints).forEach(s -> sb.append(sb.length() == 0 ? "[" : ",").append("" + s));
        sb.append("]");
        LOG.info(sb.toString());
    }

    default void cout(String str) {
        System.out.println(str);
    }

    default void cout(int i) {
        System.out.println("" + i);
    }

    default void cout(int[] ints) {
        final StringBuilder sb = new StringBuilder();
        Arrays.stream(ints).forEach(s -> sb.append(sb.length() == 0 ? "[" : ",").append("" + s));
        sb.append("]");
        System.out.println(sb.toString());
    }

    default void cout(double[] ints) {
        final StringBuilder sb = new StringBuilder();
        Arrays.stream(ints).forEach(s -> sb.append(sb.length() == 0 ? "[" : ",").append("" + s));
        sb.append("]");
        System.out.println(sb.toString());
    }

    default void cout(T [] ints) {
        final StringBuilder sb = new StringBuilder();
        Arrays.stream(ints).forEach(s -> sb.append(sb.length() == 0 ? "[" : ",").append(s.toString()));
        sb.append("]");
        System.out.println(sb.toString());
    }

    default void coutInBinary(int [] ints){
        final StringBuilder sb = new StringBuilder();
        Arrays.stream(ints).forEach(s -> sb.append(sb.length() == 0 ? "[" : ",").append(Integer.toBinaryString(s)));
        sb.append("]");
        System.out.println(sb.toString());
    }

    default void coutIntBinary(Collection collection){
        final StringBuilder sb = new StringBuilder();
        collection.stream().forEach(s -> sb.append(sb.length() == 0 ? "[" : ",").append(Integer.toBinaryString(((Integer)s).intValue())));
        sb.append("]");
        System.out.println(sb.toString());
    }

    default void cout(List<T> objs){
        System.out.println(stringfy(objs));
    }
    default void cout(Set<T> objs) {System.out.println(stringfy(objs.stream().collect(Collectors.toList())));}
    default void cout(Map<K, T> objs) {System.out.println(objs.values().stream().map(c->c.toString()).collect(Collectors.toList()));}

    default String stringfy(List<T> objs){
        StringBuilder sb = new StringBuilder();
        objs.forEach(o -> {
            sb.append(sb.length()>0?",":"[").append(stringfy((T) o));
        });
        return sb.append("]").toString();
    }

    default String stringfyString(List<String> strs){
        StringBuilder sb = new StringBuilder();
        strs.forEach(o -> {
            sb.append(sb.length()>0?",":"[").append(stringfy((T) o));
        });
        return sb.append("]").toString();
    }

    default String stringfy(T [] objs){
        StringBuilder sb = new StringBuilder();
        Arrays.stream(objs).forEach(o -> {
            sb.append(sb.length()>0?",":"[").append(stringfy((T) o));
        });
        return sb.append("]").toString();
    }

    default String stringfy(T o){
        return o==null?"":o.toString();
    }

    default void coutlistlist(List<List<T>> output){
        List<String> ls = ((List<String>) output.stream().map(l->"    " + stringfy(l)+"\n").collect(Collectors.toList()));
        System.out.println(stringfyString(ls));
    }
}
