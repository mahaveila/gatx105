package leetcodeii.PriorityQueue;

import leetcodeii.Tracker;

import java.util.*;

/**
 * Created by Erebus on 4/7/18.
 */
public class Skyline implements Tracker{

    public static void main(String [] args){
        Skyline s = new Skyline();
        int [][] input= {{1,2,1},{1,2,2},{1,2,3}};
        s.cout(s.getSkyline(input));
    }

    public static class B{
        int x;
        int y;
        boolean st;
        public B(int x, int y, boolean st){
            this.x = x;
            this.y = y;
            this.st = st;
        }
    }

    public Skyline() {
    }

    public List<int[]> getSkyline(int[][] buildings) {

        //this logics here when the points are overlapping, put the shorter one first.
        PriorityQueue<B> q = new PriorityQueue<B>(1, (b1, b2)->{
            if(b1.x!=b2.x){
                return b1.x - b2.x;
            } else if(b1.st && b2.st){
                //both starts, use the smaller one first, just for not adding this points.
                return b2.y-b1.y;
            } else if(b1.st){
                return -1;
            } else if(b2.st){
                return 1;
            } else {
                //both ends, use shorter first one to compute
                return b1.y - b2.y;
            }
        });
        for(int [] bd : buildings){
            q.offer(new B(bd[0], bd[2], true));
            q.offer(new B(bd[1], bd[2], false));
        }
        //now we have the building points sorted by increasing sequence of the x, and decreasing sequence of the y.
        //i.e. higher building always come and cover the shorter building.

        List<int[]> res = new ArrayList<>();

        //in the meanwhile, we need to maintain the current max height of the buildings.
        TreeMap<Integer, List<Integer>> height = new TreeMap<>(); // a sorted tree set
        height.put(0,new ArrayList<>(Arrays.asList(new Integer[0])));
        //let's handle the overlapping corner case for clarification here
        int pre = 0;
        while(!q.isEmpty()){

            B b = q.poll();
            cout("polling: B.x: " + b.x + ", B.y: " + b.y+ (b.st?" Start":" End"));
            if(b.st){
                //add the taller one first
                if(!height.containsKey(b.y)){
                    height.put(b.y, new ArrayList<>());
                }
                height.get(b.y).add(b.y);
            } else {
                //removed shorter one first
                height.get(b.y).remove(height.get(b.y).size()-1);
                if(height.get(b.y).size()==0){
                    height.remove(b.y);
                }
            }
            int h = height.lastKey();
            cout(" current top = " + h + ", pre = " + pre);
            if(h!=pre){
                int[] tmp = {b.x, h};
                cout("                          ------------> " + stringfy(tmp));
                res.add(tmp);
                pre = h;
                //corner case, add/remove a smaller building in a larger building, we do not want to record this point.
                /*
                for [[0,2,3],[2,5,3]] -> B0,3 B2,3  B2,3 B5,3
                sorted B0,3 B2,3(st) B2,3(ed) B5,3
                add 0,3 start, pre = 0, so init pre need to be -1
                */
            }
        }

        return res;
    }
}
