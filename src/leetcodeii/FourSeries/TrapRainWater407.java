package leetcodeii.FourSeries;

import leetcodeii.Tracker;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by Erebus on 9/15/17.
 */
public class TrapRainWater407 implements Tracker{

    public int trapRainWater(int[][] grid) {
        /**
         core: use queue to go through the grid, offer neighbours
         -> unvisited cell (neighbour offerred): it's lowest edge(neigbhour) is current visiting cell
         e.g. 1 surrounded by 234, cur must be 2 (Queue ASC), so 2-1 is the water trapped.
         --------------trapped water is calculated when checking and offering neighbours
         populate height:
         when visiting cur cell, and checking neighbours, update cur cell's height to max(cur_h, neighbour_H).
         ----------->so higher edge also effect lower cells in the same row/column. (populated)
         */

        //check empty
        if(grid==null||grid.length<2||grid[0].length<2){
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        int sum = 0;

        //build Queue
        PriorityQueue<Cell> q = new PriorityQueue<>(1, (c1, c2)->c1.height-c2.height);

        //offer the borders
        for(int ii = 0; ii< m; ii++){
            visited[ii][0]=true;
            visited[ii][n-1]=true;
            q.offer(new Cell(ii, 0, grid[ii][0]));
            q.offer(new Cell(ii, n-1, grid[ii][n-1]));
        }
        for(int jj = 0; jj< n; jj++){
            visited[0][jj]=true;
            visited[m-1][jj]=true;
            q.offer(new Cell(0, jj, grid[0][jj]));
            q.offer(new Cell(m-1, jj, grid[m-1][jj]));
        }

        int[][] neighbourVariation = {{0,1},{0,-1},{1,0},{-1,0}};
        while(!q.isEmpty()){
            Cell cur = q.poll();
            for(int [] nb : neighbourVariation){
                int row = cur.row+nb[0];
                int column = cur.column+nb[1];
                if(row>0&&row<m&&column>0&&column<n && !visited[row][column]){
                    visited[row][column]=true;
                    //cur is always the lowest edge of the 4 edges
                    sum += Math.max(0, cur.height - grid[row][column]);
                    //populate higher edge to visited node.
                    //!! instead of update the grid itself. --> could vary by different cases.
                    q.offer(new Cell(row, column, Math.max(grid[row][column], cur.height)));
                }
            }
        }
        return sum;
    }

    public static class Cell{
        int row;
        int column;
        int height;
        Cell(int r, int c, int h){
            this.row = r;
            this.column = c;
            this.height = h;
        }
    }


    /**
     * FIXME, wrong on [[12,13,1,12],[13,4,13,12],[13,8,10,12],[12,13,12,12],[13,13,13,13]], should be 14, not 15
     * error: notice the 9, both XY directions results in 9, but it's actually 8 cuz 4<-8<-10<-12
     * [
     [12,13,1,12],
     [13,4,13,12],
     [13,8,10,12],
     [12,13,12,12],
     [13,13,13,13]
     ]
     begin horizontal
     [
     [9,0],
     [4,2],
     [0,0]
     ]
     * @param grid
     * @return
     */
    public int trapRainWaterWrong(int[][] grid) {
        if(grid==null || grid.length<3 || grid[0]==null || grid[0].length<3){
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[][] container = new int[m-2][n-2];
        cout("begin horizontal");
        for(int ii = 1; ii< m-1; ii++){
            int max= 0;
            int maxIndex = 0;
            for(int jj = 0; jj<n; jj++){
                if(max<grid[ii][jj]){
                    maxIndex= jj;
                    max = grid[ii][jj];
                }
            }
            int start =1;
            int end = n-2;
            int left = grid[ii][start -1];
            int right = grid[ii][end+1];
            while(start<maxIndex){
                int cur = grid[ii][start];
                left = Math.max(left, cur);
                container[ii-1][start++-1]=left-cur;
            }
            while(end>maxIndex){
                int cur = grid[ii][end];
                right = Math.max(right, cur);
                container[ii-1][end---1]=right-cur;
            }
        }
        cout(container);
        cout("begin vertical");
        for(int jj=1; jj<n-1; jj++){
            int max = 0;
            int maxIndex= 0;
            for(int ii = 0; ii<m; ii++){
                if(max<grid[ii][jj]){
                    maxIndex= ii;
                    max = grid[ii][jj];
                }
            }
            int start =1;
            int end = m-2;
            int top = grid[start -1][jj];
            int bottom = grid[end+1][jj];
            while(start<maxIndex){
                int cur = grid[start][jj];
                top = Math.max(top, cur);
                container[start-1][jj-1]=Math.min(top-cur, container[start-1][jj-1]);
                start++;
            }
            while(end>maxIndex){
                int cur = grid[end][jj];
                bottom = Math.max(bottom, cur);
                container[end-1][jj-1]=Math.min(bottom-cur, container[end-1][jj-1]);
                end--;
            }
        }
        cout(container);
        return Arrays.stream(container).map(r->Arrays.stream(r).reduce(0, (a, b)->a+b)).reduce(0, (aa, bb)->aa+bb);
    }

    public static void main(String [] args){
        TrapRainWater407 t = new TrapRainWater407();
        int[][] in = {{12,13,1,12},{13,4,13,12},{13,8,10,12},{12,13,12,12},{13,13,13,13}};
        t.cout(in);
        t.cout(t.trapRainWaterWrong(in));

    }
}
