package leetcodeii.Graph;

import leetcodeii.Tracker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Erebus on 3/13/18.
 */
public class NumberOfIslandsII implements Tracker{


    public static void main(String [] args){
        NumberOfIslandsII n = new NumberOfIslandsII();

        Point[] input = new Point[4];
        Point [] input2 = {new Point(0,0), new Point(0,1),new Point(2,2),new Point(2,2)};
        List<Integer> res=n.numIslands2(4,5,input2);
        n.cout(res);
    }

    static class Point {
      int x;
      int y;
      Point() { x = 0; y = 0; }
      Point(int a, int b) { x = a; y = b; }
  }

    Set<Integer> islands;
    int [] sea;
    int row;
    int col;
    //connecting 2 island into one, reduce,
    //e.g. island size 5, made from 2 & 2 & 1
    //for 2,  discover +1, +1, connect -1 = 1
    //for 2, discover +1, +1, connect -1 = 1
    //for 1, discover +1, connect -2 = -1
    //total = 1+1-1=1
    int count = 0;
    Set<Integer> visited;
    //neighbor might contains -1, which means out of boundary
    public int[] getNeighbor(int i){
        int r = i/col;
        int c = i%col;
        int[] nb = new int[4];
        nb[0] = toSea(r-1, c);
        nb[1] = toSea(r+1, c);
        nb[2] = toSea(r, c-1);
        nb[3] = toSea(r, c+1);
        return nb;
    }

    public void connect(int i, int j){
        //here we need to maintain the roots of the island
        int ri = root(i);
        int rj = root(j);
        if(ri!=rj){

            count--;
            sea[ri] = rj;
        }
    }

    public int root(int i){
        return sea[i]==i?i:root(sea[i]);
    }

    public int toSea(int n, int m){
        //out of boundary
        if(n<0 || m<0 || n>=row || m>=col){
            return -1;
        }
        return n*col + m ;
    }

    /**
     * Simplifies version, only check the flipped neighbor, NO need to do DFS!!!
     * @param n
     * @param m
     * @param operators
     * @return
     */
    public List<Integer> numIslands2(int n, int m, Point[] operators) {
        // write your code here
        islands = new HashSet<>();
        sea = new int[m*n];
        row = n;
        col = m;
        for(int i=0; i<sea.length; i++){
            sea[i]=i;
        }
        //sea units set created
        List<Integer> res = new ArrayList<>();
        if(operators==null || operators.length<1){
            return res;
        }
        Set<Integer> newIslands = new HashSet<>();
        //now setting the island
        for(Point op : operators){
            int x = op.x;
            int y = op.y;
            int s = toSea(x, y);
            if(newIslands.contains(s)){
                res.add(count);
                continue;
            }
            newIslands.add(s);
            if(s<0){
                //out of boundary ops
            } else {
                //new island found
                count++;
                islands.add(s);
                //simply connect neighbor
                connectIsland(s);
            }
            res.add(count);
        }
        return res;
    }

    //one dimensional sea now has island connected.
    public void connectIsland(int i){
        int [] nb = getNeighbor(i);
        for(int n : nb){
            if(n<0){
                continue;
            }
            //when it's connecting neighbor? when neighbor is an island as well
            if(islands.contains(n)){
                connect(i,n);
            }
        }
    }





    /**
     * FIXME: TLE, Below Method is time limit exceed
     * @param n: An integer
     * @param m: An integer
     * @param operators: an array of point
     * @return: an integer array
     */
    public List<Integer> numIslands2TLE(int n, int m, Point[] operators) {
        // write your code here
        islands = new HashSet<>();
        sea = new int[m*n];
        row = n;
        col = m;
        for(int i=0; i<sea.length; i++){
            sea[i]=i;
        }
        cout(sea);
        //sea units set created

        List<Integer> res = new ArrayList<>();
        if(operators==null || operators.length<1){
            return res;
        }
        //now setting the island
        for(Point op : operators){
            int x = op.x;
            int y = op.y;
            int s = toSea(x, y);
            cout(String.format("x=%d, y=%d, s=%d", x, y, s));
            if(s<0){
                //out of boundary ops
            } else {
                //new island found
                visited = new HashSet<>();
                count++;
                islands.add(s);
                //every time try connect from that island
                connectIslandTLE(s);
                cout("after connected: \n"+stringfy(sea));
                //need to track current number of island
            }
            res.add(count);
        }
        return res;
    }

    //one dimensional sea now has island connected.

    /**
     * FIXME: TLE
     * @param i
     */
    public void connectIslandTLE(int i){
        if(visited.contains(i)){
            return;
        } else {
            visited.add(i);
        }
        if(!islands.contains(i)){
            //not an island, return, main purpose is to control entry point
            return;
        }
        int [] nb = getNeighbor(i);
        for(int n : nb){
            cout("neighbor: "+ n);
            if(n<0){
                //not a valid coordinate
                cout("invalid");
                continue;
            }
            //when it's connecting neighbor? when neighbor is an island as well
            if(islands.contains(n)){
                cout("connecting neighbor: " + n);
                connect(i,n);
                connectIslandTLE(n); //keep search surrounding island
            }
        }
    }


}
