package leetcodeii.Arrays;

import leetcodeii.Tracker;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Erebus on 3/19/18.
 */
public class ShortestDistance317 implements Tracker{

    public static void main(String [] args){
        ShortestDistance317 s = new ShortestDistance317();

        int[][] grid = {{1,0,2,0,1},{0,0,0,0,0},{0,0,1,0,0}};
        s.cout(s.shortestDistance(grid));

        int[][] grid2 = {{0,2,1},{1,0,2},{0,1,0}};
        s.cout(s.shortestDistance(grid2));
    }

    public int shortestDistance(int[][] grid) {
        cout(grid);
        if(grid==null||grid.length<1||grid[0].length<1){
            return -1;
        }
        int m =grid.length;
        int n =grid[0].length;

        int[][] buildingCount = new int[m][n];
        int building = 0;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j]%10==1){
                    cout("Traverse from["+i+"]["+j+"]");
                    boolean[][] visited = new boolean[m][n];
                    Queue<int[]> q = new LinkedList<>();
                    int[] start= {i,j,0};
                    q.offer(start);
                    traverse(grid, visited, q, buildingCount);
                    building++;
                }
            }
        }
        cout(grid);
        int dist = Integer.MAX_VALUE;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                //if a empty land
                if(grid[i][j]%10==0){
                    //if land reached all buildings
                    if(buildingCount[i][j]==building){
                        dist = Math.min(dist, grid[i][j]/10);
                    }
                }
            }
        }
        return dist==Integer.MAX_VALUE?-1:dist;
    }

    /**
     * DFS is wrong, will not have shortest path all the time
     * @param G
     * @param V
     */
    private void traverse(int[][] G, boolean[][] V, Queue<int[]> q, int[][] B){

        while(!q.isEmpty()){
            int[] start = q.poll();
            int i = start[0];
            int j = start[1];
            int step = start[2];
            if(i<0 || j<0 || i>=G.length || j>=G[0].length ||
                    //visited
                    V[i][j]  ||
                    //cannot pass, and not starting from a building
                    (G[i][j]%10!=0 && step!=0)){
                continue;
            }
            G[i][j] = G[i][j]+step*10;
            B[i][j] +=1;
            V[i][j] = true;
            int [] left= {i, j-1, step+1};
            int [] right= {i, j+1, step+1};
            int [] up= {i-1, j, step+1};
            int [] bottom= {i+1, j, step+1};
            q.offer(left);
            q.offer(right);
            q.offer(up);
            q.offer(bottom);
        }
    }
}
